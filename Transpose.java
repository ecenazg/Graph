import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class Transpose {
	public static void main(String[] args) {
        
		Map<Vertex2, List<Vertex2>> graph = new LinkedHashMap<>();

		graph.put(new Vertex2("a"), List.of(new Vertex2("b")));
		graph.put(new Vertex2("b"), List.of(new Vertex2("c"), new Vertex2("e"), new Vertex2("f")));
		graph.put(new Vertex2("c"), List.of(new Vertex2("d"), new Vertex2("g")));
		graph.put(new Vertex2("d"), List.of(new Vertex2("c"), new Vertex2("h")));
		graph.put(new Vertex2("e"), List.of(new Vertex2("a"), new Vertex2("f")));
		graph.put(new Vertex2("f"), List.of(new Vertex2("g")));
		graph.put(new Vertex2("g"), List.of(new Vertex2("h"), new Vertex2("f")));
		graph.put(new Vertex2("h"), List.of(new Vertex2("h")));

		System.out.println("Graph ->");
		graph.forEach((k, v) -> System.out.println(k + " | " + v));
		System.out.println("Transposed Graph ->");
		Map<Vertex2, List<Vertex2>> graphT = transpose(graph);
		graphT.forEach((k, v) -> System.out.println(k + " | " + v));
	}

	public static Map<Vertex2, List<Vertex2>> transpose(Map<Vertex2, List<Vertex2>> graph) {
		Map<Vertex2, List<Vertex2>> graphT = new LinkedHashMap<>();
		for (Vertex2 u : graph.keySet()) {
			for (Vertex2 v : graph.get(u)) {
				List<Vertex2> list = graphT.getOrDefault(v, new ArrayList<>());
				list.add(u);
				graphT.put(v, list);
			}
		} 
		return graphT;
	}
}