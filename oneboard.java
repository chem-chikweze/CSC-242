    

//     Game game = new Game();

//     System.out.println("Game start");

//     int humanMove; // new move
//     int computerAction;

//     Scanner reader = new Scanner(System.in);  // Reading from System.in

//     game.currentState.statePrintConf();
//     System.out.println("Game starts!");
//     while(!game.gameIsItTerminal(game.currentState)) {
//         System.out.println("minimax player of root state "+ game.currentState.statePlayer);

//         computerAction =  game.miniMax(game.currentState);
//         System.out.println("Computer Action is: " + computerAction);
//         game.currentState = new State(game.gameResult(game.currentState, computerAction));

//         System.out.println(game.currentState.stateGetStateConfig()[computerAction].tileIsTileMarked() + " is tile marked?");
//         game.currentState.statePrintConf();
//         if(game.gameIsWin(game.currentState)){
//             System.out.println("win");
//             break;
//         }

//         Boolean validMove = true;
//         humanMove = -1;
//         while(validMove) {
//             System.out.println("Choose your move");
//             humanMove = reader.nextInt(); 

//             if (game.gameValidMove(humanMove, game.currentState) && humanMove != -1){
//                 validMove = false;
//             } else {
//                 System.out.println("Invalid Move.");
//             }
//         } 

//         game.currentState = new State(game.gameResult(game.currentState, humanMove));
//         game.currentState.statePrintConf();

//         if(game.gameIsWin(game.currentState)){
//             System.out.println("win");
//             break;
//         }
//     }

//     reader.close();
//     System.out.println("Game ended.");
//     game.currentState.statePrintConf();

// }
