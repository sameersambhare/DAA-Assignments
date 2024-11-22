
import java.util.*;

public class Assignment3 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //number of offices
        System.out.println("Enter the number of offices:");
        int n = scan.nextInt();
        int Array[][] = new int[n][n];
        //accept the matrix
        System.out.println("Enter the distances bwteen offices:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Array[i][j] = scan.nextInt();
            }
        }
        //Apply floyd Warshal
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Array[i][j] = Math.min(Array[i][j], Array[i][k] + Array[k][j]);
                }
            }
        }
        //diaplay the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(Array[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
