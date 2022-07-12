import java.util.*;
import java.util.stream.Stream;

public class MCM{

    static char Alphabet = 'A';

    public static ArrayList MatrixChainOrder(int p[]){

        int n = p.length-1;
        int m[][] = new int[n+1][n+1];
        int s[][] = new int[n+1][n+1];
        
        for (int i = 1; i <= n;  i++) {
            m[i][i]=0;
        }

        for(int l = 2 ; l <= n ; l++){
            for(int i = 1 ; i <= n - l + 1 ; i++){
                int j = i + l -1;
                m[i][j] = Integer.MAX_VALUE;
                for(int k = i ; k <= j-1;k++){
                    int q = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
                    if (q < m[i][j]){
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        ArrayList <int[][]> arrMCM = new ArrayList<>();
        arrMCM.add(m);
        arrMCM.add(s);
        return arrMCM;
    }
    public static void main(String[] args) {
        Scanner br = new Scanner(System.in);
            System.out.println("Masukan Array p[] (Misal User Input = 5 3 8 9 19)");
            String input = br.nextLine();
            String[] splited = input.split(" ");
            int[] values = Stream.of(splited).mapToInt(Integer::parseInt).toArray();
            ArrayList <int[][]> arr2 = MatrixChainOrder(values);
            int[][] a = arr2.get(0);
            int[][] b = arr2.get(1);
            System.out.println("\nMatrix Jumlah Perkalian : ");
            for(int i = 1 ; i < a.length ; i++){
                for(int j = 1; j < a[i].length ; j++){
                    System.out.print(a[i][j]+"\t");
                }
                System.out.println();
            }
    }
}