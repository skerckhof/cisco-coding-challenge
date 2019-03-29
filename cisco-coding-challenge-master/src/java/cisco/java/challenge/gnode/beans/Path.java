package cisco.java.challenge.gnode.beans;

import java.util.ArrayList;
import java.util.List;

public class Path {

    List<String> path = new ArrayList<>();

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<String> addNode(GNode node) {
        String treeNodeName = node.getName();
        this.path.add(treeNodeName);
        return getPath();
    }

    @Override
    public String toString() {
        return path.toString().replace("[","(").replace("]",")");
    }
}
