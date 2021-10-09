import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class sentence {
    ArrayList<clause> clauses;
    
    public sentence() {
        clauses = new ArrayList<>();
    }

    public void addClause(clause e){
        clauses.add(e);
    }

    public Set<Integer> getSymbols() {
        Set<Integer> ret = new HashSet();
        for (clause clause : clauses) {
            ret.addAll(clause.getSymbols());
        }
        return ret;
    }
}
