/*
 *Program: Tromino.java
 *Course: Computer Science 145
 *Purpose: Work with a partner to create a tromino-filled board using recursive algorithm. Gain familiarity with running command line arguments.
 *Author: Anna Magidson and Kathryn McClintic 
 *Date: 5/11/2014
 *Partner Evaluation: I loved working with Katy. She's a good friend of mine and we have strong communication. We worked on the project together during the lab,
 in our dorm, and in Katy's house. Both of us learned something from the other. While it was easier for me to grasp the large-picture view, Katy was the better
 programmer, and our strengths complemented each other well. I loved it when one of us would get an "oh so THAT'S why it works!" moment. We would then explain
 our revelations to each other. Katy is receptive to new ideas and has wonderful determination (which I really appreciated.) I don't have any negative comments
 about Katy, and it would be great to work together again on another project.*/

import java.lang.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.*;

public class Tromino{
   //Beginning of Main
   public static void main(String args[]){
   
      Scanner input=new Scanner(System.in);
      
   	// Check command line arguments with checkArgs(args) method. 
      // If arguments are invalid, while loop executes. User input is prompted to accept new arguments.       
      while(!checkArgs(args)){
         args=new String[3];
         System.out.println("Invalid Input. Please enter three arguments again. (ex. 2 0 1, exponent, row, column)");
         System.out.println("Exponent must be between 1 and 5. Row and column number must be between 0 and 2^exponent. ");
         args[0]=input.next();
         args[1]=input.next(); 
         args[2]=input.next();  
      }
      
      // Initialize variables, store arguments using the parseInt method.
      // Size represents 2^exponent (length/width of each side of the square).  
      int exponent = Integer.parseInt(args[0]); 
      int rowOfBlankSquare = Integer.parseInt(args[1]); 
      int columnOfBlankSquare = Integer.parseInt(args[2]); 
      int size = (int)Math.pow(2,exponent);
        
      int[][] chessBoard = new int[size][size];
      
      // Set the original square to be color '0', which corresponds to black.
      chessBoard[rowOfBlankSquare][columnOfBlankSquare] = 0;
      
      //Call our fillTromino method in order to fill in the board with trominos around the initial square.
      fillTromino(chessBoard,rowOfBlankSquare, columnOfBlankSquare, size, 0, 0);
      
      // Display the board in two ways. Print numerical display of board in Run I/O tab. Displays visual colored board in graphic user interface.
      // Methods were provided at the beginning of project.     
      printBoard(chessBoard);
      printBoardGUI(chessBoard);  
       
   }//End of Main
   
   /* Method Name: fillTromino
   *  Purpose: Fills board with trominos. Includes four recursive calls to itself to fill entire board.
   *  Parameters: int[][] chessBoard, is a two dimmensional integer array. Has dimensions size*size. Holds numerical color values of trominos.
   *     Integers rowOfBlankSquare and columnOfBlankSquare represent location of colored square on chessBoard. 
   *     Integer size is how large the original board is (2^exponent). 
   *     Integers startRow and startColumn specify what position method starts from. (i.e. the first time fillTromino is called, startRow = 0 and startColumn = 0 (consider entire board).
   *     This allows method to consider a smaller section of the board with each recursive call.
   *  Return type: void. chessBoard is updated because it is a reference variable.
   */
   
