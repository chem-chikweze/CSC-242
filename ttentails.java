import java.util.TreeSet;
import java.util.SortedSet;

public class ttentails {

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
            // set model(p) as true;
            // set model(p) as false;
            int model_p_true = p;
            int model_p_false = -p;
            return (tt_check_all(kb, a, symbols, model.add(model_p_true)) && tt_check_all(kb, a, symbols, model.add(model_p_false)));
        }
    }

    private boolean pl_true(sentence kb, clause model) {
        return false;
    }
    
}
