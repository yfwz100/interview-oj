package huawei.p4;

import java.util.Scanner;

/**
 * 有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”答案是5瓶，方法如下：先用
 * 9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。然后你让老板先借给你一瓶汽水，喝掉
 * 这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？
 *
 * Created by yfwz100 on 16/3/12.
 */
public class Main {

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int size = sc.nextInt();
            if (size > 0) {
                int ret = 0;
                int a = size;
                while (a > 2) {
                    int c = a / 3;
                    a = a % 3 + c;
                    ret += c;
                }
                ret += a == 2? 1 : 0;
                System.out.println(ret);
            }
        }
    }
}
