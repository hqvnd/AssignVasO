import java.util.*;

public class BreadthFirstSearch<V> extends Search<V> {

    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<V> graph, V start) {
        Queue<V> queue = new LinkedList<>();
        marked.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            V v = queue.remove();
            for (V neighbour : graph.adjacencyList(v)) {
                if (marked.add(neighbour)) {
                    edgeTo.put(neighbour, v);
                    queue.add(neighbour);
                }
            }
        }
    }
}