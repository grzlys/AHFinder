package ahfinder;

import ahfinder.graph.Graph;
import ahfinder.graph.Vertex;
import ahfinder.link.Retriever;

import java.io.*;
import java.util.List;

public class App {

    // TODO add logger, because sout doesnt looks so professional
    //      add gitignore

    public static final String GLOSTER_METEOR = "https://en.wikipedia.org/wiki/Gloster_Meteor";
    public static final String PRZECIESZYN = "https://en.wikipedia.org/wiki/Przecieszyn";

    public static void main(String[] args) {

//        testLinks();

        try {
            long startTime = System.currentTimeMillis();
            Graph graph = Graph.fillGraphWithDepthFrom(PRZECIESZYN, 1);
            long endTime = System.currentTimeMillis();
            long duration = (endTime - startTime);
            System.out.println("Grapch size (Vertices): " + graph.getVertexes().size() + " collected in " + duration + " milis");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToFile(String message) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("output_afc_2.txt", true), true);
        out.write(message + '\n');
        out.close();
    }

    private static void testLinks() {
        List<String> linksOnWebsite = Retriever.retrieveLinksFrom("https://en.wikipedia.org/wiki/Gloster_Meteor");
        for (String link : linksOnWebsite) {
            System.out.println(link);
        }
        System.out.println("end.");
    }
}
