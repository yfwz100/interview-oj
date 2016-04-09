package microsoft.p201604.p1;

import java.util.Scanner;

/**
 * 测试用例:
 * <pre>
 * 1
 * 2 10 4 3
 * 10 10
 * </pre>
 *
 * Created by yfwz100 on 16/4/6.
 */
public class Main {

    private static int max(int... a) {
        int m = Integer.MIN_VALUE;
        for (int i : a) {
            if (i > m) {
                m = i;
            }
        }
        return m;
    }

    private static int min(int... a) {
        int m = Integer.MAX_VALUE;
        for (int i : a) {
            if (i < m) {
                m = i;
            }
        }
        return m;
    }

    private static int maxPages(int[] a, int w) {
        int lines = 0;
        for (int i : a) {
            lines += (int) Math.ceil((double) i / (double) w);
        }
        return lines;
    }

    private static int solve(int[] a, int P, double W, double H) {
        int S = min((int) W, (int) H, (int) Math.ceil(P * H / a.length));
        while (Math.ceil(maxPages(a, (int) Math.floor(W / S)) / Math.floor(H / S)) > P) {
//            S = (int) (W / (Math.floor(W / S) + 1));
            S--;
        }
        return S;
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int tasks = sc.nextInt();
            for (int t = 0; t < tasks; t++) {
                int N = sc.nextInt();
                int P = sc.nextInt();
                int W = sc.nextInt();
                int H = sc.nextInt();
                int[] a = new int[N];
                for (int l = 0; l < a.length; l++) {
                    a[l] = sc.nextInt();
                }
                int S = solve(a, P, W, H);
                System.out.println(S);
            }
        }
    }
}

