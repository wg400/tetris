package com.wanggang.tetris.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.wanggang.tetris.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Administrator on 2018/1/5.
 */

public class NotifityUtil {

    // 支付宝发送通知
    public static void sendZhifuNotifity(Context context, String title, String content, Intent intent, int notifityId) {

        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(
                context, 0, intent, 0);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.layout_notifity);
        remoteViews.setTextViewText(R.id.tvTitle, title);
        remoteViews.setTextViewText(R.id.tvContent, content);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.icon_zhifu_small)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentIntent(contentIntent);
        mBuilder.setAutoCancel(true);
        mBuilder.setContent(remoteViews);
        mBuilder.setContentIntent(contentIntent);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            mBuilder.setCustomBigContentView(remoteViews);
        }

        mNotifyMgr.notify(notifityId, mBuilder.build());
    }

    // 支付宝发送通知
    public static void sendTaobaoNotifity(Context context, String title, String content, Intent intent, int notifityId) {
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(
                context, 0, intent, 0);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.layout_notifity);
        remoteViews.setImageViewResource(R.id.ivHead, R.mipmap.taobao196);
        remoteViews.setTextViewText(R.id.tvTitle, title);
        remoteViews.setTextViewText(R.id.tvContent, content);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.taobao48)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setContentIntent(contentIntent);
        mBuilder.setAutoCancel(true);
        mBuilder.setContent(remoteViews);
        mBuilder.setContentIntent(contentIntent);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            mBuilder.setCustomBigContentView(remoteViews);
        }

        mNotifyMgr.notify(notifityId, mBuilder.build());
    }

}
