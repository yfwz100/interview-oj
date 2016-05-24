package huawei.oj.p4324;

/**
 * 点在三角形内?
 * <p>
 * Created by yfwz100 on 16/5/17.
 */
public class Main {

    public static class POINT {
        int x;
        int y;

        public POINT(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double cross(POINT a, POINT b) {
        return a.x * b.y - a.y * b.x;
    }

    private static double dot(POINT a, POINT b) {
        return a.x * b.x + a.y * b.y;
    }

    private static boolean sameSide(POINT a, POINT b, POINT c, POINT p) {
        POINT ab = new POINT(b.x - a.x, b.y - a.y);
        POINT ac = new POINT(c.x - a.x, c.y - a.y);
        POINT ap = new POINT(p.x - a.x, p.y - a.y);

        double v1 = cross(ab, ac);
        double v2 = cross(ab, ap);

        return v1 * v2 >= 0;
    }

    public static boolean isInTriangle(POINT A, POINT B, POINT C, POINT P) {
        return sameSide(A, B, C, P) && sameSide(B, C, A, P) && sameSide(C, A, B, P);
    }

    public static void main(String... args) {
        System.out.println(isInTriangle(new POINT(0, 0), new POINT(1, 10), new POINT(2, 0), new POINT(1, 5)));
    }
}
