package huawei.p6;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by yfwz100 on 16/4/1.
 */
public class Main {

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            BigInteger b = new BigInteger(line.substring(2), 16);
            System.out.println(b);
        }
    }
}
