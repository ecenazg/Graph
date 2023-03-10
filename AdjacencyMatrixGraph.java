public class AdjacencyMatrixGraph{
    private int V; //number of vertices
    private int E; //number of edges
    private int size; //size of the matrix
    private Vertex2[][] adjacencyMatrix;

    public AdjacencyMatrixGraph(int size)
    {   
       
        this.size = size;
        this.adjacencyMatrix = new Vertex2[size][size];
    }

    //=========================================================================
    // Public methods
    //=========================================================================
    /**
     * Adds an edge to the adjacency matrix.
     */
    public void addVertex()
    {
        size++;
        Vertex2[][] temp = adjacencyMatrix;
        this.adjacencyMatrix = new Vertex2[size][size];
        for (int i = 0; i < size - 1; i++)
        {
            for (int j = 0; j < size - 1; j++)
            {
                adjacencyMatrix[i][j] = temp[i][j];
            }
        }
    }

    /**
     * Removes an edge from the adjacency matrix.
     */
    public void removeVertex()
    {
        size--;
        Vertex2[][] temp = adjacencyMatrix;
        this.adjacencyMatrix = new Vertex2[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                adjacencyMatrix[i][j] = temp[i][j];
            }
        }
    }

    /**
     * Adds an edge to the adjacency matrix.
     * @param i Index of the first vetrex.
     * @param j Index of the second vertex.
     */
/*     public void addEdge(int i, int j)
    {
        adjacencyMatrix[i][j] = ;
        adjacencyMatrix[j][i] = 1;
    }*/

    /**
     * Removes an edge from the adjacency matrix.
     * @param i Index of the first vetrex.
     * @param j Index of the second vertex.
     */
 /*    public void removeEdge(int i, int j)
    {
        adjacencyMatrix[i][j] = 0;
        adjacencyMatrix[j][i] = 0;
    }
*/
    /**
     * Checks if the first vertex has a connection to the second vertex.
     * @param i The index of the first vertex.
     * @param j The index of the second vertex.
     * @return True if the vertices are connected by an edge, false otherwise.
     */
    /*public boolean hasConnection(int i, int j)
    {
        return adjacencyMatrix[i][j] == 1;
    }

    /**
     * Prints the contents of the graph.
     */
    public void print()
    {
        System.out.print("    ");
        String s = "   -";
        for (int e = 0; e < size; e++)
        {
            System.out.print(e + " ");
            s += "--";
        }
        System.out.println("\n" + s);
        for (int i = 0; i < size; i++)
        {
            System.out.print(i + " | ");
            for (int j = 0; j < size; j++)
            {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}