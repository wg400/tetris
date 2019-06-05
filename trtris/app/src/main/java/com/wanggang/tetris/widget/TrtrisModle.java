package com.wanggang.tetris.widget;

import android.graphics.Point;
import android.util.Log;

import com.wanggang.tetris.util.SoundManage;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by wanggang on 17/10/15.
 */

public class TrtrisModle {

    /**
     * 0、正方形 1、长方形（横）2、长方形（竖）
     * 3 4 5 6、L形
     * 7 8 9 10、反L形
     * 11 12 13 14、T形
     * 15 16、N形
     * 17 18、反N形
     */

    public int style;

    public Point[] points;

    public int clearLines; // 记录一次消除的行数

    private static Random random = new Random();

    public TrtrisModle() {
        points = new Point[4];
    }

    public static TrtrisModle next() {
        TrtrisModle trtrisModle = new TrtrisModle();
        trtrisModle.style = random.nextInt(19);
        switch (trtrisModle.style) {
            case 0:
                trtrisModle.points[0] = new Point(4, 1);
                trtrisModle.points[1] = new Point(5, 1);
                trtrisModle.points[2] = new Point(4, 2);
                trtrisModle.points[3] = new Point(5, 2);
                break;
            case 1:
                trtrisModle.points[0] = new Point(3, 2);
                trtrisModle.points[1] = new Point(4, 2);
                trtrisModle.points[2] = new Point(5, 2);
                trtrisModle.points[3] = new Point(6, 2);
                break;
            case 2:
                trtrisModle.points[0] = new Point(4, 0);
                trtrisModle.points[1] = new Point(4, 1);
                trtrisModle.points[2] = new Point(4, 2);
                trtrisModle.points[3] = new Point(4, 3);
                break;
            case 3:
                trtrisModle.points[0] = new Point(4, 1);
                trtrisModle.points[1] = new Point(4, 2);
                trtrisModle.points[2] = new Point(4, 3);
                trtrisModle.points[3] = new Point(5, 3);
                break;
            case 4:
                trtrisModle.points[0] = new Point(3, 1);
                trtrisModle.points[1] = new Point(4, 1);
                trtrisModle.points[2] = new Point(5, 1);
                trtrisModle.points[3] = new Point(3, 2);
                break;
            case 5:
                trtrisModle.points[0] = new Point(4, 1);
                trtrisModle.points[1] = new Point(5, 1);
                trtrisModle.points[2] = new Point(5, 2);
                trtrisModle.points[3] = new Point(5, 3);
                break;
            case 6:
                trtrisModle.points[0] = new Point(3, 2);
                trtrisModle.points[1] = new Point(4, 2);
                trtrisModle.points[2] = new Point(5, 2);
                trtrisModle.points[3] = new Point(5, 1);
                break;
            case 7:
                trtrisModle.points[0] = new Point(5, 1);
                trtrisModle.points[1] = new Point(5, 2);
                trtrisModle.points[2] = new Point(5, 3);
                trtrisModle.points[3] = new Point(4, 3);
                break;
            case 8:
                trtrisModle.points[0] = new Point(3, 1);
                trtrisModle.points[1] = new Point(3, 2);
                trtrisModle.points[2] = new Point(4, 2);
                trtrisModle.points[3] = new Point(5, 2);
                break;
            case 9:
                trtrisModle.points[0] = new Point(4, 1);
                trtrisModle.points[1] = new Point(5, 1);
                trtrisModle.points[2] = new Point(4, 2);
                trtrisModle.points[3] = new Point(4, 3);
                break;
            case 10:
                trtrisModle.points[0] = new Point(3, 1);
                trtrisModle.points[1] = new Point(4, 1);
                trtrisModle.points[2] = new Point(5, 1);
                trtrisModle.points[3] = new Point(5, 2);
                break;
            case 11:
                trtrisModle.points[0] = new Point(3, 2);
                trtrisModle.points[1] = new Point(4, 2);
                trtrisModle.points[2] = new Point(5, 2);
                trtrisModle.points[3] = new Point(4, 1);
                break;
            case 12:
                trtrisModle.points[0] = new Point(4, 1);
                trtrisModle.points[1] = new Point(4, 2);
                trtrisModle.points[2] = new Point(4, 3);
                trtrisModle.points[3] = new Point(5, 2);
                break;
            case 13:
                trtrisModle.points[0] = new Point(3, 1);
                trtrisModle.points[1] = new Point(4, 1);
                trtrisModle.points[2] = new Point(5, 1);
                trtrisModle.points[3] = new Point(4, 2);
                break;
            case 14:
                trtrisModle.points[0] = new Point(4, 2);
                trtrisModle.points[1] = new Point(5, 1);
                trtrisModle.points[2] = new Point(5, 2);
                trtrisModle.points[3] = new Point(5, 3);
                break;
            case 15:
                trtrisModle.points[0] = new Point(4, 2);
                trtrisModle.points[1] = new Point(5, 2);
                trtrisModle.points[2] = new Point(5, 1);
                trtrisModle.points[3] = new Point(4, 3);
                break;
            case 16:
                trtrisModle.points[0] = new Point(3, 1);
                trtrisModle.points[1] = new Point(4, 1);
                trtrisModle.points[2] = new Point(4, 2);
                trtrisModle.points[3] = new Point(5, 2);
                break;
            case 17:
                trtrisModle.points[0] = new Point(4, 1);
                trtrisModle.points[1] = new Point(4, 2);
                trtrisModle.points[2] = new Point(5, 2);
                trtrisModle.points[3] = new Point(5, 3);
                break;
            case 18:
                trtrisModle.points[0] = new Point(3, 2);
                trtrisModle.points[1] = new Point(4, 2);
                trtrisModle.points[2] = new Point(4, 1);
                trtrisModle.points[3] = new Point(5, 1);
                break;
        }
        return trtrisModle;
    }

