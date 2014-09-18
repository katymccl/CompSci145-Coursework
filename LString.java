/**
 *Program: LString.java
 *Course: Computer Science 145
 *Purpose: Create an LString class which uses linked lists to implement a structure which functions as a string. 
  To understand further the use of classes and objects, to gain understanding of singly linked lists and nodes.
  The LString will store a sequence of characters and can be of any length.
 *Author: Kathryn McClintic
 *Date: 5/29/2014
 */

import java.util.*;
import java.lang.*;

public class LString{
   private ListNode _head; // first value in the list
   private int _size = 0; 
   
   /* CONSTRUCTORS */
   
   // Constructor: initializes a new LString object, represents empty character sequence
   public LString(){
      _head = null;
   }
   
   // Constructor: intializes a new LString object,represents character sequence in character array
   public LString(char[] value){
      _size = value.length;
      
      if (_size > 0){
         _head = new ListNode(value[0]);
      }
      ListNode current = _head;
   
      for (int i = 1; i < _size; i++){
         current.setNext(value[i]);
         current = current.getNext();
      }
   }
   
   // Constructor: initializes a new LString object which is a copy of the original LString parameter
   public LString(LString original){
      _size = original.getLength(); 
   
      if (_size > 0){
         _head = new ListNode(original.charAt(0));
      }
      ListNode current = _head;
   
      for (int i = 1; i < _size; i++){
         current.setNext(original.charAt(i));
         current = current.getNext();   
      }
   }
   
   // Constructor: intializes a new LString object which is a copy of the string parameter
   public LString(String original){
      _size = original.length();
      if (_size > 0){
         _head = new ListNode(original.charAt(0));
      }
      ListNode current = _head;
      
      for (int i = 1; i < _size; i++){
         current.setNext(original.charAt(i));
         current = current.getNext();
      }
   }

   
   /* METHODS */
   
   /* Method: charAt
   *  Purpose: returns character at given index
   *  Parameters: integer index*/
   public char charAt(int index){
      return nodeAt(index).getChar();
   }
   
   /* Method: length, returns integer length of LString (num of characters)*/
   public int getLength(){
      return _size;
   }
   
   /* Method: toLowerCase
   *  Purpose: converts characters of LString to lowercase, returns new changed LString
   *  original LString remains in original case*/
   public LString toLowerCase(){
      char[] newLStringArray = new char[_size];
      
      for (int i = 0; i < _size; i++){
         char ch = charAt(i);
         ch = toLower(ch);
         newLStringArray[i] = ch;
      
      }
      LString newLString = new LString(newLStringArray);
      return newLString;
   }
   
   /* Method: toUpperCase
   *  Purpose: converts characters of LString to uppercase, returns new changed LString
   *  original LString remains in orginal case*/
   public LString toUpperCase(){
      char[] newLStringArray = new char[_size];
      
      for (int i = 0; i < _size; i++){
         char ch = charAt(i);
         ch = toUpper(ch);
         newLStringArray[i] = ch;
      
      }
      LString newLString = new LString(newLStringArray);
      return newLString;
   }
   
   /* Method: toString
   *  creates a String with characters from currentLString.*/
   public String toString(){
      String str = "";
      for (int i = 0; i < _size; i++){
         str += charAt(i);
      }
      return str;
   }
   
   /* Method: compareTo
   *  Purpose: Perform lexicographical comparison of the current LString with argument LString.
   *  Returns value less than 0 if current LString is less than second LString
   *  Reutrns value greater than 0 if current LString is greater than second LString
   *  Returns 0 if LStrings are lexicographically equal.*/
   public int compareTo(LString LStringToCompare){
      int size =  _size;
      if (size >= LStringToCompare.getLength()){  
         size = LStringToCompare.getLength();
      }
      for (int i = 0; i < size; i++){
         if (charAt(i) != LStringToCompare.charAt(i)){
            return (int) (charAt(i) - LStringToCompare.charAt(i));             
         }
      }
     
      if (getLength() < LStringToCompare.getLength()){
         return -1;
      }
      else if (getLength() > LStringToCompare.getLength()){
         return 1;
      }     
      return 0;
   }
   
