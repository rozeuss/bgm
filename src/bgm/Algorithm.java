package bgm;

public class Algorithm {
    private BGM bgm;

    public Algorithm(BGM bgm) {
        this.bgm = bgm;
    }

    public int[][] optimizeStructure() {
        int[][] structure = bgm.structure;
        if ((bgm.checkNecessaryCondition()) && (bgm.checkSufficientCondition())) {
            int column = findNotOptimal(structure);
            while (column != -1) {
                int tmpRow = -1;
                int tmpSuccessorsNb = 0;
                for (int j = 0; j < structure.length; j++) {
                    if ((structure[j][column] == 1) && (getOutDegree(structure, j) > tmpSuccessorsNb)) {
                        tmpRow = j;
                        tmpSuccessorsNb = getOutDegree(structure, j);
                    }
                }
                structure[tmpRow][column] = 0;
                column = findNotOptimal(structure);
            }
//            if (!((bgm.checkNecessaryCondition()) && (bgm.checkSufficientCondition()))) {
//                structure = bgm.structure;
//            }
        } else {
            throw new IllegalStateException("Cannot optimize structure - failed conditions.");
        }
        return structure;
    }

    private int findNotOptimal(int[][] structure) {
        for (int i = 0; i < structure.length; i++) {
            if (getInDegree(structure, i) > bgm.m) {
                return i;
            }
        }
        return -1;
    }

    private int getOutDegree(int[][] structure, int row) {
        int outDegree = 0;
        for (int i = 0; i < structure.length; i++) {
            outDegree += structure[row][i];
        }
        return outDegree;
    }

    private int getInDegree(int[][] structure, int column) {
        int inDegree = 0;
        for (int i = 0; i < structure.length; i++) {
            inDegree += structure[i][column];
        }
        return inDegree;
    }
}