    public void down(int[][] tetris, TrtrisListener listener) {
        if (points[0].y == 22 || points[1].y == 22 || points[2].y == 22 || points[3].y == 22) {
            tetris[points[0].x][points[0].y] = 3;
            tetris[points[1].x][points[1].y] = 3;
            tetris[points[2].x][points[2].y] = 3;
            tetris[points[3].x][points[3].y] = 3;
            checkFinish(tetris);
            Log.i("wanggang", "clearLines="+clearLines);
            if (clearLines > 0) {
                SoundManage.playCoolSound();
            }
            listener.finish();
            return;
        }
        if (tetris[points[0].x][points[0].y + 1] == 3 ||
                tetris[points[1].x][points[1].y + 1] == 3 ||
                tetris[points[2].x][points[2].y + 1] == 3 ||
                tetris[points[3].x][points[3].y + 1] == 3) {
            tetris[points[0].x][points[0].y] = 3;
            tetris[points[1].x][points[1].y] = 3;
            tetris[points[2].x][points[2].y] = 3;
            tetris[points[3].x][points[3].y] = 3;
            Log.i("wanggang", "clearLinesFinish="+clearLines);
            checkFinish(tetris);

            if (clearLines == 0) {
                if (points[0].y == 3 || points[1].y == 3 || points[2].y == 3 || points[3].y == 3) {
                    // 游戏结束
                    listener.gameOver(0);
                    return;
                }
            } else {
                SoundManage.playCoolSound();
            }
            listener.finish();
            return;
        }

        tetris[points[0].x][points[0].y] = 0;
        tetris[points[1].x][points[1].y] = 0;
        tetris[points[2].x][points[2].y] = 0;
        tetris[points[3].x][points[3].y] = 0;

        points[0].y = points[0].y + 1;
        points[1].y = points[1].y + 1;
        points[2].y = points[2].y + 1;
        points[3].y = points[3].y + 1;

        tetris[points[0].x][points[0].y] = 1;
        tetris[points[1].x][points[1].y] = 1;
        tetris[points[2].x][points[2].y] = 1;
        tetris[points[3].x][points[3].y] = 1;

//        if (points[0].y == 22 || points[1].y == 22 || points[2].y == 22 || points[3].y == 22) {
//            tetris[points[0].x][points[0].y] = 3;
//            tetris[points[1].x][points[1].y] = 3;
//            tetris[points[2].x][points[2].y] = 3;
//            tetris[points[3].x][points[3].y] = 3;
//
//            checkFinish(tetris);
//            listener.finish();
//            return;
//        }
//        if (tetris[points[0].x][points[0].y + 1] == 3 ||
//                tetris[points[1].x][points[1].y + 1] == 3 ||
//                tetris[points[2].x][points[2].y + 1] == 3 ||
//                tetris[points[3].x][points[3].y + 1] == 3) {
//            tetris[points[0].x][points[0].y] = 3;
//            tetris[points[1].x][points[1].y] = 3;
//            tetris[points[2].x][points[2].y] = 3;
//            tetris[points[3].x][points[3].y] = 3;
//            checkFinish(tetris);
//            listener.finish();
//            return;
//        }

    }

