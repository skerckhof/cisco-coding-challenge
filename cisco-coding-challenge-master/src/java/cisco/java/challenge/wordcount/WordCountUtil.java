package cisco.java.challenge.wordcount;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class WordCountUtil {

    public static Map<String, Integer> countWords(String words) {

        Map<String, Integer> wordCount = Stream.of(words.split(" "))
                .map(word -> new AbstractMap.SimpleEntry<>(word, 1))
                .collect(toMap(e -> e.getKey(), e -> e.getValue(), (v1, v2) -> v1 + v2));

        return wordCount;
    }
}
