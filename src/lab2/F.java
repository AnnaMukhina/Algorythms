package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author anna_mukhina
 */
public class F {
    public static class Graph {
        private static int dimensionOfV;
        private static int dimensionOfE;
        private static ArrayList<List<Integer>> adj;
        private static int start;

        private Graph(int n, int m, int s) {
            this.dimensionOfV = n;
            this.dimensionOfE = m;
            this.start = s;
            this.adj = new ArrayList<>(n);
        }

        private void fillList(BufferedReader in) throws IOException {
            for (int i = 0; i < dimensionOfV; i++) {
                adj.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < dimensionOfE; i++) {
                String[] edge = in.readLine().split(" ");

                int from = Integer.parseInt(edge[0]) - 1;

                int to = Integer.parseInt(edge[1]) - 1;

                List<Integer> element = adj.get(from);

                element.add(to);

                adj.set(from, element);
            }
        }

        private static boolean first(int vertex) {
            for (int v : adj.get(vertex)) {
                if (second(v)) {
                    return true;
                }
            }
            return false;
        }

        private static boolean second(int vertex) {
            for (int v : adj.get(vertex)) {
                if (!first(v)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("game.in"));

        String[] input = in.readLine().split(" ");

        Graph graph = new Graph(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])-1);

        graph.fillList(in);

        in.close();

        boolean res = graph.first(graph.start);

        FileWriter out = new FileWriter("game.out");

        if(res) {
            out.write("First player wins");
        }
        else {
            out.write("Second player wins");
        }
        out.close();
    }
}
