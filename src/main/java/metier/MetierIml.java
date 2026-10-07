package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierIml implements IMetier{
    //@Autowired
    @Qualifier("d")
    private IDao dao;

    public MetierIml(@Qualifier("d") IDao dao) {
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
