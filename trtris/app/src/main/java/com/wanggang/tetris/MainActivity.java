package com.wanggang.tetris;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanggang.tetris.util.AttrUtil;
import com.wanggang.tetris.util.SoundManage;
import com.wanggang.tetris.util.ToastUtils;
import com.wanggang.tetris.widget.Tetris;
import com.wanggang.tetris.widget.TetrisNext;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends Activity implements View.OnClickListener, View.OnTouchListener {

    ImageButton btnLeft, btnRight, btnDown, btnChange, btnVoice, btnSetting;
    LinearLayout gameLayout, nextLayout;
    RelativeLayout rootLayout;
    ImageView ivBg;
    TextView tvScoreTitle, tvScore;
    TextView tvHighScoreTitle, tvHighScore;
    TextView tvNextTitle;
    TextView btnFull;

    Disposable speedDisposable;
    Disposable leftDisposable;
    Disposable rightDisposable;

    Disposable busDisposable;

    Tetris tetris;
    TetrisNext gameNext;

    long score = 0;
    private long backTime = 2000;
    private long curTime;

    boolean leftTouch;
    boolean rightTouch;
    boolean downTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String theme = ACache.get(this).getAsString("theme");
        if (TextUtils.isEmpty(theme)) {
            setTheme(R.style.GreenTheme);
        } else {
            setTheme(Integer.parseInt(theme));
        }
        setContentView(R.layout.activity_main);

        tetris = (Tetris) findViewById(R.id.game);
        gameNext = (TetrisNext) findViewById(R.id.gameNext);
        btnLeft = (ImageButton) findViewById(R.id.btnLeft);
        btnRight = (ImageButton) findViewById(R.id.btnRight);
        btnDown = (ImageButton) findViewById(R.id.btnDown);
        btnChange = (ImageButton) findViewById(R.id.btnChange);
        btnVoice = (ImageButton) findViewById(R.id.btnVoice);
        btnSetting = (ImageButton) findViewById(R.id.btnSetting);
        tvScoreTitle = (TextView) findViewById(R.id.tvScoreTitle);
        tvScore = (TextView) findViewById(R.id.tvScore);
        btnFull = (TextView) findViewById(R.id.btnFull);
        tvHighScoreTitle = (TextView) findViewById(R.id.tvHighScoreTitle);
        tvHighScore = (TextView) findViewById(R.id.tvHighScore);
        tvNextTitle = (TextView) findViewById(R.id.tvNextTitle);
        gameLayout = (LinearLayout) findViewById(R.id.gameLayout);
        nextLayout = (LinearLayout) findViewById(R.id.nextLayout);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        ivBg = (ImageView) findViewById(R.id.ivBg);

        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnFull.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnVoice.setOnClickListener(this);
        btnDown.setOnTouchListener(this);
        btnLeft.setOnTouchListener(this);
        btnRight.setOnTouchListener(this);

        setViewBg();
        setBackgroud();

        busDisposable = RxBus.getInstance().subscribe(BusModel.class, new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                BusModel busModel = (BusModel) o;

                if (busModel.score == -1) {
                    score = 0;
                } else {
                    score += busModel.score;
                }
                tvScore.setText(score + "");

                String highScore = ACache.get(MainActivity.this).getAsString("highScore");
                if (!TextUtils.isEmpty(highScore)) {
                    if (score > Long.parseLong(highScore)) {
                        tvHighScore.setText(score + "");
                        ACache.get(MainActivity.this).put("highScore", score);
                    }
                } else {
                    tvHighScore.setText(score + "");
                    ACache.get(MainActivity.this).put("highScore", score);
                }

                if (busModel.trtrisModle != null) {
                    gameNext.setTrtrisModle(busModel.trtrisModle);
                }
            }
        });

        SoundManage.init(this);
        tetris.start();

        String noVocie = ACache.get(this).getAsString("noVocie");
        btnVoice.setSelected(!"1".equals(noVocie));

        String highScore = ACache.get(MainActivity.this).getAsString("highScore");
        if (!TextUtils.isEmpty(highScore)) {
            tvHighScore.setText(highScore);
        }

    }

    private void setViewBg() {
        GradientDrawable tabBg = (GradientDrawable) tvScoreTitle.getBackground();
        tabBg.setColor(AttrUtil.getColorByAttrId(this, R.attr.mainColor));

        tvScoreTitle.setBackground(tabBg);
        tvHighScoreTitle.setBackground(tabBg);
        tvNextTitle.setBackground(tabBg);

        tvScore.setBackground(AttrUtil.getTabBorder(this));
        tvScore.setTextColor(AttrUtil.getColorByAttrId(this, R.attr.mainColor));
        tvHighScore.setTextColor(AttrUtil.getColorByAttrId(this, R.attr.mainColor));
        tvHighScore.setBackground(AttrUtil.getTabBorder(this));
        nextLayout.setBackground(AttrUtil.getTabBorder(this));

        gameLayout.setBackground(AttrUtil.getGameBorder(this));

        btnLeft.setBackground(AttrUtil.getButtonBg(this));
        btnRight.setBackground(AttrUtil.getButtonBg(this));
        btnDown.setBackground(AttrUtil.getButtonBg(this));
        btnChange.setBackground(AttrUtil.getButtonBg(this));
        btnVoice.setBackground(AttrUtil.getButtonBg(this));
        btnSetting.setBackground(AttrUtil.getButtonBg(this));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLeft) {
            if (!leftTouch) {
                tetris.left();
                SoundManage.playMoveSound();
            }
            leftTouch = false;
        } else if (v.getId() == R.id.btnRight) {
            if (!rightTouch) {
                tetris.right();
                SoundManage.playMoveSound();
            }
            rightTouch = false;
        } else if (v.getId() == R.id.btnChange) {
            tetris.change();
            SoundManage.playRotateSound();
        } else if (v.getId() == R.id.btnDown) {
            if (!downTouch) {
                tetris.down();
                SoundManage.playMoveSound();
            }
            downTouch = false;
        } else if (v.getId() == R.id.btnSetting) {
            SettingActivity.launch(this);
        } else if (v.getId() == R.id.btnVoice) {
            btnVoice.setSelected(!btnVoice.isSelected());
            if (btnVoice.isSelected()) {
                ACache.get(this).put("noVocie", "0");
                SoundManage.stopBgSound();
            } else {
                ACache.get(this).put("noVocie", "1");
                SoundManage.playBgSound();
            }
        } else if (v.getId() == R.id.btnFull) {
            ACache.get(this).put("onehand", "");
            setScreenMode();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        tetris.play();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SoundManage.stopBgSound();

        tetris.pause();
    }

    @Override
    protected void onDestroy() {
        LogUtil.log("onDestroy");
        if (leftDisposable != null && !leftDisposable.isDisposed()) {
            leftDisposable.dispose();
        }
        if (rightDisposable != null && !rightDisposable.isDisposed()) {
            rightDisposable.dispose();
        }
        if (speedDisposable != null && !speedDisposable.isDisposed()) {
            speedDisposable.dispose();
            tetris.setSpeedEnable(true);
            tetris.speedBack();
        }
        if (busDisposable != null && !busDisposable.isDisposed()) {
            busDisposable.dispose();
        }
        SoundManage.stopBgSound();
        tetris.destory();
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (v.getId() == R.id.btnLeft) {
                Observable.interval(100, 50, TimeUnit.MILLISECONDS)
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                leftDisposable = disposable;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                leftTouch = true;
                                tetris.left();
                            }
                        });
            } else if (v.getId() == R.id.btnRight) {
                Observable.interval(100, 50, TimeUnit.MILLISECONDS)
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                rightDisposable = disposable;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                rightTouch = true;
                                tetris.right();
                            }
                        });
            } else if (v.getId() == R.id.btnDown) {
                Observable.timer(100, TimeUnit.MILLISECONDS)
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                speedDisposable = disposable;
                            }
                        })
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                downTouch = true;
                                SoundManage.playDownSound();
                                tetris.crashDown();
                            }
                        });
            }
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            LogUtil.log("ACTION_UP");
            if (v.getId() == R.id.btnLeft) {
                if (leftDisposable != null && !leftDisposable.isDisposed()) {
                    leftDisposable.dispose();
                }
            } else if (v.getId() == R.id.btnRight) {
                if (rightDisposable != null && !rightDisposable.isDisposed()) {
                    rightDisposable.dispose();
                }
            } else if (v.getId() == R.id.btnDown) {
                if (speedDisposable != null && !speedDisposable.isDisposed()) {
                    speedDisposable.dispose();
                    tetris.setSpeedEnable(true);
                    tetris.speedBack();
                }
            }
        }
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - curTime > backTime) {
                ToastUtils.showToast("再按一次退出游戏");
                curTime = System.currentTimeMillis();
            } else {
                onBackPressed();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            if (data.getBooleanExtra("reLoad", false)) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            } else if (data.getBooleanExtra("handChange", false)) {
                setScreenMode();
                setBackgroud();
            }
        }
    }

    // 设置操作模式
    private void setScreenMode() {
        String onehand = ACache.get(this).getAsString("onehand");
        if ("left".equals(onehand)) {
            rootLayout.setPivotX(0);
            rootLayout.setPivotY(rootLayout.getHeight());
            rootLayout.setScaleX(0.7f);
            rootLayout.setScaleY(0.7f);
        } else if ("right".equals(onehand)) {
            rootLayout.setPivotX(rootLayout.getWidth());
            rootLayout.setPivotY(rootLayout.getHeight());
            rootLayout.setScaleX(0.7f);
            rootLayout.setScaleY(0.7f);
        } else {
            rootLayout.setPivotX(0);
            rootLayout.setPivotY(0);
            rootLayout.setScaleX(1f);
            rootLayout.setScaleY(1f);
        }
    }

    // 设置背景
    private void setBackgroud() {
        String bgPic = ACache.get(this).getAsString("bgPic");
        if (!TextUtils.isEmpty(bgPic)) {
            Glide.with(this).load(bgPic).into(ivBg);
        }
    }
}
