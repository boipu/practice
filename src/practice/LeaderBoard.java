package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class LeaderBoard {

    public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        // Write your code here
        List<Integer> board = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < ranked.size(); i++) {
            if (!freq.containsKey(ranked.get(i))) {
                board.add(ranked.get(i));
                freq.put(ranked.get(i), 1);
            }
        }
        List<Integer> ans =  new ArrayList<>();
        for(int i=0 ; i<player.size() ; i++){
            int temp = binarySearch(board, 0, board.size()-1, player.get(i));
            ans.add(temp+1);
        }

        return ans;

    }

    public static int binarySearch(List<Integer> arr, int start, int end, int value) {

        int mid = (start + end + 1) / 2;


        if (value > arr.get(mid) && value < arr.get(start)) {
            return binarySearch(arr, start, mid-1, value);
        }

        if (value < arr.get(mid) && value> arr.get(end)) {
            return binarySearch(arr, mid+1 , end, value);
        }

        if(arr.get(start) <= value){
            return start;
        }

        if(arr.get(end)> value){
            return end+1;
        }

        return mid;
    }

    public static void main(String[] args) {
        List<Integer> ranked = Arrays.asList(100, 100, 50, 40, 40, 20, 10);
        List<Integer> player =  Arrays.asList(5, 25, 50, 120);

        List<Integer> ans = climbingLeaderboard(ranked, player);

        ans.forEach(System.out::println);
    }

}
