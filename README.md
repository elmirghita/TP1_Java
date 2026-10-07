# TP1 – Injection des dépendances et couplage faible

## Objectif

Ce TP met en pratique le principe du **couplage faible** et les différentes manières de faire l'**injection des dépendances** en Java :

- par instanciation statique ;
- par instanciation dynamique (réflexion) ;
- avec le framework **Spring** (version XML et version annotations).

---

## Structure du projet

Le projet est organisé en trois couches :

- **dao** : couche d'accès aux données (`IDao`, `DaoImp`)
- **metier** : couche des traitements (`IMetier`, `MetierImpl`)
- **pres** : couche présentation, contenant une classe de test pour chaque méthode d'injection

---

## Partie 1 : Couche DAO

### 1. Interface `IDao`

L'interface `IDao` définit le contrat de la couche d'accès aux données. Elle contient une seule méthode, `getData()`, qui retourne une donnée.

### 2. Implémentation `DaoImp`

La classe `DaoImp` implémente l'interface `IDao`. Elle simule la récupération d'une donnée (par exemple depuis une base de données) et la retourne.

---

## Partie 2 : Couche Métier

### 3. Interface `IMetier`

L'interface `IMetier` définit la méthode `calcul()`, qui représente le traitement métier de l'application.

### 4. Implémentation `MetierImpl` avec couplage faible

La classe `MetierImpl` implémente `IMetier`. Elle récupère la donnée fournie par la couche DAO, puis effectue un calcul dessus.

Pour respecter le **couplage faible**, elle possède un attribut de type `IDao` (l'interface) et non de type `DaoImp` (l'implémentation). La dépendance est fournie de l'extérieur grâce à un setter `setDao()`.

> **Couplage fort vs couplage faible** : avec un couplage fort, la classe métier créerait elle-même l'objet DAO concret, ce qui l'obligerait à être modifiée à chaque changement d'implémentation. Avec le couplage faible, la classe reste **fermée à la modification et ouverte à l'extension** (principe Open/Closed).

---

## Partie 3 : Injection des dépendances

### a. Par instanciation statique

Les objets DAO et Métier sont créés directement avec le mot-clé `new` dans la couche présentation, puis l'objet DAO est injecté dans l'objet Métier via le setter.

**Avantage** : simple à mettre en place.
**Limite** : pour changer d'implémentation, il faut modifier le code source et recompiler.

### b. Par instanciation dynamique

Les noms complets des classes à utiliser sont écrits dans un fichier de configuration `config.txt`. Au lancement, le programme lit ce fichier, charge les classes avec `Class.forName()` et crée les objets par **réflexion**. L'injection est ensuite faite en appelant dynamiquement la méthode `setDao()`.

**Avantage** : pour changer d'implémentation, il suffit de modifier le fichier de configuration, sans toucher au code ni recompiler.

> **Remarque** : le nom complet d'une classe correspond à son package suivi de son nom (par exemple `dao.DaoImp`). Le dossier `src/main/java` ne fait pas partie du nom du package, sinon on obtient une `ClassNotFoundException`.

### c. Avec le framework Spring

Spring prend en charge la création des objets et l'injection des dépendances. Il faut d'abord ajouter la dépendance `spring-context` dans le fichier `pom.xml`.

#### Version XML

Les objets (appelés *beans*) et leurs dépendances sont déclarés dans un fichier `applicationContext.xml`. Spring lit ce fichier, crée les objets et injecte automatiquement le DAO dans la couche métier. La couche présentation récupère ensuite l'objet métier depuis le contexte Spring.

#### Version annotations

Au lieu d'un fichier XML, on ajoute des annotations directement dans les classes :

- `@Component` pour indiquer à Spring les classes qu'il doit instancier ;
- `@Autowired` pour indiquer la dépendance à injecter automatiquement.

Spring parcourt les packages indiqués, détecte les classes annotées et effectue l'injection.

---


## Conclusion

Ce TP montre l'intérêt du **couplage faible** : en faisant dépendre les classes d'interfaces plutôt que d'implémentations, on obtient une application plus facile à maintenir et à faire évoluer.

- L'**instanciation statique** est simple mais oblige à modifier le code pour changer d'implémentation.
- L'**instanciation dynamique** supprime cette contrainte grâce à un fichier de configuration et à la réflexion.
- **Spring** automatise l'injection des dépendances, soit par un fichier XML, soit par annotations, ce qui est l'approche utilisée dans les projets professionnels.
