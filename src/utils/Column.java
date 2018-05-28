package utils;

import java.util.Arrays;
import java.util.List;

public class Column {

    int columnNumber;
    int[] data;
    List<Integer> positions;

    public Column(int columnNumber, int[] data, List<Integer> positions) {
        this.columnNumber = columnNumber;
        this.data = data;
        this.positions = positions;
    }

    public Column(int columnNumber, int[] data) {
        this.columnNumber = columnNumber;
        this.data = data;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Column{" +
            "columnNumber=" + columnNumber +
            ", data=" + Arrays.toString(data) +
            ", positions=" + positions +
            '}';
    }
}
