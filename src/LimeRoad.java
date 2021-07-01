import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LimeRoad {
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

        int maxSofar = 0;
        for(int w: s){
            Arrays.fill(visited, false);
            for (int j = 1; j <= n; j++) {
                Pair temp = new LimeRoad.Pair(1,1);
                int x = getNodes(m, j, visited, w, temp);
                if(x>maxSofar){
                    maxSofar = x;
                    ans=temp.first*temp.second;
                }

                if(x==maxSofar){
                    ans = Math.max(ans, temp.first*temp.second);
                }
            }
        }

        return ans;
    }

    private static int getNodes(Map<Integer, Map<Integer, List<Integer>>> m, int k, boolean[] visited, int weight, Pair pair) {
        if(visited[k]){
            return 0;
        }

        int ans=1;
        int temp = pair.first;
        if(k>pair.first){
            pair.first = k;
            if(temp>pair.second){
                pair.second=k;
            }
        } else if(k>pair.second && k!=temp){
            pair.second = k;
        }
        visited[k]=true;

        if (m.get(k).containsKey(weight)) {
            for (int i = 0; i < m.get(k).get(weight).size(); i++) {
                if (!visited[m.get(k).get(weight).get(i)]) {
                    ans += getNodes(m, m.get(k).get(weight).get(i), visited, weight, pair);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int ans = countComponents(10, Arrays.asList(1,7,5,10,6,2), Arrays.asList(2,3,6,8,9,3), Arrays.asList(5,5,5,5,5,5));
        int ans = countComponents(4, Arrays.asList(1, 1, 2, 2, 2), Arrays.asList(2, 2, 3, 3, 4), Arrays.asList(1, 2, 1, 3, 3));

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

