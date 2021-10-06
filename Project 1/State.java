import java.util.ArrayList;

public class State {

    Tile [] state; //data
    State parent;
    String statePlayer;
    ArrayList<State> children;

    Boolean pruned;
    int heuristicValue;


    public State() {
        this.state = new Tile[9];
        for(int i = 0; i < 9; i++){
            this.state[i] = new Tile(i);
        }
        statePlayer = "MAX";
    }

    public State(State s) {
        this.state = new Tile[s.state.length];
        for(int i = 0; i < 9; i++){
            this.state[i] = new Tile(s.state[i]);
        }
        statePlayer = s.statePlayer;
    }

    public Tile stateGetTile(int n) {
        return stateGetStateConfig()[n];
    }

    public State stateGetState() {
        return this;
    }
    
    public Tile[] stateGetStateConfig() {
        return this.state;
    }

    public ArrayList<Integer> stateReturnAction() {
        ArrayList<Integer> actionStates = new ArrayList<Integer>();
        for(int i = 0; i < stateGetStateConfig().length; i++) {
            if(!stateGetStateConfig()[i].tileIsTileMarked()) {
                actionStates.add(stateGetStateConfig()[i].tileGetLocation());
            }
        }
        return actionStates;
    }

    public void statePrintConf() {
        for(int i = 0; i < state.length; i++) {

            System.out.print(state[i].tileGetMark() + " ");
            if(i == 2 || i == 5) {
                System.out.println("");
            }
        }
        System.out.println();
    }

    public void stateSetPlayer(String play) {
        statePlayer = play;

    }

    public void stateMarkTile(int n, int mark) {

        if(!stateGetStateConfig()[n].tileIsTileMarked()) {
            stateGetStateConfig()[n].tileSetMark(mark);
        }else {
            System.out.println("tile is marked. Are you making a mistake?");
        }
    }
}