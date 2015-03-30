package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author anna_mukhina
 */
public class D {
    public static class Graph {
        private static int dimensionOfV;
        private static int dimensionOfE;
        private static ArrayList<List<Integer>> adj;
        private static ArrayList<List<Integer>> adjInvert;
        private static boolean[] visited;
        private static Stack<Integer> order;
        private static Stack<Integer> components;
        private static int[] answer;
        private static int componentsCount;

        private Graph(int n, int m) {
            this.dimensionOfV = n;
            this.dimensionOfE = m;
            this.adj = new ArrayList<>(n);
            this.adjInvert = new ArrayList<>(n);
            this.visited = new boolean[n];
            Arrays.fill(visited, false);
            this.order = new Stack();
            this.components = new Stack<>();
            this.answer = new int[n];
        }

        private void fillLists(BufferedReader in) throws IOException {
            for (int i = 0; i < dimensionOfV; i++) {
                adj.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < dimensionOfV; i++) {
                adjInvert.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < dimensionOfE; i++) {
                String[] edge = in.readLine().split(" ");

                int from = Integer.parseInt(edge[0]) - 1;

                int to = Integer.parseInt(edge[1]) - 1;

                List<Integer> element = adj.get(from);

                element.add(to);

                adj.set(from, element);

                List<Integer> element1 = adjInvert.get(to);

                element1.add(from);

                adjInvert.set(to, element1);
            }
        }

        private void dfs1(int current) {
            visited[current] = true;
            for (int v : adj.get(current)) {
                if (!visited[v]) {
                    dfs1(v);
                }
            }
            order.push(current);
        }

        private void dfs2(int current) {
            visited[current] = true;
            components.push(current);
            for (int v : adjInvert.get(current)) {
                if (!visited[v]) {
                    dfs2(v);
                }
            }
        }

        private void formAnswer() {
            for (int i = 0; i < dimensionOfV; i++) {
                if (!visited[i]) {
                    dfs1(i);
                }
            }

            Arrays.fill(visited, false);

            componentsCount = 0;

            for (int i = 0; i < dimensionOfV; i++) {
                int v = order.get(dimensionOfV - i - 1);

                if (!visited[v]) {
                    dfs2(v);
                    componentsCount++;

                    for (Integer c : components) {
                        answer[c] = componentsCount;
                    }
                    components.clear();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("cond.in"));

        String[] input = in.readLine().split(" ");

        Graph graph = new Graph(Integer.parseInt(input[0]), Integer.parseInt(input[1]));

        graph.fillLists(in);

        in.close();

        graph.formAnswer();

        FileWriter out = new FileWriter("cond.out");

        out.write(graph.componentsCount + "\n");

        for(Integer i : graph.answer) {
            out.write(i + " ");
        }

        out.close();
    }
}
