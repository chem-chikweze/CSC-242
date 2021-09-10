import java.util.ArrayList;

public class Game {

    String player;
    GameTree tree;
    State currentState;

    public GameTree gameGetTree() {
        return tree;
    }

    public ArrayList<Integer> gameActionsAvailable (State s) {
        return s.stateReturnAction();
    }

    public int gameUtilityFunction(State s) {

        int[] test = new int[s.stateGetStateConfig().length];
        for(int i=0; i < s.stateGetStateConfig().length; i++) {
            test[i] = s.stateGetStateConfig()[i].tileGetMark();
        }

        int a = 0;
        int b = 1; 
        int c = 2;
        
        int ret = gameTerminalTestRowColumnCheck(test, a, b, c);
        if( ret != 0) {
            return ret;
        } 

        a = 0;
        b = 3;
        c = 6;

        ret  = gameTerminalTestRowColumnCheck(test, a, b, c);
        if( ret != 0) {
            return ret;
        }

        a = 0;
        b = 4;
        c = 8;

        String rets = gameTerminalTupleChecks(test, a, b, c);
        if(!rets.equals("DRAW")){
            if(rets.equals("MIN")) {
                return -1;
            } else {
                return 1;
            }
        } 

        a = 2;
        b = 4;
        c = 6;

        rets = gameTerminalTupleChecks(test, a, b, c);
        if(!rets.equals("DRAW")){
            if(rets.equals("MIN")) {
                return -1;
            } else {
                return 1;
            }
        } 

        return 0;
    }

    public int gameTerminalTestRowColumnCheck(int[] test, int a, int b, int c) {

        if(!gameTerminalTupleChecks(test, a, b, c).equals("DRAW")) {
            if(gameTerminalTupleChecks(test, a, b, c).equals("MAX")) {
                return 1;
            } else if (gameTerminalTupleChecks(test, a, b, c).equals("MIN")) {
                return -1;
            } 
        }

        if(!gameTerminalTupleChecks(test, a+1, b+1, c+1).equals("DRAW")) {
            if(gameTerminalTupleChecks(test, a+1, b+1, c+1).equals("MAX")) {
                return 1;
            } else if (gameTerminalTupleChecks(test, a+1, b+1, c+1).equals("MIN")) {
                return -1;
            } 
        }

        if(!gameTerminalTupleChecks(test, a+2, b+2, c+2).equals("DRAW")) {
            if(gameTerminalTupleChecks(test, a+2, b+2, c+2).equals("MAX")) {
                return 1;
            } else if (gameTerminalTupleChecks(test, a+2, b+2, c+2).equals("MIN")) {
                return -1;
            } 
        }
        return 0;
    }

    public String gameTerminalTupleChecks(int[] test, int a, int b, int c) {
        int sum = test[a] + test[b]+ test[c];
        if(sum == 0) {
            return "MIN";
        } else if(sum == 3){
            return "MAX";
        } else{
            return "DRAW";
        }
    }

    public Boolean gameIsItTerminal(State s) {
        Boolean terminal = true;
        for(Tile t: s.state) {
            if(t.tileGetMark() == -1) {
                terminal =  false;
            }
        }
        return terminal;
    }

    public String gameGetPlayer() {
        return "MAX";
    }

    // UTILITY TEST :::
    // public int gameUtilityFunction(State s) {
    //     return 0;
    //     // do stuff using terminal state
    //     // return 1 if p1 wins or -1 if p1 loses or 0 if it's a draw
    // }

    public State gameResult(State s, int actionLocation) {
        int mark;

        if(gameGetPlayer().equals("MAX")) {
            mark = 1;
        } else if(gameGetPlayer().equals("MIN")) {
            mark = 0;
        } else {
            return null;
        }
        
        State stateReturn = new State(s);
        stateReturn.stateGetStateConfig()[actionLocation].tileSetMark(mark);

        return stateReturn;
    }

    public int maxValue(State state) {

        if(gameIsItTerminal(state)) {
            // gamePrintState(state);
            return gameUtilityFunction(state);
        }

        int v = Integer.MIN_VALUE;
        ArrayList<Integer> actionsAvailable = gameActionsAvailable(state);
        // for(Integer i: actionsAvailable){
        //     System.out.print(i+" ");
        // }
        // System.out.println("");

        for(int i = 0; i < actionsAvailable.size(); i++) {
            v = Math.max(v, minValue(gameResult(state, actionsAvailable.get(i))));
        }
        return v;
    }

    public void gamePrintState(State state){
        for (Tile tile : state.state) {
            System.out.print(tile.tileGetMark() + " ");
        }
        System.out.print(" end\n");
    }

    public Game() {
        State initialState = new State();
        tree = new GameTree (initialState);
        currentState = initialState;
    }

    public int miniMax(State state){
        // returns action that corresponds to the best play for that state.
        ArrayList<Integer> actions = gameActionsAvailable(state);

        for(Integer i : actions) {
            System.out.print(i+" ");
        }
        System.out.println(" end");
        
        int trackMaximumMin = Integer.MIN_VALUE;
        int trackActionThatCorrespondsToMaximumMin = 0;

        for(int i = 0; i < actions.size(); i++) {
            int actionToGo = actions.get(i);

            State res = gameResult(state, actionToGo); // returns a state
            int minimumUtiltiyOrValue = minValue(res);

            if( Math.max(trackMaximumMin, minimumUtiltiyOrValue) == minimumUtiltiyOrValue) {
                trackMaximumMin = minimumUtiltiyOrValue;
                trackActionThatCorrespondsToMaximumMin = actionToGo;
            }
        }

        return trackActionThatCorrespondsToMaximumMin;
    }

    public int minValue(State state) {
        if(gameIsItTerminal(state)) {
            return gameUtilityFunction(state);
        }

        int v = Integer.MAX_VALUE;
        ArrayList<Integer> actionsAvailable = gameActionsAvailable(state);

        for(int i = 0; i < actionsAvailable.size(); i++) {
            v = Math.min(v, maxValue(gameResult(state, actionsAvailable.get(i) )));
        }

        return v;
    }

    public static void  main(String[] args) {
        Game game = new Game();

        int minimax = game.miniMax(game.currentState);
        System.out.println(minimax);



        System.out.println(minimax);


        // available = game.gameActionsAvailable(result);
        // System.out.println(available.size());
        // for(int i : available) {
        //     System.out.println(i + " works");
        // }
        
    }

}