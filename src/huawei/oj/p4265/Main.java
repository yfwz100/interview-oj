package huawei.oj.p4265;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 合唱队队形.
 * <p>
 * Created by yfwz100 on 16/5/21.
 */
public class Main {

    private static int[] getLISA(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = 1;
            for (int j = 0; j < i; j++)
                if (arr[i] > arr[j] && res[i] < (res[j] + 1))
                    res[i] = res[j] + 1;
        }
        return res;
    }

    private static int[] getLDSA(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            res[i] = 1;
            for (int j = arr.length - 1; j > i; j--)
                if (arr[i] > arr[j] && res[i] < (res[j] + 1))
                    res[i] = res[j] + 1;
        }
        return res;
    }

    private static int solve(int[] heights) {
        int t = 0, index = 0;
        int[] lisa = getLISA(heights);
        int[] ldsa = getLDSA(heights);
        for (int i = 0; i < heights.length; i++) {
            if (t < lisa[i] + ldsa[i]) {
                t = lisa[i] + ldsa[i];
                index = i;
            }
        }
        return heights.length - lisa[index] - ldsa[index] + 1;
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int N = sc.nextInt();
            int[] heights = new int[N];
            for (int i = 0; i < heights.length; i++) {
                heights[i] = sc.nextInt();
            }
            System.out.println(solve(heights));
        }
    }
}
