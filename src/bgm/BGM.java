package bgm;

import structure.Edge;
import structure.Graph;
import structure.Node;
import structure.PrecedingElements;

import java.util.*;

public class BGM {

    private long cardinalityOfE; //liczebność zbioru
    private int[][] structure;
    private Graph graph;
    private long m;
    private Set<Edge> edges;
    private List<Node> nodes;

    public BGM(int[][] structure) {
        this.cardinalityOfE = structure.length;
        this.structure = structure;
    }

    public void diagnose(int m) {
        this.m = m;
        System.out.println("\nWARUNEK KONIECZNY");
        boolean necessaryCondition = checkNecessaryCondition();
        System.out.println("CZY SPELNIONY? " + necessaryCondition + "\n");
        createGraph();
        System.out.println("WARUNEK WYSTARCZAJACY");
        boolean sufficientCondition = checkSufficientCondition();
        System.out.println("CZY SPELNIONY? " + sufficientCondition + "\n");
        if (necessaryCondition && sufficientCondition) {
            System.out.println("BGM: Struktura jest " + this.m + "-diagnozowalna.");
        } else {
            System.out.println("BGM: Struktura nie jest " + this.m + "-diagnozowalna.");
        }
    }

    private boolean checkNecessaryCondition() {
        boolean firstCondition = checkFirstCondition();
        System.out.println("Neccesarry 1st: " + firstCondition);
        boolean secondCondition = checkSecondCondition();
        System.out.println("Neccesarry 2nd: " + secondCondition);
        return firstCondition && secondCondition;
    }

    private boolean checkFirstCondition() {
        System.out.println(cardinalityOfE + " >= " + " 2 + " + m);
        return cardinalityOfE >= 2 + m;
    }

    private boolean checkSecondCondition() {
        System.out.println("Liczba elementów opiniujących element powinna być większa lub równa "
                + m + " aby struktura była " + m + "-diagnozowalna.");
        Map<Integer, Integer> nodeTestingElements = countTestingElements();
        System.out.println(nodeTestingElements);
        return nodeTestingElements.values().stream().allMatch(integer -> integer >= m);
    }

    private boolean checkSufficientCondition() {
        Map<Edge, PrecedingElements> edgePrecedingElementMap = getPrecedingElementsSubsets();
        Collection<PrecedingElements> values = edgePrecedingElementMap.values();
        for (PrecedingElements value : values) {
            List<Integer> startNodePrecedingElements = value.getStartNodePrecedingElements();
            List<Integer> endNodePrecedingElements = value.getEndNodePrecedingElements();
            if (startNodePrecedingElements.containsAll(endNodePrecedingElements)
                    && endNodePrecedingElements.containsAll(startNodePrecedingElements)) {
                return false;
            }
        }
        return true;
    }

    private Map<Edge, PrecedingElements> getPrecedingElementsSubsets() {
        Map<Edge, PrecedingElements> edgePrecedingElementMap = new HashMap<>();
        for (Edge uniqueEdge : edges) {
            PrecedingElements precedingElements = new PrecedingElements();
            Node startNode = uniqueEdge.getStart();
            Node endNode = uniqueEdge.getEnd();
            for (Edge edge : edges) {
                if (startNode.equals(edge.getEnd())) {
                    precedingElements.getStartNodePrecedingElements().add(edge.getStart().getName());
                }
                if (endNode.equals(edge.getEnd())) {
                    precedingElements.getEndNodePrecedingElements().add(edge.getStart().getName());
                }
            }
            edgePrecedingElementMap.put(uniqueEdge, precedingElements);
        }
        edgePrecedingElementMap.entrySet().forEach(System.out::println);
        return edgePrecedingElementMap;
    }

    private void createGraph() {
        graph = new Graph();
        graph.setNodes(createNodes());
        for (int i = 0; i < structure.length; i++) {
            for (int j = 0; j < structure.length; j++) {
                if (1 == structure[i][j]) {
                    Edge edge = new Edge(graph.getNodes().get(i), graph.getNodes().get(j));
                    graph.getNodes().get(i).getConnections().add(edge);
                }
            }
        }
        nodes = graph.getNodes();
        edges = new HashSet<>();
        nodes.forEach(node -> edges.addAll(node.getConnections()));
    }

    private List<Node> createNodes() {
        List<Node> nodes = new ArrayList<>();
        for (int i = 1; i <= structure.length; i++) {
            nodes.add(new Node(i, new ArrayList<>()));
        }
        return nodes;
    }

    private Map<Integer, Integer> countTestingElements() {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < structure.length; i++) {
            int testingElements = 0;
            for (int j = 0; j < structure.length; ++j) {
                if (1 == structure[i][j]) {
                    testingElements++;
                }
            }
            map.put(i + 1, testingElements);
        }
        return map;
    }
}
