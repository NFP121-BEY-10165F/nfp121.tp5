package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

	protected java.util.Vector<T> table = new java.util.Vector<T>();

	public int size() {
		return table.size();
	}

	public Iterator<T> iterator() {
		return table.iterator();
	}

	public boolean add(T t) {
		if(table.contains(t))
            return false;
        else 
        {
          this.table.add(t);
          return true;
        } 
    }

	public Ensemble<T> union(Ensemble<? extends T> e) {
        if(e!=null)
        {
          Ensemble unE = new Ensemble();
          unE.addAll(this);
          unE.addAll(e);
          return unE; 
        }
        return null;
    }

	public Ensemble<T> inter(Ensemble<? extends T> e) {
        if(e!=null)
        {
            Ensemble intE = new Ensemble();
            intE.addAll(this);
            intE.retainAll(e);
            return intE;   
        }
        return null;
    }
	public Ensemble<T> diff(Ensemble<? extends T> e) {
        if(e!=null)
        {
            Ensemble en= new Ensemble();
            en.addAll(this);
            en.removeAll(this.inter(e));
            return en;
        }
        return null;
    }

	Ensemble<T> diffSym(Ensemble<? extends T> e) {
        if(e!=null)
        {
            Ensemble enD = new Ensemble();
            enD.addAll(this.union(e));
            enD.removeAll(this.inter(e));
            return enD; 
        }
        return null;
    }
}
