package lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author anna_mukhina
 */
public class E {
    public static class Graph {
        private static int dimensionOfV;
        private static int dimensionOfE;
        private static ArrayList<List<Integer>> adj;
        private static char[] colors;
        private static Stack<Integer> stack;
        private static int[] answer;
        private static boolean isHamiltonian;

        private Graph(int n, int m) {
            this.dimensionOfV = n;
            this.dimensionOfE = m;
            this.adj = new ArrayList<>(n);
            this.colors = new char[n];
            Arrays.fill(colors, 'w');
            this.stack = new Stack<>();
            this.answer = new int[n];
            this.isHamiltonian = true;
        }

        private void fillLists(BufferedReader in) throws IOException {
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

        private static void checkHamiltonian(int current, int next) {
            int ok = -1;
            for(int v : adj.get(current)) {
                if(v == next) {
                    ok = 1;
                }
                else {
                    ok = 0;
                }
            }
            System.out.println(ok);
            if(ok==1) {
                isHamiltonian = true;
            }
            else {
                isHamiltonian = false;
            }
        }

        private static void giveAnswer() {
            if(topologicalSort()) {
                int beg = 0;
                int end = answer.length-1;

                while(beg < end) {
                    if(isHamiltonian) {
                        checkHamiltonian(answer[beg], answer[beg+1]);
                        beg++;
                    }
                    else {
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("resources/hamiltonian.in"));

        String[] input = in.readLine().split(" ");

        Graph graph = new Graph(Integer.parseInt(input[0]), Integer.parseInt(input[1]));

        graph.fillLists(in);

        in.close();

        /*if(graph.topologicalSort()) {
            int beg=0;
            int end=graph.answer.length-1;
            while(beg < end) {
                if(graph.isHamiltonian) {
                    graph.checkHamiltonian(graph.answer[beg], graph.answer[beg + 1]);
                    beg++;
                }
                else{
                    break;
                }
            }
        }*/
        graph.giveAnswer();

        FileWriter out = new FileWriter("resources/hamiltonian.out");



        if(graph.isHamiltonian) {
            out.write("YES");
        }
        else {
            out.write("NO");
        }

        out.close();
    }
}

