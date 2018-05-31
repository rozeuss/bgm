package bgm;

public class Algorithm {
    private BGM bgm;

    public Algorithm(BGM bgm) {
        this.bgm = bgm;
    }

    public int[][] optimizeStructure() {
        if (bgm.checkNecessaryCondition() && bgm.checkSufficientCondition()) {
            int columnToOptimize = findColumnToOptimize();
            while (columnToOptimize != -1) {
                int row = 0;
                int tmpOutDegree = 0;
                for (int i = 0; i < bgm.structure.length; i++) {
                    if ((bgm.structure[i][columnToOptimize] == 1) && (getOutDegree(i) > tmpOutDegree)) {
                        row = i;
                        tmpOutDegree = getOutDegree(i);
                    }
                }
                bgm.structure[row][columnToOptimize] = 0;
                columnToOptimize = findColumnToOptimize();
            }
        } else {
            throw new IllegalStateException("Cannot optimize structure - failed conditions.");
        }
        boolean hasFulfilledConditions = bgm.checkNecessaryCondition() && bgm.checkSufficientCondition();
        System.out.println("Czy wyznaczona struktura jest " + bgm.m + "-diagnozowalna? " + hasFulfilledConditions);
        return bgm.structure;
    }

    private int findColumnToOptimize() {
        for (int i = 0; i < bgm.structure.length; i++) {
            if (getInDegree(i) > bgm.m) {
                return i;
            }
        }
        return -1;
    }

    private int getOutDegree(int row) {
        int outDegree = 0;
        for (int i = 0; i < bgm.structure.length; i++) {
            outDegree += bgm.structure[row][i];
        }
        return outDegree;
    }

    private int getInDegree(int column) {
        int inDegree = 0;
        for (int i = 0; i < bgm.structure.length; i++) {
            inDegree += bgm.structure[i][column];
        }
        return inDegree;
    }
}
