package practice;

import java.util.*;

public class encryption {
    private static String encryption(String s) {
        int row= (int)Math.floor(Math.sqrt(s.length()));
        int col= (int)Math.ceil(Math.sqrt(s.length()));

        if(row*col < s.length()){
            row++;
        }
        s = s.replace(" ", "");

        List<String> arr = new ArrayList<>();

        for(int i=0 ; i<row ; i++){
            arr.add(s.substring(i*col, Math.min(i*col+col, s.length())));
        }
        StringBuilder ans = new StringBuilder();

        for(int j=0 ; j<arr.get(0).length() ; j++){
            for(int i=0 ; i<arr.size() ; i++){
                if(arr.get(i).length() > j){
                    ans.append(arr.get(i).charAt(j));
                }
            }
            ans.append(" ");
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(encryption("chillout"));
    }
}
