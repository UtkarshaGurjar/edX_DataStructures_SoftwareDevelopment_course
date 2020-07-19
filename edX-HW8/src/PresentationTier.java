import java.util.*;

/*
 * SD2x Homework #8
 * This class represents the Presentation Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below. 
 * Also implement the start method as described in the assignment description.
 */

public class PresentationTier {
	
	private LogicTier logicTier; // link to the Logic Tier
	
	public PresentationTier(LogicTier logicTier) {
		this.logicTier = logicTier;
	}
	
	public void showBookTitlesByAuthor() {
        System.out.println("Enter author's name.");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String author = scanner.nextLine();
            System.out.print(logicTier.findBookTitlesByAuthor(author));
        }
        scanner.close();
    }

    public void showNumberOfBooksInYear() {
        System.out.println("Enter publication year.");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int year = scanner.nextInt();
            System.out.print(logicTier.findNumberOfBooksInYear(year));
        }
        scanner.close();
    }
	
	public void start() {
		
		/* IMPLEMENT THIS METHOD */
		System.out.println("Search by (1) Author or (2) Publication Year?");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
        }
        if (option == 1) {
            showBookTitlesByAuthor();
        } else if (option == 2) {
            showNumberOfBooksInYear();
        } else {
            System.out.println("Wrong option.");
        }
        scanner.close();
	}
	

}
