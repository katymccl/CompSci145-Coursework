public class TestLString {
  /** Main method */
   public static void main(String[] args) {
    
      String str1 = "Hello, my name is Alphose!";
      LString lstr1 = new LString(str1);
      System.out.println(lstr1.toString());
    
      LString lstr2 = lstr1.substring(0, 4);
   
      LString lstr3 = new LString(lstr2);
       
      char[] name = {' ','a','l','p','h','o','s','e'};
      LString lstr4 = lstr3.concat(new LString(name));
    
    
      if (lstr1.compareTo(lstr4) > 0) {
         System.out.println("String" + "\"" + lstr1.toString() + "\"" + " comes after String " + "\"" + lstr4.toString() + "\"");
      } 
      else if (lstr1.compareTo(lstr4) < 0 ) {
         System.out.println("String" + "\"" + lstr1.toString() + "\"" + " comes before String " + "\"" + lstr4.toString() + "\"");
      }   
   
      LString lstr5 = lstr2.concat(lstr1.substring(17,24));
      if (lstr5.compareToIgnoreCase(lstr4) > 0) {
         System.out.println("String " + "\"" + lstr5.toString() + "\"" + " comes after String " + "\"" + lstr4.toString() + "\".");
      } 
      else if (lstr5.compareToIgnoreCase(lstr4) < 0 ) {
         System.out.println("String " + "\"" + lstr5.toString() + "\"" + " comes before String " + "\"" + lstr4.toString() + "\".");
      } 
      else {
         System.out.println("String " + "\"" + lstr5.toString() + "\"" + " is equal to String " + "\"" + lstr4.toString() + "\"" + " if ignnore cases.");
      
      }
    
      LString lstr6 = new LString("  Welcome to CS145! ");
      System.out.println(lstr6.trim().toString());
    
      System.out.println("Let's convert number 145 to an LString: " + LString.valueOf(145).toString());
   }
}