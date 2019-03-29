package cisco.java.challenge;

import cisco.java.challenge.mockdata.MockData;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestMockData {

    @Test
    public void testGetWordListFromWordsTxt() {

        try {
            System.out.println(MockData.getWordListAsStringFromFile("words.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetWordListFromAzoresTxt() {

        try {
            System.out.println(MockData.getWordListAsStringFromFile("azores.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetGraphData() {

        System.out.println(MockData.getGraphData());
    }
}
