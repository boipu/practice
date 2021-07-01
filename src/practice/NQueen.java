package practice;

public class NQueen {

    private static int N = 4;

    public static void main(String[] args) {
        int board[][] = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 0;
            }
        }

        solve(board,0);

        for(int i=0  ;i<N ; i++){
            for(int j=0  ;j<N ; j++){
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    private static boolean solve(int[][] board, int column){
        if(column >= N){
            return true;
        }

        for(int i=0 ; i<N ;i++){
            if(isSafe(board, i, column)){
                board[i][column] = 1;
                if(solve(board, column+1)){
                    return true;
                }

                board[i][column]= 0;
            }
        }

        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col){

        for(int i=0 ; i<N ; i++){
            if(board[row][i]==1){
                return false;
            }
        }

        for(int i=row, j=col; i>=0 && j>=0 ; i-- , j--){
            if(board[i][j] ==1)
                return false;
        }

        for(int i=row, j=col; i<N && j>=0 ; i++ , j--){
            if(board[i][j] ==1)
                return false;
        }
        return true;
    }
}
