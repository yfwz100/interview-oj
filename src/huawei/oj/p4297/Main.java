package huawei.oj.p4297;

import java.util.Scanner;

/**
 * Created by yfwz100 on 16/5/3.
 */
public class Main {

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int r = 0;
            int digits = 1;
            while (n > 0) {
                int t = n / 10, d = n % 10;
                digits += t > 0 ? 1 : 0;
                r = r * 10 + d;
                n = t;
            }
            System.out.println(digits + " " + r);
        }
    }
}
