import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *         * We can use DFS to find cycles from both directed and undirected
 *         graphs. To find all the vertices that form a cycle, we can run
 *         StronglyConnectedComponents
 *
 */

public class FindCycles {
	public static void main(String[] args) {

		// will find a cycle
		Map<Vertex2, List<Vertex2>> graph = testData0();


		DFS_findCycles_Undirected(graph);


		PRINT_CYCLE_IN_UNDIRECTED(graph);
	}

	/**
	 * As per the internet, finding all the cycles in an undirected graph is
	 * NP-Complete. Hence aborting attempt.
	 */
	private static void PRINT_CYCLE_IN_UNDIRECTED(
			Map<Vertex2, List<Vertex2>> graph) {
		for (Vertex2 u : graph.keySet()) {
			u.p = null;
			u.c = Color.WHITE;
		}

		for (Vertex2 u : graph.keySet()) {
			if (u.c == Color.WHITE) {
				Stack<Vertex2> stack = new Stack<>();
				if (DFS_DETECT_CYCLES_UNDIRECTED(graph, u, stack)) {
				
					Vertex2 last = stack.pop();
					String label = last.label;
					while (last != null) {
						System.out.print(last.label + " ");
						last = last.p;
						if (last == null || last.label.equals(label)) {
							break;
						}
					}
					System.out.println();
				}
				for (Vertex2 _u : graph.keySet()) {
					_u.p = null;
					// _u.c = Color.WHITE;
				}
			}
		}
	}

	private static boolean DFS_DETECT_CYCLES_UNDIRECTED(
			Map<Vertex2, List<Vertex2>> graph, Vertex2 u, Stack<Vertex2> stack) {
		u.c = Color.BLACK;
		for (Vertex2 v : graph.get(u)) {
			if (v.c == Color.BLACK && v != u.p) {
				v.p = u;
				stack.push(v);
				return true;
			}
			if (v.c == Color.WHITE) {
				v.p = u;
				if (DFS_DETECT_CYCLES_UNDIRECTED(graph, v, stack)) {
					return true;
				}
			}
		}
		return false;
	}

	private static void DFS_findCycles_Undirected(
			Map<Vertex2, List<Vertex2>> graph) {
		for (Vertex2 u : graph.keySet()) {
			u.c = Color.WHITE;
			u.p = null;
		}

		boolean cycle = false;
		for (Vertex2 u : graph.keySet()) {
			if (u.c == Color.WHITE) {
				if (DFS_VISIT_findCycles_Undirected(graph, u)) {
				
					cycle = true;
					break;
				}
			}
		}

		if (!cycle) {
			System.out.println("No Cycle");
		}
	}

	private static boolean DFS_VISIT_findCycles_Undirected(
			Map<Vertex2, List<Vertex2>> graph, Vertex2 u) {
		u.c = Color.BLACK;
		for (Vertex2 v : graph.get(u)) {
			if (v.c == Color.BLACK && v != u.p) {
				return true;
			} else if (v.c == Color.WHITE) {
				v.p = u;
				if (DFS_VISIT_findCycles_Undirected(graph, v)) {
					return true;
				}
			}
		}
		return false;
	}


	private static Map<Vertex2, List<Vertex2>> testData0() {
		Vertex2 a = new Vertex2("a");
		Vertex2 b = new Vertex2("b");
		Vertex2 c = new Vertex2("c");
		Vertex2 d = new Vertex2("d");
		Vertex2 e = new Vertex2("e");
		Vertex2 f = new Vertex2("f");
		Vertex2 g = new Vertex2("g");
		Vertex2 h = new Vertex2("h");
		Map<Vertex2, List<Vertex2>> graph = Map.of(a, List.of(b), b,
				List.of(c, e, f), c, List.of(d, g), d, List.of(c, h), e,
				List.of(a, f), f, List.of(g), g, List.of(f, h), h, List.of(h));
		return graph;
	}

}

