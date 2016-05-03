package huawei.oj.p4313;

/**
 * Created by yfwz100 on 16/5/1.
 */
public class Main {

    /**
     *
     * @param s 输入字符串
     *
     * @description：
     *     判断字符串是否是有效数字，是返回0，不是返回-1
     *
     * @example:
     *     123.456 是有效数字，返回0
     *     123a 不是有效数字，返回-1
     *
     */
    public static int NumType(String s) {
//        boolean dot = false;
//        for (char c:s.toCharArray()) {
//            if (c == '.') {
//                if (dot) {
//                    return -1;
//                } else {
//                    dot = true;
//                }
//            } else if (c < '0' || c > '9') {
//                return -1;
//            }
//        }
//        return 0;
        try {
            Double.parseDouble(s);
            return 0;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String ... args) {
        System.out.println(NumType("123.459"));
    }
}
