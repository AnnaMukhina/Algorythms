package lab1;

import java.io.*;

/**
 * @author anna_mukhina
 */
public class B {
    public static class Matrix {
        private int size;
        private String[][] matrix;

        public void fillMatrix(BufferedReader in) throws IOException {
            size = Integer.parseInt(in.readLine());

            matrix = new String[size][size];

            int i = 0;

            while(i < size) {
                matrix[i] = in.readLine().split(" ");
                i++;
            }
        }

        public boolean checkDiagonal() {
            boolean ok = false;
            for(int i = 0; i < size; i++) {
                if(matrix[i][i].equals("0")) {
                    ok = true;
                }
                else {
                    ok = false;
                    break;
                }
            }
            return ok;
        }

        public boolean checkSymmetry() {
            boolean ok = false;
            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    if(matrix[i][j].equals(matrix[j][i])) {
                        ok = true;
                    }
                    else {
                        return false;
                    }
                }
            }
            return ok;
        }

        public void printAnswer(FileWriter out) throws IOException {
            if(checkDiagonal() && checkSymmetry()) {
                out.write("YES");
            }
            else {
                out.write("NO");
            }
        }
    }

    public static void main(String args[]) throws IOException {
        Matrix matrix = new Matrix();

        BufferedReader in = new BufferedReader(new FileReader("input.txt"));

        matrix.fillMatrix(in);

        in.close();

        FileWriter out = new FileWriter("output.txt");

        matrix.printAnswer(out);

        out.close();
    }
}