    public void left(int[][] tetris) {
        if (points[0].x != 0 && points[1].x != 0 && points[2].x != 0 && points[3].x != 0) {
            if (tetris[points[0].x - 1][points[0].y] != 3 &&
                    tetris[points[1].x - 1][points[1].y] != 3 &&
                    tetris[points[2].x - 1][points[2].y] != 3 &&
                    tetris[points[3].x - 1][points[3].y] != 3) {

                tetris[points[0].x][points[0].y] = 0;
                tetris[points[1].x][points[1].y] = 0;
                tetris[points[2].x][points[2].y] = 0;
                tetris[points[3].x][points[3].y] = 0;

                points[0].x = points[0].x - 1;
                points[1].x = points[1].x - 1;
                points[2].x = points[2].x - 1;
                points[3].x = points[3].x - 1;

                tetris[points[0].x][points[0].y] = 1;
                tetris[points[1].x][points[1].y] = 1;
                tetris[points[2].x][points[2].y] = 1;
                tetris[points[3].x][points[3].y] = 1;

            }
        }
    }

    /**
     * 检测消除
     */
    private void checkFinish(int[][] tetris) {
        Set<Integer> lines1 = getLines();
        Object[] lines = lines1.toArray();
        clearLines = 0;
        for (int i = lines.length - 1; i >= 0; i--) {
            int line = (int) lines[i];
            if (ckeckLine(tetris, line)) {
                // 消除这一行
                clearLines++;
                moveLine(tetris, line - 1);
                break;
            }
        }
    }

