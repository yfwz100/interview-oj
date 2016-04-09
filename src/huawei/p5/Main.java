package huawei.p5;

import java.util.Scanner;

/**
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 *
 * Created by yfwz100 on 16/3/12.
 */
public class Main {

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            while (line.length() > 8) {
                String sub = line.substring(0, 8);
                System.out.println(sub);
                line = line.substring(8);
            }
            System.out.print(line);
            for (int i=line.length(); i<8; i++) {
                System.out.print(0);
            }
            System.out.println();
        }
    }
}
