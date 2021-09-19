import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    int count = 0;
    String player;
    GameTree tree;
    State currentState;
    State[] board;

    public GameTree gameGetTree() {
        return tree;
    }

    public ArrayList<Integer> gameActionsAvailable (State s) {
        return s.stateReturnAction();
    }

    public Boolean gameIsWin(State s) {

        int[] test = new int[s.stateGetStateConfig().length];
        for(int i=0; i < s.stateGetStateConfig().length; i++) {
            test[i] = s.stateGetStateConfig()[i].tileGetMark();
        }

        int a = 0;
        int b = 1; 
        int c = 2;
        
        if((test[a] == 0 && test[b] == 0 && test[c] == 0) || (test[a] ==1 && test[b] ==1 && test[c] == 1 )) {
            return true;
        } else if( (test[a+1] == 0 && test[b+1] == 0 && test[c+1] == 0) || (test[a+1] == 1 && test[b+1] == 1 &&  test[c+1] == 1) ) {
            return true;
        } else if((test[a+2] == 0 && test[b+2] == 0 && test[c+2] == 0) || (test[a+2] == 1 && test[b+2] == 1 &&  test[c+2] == 1)) {
            return true;
        }
       

        a = 0;
        b = 3;
        c = 6;

        if((test[a] == 0 && test[b] == 0 && test[c] == 0) || (test[a] ==1 && test[b] ==1 && test[c] == 1 )) {
            return true;
        } else if( (test[a+1] == 0 && test[b+1] == 0 && test[c+1] == 0) || (test[a+1] == 1 && test[b+1] == 1 &&  test[c+1] == 1) ) {
            return true;
        } else if((test[a+2] == 0 && test[b+2] == 0 && test[c+2] == 0) || (test[a+2] == 1 && test[b+2] == 1 &&  test[c+2] == 1)) {
            return true;
        }

        a = 0;
        b = 4;
        c = 8;

        if((test[a] == 0 && test[b] == 0 && test[c] == 0) || (test[a] ==1 && test[b] ==1 && test[c] == 1 )) {
            return true;
        }

        a = 2;
        b = 4;
        c = 6;

        if((test[a] == 0 && test[b] == 0 && test[c] == 0) || (test[a] ==1 && test[b] ==1 && test[c] == 1 )) {
            return true;
        }

        return false;
    }

    public int gameUtilityFunction(State s) {
        String player = s.statePlayer;
        if(player  ==  null ) {
            System.out.println("oh sheesh");
        }

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
            if(rets.equals("MIN") && player.equals("MAX")) {
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
            if(rets.equals("MIN") && player.equals("MAX")) {
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

    public Boolean gameValidMove (int move, State s) {
        ArrayList<Integer> validMoves = s.stateReturnAction();
        for (int x : validMoves) {
            if(x == move) return true;
        }
        return false;
    }

    public String gameTerminalTupleChecks(int[] test, int a, int b, int c) {
        if(test[a] == 0 && test[b] == 0 &&  test[c] == 0) {
            return "MIN";
        } else if(test[a] == 1 && test[b] == 1 &&  test[c] == 1){
            return "MAX";
        } else{
            return "DRAW";
        }
    }

    public Boolean gameIsItTerminal(State s) {
        Boolean tracker = true;
        for(Tile t: s.state) {
            if(t.tileGetMark() == -1) {
                tracker = false;
            }
        }
        return tracker;
    }

    public Boolean gameIsItTerminal(State[] s) {
        Boolean tracker = true;
        for(int i = 0; i < 9; i++) {
            for(Tile t: s[i].state) {
                if(t.tileGetMark() == -1) {
                    tracker =  false;
                }
            }
        }
        return tracker;
    }

    public State gameResult(State s, int actionLocation) {
        String player = s.statePlayer;
        int mark;

        if(player.equals("MAX")) {
            mark = 1;
        } else if (player.equals("MIN")){
            mark = 0;
        } else {
            System.out.println("no player selected");
            mark = 9999;
        }
        
        State stateReturn = new State(s);
        if(player.equals("MIN")) {
            stateReturn.stateSetPlayer("MAX");
        } else if(player.equals("MAX")) {
            stateReturn.stateSetPlayer("MIN");
        } else {
            return null;
        }
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
        initialState.stateSetPlayer("MAX");
        currentState = initialState;
    }

    public int miniMax(State state){
        ArrayList<Integer> actions = gameActionsAvailable(state);
        
        int score = Integer.MIN_VALUE;
        int bestAction = 0;

        for(int i = 0; i < actions.size(); i++) {
            int actionToGo = actions.get(i);

            State res = gameResult(state, actionToGo);
            int minimumUtiltiyOrValue = minValue(res);

            if( Math.max(score, minimumUtiltiyOrValue) == minimumUtiltiyOrValue) {
                score = minimumUtiltiyOrValue;
                bestAction = actionToGo;
            }
        }
        return bestAction;
    }

    public int maxValue(State state) {
        if(state == null) {
            System.out.println("null max");
        }
        if(gameIsItTerminal(state)) {
            return gameUtilityFunction(state);
        }

        int v = Integer.MIN_VALUE;
        ArrayList<Integer> actionsAvailable = gameActionsAvailable(state);

        for(int i : actionsAvailable) {
            v = Math.max(v, minValue(gameResult(state, i)));
        }
        return v;
    }

    public int minValue(State state) {
        if(gameIsItTerminal(state)) {
            return gameUtilityFunction(state);
        }

        int v = Integer.MAX_VALUE;
        ArrayList<Integer> actionsAvailable = gameActionsAvailable(state);

        for(int i : actionsAvailable) {
            v = Math.min(v, maxValue(gameResult(state, i)));
        }
        return v;
    }

    public int heuristicValue(State s) {
        int value;
        if(s.statePlayer.equals("MAX") ) {
            value = 0;
        } else {
            value = 1;
        }

        int[] test = new int[s.stateGetStateConfig().length];
        Boolean initialCase = false;
        for(int i=0; i < s.stateGetStateConfig().length; i++) {
            test[i] = s.stateGetStateConfig()[i].tileGetMark();
            if(test[i] != -1) {
                initialCase  = true;
            }
        }
        if(initialCase == true){
            return 0;
        }

        int count;
        count = 0;

        int a = 0;
        int b = 1; 
        int c = 2;
        if(test[a] == value || test[b] == value || test[c] == value) {
            count += 1;
        }
        if(test[a+1] == value || test[b+1] == value || test[c+1] == value) {
            count += 1;
        }
        if(test[a+2] == value || test[b+2] == value || test[c+2] == value) {
            count += 1;
        }

        a = 0;
        b = 3;
        c = 6;
        if(test[a] == value || test[b] == value || test[c] == value) {
            count += 1;
        }
        if(test[a+1] == value || test[b+1] == value || test[c+1] == value) {
            count += 1;
        }
        if(test[a+2] == value || test[b+2] == value || test[c+2] == value) {
            count += 1;
        }

        a = 0;
        b = 4;
        c = 8;
        if(test[a] == value || test[b] == value || test[c] == value) {
            count += 1;
        }

        a = 2;
        b = 4;
        c = 6;
        if(test[a] == value || test[b] == value || test[c] == value) {
            count += 1;
        }

        System.out.println(8-count + " bruh" );

        return 8 - count;    
    }

    public int alphabeta(State state, int depth, int alpha, int beta, String maximizingPlayer){
        if(depth  == 0 || gameIsItTerminal(state)) {
            return heuristicValue(state);
        }
        ArrayList<Integer> actions = gameActionsAvailable(state);
        for(int i: actions) {
            System.out.print(i+ " ");
        }
        System.out.println("");
        int value;
    
        if(maximizingPlayer.equals("MAX")) {
            value = Integer.MIN_VALUE;
            for( int i: actions) {
                value = Math.max(value, alphabeta(gameResult(state, i), depth -1, alpha, beta, "MIN"));
                if(value >= beta) {
                    break;
                }
                alpha = Math.max(alpha, value);
            }
            return value;
        }else {
            value = Integer.MAX_VALUE;
            for( int i: actions) {
                value = Math.min(value, alphabeta(gameResult(state, i), depth -1, alpha, beta, "MAX"));
                if(value <= alpha) {
                    break;
                }
                alpha = Math.min(beta, value);
            }
            return value;
        }
    }

    public Game(int n) {
        board = new State[n];
        for(int i = 0; i< n; i++) {
            board[i] = new State();
        }
    }

    public static void  main(String[] args) {
        Game game = new Game(9);
        System.out.println("Game start");

        int humanMove; // new move
        int computerAction;

        Scanner reader = new Scanner(System.in);  // Reading from System.in


        System.out.println("Game starts!");

        while(!game.gameIsItTerminal(game.board)) {
            System.out.println("minimax player of root state "+ game.currentState.statePlayer);

            computerAction =  game.alphabeta(game.currentState, 8, Integer.MIN_VALUE, Integer.MAX_VALUE, game.currentState.statePlayer);
            System.out.println("Computer Action is: " + computerAction);
            game.currentState = new State(game.gameResult(game.currentState, computerAction));

            System.out.println(game.currentState.stateGetStateConfig()[computerAction].tileIsTileMarked() + " is tile marked?");
            game.currentState.statePrintConf();
            if(game.gameIsWin(game.currentState)){
                System.out.println("win");
                break;
            }

            Boolean validMove = true;
            humanMove = -1;
            while(validMove) {
                System.out.println("Choose your move");
                humanMove = reader.nextInt(); 

                if (game.gameValidMove(humanMove, game.currentState) && humanMove != -1){
                    validMove = false;
                } else {
                    System.out.println("Invalid Move.");
                }
            } 

            game.currentState = new State(game.gameResult(game.currentState, humanMove));
            game.currentState.statePrintConf();

            if(game.gameIsWin(game.currentState)){
                System.out.println("win");
                break;
            }
        }

        reader.close();
        System.out.println("Game ended.");
        game.currentState.statePrintConf();
    }

}