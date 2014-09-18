/*
*
* BookStuff.java
*
* doing stuff with books
* 
* depends on Book class, provided
* depends on BookCollection, to be completed by students 
*
*/

import java.io.*;
import java.util.Scanner;

public class BookStuff{

	public static void main(String args[]){
  
		/* check for the number of command line arguments */
		if(args.length != 2){
			System.out.println("Please Enter 2 Line Arguments!"); 
			return; /* terminate the program */
		}
		
		/* three empty collections */
		BookCollection collection1 = new BookCollection(100); 
		BookCollection collection2 = new BookCollection(50); 
		BookCollection collection3 = new BookCollection(150); 
		
		/* open the first file and add each book to collection1 */
		getBooks(collection1, args[0]); 
		
		/* make some changes to the first book collection */
		changeCollection(collection1); 
		
		/* display the first collection */
		displayCollection(collection1, "First collection"); 
		
		/* open the second file and add each book to collection2 */
		getBooks(collection2, args[1]); 
		
		/* make some changes to the second book collection */
		modifyCollection(collection2); 
		
		/* display the second collection */
		displayCollection(collection2, "Second collection"); 
		
		/* now merge collection1 and collection2 and copy them to collection3 */
		collection3.merge(collection1, collection2); 
		
		/* display the third collection */
		displayCollection(collection3, "Third collection"); 
    }

	
	/* change the price of a book in a collection, searching with either ISBN for the book */
	public static void tryChangePrice(BookCollection collection, String Isbn, double price){
		try { 
			collection.changePrice(Isbn, price); 
		} catch (UnsupportedOperationException ex){
			System.out.println("Book with ISBN " + Isbn + " not found in the collection. Cannot change price"); 
		}
	}
	
	/* adjust the stock of this book in the collection after a sale */
	public static void trySale(BookCollection collection, String Isbn, int quantity){
		try {
			collection.changeStock(Isbn, -quantity); 
		} catch (UnsupportedOperationException ex){
			if ( ex.getMessage().equals("BookNotFound")){
				System.out.println("Book with ISBN " + Isbn + " not found in the collection. Cannot change stock");
			} else {
				System.out.println("Book with ISBN " + Isbn + " has insufficient stock for sale"); 
			}
		}
	}
	
	/* add to the inventory of this book */
	public static void tryNewStock(BookCollection collection, String Isbn, int quantity){
		try { 
			collection.changeStock(Isbn, quantity);
		} catch (UnsupportedOperationException ex) {
			System.out.println("Book with ISBN " + Isbn + " not found in the collection. Cannot change stock");
		}
	}
	
	/* get the books from an input file and add them to a collection */
	public static void getBooks(BookCollection collection, String fileName){
		
		/* book data components */
		String title, author, publisher, isbn, format;  
		int year, edition;  
		double price; 

		
		String line;  /* each line from the file is read to it */
		Book book;    /* a book */
		
      try(Scanner input = new Scanner(new File(fileName))){
   		while (input.hasNextLine()) {
   			line = input.nextLine(); 
   			title = line.substring(0, 30).trim(); 
   			author = line.substring(30, 60).trim(); 
   			publisher = line.substring(60, 90).trim(); 
   			year = Integer.parseInt(line.substring(90, 94)); 
   			edition = Integer.parseInt(line.substring(95, 96)); 
   			isbn = line.substring(97, 107).trim(); 
   			price = Double.parseDouble(line.substring(108, 113).trim()); 
   			format = line.substring(113, line.length()); 
            book = new Book(title, author, publisher, year, edition, isbn, price, format); 
			try {
				collection.addBook(book); 
			} catch (UnsupportedOperationException ex) {
				if (ex.getMessage().equals("CollectionFull")){
					System.out.println("The collection is full. No more books can be added.");
				} else {
					System.out.println("Duplicate book, not added to the collection.");
				}
			}
				
   		}
      } catch (FileNotFoundException ex) {
         System.err.println("Error: File not found. Exiting program...");
      }
	}
	
	/* display the contents of a book collection */
	public static void displayCollection(BookCollection collection, String heading){
		System.out.println("\n" + heading); 
		for(int i=0; i<heading.length(); i++){
			System.out.print("*"); 
		}
		System.out.println(); 
		for(int i=0; i<collection.getSize(); i++){
         System.out.println(collection.indexOf(i).toString()); 
      } 
		System.out.print("\nThe total value of " + heading + " is $");
		System.out.println((Math.round(collection.getStockValue()*100)/100.00) + "\n"); 
	}
	
	/* do some stuff to a collection */
	public static void changeCollection(BookCollection collection){
		tryNewStock(collection, "0061781231", 20);
		tryNewStock(collection, "0849946158", 2);
		tryNewStock(collection, "030746363X", 12);
		tryNewStock(collection, "0123456789", 1);
		tryNewStock(collection, "1400064163", 10);
		tryNewStock(collection, "0978806506", 5);
		tryNewStock(collection, "1594202842", 5);
		tryNewStock(collection, "1455503304", 2);
		tryNewStock(collection, "0929701836", 3);
		tryNewStock(collection, "0061781231", 4);
		tryChangePrice(collection, "159523067X", 19.99);
		trySale(collection, "0316037915", 12);
		tryChangePrice(collection, "0123456789", 22.99);
		trySale(collection, "1401324312", 1);
		tryChangePrice(collection, "159562015X", 17.99);
		trySale(collection, "0929701836", 1);
	}
	
	/* do some stuff to a collection */
	public static void modifyCollection(BookCollection collection){
		tryNewStock(collection, "1602861331", 1);
		tryNewStock(collection, "159523067X", 20);
		tryNewStock(collection, "030726999X", 12);
		tryNewStock(collection, "0061781231", 15);
		tryNewStock(collection, "030746363X", 10);
		tryNewStock(collection, "0316037915", 3);
		tryNewStock(collection, "1565129520", 5);
		trySale(collection, "0385738838", 12);
		tryChangePrice(collection, "1101475455", 22.99);
		trySale(collection, "0316037915", 8);
		tryChangePrice(collection, "159562015X", 17.99);
		trySale(collection, "1455503304", 1);
	}		
}