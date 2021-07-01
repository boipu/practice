package practice;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class queensAttack {
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        Map<Pair<Integer, Integer>, Integer> obs = new HashMap<>();

        for (int i = 0; i < obstacles.length; i++) {
            obs.put(new Pair<>(obstacles[i][0] - 1, obstacles[i][1] - 1), 1);
        }

        return calRow(obs, n, r_q - 1, c_q - 1);
    }

    private static int calRow(Map<Pair<Integer, Integer>, Integer> obs, int s, int row, int col) {
        int ans = 0;
        int rowAns = 0;
        int colAns = 0;
        int diagAns1 = 0;
        int diagAns2 = 0;
        boolean queen = false;
        for (int i = 0; i < s; i++) {
            Pair<Integer, Integer> p = new Pair<>(row, i);
            if (obs.containsKey(p)) {
                ans = 0;
                queen = false;
            } else {
                ans++;
            }

            if (i == col) {
                queen = true;
                ans--;
            }

            if (queen) {
                rowAns = Math.max(ans, rowAns);
            }
        }

        queen = false;
        ans = 0;
        for (int i = 0; i < s; i++) {
            Pair<Integer, Integer> p = new Pair<>(i, col);
            if (obs.containsKey(p)) {
                ans = 0;
                queen = false;
            } else {
                ans++;
            }

            if (i == row) {
                queen = true;
                ans--;
            }

            if (queen) {
                colAns = Math.max(ans, colAns);
            }
        }
        queen = false;
        ans = 0;
        for (int i = row, j = col; i < s && j < s; i++, j++) {
            Pair<Integer, Integer> p = new Pair<>(i, j);
            if (obs.containsKey(p)) {
                break;
            }

            if (i != row && j != col) {
                diagAns1++;
            }
        }

        for (int i = row, j = col; i >= 0 && j < s; i--, j++) {
            Pair<Integer, Integer> p = new Pair<>(i, j);
            if (obs.containsKey(p)) {
                break;
            }
            if (i != row && j != col) {
                diagAns1++;
            }
        }

        for (int i = row, j = col; i < s && j >= 0; i++, j--) {
            Pair<Integer, Integer> p = new Pair<>(i, j);
            if (obs.containsKey(p)) {
                break;
            }
            if (i != row && j != col) {
                diagAns1++;
            }
        }

        for (int i = row, j = col; i >=0 && j >=0; i--, j--) {
            Pair<Integer, Integer> p = new Pair<>(i, j);
            if (obs.containsKey(p)) {
                break;
            }
            if (i != row && j != col) {
                diagAns1++;
            }
        }
        System.out.println(rowAns);
        System.out.println(colAns);
        System.out.println(diagAns1);
        System.out.println(diagAns1);

        return rowAns + colAns + diagAns1 + diagAns2;
    }

    public static void main(String[] args) {
        int[][] obs = new int[3][2];

        obs[0][0] = 5;
        obs[0][1] = 5;
        obs[1][0] = 4;
        obs[1][1] = 2;

        obs[2][0] = 2;
        obs[2][1] = 3;
//        obs[3][0] = 2;
//        obs[3][1] = 3;

        System.out.println(queensAttack(5, 3, 4, 3, obs));


    }
}
