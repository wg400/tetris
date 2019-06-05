package com.wanggang.tetris;

import com.wanggang.tetris.widget.TrtrisModle;

/**
 * Created by wg on 2017/10/17.
 */

public class BusModel {
    public long score;
    public TrtrisModle trtrisModle;

    public BusModel(long score) {
        this.score = score;
    }

    public BusModel(TrtrisModle trtrisModle) {
        this.trtrisModle = trtrisModle;
    }

    public BusModel(long score, TrtrisModle trtrisModle) {
        this.score = score;
        this.trtrisModle = trtrisModle;
    }

    @Override
    public String toString() {
        return "BusModel{" +
                "score=" + score +
                ", trtrisModle=" + trtrisModle +
                '}';
    }
}