    /**
     * 检测一行是否能消除
     */
    private boolean ckeckLine(int[][] tetris, int line) {
        boolean flag = true;
        for (int j = 0; j < 10; j++) {
            if (tetris[j][line] != 3) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 下移一行
     */
    private void moveLine(int[][] tetris, int line) {
        if (ckeckLine(tetris, line)) {
            clearLines++;
            moveLine(tetris, line - 1);
        }
        for (int j = 0; j < 10; j++) {
            tetris[j][line + 1] = tetris[j][line];
        }
        if (checkNeedMove(tetris, line)) {
            moveLine(tetris, line - 1);
        }
    }

    /**
     * 检测是否继续下移
     */
    private boolean checkNeedMove(int[][] tetris, int line) {
        boolean flag = false;
        for (int j = 0; j < 10; j++) {
            if (tetris[j][line] == 3) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 获取可能消除的行号
     */
    private Set<Integer> getLines() {
        Set<Integer> lines = new TreeSet<Integer>();
        lines.add(points[0].y);
        lines.add(points[1].y);
        lines.add(points[2].y);
        lines.add(points[3].y);
        return lines;
    }

    public void right(int[][] tetris) {
        if (points[0].x != 9 && points[1].x != 9 && points[2].x != 9 && points[3].x != 9) {
            if (tetris[points[0].x + 1][points[0].y] != 3 &&
                    tetris[points[1].x + 1][points[1].y] != 3 &&
                    tetris[points[2].x + 1][points[2].y] != 3 &&
                    tetris[points[3].x + 1][points[3].y] != 3) {

                tetris[points[0].x][points[0].y] = 0;
                tetris[points[1].x][points[1].y] = 0;
                tetris[points[2].x][points[2].y] = 0;
                tetris[points[3].x][points[3].y] = 0;

                points[0].x = points[0].x + 1;
                points[1].x = points[1].x + 1;
                points[2].x = points[2].x + 1;
                points[3].x = points[3].x + 1;

                tetris[points[0].x][points[0].y] = 1;
                tetris[points[1].x][points[1].y] = 1;
                tetris[points[2].x][points[2].y] = 1;
                tetris[points[3].x][points[3].y] = 1;

            }
        }
    }

    public void change(int[][] tetris) {
        switch (style) {
            case 0:
                break;
            case 1:
                if (points[0].x < 4) {
                    if (tetris[points[0].x][points[0].y - 1] != 3 &&
                            tetris[points[0].x + 1][points[0].y - 1] != 3 &&
                            tetris[points[1].x][points[1].y + 1] != 3 &&
                            tetris[points[1].x][points[1].y + 2] != 3 &&
                            tetris[points[2].x][points[2].y + 1] != 3 &&
                            tetris[points[2].x][points[2].y + 2] != 3 &&
                            tetris[points[3].x][points[3].y + 1] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;


                        points[0].x = points[0].x + 1;
                        points[0].y = points[0].y - 1;
                        points[2].x = points[2].x - 1;
                        points[2].y = points[2].y + 1;
                        points[3].x = points[3].x - 2;
                        points[3].y = points[3].y + 2;
                        style = 2;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                } else {
                    if (tetris[points[0].x][points[0].y + 1] != 3 &&
                            tetris[points[1].x][points[1].y + 1] != 3 &&
                            tetris[points[1].x][points[1].y + 2] != 3 &&
                            tetris[points[2].x][points[2].y + 1] != 3 &&
                            tetris[points[2].x][points[2].y + 2] != 3 &&
                            tetris[points[2].x][points[2].y - 1] != 3 &&
                            tetris[points[3].x][points[3].y - 1] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x + 2;
                        points[0].y = points[0].y - 1;
                        points[1].x = points[1].x + 1;
                        points[1].y = points[1].y;
                        points[2].x = points[2].x;
                        points[2].y = points[2].y + 1;
                        points[3].x = points[3].x - 1;
                        points[3].y = points[3].y + 2;
                        style = 2;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }
                break;
            case 2:
                if (points[0].x < 5 && points[0].x > 0) {
                    if (tetris[points[0].x - 1][points[0].y] != 3 &&
                            tetris[points[2].x + 1][points[2].y] != 3 &&
                            tetris[points[2].x + 1][points[2].y + 1] != 3 &&
                            tetris[points[3].x + 2][points[3].y] != 3 &&
                            tetris[points[0].x - 1][points[0].y + 1] != 3 &&
                            tetris[points[2].x + 1][points[2].y - 1] != 3 &&
                            tetris[points[3].x + 2][points[3].y - 2] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x - 1;
                        points[0].y = points[0].y + 1;
                        points[2].x = points[2].x + 1;
                        points[2].y = points[2].y - 1;
                        points[3].x = points[3].x + 2;
                        points[3].y = points[3].y - 2;
                        style = 1;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                } else if (points[0].x < 9) {
                    if (tetris[points[0].x + 1][points[0].y] != 3 &&
                            tetris[points[2].x - 1][points[2].y] != 3 &&
                            tetris[points[2].x - 1][points[2].y + 1] != 3 &&
                            tetris[points[3].x - 2][points[3].y] != 3 &&
                            tetris[points[0].x + 1][points[0].y + 1] != 3 &&
                            tetris[points[2].x - 1][points[2].y - 1] != 3 &&
                            tetris[points[3].x - 2][points[3].y - 2] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x - 2;
                        points[0].y = points[0].y + 1;
                        points[1].x = points[1].x - 1;
                        points[1].y = points[1].y;
                        points[2].x = points[2].x;
                        points[2].y = points[2].y - 1;
                        points[3].x = points[3].x + 1;
                        points[3].y = points[3].y - 2;
                        style = 1;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }
                break;
            case 3:
                if (points[0].x > 0) {
                    if (tetris[points[0].x - 1][points[0].y] != 3 &&
                            tetris[points[1].x - 1][points[1].y] != 3 &&
                            tetris[points[2].x - 1][points[2].y] != 3 &&
                            tetris[points[0].x + 1][points[0].y] != 3 &&
                            tetris[points[1].x + 1][points[1].y] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x - 1;
                        points[0].y = points[0].y + 1;
                        points[2].x = points[2].x + 1;
                        points[2].y = points[2].y - 1;
                        points[3].x = points[3].x - 2;
                        points[3].y = points[3].y;
                        style = 4;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }
                break;
            case 4:
                if (tetris[points[0].x][points[0].y - 1] != 3 &&
                        tetris[points[1].x][points[1].y - 1] != 3 &&
                        tetris[points[2].x][points[2].y - 1] != 3 &&
                        tetris[points[3].x + 1][points[3].y] != 3 &&
                        tetris[points[3].x + 2][points[3].y] != 3) {
                    tetris[points[0].x][points[0].y] = 0;
                    tetris[points[1].x][points[1].y] = 0;
                    tetris[points[2].x][points[2].y] = 0;
                    tetris[points[3].x][points[3].y] = 0;

                    points[0].x = points[0].x;
                    points[0].y = points[0].y - 1;
                    points[1].x = points[1].x;
                    points[1].y = points[1].y - 1;
                    points[2].x = points[2].x - 1;
                    points[2].y = points[2].y;
                    points[3].x = points[3].x + 1;
                    points[3].y = points[3].y;
                    style = 5;

                    tetris[points[0].x][points[0].y] = 1;
                    tetris[points[1].x][points[1].y] = 1;
                    tetris[points[2].x][points[2].y] = 1;
                    tetris[points[3].x][points[3].y] = 1;
                }
                break;
            case 5:
                if (points[1].x < 9) {
                    if (tetris[points[1].x + 1][points[0].y] != 3 &&
                            tetris[points[2].x + 1][points[1].y] != 3 &&
                            tetris[points[3].x + 1][points[2].y] != 3 &&
                            tetris[points[2].x - 1][points[0].y] != 3 &&
                            tetris[points[3].x - 1][points[1].y] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x;
                        points[0].y = points[0].y + 1;
                        points[1].x = points[1].x;
                        points[1].y = points[1].y + 1;
                        points[2].x = points[2].x + 1;
                        points[2].y = points[2].y;
                        points[3].x = points[3].x + 1;
                        points[3].y = points[3].y - 2;
                        style = 6;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }
                break;
            case 6:
                if (tetris[points[0].x][points[0].y + 1] != 3 &&
                        tetris[points[1].x][points[1].y + 1] != 3 &&
                        tetris[points[2].x][points[2].y + 1] != 3 &&
                        tetris[points[0].x][points[0].y - 1] != 3 &&
                        tetris[points[1].x][points[1].y - 1] != 3) {
                    tetris[points[0].x][points[0].y] = 0;
                    tetris[points[1].x][points[1].y] = 0;
                    tetris[points[2].x][points[2].y] = 0;
                    tetris[points[3].x][points[3].y] = 0;

                    points[0].x = points[0].x + 1;
                    points[0].y = points[0].y - 1;
                    points[1].x = points[1].x;
                    points[1].y = points[1].y;
                    points[2].x = points[2].x - 1;
                    points[2].y = points[2].y + 1;
                    points[3].x = points[3].x;
                    points[3].y = points[3].y + 2;
                    style = 3;

                    tetris[points[0].x][points[0].y] = 1;
                    tetris[points[1].x][points[1].y] = 1;
                    tetris[points[2].x][points[2].y] = 1;
                    tetris[points[3].x][points[3].y] = 1;
                }
                break;
            case 7:
                if (points[0].x < 9) {
                    if (tetris[points[0].x + 1][points[0].y] != 3 &&
                            tetris[points[1].x + 1][points[1].y] != 3 &&
                            tetris[points[2].x + 1][points[2].y] != 3 &&
                            tetris[points[0].x - 1][points[0].y] != 3 &&
                            tetris[points[1].x - 1][points[1].y] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x - 1;
                        points[0].y = points[0].y;
                        points[1].x = points[1].x - 1;
                        points[1].y = points[1].y;
                        points[2].x = points[2].x;
                        points[2].y = points[2].y - 1;
                        points[3].x = points[3].x + 2;
                        points[3].y = points[3].y - 1;
                        style = 8;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }

                break;
            case 8:
                if (tetris[points[1].x][points[1].y + 1] != 3 &&
                        tetris[points[2].x][points[2].y + 1] != 3 &&
                        tetris[points[3].x][points[3].y + 1] != 3 &&
                        tetris[points[2].x][points[0].y - 1] != 3 &&
                        tetris[points[3].x][points[1].y - 1] != 3) {
                    tetris[points[0].x][points[0].y] = 0;
                    tetris[points[1].x][points[1].y] = 0;
                    tetris[points[2].x][points[2].y] = 0;
                    tetris[points[3].x][points[3].y] = 0;

                    points[0].x = points[0].x + 1;
                    points[0].y = points[0].y;
                    points[1].x = points[1].x + 2;
                    points[1].y = points[1].y - 1;
                    points[2].x = points[2].x;
                    points[2].y = points[2].y;
                    points[3].x = points[3].x - 1;
                    points[3].y = points[3].y + 1;
                    style = 9;

                    tetris[points[0].x][points[0].y] = 1;
                    tetris[points[1].x][points[1].y] = 1;
                    tetris[points[2].x][points[2].y] = 1;
                    tetris[points[3].x][points[3].y] = 1;
                }
                break;
            case 9:
                if (points[0].x > 0) {
                    if (tetris[points[0].x - 1][points[0].y] != 3 &&
                            tetris[points[2].x - 1][points[1].y] != 3 &&
                            tetris[points[3].x - 1][points[2].y] != 3 &&
                            tetris[points[2].x + 1][points[0].y] != 3 &&
                            tetris[points[3].x + 1][points[1].y] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x - 1;
                        points[0].y = points[0].y + 1;
                        points[1].x = points[1].x - 1;
                        points[1].y = points[1].y + 1;
                        points[2].x = points[2].x + 1;
                        points[2].y = points[2].y;
                        points[3].x = points[3].x + 1;
                        points[3].y = points[3].y;
                        style = 10;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }
                break;
            case 10:
                if (tetris[points[0].x][points[0].y - 1] != 3 &&
                        tetris[points[1].x][points[2].y - 1] != 3 &&
                        tetris[points[2].x][points[3].y - 1] != 3 &&
                        tetris[points[0].x][points[0].y + 1] != 3 &&
                        tetris[points[1].x][points[1].y + 1] != 3) {
                    tetris[points[0].x][points[0].y] = 0;
                    tetris[points[1].x][points[1].y] = 0;
                    tetris[points[2].x][points[2].y] = 0;
                    tetris[points[3].x][points[3].y] = 0;

                    points[0].x = points[0].x + 1;
                    points[0].y = points[0].y - 1;
                    points[1].x = points[1].x;
                    points[1].y = points[1].y;
                    points[2].x = points[2].x - 1;
                    points[2].y = points[2].y + 1;
                    points[3].x = points[3].x - 2;
                    points[3].y = points[3].y;
                    style = 7;

                    tetris[points[0].x][points[0].y] = 1;
                    tetris[points[1].x][points[1].y] = 1;
                    tetris[points[2].x][points[2].y] = 1;
                    tetris[points[3].x][points[3].y] = 1;
                }
                break;
            case 11:
                if (tetris[points[0].x][points[0].y + 1] != 3 &&
                        tetris[points[1].x][points[1].y + 1] != 3 &&
                        tetris[points[2].x][points[2].y + 1] != 3 &&
                        tetris[points[0].x][points[0].y - 1] != 3 &&
                        tetris[points[2].x][points[2].y - 1] != 3) {
                    tetris[points[0].x][points[0].y] = 0;
                    tetris[points[1].x][points[1].y] = 0;
                    tetris[points[2].x][points[2].y] = 0;
                    tetris[points[3].x][points[3].y] = 0;

                    points[0].x = points[0].x + 1;
                    points[0].y = points[0].y - 1;
                    points[1].x = points[1].x;
                    points[1].y = points[1].y;
                    points[2].x = points[2].x - 1;
                    points[2].y = points[2].y + 1;
                    points[3].x = points[3].x + 1;
                    points[3].y = points[3].y + 1;
                    style = 12;

                    tetris[points[0].x][points[0].y] = 1;
                    tetris[points[1].x][points[1].y] = 1;
                    tetris[points[2].x][points[2].y] = 1;
                    tetris[points[3].x][points[3].y] = 1;
                }
                break;
            case 12:
                if (points[0].x > 0) {
                    if (tetris[points[0].x - 1][points[0].y] != 3 &&
                            tetris[points[1].x - 1][points[1].y] != 3 &&
                            tetris[points[2].x - 1][points[2].y] != 3 &&
                            tetris[points[0].x + 1][points[0].y] != 3 &&
                            tetris[points[2].x + 1][points[2].y] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x - 1;
                        points[0].y = points[0].y + 1;
                        points[1].x = points[1].x;
                        points[1].y = points[1].y;
                        points[2].x = points[2].x + 1;
                        points[2].y = points[2].y - 1;
                        points[3].x = points[3].x - 1;
                        points[3].y = points[3].y + 1;
                        style = 13;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }
                break;
            case 13:
                if (tetris[points[0].x][points[0].y - 1] != 3 &&
                        tetris[points[1].x][points[1].y - 1] != 3 &&
                        tetris[points[2].x][points[2].y - 1] != 3 &&
                        tetris[points[0].x][points[0].y + 1] != 3 &&
                        tetris[points[2].x][points[2].y + 1] != 3) {
                    tetris[points[0].x][points[0].y] = 0;
                    tetris[points[1].x][points[1].y] = 0;
                    tetris[points[2].x][points[2].y] = 0;
                    tetris[points[3].x][points[3].y] = 0;

                    points[0].x = points[0].x;
                    points[0].y = points[0].y;
                    points[1].x = points[1].x;
                    points[1].y = points[1].y - 1;
                    points[2].x = points[2].x - 1;
                    points[2].y = points[2].y;
                    points[3].x = points[3].x;
                    points[3].y = points[3].y;
                    style = 14;

                    tetris[points[0].x][points[0].y] = 1;
                    tetris[points[1].x][points[1].y] = 1;
                    tetris[points[2].x][points[2].y] = 1;
                    tetris[points[3].x][points[3].y] = 1;
                }
                break;
            case 14:
                if (points[0].x < 9) {
                    if (tetris[points[1].x + 1][points[1].y] != 3 &&
                            tetris[points[2].x + 1][points[2].y] != 3 &&
                            tetris[points[3].x + 1][points[3].y] != 3 &&
                            tetris[points[1].x - 1][points[1].y] != 3 &&
                            tetris[points[3].x - 1][points[3].y] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x;
                        points[0].y = points[0].y;
                        points[1].x = points[1].x;
                        points[1].y = points[1].y + 1;
                        points[2].x = points[2].x + 1;
                        points[2].y = points[2].y;
                        points[3].x = points[3].x;
                        points[3].y = points[3].y - 2;
                        style = 11;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }
                break;
            case 15:
                if (points[0].x > 0) {
                    if (tetris[points[0].x - 1][points[0].y] != 3 &&
                            tetris[points[2].x - 1][points[2].y] != 3 &&
                            tetris[points[2].x - 2][points[2].y] != 3 &&
                            tetris[points[3].x - 1][points[3].y] != 3 &&
                            tetris[points[3].x + 1][points[3].y] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x - 1;
                        points[0].y = points[0].y;
                        points[1].x = points[1].x - 1;
                        points[1].y = points[1].y;
                        points[2].x = points[2].x - 1;
                        points[2].y = points[2].y + 2;
                        points[3].x = points[3].x + 1;
                        points[3].y = points[3].y;
                        style = 16;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }
                break;
            case 16:
                if (tetris[points[0].x][points[0].y - 1] != 3 &&
                        tetris[points[1].x][points[1].y - 1] != 3 &&
                        tetris[points[3].x][points[3].y - 1] != 3 &&
                        tetris[points[3].x][points[3].y - 2] != 3 &&
                        tetris[points[0].x][points[3].y + 1] != 3) {
                    tetris[points[0].x][points[0].y] = 0;
                    tetris[points[1].x][points[1].y] = 0;
                    tetris[points[2].x][points[2].y] = 0;
                    tetris[points[3].x][points[3].y] = 0;

                    points[0].x = points[0].x + 1;
                    points[0].y = points[0].y;
                    points[1].x = points[1].x + 1;
                    points[1].y = points[1].y;
                    points[2].x = points[2].x + 1;
                    points[2].y = points[2].y - 2;
                    points[3].x = points[3].x - 1;
                    points[3].y = points[3].y;
                    style = 15;

                    tetris[points[0].x][points[0].y] = 1;
                    tetris[points[1].x][points[1].y] = 1;
                    tetris[points[2].x][points[2].y] = 1;
                    tetris[points[3].x][points[3].y] = 1;
                }
                break;
            case 17:
                if (points[0].x > 0) {
                    if (tetris[points[0].x - 1][points[0].y] != 3 &&
                            tetris[points[1].x - 1][points[1].y] != 3 &&
                            tetris[points[3].x - 1][points[3].y] != 3 &&
                            tetris[points[3].x - 2][points[3].y] != 3 &&
                            tetris[points[0].x + 1][points[0].y] != 3) {
                        tetris[points[0].x][points[0].y] = 0;
                        tetris[points[1].x][points[1].y] = 0;
                        tetris[points[2].x][points[2].y] = 0;
                        tetris[points[3].x][points[3].y] = 0;

                        points[0].x = points[0].x - 1;
                        points[0].y = points[0].y + 1;
                        points[1].x = points[1].x;
                        points[1].y = points[1].y;
                        points[2].x = points[2].x - 1;
                        points[2].y = points[2].y - 1;
                        points[3].x = points[3].x;
                        points[3].y = points[3].y - 2;
                        style = 18;

                        tetris[points[0].x][points[0].y] = 1;
                        tetris[points[1].x][points[1].y] = 1;
                        tetris[points[2].x][points[2].y] = 1;
                        tetris[points[3].x][points[3].y] = 1;
                    }
                }
                break;
            case 18:
                if (tetris[points[0].x][points[0].y + 1] != 3 &&
                        tetris[points[1].x][points[1].y + 1] != 3 &&
                        tetris[points[3].x][points[3].y + 1] != 3 &&
                        tetris[points[3].x][points[3].y + 2] != 3 &&
                        tetris[points[0].x][points[0].y - 1] != 3) {
                    tetris[points[0].x][points[0].y] = 0;
                    tetris[points[1].x][points[1].y] = 0;
                    tetris[points[2].x][points[2].y] = 0;
                    tetris[points[3].x][points[3].y] = 0;

                    points[0].x = points[0].x + 1;
                    points[0].y = points[0].y - 1;
                    points[1].x = points[1].x;
                    points[1].y = points[1].y;
                    points[2].x = points[2].x + 1;
                    points[2].y = points[2].y + 1;
                    points[3].x = points[3].x;
                    points[3].y = points[3].y + 2;
                    style = 17;

                    tetris[points[0].x][points[0].y] = 1;
                    tetris[points[1].x][points[1].y] = 1;
                    tetris[points[2].x][points[2].y] = 1;
                    tetris[points[3].x][points[3].y] = 1;
                }
                break;
        }
    }

    /**
     * 检测方块下落的位置
     */
    public void checkDownPosition(int[][] tetris, TrtrisModle downTrtrisModle, boolean isDown) {

        if (downTrtrisModle != null && downTrtrisModle.points[0] != null) {
            if (!isDown) {
                setTetrisStatus(tetris, downTrtrisModle.points[0].x, downTrtrisModle.points[0].y, 0);
                setTetrisStatus(tetris, downTrtrisModle.points[1].x, downTrtrisModle.points[1].y, 0);
                setTetrisStatus(tetris, downTrtrisModle.points[2].x, downTrtrisModle.points[2].y, 0);
                setTetrisStatus(tetris, downTrtrisModle.points[3].x, downTrtrisModle.points[3].y, 0);
            }
        } else {
            downTrtrisModle.points[0] = new Point();
            downTrtrisModle.points[1] = new Point();
            downTrtrisModle.points[2] = new Point();
            downTrtrisModle.points[3] = new Point();
        }

        int i = 0;
        while (true) {
            if (points[0].y + i > 22 || points[1].y + i > 22 || points[2].y + i > 22 || points[3].y + i > 22) {
                if (downTrtrisModle != null) {
                    setTetrisStatus(tetris, downTrtrisModle.points[0].x, downTrtrisModle.points[0].y, 2);
                    setTetrisStatus(tetris, downTrtrisModle.points[1].x, downTrtrisModle.points[1].y, 2);
                    setTetrisStatus(tetris, downTrtrisModle.points[2].x, downTrtrisModle.points[2].y, 2);
                    setTetrisStatus(tetris, downTrtrisModle.points[3].x, downTrtrisModle.points[3].y, 2);
                }
                return;
            }
            if (tetris[points[0].x][points[0].y + i] == 3 ||
                    tetris[points[1].x][points[1].y + i] == 3 ||
                    tetris[points[2].x][points[2].y + i] == 3 ||
                    tetris[points[3].x][points[3].y + i] == 3) {
                if (downTrtrisModle != null) {
                    setTetrisStatus(tetris, downTrtrisModle.points[0].x, downTrtrisModle.points[0].y, 2);
                    setTetrisStatus(tetris, downTrtrisModle.points[1].x, downTrtrisModle.points[1].y, 2);
                    setTetrisStatus(tetris, downTrtrisModle.points[2].x, downTrtrisModle.points[2].y, 2);
                    setTetrisStatus(tetris, downTrtrisModle.points[3].x, downTrtrisModle.points[3].y, 2);
                }
                return;
            }
            downTrtrisModle.points[0].x = points[0].x;
            downTrtrisModle.points[0].y = points[0].y + i;
            downTrtrisModle.points[1].x = points[1].x;
            downTrtrisModle.points[1].y = points[1].y + i;
            downTrtrisModle.points[2].x = points[2].x;
            downTrtrisModle.points[2].y = points[2].y + i;
            downTrtrisModle.points[3].x = points[3].x;
            downTrtrisModle.points[3].y = points[3].y + i;
            i++;
        }
    }

    private void setTetrisStatus(int[][] tetris, int i, int j, int type) {
        if (tetris[i][j] != 1 && tetris[i][j] != 3) {
            tetris[i][j] = type;
        }
    }

    @Override
    public String toString() {
        return "TrtrisModle{" +
                "style=" + style +
                ", points=" + Arrays.toString(points) +
                ", clearLines=" + clearLines +
                '}';
    }

    public interface TrtrisListener {
        public void finish();

        public void gameOver(int status);
    }

}
