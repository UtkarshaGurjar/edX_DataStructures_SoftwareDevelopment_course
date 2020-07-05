import java.util.Queue;
import java.util.Stack;

/*
 * SD2x Homework #2
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

public class HtmlValidator 
{
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) 
	{
		Stack<HtmlTag> s = new Stack<>();
		for (HtmlTag tag : tags) 
		{
		    if (tag.isSelfClosing()) 
			    continue;
		    else if (tag.isOpenTag()) 
		    	s.push(tag);
		    else if (!tag.isOpenTag()) 
		    {
			    if (!s.isEmpty()) 
			    {
                    if (s.peek().matches(tag)) 
                    	s.pop();
				    else 
				    	return s;
                } 
			    else 
			    	return null;
                            
            }
		}
        return s;
	
	}
}

