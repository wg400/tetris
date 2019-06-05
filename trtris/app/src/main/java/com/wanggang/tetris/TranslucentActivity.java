package com.wanggang.tetris;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/1/5.
 */

public class TranslucentActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent);

        int style = getIntent().getIntExtra("style", 0);
        String text = getIntent().getStringExtra("text");
        if (style == 2) {
            try {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(text);
                intent.setData(content_url);
                intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
                startActivity(intent);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(text);
                intent.setData(content_url);
                startActivity(intent);
            } finally {
                finish();
            }
        } else {
            ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData mClipData = ClipData.newPlainText("Label", text);
            mClipboardManager.setPrimaryClip(mClipData);
            try {
                PackageManager packageManager
                        = getApplicationContext().getPackageManager();
                Intent intent1 = null;
                if (style == 0) {
                    intent1 = packageManager.
                            getLaunchIntentForPackage("com.eg.android.AlipayGphone");
                } else if (style == 1) {
                    intent1 = packageManager.
                            getLaunchIntentForPackage("com.taobao.taobao");
                }
                startActivity(intent1);
            } catch (Exception e) {

            } finally {
                finish();
            }
        }


    }

    public static Intent getLaunch(Context context, String text, int style) {
        Intent intent = new Intent(context, TranslucentActivity.class);
        intent.putExtra("text", text);
        intent.putExtra("style", style);
        return intent;
    }
}
