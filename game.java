import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    int count = 0;
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
        for(Tile t: s.state) {
            if(t.tileGetMark() == -1) {
                return false;
            }
        }

        // s.statePrintConf();
        updateCounter();
        return true;
    }

    public void updateCounter(){
        count = count + 1;
    }
    public int gameGetCounter(){
        return count;
    }

    // public String gameGetPlayer() {
    //     return "MAX";
    // }

    public State gameResult(State s, int actionLocation) {
        // pain :::
        // which player?

        int mark;

        int lengthOfActions = s.stateReturnAction().size();

        // knowing that the depth decreases by 1 on each ply, we link the current player with the modality to 2 of the depth
        if(lengthOfActions % 2 == 1) {
            mark = 1;
        } else {
            mark = 0;
        }
        
        State stateReturn = new State(s);
        stateReturn.stateGetStateConfig()[actionLocation].tileSetMark(mark);

        return stateReturn;
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
        
        int trackMaximumMin = Integer.MIN_VALUE;
        int trackActionThatCorrespondsToMaximumMin = 0;

        for(int i = 0; i < actions.size(); i++) {
            int actionToGo = actions.get(i);

            State res = gameResult(state, actionToGo); // pain point
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

    public int maxValue(State state) {
        if(gameIsItTerminal(state)) {
            return gameUtilityFunction(state);
        }

        int v = Integer.MIN_VALUE;
        ArrayList<Integer> actionsAvailable = gameActionsAvailable(state);

        for(int i = 0; i < actionsAvailable.size(); i++) {
            v = Math.max(v, minValue(gameResult(state, actionsAvailable.get(i))));
        }

        return v;
    }

    public Boolean gameIsWin(State s) {

        int[] test = new int[s.stateGetStateConfig().length];
        for(int i=0; i < s.stateGetStateConfig().length; i++) {
            test[i] = s.stateGetStateConfig()[i].tileGetMark();
        }

        int a = 0;
        int b = 1; 
        int c = 2;
        
        int ret = gameTerminalTestRowColumnCheck(test, a, b, c);
        if( ret != 0) {
            return true;
        } 

        a = 0;
        b = 3;
        c = 6;

        ret  = gameTerminalTestRowColumnCheck(test, a, b, c);
        if( ret != 0) {
            return true;
        }

        a = 0;
        b = 4;
        c = 8;

        String rets = gameTerminalTupleChecks(test, a, b, c);
        if(!rets.equals("DRAW")){
            return true;
        } 

        a = 2;
        b = 4;
        c = 6;

        rets = gameTerminalTupleChecks(test, a, b, c);
        if(!rets.equals("DRAW")){
            return true;
        } 

        return false;
    }

    public static void  main(String[] args) {
        Game game = new Game();

        System.out.println("Game start");

        int humanMove; // new move
        int computerAction;
        State rootState = game.gameGetTree().getRoot();

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        rootState.statePrintConf();
        System.out.println("Game starts!");
        while(!game.gameIsItTerminal(rootState) ) {

            computerAction =  game.miniMax(rootState);
            System.out.println("Computer Action is: " + computerAction);
            rootState = new State(game.gameResult(rootState, computerAction));
            rootState.statePrintConf();

            System.out.println("Choose your move");
            humanMove = reader.nextInt(); // Scans the next token of the input as an int.
            rootState = new State(game.gameResult(rootState, humanMove));
            rootState.statePrintConf();
        }

        reader.close();
        System.out.println("Game ended.");
        rootState.statePrintConf();

    }

}