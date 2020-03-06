import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RectangleCounter {

    public int countRectangles(String[] inputGrid) {
        int numberOfRectangles = 0;
        Set<int[]> edges;

        for (int row = 0; row < inputGrid.length; row++) {
            edges = findEdges(inputGrid[row]);

            for (int[] edge : edges) {
                numberOfRectangles += findRectangles(inputGrid, edge, row + 1);
            }
        }
        return numberOfRectangles;
    }

    private Set<int[]> findEdges(String string) {
        Set<int[]> edges = new HashSet<>();
        List<Integer> vertices = new ArrayList<>();

        int point;
        int startingIndex = 0;

        while (startingIndex < string.length()) {
            point = string.indexOf("+", startingIndex);

            if (point != -1) {
                vertices.add(point);
            } else {
                break;
            }

            startingIndex = point + 1;
        }

        if (vertices.size() < 2) {
            return edges;
        }

        int vertexA;
        int vertexB;
        int indexB;

        for (int indexA = 0; indexA < vertices.size() - 1; indexA++) {
            indexB = indexA + 1;
            vertexA = vertices.get(indexA);

            while (indexB < vertices.size()) {
                vertexB = vertices.get(indexB);
                edges.add(new int[]{vertexA, vertexB});
                indexB++;
            }
        }
        return edges;
    }

    private int findRectangles(String[] inputGrid, int[] edge, int startRow) {
        int numberOfRectangles = 0;
        char vertex = '+';
        char side = '|';

        for (int row = startRow; row < inputGrid.length; row++) {

            if (inputGrid[row].charAt(edge[0]) == vertex && inputGrid[row].charAt(edge[1]) == vertex) {
                numberOfRectangles++;
            } else if (
                    (inputGrid[row].charAt(edge[0]) == vertex || inputGrid[row].charAt(edge[0]) == side)
                            && (inputGrid[row].charAt(edge[1]) == vertex || inputGrid[row].charAt(edge[1]) == side)
            ) {
                continue;
            } else {
                break;
            }
        }
        return numberOfRectangles;
    }

}
