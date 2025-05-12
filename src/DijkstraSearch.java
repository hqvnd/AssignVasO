import java.util.*;

public class DijkstraSearch<V> extends Search<V> {
    private final Map<V, Double> dist = new HashMap<>();
    private final WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        this.graph = graph;
        dijkstra();
    }

    private void dijkstra() {
        PriorityQueue<V> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(dist::get));
        dist.put(source, 0.0);
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            V v = priorityQueue.poll();

            if (!marked.add(v)) {
                continue;
            }

            for (V neighbour : graph.adjacencyList(v)) {

                double newDist = dist.get(v) + graph.getWeight(v, neighbour);

                if (newDist < dist.getOrDefault(neighbour, Double.MAX_VALUE)) {
                    dist.put(neighbour, newDist);
                    edgeTo.put(neighbour, v);
                    priorityQueue.remove(neighbour);
                    priorityQueue.add(neighbour);
                }
            }
        }
    }
}