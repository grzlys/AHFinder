package ahfinder.graph;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static ahfinder.App.GLOSTER_METEOR;
import static org.assertj.core.api.Assertions.assertThat;

class VertexTest {

    @Test
    void shouldAttachNeighboursToGivenVertex() {
        // given
        Vertex root = new Vertex(GLOSTER_METEOR, new LinkedList<>(), Visited.WHITE, null, 0);

        // when
        root.attachNeighbours();

        // then
        assertThat(root.getNeighbours()).size().isNotZero();
        root.getNeighbours().forEach(
                vertex -> assertThat(vertex.getDistanceFromOrigin()).isEqualTo(root.getDistanceFromOrigin() + 1)
        );

    }
}