package structure;

import java.util.ArrayList;
import java.util.List;

public class Predecessor {
    private List<Integer> startNodePredecessors = new ArrayList<>();
    private List<Integer> endNodePredecessors = new ArrayList<>();

    public List<Integer> getStartNodePredecessors() {
        return startNodePredecessors;
    }

    public void setStartNodePredecessors(List<Integer> startNodePredecessors) {
        this.startNodePredecessors = startNodePredecessors;
    }

    public List<Integer> getEndNodePredecessors() {
        return endNodePredecessors;
    }

    public void setEndNodePredecessors(List<Integer> endNodePredecessors) {
        this.endNodePredecessors = endNodePredecessors;
    }

    @Override
    public String toString() {
        return "Predecessor{" +
                "startNodePredecessors={" + startNodePredecessors +
                "}\t endNodePredecessors={" + endNodePredecessors +
                "}}";
    }

    private String listToString(List<Node> nodePredecessors) {
        StringBuilder result = new StringBuilder();
        for (Node nodePredecessor : nodePredecessors) {
            result.append(" ").append(nodePredecessor.getName());
        }
        return result + " ";
    }
}
