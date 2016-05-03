package huawei.oj.p4311;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yfwz100 on 16/5/1.
 */
public class Main {

    /**
     * 小明是个马大哈，某天他到超市买了若干双筷子（n<20）
     *  筷子的长度不尽相同，他把全部筷子都放在购物袋里面拿回家，路上不小心漏了一根
     *  请你用程序帮他找出是漏掉的筷子是多长的。
     *
     *
     * @param chopsticks  剩下的筷子数组，如：1, 2, 3, 2, 1, 3, 2
     * @return int 漏掉的筷子长度，如上述输入返回：2（当输入的筷子数据异常时返回-1，如：找不到漏掉的筷子）
     *
     */
    public static int checkChopsticks(int[] chopsticks)
    {
        Set<Integer> missing = new HashSet<Integer>();
        for (int chopstick:chopsticks) {
            if (missing.contains(chopstick)) {
                missing.remove(chopstick);
            } else {
                missing.add(chopstick);
            }
        }
        return missing.isEmpty() ? -1 : missing.iterator().next();
    }

    public static void main(String ... args) {
        System.out.println(checkChopsticks(new int[] {1, 2, 3, 2, 1, 3, 2}));
    }
}
