package huawei.oj.p4303;

/**
 * 判断字符串类型。
 * <p>
 * Created by yfwz100 on 16/5/19.
 */
public class Main {

    /**
     * 定义了如下三种字符串类型：
     *
     * Slump是一串字符，具有如下性质：
     *  1.以'D'或'E'开始
     *  2.第一个字符后由1个或多个'F'
     *  3.之后跟着一个Slump或'G'，至此一个Slump结束。
     *  4.其他的都不是Slump
     *
     * 例如，DFFEFFFG是Slump
     *
     * Slimp是一串字符，具有如下性质：
     *  1.第一个字符是'A'
     *  2.如果Slimp只有2个字符，则第二个字符是'H'
     *  3.如果大于2个字符，则可能有2种形式
     *      a) 'A'后接一个'B'再接一个Slimp再接一个'C'
     *      b) 'A'后接一个Slump再接一个'C'
     *  4.其他的都不是Slimp
     *
     * Slurpy是一个Slimp后接一个Slump组成
     *
     * 请编写程序判断一个字符串是否为Slurpy
     *
     * @param str the string to test.
     * @return 1 for success, 0 otherwise.
     */
    public static int isSlurpy(String str) {
        int s = slimp(str);
        if (s > 0) {
            String s2 = str.substring(s);
            return slump(s2) == s2.length() ? 1 : 0;
        } else {
            return 0;
        }
    }

    /**
     * Slimp是一串字符，具有如下性质：
     *  1.第一个字符是'A'
     *  2.如果Slimp只有2个字符，则第二个字符是'H'
     *  3.如果大于2个字符，则可能有2种形式
     *      a) 'A'后接一个'B'再接一个Slimp再接一个'C'
     *      b) 'A'后接一个Slump再接一个'C'
     *  4.其他的都不是Slimp
     *
     * @param str the string to test.
     * @return the end index.
     */
    private static int slimp(String str) {
        if (str.charAt(0) == 'A') {
            if (str.charAt(1) == 'H') {
                return 2;
            } else if (str.charAt(1) == 'B') {
                String sub = str.substring(2);
                int i = slimp(sub);
                if (i > 0 && sub.charAt(i) == 'C') {
                    return 3 + i;
                }
            } else {
                String sub = str.substring(1);
                int i = slump(sub);
                if (i > 0 && sub.charAt(i) == 'C') {
                    return 2 + i;
                }
            }
        }
        return 0;
    }

    /**
     * Slump是一串字符，具有如下性质：
     *  1.以'D'或'E'开始
     *  2.第一个字符后由1个或多个'F'
     *  3.之后跟着一个Slump或'G'，至此一个Slump结束。
     *  4.其他的都不是Slump
     *
     * @param str the string to test.
     * @return the end index.
     */
    private static int slump(String str) {
        try {
            if (str.charAt(0) == 'D' || str.charAt(0) == 'E') {
                int i = 1;
                while (str.charAt(i) == 'F') {
                    i++;
                }
                if (str.charAt(i) == 'G') {
                    return i + 1;
                } else {
                    String sub = str.substring(i);
                    int t = slump(sub);
                    if (t > 0) {
                        return i + t;
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException ignored) {
        }
        return 0;
    }

    public static void main(String... args) {
        System.out.println(isSlurpy("ABABABADFGCCCCDFFEFFFEFFG"));
    }
}
