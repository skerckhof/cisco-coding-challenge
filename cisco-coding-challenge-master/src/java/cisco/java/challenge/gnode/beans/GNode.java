package cisco.java.challenge.gnode.beans;

import java.util.List;
import java.util.stream.Stream;

public interface GNode {

    String getName();
    GNode[] getChildren();

    public Stream<GNode> stream();
    public Stream<GNode> flattened();
    public List<GNode> asList();
    public int size();
    public List<GNode> getChildrenAsList();
    public boolean hasChildren();
    public Iterable<GNode> getInOrderViewIterator();
    public List<GNode> getInOrderViewIterator(GNode graph);
}
