package com.wanggang.tetris;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import com.wanggang.tetris.util.AlertDialogUtil;

import java.io.File;

public class SettingActivity extends Activity implements View.OnClickListener {

    RadioGroup themeGroup, styleGroup;
    ImageButton btnBack;
    ToggleButton btnKey, btnBg, btnHand;

    LinearLayout titleLayout;
    LinearLayout btnBgPic;
    LinearLayout btnGamePic;

    boolean reLoad;
    boolean handChange;

    Uri outFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String theme = ACache.get(this).getAsString("theme");
        if (TextUtils.isEmpty(theme)) {
            setTheme(R.style.GreenTheme);
        } else {
            setTheme(Integer.parseInt(theme));
        }

        setContentView(R.layout.activity_setting);
        themeGroup = (RadioGroup) findViewById(R.id.themeGroup);
        styleGroup = (RadioGroup) findViewById(R.id.styleGroup);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        titleLayout = (LinearLayout) findViewById(R.id.titleLayout);
        btnBgPic = (LinearLayout) findViewById(R.id.btnBgPic);
        btnGamePic = (LinearLayout) findViewById(R.id.btnGamePic);
        btnKey = (ToggleButton) findViewById(R.id.btnKey);
        btnBg = (ToggleButton) findViewById(R.id.btnBg);
        btnHand = (ToggleButton) findViewById(R.id.btnHand);

        themeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.greenRadio) {
                    ACache.get(SettingActivity.this).put("theme", R.style.GreenTheme);
                } else if (i == R.id.redRadio) {
                    ACache.get(SettingActivity.this).put("theme", R.style.RedTheme);
                } else if (i == R.id.blueRadio) {
                    ACache.get(SettingActivity.this).put("theme", R.style.BlueTheme);
                } else if (i == R.id.yellowRadio) {
                    ACache.get(SettingActivity.this).put("theme", R.style.YellowTheme);
                }
                reLoad = true;
            }
        });

        if (!TextUtils.isEmpty(theme)) {
            switch (theme) {
                case R.style.GreenTheme+"":
                    themeGroup.check(R.id.greenRadio);
                    break;
                case R.style.RedTheme+"":
                    themeGroup.check(R.id.redRadio);
                    break;
                case R.style.BlueTheme+"":
                    themeGroup.check(R.id.blueRadio);
                    break;
                case R.style.YellowTheme+"":
                    themeGroup.check(R.id.yellowRadio);
                    break;
            }
        }

        styleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.lightRadio) {
                    ACache.get(SettingActivity.this).put("style", "light");
                } else if (i == R.id.darkRadio) {
                    ACache.get(SettingActivity.this).put("style", "dark");
                }
                handChange = true;
            }
        });

        String style = ACache.get(this).getAsString("style");
        if (!TextUtils.isEmpty(style)) {
            switch (style) {
                case "light":
                    styleGroup.check(R.id.lightRadio);
                    break;
                case "dark":
                    styleGroup.check(R.id.darkRadio);
                    break;
            }
        }


        if (!TextUtils.isEmpty(theme)) {
            setTheme(Integer.parseInt(theme));
        }

        btnBack.setOnClickListener(this);
        btnBgPic.setOnClickListener(this);
        btnGamePic.setOnClickListener(this);

        String keyVocie = ACache.get(this).getAsString("keyVocie");
        String bgVocie = ACache.get(this).getAsString("bgVocie");

        btnBg.setChecked("1".equals(bgVocie));
        btnKey.setChecked("1".equals(keyVocie));

        btnBg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ACache.get(SettingActivity.this).put("bgVocie", b ? "1": "0");
            }
        });
        btnKey.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ACache.get(SettingActivity.this).put("keyVocie", b ? "1": "0");
            }
        });

        String onehand = ACache.get(this).getAsString("onehand");
        btnHand.setChecked(!TextUtils.isEmpty(onehand));

        btnHand.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AlertDialogUtil.show(SettingActivity.this, "请选择您玩游戏的手", true, "左手", "右手", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ACache.get(SettingActivity.this).put("onehand", "left");
                            handChange = true;
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ACache.get(SettingActivity.this).put("onehand", "right");
                            handChange = true;
                        }
                    });
                } else {
                    ACache.get(SettingActivity.this).remove("onehand");
                    handChange = true;
                }
            }
        });

    }

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivityForResult(intent, 101);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnBack) {
            back();
        } else if (view.getId() == R.id.btnBgPic) {

            Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
            // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(pickIntent, 666);

        } else if (view.getId() == R.id.btnGamePic) {
            Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
            // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
            pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(pickIntent, 667);
        }
    }

    public Uri createOutFile() {
        String path = "/sdcard/tetris/pic/";
        File dir = new File(path);
        if (!dir.exists()) {
            boolean flag = dir.mkdirs();
        }
        File file = new File(path + System.currentTimeMillis() + ".jpg");
        return Uri.fromFile(file);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 2);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 480);
        intent.putExtra("outputY", 960);
        //是否返回bitmap对象
        intent.putExtra("return-data", false);
        outFileUri = createOutFile();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outFileUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片的格式
        startActivityForResult(intent, 668);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 666) {
                ACache.get(SettingActivity.this).put(this, "bgPic", data.getData());
                handChange = true;
            } else if (requestCode == 667) {
                startPhotoZoom(data.getData());
            } else if (requestCode == 668) {
                ACache.get(SettingActivity.this).put(this, "gamePic", outFileUri);
            }
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
        }
        return true;
    }

    private void back() {
        Intent intent = new Intent();
        if (reLoad) {
            intent.putExtra("reLoad", reLoad);
            setResult(RESULT_OK, intent);
        }
        if (handChange) {
            intent.putExtra("handChange", handChange);
            setResult(RESULT_OK, intent);
        }
        finish();
    }
}
