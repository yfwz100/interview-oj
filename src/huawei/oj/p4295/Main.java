package huawei.oj.p4295;

import java.util.Scanner;

/**
 * 找零钱.
 * <p>
 * Created by yfwz100 on 16/5/17.
 */
public class Main {

    private static final int[] coins = {100, 50, 20, 10, 5, 2, 1};

    private static int[][][] cache = new int[250][100][coins.length];

    static {
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[i].length; j++) {
                for (int k = 0; k < coins.length; k++) {
                    cache[i][j][k] = -1;
                }
            }
        }
    }

    private static int changes(int n, int remain, int m) {
        if (n == 0) {
            return remain >= 0 ? 1: 0;
        } else if(n > 0 && remain > 0) {
            if (cache[n - 1][remain - 1][m] > -1) {
                return cache[n - 1][remain - 1][m];
            } else {
                int count = 0;
                for (int i = m; i < coins.length; i++) {
                    int c = coins[i];
                    count += changes(n - c, remain - 1, i);
                }
                cache[n - 1][remain - 1][m] = count;
                return count;
            }
        } else {
            return 0;
        }
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n > 0) {
                System.out.println(changes(n, 100, 0));
            } else {
                break;
            }
        }
    }
}