   public static void fillTromino(int[][] chessBoard, int rowOfBlankSquare, int columnOfBlankSquare, int size, int startRow, int startColumn){
      
      //Random number generated to create a random color to fill each tromino.
      int randomNum = 1 + (int)(Math.random()*8); 
      
      // Two-dimmensional integer array stores values of filled squares (trominos) in each quadrant. 
      int[][] filledCoordinates = {
         {startRow+size/2-1, startColumn+size/2}, //first quadrant
         {startRow+size/2-1, startColumn+size/2-1}, //second quadrant
         {startRow+size/2, startColumn+size/2-1}, //thrird quadrant
         {startRow+size/2, startColumn+size/2} //fourth quadrant
         };
      
      //Base case
      if (size == 1){   //I.e. if there is one square left.
         return;
      }
      
      else{ 
      
         /* First quadrant; If filled square is in first quadrant, tromino created with filled squares in 2nd, 3rd and 4th quadrants by filling squares with random color based on
          * a random number. At each location, the value in chessBoard is changed to its respective color index. 
          * Update filledCoordinates variable to new coordinates, in order to keep track of where the new filled square is in each quadrant. */
         if (rowOfBlankSquare < startRow + size/2 && columnOfBlankSquare >= startColumn+size/2){
            chessBoard[startRow+size/2-1][startColumn+size/2-1] = randomNum; 
            chessBoard[startRow+size/2][startColumn+size/2-1] = randomNum;
            chessBoard[startRow+size/2][startColumn+size/2] = randomNum;
            filledCoordinates[0][0] = rowOfBlankSquare;
            filledCoordinates[0][1] = columnOfBlankSquare;
         }
         
         // second quadrant 
         else if (rowOfBlankSquare < startRow + size/2 && columnOfBlankSquare < startColumn + size/2){
            chessBoard[startRow+size/2-1][startColumn+size/2] = randomNum; 
            chessBoard[startRow+size/2][startColumn+size/2-1] = randomNum;
            chessBoard[startRow+size/2][startColumn+size/2] = randomNum;
            filledCoordinates[1][0] = rowOfBlankSquare;
            filledCoordinates[1][1] = columnOfBlankSquare;
         } 
         
         // third quadrant
         else if (rowOfBlankSquare >= startRow + size/2 && columnOfBlankSquare < startColumn + size/2){
            chessBoard[startRow+size/2-1][startColumn+size/2-1] = randomNum; 
            chessBoard[startRow+size/2-1][startColumn+size/2] = randomNum;
            chessBoard[startRow+size/2][startColumn+size/2] = randomNum;
            filledCoordinates[2][0] = rowOfBlankSquare;
            filledCoordinates[2][1] = columnOfBlankSquare;
         }
          
         // fourth quadrant
         else if (rowOfBlankSquare >= startRow + size/2 && columnOfBlankSquare >= startColumn + size/2){
            chessBoard[startRow+size/2-1][startColumn+size/2-1] = randomNum; 
            chessBoard[startRow+size/2][startColumn+size/2-1] = randomNum;
            chessBoard[startRow+size/2-1][startColumn+size/2] = randomNum;
            filledCoordinates[3][0] = rowOfBlankSquare;
            filledCoordinates[3][1] = columnOfBlankSquare;
         }
         
         /* Recursive calls of fillTromino method.
         *  Recursive calls differ in the filled/blank square coordinates, and the startRow and startColum
         *  Start row and start column changed to refect a smaller section of the board (first, second, third, fourth quadrant)
         *  Size is divided by two to cut board in half each time function is called. */
      
         //first quadrant recursive call
         fillTromino(chessBoard, filledCoordinates[0][0], filledCoordinates[0][1], size/2, startRow, startColumn + size/2);
         //second quadrant recursive call
         fillTromino(chessBoard, filledCoordinates[1][0], filledCoordinates[1][1], size/2, startRow, startColumn);
         //third quadrant recursive call
         fillTromino(chessBoard, filledCoordinates[2][0], filledCoordinates[2][1], size/2, startRow + size/2, startColumn);
         //fourth quadrant recursive call
         fillTromino(chessBoard, filledCoordinates[3][0], filledCoordinates[3][1], size/2, startRow + size/2, startColumn + size/2);
      }
   } // End of fillTromino
   
   
	/* Method: checkArgs
   *  Purpose: Check if command line arguments are valid input.
   *  Parameters: array of Strings called args. Array holds command line arguments or user-given inputs.
   *  Return type: Boolean, returns True if arguments are present and valid; false if arguments are invalid.*/
   public static boolean checkArgs(String[] args){
      int exponent,rowOfBlankSquare,columnOfBlankSquare,size;
      
      //Try catch block used to determine if arguments are valid integers without crashing with NumberFormatException
      //Also catches ArrayIndexOutOfBounds exception if user did not enter three arguments. 
      try{
         exponent = Integer.parseInt(args[0]); 
         rowOfBlankSquare = Integer.parseInt(args[1]); ;
         columnOfBlankSquare = Integer.parseInt(args[2]); ;
         size = (int)Math.pow(2,exponent); 
      }
      catch (NumberFormatException e){
         return false;
      }
      catch (ArrayIndexOutOfBoundsException e){
         return false;
      }
      if (exponent >= 1 && exponent <= 5 && rowOfBlankSquare < size && rowOfBlankSquare >= 0 && columnOfBlankSquare < size && columnOfBlankSquare >= 0){
         return true;
      }
      else{
         return false;
      }  
   }//end of checkArgs
   
   /* Method name: printBoard
   *  Purpose: draws a visual board in the Run I/O window; shows integer color indexes in chessBoard
   *  Parameters: two-dimmensional integer array board, the chessBoard.
   *  Return type: void. Method used for display purpose.
   *  Ready to use method. */ 
   public static void printBoard(int[][] board){
      int boardLength = board.length;
   
      for(int i = 0; i < boardLength; i++){
         System.out.print(" ");
         for(int j = 0; j < boardLength; j++){
            System.out.print("----"); 
         }
         System.out.println();
         for(int j = 0; j < boardLength; j++){
            System.out.print(" | " + board[i][j]); 
         }
         System.out.println(" |");
      }
      System.out.print(" ");
      for(int j = 0; j < boardLength; j++){
         System.out.print("----"); 
      } 
      System.out.println();
   }//end of printBoard
   
   /* Method name: printBoardGUI
   *  Purpose: display board in graphic user interface.
   *  Parameters: two-dimmensional integer array board, the chessBoard.
   *  Return type: void. Method used for display purpose.
   *  Ready to use method. */ 
   public static void printBoardGUI(int[][] board){
      int boardLength = board.length;
      int width = 50;
      int boardLengthPx = boardLength * width;
      Hashtable<Integer, Color> colors = new Hashtable<Integer, Color>();
      DrawingPanel panel = new DrawingPanel(boardLengthPx, boardLengthPx);
      panel.setBackground(Color.darkGray);
   
      colors.put(0, Color.black);
      colors.put(1, Color.red);
      colors.put(2, Color.blue);
      colors.put(3, Color.green);
      colors.put(4, Color.magenta);
      colors.put(5, Color.yellow);
      colors.put(6, Color.pink);
      colors.put(7, Color.cyan);
      colors.put(8, Color.orange);
     
      Graphics g = panel.getGraphics();
      for(int i = 0; i < boardLength; i++) {
         for(int j = 0; j < boardLength; j++) {
            g.setColor((Color)colors.get(board[i][j]));		
            g.fillRect(j * width, i * width, width, width);
            g.setColor(Color.black);
         	
            g.drawLine(j * width, i * width, j * width, (i * width) + width);
            g.drawLine(j * width, i * width, (j * width) + width, i * width);
         }
      }
   }//end of printBoardGUI
  	    
}
