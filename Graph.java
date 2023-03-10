//-----------------------------------------------------
// Title: Graph Class
// Author: Ecenaz Güngör
// ID: 38210050188
// Section: 1
// Assignment: 2
// Description: This class graph structure implementation.
//-----------------------------------------------------

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Graph {
	private Map<Vertex, Set<Vertex>> graphAdj;	
	
	public Map<Vertex, Set<Vertex>> getGraphAdj() {
		return graphAdj;
	}
	
	public Graph() {
		graphAdj = new HashMap<>();
	}

	public void setGraphAdj(Map<Vertex, Set<Vertex>> graphAdj) {
		this.graphAdj = graphAdj;
	}

	void addVertex(String vertexElement) {
		graphAdj.putIfAbsent(new Vertex(vertexElement), new TreeSet<>());			
	}
	
	void addVertex(Vertex ver) {
		graphAdj.putIfAbsent(ver, new TreeSet<>());			
	}
	
	// Unidirected edge
	void addEdge(String vertexElement1, String vertexElement2) {
	    Vertex v1 = new Vertex(vertexElement1);
	    Vertex v2 = new Vertex(vertexElement2);
	    graphAdj.get(v1).add(v2);
	}
	
	// Unidirected edge
		void addEdge(Vertex v1, Vertex v2) {
		    graphAdj.get(v1).add(v2);
		}

	
}
