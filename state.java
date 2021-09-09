public class State {

    Tile [] state; //data
    State parent;
    ArrayList<State> children;

    Boolean pruned;
    int heuristicValue;

    public State() {
        this.state = new Tile[9];
        for(int i = 0; i < 9; i++){
            this.state[i] = new Tile(i);
        }
    }

    public Tile stateGetTile(int n) {
        return this.state[n];
    }

    public State stateGetState() {
        return this;
    }
    
    public Tile[] stateGetStateConf() {
        return this.state;
    }

    public ArrayList<int> stateReturnAction() {
        ArrayList<int> actionStates = new ArrayList<int>();
        for(int i = 0; i < state.length; i++) {
            if(!state[i].tileIsTileMarked()) {
                actionStates.add(state[i].getLocation());
            }
        }
        return actionStates;
    }

    public void stateMarkTile(int n, int mark) {

        if(!this.state[n].tileIsTileMarked()) {
            state[n].tileSetMark(mark);
        }else {
            System.out.println("tile is marked. Are you making a mistake?")
        }
    }

    public static void main(String[] args) {
        
    }
}]