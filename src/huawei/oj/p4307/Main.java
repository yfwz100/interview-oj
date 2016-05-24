package huawei.oj.p4307;

import java.util.Arrays;

/**
 * 渊子赛马
 * <p>
 * Created by yfwz100 on 16/5/11.
 */
public class Main {

    public static String isYuanziWin(int num, int[] speedYz, int[] speedOp) {
        int i = 0, j = 0, k = 0;
        Arrays.sort(speedYz);
        Arrays.sort(speedOp);
        while (i < speedOp.length) {
            while (j < speedYz.length) {
                if (speedYz[j++] > speedOp[i]) {
                    k++;
                    break;
                }
            }
            i++;
        }
        return k > num / 2 ? "YES" : "NO";
    }

    public static void main(String... args) {
        System.out.println(isYuanziWin(4, new int[]{2, 2, 1, 2}, new int[]{2, 2, 3, 1}));
    }
}