   /* Method: compareToIgnoreCase
   *  Converts current and given LStrings to lowercase to make case equal
   *  Calls compareTo function to compare both LStrings*/
   
   public int compareToIgnoreCase(LString LStringToCompare){
      return toLowerCase().compareTo(LStringToCompare.toLowerCase());
   }

   /*Method: equals
   * Returns true if current LString equals given LString*/
   public boolean equals (LString LStringToCompare){
      return (compareTo(LStringToCompare)==0);
   }
     
   /* Method: concat
   /* Creates and returns a new Lstring as the concatenation of current LString and given LString*/
   public LString concat(LString LStringToConcat){
      return new LString(toString()+ LStringToConcat.toString() );
   }
   
   /* Method: substring
   /* Creates and returns a new LString with chars from position start index to end of current LString.*/
   public LString substring(int startIndex){
      return substring (startIndex, getLength()-1);
   }
   
   /* Method: substring (method overloading)
   /* Creates and returns a new LString with chars from position start index to end index of current LString*/
   public LString substring(int startIndex, int endIndex){  // 2 5
      char[] newLStringArray = new char[endIndex - startIndex + 1]; // 4  2 3 4 5
   
      if (endIndex > _size || startIndex <  0 ){
         throw new NoSuchElementException("Index is too big.");
      }
      
      for (int i = startIndex; i <= endIndex; i++){
         newLStringArray[i - startIndex] =  charAt(i);
      }
      return new LString(newLStringArray);
   }
   
   /* Method: Trim
   /* Creates and reutrns a new LString with leading and trailing white space removed. 
   /* Utilizes substring function */
   public LString trim(){
      int startIndex = 0;
      int endIndex = 0;
      int i = 0;
      
      while (charAt(i) == ' '){
         i++;
      }
      startIndex = i;
      i = _size - 1;
      while (charAt(i) == ' '){
         i--;    
      }
      endIndex = i;
   
      return substring(startIndex,endIndex);
   }
   
   /* Method: valueOf
   /* Converts integer into an LString represenation of number. */
   public static LString valueOf(int value){
   return new LString (Integer.toString(value));
   //    int length = (int) Math.log10(value)+1;
//       char array[] = new char [length];
//       int i = array.length - 1;
//      
//       while (value !=0){
//       
//          array[i] = (char) (value % 10 + 48);
//          value = value / 10;
//          i--;
//       }
//    
//    
//    
//       return new LString(array);
   
   }
   /* Method: nodeAt
   *  Private method iterates through linked list until it reaches int index
   *  Returns current node at index
   *  To be used by class functions*/
   private ListNode nodeAt(int index) {
      if (index >= _size){
         throw new NoSuchElementException("Index is too big.");
      }
      ListNode current = _head;
      for (int i = 0; i < index; i++) {
         current = current.getNext();
      }
      return current;
   }
   
   /* Method: toLower
   *  private method to be used by toLowerCase
   *  takes in a character, converts it to its integer ascii value, 
   *  and offsets it to make it lowercase if it is a letter
   *  Returns lowercase character. */
   private char toLower(char ch) {
      int offset = (int) 'a' - (int) 'A';
   
      int ch_int = (int) ch;
      if (ch >= 'A' && ch <= 'Z'){
         ch_int = ch_int + offset;
      }
      return (char)ch_int;
   }
   
   /* Method: toUpper
   *  private method to be used by toUppperCase
   *  takes in a character, converts it to its integer ascii value, 
   *  and offsets it to make it uppercase if it is a letter
   *  Returns lowercase character. */
   private char toUpper(char ch){
      int offset = (int) 'a' - (int) 'A';
   
      int ch_int=(int) ch;
      if (ch>='a'&& ch<='z'){
         ch_int=ch_int-offset;
      }
   
      return (char)ch_int;    
   
   } 
   
}