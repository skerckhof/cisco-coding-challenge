package cisco.java.challenge;

import cisco.java.challenge.gnode.GraphUtil;
import cisco.java.challenge.gnode.beans.GNode;
import cisco.java.challenge.mockdata.MockData;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestGraphUtil {

    @Test
    public void testWalkGraph() {
        GNode graph = MockData.getGraphData();
        System.out.println(GraphUtil.walkGraph(graph));
    }

    @Test
    public void testWalkGraphFlattened() {
        GNode graph = MockData.getGraphData();
        System.out.println(GraphUtil.walkGraphFlattened(graph));
    }

    @Test
    public void testPaths() {       //TODO: finish

        GNode graph = MockData.getGraphData();
        System.out.println(GraphUtil.paths(graph));
    }

    //******************************************************************************************************************

    @Test
    public void testConstructPathString() {
        String pathString = "";
        GNode graph = MockData.getGraphData();
        Map<String,List> pathMap = GraphUtil.buildPathMap(graph);
        String rootNodeName = graph.getName();

        for (String node : pathMap.keySet()) {
            pathString = GraphUtil.constructPathString(pathMap, rootNodeName, pathString);
        }

        pathString = GraphUtil.constructPathString(pathMap, rootNodeName, pathString);
        System.out.println("\n***************\n" + pathString);
    }

    @Test
    public void testGNodeInOrderView() {
        GNode graph = MockData.getGraphData();

        for (Iterator<GNode> gNodeIterator = graph.getInOrderViewIterator().iterator(); gNodeIterator.hasNext();) {
            GNode gNode = gNodeIterator.next();
            System.out.println(gNode);
        }
    }

    @Test
    public void testLevelorderWalk() {
        GNode graph = MockData.getGraphData();
        List<GNode>  levelOrderList = GraphUtil.levelorderWalk(graph);
        System.out.println("\n**************************************\ntestLevelorderWalk:\n" + levelOrderList);
    }

    @Test
    public void testBuildPathMap() {
        GNode graph = MockData.getGraphData();
        Map<String,List> pathMap = GraphUtil.buildPathMap(graph);
        System.out.println("\n**************************************\npathMap:\n" + pathMap);
    }

    @Test
    public void testCreatePathString() {
        GNode graph = MockData.getGraphData();
        Map<String,List> pathMap = GraphUtil.buildPathMap(graph);
    }

    @Test
    public void testPreorder() {

        GNode graph = MockData.getGraphData();

        System.out.println(graph);
        System.out.println(GraphUtil.walkGraphFlattened(graph));
        System.out.println("*****************************************");

        System.out.println(GraphUtil.preorder(graph));
    }

    @Test
    public void testPostOrder() {

        GNode graph = MockData.getGraphData();

        System.out.println(graph);
        System.out.println(GraphUtil.walkGraphFlattened(graph));
        System.out.println("*****************************************");

        GraphUtil.postorder(graph);
    }

    @Test
    public void testPrintGNodeNames() {

        GNode graph = MockData.getGraphData();

        System.out.println(graph);
        System.out.println(GraphUtil.walkGraphFlattened(graph));
        System.out.println("*****************************************");

        GraphUtil.printGNodeNames(graph);
    }
}
