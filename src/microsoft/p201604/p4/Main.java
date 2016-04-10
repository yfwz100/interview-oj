package microsoft.p201604.p4;

import java.util.*;

/**
 * At the beginning there is nothing but plane ground in the world. The ground consists of all the cubes of z=0. Little
 * Hi needs to build everything by placing cubes one by one following the rules:
 * <p>
 * 1. The newly placed cube must be adjacent to the ground or a previously placed cube. Two cubes are adjacent if and
 * only if they share a same face.
 * <p>
 * 2. The newly placed cube must be accessible from outside which means by moving in 6 directions(up, down, minX, maxX,
 * forward, maxY) there is a path from a very maxY build - say (1000, 1000, 1000) in this problem - to this cube
 * without passing through ground or other cubes.
 * <p>
 * Given a sequence of cubes Little Hi wants to know if he can build the world by placing the cubes in such order.
 * <p>
 * Created by yfwz100 on 16/4/6.
 */
public class Main {

    /**
     * The maze interface.
     */
    interface Maze {
        /**
         * for building the maze.
         *
         * @param x the x coordinate.
         * @param y the y coordinate.
         * @param z the z coordinate.
         * @return true if it can be built.
         */
        boolean build(int x, int y, int z);

        /**
         * To destroy the building.
         *
         * @return true if successful.
         */
        boolean unbuild();
    }

    private static class ArrayMaze implements Maze {
        private final static int SIZE = 101;
        private final static int[][] DIR = {
            {-1, 0, 0}, {0, -1, 0}, {0, 0, -1},
            {1, 0, 0}, {0, 1, 0}, {0, 0, 1}
        };

        int[][][] cubes = new int[SIZE][SIZE][SIZE];
        int[][][] visited = new int[SIZE][SIZE][SIZE];

        int maxX, minX = Integer.MAX_VALUE;
        int maxY, minY = Integer.MAX_VALUE;
        int maxZ, minZ = Integer.MAX_VALUE;

        private class Coord {
            int x, y, z;

            Coord(int x, int y, int z) {
                this.x = x;
                this.y = y;
                this.z = z;
            }
        }

        private Deque<Coord> points = new ArrayDeque<>();

        ArrayMaze() {
            // fill the solid ground.
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    cubes[i][j][0] = 1;
                }
            }
        }

        private boolean attachable(int x, int y, int z) {
            for (int[] dir:DIR) {
                if (cubes[x + dir[0]][y + dir[1]][z + dir[2]] > 0) {
                    return true;
                }
            }
            return false;
        }

        public boolean build(int x, int y, int z) {
            if (cubes[x][y][z] == 0 && attachable(x, y, z)) {
                // visit the cube.
                cubes[x][y][z] = 1;
                // set the bounds
                minX = Math.min(x, minX);
                maxX = Math.max(x, maxX);
                minY = Math.min(y, minY);
                maxY = Math.max(y, maxY);
                minZ = Math.min(z, minZ);
                maxZ = Math.max(z, maxZ);
                // push back for destructing.
                points.add(new Coord(x, y, z));
                return true;
            } else {
                return false;
            }
        }

        private boolean bfs(Coord p, int n) {
            boolean ret = false;
            Queue<Coord> q = new ArrayDeque<>();
            q.add(p);
            visited[p.x][p.y][p.z] = n;
            while (!q.isEmpty()) {
                Coord c = q.poll();
                if (c.x < minX || c.y < minY || c.z < minZ || c.x > maxX || c.y > maxY || c.z > maxZ) {
                    ret = true;
                    break;
                }
                for (int[] dir : DIR) {
                    int nx = c.x + dir[0];
                    int ny = c.y + dir[1];
                    int nz = c.z + dir[2];
                    if (cubes[nx][ny][nz] == 0 && n != visited[nx][ny][nz]) {
                        if (visited[nx][ny][nz] > 0) {
                            ret = true;
                            break;
                        } else {
                            visited[nx][ny][nz] = n;
                            q.add(new Coord(nx, ny, nz));
                        }
                    }
                }
            }
            return ret;
        }

        @Override
        public boolean unbuild() {
            while (!points.isEmpty()) {
                Coord p = points.pollLast();
                // unbuild.
                cubes[p.x][p.y][p.z] = 0;
                if (!bfs(p, points.size() + 1)) {
                    return false;
                }
            }
            return true;
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
                for (int j = 0; j < voxels; j++) {
                    int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
                    if (flag && !maze.build(x, y, z)) {
                        flag = false;
                    }
                }
                if (flag && maze.unbuild()) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
        }
    }
}
