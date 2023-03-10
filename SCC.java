import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;


public class SCC{



    private int V; //number of vertices
    private int E; // number of edges
    
    private LinkedList<Integer> adj[]; //Adjacency List
    public SCC(int v) {
        this.V = v;
        adj= new LinkedList[v];
        for( int i=0; i<v; ++i){
            adj[i]= new LinkedList<>();

        }


        
    }

    public void addEdge (int v, int w){

        adj[v].add(w);
    
    }
    public void DFSUtil(int v, boolean visited[]){
        visited[v]= true;
        System.out.println(v+ " ");
        int n;
        Iterator<Integer> i = adj[v].iterator();
        while(i.hasNext()){
            n=i.next();
            if(!visited[n])
                DFSUtil(n, visited);
        }
    }
    SCC getTranspose(){ //tersini alma
        SCC g = new SCC(V);
        for(int v=0; v<V; v++){
            Iterator<Integer> i = adj[v].listIterator();
            while(i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }
    public void fillOrder(int v, boolean visited[], Stack stack){
        visited[v]=true;
        Iterator<Integer> i = adj[v].iterator();
        while(i.hasNext()){
            int n= i.next();
            if(!visited[n])
                fillOrder(v, visited, stack);
        }
        stack.push(new Integer(v));
    }
    public void printSCC(){
        Stack stack = new Stack<>();
        boolean visited[] = new boolean[V];
        for(int i=0; i<V; i++)
            visited[i]= false;

        for(int i=0; i<V; i++){
            if(visited[i]==false)
                fillOrder(i, visited, stack);
        }
        SCC gr= getTranspose();
        for(int i=0; i<V; i++)
        visited[i]=false;

        while(stack.empty()==false){
            int v=(int) stack.pop();

            if(visited[v]==false){
                gr.DFSUtil(v, visited);
                System.out.println();
            }
        }

    }
    public static void main(String[] args) {
        SCC g = new SCC(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);


        g.printSCC();

    }


}