package com.wanggang.tetris.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wanggang.tetris.ACache;
import com.wanggang.tetris.BusModel;
import com.wanggang.tetris.R;
import com.wanggang.tetris.RxBus;
import com.wanggang.tetris.util.AlertDialogUtil;
import com.wanggang.tetris.util.AttrUtil;
import com.wanggang.tetris.util.SoundManage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by wanggang on 17/10/14.
 */

public class Tetris extends View {

    private int width;
    private int measureWidth;
    private int gridSize;

    private int[][] tetris;
    Paint linePaint;
    Paint gridPaint;
    Paint rectPaint;
    Paint gridShadowPaint;
    Rect gridRect;

    TrtrisModle trtrisModle;
    TrtrisModle nextTrtrisModle;
    TrtrisModle downTrtrisModle;

    int speed; // 方块下落速度
    int speedIndex;
    int initSpeed; // 初始速度
    boolean speedEnable = true; // 是否可以继续加速

    private HashMap<Integer, Long> scoreMap;

    private Disposable timeDisposable;

    // 游戏结束
    int gameStatus; // 0、游戏进行中， 1、游戏结束（开始） 2、游戏结束
    int oldStatus;
    Point gamePoint;

    boolean isDown; // 是否直接落下


    private long gameTime; // 记录游戏进行的时间，单位ms

    public Tetris(Context context) {
        super(context);

        init(context);
    }

    public Tetris(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public Tetris(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        tetris = new int[10][23];

        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(getResources().getColor(R.color.line));
        linePaint.setStrokeWidth(2);

        gridPaint = new Paint();
        gridPaint.setColor(Color.WHITE);

        rectPaint = new Paint();
        rectPaint.setColor(AttrUtil.getColorByAttrId(getContext(), R.attr.mainColor));

        gridShadowPaint = new Paint();
        gridShadowPaint.setColor(getResources().getColor(R.color.light_gray));
        gridShadowPaint.setAlpha(244);

        gridRect = new Rect();
        trtrisModle = TrtrisModle.next();
        nextTrtrisModle = TrtrisModle.next();
        downTrtrisModle = new TrtrisModle();
        initSpeed = 100;
        speed = initSpeed;

        scoreMap = new HashMap<>();
        scoreMap.put(0, 0l);
        scoreMap.put(1, 10l);
        scoreMap.put(2, 30l);
        scoreMap.put(3, 50l);
        scoreMap.put(4, 80l);

        gameTime = 0;
    }

    public void start() {
        RxBus.getInstance().send(new BusModel(nextTrtrisModle));
        Observable.interval(250, 10, TimeUnit.MILLISECONDS)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        timeDisposable = disposable;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (gameStatus == 3) {
                            // 暂停
                            return;
                        }

                        if (gameStatus == 2) {
                            return;
                        }
                        if (gameStatus == 1) {
                            if (speedIndex >= 6) {
                                gameOver();
                                invalidate();
                            } else {
                                speedIndex++;
                            }
                            return;
                        }
                        SoundManage.playBgSound();
                        gameTime += 10;

                        if (speed == initSpeed) {
                            speed = 100 - (int) (gameTime / 30000);
                            initSpeed = speed;
                        } else {
                            initSpeed = 100 - (int) (gameTime / 30000);
                        }
                        if (speedIndex >= speed || isDown) {
                            speedIndex = 0;
                            trtrisModle.down(tetris, new TrtrisModle.TrtrisListener() {
                                @Override
                                public void finish() {
                                    isDown = false;
                                    if (speed < initSpeed) {
                                        // 长按加速中
                                        setSpeedEnable(false);
                                    }
                                    Log.i("wanggang", "trtrisModle=" + trtrisModle + "");
                                    int clearLines = trtrisModle.clearLines;
                                    trtrisModle = nextTrtrisModle;
                                    nextTrtrisModle = TrtrisModle.next();
                                    RxBus.getInstance().send(new BusModel(scoreMap.get(clearLines), nextTrtrisModle));
                                    trtrisModle.checkDownPosition(tetris, downTrtrisModle, true);
                                }

                                @Override
                                public void gameOver(int status) {
                                    if (status == 0) {
                                        // 游戏结束
//                                        destory();
                                        SoundManage.stopBgSound();
                                        SoundManage.playOverSound();
                                        gameStatus = 1;
                                        if (gamePoint == null) {
                                            gamePoint = new Point();
                                        }
                                        gamePoint.x = 9;
                                        gamePoint.y = 22;
                                    }
                                }
                            });
                            invalidate();
                        } else {
                            speedIndex++;
                        }
                    }
                });
        trtrisModle.checkDownPosition(tetris, downTrtrisModle, false);
    }

    /**
     * 游戏结束
     * 遍历所有方块、将图片补齐
     */

    private void gameOver() {
        while (true) {
            if (gamePoint.y > 2) {
                if (tetris[gamePoint.x][gamePoint.y] != 3) {
                    tetris[gamePoint.x][gamePoint.y] = 3;
                    return;
                } else {
                    subductionPoint();
                }
            } else {
                // 动画结束
                speedIndex = 0;
                gameStatus = 2;

                AlertDialogUtil.show(getContext(), "游戏结束，再来一局？", true, "那就玩玩", "不玩了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        rePlay();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                return;
            }
        }
    }

    /**
     * 重新开始新游戏
     */
    private void rePlay() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 23; j++) {
                tetris[i][j] = 0;
            }
        }
        isDown = false;
        gameStatus = 0;
        speedIndex = 0;
        gameTime = 0;

        // 分数清零
        RxBus.getInstance().send(new BusModel(-1));
    }

    private void subductionPoint() {
        if (gamePoint.x > 0) {
            gamePoint.x = gamePoint.x - 1;
        } else {
            gamePoint.y = gamePoint.y - 1;
            gamePoint.x = 9;
        }
    }

    public void left() {
        trtrisModle.left(tetris);
        trtrisModle.checkDownPosition(tetris, downTrtrisModle, false);
        invalidate();
    }

    public void right() {
        trtrisModle.right(tetris);
        trtrisModle.checkDownPosition(tetris, downTrtrisModle, false);
        invalidate();
    }

    public void down() {
        speedIndex = 0;
        trtrisModle.down(tetris, new TrtrisModle.TrtrisListener() {
            @Override
            public void finish() {
                trtrisModle = nextTrtrisModle;
                nextTrtrisModle = TrtrisModle.next();
                trtrisModle.checkDownPosition(tetris, downTrtrisModle, true);
            }

            @Override
            public void gameOver(int status) {
                if (status == 0) {
                    // 游戏结束
//                    destory();

                    SoundManage.stopBgSound();
                    SoundManage.playOverSound();
                    gameStatus = 1;
                    if (gamePoint == null) {
                        gamePoint = new Point();
                    }
                    gamePoint.x = 9;
                    gamePoint.y = 22;
                }
            }
        });
        invalidate();
    }

    // 加速下落
