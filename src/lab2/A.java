package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author anna_mukhina
 */
public class A {
    public static class Graph {
        private static int dimensionOfV;
        private static int dimensionOfE;
        private static ArrayList<List<Integer>> adj;
        private static char[] colors;
        private static Stack<Integer> stack;
        private static int[] answer;

        private Graph(int n, int m) {
            this.dimensionOfV = n;
            this.dimensionOfE = m;
            this.adj = new ArrayList<>(n);
            this.colors = new char[n];
            Arrays.fill(colors, 'w');
            stack = new Stack<>();
            answer = new int[n];
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

        private static boolean topologicalSort() {
            boolean cicle;

            for (int i = 0; i < dimensionOfV; i++) {
                cicle = dfs(i);
                if (cicle) {
                    return false;
                }
            }
            for (int i = 0; i < dimensionOfV; i++) {
                answer[i] = stack.pop();
            }
            return true;
        }

        private static boolean dfs(int vertex) {
            if (colors[vertex] == 'g') {
                return true;
            }
            if (colors[vertex] == 'b') {
                return false;
            }
            colors[vertex] = 'g';

            for (int v : adj.get(vertex)) {
                if (dfs(v)) {
                    return true;
                }
            }
            stack.push(vertex);
            colors[vertex] = 'b';
            return false;
        }

        private static void printAnswer(FileWriter out) throws IOException {
            if(topologicalSort()) {
                for(int vertex : answer) {
                    out.write((vertex+1) + " ");
                }
            }
            else {
                out.write("-1");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("resources/topsort.in"));

        String[] input = in.readLine().split(" ");

        Graph graph = new Graph(Integer.parseInt(input[0]), Integer.parseInt(input[1]));

        graph.fillList(in);

        in.close();

        FileWriter out = new FileWriter("resources/topsort.out");

        graph.printAnswer(out);

        out.close();
    }
}
