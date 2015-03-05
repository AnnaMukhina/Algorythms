package lab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author anna_mukhina
 */
public class A {
    public static class Graph {
        private int dimensionOfV;
        private int dimensionOfE;
        private int[][] matrix;

        public void fillMatrix(BufferedReader in) throws IOException {
            String[] input = in.readLine().split(" ");

            dimensionOfV = Integer.parseInt(input[0]);

            dimensionOfE = Integer.parseInt(input[1]);

            matrix = new int[dimensionOfV][dimensionOfV];

            for(int[] line : matrix) {
                Arrays.fill(line, 0);
            }

            int i = 0;

            while(i < dimensionOfE) {
                String[] vertexes = in.readLine().split(" ");
                matrix[Integer.parseInt(vertexes[0]) - 1][Integer.parseInt(vertexes[1]) - 1] = 1;
                i++;
            }
        }

        public void printMatrix(FileWriter out) throws IOException {
            for(int i = 0; i < dimensionOfV; i++) {
                for(int j = 0; j < dimensionOfV; j++) {
                    out.write(matrix[i][j] + " ");
                }
                out.write("\n");
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));

        Graph graph = new Graph();

        graph.fillMatrix(in);

        in.close();

        FileWriter out = new FileWriter("output.txt");

        graph.printMatrix(out);

        out.close();
    }
}
