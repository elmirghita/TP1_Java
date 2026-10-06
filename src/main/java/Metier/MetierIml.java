package Metier;

import Dao.IDao;

public class MetierIml implements IMetier{
    private IDao dao;

    public MetierIml(IDao dao) {
        this.dao = dao;
    }

    public MetierIml() {
    }

    @Override
    public double calcul() {
        double t= dao.getData();
        double res = t * 12 * Math.PI/2 * Math.cos(t);
        return res;
    }

    public void setDao(IDao dao){
        this.dao = dao;
    }
}
