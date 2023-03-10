// Java program to print all the cycles
// in an undirected graph
import java.util.*;
 
class GraphColoring
{
 
    static final int N = 100000;
 
    // variables to be used
    // in both functions
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] graph = new Vector[N];
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] cycles = new Vector[N];
    static int cycleNumber;
 
    // Function to mark the vertex with
    // different colors for different cycles
    static void dfs_cycle(int u, int p, int[] color,int[] par)
    {
 
        // already (completely) visited vertex.
        if (color[u] == 2)
        {
            return;
        }
 
        // seen vertex, but was not completely visited -> cycle detected.
        // backtrack based on parents to find the complete cycle.
        if (color[u] == 1)
        {
 
             
              Vector<Integer> v = new Vector<Integer>();
            int cur = p;
              v.add(cur);
 
            // backtrack the vertex which are
            // in the current cycle thats found
            while (cur != u)
            {
                cur = par[cur];
                v.add(cur);
            }
              cycles[cycleNumber] = v;
            cycleNumber++;
            return;
        }
        par[u] = p;
 
        // partially visited.
        color[u] = 1;
 
        // simple dfs on graph
        for (int v : graph[u])
        {
 
            // if it has not been visited previously
            if (v == par[u])
            {
                continue;
            }
            dfs_cycle(v, u, color, par);
        }
 
        // completely visited.
        color[u] = 2;
    }
 
    // add the edges to the graph
    static void addEdge(int u, int v)
    {
        graph[u].add(v);
        graph[v].add(u);
    }
 
    // Function to print the cycles
    static void printCycles()
    {
 
        // print all the vertex with same cycle
        for (int i = 0; i < cycleNumber; i++)
        {
            // Print the i-th cycle
            System.out.printf("%d) ", i + 1);
            for (int x : cycles[i])
                System.out.printf("%d ", x);
            System.out.println();
        }
    }
 
    // Driver Code
    public static void main(String[] args)
    {
 
        for (int i = 0; i < N; i++)
        {
            graph[i] = new Vector<>();
            cycles[i] = new Vector<>();
        }
 
        // add edges
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(3, 4);
        addEdge(4, 6);
        addEdge(4, 7);
        addEdge(5, 6);
        addEdge(3, 5);
        addEdge(7, 8);
        addEdge(6, 10);
        addEdge(5, 9);
          addEdge(10, 9);
        addEdge(10, 11);
        addEdge(11, 12);
        addEdge(11, 13);
        addEdge(12, 13);
 
        // arrays required to color the
        // graph, store the parent of node
        int[] color = new int[N];
        int[] par = new int[N];
 
        // mark with unique numbers
        //int[] mark = new int[N];
 
        // store the numbers of cycle
        cycleNumber = 0;
 
        // call DFS to mark the cycles
        dfs_cycle(1, 0, color, par);
 
        // function to print the cycles
        printCycles();
    }
}