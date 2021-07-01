package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class meetings {
    public static class Pair{
        int first;
        int second;

        Pair(int x, int y){
            this.first = x;
            this.second = y;
        }
    }

    public static int solve(int[][] A) {
        List<Pair> l = new ArrayList<>();
        int max = 0;
        for (int i=0  ;i<A.length ; i++){
            l.add(new Pair(A[i][0], A[i][1]));
            max = Math.max(max, A[i][1]);
        }

        int[] freq = new int[max+1];
        Arrays.fill(freq, 0);
        for(int i=0 ; i<l.size() ; i++){
            freq[l.get(i).first]++;
            freq[l.get(i).second]--;
        }

        int temp = 0;
        int ans = 0;

        for(int i=0 ; i<=max ; i++){
            temp += freq[i];

            ans = Math.max(temp, ans);
        }

        return ans;
    }

    public static void main(String[] args) {
//        Scanner sc= new Scanner(System.in);
//        int s = sc.nextInt();
//        int[][] arr = new int[s][2];
//        for(int i=0 ; i<s ; i++){
//            arr[i][0] = sc.nextInt();
//            arr[i][1] = sc.nextInt();
//        }
//        int [] arr2 = {-259, -825, 459, 825, 221, 870, 626, 934, 205, 783, 850, 398};
//        System.out.println(solve(arr2, -42));
        System.out.println(solve("abadbc"));
    }

    public static String solve(String A) {
        int[] freq = new int[26];

        Arrays.fill(freq,0);
        String ans= "";
        String temp;

        Deque<Character> q = new LinkedList<>();

        for(int j=0 ; j<A.length() ; j++){
            freq[(int) A.charAt(j) -97]++;
            q.add(A.charAt(j));
            while (!q.isEmpty() && freq[Integer.valueOf(q.getFirst()) - 97] > 1) {
                q.removeFirst();
            }
            q.remove("practice.A");

            if(!q.isEmpty()){
                temp = String.valueOf(q.getFirst());
            } else{
                temp="#";
            }
            ans = ans + temp;
        }


        return ans;
    }

    public String solve2(String A) {
        //to store the characters in the order they come, and allow deletion from front when needed.
        Queue<Character> q = new LinkedList<>();
        //to keep track of characters already there.
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder B = new StringBuilder();
        for(int i = 0;i<A.length();++i){
            //if character was already there, we increment its value by 1. ELse we put it with a value of 1.
            if(map.containsKey(A.charAt(i))) map.replace(A.charAt(i), map.get(A.charAt(i))+1);
            else map.put(A.charAt(i),1);
            //adding current character to queue
            q.add(A.charAt(i));
            //map has the count of characters. So we remove the characters from front until we find a character whose count is not >1
            while(!q.isEmpty() && map.get(q.peek())>1){
                q.poll();
            }
            //if queue is empty, means no unrepeated characters presednt. Hence, we add #, else we add the character in front of the queue.
            if(q.isEmpty()) B.append("#");
            else B.append(q.peek());
        }
        //we return the required string.
        return B.toString();
    }

    private static int solve(int[] A, int B) {
        Arrays.sort(A);

        int i=0;
        int j=1;

        while(i<A.length && j<A.length){
            if(A[j]<0 && A[i]<0){
                if(A[j]-A[i]>B){
                    j++;
                } else if(A[j]-A[i]<B){
                    i++;
                } else{
                    return 1;
                }
            }else{
                if(A[j]-A[i]>B){
                    i++;
                } else if(A[j]-A[i]<B){
                    j++;
                } else{
                    return 1;
                }
            }

            if(i==j){
                j+=1;
            }
        }

        return 0;
    }

//    5
//            0 14
//            6 25
//            12 19
//            13 19
//            5 9
}

//[1, 18]
//        [18, 23]
//        [15, 29]
//        [4, 15]
//        [2, 11]
//        [5, 13]