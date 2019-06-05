package com.wanggang.tetris.util;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanggang.tetris.R;

/**
 * Created by Administrator on 2015/10/12 0012.
 */
public class AlertDialogUtil {

    /**
     * 显示系统对话框
     *
     * @param msg            提示信息
     * @param msgColor       提示信息颜色
     * @param hasCancel      是否包含取消按钮
     * @param canCancle      是否能够点击外部关闭
     * @param okText         确定按钮文本
     * @param cancelText     取消按钮文本
     * @param okListener     确定按钮回调
     * @param cancelListener 取消按钮回调
     */
    public static void show(Context context, String msg, int msgColor, boolean hasCancel, boolean canCancle, String okText,
                            String cancelText, final DialogInterface.OnClickListener okListener, final DialogInterface.OnClickListener cancelListener) {
        LinearLayout dialogview = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
        TextView tvMsg = (TextView) dialogview.findViewById(R.id.tvMsg);
        TextView tvOk = (TextView) dialogview.findViewById(R.id.tvOk);
        TextView tvCancel = (TextView) dialogview.findViewById(R.id.tvCancel);

        dialogview.findFocus();
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(dialogview);
        dialog.setCancelable(canCancle);

        tvMsg.setText(msg);
        tvMsg.setTextColor(context.getResources().getColor(msgColor));
        if (!hasCancel) {
            tvCancel.setVisibility(View.GONE);
        } else {
            tvCancel.setText(cancelText);
            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (cancelListener != null) {
                        cancelListener.onClick(dialog, 1);
                    }
                }
            });
        }
        tvOk.setText(okText);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (okListener != null) {
                    okListener.onClick(dialog, 0);
                }
            }
        });
    }

    public static void show(Context context, String msg, boolean hasCancel, String okText,
                            String cancelText, final DialogInterface.OnClickListener okListener, final DialogInterface.OnClickListener cancelListener) {
        show(context, msg, R.color.black, hasCancel, true, okText, cancelText, okListener, cancelListener);
    }

    public static void show(Context context, String msg) {
        show(context, msg, R.color.black, false, false, "确定", "", null, null);
    }

    public static void showGreen(Context context, String msg) {
        show(context, msg, R.color.green, false, false, "确定", "", null, null);
    }

    public static void show(Context context, String msg, DialogInterface.OnClickListener okListener) {
        show(context, msg, R.color.black, false, false, "确定", "", okListener, null);
    }

    public static void showGreen(Context context, String msg, DialogInterface.OnClickListener okListener) {
        show(context, msg, R.color.green, false, false, "确定", "", okListener, null);
    }
}
