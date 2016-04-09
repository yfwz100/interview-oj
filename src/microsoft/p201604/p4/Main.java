package microsoft.p201604.p4;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * At the beginning there is nothing but plane ground in the world. The ground consists of all the cubes of z=0. Little
 * Hi needs to build everything by placing cubes one by one following the rules:
 * <p>
 * 1. The newly placed cube must be adjacent to the ground or a previously placed cube. Two cubes are adjacent if and
 * only if they share a same face.
 * <p>
 * 2. The newly placed cube must be accessible from outside which means by moving in 6 directions(up, down, left, right,
 * forward, backward) there is a path from a very far place - say (1000, 1000, 1000) in this problem - to this cube
 * without passing through ground or other cubes.
 * <p>
 * Given a sequence of cubes Little Hi wants to know if he can build the world by placing the cubes in such order.
 * <p>
 * Created by yfwz100 on 16/4/6.
 */
public class Main {

    private static class Cube implements Comparable<Cube> {
        final int x, y, z, dist;

        private Cube(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.dist = x * 13 + y * 39 + z * 19;
        }

        Cube left() {
            return Cube.create(x - 1, y, z);
        }

        Cube right() {
            return Cube.create(x + 1, y, z);
        }

        Cube forward() {
            return Cube.create(x, y - 1, z);
        }

        Cube backward() {
            return Cube.create(x, y + 1, z);
        }

        Cube upper() {
            return Cube.create(x, y, z + 1);
        }

        Cube lower() {
            return Cube.create(x, y, z - 1);
        }

        static Cube create(int x, int y, int z) {
            return new Cube(x, y, z);
        }

        private static int compareTo(int a, int b, int o) {
            int cmp = a - b;
            if (cmp == 0) {
                return o;
            } else {
                return cmp > 0 ? 1 : -1;
            }
        }

        @Override
        public int compareTo(Cube o) {
            return compareTo(x, o.x, compareTo(y, o.y, compareTo(z, o.z, 0)));
        }

        @Override
        public int hashCode() {
            return dist;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Cube) {
                Cube o = (Cube) obj;
                return x == o.x && y == o.y && z == o.z;
            }
            return false;
        }
    }

    interface Maze {
        boolean place(int x, int y, int z);
    }

    private static class MapMaze implements Maze {

        Set<Cube> cubes = new HashSet<>();

        int left, right;
        int forward, backward;
        int upper;

        boolean reachable(int x, int y, int z, Set<Cube> visited) {
            Cube cube = Cube.create(x, y, z);
            if (visited.contains(cube) || cubes.contains(cube) || z <= 0) {
                return false;
            } else if ((z >= upper) || (x <= left || x >= right) || (y <= forward || y >= backward)) {
                return true;
            } else {
                visited.add(cube);
                return (
                        reachable(x, y, z - 1, visited) || reachable(x, y, z + 1, visited)
                                || reachable(x - 1, y, z, visited) || reachable(x + 1, y, z, visited)
                                || reachable(x, y - 1, z, visited) || reachable(x, y + 1, z, visited)
                );
            }
        }

        boolean reachable(int x, int y, int z) {
            Set<Cube> visited = new HashSet<>();
            Stack<Cube> unvisited = new Stack<>();
            unvisited.add(Cube.create(x, y, z));
            while (!unvisited.isEmpty()) {
                Cube current = unvisited.pop();
                if (!visited.contains(current)) {
                    if (!cubes.contains(current) && current.z > 0) {
                        if ((current.z >= upper) || (current.x <= left || current.x >= right)
                                || (current.y <= forward || current.y >= backward)) {
                            return true;
                        } else {
                            unvisited.add(Cube.create(current.x, current.y, current.z - 1));
                            unvisited.add(Cube.create(current.x - 1, current.y, current.z));
                            unvisited.add(Cube.create(current.x + 1, current.y, current.z));
                            unvisited.add(Cube.create(current.x, current.y - 1, current.z));
                            unvisited.add(Cube.create(current.x, current.y + 1, current.z));
                            unvisited.add(Cube.create(current.x, current.y, current.z + 1));
                        }
                    }
                    visited.add(current);
                }
            }
            return false;
        }

        boolean attachable(int x, int y, int z) {
            Cube cube = Cube.create(x, y, z);
            if (z == 1) {
//                return !cubes.test(cube);
                return true;
            } else {
                return cubes.contains(cube.left()) || cubes.contains(cube.right())
                        || cubes.contains(cube.forward()) || cubes.contains(cube.backward())
                        || cubes.contains(cube.upper()) || cubes.contains(cube.lower());
            }
        }

        public boolean place(int x, int y, int z) {
            if (attachable(x, y, z) && reachable(x, y, z)) {
                left = Math.min(x, left);
                right = Math.max(x, right);
                forward = Math.min(y, forward);
                backward = Math.max(y, backward);
                upper = Math.max(z, upper);
                cubes.add(Cube.create(x, y, z));
                return true;
            } else {
                return false;
            }
        }
    }

    private static class ArrayMaze implements Maze {

        int[][][] cubes = new int[101][101][101];

        int left, right;
        int forward, backward;
        int upper;

        boolean reachable(int x, int y, int z) {
            int[][][] visited = new int[101][101][101];
            Stack<Integer> uxs = new Stack<>(), uys = new Stack<>(), uzs = new Stack<>();
            uxs.add(x); uys.add(y); uzs.add(z);
            while (!uxs.isEmpty()) {
                int cx = uxs.pop(), cy = uys.pop(), cz = uzs.pop();
                if (!test(visited, cx, cy, cz)) {
                    if (!test(cx, cy, cz) && cz > 0) {
                        if ((cz >= upper) || (cx <= left || cx >= right)
                                || (cy <= forward || cy >= backward)) {
                            return true;
                        } else {
                            uxs.add(cx); uys.add(cy); uzs.add(cz - 1);
                            uxs.add(cx - 1); uys.add(cy); uzs.add(cz);
                            uxs.add(cx + 1); uys.add(cy); uzs.add(cz);
                            uxs.add(cx); uys.add(cy - 1); uzs.add(cz);
                            uxs.add(cx); uys.add(cy + 1); uzs.add(cz);
                            uxs.add(cx); uys.add(cy); uzs.add(cz + 1);
                        }
                    }
                    visited[x][y][z] = 1;
                }
            }
            return false;
        }

        boolean test(int[][][] arr, int x, int y, int z) {
            if (x > 0 && y > 0 && z > 0 && x < 101 && y < 101 && z < 101) {
                return arr[x][y][z] > 0;
            }
            return true;
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
                left = Math.min(x, left);
                right = Math.max(x, right);
                forward = Math.min(y, forward);
                backward = Math.max(y, backward);
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
