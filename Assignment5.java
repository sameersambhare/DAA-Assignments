
import java.util.*;

class KnightTour {

    int N;

    KnightTour(int N) {
        this.N = N;
    }

    public void FindSolution(int x, int y) {
        int chess[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                chess[i][j] = -1;
            }
        }
        chess[x][y] = 0;
        int MovX[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int MovY[] = {1, 2, 2, 1, -1, -2, -2, -1};
        if (!Tour(x, y, 1, chess, MovX, MovY)) {
            System.out.println("Solution doesn't exits.");
        } else {
            System.out.println("Solution exits.");
            printSolution(chess);
        }
    }

    public boolean Tour(int x, int y, int move, int chess[][], int MovX[], int MovY[]) {
        int k;
        int Xnew, Ynew;
        if (move == N * N) {
            return true;
        }
        for (k = 0; k < 8; k++) {
            Xnew = x + MovX[k];
            Ynew = y + MovY[k];
            if (Safe(Xnew, Ynew, chess)) {
                chess[Xnew][Ynew] = move;
                if (Tour(Xnew, Ynew, move + 1, chess, MovX, MovY)) {
                    return true;
                } else {
                    chess[Xnew][Ynew] = -1;
                }
            }
        }
        return false;
    }

    public boolean Safe(int x, int y, int chess[][]) {
        return (x >= 0 && x < N && y >= 0 && y < N && chess[x][y] == -1);
    }

    public void printSolution(int chess[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}

public class Assignment5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of chess vertices: ");
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        KnightTour k = new KnightTour(n);
        k.FindSolution(x, y);
    }
}
