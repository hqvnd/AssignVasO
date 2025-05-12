import java.util.*;
import java.util.stream.Collectors;

public class WeightedGraph<V> {

    private final boolean undirected;
    private final Map<V, Vertex<V>> vertices = new HashMap<>();

    public WeightedGraph() {
        this(true);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V data) {
        vertices.computeIfAbsent(data, Vertex::new);
    }

    public void addEdge(V source, V dest, double weight) {
        if (Objects.equals(source, dest)) return;
        addVertex(source);
        Vertex<V> from = vertices.get(source);

        addVertex(dest);
        Vertex<V> to = vertices.get(dest);

        from.addAdjacentVertex(to, weight);
        if (undirected) {
            to.addAdjacentVertex(from, weight);
        }
    }

    public Set<V> adjacencyList(V v) {
        Vertex<V> vertex = vertices.get(v);
        if (vertex == null) return Collections.emptySet();

        return vertex.getAdjacentVertices()
                .keySet()
                .stream()
                .map(Vertex::getData)
                .collect(Collectors.toSet());
    }

    public double getWeight(V source, V dest) {

        Vertex<V> from = vertices.get(source);
        Vertex<V> to   = vertices.get(dest);

        if (from == null || to == null) {
            throw new IllegalArgumentException("???-vertex");
        }
        Double w = from.getAdjacentVertices().get(to);

        if (w == null) {
            throw new IllegalArgumentException("null edge");
        }
        return w;
    }
}