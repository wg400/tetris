package com.wanggang.tetris.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;

import com.wanggang.tetris.R;

/**
 * Created by wanggang on 17/10/18.
 */

public class AttrUtil {

    public static int getColorByAttrId(Context context, int attrIdForColor) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(attrIdForColor, typedValue, true);
        return typedValue.data;
    }

    public static Drawable getButtonBg(Context context) {
        StateListDrawable stateDrawable = new StateListDrawable();
        GradientDrawable normalDrawable = new GradientDrawable();
        GradientDrawable pressedDrawable = new GradientDrawable();

        int[][] states = new int[3][];
        states[0] = new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_focused};
        states[2] = new int[]{android.R.attr.state_enabled};

        // 为各种状态下的 drawable 设置 attr 颜色值
        normalDrawable.setColor(getColorByAttrId(context, R.attr.mainColor));
        pressedDrawable.setColor(getColorByAttrId(context, R.attr.darkColor));
        normalDrawable.setShape(GradientDrawable.OVAL);
        pressedDrawable.setShape(GradientDrawable.OVAL);

        stateDrawable.addState(states[0], pressedDrawable);
        stateDrawable.addState(states[1], pressedDrawable);
        stateDrawable.addState(states[2], normalDrawable);

        return stateDrawable;
    }

    // 获取相框
    public static Drawable getGameBorder(Context context) {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.TRANSPARENT);
        shape.setStroke((int) context.getResources().getDimension(R.dimen.border_width),
                getColorByAttrId(context, R.attr.mainColor));

        return shape;
    }

    // 设置边框
    public static Drawable getTabBorder(Context context) {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(Color.WHITE);
        shape.setCornerRadius(context.getResources().getDimension(R.dimen.radius));
        shape.setStroke((int) context.getResources().getDimension(R.dimen.line_width),
                getColorByAttrId(context, R.attr.mainColor));

        return shape;
    }

    // 获取相框
    public static Drawable getMainColor(Context context) {

        GradientDrawable shape = new GradientDrawable();
        shape.setColor(getColorByAttrId(context, R.attr.mainColor));

        return shape;
    }
}
