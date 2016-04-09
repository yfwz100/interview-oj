package netease.p201603.p1;

import java.util.*;

/**
 * 题目: 电子数字
 *
 * 描述:
 * 电子数字在生活中很常见，而许多的电子数字是由LED数码管制作而成。数字LED数码管一般由7个发光二极管封装在一起，组成'8'字型，引线在内部连接完成。如下图所示，我们可以对每个发光管进行编码从1到7。而数字0到数字9可以由这七根发光管的亮暗来表示。
 * 假设我们现在有从左到右排列好的K个LED数码管，并且我们已知每个数码管当前有哪些编号的二极管是亮着的，另外剩余的二极管由于某些原因，我们并不清楚它们的亮暗情况。由于已经有部分二极管是确定亮着的，所以每个LED数码管能表示的数字范围会有所缩小，譬如假设1号二极管已经确定是亮着的状态，那么这个LED数码管就不能表示数字1和4。
 *
 * 我们想知道的是，给定一个数N，在这K个LED数码管的当前亮暗的状态下，所有可能表示的数中，比N小的数有多少个。
 *
 * 注意，前导0是必须的，假设有4个数码管的话，'0000'表示0，'0123'表示123，即每个数的表示方法唯一。
 *
 * 输入:
 * 每个输入数据包含多个测试点。
 *
 * 第一行为测试点的个数 S ≤ 100。之后是 S 个测试点的数据。测试点之间无空行。
 *
 * 每个测试点的第一行为 K(1 ≤ K ≤ 5)和N(0 ≤ N ≤ 109)。之后是K行，每行表示对应数码管已点亮的二极管的情况。每行至少包含一个数字，表示对应点亮的二极管的编号，即每个数码管至少有一根二极管是点亮的。二极管编号的范围保证在1到7之间，且每行无重复编号。
 *
 * 注意表示数码管点亮情况的每行数字之间以及行首行末之间可能存在冗余空格，每行的字符总长度不超过100。
 *
 * 输出:
 * 对于每个测试点，对应的结果输出一行，表示这K个数码管在当前状态下，所有可能表示的数中，比N小的数有多少个。
 *
 * Created by yfwz100 on 16/3/18.
 */
public class Main {

    private static int check(ArrayList<HashSet<Integer>> candidatesList, int depth, int value, int n) {
        if (depth < candidatesList.size()) {
            int ret = 0;
            HashSet<Integer> candidates = candidatesList.get(depth);
            for (int c : candidates) {
                int s = value * 10 + c;
                if (s < n) {
                    ret += check(candidatesList, depth + 1, s, n);
                }
            }
            return ret;
        } else {
            return value < n ? 1 : 0;
        }
    }

    private static int check(ArrayList<HashSet<Integer>> candidatesList, int n) {
        return check(candidatesList, 0, 0, n);
    }

    public static void main(String... args) {
        ArrayList<HashSet<Integer>> numbers = new ArrayList<>();
        numbers.add(new HashSet<>(Arrays.asList(2, 3, 5, 6, 7, 8, 9, 0)));
        numbers.add(new HashSet<>(Arrays.asList(4, 5, 6, 8, 9, 0)));
        numbers.add(new HashSet<>(Arrays.asList(2, 3, 4, 7, 8, 9, 0)));
        numbers.add(new HashSet<>(Arrays.asList(2, 3, 4, 5, 6, 8, 9)));
        numbers.add(new HashSet<>(Arrays.asList(2, 6, 8, 0)));
        numbers.add(new HashSet<>(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 0)));
        numbers.add(new HashSet<>(Arrays.asList(2, 3, 5, 6, 8, 9, 0)));

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int cases = sc.nextInt();
            for (int i = 0; i < cases; i++) {
                int k = sc.nextInt();
                int n = sc.nextInt();
                ArrayList<HashSet<Integer>> candidatesList = new ArrayList<>();
                sc.nextLine();
                for (int j = 0; j < k; j++) {
                    String line = sc.nextLine();
                    if (!line.isEmpty()) {
                        HashSet<Integer> candidates = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
                        for (String s : line.split("\\s+")) {
                            try {
                                int l = Integer.parseInt(s);
                                candidates.retainAll(numbers.get(l - 1));
                            } catch (NumberFormatException ignored) {
                            }
                        }
                        candidatesList.add(candidates);
                    }
                }
                System.out.println(check(candidatesList, n));
            }
        }
    }
}
