package netease.p201604.p1;

import java.util.Scanner;

/**
 * 简单图像处理.
 * http://hihocoder.com/contest/ntest2016spring2/problem/1
 *
 * Created by yfwz100 on 16/4/21.
 */
public class Main {

    public static void main(String ... args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int S = sc.nextInt();
            for (int i = 0; i < S; i++) {
                int N = sc.nextInt(), M = sc.nextInt();
                int[][] img = new int[N][M];
                for (int y = 0; y < N; y++) {
                    for (int x = 0; x < M; x++) {
                        img[y][x] = sc.nextInt();
                    }
                }
                int T = sc.nextInt();
                for (int j = 0; j < T; j++) {
                    switch (sc.nextInt()) {
                        case 1: // 顺时针旋转90度
                        {
                            int[][] newImg = new int[img[0].length][img.length];
                            for (int y = 0; y < img[0].length; y++) {
                                for (int x = 0; x < img.length; x++) {
                                    newImg[y][x] = img[img.length- 1 - x][y];
                                }
                            }
                            img = newImg;
                            break;
                        }
                        case 2: // 逆时针旋转90度
                        {
                            int[][] newImg = new int[img[0].length][img.length];
                            for (int y = 0; y < img[0].length; y++) {
                                for (int x = 0; x < img.length; x++) {
                                    newImg[y][x] = img[x][img[0].length - 1 - y];
                                }
                            }
                            img = newImg;
                            break;
                        }
                        case 3: // 垂直旋转
                        {
                            for (int y = 0; y < img.length / 2; y++) {
                                for (int x = 0; x < img[y].length; x++) {
                                    int t = img[y][x];
                                    img[y][x] = img[img.length - 1 - y][x];
                                    img[img.length - 1 - y][x] = t;
                                }
                            }
                            break;
                        }
                        case 4: // 水平旋转
                        {
                            for (int y = 0; y < img.length; y++) {
                                for (int x = 0; x < img[y].length / 2; x++) {
                                    int t = img[y][x];
                                    img[y][x] = img[y][img[y].length - 1 - x];
                                    img[y][img[y].length - 1 - x] = t;
                                }
                            }
                            break;
                        }
                        case 5: // 区域灰度值增加
                        {
                            int x0 = sc.nextInt(), y0 = sc.nextInt(), x1 = sc.nextInt(), y1 = sc.nextInt();
                            int value = sc.nextInt();
//                            System.out.println(x0 + "," + x1 + "," + y0 + "," + y1 + ":" + img.length + "," + img[0].length);
                            for (int y = y0; y < y1+1; y++) {
                                for (int x = x0; x < x1+1; x++) {
                                    int t = img[x][y] + value;
                                    if (t > 255) {
                                        img[x][y] = 255;
                                    } else {
                                        img[x][y] = t;
                                    }
                                }
                            }
                            break;
                        }
                        case 6: // 区域灰度值减少
                        {
                            int x0 = sc.nextInt(), y0 = sc.nextInt(), x1 = sc.nextInt(), y1 = sc.nextInt();
                            int value = sc.nextInt();
                            for (int y = y0; y < y1+1; y++) {
                                for (int x = x0; x < x1+1; x++) {
                                    int t = img[x][y] - value;
                                    if (t > 0) {
                                        img[x][y] = t;
                                    } else {
                                        img[x][y] = 0;
                                    }
                                }
                            }
                            break;
                        }
                        case 7: // 区域裁剪
                        {
                            int x0 = sc.nextInt(), y0 = sc.nextInt(), x1 = sc.nextInt(), y1 = sc.nextInt();
                            int[][] newImg = new int[x1-x0+1][y1-y0+1];
                            for (int y = y0; y < y1+1; y++) {
                                for (int x = x0; x < x1+1; x++) {
                                    newImg[x-x0][y-y0] = img[x][y];
                                }
                            }
                            img = newImg;
                            break;
                        }
                    }
                }
                // output
                int pxSum = 0;
                for (int[] anImg : img) {
                    for (int anAnImg : anImg) {
                        pxSum += anAnImg;
                    }
                }
                System.out.println(img.length + " " + img[0].length + " " + img[0][0] + " " + pxSum);
            }
        }
    }
}
