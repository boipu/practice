package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeviceApplication {

    public static void main(String[] args) {

//        System.out.println(solve(10, Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 5), Arrays.asList(3, 7) , Arrays.asList(4,10) , Arrays.asList(5,5)), Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4), Arrays.asList(4, 5), Arrays.asList(5, 5))));

        System.out.println(solve(1, Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 5), Arrays.asList(3, 7) , Arrays.asList(4,10) , Arrays.asList(5,5)), Arrays.asList(Arrays.asList(1, 2), Arrays.asList(2, 3), Arrays.asList(3, 4), Arrays.asList(4, 5), Arrays.asList(5, 5))));


    }

    public static List<List<Integer>> solve(int d, List<List<Integer>> f, List<List<Integer>> b) {

        Map<Integer, List<Integer>> m = new HashMap<>();

        List<Pair> f1 = new ArrayList<>();
        List<Pair> b1 = new ArrayList<>();

        for (int i = 0; i < f.size(); i++) {
            f1.add(new Pair(f.get(i).get(0), f.get(i).get(1)));
        }

        for (int i = 0; i < b.size(); i++) {
            b1.add(new Pair(b.get(i).get(0), b.get(i).get(1)));

            if (m.containsKey(b1.get(i).second)) {
                List<Integer> temp = m.get(b1.get(i).second);
                temp.add(b1.get(i).first);
                m.put(b1.get(i).second, temp);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(b1.get(i).first);
                m.put(b1.get(i).second, temp);
            }
        }

        f1.sort(Comparator.comparingInt(value -> value.second));
        b1.sort(Comparator.comparingInt(value -> value.second));
        int closest = findClosestPair(f1, b1, d);

        List<List<Integer>> ans = new ArrayList<>();

        if(closest <= d){
            for (int i = 0; i < f1.size(); i++) {
                if (m.containsKey(closest - f1.get(i).second)) {
                    List<Integer> temp = m.get(closest - f1.get(i).second);
                    ans.add(Arrays.asList(f1.get(i).first, temp.get(0)));
                    temp.remove(0);
                    if (temp.size() > 0) {
                        m.put(closest - f1.get(i).second, temp);
                    } else {
                        m.remove(closest - f1.get(i).second);
                    }
                }
            }
        }

        if(ans.isEmpty()){
            ans.add(new ArrayList<>());
        }

        return ans;
    }

    public static Integer findClosestPair(List<Pair> l1, List<Pair> l2, int x) {
        int diff = Integer.MAX_VALUE;
        int left_res = 0, right_res = 0;
        int m = l1.size();
        int n = l2.size();
        int left = 0, right = n - 1;
        while (left < m && right >= 0) {
            if (Math.abs(l1.get(left).second + l2.get(right).second - x) < diff) {
                left_res = left;
                right_res = right;
                diff = Math.abs(l1.get(left).second + l2.get(right).second - x);
            }

            if (l1.get(left).second + l2.get(right).second > x)
                right--;
            else
                left++;
        }

        return l1.get(left_res).second + l2.get(right_res).second;
    }

    public static class Pair {
        Integer first;
        Integer second;

        Pair(int x, int y) {
            this.first = x;
            this.second = y;
        }
    }

}

