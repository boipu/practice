package practice;

import java.util.*;
import java.util.Queue;

public class SnakeAndLadder {

    static class point{
        private Integer vertex;
        private Integer distance;

        point(int v,int d){
            vertex = v;
            distance =d;
        }
    }

    static class Graph{
        private Integer vertex;
        private LinkedList<Integer> adj[];

        Graph(int v){
            vertex = v;
            adj = new LinkedList[v];
            for(int i= 0 ; i<v ; i++){
                adj[i] = new LinkedList<>();
            }
        }

        void addEdge(int source, int destination){
            adj[source].add(destination);
        }
    }

    public static void main(String[]args){
        int N = 30;
        int moves[] = new int[N];
        for (int i = 0; i < N; i++)
            moves[i] = -1;

        // Ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

        // Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        System.out.println("min distance" + solveSnakeAndLadder(moves, 29));
    }

    private static int solveSnakeAndLadder(int[] moves, int destination){
        int [] visited = new int[100];

        for(int i= 0 ; i<100 ;i++){
            visited[i] = -1;
        }

        visited[0] = 1;

        Queue<point> q = new LinkedList<>();
        q.add(new point(0, 0));

        point current = null;
        while(!q.isEmpty()){

            current = q.peek();
            q.remove();

            if(current.vertex == destination ){
                break;
            }

            for(int i=current.vertex+1 ; i<=current.vertex+6 && i<=destination ; i++){
                System.out.println(i);
                if(visited[i] == -1){

                    point next = new point( i, current.distance + 1);
                    visited[next.vertex] = 1;
                    if(moves[i] != -1){
                        next.vertex = moves[i];
                    }

                    q.add(next);
                }
            }

        }

        return current.distance;
    }

    public static Comparator<List<Integer>> compareRoutes = new Comparator<List<Integer>>() {
        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            return o1.get(1) < o2.get(1) ? 1 : 0;
        }
    };

    public static Comparator<List<Integer>> compareAnswer = new Comparator<List<Integer>>() {
        @Override
        public int compare(List<Integer> o1, List<Integer> o2) {
            return o1.get(0) < o2.get(0) ? 1 : 0;
        }
    };

    public static List<List<Integer>> findMatch(List<List<Integer>> routeList, int target)
    {
        int start = 0, end = routeList.size()-1;
        int ans = -1;

        List<List<Integer>> answer = new ArrayList<>();

        while (start <= end) {
            int mid = (start + end) / 2;

            // Move to the left side if the target is smaller
            if ((routeList.get(mid)).get(1) > target) {
                end = mid - 1;
            }

            // Move right side
            else {
                if ((routeList.get(mid)).get(1) == target) {
                    answer.add(routeList.get(mid));
                }
                ans = mid;
                start = mid + 1;
            }
        }

        if (answer.contains(routeList.get(ans)) == false) {
            answer.add(routeList.get(ans));
        }
        return answer;
    }

    public static List<List<Integer>> routePairs(int maxTravelDistance, List<List<Integer>> forwardRoute, List<List<Integer>> returnRoute) {
        List<List<Integer>> answer = new ArrayList<>();

        int maxMatch = -1;

        if (forwardRoute.size() < returnRoute.size()) {
            Collections.sort(returnRoute, compareRoutes);
            for (List<Integer> currentRoute : forwardRoute) {
                int targetValue = maxTravelDistance - currentRoute.get(1);

                for(List<Integer> match: findMatch(returnRoute, targetValue)) {
                    if (match.get(1) + currentRoute.get(1) > maxMatch) {
                        answer.clear();
                        maxMatch = match.get(1) + currentRoute.get(1);
                        answer.add(new ArrayList<Integer>(){{
                            add(currentRoute.get(0));
                            add(match.get(0));
                        }});
                    } else if (match.get(1) + currentRoute.get(1) == maxMatch) {
                        answer.add(new ArrayList<Integer>(){{
                            add(currentRoute.get(0));
                            add(match.get(0));
                        }});
                    }
                }
            }
        } else {
            Collections.sort(forwardRoute, compareRoutes);
            for (List<Integer> currentRoute : returnRoute) {
                int targetValue = maxTravelDistance - currentRoute.get(1);
                for(List<Integer> match : findMatch(forwardRoute, targetValue)) {
                    if (match.get(1) + currentRoute.get(1) > maxMatch) {
                        answer.clear();
                        maxMatch = match.get(1) + currentRoute.get(1);
                        answer.add(new ArrayList<Integer>(){{
                            add(match.get(0));
                            add(currentRoute.get(0));
                        }});
                    } else if (match.get(1) + currentRoute.get(1) == maxMatch) {
                        answer.add(new ArrayList<Integer>(){{
                            add(match.get(0));
                            add(currentRoute.get(0));
                        }});
                    }
                }
            }
        }

        Collections.sort(answer, compareAnswer);
        return answer;
    }

}
