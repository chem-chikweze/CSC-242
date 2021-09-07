public class Game {

    String player;
    GameTree tree;

    public ArrayList<int> gameActionsAvailable (State s) {
        return s.stateReturnAction();
    }

    public State gameResult(State s, int actionLocation, String playerX) {
        int mark;

        if(playerX.equals("MAX")) {
            mark = 1;
        } else if(playerX.equals("MIN")) {
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

    // UTILITY TEST :::
    // utility value for a player if the 
    // game ends in terminal state ends
}