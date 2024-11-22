import java.util.*;
public class Assignment601 {
    static int INF=Integer.MAX_VALUE;
    static class Node{
        boolean[] available;
        int[] assigned;
        int cost;
        int level;
        Node(boolean[] available,int[] assigned,int cost,int level){
            this.assigned=assigned.clone();
            this.available=available.clone();
            this.cost=cost;
            this.level=level;
        }
    }
    public static int findMinCost(int[][] costMatrix,int N){
        PriorityQueue<Node>pq=new PriorityQueue<>(Comparator.comparingInt(a->a.cost));
        int[] assigned=new int[N];
        boolean[] available=new boolean[N];
        Arrays.fill(available,true);
        Node root=new Node(available,assigned,0,0);
        pq.add(root);
        Node node;
        while(!pq.isEmpty()){
            node=pq.poll();
            int level=node.level;
            if(level==N){
                return node.cost;
            }
            for(int i=0;i<N;i++){
                if(node.available[i]){
                    node.assigned[level]=i;
                    node.available[i]=false;
    
                    int newcost=node.cost+costMatrix[level][i];
                    pq.add(new Node(node.available,node.assigned,newcost,level+1));
                    node.available[i]=true;
                }
            }
        }
        return INF;
    }
    public static void main(String[] args){
        System.out.println("Enter the N: ");
        Scanner scan=new Scanner(System.in);
        int n=scan.nextInt();
        int[][] costMatrix=new int[n][n];
        System.out.println("Enter the cost matrix: ");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                costMatrix[i][j]=scan.nextInt();
            }
            System.out.print("");
        }
        System.out.println("MinCost: "+findMinCost(costMatrix,n));
    }
}
