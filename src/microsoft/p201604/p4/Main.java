package microsoft.p201604.p4;

import java.util.*;

/**
 * At the beginning there is nothing but plane ground in the world. The ground consists of all the cubes of z=0. Little
 * Hi needs to build everything by placing cubes one by one following the rules:
 * <p>
 * 1. The newly placed cube must be adjacent to the ground or a previously placed cube. Two cubes are adjacent if and
 * only if they share a same face.
 * <p>
 * 2. The newly placed cube must be accessible from outside which means by moving in 6 directions(up, down, left, right,
 * forward, far) there is a path from a very far place - say (1000, 1000, 1000) in this problem - to this cube
 * without passing through ground or other cubes.
 * <p>
 * Given a sequence of cubes Little Hi wants to know if he can build the world by placing the cubes in such order.
 * <p>
 * Created by yfwz100 on 16/4/6.
 */
public class Main {

    interface Maze {
        boolean place(int x, int y, int z);
    }

    private static class ArrayMaze implements Maze {

        int[][][] cubes = new int[101][101][101];

        int right;
        int far;
        int upper;

        boolean reachable(int x, int y, int z) {
            // bfs
            Set<Integer> visited = new HashSet<>();
            Deque<Integer> uxs = new ArrayDeque<>(), uys = new ArrayDeque<>(), uzs = new ArrayDeque<>();
            uxs.add(x); uys.add(y); uzs.add(z);
            while (!uxs.isEmpty()) {
                int cx = uxs.poll(), cy = uys.poll(), cz = uzs.poll();
                if (cx < 0 || cy < 0 || cx > right || cy > far || cz > upper) {
                    return true;
                }
                int idx = cx * 10000 + cy * 100 + cz;
                if (!visited.contains(idx) && cubes[cx][cy][cz] == 0) {
                    if (cz > 1) {
                        uxs.add(cx); uys.add(cy); uzs.add(cz - 1);
                    }
                    uxs.add(cx - 1); uys.add(cy); uzs.add(cz);
                    uxs.add(cx + 1); uys.add(cy); uzs.add(cz);
                    uxs.add(cx); uys.add(cy - 1); uzs.add(cz);
                    uxs.add(cx); uys.add(cy + 1); uzs.add(cz);
                    uxs.add(cx); uys.add(cy); uzs.add(cz + 1);

                    visited.add(idx);
                }
            }
            return false;
        }

        boolean test(int x, int y, int z) {
            return x > 0 && y > 0 && z > 0 && cubes[x][y][z] > 0;
        }

        boolean attachable(int x, int y, int z) {
            return z == 1
                    || test(x - 1, y, z) || test(x + 1, y, z)
                    || test(x, y + 1, z) || test(x, y - 1, z)
                    || test(x, y, z - 1) || test(x, y, z + 1);
        }

        public boolean place(int x, int y, int z) {
            if (attachable(x, y, z) && reachable(x, y, z)) {
                right = Math.max(x, right);
                far = Math.max(y, far);
                upper = Math.max(z, upper);
                cubes[x][y][z] = 1;
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int cases = sc.nextInt();
            for (int i = 0; i < cases; i++) {
                int voxels = sc.nextInt();
                Maze maze = new ArrayMaze();
                boolean flag = true;
                int j;
                for (j = 0; j < voxels; j++) {
                    int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
                    if (!maze.place(x, y, z)) {
                        flag = false;
//                        System.out.println(x + "," + y + "," + z);
                        break;
                    }
                }
                if (flag) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
                for (; j < voxels-1; j++) {
                    sc.nextInt();
                    sc.nextInt();
                    sc.nextInt();
                }
            }
        }
    }
}
