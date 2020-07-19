import java.util.*;

/*
 * SD2x Homework #8
 * This class represents the Logic Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

public class LogicTier {
	
	private DataTier dataTier; // link to the Data Tier
	
	public LogicTier(DataTier dataTier) {
		this.dataTier = dataTier;
	}
	
	public static Comparator<Book> BookYearThenTitleComparator = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            if (o1.getPublicationYear() == o2.getPublicationYear()) {
                return o1.getAuthor().compareTo(o2.getAuthor());
            } else {
                return o1.compareTo(o2);
            }
        }
    };

	public List<String> findBookTitlesByAuthor (String author) {
	    author = author.toLowerCase();

	    List<String> titles = new ArrayList<>();
	    List<Book> books = dataTier.getAllBooks();
	    Collections.sort(books, LogicTier.BookYearThenTitleComparator);
	    for (Book book : books) {
	        if (book.getAuthor().toLowerCase().contains(author)) {
	            titles.add(book.getTitle());
            }
        }
        return titles;
    }

    public int findNumberOfBooksInYear (int year) {
	    List<Book> books = dataTier.getAllBooks();
	    int numberOfBooks = 0;
	    for (Book book : books) {
	        if (book.getPublicationYear() == year) {
	            numberOfBooks ++;
            }
        }
        return numberOfBooks;
    }

}
