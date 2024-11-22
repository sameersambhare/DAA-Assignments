
import java.util.*;

public class Assignment4 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of vertices: ");
        int vertices = scan.nextInt();
        int[][] graph = new int[vertices][vertices];
        //Enter the graph
        System.out.println("Enter the graph: ");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = scan.nextInt();
            }
        }
        //Display the graph
        System.out.println("Enter the graph: ");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println("");
        }
        Djikstras dj = new Djikstras(vertices, graph);
        dj.findAnswer(0);
    }
}

class Djikstras {

    int v;
    int[][] graph;

    public Djikstras(int v, int[][] graph) {
        this.v = v;
        this.graph = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                this.graph[i][j] = graph[i][j];
            }
        }
    }

    public int minDistance(int[] dist, boolean[] visited) {
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < v; i++) {
            if (!visited[i] && dist[i] < minValue) {
                minValue = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void  printSolution(int[] dist) {
        System.out.println("Vertices  Distance from source");
        for (int i = 0; i < v; i++) {
            System.out.println(i + "\t" + dist[i]);
        }
    }

    public void findAnswer(int src) {
        int[] dist = new int[v];
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        dist[src] = 0;

        for (int count = 0; count < v - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int j = 0; j < v; j++) {
                if (!visited[j] && graph[u][j] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][j] < dist[j]) {
                    dist[j] = dist[u] + graph[u][j];
                }
            }
        }
        printSolution(dist);
    }
}
