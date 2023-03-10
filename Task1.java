//-----------------------------------------------------
// Title: Task 1 Class
// Author: Ecenaz Güngör
// ID: 38210050188
// Section: 1
// Assignment: 2
// Description: This class tests the certain course is a prerequisite for another course..
//-----------------------------------------------------

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Task1 {

	static String[] courseCodes;
	static String[] prereqLines;
	static String courseCodeToCheck;
	
	public static void main(String[] args) {
		
		// Reading input from user
		readInput();

		// Creating unidirected graph
		Graph adjGraph = initGraph();
		
		//BFS search on the graphs, results are already sorted.
		Set<Vertex> sortedSet = bfs(adjGraph);
		
		// Already sorted vertex result set
		printResultSet(sortedSet);
	
	}

	private static void readInput() {
		
		Scanner sc = new Scanner(System.in);

		// Parsing input
		int numberOfCourse = Integer.parseInt(sc.nextLine());
		String courseCodeStr = sc.nextLine();
		courseCodes = courseCodeStr.split(",");
		int prerequisiteLen = Integer.parseInt(sc.nextLine());

		prereqLines = new String[prerequisiteLen];
		for(int i=0;i<prerequisiteLen;i++) {
			String line = sc.nextLine();
			prereqLines[i] = line;
		}
		
		courseCodeToCheck = sc.nextLine();
		sc.close();
	}

	private static void printResultSet(Set<Vertex> resultSet) {
		if(resultSet.size()==0) {
			System.out.println("There is no prerequisite for this course!");
			return;
		}
		String str = "";
		for(Vertex v:resultSet)
			str+=v.getVertexElement()+",";
		System.out.println(str.substring(0,str.length()-1));
		
	}

	private static Set<Vertex> bfs(Graph adjGraph) {
		Map<Vertex, Set<Vertex>> gr = adjGraph.getGraphAdj();
		// Results are stored in Sorted set
		Set<Vertex> retSet = new TreeSet<Vertex>();
		
		// Visited vertices are stored in here
		Set<Vertex> visited = new HashSet<Vertex>();
		
		// BFS
		Queue<Vertex> q = new LinkedList<>();
		Vertex seed = new Vertex(courseCodeToCheck);
		q.add(seed);
		visited.add(seed);
		
		// Queue approach for BFS
		while(!q.isEmpty()) {
			Vertex polled = q.poll();
			Set<Vertex> polledSet = gr.get(polled);
			retSet.addAll(polledSet);
			for(Vertex v:polledSet) {
				if(!visited.contains(v)) {
					q.add(v);
					visited.add(v);
				}
			}
		}
		return retSet;

	}

	private static Graph initGraph() {
		Graph gr = new Graph();
		for(String str:courseCodes) {
			gr.addVertex(str);
		}
		
		// I store edges reversed ordered
		for(String str:prereqLines) {
			String[] crs = str.split("-");
			gr.addEdge(crs[1], crs[0]);
		}
		return gr;
	}

}
