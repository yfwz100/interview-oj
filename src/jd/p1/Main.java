package jd.p1;

import java.util.Scanner;

/**
 * 第k个幸运数
 * <p>
 * 4和7是两个幸运数字，我们定义，十进制表示中，每一位只有4和7两个数的正数都是幸运数字。前几个幸运数字为
 * <p>
 * 4、7、44、47、74、77、444、447……
 * <p>
 * 现在输入一个数字k，输出第k个幸运数。
 * <p>
 * Created by yfwz100 on 16/9/5.
 */
public class Main {

    /**
     * 计算第k个幸运数：先算出当前层，然后换算为位数。然后根据当前数值和位数初始值的差值，使用二进制的方法计算出位数。
     *
     * @param k 第k位
     * @return 第k个幸运数
     */
    private static String solve(int k) {
        int level = (int) (Math.log(k + 1) / Math.log(2));
        System.out.println("l: " + level);
        int remain = (int) (k - Math.pow(2, level) + 1);
        StringBuilder sb = new StringBuilder();
        while (level-- > 0) {
            sb.append(remain % 2 == 1 ? 7 : 4);
            remain /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            System.out.println(solve(sc.nextInt()));
        }
    }
}
