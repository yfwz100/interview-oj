package huawei.p3;

import java.util.*;

/**
 * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤100），对于其中重复的数字，
 * 只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完
 * 成“去重”与“排序”的工作。
 *
 * Created by yfwz100 on 16/3/12.
 */
public class Main {

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int randn = sc.nextInt();
            Set<Integer> randnums = new HashSet<>();
            for (int i = 0; i < randn; i++) {
                randnums.add(sc.nextInt());
            }
            List<Integer> ret = new ArrayList<>(randnums);
            Collections.sort(ret);
            for (int i : ret) {
                System.out.println(i);
            }
        }
    }
}
