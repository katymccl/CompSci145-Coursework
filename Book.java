/*
*Book.java
*
*/

public class Book{
	
	/* fields for the Book class */
	private String title; 
	private String author; 
	private String publisher; 
	private int year; 
	private int edition; 
	private String isbn; 
	private double price; 
	private int stock; 
	private String format; 
	
	/* constructor function */
	Book(String title, String author, String publisher, int year, int 
                         edition, String isbn, double price, String format){
		
		this.title = title; 
		this.author = author; 
		this.publisher = publisher; 
		this.year = year; 
		this.edition = edition; 
		this.isbn = isbn; 
		this.price = price; 
		this.stock = 0; 
	   this.format = format; 
	}
   
   /* copy constructor, use another book to initialize a new book (deep copies another Book to this Book) */
   public Book(Book another) {
      this.title = another.title; 
		this.author = another.author; 
		this.publisher = another.publisher; 
		this.year = another.year; 
		this.edition = another.edition; 
		this.isbn = another.isbn; 
		this.price = another.price; 
		this.stock = another.stock; 
	   this.format = another.format;  
   }
	
	/* return book title */
	public String getTitle(){ 
		return title;  
	}
	
	/* return book author */
	public String getAuthor(){
		return author; 
	}
	
	/* return book publisher */
	public String getPublisher(){
		return publisher; 
	}
	
	/* return book year of publication */
	public int getPublishYear(){
		return year; 
	}
	
	/* return book edition */
	public int getEdition(){
		return edition; 
	}
	
	/* return book International Standard Book Number (ISBN) */
	public String getIsbn(){
		return isbn; 
	}
	
	/* return book price */
	public double getPrice(){
		return price; 
	}
	
	/* return book stock on hand */
	public int getStock(){
		return stock; 
	}
	
	/* return book format */
	public String getFormat(){
		return format;
	}
	
	/* return book stock value */
	public double getStockValue(){
		return stock * price; 
	}	
	
	/* return a formated string representation of the book details */
	public String toString(){
   
      String s = String.format("%-30s %-30s %-30s %-4d %-10s %5.2f %-2d %-10s", 
                                 title, author, publisher, year, isbn, price, stock, format);
      
		return s; 
	}	
	
	/* sets the price of the book to a new price */
	public void setPrice(double newPrice){
		price = newPrice; 
	}
	
	/* change the stock level of the book */
	public void changeStock(int change){
   
		if( (stock + change) < 0 ){
			throw new UnsupportedOperationException("BookStockError");
		} else {
			stock += change; 
		}
	}
}