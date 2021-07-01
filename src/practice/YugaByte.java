package practice;

import java.util.*;

class Yugabyte {
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();
        Map<Integer, ArrayList<Integer>> adj = new HashMap<>();

        for(int i=1 ; i<=n ; i++){
            adj.put(i, new ArrayList<Integer>());
        }
        for (int i=0 ;i<x ; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            ArrayList<Integer> temp = adj.get(b);
            temp.add(a);
            adj.put(b, temp);
        }

        System.out.println(solve(adj, k, n));
    }

    public static int solve(Map<Integer, ArrayList<Integer>> adj, int k, int n){
        boolean[] visited = new boolean[n+1];

        Arrays.fill(visited, false);
        Queue<Integer> dfs = new LinkedList<>();
        for(int i=1;  i<=n ; i++){
            if((adj.get(i).size())==0){
                dfs.add(i);
            }
        }
        int ans=0;
        while(!dfs.isEmpty()){
            for(int i=0 ; i<k ; i++){
                if(!dfs.isEmpty()){
                    int current = dfs.peek();
                    dfs.remove();
                    visited[current]=true;
                    ArrayList<Integer> temp = adj.get(current);
                    for(int j= 0 ; j<temp.size() ; j++){
                        ArrayList<Integer> d = adj.get(temp.get(j));
                        boolean allVisted = true;
                        for(int l = 0 ; l<d.size() ; l++){
                            allVisted = allVisted && visited[d.get(l)];
                        }

                        if(!visited[temp.get(j)] && allVisted){
                            dfs.add(temp.get(j));
                        }
                    }
                }
            }
            ans++;

            if(dfs.isEmpty()){
                for(int i=1  ;i<=n  ;i++){
                    boolean allVisted = true;
                    if(!visited[i]){
                        ArrayList<Integer> d = adj.get(i);
                        for(int l = 0 ; l<d.size() ; l++){
                            allVisted = allVisted && visited[d.get(l)];
                        }
                        if(allVisted){
                            dfs.add(i);
                        }
                    }
                }
            }
        }
        return ans;
    }
}