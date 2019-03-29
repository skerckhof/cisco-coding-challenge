package cisco.java.challenge.gnode;

import cisco.java.challenge.gnode.beans.GNode;
import cisco.java.challenge.gnode.beans.Path;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Utility class with methods to return data about a graph starting
 * for a graph node
 * */
public class GraphUtil {

    public static List<GNode> walkGraph(GNode graphNode) {
        return graphNode.flattened().collect(toList());
    }

    public static List walkGraphFlattened(GNode graphNode) {
        return graphNode.flattened().map(GNode::getName).collect(toList());
    }

    //******************************************************************************************************************

    public static List paths(GNode node) {

        return null;
    }

    public static List<GNode> preorder(GNode root)
    {
        List<GNode> list = new ArrayList<>();
        helper(root, list);
        //System.out.println(list);
        return list;
    }

    public static void helper(GNode root, List<GNode> list)
    {
        if(root==null)
            return;

        String nodeName = root.getName();

        System.out.println("==================================");
        System.out.println(nodeName + " : " + root);
        System.out.println("list  : " + list);
        System.out.println("Children : " + getChildNodes(root));

        list.add(root);

        if(root.hasChildren())
        {
            for(GNode child : root.getChildrenAsList())
            {
                String nodeName2 = child.getName();
                System.out.println("----------------------------------");
                System.out.println("child : " + child);
                System.out.println("list  : " + list);
                System.out.println("Children of child : " + getChildNodes(child));
                helper(child, list);
            }
        }
    }

    public static void postorder(GNode root) {

        System.out.println("*START* root: " + root + "\n");

        Stack<List<GNode>> stack = new Stack<>();

        GNode node = root;
        List<GNode> lst = null;

        while (true) {
            if(node != null) {
                lst = node.getChildrenAsList();
                System.out.println("lst: " + lst);
                node = null;

                if(lst != null && lst.size() > 0) {
                    //push the list in the stack (do not modify original tree structure).
                    stack.push(new ArrayList<>(lst));
                    //get first item from this list
                    node = stack.peek().get(0);
                }
            } else if (!stack.isEmpty()) {
                lst = stack.pop();
                node = lst.remove(0);	//shift left
                System.out.println("node : " + walkGraphFlattened(node));
                System.out.println("-------------------------");

                node = null;
                if(lst.size() > 0) {
                    stack.push(lst);	//push back remaining list into stack
                    node = stack.peek().get(0);	//prepare for next iteration
                }
            } else {
                break;
            }
        }

        System.out.println("\n*END* root: " + root);
    }

    public static void printGNodeNames(GNode root) {

        Queue<GNode> queue = new LinkedList<GNode>();
        queue.add(root);
        GNode current;
        while ((current = queue.poll()) != null) {
            boolean result = printGNodeNames(current, queue);
            System.out.println(result);
        }
    }

    private static boolean printGNodeNames(GNode n, Queue<GNode> queue) {

        System.out.println(n.getName());
        if (n.hasChildren()) {
            System.out.println("   " + n.getChildrenAsList());
            for (int i = 0; i < n.getChildren().length; i++) {
                System.out.println("   --- " + n.getChildren()[i].getName());
                queue.add(n.getChildren()[i]);
            }
            return true;
        } else {
            System.out.println("==> " + n.getName() + " *NO CHILDREN*");
            return false;
        }
    }

    private static List<GNode> preorderWalk(GNode node, List<GNode> nodes) {

        if (node == null) {
            return null;
        }
        nodes.add(node);
        for (GNode gnode : node.getChildren()) {
            nodes = preorderWalk(gnode, nodes);
        }
        return nodes;
    }

