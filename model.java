public class model {
    int index;
    boolean value;
    model next;
    
    public model() {
        
    }

    public model(int i, Boolean b){
        index = i;
        value = b;
        next = null;
    }

}
