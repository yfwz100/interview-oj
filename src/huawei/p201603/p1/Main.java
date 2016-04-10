package huawei.p201603.p1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 * 题目: 交错数列.
 * 给定一组数列, 序数为奇数的顺序排列, 序数为偶数的降序排列.
 *
 * Created by yfwz100 on 16/4/10.
 */
public class Main {

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Scanner lsc = new Scanner(line);
            ArrayList<Integer> list = new ArrayList<>();
            while (lsc.hasNextInt()) {
                list.add(lsc.nextInt());
            }
            for (int i = 0; i < list.size(); i+=2) {
                for (int j = i+2; j < list.size(); j+=2) {
                    if (list.get(i) > list.get(j)) {
                        Collections.swap(list, i, j);
                    }
                }
            }
            for (int i = 1; i < list.size(); i+=2) {
                for (int j = i+2; j < list.size(); j+=2) {
                    if (list.get(i) < list.get(j)) {
                        Collections.swap(list, i, j);
                    }
                }
            }
            for (Integer it : list) {
                System.out.print(it + " ");
            }
            System.out.println();
        }
    }
}
