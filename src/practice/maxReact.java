package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class maxReact {
    public static int largestRectangleArea(int[] A) {
        Stack<Integer> s = new Stack<>();

        int i = 0;
        int ans = 0;

        while (i < A.length) {
            if (s.isEmpty() || A[s.peek() - 1] <= A[i]) {
                s.push(i + 1);
                i++;
            } else {
                int height = A[s.peek() - 1];
                s.pop();

                if (s.isEmpty()) {
                    ans = Math.max(ans, height * i);
                } else {
                    ans = Math.max(ans, height * (i - s.peek()));
                }
            }
        }

        while (!s.isEmpty()) {
            int height = A[s.peek() - 1];
            s.pop();

            if (s.isEmpty()) {
                ans = Math.max(ans, height * i);
            } else {
                ans = Math.max(ans, height * (i - s.peek()));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
//        int[] practice.A = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
//        System.out.println(Arrays.toString(slidingMaximum(practice.A, 2)));
//        System.out.println(solvePalindrome("AAAAA"));
        System.out.println(highestValuePalindrome("092282", 6, 3));
    }

    public static int[] slidingMaximum(final int[] A, int B) {
        if (B > A.length) {
            int[] x = new int[1];
            x[0] = Integer.MIN_VALUE;
            for (int i = 0; i < A.length; i++) {
                x[0] = Math.max(x[0], A[i]);
            }
            return x;
        }

        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(Collections.reverseOrder());

        int[] ans = new int[A.length - B + 1];
        for (int i = 0; i < B; i++) {
            pQueue.add(A[i]);
        }
        int i = 0;
        int j = B;
        while (j < A.length) {
            ans[i] = pQueue.peek();
            pQueue.remove(A[i]);
            pQueue.add(A[j]);
            i++;
            j++;
        }
        ans[i] = pQueue.peek();
        return ans;
    }

    public static int solvePalindrome(String A) {
        int size = A.length();
        int mid = (size + 1) / 2;
        int[] ans = new int[mid];
        Arrays.fill(ans, size);
        Queue<Integer> q = new LinkedList<>();
        q.size();

        for (int i = 0; i < mid; i++) {
            if (new StringBuilder(A.substring(0, i + 1)).reverse().toString().equals(A.substring(i + 1, 2 * (i + 1)))) {
                ans[i] = size - (2 * (i + 1));
            }
        }

        int x = size;
        for (int i = 0; i < mid; i++) {
            x = Math.min(x, ans[i]);
        }
        return x;
    }

    static String highestValuePalindrome(String s, int n, int k) {
        AtomicInteger misMatch = new AtomicInteger(0);
        if (isPalindrome(s, misMatch)) {
            s = maxi(s, k);
            return s;
        } else {
            int mid = s.length() / 2;
            int i = mid - 1;
            int j;
            if (s.length() % 2 == 0) {
                j = mid;
            } else {
                j = mid + 1;
            }
            StringBuilder temp = new StringBuilder(s);
            while (k > 0 && i >= 0 && j < s.length()) {
                if (temp.charAt(i) != temp.charAt(j)) {
                    if (temp.charAt(i) != '9' && temp.charAt(j) != '9' && k >= (misMatch.get() * 2)) {
                        temp.setCharAt(i, '9');
                        k--;

                        temp.setCharAt(j, '9');
                        k--;
                        misMatch.getAndAdd(-1);


                    } else if (temp.charAt(i) > temp.charAt(j)) {
                        temp.setCharAt(j, temp.charAt(i));
                        k--;

                    } else {
                        temp.setCharAt(i, temp.charAt(j));
                        k--;
                    }
                }
                i--;
                j++;
            }
            s = temp.toString();
            misMatch.set(0);
            if (isPalindrome(s, misMatch)) {
                if (k > 0) {
                    s = maxi(s, k);
                }
                return s;
            } else {
                return "-1";
            }
        }

    }

    static String maxi(String s, int k) {
        StringBuilder temp = new StringBuilder(s);
        int i = 0;
        int j = s.length() - 1;
        while (k > 0 && i <= j) {
            if (k >= 2) {
                if (temp.charAt(i) != '9') {
                    temp.setCharAt(i, '9');
                    temp.setCharAt(j, '9');
                    k = k - 2;
                }
                i++;
                j--;
            } else {
                if (i == j) {
                    temp.setCharAt(i, '9');
                    k = k - 1;

                }
                i++;
                j--;
            }
        }
        return temp.toString();
    }

    static boolean isPalindrome(String s, AtomicInteger misMatch) {
        int i = 0;
        int j = s.length() - 1;
        boolean ans = true;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                if (s.charAt(i) == '9' || s.charAt(j) == '9') {
                    misMatch.getAndIncrement();
                }
                ans = false;
            }

            i++;
            j--;
        }

        return ans;
    }

    public ArrayList<Integer> slidingMaximum2(final List<Integer> A, int B) {
        Deque<Integer> window = new LinkedList<Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < A.size(); i++) {
            int val = A.get(i);
            // Remove all elements smaller than the current one from the queue
            while (!window.isEmpty() && val >= A.get(window.getLast())) {
                window.removeLast();
            }
            // Add the current element's index to the queue. It is important that we
            // store the indicies rather than the values.
            window.add(i);
            // The first element of the queue is always the largest. If the index of
            // this number is smaller than the window's stating index then we remove it
            if (window.getFirst() < (i + 1) - B) {
                window.removeFirst();
            }
            // Ignores the first few interations
            if (i >= B - 1)
                result.add(A.get(window.getFirst()));
        }
        return result;
    }
}
