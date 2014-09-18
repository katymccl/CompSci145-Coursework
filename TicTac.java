/*
 *Program: TicTac.java
 *Course: Computer Science 145
 *Purpose: To create a tic tac toe game which allows the user to play with another user. 
   Checks for row, column and diagonal wins, and draws. To understand how to use multi-dimmensional arrays
   and logic algorithms.
 *Author: Kathryn McClintic
 *Date: 4/28/2014

*/

import java.util.*;
import java.lang.*;

public class TicTac{
   //declare a constant variable
   public static final int SIZE = 3; //size of each row and each column
   
   public static void main(String[] args) {
   
      char[][] board = new char[SIZE][SIZE];
      int [][] number = new int[SIZE][SIZE];
      char winner = ' ';
      intializeBoard(board);
      displayBoard(board);
      
      //prompt for the first player
      char player = ' ';
      Scanner input = new Scanner(System.in);
      do{
         System.out.println("Who wants to go first (X or O)?");
         String firstPlayer = input.nextLine();
         firstPlayer=firstPlayer.toUpperCase();
         player = firstPlayer.charAt(0);         
      }while(player != 'X' &&  player != 'O');
      
      
      /*take turns to make moves, before each move prompt for target position, after each move, check if there is a winner
      and display the board*/
      //uses a while loop to keep game going until a winner is found, runs while hasEmptyCell is true and winner is not defined
      // switches player after each turn
      // Prints out winner or a draw
      
      while (hasEmptyCell(board) && winner == ' '){
         getMove(player,board);
         displayBoard(board);
         winner = findWinner(board);
         if (winner==' ') 
            player = switchPlayer(player);   
      }
      
      if(winner == 'X' || winner == 'O')
         System.out.println("Player "+player+" has won!!! Game Over!");
      
      else
         System.out.println("It's a draw! Board Full."); 
   }
   
   //Method: initializeBoard
   //Purpose: initialize each cell in the array with a blank space character
   //To do: define and implement the method here.
   public static void intializeBoard(char[][] board){
    
      for (int i = 0; i < SIZE; i++){
         for (int j = 0; j < SIZE; j++){
            board[i][j] = ' ';
         }
      }
   }
   // Method: switchPlayer
   // Purpose: switches player depending on who the current player is
   public static char switchPlayer(char player){
      return (player != 'X' ? 'X' : 'O'); //new operator, ternary operator: (boolean value ? Return if True: Return if False)
   }
   
   //Method: displayBoard 
   //Purpose: display the current board on screen
   //This method has been provided to you.
   public static void drawLine() {
      for (int i = 0; i <= 4 * SIZE; i++) {
         System.out.print("-");
      }
      System.out.println();
   }

   public static void displayBoard(char[][] board) {
      drawLine();
      for (int i = 0; i < SIZE; i++) {
         for (int j = 0; j < SIZE; j++) {
            System.out.print("| " + board[i][j] + " ");
         }
         System.out.println("|");
         drawLine();
      }
      System.out.println();
   }

   //Method: getMove
   //Purpose: to prompt the current player for a target position, and place the mark in the position if the position is available.
   public static void getMove(char player, char[][] board){
   
      Scanner input = new Scanner(System.in);
      int row = 0 , column = 0;
      
      System.out.println("Where would you like to place your token?");
      
      // try and catch used to prevent program from crashing if non-integer value is entered
      try{
         // do while loop used to check if space is empty
         do{
            //do while loop used to check if integer is valid (0-2)
            do{
               System.out.println("Enter the row number(0,1,2) for player "+ player+" : ");
               row = input.nextInt();
               
               if (row > 2 || row < 0)
                  System.out.println("Enter a valid integer between 0 and 2. ");
                  
            }while( row > 2 || row < 0);
         
            do{                  
               System.out.println("Enter the column number(0,1,2) for player, "+ player+" : ");
               column = input.nextInt();  
               
               if (column > 2 || column < 0)
                  System.out.println("Enter a valid integer between 0 and 2. ");
            
            }while(column > 2 || column < 0);
         
            if (board[row][column] != ' '){
               System.out.println("Space already taken.");
            }
            
         }while(board[row][column] != ' ');
         // after conditions are checked, player is assigned to board space  
         board[row][column] = player;
      }
      catch(InputMismatchException e){
         System.err.println("Input Mismatch Exception. Please enter a valid integer. ");
         player = player != 'X' ? 'O' : 'X' ;  
         getMove(player,board);
      }     
   }

   //Method: findWinner
   //Purpose: after each move, check the board and see if any player has achieve a win.
   //Uses three different algorithms to check for a winner, examines rows and columns and both diagonals. 
  
   public static char findWinner(char[][] board){
   
      //switched board is a board with the rows as the original columns so that "rowChecker" can be used for both rows and columns
      char [][] switchedBoard = new char[SIZE][SIZE];
      char winner = ' ';
   
      //iterates through rows on board and uses method rowChecker to compare elements
      for (int i = 0; i < SIZE; i++){              
         winner=rowChecker(board[i]);
         if (winner!=' ')
            return winner; 
      }
      
      //creates switchedBoard with columns as rows
      for (int i = 0; i < SIZE; i++){
         for (int j = 0; j < SIZE; j++){
            switchedBoard[i][j]= board[j][i];
         } 
      }     
      
      //checks columns with rowChecker using switchedBoard
      for (int i = 0; i < SIZE; i++){              
         winner=rowChecker(switchedBoard[i]);
         if (winner!=' ')
            return winner; 
      }  
      
      // checks diagonal starting from left upward corner
      int count = 0;
      for (int i = 0; i < SIZE-1; i++){
        
         if (board[i][i] == board[i+1][i+1] && board[i][i]!=' '){
            count ++;
            winner = board[i][i];
         }
      }  
      if (count == SIZE-1){
         return winner;
      }
      else{
         winner = ' ';
      }
      
      //checks diagonal from right corner
      count = 0;
      for (int i = 0, j = SIZE - 1; i < SIZE-1; i++, j--){
      
         if (board[i][j] == board[i+1][j-1] && board[i][i]!=' '){
            count ++;
            winner = board[i][j];
         }   
      }
      if (count == SIZE-1){
         return winner;
      }
      else{
         winner = ' ';
      }      
      return winner;
   }
   
   // Method: rowChecker
   // Purpose: compares each element of a row in the board represented by a character array called row. 
   // Returns the 0th character if all elements of row are the same compared to that character
   // Returns blank character if row is not the same
   public static char rowChecker(char[] row){
      char c = row[0];
      for (int i = 1; i < SIZE; i++){
         if (row[i] != c)
            return ' ';
      }
      return c;
   }

   //Method: hasEmptyCell
   //Purpose: check if there are still any empty spots on the board
   //To do: define and implement the method here.
   public static boolean hasEmptyCell (char[][] board){
      for (int i = 0; i < SIZE; i++){
         for (int j = 0; j < SIZE; j++){
            if (board[i][j] == ' '){
               return true;
            }
         }
      }
      return false;
   }
}