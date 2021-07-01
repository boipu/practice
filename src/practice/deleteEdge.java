package practice;

import java.util.*;

public class deleteEdge {
    static long m = 1000000007;
    static long[] ans = new long[100001];
    public long deleteEdge(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {
        int V = A.size();
        ArrayList<ArrayList<Integer> > adj
                = new ArrayList<ArrayList<Integer> >(V);

        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<Integer>());


        for(int i = 0 ; i < B.size() ; i++){
            int a = B.get(i).get(0);
            int b = B.get(i).get(1);

            addEdge(adj, a-1, b-1);
        }
        long max = 0;

        boolean visited[] = new boolean[V];

        long zero = dfs(0, adj, visited, A);

        for(int j = 0 ; j < B.size() ; j++){
            int a = B.get(j).get(0) - 1;
            int b = B.get(j).get(1) - 1;

            boolean lol[] = new boolean[V];

            max = Math.max(max, (((zero - ans[b])*ans[b])%m + m)%m);
        }

        return max;

    }
    static void addEdge(ArrayList<ArrayList<Integer> > adj,
                        int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static long dfs(int v, ArrayList<ArrayList<Integer> > adj, boolean[] visited, ArrayList<Integer> A){
        visited[v] = true;

        Iterator<Integer> it  = adj.get(v).listIterator();

        long sum = A.get(v);

        while(it.hasNext()){
            int n = it.next();

            if(!visited[n]){
                sum = (sum + dfs(n, adj, visited, A))%m;
            }
        }

        ans[v]  = sum;
        return sum;
    }
}
