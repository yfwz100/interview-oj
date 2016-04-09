package netease.p201603.p3;

import java.util.*;

/**
 * 描述:
 * 小王最近在开发一种新的游戏引擎，但是最近遇到了性能瓶颈。于是他打算从最基本的画线功能开始分析优化。画线其实就是调用一次drawline命令，根据给出的两端坐标，在屏幕画出对应的线段。但是小王发现，很多的drawline其实可以合并在一起，譬如下图中的线段(2,3)-(4,5)和线段(3,4)-(6,7)，其实可以合并为一次drawline命令，直接画出线段(2,3)-(6,7)。当然有些线段是无法合并的，如线段(-3,8)-(1,8)和线段(3,8)-(6,8)，就必须调用两次drawline命令。
 * <p/>
 * 输入:
 * 每个输入数据包含多个测试点。
 * <p/>
 * 第一行为测试点的个数 S ≤ 10。之后是 S 个测试点的数据。
 * <p/>
 * 每个测试点的第一行为 N(N ≤ 10^5)。之后是 N 行，每行包含4个整数:x0, y0, x1, y1，表示线段(x0,y0)-(x1,y1)，坐标的范围在[-10^8, 10^8]，保证线段的长度大于0。
 * <p/>
 * 输出:
 * 对于每个测试点，对应的结果输出一行，表示最少用多少次指令即可完成所有的画线。
 * <p/>
 * Created by yfwz100 on 16/3/18.
 */
public class Main {

    private static class Line {
        int x0, y0;
        int x1, y1;

        public Line(int x0, int y0, int x1, int y1) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
        }

        public int maxX() {
            return Math.max(x0, x1);
        }

        public int minX() {
            return Math.min(x0, x1);
        }

        public int maxY() {
            return Math.max(y0, y1);
        }

        public int minY() {
            return Math.min(y0, y1);
        }

        public boolean mergable(Line l) {
            return this.maxX() >= l.minX() && this.maxY() >= l.minY()
                    && l.maxX() >= this.minX() && l.maxY() >= this.minY();
        }

        public int k() {
            int dy = y0 - y1;
            if (dy == 0) {
                return Integer.MAX_VALUE;
            } else {
                int dx = x0 - x1;
                return dx / dy;
            }
        }
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, ArrayList<Line>> lineMap = new HashMap<>();
        while (sc.hasNextInt()) {
            int cases = sc.nextInt();
            for (int i = 0; i < cases; i++) {
                int points = sc.nextInt();
                int ret = 0;
                for (int j = 0; j < points; j++) {
                    Line l = new Line(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
                    ArrayList<Line> a = lineMap.get(l.k());
                    if (a == null) {
                        a = new ArrayList<>();
                        a.add(l);
                        lineMap.put(l.k(), a);
                        ret += 1;
                    } else {
                        boolean merged = false;
                        for (Line line : a) {
                            merged |= line.mergable(l);
                        }
                        if (!merged) {
                            ret += 1;
                            a.add(l);
                        }
                    }
                }
                System.out.println(ret);
            }
        }
    }
}
