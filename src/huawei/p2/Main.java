package huawei.p2;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个有字母和数字以及空格组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 *
 * Created by yfwz100 on 16/3/12.
 */
public class Main {

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String a = sc.nextLine();
            char b = sc.nextLine().charAt(0);
            int ret = 0;
            for (int i=0; i<a.length(); i++) {
                if (b == a.charAt(i)) {
                    ret += 1;
                }
            }
            System.out.println(ret);
        }
    }
}
