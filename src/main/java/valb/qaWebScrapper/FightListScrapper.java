package valb.qaWebScrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class FightListScrapper {

    static String baseLink = "https://fightlist.info/?&page=1";
    static Document currentQuestDoc; // QA.Question page document
    static Document currentPageDoc; // Page document thats contains link to all the questions

    public static void start()
    {
        try {
            currentPageDoc = Jsoup.connect(baseLink).get();
        } catch (IOException e) {
            System.out.println("no link");
            throw new RuntimeException(e);
        }
        int i = 1;
        //processPage();
        Element linkAddOn = hasNextPage();
        processPage();
        while (linkAddOn != null) {
            System.out.println(i++);
            currentPageDoc = fetchPage(baseLink+linkAddOn.attr("href"));
            processPage();
            linkAddOn = hasNextPage();
        }
    }
    private static Document fetchPage(String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("no such link");
            throw new RuntimeException(e);
        }
    }
    private static Element hasNextPage(){
        Element nextButton = currentPageDoc.select("li:contains(next)").first();
        if (nextButton == null || nextButton.classNames().contains("disabled")) return null;
        return nextButton.select("a").first();
    }

    private static void processPage()
    {
        Elements container = currentPageDoc.getElementsByClass("quest");
        Elements questLinks = container.select("a[href]");
        for (Element q : questLinks){
            currentQuestDoc = fetchPage(baseLink + q.attr("href"));
            processQA();
        }
    }

    // Ignore all the assert null warning, we don't need to check in this case

    private static void processQA(){
        Element content = currentQuestDoc.select("div.content").first();
        assert content != null;
        Element words = content.select("div.words").first();
        String question = Objects.requireNonNull(content.select("h1").first()).text().substring(12);

        //fix quotation
        if (question.contains("\"")){
            question = question.replace(Character.toString('\"'),"");
        }
        int currentPoint = 0;
        boolean first = true;
        try (FileWriter fw = new FileWriter("src/main/resources/valb/game/questions/"+question+".txt")) {
            fw.write(question);
            fw.write("\n");
            assert words != null;
            for (Element answer : words.children()){
                if (answer.tagName().equals("br")) {
                    fw.write(" " + currentPoint);
                    fw.write("\n");
                    first = true;
                } else if (answer.className().equals("blank")) {
                    fw.write(" ");
                } else if (!answer.className().isEmpty()) {
                    fw.write(answer.text());
                    if (first){ // First letter of a new answer --> find the score
                        String point = answer.className();
                        currentPoint = switch (point) {
                            case "p1" -> 1;
                            case "p2" -> 2;
                            case "p3" -> 3;
                            default -> currentPoint;
                        };
                        first = false; // The next one is no longer first
                    }
                }

            }
        } catch (IOException e) {
            System.out.println("Ohnyo something went wrong with filewriter in valb.qaWebScrapper.FightListScrapper.java trying to write " + question);
            throw new RuntimeException(e);
        }

    }

}

