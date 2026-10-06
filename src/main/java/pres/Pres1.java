package pres;

import metier.MetierIml;
import ext.DaoImpV2;

public class Pres1 {
    public static void main(String[] args){
        DaoImpV2 d = new DaoImpV2();
        MetierIml metier = new MetierIml();
        metier.setDao(d); //Injection des dependancs via le setter
        System.out.println("RES= "+metier.calcul());
    }
}
