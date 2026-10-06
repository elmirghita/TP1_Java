package Metier;

import Dao.IDao;

public class MetierIml implements IMetier{
    private IDao dao;
    public MetierIml(IDao dao){
        this.dao = dao;
    }


    @Override
    public double calcul() {
        double t= dao.getData();
        return t*43/3;
    }
}
