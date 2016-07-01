import java.util.Comparator;

/*
 * This is the comparator for edge length btw two vertices
 */

public class EdgeLengthComparator implements Comparator<Vertex> 	{
	    @Override
	    public int compare(Vertex x, Vertex y)
	    {
	        // Assume neither string is null. Real code should
	        // probably be more robust
	        // You could also just return x.length() - y.length(),
	        // which would be more efficient.
	        if (x.getLength() < y.getLength()) {
	            return -1;
	        }
	        if (x.getLength() > y.getLength()) {
	            return 1;
	        }
	        return 0;
	    }
	
}
