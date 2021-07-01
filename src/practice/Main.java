package practice;

import javafx.util.Pair;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//
//        String s = "1102021222";
         int[] arr = {1,2,3,1,2};

        solve2(arr);
//        System.out.println(perfectSubstring(s, 2));



//        int[] arr = {-1,-1,0,1,1};
//        plusMinus(arr);
//
//        System.out.println(arr.length);

//        practice.Node root = new practice.Node(3);
//        root.left = new practice.Node(2);
//        root.right = new practice.Node(4);
//        root.left.left = new practice.Node(1);
//
//        practice.Node lca = LCA(root, 2, 4);
//
//        System.out.println(lca.data);
//
//        Queue<practice.Node> q1 = new LinkedList<>();
//        Queue<practice.Node> q2 = new LinkedList<>();
//
//        q1.add(root);
//
//        while (!q1.isEmpty() && !q2.isEmpty()) {
//            while (!q1.isEmpty()) {
//                practice.Node current = q1.peek();
//                q1.remove();
//                if (current.left != null) {
//                    q2.add(current.left);
//                }
//
//                if (current.right != null) {
//                    q2.add(current.right);
//                }
//
//                practice.Node next = q1.peek();
//                current.right = next;
//            }
//
//            while (!q2.isEmpty()) {
//                practice.Node current = q2.peek();
//                q2.remove();
//                if (current.left != null) {
//                    q1.add(current.left);
//                }
//
//                if (current.right != null) {
//                    q1.add(current.right);
//                }
//
//                practice.Node next = q2.peek();
//                current.right = next;
//            }
//        }

//        String time = timeConversion("07:05:45PM");
//        System.out.println(time);

    }

    static void plusMinus(int[] arr) {
        double postive = 0;
        double negative = 0;
        double zero = 0;
        int n= arr.length;

        for(int i=0 ; i<n ; i++){
            if(arr[i]>0){
                postive++;
            }else if(arr[i]<0){
                negative++;
            } else{
                zero++;
            }
        }
        String ans1 = String.valueOf(postive/n);
        String ans2 = String.valueOf(negative/n);
        String ans3 = String.valueOf(zero/n);

        System.out.println(finalAns(ans1));
        System.out.println(finalAns(ans2));
        System.out.println(finalAns(ans3));


    }

    public static String finalAns(String ans){
        if(ans.length() < 8){
            for (int i= ans.length() ; i<8 ; i++){
                ans = ans.concat("0");
            }
            return ans;
        } else{
            return ans.substring(0, 8);
        }
    }

    public static Node LCA(Node root, int a, int b) {

        if (root == null) {
            return null;
        }

        if (root.data < a && root.data < b) {
            return LCA(root.right, a, b);
        }

        if (root.data > a && root.data > b) {
            return LCA(root.left, a, b);
        }

        return root;
    }

    public static String timeConversion(String s) {
        /*
         * Write your code here.
         */

        String hour = s.substring(0,2);
        int h = Integer.valueOf(hour);

        h = h%12;

        if(s.contains("PM")){
            h = h+12;
            s= s.replace("PM","");
        } else{
            s= s.replace("AM","");
        }

        s= s.replaceFirst(hour, String.valueOf(h));

        return s;

    }

    public static boolean check(int[] arr, int k) {
        for(int val : arr) {
            if(val != 0 && val != k) return false;
        }
        return true;
    }

    public static int perfectSubstring(String s, int k) {
        int res = 0;
        for(int i=0; i<s.length(); i++) {
            int[] arr = new int[10];
            for(int j = i; j<s.length(); j++) {
                if(j > i + (10*k)) break;
                char ch = s.charAt(j);
                arr[ch-'0']++;
                if(check(arr, k)) res++;
            }
        }
        return res;
    }

    public static int solve(int [] arr){
        int ans = 0;
        for(int i=0 ; i<arr.length ; i++){
            int left = i-1;
            int right = i+1;
            while(left>=0 && arr[left]<arr[i]){
                ans++;
                left--;
            }

            while(right<arr.length && arr[right]<arr[i]){
                ans++;
                right++;
            }

            ans++;
        }
        return ans;
    }

    public static int solve2(int[] arr){
        List<Pair<Integer, Integer>> pairs =  new ArrayList<>();

        for (int i=0  ; i<arr.length ; i++){
            pairs.add(new Pair<>(i, arr[i]));
        }

        pairs.sort(Comparator.comparing(Pair::getValue));

        int l_min = Integer.MAX_VALUE;
        long x= 1233;
        l_min = Math.toIntExact(x);
        String s ="asd";
        int l_max = Integer.MIN_VALUE;
        int ans=0;

        for(int i=pairs.size()-1 ; i>=0 ; i--){
            l_min = Math.min(l_min, pairs.get(i).getKey());
            l_max = Math.max(l_max, pairs.get(i).getKey());
            if(i==pairs.size()-1){
                ans = ans+arr.length;
            } else {
                ans = ans + getCloset(l_min, l_max, arr.length-1, pairs.get(i).getKey());
            }
        }
        System.out.println(ans);
        return ans;

    }

    public  static int getCloset(int l, int r, int s, int f){

        if(l>r){
            if(f<=r){
                return 1 + Math.abs(f-0) + Math.abs(f-r);
            } else if(f>r && f<l){
                return 1 + Math.abs(f-l) + Math.abs(f-r);
            } else {
                return 1 + Math.abs(f-l) + Math.abs(f-s);
            }
        } else {
            if(f<=l){
                return 1 + Math.abs(f-0) + Math.abs(f-l);
            } else if(f>l && f<r){
                return 1 + Math.abs(f-l) + Math.abs(f-r);
            } else {
                return 1 + Math.abs(f-r) + Math.abs(f-s);
            }
        }

    }
}

