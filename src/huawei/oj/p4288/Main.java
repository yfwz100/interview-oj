package huawei.oj.p4288;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 删除出现次数最少的字符串。
 *
 * Created by yfwz100 on 16/5/19.
 */
public class Main {

    private static String freqLess(String s) {
        Map<Character, Integer> counterMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer counter = counterMap.get(s.charAt(i));
            if (counter == null) {
                counterMap.put(s.charAt(i), 1);
            } else {
                counterMap.put(s.charAt(i), counter + 1);
            }
        }
        int freq = Collections.min(counterMap.values());
        for (Map.Entry<Character, Integer> c:counterMap.entrySet()) {
            if (c.getValue() == freq) {
                s = s.replace(c.getKey().toString(), "");
            }
        }
        return s;
    }

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String text = sc.next();
            System.out.println(freqLess(text));
        }
    }
}
