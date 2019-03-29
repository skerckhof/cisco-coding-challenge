package cisco.java.challenge.mockdata;

import cisco.java.challenge.gnode.beans.GNode;
import cisco.java.challenge.gnode.beans.GraphNode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockData {

    public static String getWordListAsStringFromFile(String fileName) throws IOException, URISyntaxException {

        Stream<String> lines = Files.lines(
                Paths.get(ClassLoader.getSystemClassLoader().getResource(fileName).toURI()));

        //TODO: research how to use Collectiors.joining to possibly tighten up code
        List<String> lines2 = lines
                .filter(line -> line.length() > 0)
                .collect(Collectors.toList());

        return cleanTextContent(String.join("", lines2));
    }

    /***
     * Creates a set of mock Graph data for 9 nodes (A-I) with A being the root node
     * and E, F, G, H, and I being leaf nodes or unit testing purposes.
     */
    public static GNode getGraphData() {

        /* the graph structure we are creating nodes for:
         A
         |
         |-- B
         |   |-- E
         |   |-- F
         |
         |-- C
         |   |-- G
         |   |-- H
         |   |-- I
         |
         |-- D
        */

        //create leaf nodes D, E, F, G, H, and I
        GNode d = new GraphNode("D");
        GNode e = new GraphNode("E");
        GNode f = new GraphNode("F");
        GNode g = new GraphNode("G");
        GNode h = new GraphNode("H");
        GNode i = new GraphNode("I");

        //create nodes B, and C
        GNode[] bChildren = {e, f};
        GNode b = new GraphNode("B", bChildren);
        GNode[] cChildren = {g, h, i};
        GNode c = new GraphNode("C", cChildren);

        //create root node A
        GNode[] aChildren = {b, c, d};
        GNode a = new GraphNode("A", aChildren);

        //returns the following graph representation of A
        /*
        GraphNode{
        nodeName='A', nodeList=[GraphNode{
            nodeName='B', nodeList=[GraphNode{
                nodeName='E', nodeList=[]}, GraphNode{
                nodeName='F', nodeList=[]}]}, GraphNode{
            nodeName='C', nodeList=[GraphNode{
                nodeName='G', nodeList=[]}, GraphNode{
                nodeName='H', nodeList=[]}, GraphNode{
                nodeName='I', nodeList=[]}]}, GraphNode{
            nodeName='D', nodeList=[]}]}
        * */

        return a;
    }

    private static String cleanTextContent(String text)
    {
        String[] abbrevs = {"km ", "mi ", "C ", "F ", "N ", "S ", "E ", "W ", "NE ", "NW ", "SE ", "SW ", "III ", "II ", "I ", "IV ", "MD ", "th ", "nd "};

        //replace specific chars with a space
        text = text.replaceAll("[.]", " ");
        text = text.replaceAll("[-]", " ");
        text = text.replaceAll("[;]", " ");
        text = text.replaceAll("[\"]", " ");
        text = text.replaceAll("[']", " ");
        text = text.replaceAll("[/]", " ");
        text = text.replaceAll("[,]", " ");
        text = text.replaceAll("[(]", " ");
        text = text.replaceAll("[)]", " ");

        //removes numbers
        text = text.replaceAll("[0-9]", " ");

        //strips off all non-ASCII characters
        text = text.replaceAll("[^\\x00-\\x7F]", " ");

        //erases all the ASCII control characters
        text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", " ");

        //removes non-printable characters from Unicode
        text = text.replaceAll("\\p{C}", " ");

        //removes the common distance, temp, direction, and name suffix abbreviations
        for (int i=0; i<abbrevs.length; i++) {
            text = text.replaceAll(abbrevs[i]," ");
        }

        //removes excess whitespace
        text = text.replaceAll("\\s+", " ");

        //normalize all words to lowercase
        text = text.toLowerCase();

        return text.trim();
    }
}
