/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.Map;
import java.util.function.Predicate;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
	    List<String> alphabeticalMovies = new ArrayList<>();
		if (movieRatings == null || movieRatings.isEmpty()) 
			return alphabeticalMovies;
		alphabeticalMovies.addAll(movieRatings.keySet());
        return alphabeticalMovies;
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
	    List<String> alphabeticalMovies = new ArrayList<>();
	    if (movieRatings == null || movieRatings.isEmpty()) 
	    	return alphabeticalMovies;
	    for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
	        if (entry.getValue().peek() > rating) {
	            alphabeticalMovies.add(entry.getKey());
            }
        }
        return alphabeticalMovies;
	}
	
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
	    TreeMap<String, Integer> removedRatings = new TreeMap<>();
	    if (movieRatings == null || movieRatings.isEmpty() || rating < 0) 
	    	return removedRatings;
	    ArrayList<String> emptyRatings = new ArrayList<>();
	    for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
	        if (entry.getValue().peek() >= rating) 
	        	continue;
	        PriorityQueue<Integer> ratings = entry.getValue();
	        int allRatings = ratings.size();
            Predicate<Integer> ratingPredicate = r -> r < rating;
	        ratings.removeIf(ratingPredicate);
	        if (ratings.isEmpty()) 
	        	emptyRatings.add(entry.getKey());
	        removedRatings.put(entry.getKey(), allRatings - ratings.size());
        }
        if (!emptyRatings.isEmpty()) {
	        for (String title : emptyRatings) {
	            movieRatings.remove(title);
            }
        }
        return removedRatings;
	}
}
