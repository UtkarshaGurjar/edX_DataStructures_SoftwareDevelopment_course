import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) 
	{
		if (list != null) 
		{
			if (list.isEmpty()) 
			{
				list.add(value);
			} 
			else 
			{
				int i;
				for (i = 0; i < list.size(); i++) 
				{
					if (list.get(i) >= value) 
						break;
				}
				list.add(i, value);
			}
        }

	}
	

	public static void removeMaximumValues(LinkedList<String> list, int N) 
	{
		if (list != null && !list.isEmpty()) 
		{
			if (list.size() <= N) 
			{
				list.removeAll(list);
				return;
			}
			for (int i = 0; i < N; i++) 
			{
				LinkedList<String> ll = new LinkedList<>(list);
				Collections.sort(ll, Collections.reverseOrder());
				list.removeAll(Arrays.asList(ll.get(0)));
			}
		}
		

	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) 
	{
		if (one == null || two == null || one.isEmpty() || two.isEmpty()) 
			return false;
		int first = one.indexOf(two.getFirst());
		if (first < 0) 
			return false;
		for (int i = 0; i < two.size(); i++) 
		{
			if (first + i >= one.size()) 
				return false;
			if (!(one.get(first + i).equals(two.get(i)))) 
				return false;
		}
		return true; // this line is here only so this code will compile if you don't modify it
	}
}
