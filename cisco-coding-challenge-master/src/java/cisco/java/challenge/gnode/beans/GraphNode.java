package cisco.java.challenge.gnode.beans;

import java.util.*;
import java.util.stream.Stream;

public class GraphNode implements GNode {

    private GNode left;
    private GNode right;
    private String name;
    private List<GNode> children = new ArrayList<>();
    private GNode[] childrenArray = new GraphNode[0];
    private Iterable<GNode> inOrderView;

    public GraphNode(String name) {
        super();
        this.name = name;
    }

    public GraphNode(String name, List<GNode> children) {
        super();
        this.name = name;
        this.children.addAll(children);
        Object[] temp;
        temp = children.toArray();
        childrenArray = new GraphNode[children.size()];
        for (int i=0; i<temp.length; i++) {
            childrenArray[i] = (GNode)temp[i];
        }
    }

    public GraphNode(String name, GNode... children) {
        this(name, Arrays.asList(children));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public GNode[] getChildren() {
        return childrenArray;
    }

    @Override
    public List<GNode> getChildrenAsList() {
        return children;
    }

    @Override
    public List<GNode> getInOrderViewIterator(GNode graph) {
        return null;
    }

    @Override
    public List<GNode> asList() {
        List<GNode> gNodeList = new ArrayList<>();
        gNodeList.add(this);
        for (int i=0; i<this.childrenArray.length; i++) {
            gNodeList.add(childrenArray[i]);
        }
        return gNodeList;
    }

    @Override
    public boolean hasChildren() {
        return  !children.isEmpty();
    }

    @Override
    public Stream<GNode> flattened() {
        return Stream.concat(
                Stream.of(this),
                children.stream().flatMap(GNode::flattened));
    }

    @Override
    public Stream<GNode> stream() {
        if (this.hasChildren()) {
            return Stream.of(this);
        } else {
            return this.getChildrenAsList().stream()
                    .map(child -> child.stream())
                    .reduce(Stream.of(this), (s1, s2) -> Stream.concat(s1, s2));
        }
    }

    @Override
    public int size() {
        return children.size();
    }

    @Override
    public String toString() {
        return "GNode{" +
                "'" + name + '\'' +
                ", " + children +
                '}';
    }
    
    //******************************************************************************************************************

    @Override
    public Iterable<GNode> getInOrderViewIterator() {

        inOrderView = new Iterable<GNode>() {
            @Override
            public Iterator<GNode> iterator() {
                return new InOrderIterator(GraphNode.this);
            }
        };
        return inOrderView;
    }

    static class InOrderIterator implements Iterator<GNode> {
        final Queue<GNode> queue = new LinkedList<GNode>();

        public InOrderIterator(GNode tree) {
            queue.add(tree);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public GNode next() {
            GNode node = queue.remove();
            queue.addAll(node.getChildrenAsList());
            return node;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
