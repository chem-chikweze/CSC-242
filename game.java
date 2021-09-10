import GameTree.java;
import State.java;
import java.util.ArrayList;

public class Game {

    String player;
    GameTree tree;

    public ArrayList<Integer> gameActionsAvailable (State s) {
        return s.stateReturnAction();
    }

    public State gameResult(State s, int actionLocation) {
        int mark;

        if(gameGetPlayer().equals("MAX")) {
            mark = 1;
        } else if(gameGetPlayer().equals("MIN")) {
            mark = 0;
        } else {
            return null;
        }
        
        State stateReturn = s;
        stateReturn.state[actionLocation].tileSetMark(mark);

        return stateReturn;
    }

    public boolean gameTerminalTest(State s) {

        Tile[] stateTest = s.state;

        int[] test = new int[stateTest.length];
        for(int i=0; i < stateTest.length; i++) {
            test[i] = stateTest[i].tileGetMark();
        }

        int a = 0;
        int b = 1; 
        int c = 2;

        if(gameTerminalTestHelperTestEnd(test, a, b, c)){
            return true;
        } else if (gameTerminalTestHelperTestEnd(test, a+1, b+1, c+1)){
            return true;
        } else if (gameTerminalTestHelperTestEnd(test, a+2, b+2, c+2)){
            return true;
        }

        a = 0;
        b = 3;
        c = 6;

        if(gameTerminalTestHelperTestEnd(test, a, b, c)){
            return true;
        } else if (gameTerminalTestHelperTestEnd(test, a+1, b+1, c+1)){
            return true;
        } else if (gameTerminalTestHelperTestEnd(test, a+2, b+2, c+2)){
            return true;
        }

        a = 0;
        b = 4;
        c = 8;

        if(gameTerminalTestHelperTestEnd(test, a, b, c)){
            return true;
        } 

        a = 2;
        b = 4;
        c = 6;

        if(gameTerminalTestHelperTestEnd(test, a, b, c)){
            return true;
        } 

        return false;
    }

    public boolean gameTerminalTestHelperTestEnd(int[] test, int a, int b, int c) {
        int sum = test[a] + test[b]+ test[c];
        if(sum == 0 || sum == 3) {
            return true;
        } else {
            return false;
        }
    }

    public String gameGetPlayer() {
        return "h";
    }

    // UTILITY TEST :::
    public int gameUtilityFunction(State s) {
        // do stuff using terminal state
        // return 1 if p1 wins or -1 if p1 loses or 0 if it's a draw
    }

    public int miniMax(State state){
        // returns action that corresponds to the best play for that state.
        ArrayList<Integer> actions = gameActionsAvailable(state);
    
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
        if(gameTerminalTest(state)) {
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
        if(gameTerminalTest(state)) {
            return gameUtilityFunction(state);
        }

        int v = Integer.MIN_VALUE;
        ArrayList<Integer> actionsAvailable = gameActionsAvailable(state);

        for(int i = 0; i < actionsAvailable.size(); i++) {
            v = Math.max(v, minValue(gameResult(state, actionsAvailable.get(i))));
        }

     
        return v;
    }

    public static void  main(String[] args) {
        Game g = new Game();

        System.out.println("hey");
        
    }


}