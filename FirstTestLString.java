public class FirstTestLString {
  /** Main method */
   public static void main(String[] args) {
    
      String str1 =  "     Hello Small Cat               ";
      LString lstr1 = new LString(str1);
      char[] array = {'d','o','g','s'};
      LString lstr2 = new LString(array);
      LString lstr3 = new LString(lstr1);
      String str2 = "CATS.";
      String str3 = "cats.";
      LString lstr4 = new LString(str3); //cats
      LString lstr5 = new LString (str2); //CATS
      String str4 = "Hello Small Cat";
      LString lstr6 = new LString (str4);
      
      System.out.println(lstr1.charAt(1));
      System.out.println(lstr2.charAt(0));
      System.out.println(lstr3.charAt(3));
      System.out.println(lstr1.getLength());
      System.out.println(lstr4.charAt(3));
      System.out.println(lstr4.charAt(1));
      
      System.out.println(lstr4.toLowerCase().charAt(0));
      System.out.println(lstr4.compareToIgnoreCase(lstr4));
      System.out.println(lstr4.compareTo(lstr2));
    
      System.out.println(lstr4.compareTo(lstr5));
      
      System.out.println(lstr4); //to string test
      
      System.out.println(lstr6.substring(0,3));
      System.out.println(lstr1.substring(3));
      
      System.out.println(lstr1.concat(lstr4));
      System.out.println(lstr6.compareTo(lstr1.trim()));
      System.out.println(lstr1.trim());
    
      }
      }
      