package cisco.java.challenge;

import cisco.java.challenge.mockdata.MockData;
import cisco.java.challenge.wordcount.WordCountUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class TestWordCountUtil {

    @Test
    public void testCountWordsFromWordsTxt() {

        try {
            String wordListString = MockData.getWordListAsStringFromFile("words.txt");
            Map<String, Integer> wordCountMap = WordCountUtil.countWords(wordListString);
            //System.out.println(wordCountMap);
            prettyPrint(wordCountMap);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCountWordsFromAzoresTxt() {

        try {
            String wordListString = MockData.getWordListAsStringFromFile("azores.txt");
            System.out.println(wordListString);
            Map<String, Integer> wordCountMap = WordCountUtil.countWords(wordListString);
            //System.out.println(wordCountMap);
            prettyPrint(wordCountMap);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void prettyPrint(Map<String, Integer> map) {

        for (String key : map.keySet()) {
            System.out.println(key + "=" + map.get(key));
        }
    }
}
