package structure;

import java.util.List;

public class Node {
    private Integer name;
    private List<Edge> connections;

    public Node(Integer name, List<Edge> connections) {
        this.name = name;
        this.connections = connections;
    }

    public Node(Integer name) {
        this.name = name;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public List<Edge> getConnections() {
        return connections;
    }

    public void setConnections(List<Edge> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name=" + name +
                ", connections=" + connections +
                '}';
    }


}