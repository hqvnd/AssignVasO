package graph.path;

import graph.MyGraph;

import java.util.*;

public class DijkstraSearch<Vertex> implements Search<Vertex> {
    public <Graph extends MyGraph<Vertex>> Map<Vertex, Integer> dijkstra(Graph graph, Vertex start) {
        Map<Vertex, Integer> distances = new HashMap<>();
        PriorityQueue<VertexDistancePair<Vertex>> pq = new PriorityQueue<>(Comparator.comparingInt(VertexDistancePair::getDistance));
        Set<Vertex> settled = new HashSet<>();

        distances.put(start, 0);
        pq.add(new VertexDistancePair<>
                (start, 0));

        while (!pq.isEmpty()) {
            Vertex current = pq.poll().getVertex();
            settled.add(current);

            for (var edge : graph.getEdges(current)) {
                Vertex neighbor = edge.getDest();
                if (!settled.contains(neighbor)) {
                    int newDist = distances.get(current) + edge.getWeight();
                    if (newDist < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        distances.put(neighbor, newDist);
                        pq.add(new VertexDistancePair<>(neighbor, newDist));
                    }
                }
            }
        }

        return distances;
    }

    private static class VertexDistancePair<Vertex> {
        private Vertex vertex;
        private int distance;

        public VertexDistancePair(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Vertex getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }
    }
}