package practice;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class anagram {

    static int sherlockAndAnagrams(String s) {
        AtomicInteger ans= new AtomicInteger();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0 ; i<s.length() ; i++){
            for(int j=i+1 ; j<=s.length() ; j++){
                String key = s.substring(i,j);
                key = sortString(key);
                if(map.containsKey(key)){
                    int t = map.get(key);
                    map.put(key,t+1);
                }else{
                    map.put(key,1);
                }
            }
        }
        map.forEach((key, value) -> {
            ans.addAndGet(value*(value-1)/2);
        });

        return ans.get();

    }

    public static String sortString(String inputString)
    {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    public static void reactance(){
        Scanner s = new Scanner(System.in);

        int n= s.nextInt();
        int m= s.nextInt();
        int[][] arr = new int[n][m];
        for(int i=0 ; i<n ; i++){
            for(int j=0 ; j<m ; j++){
                arr[i][j] = s.nextInt();
            }
        }

        List<Integer> l = new ArrayList<>();
        for(int i=0 ; i<n ; i++){
            for(int j=0 ; j<m ; j++){
                l.add(arr[i][j]);
            }
        }
        int ans=0;
        for(int i=0;  i<l.size() ; i++){
            int temp = l.get(i);
            for(int j=i+1 ; j<l.size() ; j++){
                temp+=l.get(j);
                System.out.println(temp);
                if(temp==0){
                    ans = Math.max(ans, j-i+1);
                }
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> l = finalPosition("MMLM");

        for(int i=0 ;  i<l.size() ; i++){
            System.out.println(l.get(i).getKey()+"     "+ l.get(i).getValue());
        }
    }

    private static List<Pair<Integer, Integer>> finalPosition(String s)
    {

        // Initialize starting
        // point for practice.robot as
        // (0, 0) and starting
        // direction as N North
        char[] path = s.toCharArray();
        int x = 0, y = 0;
        int dir = 0;
        List<Pair<Integer, Integer>> moves = new ArrayList<>();

        // Traverse the path given for practice.robot
        for (int i=0; i < path.length; i++)
        {
            char move = path[i];

            if (move == 'R')
                dir = (dir + 1)%4;
            else if (move == 'L')
                dir = (4 + dir - 1) % 4;

            else
            {
                if (dir == 0)
                    y++;
                else if (dir == 1)
                    x++;
                else if (dir == 2)
                    y--;
                else // dir == 3
                    x--;

                if(Math.abs(x) == Math.abs(y)){
                    moves.clear();
                }
                moves.add(new Pair<>(x, y));
            }
        }

        return moves;
    }
//10 9
//        2 1
//        3 1
//        4 3
//        5 2
//        6 1
//        7 2
//        8 6
//        9 8
//        10 8

    static int quickestWayUp(int[][] ladders, int[][] snakes) {
        int[] board = new int[101];
        Map<Integer, Integer> l_map = new HashMap<>();
        Map<Integer, Integer> s_map = new HashMap<>();

        for(int i=0 ; i<ladders.length ; i++){
            l_map.put(ladders[i][0], ladders[i][1]);
        }

        for(int i=0 ; i<snakes.length ; i++){
            s_map.put(snakes[i][0], snakes[i][1]);
        }

        Arrays.fill(board,Integer.MAX_VALUE);
        board[1]=0;
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(1,0));
        while(!q.isEmpty()){
            Pair<Integer, Integer> current = q.peek();
            q.remove();
            int pos = current.getKey();
            // if(pos>=100){
            //     break;
            // }
            for(int j=1 ; j<=6 ; j++){
                int distance = current.getValue();
                if(pos+j<=100 && board[pos+j]>distance+1){

                    if(l_map.containsKey(pos+j) &&
                            board[l_map.get(pos+j)]>distance+1){
                        q.add(new Pair<>(l_map.get(pos+j), distance+1));
                        board[l_map.get(pos+j)] = Math.min(distance+1, board[l_map.get(pos+j)]);
                    }else if(s_map.containsKey(pos+j) &&
                            board[s_map.get(pos+j)]>distance+1){
                        q.add(new Pair<>(s_map.get(pos+j), distance+1));
                        board[s_map.get(pos+j)] = Math.min(distance+1, board[s_map.get(pos+j)]);
                    }else {
                        q.add(new Pair<>(pos+j, distance+1));
                        board[pos+j] = Math.min(distance+1, board[pos+j]);

                    }
                }
            }
        }

        if(board[100]==Integer.MAX_VALUE){
            return -1;
        }

        return board[100];
    }

    private static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to){
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i=0 ; i<t_edges ; i++){
            if(!adj.containsKey(t_from.get(i))){
                List<Integer> temp = new ArrayList<>();
                temp.add(t_to.get(i));
                adj.put(t_from.get(i),temp);
            } else{
                adj.get(t_from.get(i)).add(t_to.get(i));
            }

            if(!adj.containsKey(t_to.get(i))){
                List<Integer> temp = new ArrayList<>();
                temp.add(t_from.get(i));
                adj.put(t_to.get(i),temp);
            } else{
                adj.get(t_to.get(i)).add(t_from.get(i));
            }
        }

        int ans = 0;

        for(int i=0 ; i<t_edges ; i++){
            int l = t_from.get(i);
            int r = t_to.get(i);
            int nodes_l = calculateNodes(adj, l,r, t_nodes);
            int nodes_r = calculateNodes(adj, r, l, t_nodes);

            if(nodes_l%2==0 && nodes_r%2==0){
                ans++;
            }
        }


        return ans;

    }

    private static int calculateNodes(Map<Integer, List<Integer>> adj, Integer target, Integer s, Integer t_nodes){
        Queue<Integer> q= new LinkedList<>();
        q.add(target);
        int nodes = 0;
        int[] visited= new int[t_nodes+1];
        Arrays.fill(visited, 0);
        while(!q.isEmpty()){
            int current = q.peek();
            visited[current]=1;
            q.remove();
            nodes++;
            List<Integer> l = adj.get(current);
            if(l!=null && !l.isEmpty()){
                for(int i=0 ; i<l.size() ; i++){
                    if(l.get(i)!=s && visited[l.get(i)]==0){
                        q.add(l.get(i));
                    }
                }
            }
        }
        return nodes;
    }
}
