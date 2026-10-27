package com.byd.music.utils;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.util.Utils;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class MusicToastUtils {
    public static final int SHOW_TYPE_CENTER = 3;
    public static final int SHOW_TYPE_LONG = 2;
    public static final int SHOW_TYPE_SHORT = 1;
    public static final int SHOW_TYPE_SUCCESS = 4;
    private static final String TAG = "MusicToastUtils";
    private static final MusicToastUtils mInstance = new MusicToastUtils();
    private ViewGroup activityView;
    private Activity context;
    private String lastContent;
    private WindowManager.LayoutParams mParams;
    private View mView;
    private WindowManager mWM;
    private final int shortTime = 2000;
    private final int longTime = 3500;
    private long lastContentTime = 0;
    private int lastShowType = 0;

    public static MusicToastUtils getInstance() {
        return mInstance;
    }

    public synchronized void init(Activity activity) {
        synchronized (MusicToastUtils.class) {
            if (activity == null) {
                return;
            }
            if (this.context == activity) {
                return;
            }
            unInit(activity);
            this.context = activity;
            this.mWM = activity.getWindowManager();
            this.mParams = new WindowManager.LayoutParams();
            ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
            this.activityView = viewGroup;
            this.mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.music_toast_layout, (ViewGroup) null);
            if (!TextUtils.isEmpty(this.lastContent) && System.currentTimeMillis() - this.lastContentTime < 2000) {
                showToast(this.lastContent, this.lastShowType);
                this.lastContent = "";
            }
        }
    }

    public void showToast(String str, int i) {
        if (this.mView == null || TextUtils.isEmpty(str)) {
            this.lastContent = str;
            this.lastContentTime = System.currentTimeMillis();
            this.lastShowType = i;
            return;
        }
        initParams();
        if (i == 1) {
            showShortToast(str);
            return;
        }
        if (i == 2) {
            showLongToast(str);
        } else if (i == 3) {
            showCenterToast(str);
        } else {
            if (i != 4) {
                return;
            }
            showSuccessToast(str);
        }
    }

    private void showShortToast(final String str) {
        Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.byd.music.utils.MusicToastUtils.1
            @Override // java.lang.Runnable
            public void run() {
                LinearLayout linearLayout = (LinearLayout) MusicToastUtils.this.mView.findViewById(R.id.ll_toast);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.bottomMargin = 0;
                layoutParams.topMargin = com.blankj.utilcode.util.ScreenUtils.getAppScreenHeight() / 3;
                linearLayout.setLayoutParams(layoutParams);
                TextView textView = (TextView) MusicToastUtils.this.mView.findViewById(R.id.tv_toast);
                textView.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.color_text_white_front));
                textView.setPadding((int) MusicToastUtils.this.mView.getResources().getDimension(R.dimen.dp_24), 0, (int) MusicToastUtils.this.mView.getResources().getDimension(R.dimen.dp_24), 0);
                ((ImageView) MusicToastUtils.this.mView.findViewById(R.id.iv_toast)).setVisibility(8);
                textView.setText(str);
                MusicToastUtils.this.addView();
            }
        }, 300L);
        Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.byd.music.utils.MusicToastUtils.2
            @Override // java.lang.Runnable
            public void run() {
                MusicToastUtils.this.removeView();
            }
        }, 2000L);
    }

    private void showCenterToast(final String str) {
        Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.byd.music.utils.MusicToastUtils.3
            @Override // java.lang.Runnable
            public void run() {
                LinearLayout linearLayout = (LinearLayout) MusicToastUtils.this.mView.findViewById(R.id.ll_toast);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.bottomMargin = 0;
                layoutParams.topMargin = 0;
                linearLayout.setLayoutParams(layoutParams);
                TextView textView = (TextView) MusicToastUtils.this.mView.findViewById(R.id.tv_toast);
                textView.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.color_text_white_front));
                textView.setPadding((int) MusicToastUtils.this.mView.getResources().getDimension(R.dimen.dp_24), 0, (int) MusicToastUtils.this.mView.getResources().getDimension(R.dimen.dp_24), 0);
                textView.setText(str);
                ((ImageView) MusicToastUtils.this.mView.findViewById(R.id.iv_toast)).setVisibility(8);
                MusicToastUtils.this.addView();
            }
        }, 300L);
        Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.byd.music.utils.MusicToastUtils.4
            @Override // java.lang.Runnable
            public void run() {
                MusicToastUtils.this.removeView();
            }
        }, 2000L);
    }

    private void showLongToast(final String str) {
        Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.byd.music.utils.MusicToastUtils.5
            @Override // java.lang.Runnable
            public void run() {
                LinearLayout linearLayout = (LinearLayout) MusicToastUtils.this.mView.findViewById(R.id.ll_toast);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.bottomMargin = 0;
                layoutParams.topMargin = com.blankj.utilcode.util.ScreenUtils.getAppScreenHeight() / 3;
                linearLayout.setLayoutParams(layoutParams);
                TextView textView = (TextView) MusicToastUtils.this.mView.findViewById(R.id.tv_toast);
                textView.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.color_text_white_front));
                textView.setPadding((int) MusicToastUtils.this.mView.getResources().getDimension(R.dimen.dp_24), 0, (int) MusicToastUtils.this.mView.getResources().getDimension(R.dimen.dp_24), 0);
                textView.setText(str);
                ((ImageView) MusicToastUtils.this.mView.findViewById(R.id.iv_toast)).setVisibility(8);
                MusicToastUtils.this.addView();
            }
        }, 300L);
        Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.byd.music.utils.MusicToastUtils.6
            @Override // java.lang.Runnable
            public void run() {
                MusicToastUtils.this.removeView();
            }
        }, 3500L);
    }

    private void showSuccessToast(final String str) {
        Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.byd.music.utils.MusicToastUtils.7
            @Override // java.lang.Runnable
            public void run() {
                LinearLayout linearLayout = (LinearLayout) MusicToastUtils.this.mView.findViewById(R.id.ll_toast);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.bottomMargin = 0;
                layoutParams.topMargin = com.blankj.utilcode.util.ScreenUtils.getAppScreenHeight() / 3;
                linearLayout.setLayoutParams(layoutParams);
                TextView textView = (TextView) MusicToastUtils.this.mView.findViewById(R.id.tv_toast);
                textView.setTextColor(ContextCompat.getColor(Utils.getApp(), R.color.color_toast_text_success));
                textView.setText(str);
                textView.setPadding(0, 0, 36, 0);
                ImageView imageView = (ImageView) MusicToastUtils.this.mView.findViewById(R.id.iv_toast);
                imageView.setVisibility(0);
                imageView.setPadding(20, 0, 0, 0);
                imageView.setImageResource(R.drawable.icon_toast_ok);
                MusicToastUtils.this.addView();
            }
        }, 300L);
        Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.byd.music.utils.MusicToastUtils.8
            @Override // java.lang.Runnable
            public void run() {
                MusicToastUtils.this.removeView();
            }
        }, 2000L);
    }

    private void initParams() {
        if (Build.VERSION.SDK_INT < 25) {
            this.mParams.type = 2005;
        } else {
            this.mParams.type = 99;
        }
        this.mParams.height = -1;
        this.mParams.width = -1;
        this.mParams.format = -3;
        this.mParams.windowAnimations = 16973828;
        this.mParams.setTitle("ToastWithoutNotification");
        this.mParams.flags = 152;
        this.mParams.packageName = Utils.getApp().getPackageName();
        this.mParams.x = 0;
        this.mParams.y = 0;
        this.mParams.horizontalMargin = 0.0f;
        this.mParams.verticalMargin = 0.0f;
    }

    public synchronized void addView() {
        try {
            if (this.mWM != null && !this.mView.isAttachedToWindow()) {
                this.mWM.addView(this.mView, this.mParams);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "addView: " + e.toString());
        }
    }

    public synchronized void removeView() {
        try {
            if (this.mWM != null && this.mView != null && this.mView.isAttachedToWindow()) {
                this.mWM.removeViewImmediate(this.mView);
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "removeView: " + e.toString());
        }
    }

    public void unInit(Activity activity) {
        if (this.context != activity) {
            return;
        }
        this.context = null;
        removeView();
        this.mView = null;
        this.mWM = null;
        this.mParams = null;
    }
}
