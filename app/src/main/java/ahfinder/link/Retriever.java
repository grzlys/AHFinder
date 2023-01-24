package ahfinder.link;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Retriever {

    public List<String> retrieveLinksFrom(String websiteUrl) throws IOException {
        Document doc = Jsoup.connect(websiteUrl).get();
        Elements links = doc.select("a[href]");

        List<String> result = new ArrayList<>();
        for (Element link : links) {
            result.add(link.attr("abs:href"));
        }
        return result;
    }

}
