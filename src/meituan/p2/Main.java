package meituan.p2;

import java.util.Scanner;

/**
 * 寻找分店价值最大化选址方案。
 * 给定 n 个地址，给定分店最短距离 k，以及每个地点开分店的价值期望，求分店的选址方案使得期望价值最大。
 *
 * Created by yfwz100 on 16/9/11.
 */
public class Main {

    private static void solve(int k, int[] x, int[] y) {
        assert(k >= 0);
        assert(x.length == y.length);
        // 根据位置排序
        for (int i = 0; i < x.length; i++) {
            for (int j = i+1; j < x.length; j++) {
                if (x[i] > x[j]) {
                    int t;

                    t = x[i];
                    x[i] = x[j];
                    x[j] = t;

                    t = y[i];
                    y[i] = y[j];
                    y[j] = t;
                }
            }
        }
        // 价值转移状态矩阵
        int[] t = new int[x.length];
        t[0] = y[0];
        // 选择转移状态矩阵
        int[][] choice = new int[x.length][x.length];
        choice[0][0] = 1;
        // 遍历分店地址
        for (int i = 1; i < x.length; i++) {
            t[i] = y[i]; // 初始化当前位置价值
            choice[i][i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (x[i] - x[j] < k) {
                    if (t[j] > t[i]) {
                        t[i] = t[j];

                        System.arraycopy(choice[j], 0, choice[i], 0, choice[j].length);
                    }
                } else {
                    int val = t[j] + y[i];
                    if (val > t[i]) {
                        t[i] = val;

                        System.arraycopy(choice[j], 0, choice[i], 0, choice[j].length);
                        choice[i][i] = 1;
                    }
                    break;
                }
            }
        }
        // 输出最后的 t 值
        System.out.println(t[t.length - 1]);
        // 输出选择
        boolean first = true;
        int[] c = choice[choice.length-1];
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 1) {
                if (!first) {
                    System.out.print(',');
                } else {
                    first = false;
                }
                System.out.print(x[i]);
            }
        }
    }

    public static void main(String ... args) {
        int k, x[], y[];
        Scanner sc = new Scanner(System.in);
        String[] input;

        k = sc.nextInt();

        input = sc.next().split(",");
        x = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            x[i] = Integer.parseInt(input[i]);
        }

        input = sc.next().split(",");
        y = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            y[i] = Integer.parseInt(input[i]);
        }

        solve(k, x, y);
    }
}