    public static List<GNode> levelorderWalk(GNode graph) {

        /*
						  A
				 /		  |		 \
				B		  C		  D
			   / \     /  |  \
			  E   F	  G	  H   I

        * */

        Stack<List<GNode>> stack = new Stack<>();

        List<GNode> levelOrderList = new ArrayList<>();
        List<GNode> lst = null;

        int i = 0;
        for (Iterator<GNode> gNodeIterator = graph.getInOrderViewIterator().iterator(); gNodeIterator.hasNext();) {
            GNode gNode = gNodeIterator.next();
            System.out.println("levelorderWalk: ["+i+"] ==> " + gNode);

            if(gNode.hasChildren()) {
                System.out.println(gNode.getChildrenAsList());
                System.out.println(gNode.getChildrenAsList().size());
                GNode[] children = gNode.getChildren();
                for (int j=0; j < children.length; j++) {
                    System.out.println("child["+j+"]=" + children[j]);
                }
            }

            levelOrderList.add(gNode);
            i++;
        }

        System.out.println("\n>>> " + levelOrderList);

        return levelOrderList;
    }

    public static Map<String,List> buildPathMap(GNode graph) {
        /*
						  A
				 /		  |		 \
				B		  C		  D
			   / \     /  |  \
			  E   F	  G	  H   I

        * */
        List<String> visitedNodeNames = new ArrayList<>();
        Map<String,List> pathMap = new HashMap<>();

        int i = 0;
        for (Iterator<GNode> gNodeIterator = graph.getInOrderViewIterator().iterator(); gNodeIterator.hasNext();) {

            GNode gNode = gNodeIterator.next();
            System.out.println("levelorderWalk: ["+i+"] ==> " + gNode);
            String parentNodeName = gNode.getName();

            List<String> pathNodeList = new ArrayList<>();
            if(gNode.hasChildren()) {
                System.out.println(gNode.getChildrenAsList());
                System.out.println(gNode.getChildrenAsList().size());
                List<GNode> children = gNode.getChildrenAsList();

                System.out.println("   ==> parentNodeName: " + parentNodeName);
                for (int j=0; j < children.size(); j++) {
                    GNode childNode = children.get(j);
                    System.out.println("child[" + j + "]=" + childNode);
                    pathNodeList.add(childNode.getName());
                }
            } else {
                pathNodeList.add(parentNodeName);
            }
            pathMap.put(parentNodeName, pathNodeList);
            System.out.println("   ==> pathMap: " + pathMap);

            visitedNodeNames.add(parentNodeName);
            System.out.println("   ==> visitedNodeNames: " + visitedNodeNames);
            i++;
        }

        return pathMap;
    }

    public static String constructPathString(Map<String,List> pathMap, String nodeName, String pathString) {

        List<String> pathList = pathMap.get(nodeName);

        if (pathList.size() > 1) {
            System.out.println(pathList);
            pathString.concat(nodeName);
            //pathString = constructPathString(pathMap, nodeName, pathString);
        }
        return pathString;
    }

    public static List<GNode> getChildNodes(GNode parentNode) {
        return parentNode.getChildrenAsList();
    }

    private static List<GNode> processChildNode(GNode childNode, Path nodePath, List<Path> graphPathList, List<GNode> childrenNodeList ) {

        boolean nodeHasChildren = false;
        String childNodeName = childNode.getName();
        //List<GNode> childrenNodeList = new ArrayList<>();
        Stream<GNode> gnodeChildrenStream = childNode.stream();
        Optional<GNode> graphChildNode = gnodeChildrenStream.findFirst();
        GNode nextNode = graphChildNode.get(); //get first node for this child node
        nodeHasChildren = nextNode.hasChildren();

        System.out.println("Node '" + nextNode.getName() + "': " + nextNode);
        System.out.println(walkGraphFlattened(nextNode));
        System.out.println("Node '" + nextNode.getName() + "' has children = " + nodeHasChildren);

        if (nodeHasChildren) {
            nodePath.addNode(nextNode);

        } else {
            nodePath.addNode(nextNode);
            graphPathList.add(nodePath);
            System.out.println("==> Found path '" + nodePath + "' for parent node '" + childNodeName + "'" );
            System.out.println("==> Current path list : " + graphPathList);
        }
        System.out.println("-------------------------");

        childrenNodeList = nextNode.getChildrenAsList();
        System.out.println("childrenNodeList : " + childrenNodeList);

        //process child nodes for this parent node
        childrenNodeList = processChildNode(childNode, nodePath, graphPathList, childrenNodeList);

        return childrenNodeList;
    }

    private static List<GNode> processChildNode3(GNode childNode) {

        return null;
    }
}
