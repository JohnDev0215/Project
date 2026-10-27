package com.ts.appservice.power.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.appservice.power.CarPowerBinder;
import com.ts.appservice.power.hardkey.ExtHardKeyManager;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class PowerBlackViewControler {
    private static final float ALPHA = 0.0f;
    private static final String BACK_GROUND_COLOR = "#696969";
    private static final int INVALID_WINDOW_TYPE = -1;
    private static final int MARK_TEXT_SIZE = 15;
    private static final int POWER_ABNORMAL_SHOW = 1;
    private static final String TAG = PowerBlackViewControler.class.getSimpleName();
    private static int mBackgroundColor = -16777216;
    private static volatile PowerBlackViewControler sInstance;
    private ExtHardKeyManager mHardKeyManager;
    private boolean mIsShowBlackView = false;
    private RootLayout mRootLayout;
    private CarPowerBinder mTsCarPowerBinder;
    private WindowManager mWindowManager;

    public static PowerBlackViewControler getInstance(Context context, CarPowerBinder tsCarPowerBinder) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (PowerBlackViewControler.class) {
                if (sInstance == null) {
                    sInstance = new PowerBlackViewControler(context, tsCarPowerBinder);
                }
            }
        }
        return sInstance;
    }

    private PowerBlackViewControler(Context context, CarPowerBinder tsCarPowerBinder) {
        mBackgroundColor = Color.parseColor(BACK_GROUND_COLOR);
        LogUtil.info(TAG, "PowerBlackViewControler");
        this.mTsCarPowerBinder = tsCarPowerBinder;
        initManager(context);
    }

    private void initManager(Context context) {
        LogUtil.info(TAG, "initManager");
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mHardKeyManager = ExtHardKeyManager.getInstance(context, this.mTsCarPowerBinder);
    }

    private void initRootLayout(Context context, boolean isTransparent) {
        if (this.mRootLayout == null) {
            int color = isTransparent ? 0 : mBackgroundColor;
            ColorDrawable drawable = new ColorDrawable(color);
            RootLayout rootLayout = new RootLayout(context);
            this.mRootLayout = rootLayout;
            rootLayout.setBackground(drawable);
            addTagView(true, context, isTransparent);
            addMarkView(context, this.mRootLayout);
        }
    }

    private void addMarkView(Context context, RootLayout rootLayout) {
        TextView textView = new TextView(context);
        FrameLayout.LayoutParams rlp = new FrameLayout.LayoutParams(-1, -1);
        rlp.gravity = 17;
        textView.setLayoutParams(rlp);
        textView.setBackgroundColor(0);
        textView.setTextSize(15.0f);
        textView.setText("拦截touch");
        textView.setTextColor(-16777216);
        textView.setGravity(17);
        rootLayout.addView(textView);
        rootLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.ts.appservice.power.view.PowerBlackViewControler.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == 0) {
                    LogUtil.info(PowerBlackViewControler.TAG, "MotionEvent ACTION_DOWN");
                    return true;
                }
                return true;
            }
        });
    }

    private WindowManager.LayoutParams getSceneManagerWindowType(boolean isTransparent) {
        WindowManager.LayoutParams mparams = new WindowManager.LayoutParams();
        mparams.height = -1;
        mparams.width = -1;
        mparams.gravity = 17;
        if (isTransparent) {
            mparams.format = -3;
        }
        mparams.alpha = ALPHA;
        mparams.flags = 1792;
        mparams.type = 2021;
        if (mparams.type == -1) {
            LogUtil.error(TAG, "mSceneManager getWindowType error !Setting the type TYPE_SYSTEM_DIALOG !");
            return null;
        }
        return mparams;
    }

    private void setViewEnable(Boolean enable) {
        RootLayout rootLayout = this.mRootLayout;
        if (rootLayout == null) {
            LogUtil.error(TAG, "mRootLayout is null !");
            return;
        }
        int count = rootLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = this.mRootLayout.getChildAt(i);
            if (view instanceof TextView) {
                view.setEnabled(enable.booleanValue());
            }
        }
    }

    public void showBlackView(Context context, boolean isTransparent) {
        LogUtil.warning(TAG, "showBlackView mIsShowBlackView : " + this.mIsShowBlackView);
        if (!this.mIsShowBlackView) {
            WindowManager.LayoutParams params = getSceneManagerWindowType(isTransparent);
            if (params == null) {
                LogUtil.error(TAG, "LayoutParams is null ! Can not show the blackView !");
                return;
            }
            initRootLayout(context, isTransparent);
            DisplayMetrics metrics = new DisplayMetrics();
            this.mWindowManager.getDefaultDisplay().getRealMetrics(metrics);
            params.width = metrics.widthPixels > metrics.heightPixels ? metrics.widthPixels : metrics.heightPixels;
            params.height = metrics.widthPixels > metrics.heightPixels ? metrics.widthPixels : metrics.heightPixels;
            params.width *= 2;
            params.height *= 2;
            this.mWindowManager.addView(this.mRootLayout, params);
            this.mIsShowBlackView = true;
            this.mHardKeyManager.handleBlackModeKeyEvent();
        }
        setViewEnable(false);
    }

    public void updateParams() {
        if (this.mIsShowBlackView) {
            LogUtil.warning(TAG, "updateParams !");
            WindowManager.LayoutParams params = getSceneManagerWindowType(false);
            if (params == null) {
                LogUtil.error(TAG, "LayoutParams is null ! Can not show the blackView !");
                return;
            }
            DisplayMetrics metrics = new DisplayMetrics();
            this.mWindowManager.getDefaultDisplay().getRealMetrics(metrics);
            params.width = metrics.widthPixels > metrics.heightPixels ? metrics.widthPixels : metrics.heightPixels;
            params.height = metrics.widthPixels > metrics.heightPixels ? metrics.widthPixels : metrics.heightPixels;
            params.width *= 2;
            params.height *= 2;
            this.mWindowManager.updateViewLayout(this.mRootLayout, params);
        }
    }

    public void hideBlackView(Context context) {
        LogUtil.warning(TAG, "hideBlackView mIsShowBlackView : " + this.mIsShowBlackView);
        if (this.mIsShowBlackView) {
            this.mWindowManager.removeView(this.mRootLayout);
            this.mIsShowBlackView = false;
            this.mHardKeyManager.unhandleBlackModeKeyEvent();
        }
    }

    private void addTagView(boolean bool, final Context context, boolean isTransparent) {
        LogUtil.info(TAG, "addTagView bool : " + bool);
        TextView textView = new TextView(context);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(-1, -1);
        textView.setLayoutParams(rlp);
        textView.setTextColor(mBackgroundColor);
        textView.setBackgroundColor(isTransparent ? 0 : mBackgroundColor);
        textView.setGravity(3);
        textView.setOnTouchListener(new View.OnTouchListener() { // from class: com.ts.appservice.power.view.-$$Lambda$PowerBlackViewControler$rUfkcBuoddfaxVFP5e0GhtZ9SmE
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$addTagView$0$PowerBlackViewControler(context, view, motionEvent);
            }
        });
        if (bool) {
            this.mRootLayout.addView(textView);
        } else {
            this.mRootLayout.removeView(textView);
        }
    }

    public /* synthetic */ boolean lambda$addTagView$0$PowerBlackViewControler(Context context, View view, MotionEvent motionEvent) {
        LogUtil.info(TAG, "mTsBlackView onTouch");
        if (motionEvent.getAction() == 0) {
            LogUtil.info(TAG, "hide black view");
            this.mTsCarPowerBinder.setScreenBrightnessOnOff(1, 0);
            hideBlackView(context);
        }
        return true;
    }
}
