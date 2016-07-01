import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * This program implements Dijkstra's shorted path algorithm
 */

public class testVertexObject {

	public static void main(String[] args) {
		
		Comparator<Vertex> comparator = new EdgeLengthComparator();
		
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(200, comparator);

		
		queue.add(new Vertex(1, 4));
        queue.add(new Vertex(2, 4));
        queue.add(new Vertex(3, 8));
        queue.add(new Vertex(4, 9));
        queue.add(new Vertex(5, 4));
        queue.add(new Vertex(6, 12));
        queue.add(new Vertex(7, 9));
        queue.add(new Vertex(8, 11));
        queue.add(new Vertex(9, 13));
        
        System.out.println ( "Initial priority queue values are: "+ queue.peek().getLength());
		
		while(true) {
	        Vertex currentVertex = queue.poll();
	        
	        if(currentVertex == null) {
	            break;
	        }

	        System.out.print(currentVertex.getVertexName() + "("+currentVertex.getLength()+") "+" <-- ");
	    }
	    System.out.println();
	    
	    System.out.println("==================================================");
	    
	    queue.add(new Vertex(1, 4));
        queue.add(new Vertex(2, 4));
        queue.add(new Vertex(3, 8));
        queue.add(new Vertex(4, 9));
        queue.add(new Vertex(5, 4));
        queue.add(new Vertex(6, 12));
        queue.add(new Vertex(7, 9));
        queue.add(new Vertex(8, 11));
        queue.add(new Vertex(9, 13));
	    queue.add(new Vertex(10, 7));
	    queue.add(new Vertex(11, 10));
	    queue.add(new Vertex(12, 5));
        
        
	    while(true) {
	        Vertex currentVertex = queue.poll();
	        
	        if(currentVertex == null) {
	            break;
	        }

	        System.out.print(currentVertex.getVertexName() + "("+currentVertex.getLength()+") "+" <-- ");
	    }
	    System.out.println();
        
	    System.out.println("==================================================");
	    
	    queue.add(new Vertex(1, 4));
        queue.add(new Vertex(2, 4));
        queue.add(new Vertex(3, 8));
        queue.add(new Vertex(4, 9));
        queue.add(new Vertex(5, 4));
        queue.add(new Vertex(6, 12));
        queue.add(new Vertex(7, 9));
        queue.add(new Vertex(8, 11));
        queue.add(new Vertex(9, 13));
	    queue.add(new Vertex(10, 7));
	    queue.add(new Vertex(11, 10));
	    Vertex tbr = new Vertex(12, 5);
	    queue.add(tbr);
	    queue.remove(tbr);
	    System.out.println(queue.contains(new Vertex(11, 10)));
        
        
	    while(true) {
	        Vertex currentVertex = queue.poll();
	        
	        if(currentVertex == null) {
	            break;
	        }

	        System.out.print(currentVertex.getVertexName() + "("+currentVertex.getLength()+") "+" <-- ");
	    }
	    System.out.println();
	}

}

