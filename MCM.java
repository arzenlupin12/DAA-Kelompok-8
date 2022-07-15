import java.util.*;
import java.util.stream.Stream;

public class MCM {

    static char Alphabet = 'A';

    public static ArrayList MatrixChainOrder(Integer p[]) {

        int n = p.length - 1;
        Integer m[][] = new Integer[n + 1][n + 1];
        Integer s[][] = new Integer[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        ArrayList<Integer[][]> arrMCM = new ArrayList<>();
        arrMCM.add(m);
        arrMCM.add(s);
        return arrMCM;
    }

    public static void PrintOptimalParent(Integer s[][], int i, int j) {
        if (i == j) {
            System.out.print(Alphabet++);
        } else {
            System.out.print("(");
            PrintOptimalParent(s, i, s[i][j]);
            PrintOptimalParent(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Set<Integer> inputUser = new LinkedHashSet<>();
        boolean userInput = false;
        while (!userInput) {
            try {
                System.out.print("Masukan Jumlah Matrix yang Akan Dihitung : ");
                String temp = in.nextLine();
                int jumlahMatrix = Integer.parseInt(temp);
                String[] input = new String[jumlahMatrix];

                System.out.print("Matrix ke " + (1) + " (Contoh Input : 30x35) :  ");
                String str = in.nextLine();
                String[] temp2 = str.split("x", 2);
                str = str.replaceAll("[a-zA-Z]", " ");
                input[0] = str;

                for (int i = 1; i < jumlahMatrix; i++) {
                    System.out.print("Matrix ke " + (i + 1) + " (Contoh Input : 30x35) :  ");
                    str = in.nextLine();
                    String[] temp3 = str.split("x", 2);
                    System.out.println(Arrays.toString(temp2));
                    System.out.println(Arrays.toString(temp3));
                    if (!temp2[1].equalsIgnoreCase(temp3[0])) {
                        System.out.println("Mohon masukkan matriks dengan dimensi yang benar");
                        i--;
                    } else {
                        temp2 = str.split("x ", 2);
                        str = str.replaceAll("[a-zA-Z]", " ");
                        input[i] = str;
                    }
                }
                for (int i = 0; i < input.length; i++) {
                    String tmp = input[i];
                    String[] spliited = tmp.split(" ");
                    inputUser.add(Integer.parseInt(spliited[0]));
                    inputUser.add(Integer.parseInt(spliited[1]));
                }
                Integer[] values = new Integer[inputUser.size()];
                inputUser.toArray(values);
                ArrayList<Integer[][]> arr2 = MatrixChainOrder(values);
                Integer a[][] = arr2.get(0);
                Integer b[][] = arr2.get(1);
                System.out.println("=============================================================");
                System.out.println("\nMatrix Jumlah Perkalian : ");
                for (int i = 1; i < a.length; i++) {
                    for (int j = 1; j < a[i].length; j++) {
                        if (a[i][j] == null) {
                            System.out.print(0 + "\t");
                        } else {
                            System.out.print(a[i][j] + "\t");
                        }
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println("=============================================================");
                System.out.println("\nTabel K : ");
                for (int i = 1; i < b.length; i++) {
                    for (int j = 1; j < b[i].length; j++) {
                        if (b[i][j] == null) {
                            System.out.print(0 + "\t");
                        } else {
                            System.out.print(b[i][j] + "\t");
                        }
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println("=============================================================");
                System.out.print("Optimal Parent : ");
                PrintOptimalParent(b, 1, b.length - 1);
                System.out.println();
                System.out.println("=============================================================");
                in.close();
                userInput = true;

            } catch (Exception e) {
                System.out.println("Input Salah, Harap Masukan Kembali");
            }
        }
    }
}