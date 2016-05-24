package huawei.oj.p3633;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 扑克牌大小.
 *
 * Created by yfwz100 on 16/5/17.
 */
public class Main {

    private static final Map<String, Integer> weights = new HashMap<>();
    static {
        weights.put("3", 0);
        weights.put("4", 1);
        weights.put("5", 2);
        weights.put("6", 3);
        weights.put("7", 4);
        weights.put("8", 5);
        weights.put("9", 6);
        weights.put("10", 7);
        weights.put("J", 8);
        weights.put("Q", 9);
        weights.put("K", 10);
        weights.put("A", 11);
        weights.put("2", 12);
        weights.put("joker", 13);
        weights.put("JOKER", 14);
    }

    private static String bigger(String left, String right) {
        if (left.equals("joker JOKER")) {
            return left;
        } else if (right.equals("joker JOKER")) {
            return right;
        } else {
            String[] lefts = left.split(" ");
            String[] rights = right.split(" ");
            if (lefts.length == rights.length) {
                return weights.get(lefts[0]).compareTo(weights.get(rights[0])) > 0 ? left : right;
            } else {
                if (lefts.length == 4) {
                    return left;
                } else if (rights.length == 4) {
                    return right;
                } else {
                    return "ERROR";
                }
            }
        }
    }

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("-");
            System.out.println(bigger(parts[0], parts[1]));
        }
    }
}
