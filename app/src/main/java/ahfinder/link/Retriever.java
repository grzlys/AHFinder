package ahfinder.link;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Retriever {

    // TODO 1. make url pattern allowing only urls starts with https://en.wikipedia.org
    //      2. do some refactor because if (doc != null) doesnt looks profesional...
    //      3. Create unit test for positive and negative scenario (Http 200/400)

    public static List<String> retrieveLinksFrom(String websiteUrl) {
        Connection connect = Jsoup.connect(websiteUrl);
        List<String> linksOnWebsite = new LinkedList<>();

        Document doc = getDocument(websiteUrl, connect);

        if (doc != null) {
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String url = link.attr("abs:href");
                if (url.startsWith("https://en.wikipedia.org")) {
                    linksOnWebsite.add(url);
                }
            }
        }
        return linksOnWebsite;
    }

    private static Document getDocument(String websiteUrl, Connection connect) {
        Document doc = null;
        try {
            doc = connect.get();
        } catch (IOException e) {
            System.out.println("Cannot connect to url: " + websiteUrl + " due to " + e.getMessage());
        }
        return doc;
    }

}
