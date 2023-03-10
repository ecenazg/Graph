import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyGraph {

    public static void main(String[] args) {
 
        undirectedGraphDemo();

        undirectedGraphCycle();
    }

    private char[] vertices;
    private int[][] edges;


    public MyGraph(char[] vertices, Map<String, Integer> edges) {
     
        this.vertices = vertices;
        this.edges = new int[this.vertices.length][this.vertices.length];

        for (String key : edges.keySet()) {
            char[] chars = key.toCharArray();
            int from = chars[0] - 'A';
            int to = chars[3] - 'A';

            this.edges[from][to] = edges.get(key);

        }
    }

    
    public static void undirectedGraphDemo() {
        System.out.println("");
        System.out.println("-------- Undirected Graph Demo -----------");

        // undirected graph
        char[] vertices = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Map<String, Integer> edges = new HashMap<>();
        edges.put("A->B", 1);
        edges.put("A->C", 1);
        edges.put("B->D", 2);
        edges.put("B->E", 4);
        edges.put("C->B", 1);
        edges.put("C->E", 3);
        edges.put("D->F", 1);
        edges.put("E->F", 1);

        MyGraph graph = new MyGraph(vertices, edges);

    }

    

    public static void undirectedGraphCycle() {
        System.out.println("");
        System.out.println("-------- Undirected Graph Cycle -----------");

        // undirected graph
        char[] vertices = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Map<String, Integer> edges = new HashMap<>();
        edges.put("A->B", 1);
        edges.put("A->C", 1);
        edges.put("B->D", 2);
        edges.put("B->E", 4);
        edges.put("C->B", 1);
        edges.put("C->E", 3);
        edges.put("D->F", 1);
        edges.put("E->F", 1);

        MyGraph graph = new MyGraph(vertices, edges);
;

 
        // Find all cycles
    }

    public List<Character> dfs() {
        List<Character> result = new ArrayList<>();

        boolean[] visited = new boolean[vertices.length];

        while (true) {
            // Find a unvisited vertex
            int index = -1;
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    index = i;
                    break;
                }
            }
            // If all vertices have been visited, stop the program
            if (index == -1) {
                break;
            }

            //do DFS
            dfsHelper(result, visited, index);
        }

        return result;
    }

    private void dfsHelper(List<Character> result, boolean[] visited, int i) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;        
        result.add(vertices[i]);

        for (int j = 0; j < vertices.length; j++) {
            if (edges[i][j] != 0) {
                dfsHelper(result, visited, j);
            }
        }
    }

    public List<Character> bfs() {
        List<Character> result = new ArrayList<>();
        boolean[] visited = new boolean[vertices.length];

        while (true) {
            int index = -1;

            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                break;
            }

            //bfs
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(index);                          //BFS 加入 queue 前更新 visit 数组
            visited[index] = true;

            while (!queue.isEmpty()) {
                int i = queue.poll();

                result.add(vertices[i]);                // BFS 访问元素时添加到结果集

                for (int j = 0; j < vertices.length; j++) {
                    if (edges[i][j] != 0 && !visited[j]) {
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
            }
        }

        return result;
    }

    public boolean isConnected(char from, char to) {
        int fromIndex = from - 'A';
        int toIndex = to - 'A';

        boolean[] visited = new boolean[vertices.length];
        return isConnectedHelper(fromIndex, toIndex, visited);
    }

    private boolean isConnectedHelper(int index, int toIndex, boolean[] visited) {
        if (index == toIndex) {
            return true;
        }

        if (visited[index]) {
            return false;
        }

        visited[index] = true;

        boolean result = false;
        for (int i = 0; i < edges[index].length; i++) {
            if (edges[index][i] != 0) {
                result = isConnectedHelper(i, toIndex, visited);
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    public int numOfSubgraph() {
        int count = 0;
        boolean[] visited = new boolean[vertices.length];

        while (true) {
            int index = -1;
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i]) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                break;
            }

            count++;

            //bfs
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(index);
            visited[index] = true;

            while (!queue.isEmpty()) {
                int i = queue.poll();
                for (int j = 0; j < edges[i].length; j++) {
                    if (edges[i][j] != 0 && !visited[j]) {
                        queue.offer(j);
                        visited[j] = true;
                    }
                }
            }
        }

        return count;
    }


   

   
   
    /**
     * Cycle problems - undirected graph
     */


    public boolean isCyclicUndirectedGraphTopology() {
       

        int[] degrees = new int[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            int count = 0;
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[j][i] != 0) {
                    count++;
                }
            }
            degrees[i] = count;
        }

        Set<Integer> visited = new HashSet<>();

        while (true) {
            int index = -1;
            for (int i = 0; i < degrees.length; i++) {
                if (degrees[i] <= 1 && !visited.contains(i)) { //island node degree is 0
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                break;
            }

            visited.add(index);

            for (int i = 0; i < edges[index].length; i++) {
                if (!visited.contains(index) && edges[index][i] != 0) {
                    degrees[i]--;
                }
            }
        }

        return visited.size() < vertices.length;
    }

 
    public boolean isCyclicUndirectedGraphDFS() {
        boolean[] visited = new boolean[vertices.length];

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                List<Integer> path = new ArrayList<>();
                boolean result = hasCycleUndirectedGraphDFSHelper(i, -1, visited);
                if (result) {
                    return true;
                }
                break;
            }
        }

        return false;
    }

    private boolean hasCycleUndirectedGraphDFSHelper(int i, int prev, boolean[] visited) {
        if (visited[i]) { 
            return true;
        }

        visited[i] = true;

        for (int j = 0; j < edges[i].length; j++) {
            if (j != prev && edges[i][j] != 0) {
                boolean result = hasCycleUndirectedGraphDFSHelper(j, i, visited);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

   
}