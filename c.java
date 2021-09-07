public class State {

    int [] state; 

    public State() {
        state = new int[9];
        for(int i = 0; i < 9; i++){
            state[i] = -1;
        }
    }

    public int stateCheckTile(int n) {
        return state[n];
    }

    public int[] stateGetStateConf() {
        return state;
    }

    public void stateMarkTile(int n, int mark) {
        if(state[n] != -1) {
            state[n] = mark;
        }
    }

    public static void main(String[] args) {
    }
    
}