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

    // TODO
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
                if (isValid(url)) {
                    linksOnWebsite.add(url);
                }
            }
        }
        return linksOnWebsite;
    }

    /**
     * We are interested only at 'article' url, all url like 'https://en.wikipedia.org/w/index.php?title=Special:CreateAccount&returnto=Octamerella' should not be take as a vertex
     */
    private static boolean isValid(String url) {
        return url.startsWith("https://en.wikipedia.org") &&
                !url.contains("Special:") &&
                !url.contains("action=edit") &&
                !url.contains("index.php?") &&
                !url.contains("Portal:");
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
