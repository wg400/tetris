package com.wanggang.tetris.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.wanggang.tetris.R;
import com.wanggang.tetris.util.AttrUtil;

/**
 * Created by wanggang on 17/10/14.
 */

public class TetrisNext extends View {


    private int width;
    private int gridSize;

    private int[][] tetris;
    Paint rectPaint;
    Rect gridRect;


    public TetrisNext(Context context) {
        super(context);

        init(context);
    }

    public TetrisNext(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public TetrisNext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        tetris = new int[4][4];

        rectPaint = new Paint();
        rectPaint.setColor(AttrUtil.getColorByAttrId(getContext(), R.attr.mainColor));

        gridRect = new Rect();
    }

    public void setTrtrisModle(TrtrisModle trtrisModle) {
        clear();
        tetris[trtrisModle.points[0].x - 3][trtrisModle.points[0].y] = 1;
        tetris[trtrisModle.points[1].x - 3][trtrisModle.points[1].y] = 1;
        tetris[trtrisModle.points[2].x - 3][trtrisModle.points[2].y] = 1;
        tetris[trtrisModle.points[3].x - 3][trtrisModle.points[3].y] = 1;

        invalidate();
    }

    private void clear() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tetris[i][j] = 0;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = MeasureSpec.getSize(widthMeasureSpec);

        gridSize = width / 4;

        setMeasuredDimension(width, width);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, width, width, paint);

        drawGrid(canvas);
    }


    /**
     * 绘制网格
     */

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gridRect.left = gridSize * i;
                gridRect.top = gridSize * j;
                gridRect.right = gridSize * i + gridSize;
                gridRect.bottom = gridSize * j + gridSize;

                drawRect(canvas, tetris[i][j]);
            }
        }
    }

    private void drawRect(Canvas canvas, int style) {
        if (style == 1) {
            canvas.drawRect(gridRect, rectPaint);
        }
    }
}
