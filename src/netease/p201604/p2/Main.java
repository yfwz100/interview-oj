package netease.p201604.p2;

import java.util.Scanner;

/**
 * 一起消消毒
 * http://hihocoder.com/contest/ntest2016spring2/problem/2
 *
 * Created by yfwz100 on 16/4/21.
 */
public class Main {
    
    private static void fallDown(char[][] grid, int x, int y, int offset) {
        for (int i = x; i >= offset; i--) {
            grid[i][y] = grid[i-offset][y];
        }
        for (int i = 0; i < offset; i++) {
            grid[i][y] = '.';
        }
    }

    private static boolean check(char[][] grid, int x, int y, char val) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            return grid[x][y] == val;
        }
        return false;
    }

    private static int solve(char[][] grid) {
        int[][] mask = new int[grid.length][grid[0].length];
        // check horizontally
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != '.') {
                    int offset = 0;
                    while (check(grid, i, j + offset, grid[i][j])) {
                        offset++;
                    }
                    if (offset > 2) {
                        for (int k = j; k < j + offset; k++) {
                            mask[i][k] = 1;
                        }
                    }
                }
            }
        }
        // check vertically
        for (int j = 0; j < grid[0].length; j++) {
            for (int i = 0; i < grid.length; i++) {
                if (grid[i][j] != '.') {
                    int offset = 0;
                    while (check(grid, i + offset, j, grid[i][j])) {
                        offset++;
                    }
                    if (offset > 2) {
                        for (int k = i; k < i + offset; k++) {
                            mask[k][j] = 1;
                        }
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[i].length; j++) {
                if (mask[i][j] > 0) {
                    fallDown(grid, i, j, 1);
                    count ++;
                }
            }
        }
//        printMask(mask);
        return count;
    }

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int S = sc.nextInt();
            for (int i = 0; i < S; i++) {
                int N = sc.nextInt(), M = sc.nextInt();
                // read grid.
                char[][] grid = new char[N][M];
                for (int y = 0; y < N; y++) {
                    grid[y] = sc.next().toCharArray();
                }
                int x0 = sc.nextInt(), y0 = sc.nextInt();
                int x1 = sc.nextInt(), y1 = sc.nextInt();
                // swap.
                char t = grid[x0][y0];
                grid[x0][y0] = grid[x1][y1];
                grid[x1][y1] = t;
                int count = 0;
                int a = 0;
                while ((a = solve(grid)) > 0) {
                    count += a;
//                    printMatrix(grid);
                }
                System.out.println(count);
            }
        }
    }

    private static void printMatrix(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                System.out.print(grid[y][x] + " ");
            }
            System.out.println();
        }
    }

    private static void printMask(int[][] mask) {
        for (int y = 0; y < mask.length; y++) {
            for (int x = 0; x < mask[y].length; x++) {
                System.out.print(mask[y][x] + " ");
            }
            System.out.println();
        }
    }
}
