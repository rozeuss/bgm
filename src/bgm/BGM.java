package bgm;

import structure.Edge;
import structure.Graph;
import structure.Node;
import structure.Predecessor;

import java.util.*;

public class BGM {

    private long cardinalityOfE; //liczebność zbioru
    private String[][] structure;
    private Graph graph;
    private long m;
    private long min;
    private Set<Edge> edges;

    public BGM(String[][] structure) {
        this.cardinalityOfE = structure.length;
        this.structure = structure;
    }

    public void diagnose() {
        createGraph();
        System.out.println("\nWARUNEK KONIECZNY");
        boolean necessaryCondition = checkNecessaryCondition();
        System.out.println("CZY SPELNIONY? " + necessaryCondition + " (MOZE TRZEBA SPRAWDZIC CZY M > 0)\n");

        System.out.println("WARUNEK WYSTARCZAJACY");
        boolean sufficientCondition = checkSufficientCondition();
        System.out.println("CZY SPELNIONY? " + sufficientCondition);
        if (sufficientCondition) {
            System.out.println("BGM: Struktura jest " + min + "-diagnozowalna");
        } else {
            System.out.println("BGM: Struktura nie jest " + m + "-diagnozowalna");
        }
    }

    private boolean checkNecessaryCondition() {
        System.out.println("m<=" + nDiagnosable());
        System.out.println("Struktura jest co najwyzej " + m + "-diagnozowalna");
        return checkSecondCondition();
    }

    private long nDiagnosable() {
        m = cardinalityOfE - 2;
        return cardinalityOfE - 2;
    }

    private boolean checkSecondCondition() {
        Map<Integer, Integer> nodeTestingElements = countTestingElements();
        min = Collections.min(nodeTestingElements.values());
        System.out.println("Liczba elementów opiniujących element powinna być co najmniej równa " + m);
        System.out.println(nodeTestingElements);
        System.out.println("Struktura jest co najwyżej " + Collections.min(nodeTestingElements.values()) + "-diagnozowalna");
        return nodeTestingElements.values().stream().allMatch(integer -> integer >= m);
//        return Collections.min(nodeTestingElements.values());
    }


    private boolean checkSufficientCondition() {
        Map<Edge, Predecessor> edgePredecessorMap = countSubsets();
        Collection<Predecessor> values = edgePredecessorMap.values();
        for (Predecessor value : values) {
            List<Integer> startNodePredecessors = value.getStartNodePredecessors();
            List<Integer> endNodePredecessors = value.getEndNodePredecessors();
            if (startNodePredecessors.containsAll(endNodePredecessors)
                    && endNodePredecessors.containsAll(startNodePredecessors)) {
                return false;
            }
        }
        return true;
    }

    private Map<Edge, Predecessor> countSubsets() {
        Map<Edge, Predecessor> edgePredecessorMap = new HashMap<>();
        for (Edge uniqueEdge : edges) {
            Predecessor predecessor = new Predecessor();
            Node startNode = uniqueEdge.getStart();
            Node endNode = uniqueEdge.getEnd();
            for (Edge edge : edges) {
                if (startNode.equals(edge.getEnd())) {
                    predecessor.getStartNodePredecessors().add(edge.getStart().getName());
                }
                if (endNode.equals(edge.getEnd())) {
                    predecessor.getEndNodePredecessors().add(edge.getStart().getName());
                }
            }
            edgePredecessorMap.put(uniqueEdge, predecessor);
        }
        edgePredecessorMap.entrySet().forEach(System.out::println);
        return edgePredecessorMap;
    }


    private void createGraph() {
        graph = new Graph();
        graph.setNodes(createNodes());
        for (int i = 0; i < structure.length; i++) {
            for (int j = 0; j < structure.length; j++) {
                if ("1".equals(structure[i][j])) {
                    Edge edge = new Edge(graph.getNodes().get(i), graph.getNodes().get(j));
                    graph.getNodes().get(i).getConnections().add(edge);
                }
            }
        }
        List<Node> nodes = graph.getNodes();
        edges = new HashSet<>();
        nodes.forEach(node -> edges.addAll(node.getConnections()));
//        edges.forEach(System.out::println);
//        nodes.forEach(System.out::println);
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
                if ("1".equals(structure[i][j])) {
                    testingElements++;
                }
            }
            map.put(i + 1, testingElements);
        }
        return map;
    }
}
