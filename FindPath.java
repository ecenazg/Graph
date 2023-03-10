//-----------------------------------------------------
// Title: Find Path Class
// Author: Ecenaz Güngör
// ID: 38210050188
// Section: 1
// Assignment: 1
// Description: This class tests the if there is a path between two nodes in an undirected graph.
//-----------------------------------------------------
import java.util.List;
import java.util.LinkedList;

/*  find if an undirected graph is connected
 *  count number of edges in a graph
 *  check if graph has loops (detect cycle)
 *  find if a graph is tree or not (no cycle & connected)
 *  Is there a path between two nodes in this undirected graph, and if yes print them all? 
 *  		(solution needs to get optimized-space-wise)
 */
public class FindPath extends Graph2{

	FindPath(int v) {
		super(v);
	}
	
	boolean ifConnected(){
		boolean[] visited = new boolean[V()];
		DFSRecursive((char) 0, visited);
		for(int v = 0 ; v < v; v++){
			if(!visited[v]){
				return false;
			}
		}
		return true;
	}
	
	int numberOfEdges(){
		int count = 0;
		for(int v = 0; v < V(); v++ ){
			count += adj[v].size();
		}
		return count/2;
	}
	

	
	boolean detectCycleDFS(int node, int parentNode, boolean[] visited){
		visited[node] = true;
		for(int adjNode : adj[node]){
			if(adjNode != parentNode){
				if(visited[adjNode]){ 
					return true;
				}else{
					if(detectCycleDFS(adjNode, node, visited)){
						return true;
					}
				}
			}
		}
		return false;
	}
	boolean isCyclic(){
		boolean[] visited = new boolean[V()];
		for(int v = 0; v < V(); v++){
			if(!visited[v]){
				if(detectCycleDFS(v, v, visited)){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/*  find if there is a path between 2 nodes (source, destination) in an undirected graph (and print them all,if any)
	 * 
	 * path : ordered list of ancestors from source to destination
	 * 
	 * ***NOT OPTIMUM SOLUTION (for space) : extra usages of ancestors list for each path!
	 * */
	public boolean findPath(char source, char des){
		List<Character> ancestors = new LinkedList<>();
		return hasPathRecursive(source, des, ancestors);
	}
	public static boolean hasPathRecursive(char node, char des, List<Character> ancestors){
		if(node == des){
			ancestors.add((char) node);
			System.out.println(ancestors);
			return true;
		}
		boolean isPath = false;
		for(Character adj : adj[node]){
			if (!ancestors.contains(adj)){
				LinkedList<Character> adjAncestor = new LinkedList<>(ancestors);
				adjAncestor.add((char) node);
				if(hasPathRecursive(adj, des, adjAncestor)){
					isPath = true;
				}
			}
		}
		return isPath;
	}
    public static void main(String[] args) {

        FindPath g = new FindPath(5);
      
		g.addEdge('a', 'e');
		g.addEdge('a', 'f');
		g.addEdge('c', 'e');
	
		g.addEdge('e', 'f');
		g.addEdge('e', 'b');

    System.out.println( g.findPath('a', 'e'));

            }



    
}