import java.util.*;

public class LCS {

    public static ArrayList LCS_Lenght(String InputX[], String InputY[]) {
        int m = InputX.length;
        int n = InputY.length;
        int c[][] = new int[m + 1][n + 1];
        String b[][] = new String[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            c[i][0] = 0;
        }
        for (int j = 1; j <= n; j++) {
            c[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (InputX[i - 1].equals(InputY[j - 1])) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = "\\";
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i][j] = "^";
                } else {
                    c[i][j] = c[i][j - 1];
                    b[i][j] = "<-";
                }
            }
        }

        System.out.println("=============================================================================");
        System.out.println("Tabel C : ");

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                System.out.print(c[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("=============================================================================");
        System.out.println("Tabel B (Arah) : ");
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("\t");
                } else if (b[i][j] == null) {
                    System.out.print("0\t");
                } else {
                    System.out.print(b[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("=============================================================================");
        ArrayList<String[][]> arrs = new ArrayList<>();
        arrs.add(b);
        return arrs;
    }
    public static void Print_LCS(String[][] b, String[] X, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (b[i][j] == "\\") {
            Print_LCS(b, X, i - 1, j - 1);
            System.out.print(X[i - 1]+" ");
        } else if (b[i][j] == "^") {
            Print_LCS(b, X, i - 1, j);
        } else {
            Print_LCS(b, X, i, j - 1);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Masukan X : ");
        System.out.println("Contoh Masukan : A B C D E");
        String Input_X = in.nextLine();
        System.out.print("Masukan Y : ");
        System.out.println("Contoh Masukan : A B C D E");
        String Input_Y = in.nextLine();

        String X[] = Input_X.split(" ");
        String Y[] = Input_Y.split(" ");
        ArrayList<String [][]> arr2 = LCS_Lenght(X, Y);
        System.out.print("Longest Common Subsequence dari " +Input_X+" adalah : ");
        Print_LCS(arr2.get(0), X, X.length, Y.length);

    }    
}