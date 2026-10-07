package pres;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresSpringAnnotation {
    static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("java");

        IMetier metier = applicationContext.getBean((IMetier.class));
        System.out.println("RES = "+metier.calcul());
    }
}
