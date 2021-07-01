package practice;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

//import for Scanner and other utility classes

public class robot {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int[] s = new int[t];
        List<String> input = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            s[i] = sc.nextInt();
            input.add(sc.next());
        }

        for (int i = 0; i < t; i++) {
            String ans = solve(input.get(i));
            ans = ans + "0";
            System.out.println(ans);
        }

    }

    private static String solve(String s) {
        String ans = "";
        Pair<Integer, Integer> pos = finalPosition(s);
        int x = pos.getKey();
        int y = pos.getValue();
        int x1 = 0;
        int y1 = 0;
        if (Math.abs(x) > Math.abs(y) && x != 0 && y != 0) {
            y1 = y;
            x1 = Math.abs(x) - Math.abs(y);
            x1 = x1 * (x / Math.abs(x));
        } else if (y != 0 && Math.abs(y) > Math.abs(x) && x != 0) {
            x1 = x;
            y1 = Math.abs(y) - Math.abs(x);
            y1 = y1 * (y / Math.abs(y));
        }

        String d1 = getDir(x1, y1, 0, 0);
        int temp = Math.abs(x1);
        while (temp > 0) {
            ans = ans + d1;
            temp--;
        }

        String d2 = getDir(x, y, x1, y1);

        for (int i = Math.abs(x1); i < Math.max(Math.abs(x), Math.abs(y)); i++) {
            ans = ans + d2;
        }


        return ans;
    }

    private static String getDir(int x1, int y1, int x2, int y2) {
        if (x1 > x2 && y1 > y2) {
            return "1";
        } else if (x1 < x2 && y1 > y2) {
            return "4";
        } else if (x1 < x2 && y1 < y2) {
            return "3";
        } else if (x1 > x2 && y1 < y2) {
            return "2";
        } else if (x1 > x2) {
            return "6";
        } else if (y1 > y2) {
            return "5";
        } else if (x2 > x1) {
            return "8";
        } else {
            return "7";
        }

    }

    private static Pair<Integer, Integer> finalPosition(String s) {

        char[] path = s.toCharArray();
        int x = 0, y = 0;
        int dir = 0;

        for (int i = 0; i < path.length; i++) {
            char move = path[i];

            if (move == 'R')
                dir = (dir + 1) % 4;
            else if (move == 'L')
                dir = (4 + dir - 1) % 4;

            else {
                if (dir == 0)
                    y++;
                else if (dir == 1)
                    x++;
                else if (dir == 2)
                    y--;
                else // dir == 3
                    x--;
            }
        }

        return new Pair<>(x, y);
    }
}