//    public void plusSpped() {
//        if (!speedEnable) {
//            speedBack();
//            return;
//        }
//        if (speed > 2)
//            speed -= 20;
//    }

    // 直接落下
    public void crashDown() {
        isDown = true;
    }

    // 还原速度
    public void speedBack() {
        speed = initSpeed;
    }

    public void setSpeedEnable(boolean enable) {
        speedEnable = enable;
    }

    public void change() {
        trtrisModle.change(tetris);
        trtrisModle.checkDownPosition(tetris, downTrtrisModle, false);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measureWidth = MeasureSpec.getSize(widthMeasureSpec);

        gridSize = measureWidth / 10;

        width = gridSize * 10;

        setMeasuredDimension(measureWidth, 2 * width);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint rightPaint = new Paint();
        rightPaint.setColor(AttrUtil.getColorByAttrId(getContext(), R.attr.mainColor));
        canvas.drawRect(width, 0, measureWidth, 2 * width, rightPaint);
//
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);

        String gamePic = ACache.get(getContext()).getAsString("gamePic");
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(gamePic)) {
            bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.img_game)).getBitmap();
        } else {
            bitmap = BitmapFactory.decodeFile(gamePic);
        }
        Rect srcRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Rect bgRect = new Rect(0, 0, width, 2 * width);
        canvas.drawBitmap(bitmap, srcRect, bgRect, paint);

        drawGrid(canvas);
    }

    // 游戏暂停
    public void pause() {
        oldStatus = gameStatus;
        gameStatus = 3;
    }

    // 游戏继续
    public void play() {
        gameStatus = oldStatus;
    }

    /**
     * 绘制网格
     */

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                gridRect.left = gridSize * i;
                gridRect.top = gridSize * j;
                gridRect.right = gridSize * i + gridSize;
                gridRect.bottom = gridSize * j + gridSize;

                drawRect(canvas, tetris[i][j + 3]);
            }
        }
    }

    private void drawRect(Canvas canvas, int style) {
        if (style == 0) {
            canvas.drawRect(gridRect, gridPaint);
            canvas.drawRect(gridRect, linePaint);
        } else if (style == 1) {
            canvas.drawRect(gridRect, rectPaint);
        } else if (style == 2) {
            canvas.drawRect(gridRect, gridShadowPaint);
        }
    }

    /**
     * 销毁
     */
    public void destory() {
        if (timeDisposable != null && !timeDisposable.isDisposed()) {
            timeDisposable.dispose();
        }
    }
}
