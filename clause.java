import java.util.HashSet;
import java.util.Set;

public class clause {
    Set<Integer> literals;

    public clause() {
        literals = new HashSet<>();
    }

    public clause(Set<Integer> lit) {
        literals = lit;
    }

    public clause add(int i) {
        literals.add(i);
        return this;
    }

    public boolean contains(int j) {
        for(int i : literals){
            if(Math.abs(i) == j) return true;
        }
        return false;
    }

    public boolean containsStrict(int j) {
        for(int i : literals){
            if(i == j) return true;
        }
        return false;
    }

    public void addToClause(int i) {
        literals.add(i);
    }

    public void removeFromClause(int i){
        literals.remove(i);
    }

    public clause unionClause(clause c) {
        Set<Integer> lit = c.getLiterals();
        Set<Integer> ret = this.literals;
        if(lit.addAll(ret)) {
            return new clause(lit);
        }else{ System.out.println("union failed"); return null;}
        
    }

    public Set<Integer> getLiterals() {
        return this.literals;
    }

    public Set<Integer> getSymbols() {
        Set<Integer> ret = new HashSet<Integer>();
        for (Integer integer : literals) {
            ret.add(Math.abs(integer));
        }
        return ret;
    }
}
