import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dunzo {
    public static int countComponents(int n, List<Integer> graphFrom, List<Integer> graphTo, List<Integer> weights) {
        int ans = 0;

        Map<Integer, Map<Integer, List<Integer>>> m = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            Map<Integer, List<Integer>> tempMap = new HashMap<>();
            m.put(i, tempMap);
        }

        Set<Integer> s = new HashSet<>();
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < graphFrom.size(); i++) {
            Map<Integer, List<Integer>> temp = m.get(graphFrom.get(i));
            if (temp.containsKey(weights.get(i))) {
                temp.get(weights.get(i)).add(graphTo.get(i));
            } else {
                temp.put(weights.get(i), new ArrayList<>());
                temp.get(weights.get(i)).add(graphTo.get(i));
            }

            temp = m.get(graphTo.get(i));
            if (temp.containsKey(weights.get(i))) {
                temp.get(weights.get(i)).add(graphFrom.get(i));
            } else {
                temp.put(weights.get(i), new ArrayList<>());
                temp.get(weights.get(i)).add(graphFrom.get(i));
            }

            s.add(weights.get(i));
        }

        for(int w: s){
            Arrays.fill(visited, false);
            for (int j = 1; j <= n; j++) {
                Pair temp = new Pair(1, 1);
                getNodes(m, j, visited, w, temp);
                ans = Math.max(temp.first * temp.second, ans);
            }
        }

        return ans;
    }

    private static void getNodes(Map<Integer, Map<Integer, List<Integer>>> m, int k, boolean[] visited, int weight, Pair ans) {
        if (visited[k]) {
            return;
        }

        visited[k] = true;
        int temp = ans.first;
        if(k>ans.first){
            ans.first = k;
            if(temp>ans.second){
                ans.second=k;
            }
        } else if(k>ans.second){
            ans.second = k;
        }

        if (m.get(k).containsKey(weight)) {
            for (int i = 0; i < m.get(k).get(weight).size(); i++) {
                if (!visited[m.get(k).get(weight).get(i)]) {
                    getNodes(m, m.get(k).get(weight).get(i), visited, weight, ans);
                }
            }
        }
    }

    public static void main(String[] args) {
        int ans = countComponents(10, Arrays.asList(1,7,5,10,6,2), Arrays.asList(2,3,6,8,9,3), Arrays.asList(5,5,5,5,5,5));
//        int ans = countComponents(4, Arrays.asList(1, 1, 2, 2, 2), Arrays.asList(2, 2, 3, 3, 4), Arrays.asList(1, 2, 1, 3, 3));

        System.out.println(ans);
    }

    static class Pair {
        int first;
        int second;

        Pair(int x, int y) {
            this.first = x;
            this.second = y;
        }
    }
}

