package worksap.p1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by yfwz100 on 16/3/22.
 */
public class Main {

    private static int mazeValue(int[][] maze) {
        int width = maze[0].length, height = maze.length;
        int[][] upVal = new int[height][width];
        upVal[0][width - 1] = maze[0][width - 1];
        for (int i = height - 2; i >= 0; i--) {
            upVal[i][width - 1] = maze[i + 1][width - 1];
            if (maze[i][width - 1] > 0 && upVal[i + 1][width - 1] > 0) {
                upVal[i][width - 1] += upVal[i + 1][width - 1];
            }
        }
        for (int i = width - 2; i >= 0; i--) {
            upVal[height - 1][i] = maze[height - 1][i];
            if (maze[height - 1][i] > 0 && upVal[height - 1][i + 1] > 0) {
                upVal[height - 1][i] += upVal[height - 1][i + 1];
            }
        }
        for (int i = width - 2; i >= 0; i--) {
            for (int j = height - 2; j >= 0; j--) {
                upVal[j][i] = maze[j][i];
                if (upVal[j][i] > 0) {
                    int v = Math.max(upVal[j - 1][i], upVal[j][i - 1]);
                    if (v > 0) {
                        upVal[j][i] = v;
                    }
                }
            }
        }
        int[][] downVal = new int[height][width];
        downVal[0][width - 1] = maze[0][width - 1];

//        int[][] val = new int[height][width];
//        for (int i = 0; i < width; i++) {
//            for (int j = height - 2; j >= 0; j--) {
//                val[j][i] = maze[j][i];
//                if (val[j][i] > 0) {
//                    int v = Math.max(val[j - 1][i], val[j][i - 1]);
//                    if (v > 0) {
//                        val[j][i] = v;
//                    }
//                }
//            }
//        }
        return maze[height - 1][0];
    }

    private enum Dir {
        RIGHT, UP, DOWN
    }

    private static int max(int... args) {
        int m = -1;
        for (int i : args) {
            if (m < i) {
                m = i;
            }
        }
        return m;
    }

    private static int findPath(int[][] maze, int x, int y, final Dir d, Set<Integer> stack) {
        System.out.println("(" + x + ", " + y + ")");
        if (!stack.contains(x + y * maze.length) && maze[y][x] >= 0) {
            stack.add(x + y * maze.length);
            Set<Integer> maxStack = Collections.emptySet();
            int val = -1;
            switch (d) {
                case RIGHT:
                    if (y > 0) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.add(x + y * maze.length);
                        int aVal = findPath(maze, x, y - 1, Dir.UP, aStack);
                        if (aVal > val) {
                            maxStack = aStack;
                            val = aVal;
                        }
                    }
                    if (y < maze.length - 1) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.add(x + y * maze.length);
                        int aVal = findPath(maze, x, y + 1, Dir.DOWN, aStack);
                        if (aVal > val) {
                            maxStack = aStack;
                            val = aVal;
                        }
                    }
                    if (x < maze[0].length - 1) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.add(x + y * maze.length);
                        int aVal = findPath(maze, x + 1, y, Dir.RIGHT, aStack);
                        if (aVal > val) {
                            maxStack = aStack;
                            val = aVal;
                        }
                    }
                    break;
                case UP:
                    if (y > 0) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.add(x + y * maze.length);
                        int aVal = findPath(maze, x, y - 1, Dir.UP, aStack);
                        if (aVal > val) {
                            maxStack = aStack;
                            val = aVal;
                        }
                    }
                    if (x < maze[0].length - 1) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.add(x + y * maze.length);
                        int aVal = findPath(maze, x + 1, y, Dir.RIGHT, aStack);
                        if (aVal > val) {
                            maxStack = aStack;
                            val = aVal;
                        }
                    }
                    break;
                case DOWN:
                    if (y < maze.length - 1) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.add(x + y * maze.length);
                        int aVal = findPath(maze, x, y + 1, Dir.DOWN, aStack);
                        if (aVal > val) {
                            maxStack = aStack;
                            val = aVal;
                        }
                    }
                    if (x < maze[0].length - 1) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.add(x + y * maze.length);
                        int aVal = findPath(maze, x + 1, y, Dir.RIGHT, aStack);
                        if (aVal > val) {
                            maxStack = aStack;
                            val = aVal;
                        }
                    }
                    break;
            }
            // no route to take.
            if (val >= 0) {
                val += maze[y][x];

                stack.addAll(maxStack);
                System.out.println("1. " + val + "(" + d + "," + x + "," + y + "," + maze[y][x] + ")" + maxStack);
            } else if (x == maze[0].length - 1) {
                // touch the border.
                val = maze[y][x];
                System.out.println("2. " + val + "(" + d + "," + x + "," + y + ")");
            }
            // transfer?
            switch (d) {
                case RIGHT:
                    if (y == 0) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.addAll(maxStack);
                        int aVal = findPath(maze, x, maze.length - 1, Dir.UP, aStack);
                        if (aVal > val) {
                            val = aVal;
                            maxStack.addAll(aStack);
                        }
                    } else if (y == maze.length - 1) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.addAll(maxStack);
                        int aVal = findPath(maze, x, 0, Dir.DOWN, aStack);
                        if (aVal > val) {
                            val = aVal;
                            maxStack.addAll(aStack);
                        }
                    }
                    break;
                case UP:
                    if (y == 0) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.addAll(maxStack);
                        int aVal = findPath(maze, x, maze.length - 1, Dir.UP, aStack);
                        if (aVal > val) {
                            val = aVal;
                            maxStack.addAll(aStack);
                        }
                    }
                    break;
                case DOWN:
                    if (y == maze.length - 1) {
                        Set<Integer> aStack = new HashSet<>(stack);
                        aStack.addAll(maxStack);
                        int aVal = max(findPath(maze, x, 0, Dir.DOWN, stack), val);
                        if (aVal > val) {
                            val = aVal;
                            maxStack.addAll(aStack);
                        }
                    }
                    break;
            }
            stack.addAll(maxStack);
            System.out.println("3. " + val + "(" + d + "," + x + "," + y + ")");
            return val;
        }
        if (x == maze[0].length - 1) {
            System.out.println("end");
            stack.add(x + y * maze.length);
            return 0;
        }
        return -1;
    }

    private static int findPath(int[][] maze) {
        return findPath(maze, 0, maze.length - 1, Dir.RIGHT, new HashSet<Integer>());
    }

    public static void main(String... args) {
        System.out.println(findPath(new int[][]{
                {-1, 4, 3, 1},
                {2, -1, 2, 4},
                {3, 3, -1, 3},
                {4, 2, 1, 2}
        }));
    }
}
