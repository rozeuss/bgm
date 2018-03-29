import bgm.BGM;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import utils.MatrixUtils;

public class Main {

    public static final String INPUT = "\\input.txt";
    public static final String OUTPUT = "\\output.txt";
    public static final String PATH = System.getProperty("user.dir");
    public static List<String> inputData = null;

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get(PATH + INPUT))) {
            inputData = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] matrix = MatrixUtils.createMatrix(inputData);
        System.out.println(Arrays.deepToString(matrix));
        System.out.println(MatrixUtils.validateInput(matrix));

        if (MatrixUtils.validateInput(matrix)) {
            BGM bgm = new BGM(matrix.length);
            bgm.diagnose(matrix);
        } else {
            throw new IllegalArgumentException("Invalid input data.\nAllowed symbols: 0, 1, x, X");
        }

    }

}