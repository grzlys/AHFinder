package ahfinder.graph;

import ahfinder.link.Retriever;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Vertex {

    private String url;
    private List<Vertex> neighbours;
    private Visited visited;
    private Vertex parent;
    private int distanceFromOrigin;

    public void attachNeighbours() {
        Retriever.retrieveLinksFrom(this.getUrl())
                .forEach(link ->
                        this.getNeighbours().add(
                                new Vertex(link,
                                        new LinkedList<>(),
                                        Visited.WHITE,
                                        this,
                                        this.getDistanceFromOrigin() + 1))
                );
    }

}

