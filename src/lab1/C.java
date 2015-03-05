package lab1;

import java.io.*;
import java.util.Arrays;

/**
 * @author anna_mukhina
 */
public class C {
    public static  class Graph {
        private int dimensionOfV;
        private int dimensionOfE;
        private int[][] matrix;

        public boolean checkParallel(BufferedReader in) throws IOException {
            String[] input = in.readLine().split(" ");

            dimensionOfV = Integer.parseInt(input[0]);

            dimensionOfE = Integer.parseInt(input[1]);

            matrix = new int[dimensionOfV][dimensionOfV];

            for(int[] line : matrix) {
                Arrays.fill(line, 0);
            }

            for(int i = 0; i < dimensionOfE; i++) {
                String[] edge = in.readLine().split(" ");

                int value1 = matrix[Integer.parseInt(edge[0]) - 1][Integer.parseInt(edge[1]) - 1];

                int value2 = matrix[Integer.parseInt(edge[1]) - 1][Integer.parseInt(edge[0]) - 1];

                if(value1 == 0 && value2 == 0) {
                    matrix[Integer.parseInt(edge[0]) - 1][Integer.parseInt(edge[1]) - 1] = matrix[Integer.parseInt(edge[1]) - 1][Integer.parseInt(edge[0]) - 1] = 1;
                }
                else {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String args[]) throws IOException {
        Graph graph = new Graph();

        BufferedReader in = new BufferedReader(new FileReader("input.txt"));

        FileWriter out = new FileWriter("output.txt");

        if(graph.checkParallel(in)) {
            out.write("YES");
        }
        else {
            out.write("NO");
        }

        in.close();

        out.close();
    }
}
