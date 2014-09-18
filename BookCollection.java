/* Program: BookCollection.java
*  Course: Computer Science 145
*  Purpose: Write a class which creates Book Collection object which holds an array of Books, created from the Book Class
*  Implements various methods on the books and the book collection. To understand how classes can be used within eachother and how public and private methods differ. 
*  To understand how accessor methods can give outside class access to private variables. 
*  Author: Kathryn McClintic
*  Date: 05/15/2014
*/

public class BookCollection{

  //data fields
   private int limit = 200;
   private Book[] collection;
   private int size = 0;
   
   //BookCollection constructor
   public BookCollection(int limit){
      if (limit > 200){
         limit = 200;
      }
      collection = new Book[limit];
      this.limit = limit;
   }
   
   /* Method: changePrice
   *  Purpose: changes price of specified book in collection. Utilizes findBook method to find index of specified book via ISBN. 
   *  Parameters: String of ISBN of book, double of price of book with ISBN
   *  Return Type: Void 
   *  Pre-completed Method 
   *throw exception exactly "BookNotFound". this is a completed method*/
   public void changePrice(String isbn, double price){
      int index = findBook(isbn); 
      
      if( index == -1){
         throw new UnsupportedOperationException("BookNotFound");
      }
      collection[index].setPrice(price); 
   }
   
   /* Method: changeStock
   *  Purpose: changes stock of specified book in collection. Utilizes findBook method, and changeStock method (Book class)
   *  Parameters: String of ISBN of book, int of amount stock will be changed
   *  Return Type: Void 
   *throw exception exactly "BookNotFound" if index equals -1 which means book is not in array*/
   public void changeStock(String isbn, int change){
      int index = findBook(isbn);
      
      if (index == -1){
         throw new UnsupportedOperationException("BookNotFound");
      }
      collection[index].changeStock(change);
   }
   
   // Method: getSize -- Accessor method to obtain private variable size from  BookCollection class
   public int getSize(){
      return size;
   }
   
   /*  Method: getStockValue
   *   Purpose: accessor method to get entire stock value in dollars (double) of entire collection. 
   *   Parameters: none
   *   Return Type: double, returns stock value*/
   public double getStockValue(){
      double value = 0;
      
      for (int i = 0; i < size; i++){
         value += collection[i].getStockValue();
      }
      
      return value;
   }
   
   /* Method: addBook
   *  Purpose: adds new book to collection. If book is duplicate, throws DuplicateBook exception
   *  If collection is full, throws CollectionFull exception. Increments size accordingly if a book is added. 
   *  Parameters: Book object
   *  Return Type: Void*/
   public void addBook(Book newBook){
      for (int i = 0; i < size; i++){
         if (collection[i].getIsbn().equals(newBook.getIsbn())){
            throw new UnsupportedOperationException("DuplicateBook");
         }   
      }
      
      if (size < limit){
         collection [size] = newBook;
         size += 1;
      }
      else{ 
         throw new UnsupportedOperationException("CollectionFull");              
      }       
   }
   
   /* Method: indexOf
   *  Purpose: finds a Book at a specified index provided the index is the collection's size.
   *  Parameters: int index of Book in collection
   *  Return Type: Book at the specified index, returns null if index is not within size*/
   public Book indexOf(int index){
      if (index <= size && index >= 0){
         return collection[index];   
      }
      return null;
   }
   
   /* Method: merge
   *  Purpose: merges two book collections together; changes stock of books in collection, changes price to be minimum price per book
   *  Catches Duplicate Book exception if duplicate book is added from Collection 2. 
   *  Parameters: two book collections
   *  Return Type: void */
   public void merge(BookCollection collection1, BookCollection collection2){
      size = 0;
      limit = 200;
      collection = new Book[limit];
                 
      for (int i = 0; i < collection1.getSize(); i++){
         addBook(collection1.indexOf(i));
      
      }
       
      for (int i = 0; i < collection2.getSize(); i++){
         try{
            addBook(collection2.indexOf(i));
            
         }
         catch (UnsupportedOperationException ex){ 
       
            if (ex.getMessage().equals("DuplicateBook")){ // we know that we found a duplicate Book
               String isbn = collection2.indexOf(i).getIsbn();
              
               changeStock(isbn, collection2.indexOf(i).getStock());
               if (indexOf(findBook(isbn)).getPrice() <= collection2.indexOf(i).getPrice()){
                  changePrice(isbn, indexOf(findBook(isbn)).getPrice());
               }
               else{ 
                  changePrice(isbn, collection2.indexOf(i).getPrice()); 
               }
            }
         }
      }
      
   } 
      
   /* Method: findBook, private method
   *  Purpose: searches collection for a book via ISBN, gives integer index of specified book in collection
   *  Parameters: String of ISBN of book
   *  Return Type: int of index of book in collection; Returns -1 if book is not found*/
   private int findBook(String isbn){
      for (int index = 0; index < size; index ++){
         if (collection[index].getIsbn().equals(isbn)){
            return index;
         }
      }
      return -1;
   }
       
}




