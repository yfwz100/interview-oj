package netease.p201603.p2;

import java.util.*;

/**
 * 题目描述:
 * 在网易游戏的日常工作中，C++ 是一门常用的语言。面对众多的 C++ 代码，等待源文件编译的漫长时间是个令人糟心的时刻,一直以来大家对此怨声载道。终于有一天，大家找到了你，一位优秀的程序员，请你来帮忙分析一下编译速度的瓶颈。
 * <p/>
 * 经过一番调查和研究，你发现一些源代码之间是有依赖关系的。例如，某个源文件 a.cpp 编译链接生成了动态链接库 a.dll，而 b.cpp 编译链接生成的 b.dll 依赖于 a.dll。这个时候，必须等待 a.dll 生成之后才能生成 b.dll。为了表达简单，我们这个时候称 b.cpp 依赖于 a.cpp。
 * <p/>
 * 网易游戏内部使用了一个分布式并行的编译平台，可以同时编译多个不互相依赖的文件，大大提高了源代码的编译速度。然而当某些依赖链很长的时候，这个编译平台也无能为力，只能按照依赖顺序一个一个完成编译，从而造成了很长的编译时间。
 * <p/>
 * 为了验证这个想法，你决定着手通过代码分析这些文件之间的编译顺序。已知这些文件的文件名，以及这些文件所依赖的其他文件，你需要编写一个程序，输出一个可行的编译所有源文件的编译顺序。如果有多种可行的序列，请输出所有文件名序列中字典序最小的那一个（序列 (a1, a2, ..., an) 字典序小于序列 (b1, b2, ..., bn)，当且仅当存在某个 i ，使得 ai 的字典序小于 bi，并且对于任意 j < i ，都有 aj = bj）。
 * <p/>
 * 输入:
 * 输入包含多组测试数据。
 * <p/>
 * 输入的第一行包含一个整数 T(T ≤ 100)，表示输入中一共包含有 T 组测试数据。
 * <p/>
 * 每组测试数据第一行是一个整数 N(N ≤ 1000)，表示一共有 N 个源代码文件。随后一共有 N 行数据，其中第 i(0 ≤ i < N) 行数据包含序号为 i 的源代码文件的依赖信息。每一行开头是一个字符串，表示这一个文件的文件名，随后一个整数 m(0 ≤ m ≤ N)，表示编译这个源文件之前需要先编译 m 个依赖文件。之后是 m 个整数 j0 ... jm-1，表示这 m 个依赖文件的序号(0 ≤ j < N) 。所有的文件名仅由小写字母、数字或“.”组成，并且不会超过 10 个字符。保证 n 个源代码文件的文件名互不相同。
 * <p/>
 * 输出:
 * 对于每一组输入，按照编译先后顺序输出一组可行的编译顺序，一行一个文件名。如果有多种可行的序列，请输出所有文件名序列中字典序最小的那一个。如果不存在可行的编译顺序，输出一行 ERROR。每组测试数据末尾输出一个空行。
 * <p/>
 * Created by yfwz100 on 16/3/18.
 */
public class Main {

    private static class Flag {
        boolean value = false;
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int cases = sc.nextInt();
            for (int i = 0; i < cases; i++) {
                int n = sc.nextInt();
                sc.nextLine();
                final ArrayList<String> files = new ArrayList<>();
                final ArrayList<ArrayList<Integer>> dependencies = new ArrayList<>();
                final Flag error = new Flag();
                for (int j = 0; j < n; j++) {
                    String file = sc.next();
                    files.add(file);
                    int deps = sc.nextInt();
                    ArrayList<Integer> dependency = new ArrayList<>();
                    for (int k = 0; k < deps; k++) {
                        dependency.add(sc.nextInt());
                    }
                    dependencies.add(dependency);
                }
                Collections.sort(files, new Comparator<String>() {
                    @Override
                    public int compare(String a, String b) {
                        int ai = files.indexOf(a);
                        int bi = files.indexOf(b);
                        if (dependencies.get(ai).contains(bi)) {
                            if (dependencies.get(bi).contains(ai)) {
                                error.value = true;
                            }
                            return 1;
                        } else if (dependencies.get(bi).contains(ai)) {
                            return -1;
                        } else {
                            return a.compareTo(b);
                        }
                    }
                });
                if (!error.value) {
                    for (String f : files) {
                        System.out.println(f);
                    }
                } else {
                    System.out.println("ERROR");
                }
                System.out.println();
            }
        }
    }
}
