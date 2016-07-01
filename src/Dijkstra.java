import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * This program implements Dijkstra's shorted path algorithm
 */

class Vertex {
	private int edgeLength;
	private int name;
	
	public Vertex (int id, int length) {
		name = id;
		edgeLength = length;		//this is the incoming edge length
	}
	
	public int getLength() {
		return edgeLength;
	}
	
	public void setLength (int length) {
		edgeLength = length;
	}
	
	public int getVertexName() {
		return name;
	}
	
	public void setVertexName (int id) {
		name = id;
	}
	
	@Override
	public boolean equals(Object o){
	    if(o instanceof Vertex){
	    	Vertex c = (Vertex)o;
	      return name == c.name;
	    }
	    return false;
	  }
}

public class Dijkstra {

	public static void main(String[] args) throws FileNotFoundException {
						
		//read in adjacent list file
		File inputFile = new File("dijkstraData.txt");		//dijkstraData		//dijkstra_test200		//dijkstra_test20000
		
		int N = 200;			//this is the # of vertices in graph
		
        Scanner input = new Scanner(inputFile); 
        
        //read the content and then store in an array using a loop
        
    	short[][] graph = new short[N][2*N];
    	int[] nEdge = new int[N];
    	
    	int r = -1;
    	int c;
    	
        while (input.hasNextLine()) {  
        	r++;
        	c = -1;
            
        	String inputEdges = input.nextLine(); 
            Scanner tail = new Scanner(inputEdges);			//tail vertex 
            
            //System.out.print("["+r+"]"+"["+c+"]");
            tail.nextInt();
            
            /*
            if (r == 199) {
            	System.out.print("vertex "+(r+1));
            }
            */

            while (tail.hasNext()) {
            	/*
            	if (r == 199) {
                	System.out.print(" --> ");
            	}
            	*/
            	
            	c++;
            	tail.useDelimiter("\\,");
            	//graph[r][c] = (short) (Integer.parseInt(tail.next().trim()));		//this is for graph starting @ vertex 0
            	graph[r][c] = (short) (Integer.parseInt(tail.next().trim())-1);  //this is for graph starting @ vertex 1
            	/*
            	if (r == 199) {
                	System.out.print("["+r+"]"+"["+c+"]");
                	System.out.print((graph[r][c])+"(");
            	}
            	*/
                
                tail.useDelimiter("\\s*");
                //System.out.print(tail.next()+"-----");
                tail.next();
                
                c++;
                tail.reset();
                graph[r][c] = (short) tail.nextInt();
                
                /*
                if (r == 199) {
                	System.out.print("["+r+"]"+"["+c+"]");
                	System.out.print((graph[r][c])+") ");
                }
                */
                
            }
            
            //System.out.println();
            tail.close();
            
            nEdge[r] = (c+1)/2;
            
            //System.out.print("["+r+"]");
            //System.out.println(nEdge[r]); 
                                 
        }       
        //close the file
        input.close(); 
        
        //System.out.println(".....................");
        
        /*
        for (int i = 0; i < 4; i++){
        	//System.out.println((2*i)+"  "+(2*i+1));
			System.out.println(i+"th entry in graph is: "+graph[3][i]);
			//System.out.println("edge to "+graph[0][2*i]+" with length "+graph[0][2*i+1]+" is added");
		}
        */
		
		Comparator<Vertex> comparator = new EdgeLengthComparator();
		
		//put vertex 1 into list of explored notes
		int explored_vertices = 1;		
		int[] explored = new int[N];
		
		//initialize list containing distance from the staring vertex to other s.t. d(startVertex) = 0
		int startVertex = 0;
		
		int[] dist = new int[N];
		String[] path = new String[N];
				
		dist[startVertex] = 0;
		path[startVertex] = String.valueOf(startVertex);
		
		explored[startVertex] = 1;
		
		int[] crossingEdge = new int[N];			//Dijkstra's score for the crossing edges
		
		
		//set directed edge emanating from the starting vertex to be vertices in priority queue
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(nEdge[startVertex], comparator);
	
		for (int i = 0; i < nEdge[startVertex]; i++) {
			if(queue.contains(new Vertex(graph[startVertex][2*i], graph[startVertex][2*i+1]))) {  //here is the newly picked vertex's edge to an exiting vertex in X
				queue.remove(new Vertex(graph[startVertex][2*i], 1));
				queue.add(new Vertex(graph[startVertex][2*i], Math.min(crossingEdge[graph[startVertex][2*i]], graph[startVertex][2*i+1])));	
				crossingEdge[graph[startVertex][2*i]] = Math.min(crossingEdge[graph[startVertex][2*i]], graph[startVertex][2*i+1]);
			}
			else {	
				queue.add(new Vertex(graph[startVertex][2*i], graph[startVertex][2*i+1]));
				crossingEdge[graph[startVertex][2*i]] = graph[startVertex][2*i+1];
				path[graph[startVertex][2*i]] = String.valueOf(startVertex); 
				
			}

			//System.out.println(graph[0][2*i]+"  "+graph[0][2*i+1]);
            //System.out.println("edge to "+graph[0][2*i]+" with length "+graph[0][2*i+1]+" is added");
		}
		
		//System.out.println("-------------------------");
		      
		        
        //while loop i = 0-198
		while (queue.size() != 0) {
        	//extract_min, put vertex v's name into list of explored notes, add d(v) to list of distance
			Vertex currentVertex = queue.poll();
			
			dist[currentVertex.getVertexName()] = currentVertex.getLength();		
			path[currentVertex.getVertexName()] += "-->"+String.valueOf(currentVertex.getVertexName());
			
			if (explored[currentVertex.getVertexName()] == 1) {
				System.out.print("after exploring "+explored_vertices+" vertices, ");
				System.out.println("adding explored vertex "+currentVertex.getVertexName());
			}
			explored[currentVertex.getVertexName()] += 1;
			
			explored_vertices++;
			
			/*
			System.out.println("vertex "+currentVertex.getVertexName()+" is added to the explored collection");
			System.out.println("before update length");
			
			
			for (int i = 0; i < 5; i++){
	        	//System.out.println((2*i)+"  "+(2*i+1));
				System.out.println(i+"th entry in graph is: "+graph[currentVertex.getVertexName()][i]);
				//System.out.println("edge to "+graph[0][2*i]+" with length "+graph[0][2*i+1]+" is added");
			}
			
			System.out.println("new dist["+currentVertex.getVertexName()+"]: "+dist[currentVertex.getVertexName()]);
			System.out.println("nEdge["+currentVertex.getVertexName()+"]: "+nEdge[currentVertex.getVertexName()]);
			
			
			for (int i = 0; i < 5; i++){
	        	//System.out.println((2*i)+"  "+(2*i+1));
				System.out.println(i+"th entry in graph is: "+graph[currentVertex.getVertexName()][i]);
				//System.out.println("edge to "+graph[0][2*i]+" with length "+graph[0][2*i+1]+" is added");
			}
			
			for (int i = 0; i < 5; i++){
	        	
				System.out.println(i+"th entry in crossingEdge is: "+crossingEdge[i]);
				
			}
			*/
        
        	//add edges from vertex v to the priority queue			
			for (int j = 0; j < nEdge[currentVertex.getVertexName()]; j++) {
				
				//update length of edges coming out from w, the vertex just got selected
				graph[currentVertex.getVertexName()][2*j+1] += dist[currentVertex.getVertexName()];
				//System.out.println("length of edge to "+graph[currentVertex.getVertexName()][2*j]+" has been updated to "+graph[currentVertex.getVertexName()][2*j+1]);
				
				
				//System.out.println("current vertex is: "+currentVertex.getVertexName()+" children vertex is: "+graph[currentVertex.getVertexName()][2*j]);
				//if extract_min give a vertex w already in priority queue, remove vertex w from queue, add it back with edge length = min( original length for w, d(w) )
				if(queue.contains(new Vertex(graph[currentVertex.getVertexName()][2*j], graph[currentVertex.getVertexName()][2*j+1]))) {  //here is the newly picked vertex's edge to an exiting vertex in X
					queue.remove(new Vertex(graph[currentVertex.getVertexName()][2*j], 1));
					queue.add(new Vertex(graph[currentVertex.getVertexName()][2*j], Math.min(crossingEdge[graph[currentVertex.getVertexName()][2*j]], graph[currentVertex.getVertexName()][2*j+1])));	
					//System.out.println("two lengths for comparison are: "+crossingEdge[graph[currentVertex.getVertexName()][2*j]]+" "+graph[currentVertex.getVertexName()][2*j+1]);
					
					if (crossingEdge[graph[currentVertex.getVertexName()][2*j]] > graph[currentVertex.getVertexName()][2*j+1]) {
						crossingEdge[graph[currentVertex.getVertexName()][2*j]] = graph[currentVertex.getVertexName()][2*j+1];
						path[graph[currentVertex.getVertexName()][2*j]] = path[currentVertex.getVertexName()];
					}
				}
				//if extract_min give a vertex w not already in priority queue, update length from vertex v to vertex w, d(w), to be d(v)+l_vw
				else if (explored[graph[currentVertex.getVertexName()][2*j]] == 0) {
					queue.add(new Vertex(graph[currentVertex.getVertexName()][2*j], graph[currentVertex.getVertexName()][2*j+1]));
					crossingEdge[graph[currentVertex.getVertexName()][2*j]] = graph[currentVertex.getVertexName()][2*j+1];
					
					path[graph[currentVertex.getVertexName()][2*j]] = path[currentVertex.getVertexName()];
					
				}
				
			}	
			
			//System.out.println("size of queue is: "+queue.size());
			//System.out.println("=====================");	
						
		}
		
		while(true) {
	        Vertex current = queue.poll();
	        
	        if(current == null) {
	            break;
	        }

	        System.out.print(current.getVertexName() + "("+current.getLength()+") "+" <-- ");
	    }
	    System.out.println();
	    
	    for (int i = 0; i < N; i++) {
	    	if (explored[i] > 1 || explored[i] == 0) {
	    		System.out.println("vertex "+i+" has been explored "+explored[i]+" times");
	    	}
	    }
		
		int[] requestVertex = {7,37,59,82,99,115,133,165,188,197, 155, 96}; 
		
		for (int i=0;i<10;i++) {
			//System.out.print("vertex "+(i+1));
			//System.out.println(" is at distance "+dist[i]+" from vertex 1");
			if (N > 100) {
			System.out.print(dist[requestVertex[i]-1]+",");
			}
			else if (i < N){
				System.out.print(dist[i]+",");
			}
			
		}
		System.out.println();
		
		if (N > 15) {
			for (int i=0;i<10;i++) {
				System.out.println(path[requestVertex[i]-1]);
			}
		}
		else {
			for (int i=0;i<N;i++) {
				System.out.println(path[i]);
			}
		}
			
		
		System.out.println("total explored vertices = "+explored_vertices);
        	    
	}

}

