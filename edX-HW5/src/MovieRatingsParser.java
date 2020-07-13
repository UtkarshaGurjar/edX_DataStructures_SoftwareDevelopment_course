/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser 
{

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) 
	{
		
		TreeMap<String, PriorityQueue<Integer>> ratingsMap = new TreeMap<>();
	    if (allUsersRatings == null || allUsersRatings.isEmpty()) return ratingsMap;
        for (UserMovieRating umr : allUsersRatings) {
            if (umr == null || umr.getMovie() == null || umr.getMovie().isEmpty() || umr.getUserRating() < 0) continue;
            String movie = umr.getMovie().toLowerCase();
            PriorityQueue<Integer> pq;
            if (ratingsMap.containsKey(movie)) {
                pq = ratingsMap.get(movie);
                pq.add(umr.getUserRating());
            } else {
                pq = new PriorityQueue<>();
                pq.add(umr.getUserRating());
                ratingsMap.put(movie, pq);
            }
        }
        return ratingsMap;
	}
}
