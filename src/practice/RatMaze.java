package practice;

public class RatMaze {

    private static int N = 4;

    private static int[][] ans = new int[4][4];

    public static void main(String[] args) {
        int maze[][] = {{1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans[i][j] = 0;
            }
        }

        if (solve(maze, 0, 0)) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(ans[i][j]);
                    System.out.print("  ");
                }
                System.out.println();
            }
        } else {
            System.out.println("cant be solved");
        }

    }

    static boolean solve(int[][] maze, int row, int col) {
        ans[row][col] = 1;

        if (row == N - 1 && col == N - 1) {
            return true;
        }

        if (row + 1 < N && col < N && maze[row + 1][col] == 1) {
            ans[row + 1][col] = 1;
            if (solve(maze, row + 1, col)) {
                return true;
            }

            ans[row + 1][col] = 0;
        }

        if (row < N && col + 1 < N && maze[row][col + 1] == 1) {
            ans[row][col + 1] = 1;
            if (solve(maze, row, col + 1)) {
                return true;
            }
            ans[row][col + 1] = 0;
        }

        return false;

    }
}
