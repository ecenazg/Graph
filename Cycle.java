//-----------------------------------------------------
// Title: Cycle Class
// Author: Ecenaz Güngör
// ID: 38210050188
// Section: 1
// Assignment: 1
// Description: This class tests if the undirected graph contains any cycle or not.
//-----------------------------------------------------
import java.util.Stack;

 /*  count number of edges in a graph
 *  check if graph has loops (detect cycle)
 *  find if a graph is tree or not (no cycle & connected)
 * */
public class Cycle {
    private boolean[] marked;
    private int[] edgeTo;
    //private Stack<Integer> cycle;
    private Stack<Character> cycle;  
    /**
     * Determines whether the undirected graph {@code G} has a cycle and,
     * if so, finds such a cycle.
     *
     * @param G the undirected graph
     */
    // I am using depth first search.
    public Cycle(Graph2 G) {
        // need special case to identify parallel edge as a cycle
        if (hasParallelEdges(G)) return;

        // don't need special case to identify self-loop as a cycle
        // if (hasSelfLoop(G)) return;

        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v])
                dfs(G, -1, v);
    }
	
	

    // does this graph have two parallel edges?
    // side effect: initialize cycle to be two parallel edges
    private boolean hasParallelEdges(Graph2 G) {
        marked = new boolean[G.V()];

        for (char v = 0; v < G.V(); v++) {

            // check for parallel edges incident to v
            for (int w : G.adj(v)) {
                if (marked[w]) {
                    cycle = new Stack<Character>();
                    cycle.push((char) v);
                    cycle.push((char) w);
                    cycle.push((char) v);
                    return true;
                }
                marked[w] = true;
            }

            // reset so marked[v] = false for all v
            for (int w : G.adj(v)) {
                marked[w] = false;
            }
        }
        return false;
    }
/*  Detect cycle in an undirected graph
	 *    Like directed graphs, I can use DFS to detect cycle in an undirected graph in O(V+E) time.
	 *    I do a DFS traversal of the given graph. For every visited vertex, 
	 *    if there is an adjacent such that u is already visited and u is not parent of v, 
	 *    then there is a cycle in graph.
	 *    The assumption of this approach is that there are no parallel edges between any two vertices
	 *    My source is given below. I inspired from it.
	 *    https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
	 * */
    /**
     * Returns true if the graph {@code G} has a cycle.
     *
     * @return {@code true} if the graph has a cycle; {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }
    public Stack<Character> cycle() {
        return cycle;
    }
    private void dfs(Graph2 G, int i, int v) {
        marked[v] = true;
        for (char w : G.adj(v)) {
            if (cycle != null) return;

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, v, w);}
            else if (w != i) {
                cycle = new Stack<Character>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push((char) x);}
                    cycle.push((char) w);
                    cycle.push((char) v);
            }
        }
    }

    /**
     * Unit tests the {@code Cycle} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
       
        Graph2 g = new Graph2(6);
        g.addEdge('a', 'c');
		g.addEdge('a', 'e');
		g.addEdge('a', 'f');
		g.addEdge('c', 'e');
	
		g.addEdge('e', 'f');
		g.addEdge('e', 'b');
        Cycle finder = new Cycle(g);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {

                System.out.print(v + " "); 
            }
           // StdOut.println();
        }
        else {
            System.out.println("No Cycle."); 
        }
    }

}