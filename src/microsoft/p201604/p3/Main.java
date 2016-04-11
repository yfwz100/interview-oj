package microsoft.p201604.p3;

import java.util.Scanner;

/**
 * 题目: Demo Day
 * 地址: http://hihocoder.com/problemset/problem/1290
 *
 * Created by yfwz100 on 16/4/11.
 */
public class Main {

    /**
     * Check if there's a block in the given position. Return 0 if it is, otherwise 1.
     * It's the opposite of the {@link #exists(char[][], int, int)}.
     *
     * @see #exists(char[][], int, int)
     * @param maze the maze array.
     * @param i the first index.
     * @param j the second index.
     * @return 0 if it is, otherwise 1.
     */
    private static int blocked(char[][] maze, int i, int j) {
        if (i < maze.length && j < maze[i].length) { // check bounds.
            if (maze[i][j] == 'b') {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    /**
     * Check if there's a block in the given position. Return 1 if it is, otherwise 0.
     * It's the opposite of the {@link #blocked(char[][], int, int)}.
     *
     * @see #blocked(char[][], int, int)
     * @param maze the maze array.
     * @param i the first index.
     * @param j the second index.
     * @return 1 if it is, otherwise 0.
     */
    private static int exists(char[][] maze, int i, int j) {
        if (maze[i][j] == 'b') {
            return 1;
        } else {
            return 0;
        }
    }

    private static final int RIGHT = 0;
    private static final int DOWN = 1;

    private static int solve(char[][] maze) {
        int[][][] ans = new int[maze.length][maze[0].length][2];
        ans[0][0][RIGHT] = 0;
        ans[0][0][DOWN] = blocked(maze, 0, 1);
        // fill the border.
        for (int i = 1; i < ans.length; i++) {
            ans[i][0][DOWN] = ans[i-1][0][DOWN] + exists(maze, i, 0);
            ans[i][0][RIGHT] = ans[i-1][0][DOWN] + blocked(maze, i+1, 0) + exists(maze, i, 0);
        }
        // fill the border.
        for (int i = 1; i < ans[0].length; i++) {
            ans[0][i][RIGHT] = ans[0][i-1][RIGHT] + exists(maze, 0, i);
            ans[0][i][DOWN] = ans[0][i-1][RIGHT] + blocked(maze, 0, i+1) + exists(maze, 0, i);
        }
        // fill the content.
        for (int i = 1; i < ans.length; i++) {
            for (int j = 1; j < ans[i].length; j++) {
                ans[i][j][RIGHT] = Math.min(
                        ans[i][j-1][RIGHT],
                        ans[i-1][j][DOWN] + blocked(maze, i+1, j)
                ) + exists(maze, i, j);
                ans[i][j][DOWN] = Math.min(
                        ans[i-1][j][DOWN],
                        ans[i][j-1][RIGHT] + blocked(maze, i, j+1)
                ) + exists(maze, i, j);
            }
        }
        return Math.min(ans[ans.length-1][ans[0].length-1][RIGHT], ans[ans.length-1][ans[0].length-1][DOWN]);
    }

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        char[][] maze = new char[N][M];
        for (int i = 0; i < maze.length; i++) {
            String line = sc.next();
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j] = line.charAt(j);
            }
        }
        System.out.println(solve(maze));
    }
}
