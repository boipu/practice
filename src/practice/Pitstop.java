package practice;

import java.util.Comparator;
import java.util.*;
import java.util.List;

import java.util.ArrayList;

public class Pitstop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int s= sc.nextInt();
        int l = sc.nextInt();
        int[][] ar = new int[l][2];
        for(int i=0 ; i<l ; i++){
            ar[i][0] = sc.nextInt();
            ar[i][1] = sc.nextInt();
        }
        System.out.println(least(k,s,ar));
    }


    static class Pair{
        int key;
        int value;

        Pair(int x, int y){
            this.key=x;
            this.value=y;
        }
    }
    public static int least(int k, int s, int[][] arr){
        List<Pair> l= new ArrayList<>();
        for(int i =0 ; i<arr.length ; i++){
            int rem=0;
            if(s>arr[i][0]){
                rem= s-arr[i][0];
            }
            l.add(new Pair(arr[i][0], arr[i][0]+arr[i][1]+ rem));
        }

        int[] a= new int[k+1];
        Arrays.fill(a, Integer.MAX_VALUE);
        for(int i=0  ;i<l.size() ; i++){

            if(l.get(i).key<=s){
                a[l.get(i).key] = 0;
            }

            if(l.get(i).value>=k){
                a[k]=Math.min(a[k], l.get(i).key);
            }

            for(int j=i+1 ; j<l.size() ; j++){
                if(l.get(i).value<l.get(j).key){
                    break;
                }

                int rem = l.get(i).value - l.get(j).key;

                l.get(j).value = l.get(j).value+rem;
                a[l.get(j).key] = Math.min(l.get(i).key, a[l.get(j).key]);
            }
        }


        int ans=0;
        while(a[k]!=Integer.MAX_VALUE){
            k=a[k];
            ans++;
        }

        return k==0? ans-1:-1;
    }
}


