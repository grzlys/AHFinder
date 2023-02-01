package ahfinder.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ahfinder.graph.Visited.WHITE;

@Getter
@AllArgsConstructor
public class Graph {

    private static final List<Vertex> EMPTY_LIST = new LinkedList<>();
    private List<Vertex> vertexes;


    // TODO where remove duplicated vertices. investigate situation where 2 different vertices
    //      has the same url, and the same distance form root  (2nd input example, two vertices with distance from origin = 0)
    public static Graph fillGraphWithDepthFrom(@NonNull String startingUrl, int depth) throws IOException {
        Graph graph = new Graph(EMPTY_LIST);

        int currentDepth = 0;
        Vertex startingPoint = new Vertex(startingUrl, EMPTY_LIST, WHITE, null, currentDepth);
        startingPoint.attachNeighbours();
        graph.addNewVertex(startingPoint);
        graph.addNewVertexes(startingPoint.getNeighbours());
        currentDepth++;

        while (currentDepth <= depth) {
            System.out.println(" ======= Wokring on depth " + currentDepth + " ======= ");
            graph.retrieveVerticesDistantBy(currentDepth).forEach(
                    vertex -> {
                        System.out.println("Attaching neighbours for vertex with url " + vertex.getUrl());
                        vertex.attachNeighbours();
                        graph.addNewVertexes(vertex.getNeighbours());
                    });
            currentDepth++;
        }
        return graph;
    }


    private List<Vertex> retrieveVerticesDistantBy(int distanceFromRoot) {
        return vertexes.stream().filter(vertex -> vertex.getDistanceFromOrigin() == distanceFromRoot).collect(Collectors.toList());
    }

    private void addNewVertex(Vertex vertex) {
        vertexes.add(vertex);
    }

    private void addNewVertexes(List<Vertex> list) {
        vertexes.addAll(list);
    }
}
