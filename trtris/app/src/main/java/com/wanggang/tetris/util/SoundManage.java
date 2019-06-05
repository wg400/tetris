package com.wanggang.tetris.util;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.wanggang.tetris.ACache;
import com.wanggang.tetris.R;

/**
 * Created by wg on 2017/10/20.
 */

public class SoundManage {

    private static SoundManage instance;

    private SoundPool buttonPool;
    private int overId;
    private int removeId;
    private int rotateId;
    private int downId;
    private int coolId;
    private int bgId;

    private Context context;

    private static int bgStreamID;

    public static void init(Context context) {
        instance = new SoundManage();
        instance.context = context;
        instance.buttonPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
        instance.overId = instance.buttonPool.load(context, R.raw.over, 1);
        instance.removeId = instance.buttonPool.load(context, R.raw.remove, 1);
        instance.rotateId = instance.buttonPool.load(context, R.raw.rotate, 1);
        instance.downId = instance.buttonPool.load(context, R.raw.down, 1);
        instance.coolId = instance.buttonPool.load(context, R.raw.cool, 1);
        instance.bgId = instance.buttonPool.load(context, R.raw.bg, 1);

        ((Activity) context).setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    public static void playOverSound() {
        String noVocie = ACache.get(instance.context).getAsString("noVocie");
        String bgVocie = ACache.get(instance.context).getAsString("bgVocie");

        if ("1".equals(noVocie) && "1".equals(bgVocie)) {
            instance.buttonPool.play(instance.overId, 1, 1, 0, 0, 1);
        }
    }

    public static void playMoveSound() {
        String noVocie = ACache.get(instance.context).getAsString("noVocie");
        String keyVocie = ACache.get(instance.context).getAsString("keyVocie");

        if ("1".equals(noVocie) && "1".equals(keyVocie)) {
            instance.buttonPool.play(instance.removeId, 1, 1, 0, 0, 1);
        }
    }

    public static void playRotateSound() {
        String noVocie = ACache.get(instance.context).getAsString("noVocie");
        String keyVocie = ACache.get(instance.context).getAsString("keyVocie");

        if ("1".equals(noVocie) && "1".equals(keyVocie)) {
            instance.buttonPool.play(instance.rotateId, 1, 1, 0, 0, 1);
        }
    }

    public static void playDownSound() {
        String noVocie = ACache.get(instance.context).getAsString("noVocie");
        String keyVocie = ACache.get(instance.context).getAsString("keyVocie");

        if ("1".equals(noVocie) && "1".equals(keyVocie)) {
            instance.buttonPool.play(instance.downId, 1, 1, 0, 0, 1);
        }
    }

    public static void playCoolSound() {
        String noVocie = ACache.get(instance.context).getAsString("noVocie");
        String keyVocie = ACache.get(instance.context).getAsString("keyVocie");

        if ("1".equals(noVocie) && "1".equals(keyVocie)) {
            instance.buttonPool.play(instance.coolId, 1, 1, 0, 0, 1);
        }
    }

    public static void playBgSound() {

        String noVocie = ACache.get(instance.context).getAsString("noVocie");
        String bgVocie = ACache.get(instance.context).getAsString("bgVocie");

        if ("1".equals(noVocie) && "1".equals(bgVocie)) {
            if (bgStreamID == 0) {
                bgStreamID = instance.buttonPool.play(instance.bgId, 1, 1, 0, -1, 1);
            }
        }
    }

    public static void stopBgSound() {
        if (bgStreamID != 0) {
            instance.buttonPool.stop(bgStreamID);
            bgStreamID = 0;
        }
    }

}
