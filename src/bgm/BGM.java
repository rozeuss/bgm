package bgm;

import structure.*;

import java.util.*;

public class BGM {

    private long cardinalityOfE;
    int[][] structure;
    private Graph graph;
    int m;
    private List<Pair> pairs;

    public BGM(int[][] structure, int m) {
        this.m = m;
        this.cardinalityOfE = structure.length;
        this.structure = structure;
        getPairs();
    }

    public boolean diagnose() {
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
        return necessaryCondition && sufficientCondition;
    }

    public boolean checkNecessaryCondition() {
        System.out.println("\nWARUNEK KONIECZNY");
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

    public boolean checkSufficientCondition() {
        System.out.println("WARUNEK WYSTARCZAJACY");
        createGraph();
        Map<Pair, PrecedingElements> pairPrecedingElementsMap = getPrecedingElementsSubsets();
        Collection<PrecedingElements> values = pairPrecedingElementsMap.values();
        for (PrecedingElements value : values) {
            List<Integer> startNodePrecedingElements = value.getStartNodePrecedingElements();
            List<Integer> endNodePrecedingElements = value.getEndNodePrecedingElements();
            if (startNodePrecedingElements.containsAll(endNodePrecedingElements)
                    && endNodePrecedingElements.containsAll(startNodePrecedingElements)) {
                System.out.println("Sufficient: " + false);
                return false;
            }
        }
        System.out.println("Sufficient: " + true);
        return true;
    }

    private Map<Pair, PrecedingElements> getPrecedingElementsSubsets() {
        Map<Pair, PrecedingElements> pairPrecedingElementsMap = new HashMap<>();
        for (Pair pair : pairs) {
            List<Integer> firstNodePrecedingElements = new ArrayList<>();
            List<Integer> secondNodePrecedingElements = new ArrayList<>();
            for (Edge edges : graph.getEdges()) {
                Node start = edges.getStart();
                Node end = edges.getEnd();
                if (pair.getFirst() == end.getName()) {
                    firstNodePrecedingElements.add(start.getName());
                }
                if (pair.getSecond() == end.getName()) {
                    secondNodePrecedingElements.add(start.getName());
                }
            }
            pairPrecedingElementsMap.put(pair,
                    new PrecedingElements(firstNodePrecedingElements, secondNodePrecedingElements));
        }
        pairPrecedingElementsMap.entrySet().forEach(System.out::println);
        return pairPrecedingElementsMap;
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
        graph.getNodes().forEach(node -> graph.getEdges().addAll(node.getConnections()));
    }

    private void getPairs() {
        pairs = new ArrayList<>();
        for (int i = 0; i < structure.length; i++)
            for (int j = i + 1; j < structure.length; j++) {
                pairs.add(new Pair(i + 1, j + 1));
            }
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
                if (1 == structure[j][i]) {
                    testingElements++;
                }
            }
            map.put(i + 1, testingElements);
        }
        return map;
    }

    public int[][] getStructure() {
        return structure;
    }

    public void setStructure(int[][] structure) {
        this.structure = structure;
    }
}
