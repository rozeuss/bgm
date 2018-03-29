package bgm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import structure.Edge;
import structure.Graph;
import structure.Node;

public class BGM {

    private long cardinalityOfE; //liczebność zbioru
    private long inDegreeNode; //stopień wejściowy
    private String[][] structure;
    private Node e; //wezeł
    private Graph graph = new Graph();

    public BGM(long cardinalityOfE) {
        this.cardinalityOfE = cardinalityOfE;
    }

    public void diagnose(String[][] matrix) {
        this.structure = matrix;
        checkNecessaryCondition();
        createGraph();
        checkSufficientCondition();
    }

    private void checkNecessaryCondition() {
        long first = checkFirstCondition();
        System.err.println("First condition: " + first);
        long second = checkSecondCondition();
        System.err.println("Second condition: " + second);

    }

    private long checkFirstCondition() {
        long m;
        m = cardinalityOfE - 2;
        return m;
    }

    private long checkSecondCondition() {
        Map<Integer, Integer> nodeTestingElements = countTestingElements();
        return Collections.min(nodeTestingElements.values());
    }

    private void checkSufficientCondition() {
        List<Node> startNodePredecessors = new ArrayList<>();
        List<Node> endNodePredecessors = new ArrayList<>();

        List<Node> nodes = graph.getNodes();
        Set<Edge> uniqueEdges = new HashSet<>();
        nodes.forEach(node -> uniqueEdges.addAll(node.getConnections()));
        uniqueEdges.forEach(System.out::println);
    }

    private Map<Integer, Integer> countTestingElements() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < structure.length; i++) {
            int testingElements = 0;
            for (int j = 0; j < structure.length; ++j) {
                if ("1".equals(structure[i][j])) {
                    testingElements++;
                }
            }
            map.put(i + 1, testingElements);
        }
        return map;
    }

    private List<Node> createNodes() {
        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= structure.length; i++) {
            nodes.add(new Node(i, new ArrayList<>()));
        }
        return nodes;
    }

    private Graph createGraph() {
        graph.setNodes(createNodes());
        for (int i = 0; i < structure.length; i++) {
            for (int j = 0; j < structure.length; j++) {
                if ("1".equals(structure[i][j])) {
                    Edge edge = new Edge(graph.getNodes().get(i), graph.getNodes().get(j));
                    graph.getNodes().get(i).getConnections().add(edge);
                }
            }
        }
        return graph;
    }

}
