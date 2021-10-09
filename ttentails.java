import java.util.TreeSet;
import java.util.SortedSet;

public class ttentails {
    public static void main(String[] args) {
        ttentails tt = new ttentails();

        sentence kb = new sentence();
        clause c = new clause();
        c.add(1);
        clause c1 = new clause();
        c1.add(-1);
        c1.add(2);

        kb.addClause(c);
        kb.addClause(c1);

        sentence a = new sentence();
        clause c2 = new clause();
        c2.add(2);
        a.addClause(c2);

        System.out.println(tt.entails(kb, a));
        
    }

    public Boolean entails(sentence kb, sentence a) {
        SortedSet<Integer> symbols = new TreeSet<Integer>();
        symbols.addAll(kb.getSymbols()) ;
        symbols.addAll(a.getSymbols());
        return tt_check_all(kb, a, symbols, new clause());
    }

    private Boolean tt_check_all(sentence kb, sentence a, SortedSet<Integer> symbols, clause model) {
        if(symbols.isEmpty()) {
            if(pl_true(kb, model)) {
                return pl_true(a, model);
            } else return true;
        }
        else {
            int p = symbols.first();
            symbols.remove(p);
            int model_p_true = p;
            int model_p_false = -p;
            return (tt_check_all(kb, a, symbols, model.add(model_p_true)) && tt_check_all(kb, a, symbols, model.add(model_p_false)));
        }
    }

    private boolean pl_true(sentence kb, clause model) {
        Boolean ret = true;
        for(clause cl : kb.clauses) {
            if(!pl_clause(cl, model)) {
                ret  = false;
            } else {
                ret = true;
            }
        }
        return ret;
    }

    public boolean pl_clause(clause cl, clause model) {
        for(int i: cl.literals) {
            if(model.contains(Math.abs(i))) {
                if(model.containsStrict(i)) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
