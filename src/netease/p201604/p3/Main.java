package netease.p201604.p3;

import java.util.Scanner;

/**
 * 神奇的数
 * http://hihocoder.com/contest/ntest2016spring2/problem/3
 *
 * Created by yfwz100 on 16/4/21.
 */
public class Main {

    private static boolean check(long a) {
        boolean cond1 = false, cond2 = true;
        while (a > 0) {
            long t = a % 10;
            if (t == 2 || t == 3 || t == 5) {
                cond1 = true;
            }
            long tt = a % 100;
            if (tt == 18) {
                cond2 = false;
            }
            a /= 10;
        }
        return cond1 && cond2;
    }

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int S = sc.nextInt();
            for (int i = 0; i < S; i++) {
                long N = sc.nextLong(), M = sc.nextLong();
                long init = (long) Math.ceil((double)N / 7) * 7;
                long count = 0;
                for (long j = init; j <= M; j+=7) {
                    if (check(j)) {
                        count ++;
                    }
                }
                System.out.println(count);
            }
        }
    }
}
