package huawei.oj.p4282;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 报数
 *
 * Created by yfwz100 on 16/5/24.
 */
public class Main {

    private static int solve(int n) {
        boolean[] arr = new boolean[n];
        Arrays.fill(arr, true);
        int idx = -1;
        while (--n > 0) {
            int counter = 0;
            while (counter < 3) {
                if (arr[idx=(idx+1)%arr.length]) {
                    counter++;
                }
            }
            arr[idx] = false;
        }
        for (int i=0;i<arr.length; i++) {
            if (arr[i]) {
                return i+1;
            }
        }
        return -1;
    }

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            System.out.println(solve(n));
        }
    }
}
