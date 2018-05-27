package structure;

import java.util.ArrayList;
import java.util.List;

public class PrecedingElements {
    private List<Integer> startNodePrecedingElements = new ArrayList<>();
    private List<Integer> endNodePrecedingElements = new ArrayList<>();

    public List<Integer> getStartNodePrecedingElements() {
        return startNodePrecedingElements;
    }

    public void setStartNodePrecedingElements(List<Integer> startNodePrecedingElements) {
        this.startNodePrecedingElements = startNodePrecedingElements;
    }

    public List<Integer> getEndNodePrecedingElements() {
        return endNodePrecedingElements;
    }

    public void setEndNodePrecedingElements(List<Integer> endNodePrecedingElements) {
        this.endNodePrecedingElements = endNodePrecedingElements;
    }

    @Override
    public String toString() {
        return "PrecedingElements{" +
                "start={" + startNodePrecedingElements +
                "} | end={" + endNodePrecedingElements +
                "}}";
    }
}
