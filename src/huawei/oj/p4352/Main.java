package huawei.oj.p4352;

import java.util.*;

/**
 * 4352
 *
 * Created by yfwz100 on 16/5/1.
 */
public class Main {

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            Set<Character> set = new HashSet<>();
            for (char c:s.toCharArray()) {
                set.add(c);
            }
            System.out.println(set.size());
        }
    }
}
