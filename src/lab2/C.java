package lab2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anna_mukhina
 */
public class C {
    public static class Graph {
        private static int dimensionOfV;
        private static int dimensionOfE;
        private static ArrayList<List<Integer>> adj;
        private static char[] colors;
        private static int[] vertexes;
        private static boolean bipartite;

        private Graph(int n, int m) {
            this.dimensionOfV = n;
            this.dimensionOfE = m;
            this.adj = new ArrayList<>(n);
            this.colors = new char[n];
            Arrays.fill(colors, 'w');
            this.vertexes = new int[n];
            Arrays.fill(vertexes, 0);
            this.bipartite = true;
        }

        private void fillList(BufferedReader in) throws IOException {
            for (int i = 0; i < dimensionOfV; i++) {
                adj.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < dimensionOfE; i++) {
                String[] edge = in.readLine().split(" ");

                int from = Integer.parseInt(edge[0]) - 1;

                int to = Integer.parseInt(edge[1]) - 1;

                if(from == to) {
                    bipartite = false;
                }

                List<Integer> element = adj.get(from);

                element.add(to);

                adj.set(from, element);

                List<Integer> element1 = adj.get(to);

                element1.add(from);

                adj.set(to, element1);
            }
        }

        private void dfs(int vertex) {
            colors[vertex] = 'g';

            for (int v : adj.get(vertex)) {
                if (colors[v] == 'w') {
                    if(vertexes[vertex] == 1) {
                        vertexes[v] = 2;
                    }
                    else {
                        vertexes[v] = 1;
                    }
                    dfs(v);
                }
                if (colors[v] == 'g') {
                    if (vertexes[v] == vertexes[vertex]) {
                        bipartite = false;
                    }
                }
            }
            colors[vertex] = 'b';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("bipartite.in"));

        String[] input = in.readLine().split(" ");

        Graph graph = new Graph(Integer.parseInt(input[0]), Integer.parseInt(input[1]));

        graph.fillList(in);

        in.close();

        FileWriter out = new FileWriter("bipartite.out");

        if(!graph.bipartite) {
            out.write("NO");
        }
        else {
            //graph.vertexes[0] = 1;

            for (int i = 0; i < graph.dimensionOfV; i++) {
                graph.vertexes[i] = 1;
                graph.dfs(i);
            }

            if (graph.bipartite) {
                out.write("YES");
            } else {
                out.write("NO");
            }
        }
        out.close();
    }
}
