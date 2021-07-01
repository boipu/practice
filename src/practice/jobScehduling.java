package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Tuple {

    int first;

    int second;

    int third;

    Tuple(int a, int b, int c) {
        this.first = a;
        this.second = b;
        this.third = c;
    }
}

public class jobScehduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int s = sc.nextInt();
            List<Tuple> input = new ArrayList<>();
            for (int j = 0; j < s; j++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                int c = sc.nextInt();
                if (c <= a) {
                    input.add(new Tuple(a, b, c));
                }
            }
            int ans = solve(input);
            System.out.println(ans);
        }
    }

    private static int solve(List<Tuple> input) {
        int s = input.size();
        input.sort(Comparator.comparingInt(t -> t.first));
        int length = input.get(s - 1).first;

        int[][] dp = new int[s + 1][length + 1];

        for (int i = 0; i <= s; i++) {
            for (int j = 0; j <= length; j++) {
                dp[i][j] = 0;
            }
        }

        for (int i = 1; i <= s; i++) {
            for (int j = 1; j <= length; j++) {
                int x = Math.min(j, input.get(i-1).first) - input.get(i-1).third;
                if(x<0){
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], input.get(i - 1).second + dp[i - 1][x]);
                }
            }
        }

        return dp[s][length];
    }
}
