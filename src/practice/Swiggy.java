package practice;

import java.util.*;

public class Swiggy {

    /*
     * Complete the 'distinctMoves' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER n
     *  3. INTEGER x
     *  4. INTEGER y
     */
    static int[][][] dp = new int[1001][2501][2];

    static int X = 1000000007;

    public static int distinctMoves(String s, int n, int x, int y) {
        // Write your code here

        Arrays.fill(dp, -1);

        return util(s, n, x , y, 0 , false)+util(s, n, x, y, 0, true);

    }

    public static void main(String[] args) {
        distinctMoves("rrlrlr", 6, 1, 2);
    }

    public static int util(String moves, int n, int begin, int end,int index, boolean        flag){
        if(begin<0 || begin>n){
            return 0;
        }
        if(begin == end && index == moves.length()){
            return 1;
        }

        if(index>=moves.length()){
            return 0;
        }
        int y = flag? 1:0;
        if(dp[index][begin][y]!=-1){
            return dp[index][begin][y];
        }

        int ans = 0;

        if((moves.charAt(index) == 'l' && flag) || (moves.charAt(index)=='r' && flag)){
            int diff = moves.charAt(index) == 'l'? -1:1;

            ans = (util(moves, n, begin+diff, end, index+1, flag)%X + util(moves, n, begin                  +diff, end, index+1, !flag)%X)%X;
        }else{
            ans= util(moves, n, begin, end, index+1, flag);
        }

        return dp[index][begin][y] = ans;

    }

    public static List<String> processLogs(List<String> logs, int threshold){
        Map<String, Integer> m = new HashMap<>();

        for(int i=0 ; i<logs.size() ; i++){
            List<String> temp = Arrays.asList(logs.get(i).split(" "));
            if(temp.get(0).equals(temp.get(1))){
                if(m.containsKey(temp.get(0))){
                    m.put(temp.get(0), m.get(temp.get(0))+1);
                }else{
                    m.put(temp.get(0), 1);
                }
            }else{
                if(m.containsKey(temp.get(0))){
                    m.put(temp.get(0), m.get(temp.get(0))+1);
                }else{
                    m.put(temp.get(0), 1);
                }
                if(m.containsKey(temp.get(1))){
                    m.put(temp.get(1), m.get(temp.get(1))+1);
                }else{
                    m.put(temp.get(1), 1);
                }
            }
        }

        List<String> ans = new ArrayList<>();
        m.forEach((key,value)->{
            if(value>=threshold){
                ans.add(key);
            }
        });

        Collections.sort(ans);
        return ans;
    }

}