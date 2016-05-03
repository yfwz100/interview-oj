package huawei.oj.p4284;

import java.util.Scanner;

/**
 * 求整数的Root:给定正整数,求每位数字之和;如果和不是一位数,则重复;
 *
 * Created by yfwz100 on 16/5/3.
 */
public class Main {

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n > 0) {
                int s;
                do {
                    s = 0;
                    while (n > 0) {
                        int r = n % 10;
                        s += r;
                        n /= 10;
                    }
                } while ((n = s) >= 10);
                System.out.println(s);
            } else {
                System.out.println(-1);
            }
        }
    }
}
