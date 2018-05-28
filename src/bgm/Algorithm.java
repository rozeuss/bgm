package bgm;

public class Algorithm {
    private BGM bgm;

    public Algorithm(BGM bgm) {
        this.bgm = bgm;
    }

    //liczy stopień wejściowy węzła (liczbę poprzedników)
    private int countDegree(int[][] diagnosticStructure, int column) {
        int degree = 0;
        for (int i = 0; i < diagnosticStructure.length; i++) {
            degree += diagnosticStructure[i][column];
        }
        return degree;
    }

    //liczy liczbę następników węzła
    private int countSuccessors(int[][] diagnosticStructure, int row) {
        int successorsNb = 0;
        for (int i = 0; i < diagnosticStructure.length; i++) {
            successorsNb += diagnosticStructure[row][i];
        }
        return successorsNb;
    }

    //sprawdza czy liczba poprzedników e jest większa od m
    private int findNotOptimal(int[][] diagnosticStructure) {
        for (int i = 0; i < diagnosticStructure.length; i++) {
            if (countDegree(diagnosticStructure, i) > bgm.m) {
                return i;
            }
        }
        return -1;
    }

    public int[][] optimizeStructure() {
        int[][] structure = bgm.structure;
        if ((bgm.checkNecessaryCondition()) && (bgm.checkSufficientCondition())) {
            int column = findNotOptimal(structure);
            while (column != -1) {
                int tmpRow = -1;
                int tmpSuccessorsNb = 0;
                for (int j = 0; j < structure.length; j++)
                    if ((structure[j][column] == 1) && (countSuccessors(structure, j) > tmpSuccessorsNb)) {
                        tmpRow = j;
                        tmpSuccessorsNb = countSuccessors(structure, j);
                    }
                structure[tmpRow][column] = 0;
                column = findNotOptimal(structure);
            }
            if (!((bgm.checkNecessaryCondition()) && (bgm.checkSufficientCondition()))) {
                structure = bgm.structure;
            }
        } else {
            throw new IllegalStateException("Cannot optimize structure - failed conditions.");
        }
        return structure;
    }
}
