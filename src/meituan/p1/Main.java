package meituan.p1;

import java.util.Scanner;

/**
 * 寻找最大公共子序列。
 *
 * Created by yfwz100 on 16/9/11.
 */
public class Main {

    private static int solve(String s1, String s2) {
        int max = 0;
        int len1 = s1.length(), len2 = s2.length();
        int maxLen = Math.max(len1, len2);
        int[] t = new int[maxLen]; // 状态转移矩阵（压缩）
        for (int i = 0; i < len2; i++) {
            for (int j = len1 - 1; j >= 0; j--) { // 注意遍历方向，因为我们需要用到前一次遍历的结果。
                if (s2.charAt(i) == s1.charAt(j)) {
                    if ((i == 0) || (j == 0)) {
                        t[j] = 1;
                    } else {
                        t[j] = t[j - 1] + 1;
                    }
                } else {
                    t[j] = 0;
                }
                // 如果更大，就赋予新的值。
                if (t[j] > max) {
                    max = t[j];
                }
            }
        }
        return max;
    }

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();

        System.out.println(solve(s1, s2));
    }
}
