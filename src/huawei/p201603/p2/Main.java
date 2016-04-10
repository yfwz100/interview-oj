package huawei.p201603.p2;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 题目: 取石子游戏(斐波那契数列博弈)
 *
 * 1堆石子有n个,两人轮流取.先取者第1次可以取任意多个，但不能全部取完.以后每次取的石子数不能超过上次取子数的2倍。取完者胜.
 * 先取者负输出"Second win".先取者胜输出"First win".
 *
 * Created by yfwz100 on 16/3/19.
 */
public class Main {

    public static void main(String ... args) {
        HashSet<Integer> fab = new HashSet<>();
        int a = 1, b = 1;
        for(int i=0; i<46; i++) {
            int t = b;
            b = a + b;
            a = t;
            fab.add(b);
        }
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            if (fab.contains(n)) {
                System.out.println("Second win");
            } else {
                System.out.println("First win");
            }
        }
    }
}
