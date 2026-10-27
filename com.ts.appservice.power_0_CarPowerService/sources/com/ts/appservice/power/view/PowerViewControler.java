package com.ts.appservice.power.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class PowerViewControler {
    private static final int ALPHA_HALF = 217;
    private static final String TAG = "PowerViewControler";
    private static volatile PowerViewControler sPopControler;
    private boolean mIsRootAdded;
    private RootLayout mRootLayout;
    private WindowManager mWindowManager;

    private PowerViewControler(Context context) {
        initRootLayout(context);
        initManager(context);
    }

    private void initManager(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }

    private WindowManager.LayoutParams getSceneManagerWindowType() {
        WindowManager.LayoutParams mparams = new WindowManager.LayoutParams();
        mparams.screenOrientation = 0;
        mparams.flags = 75760448;
        mparams.format = -3;
        mparams.width = -1;
        mparams.height = -1;
        mparams.gravity = 51;
        return mparams;
    }

    public static PowerViewControler getInstance(Context context) {
        if (sPopControler == null) {
            synchronized (PowerViewControler.class) {
                if (sPopControler == null) {
                    sPopControler = new PowerViewControler(context);
                }
            }
        }
        return sPopControler;
    }

    public void initRootLayout(Context context) {
        if (this.mRootLayout == null) {
            ColorDrawable drawable = new ColorDrawable(-16777216);
            drawable.setAlpha(217);
            RootLayout rootLayout = new RootLayout(context);
            this.mRootLayout = rootLayout;
            rootLayout.setBackground(drawable);
        }
    }

    private void addRootLayout() {
        if (!this.mIsRootAdded) {
            this.mWindowManager.addView(this.mRootLayout, getSceneManagerWindowType());
            this.mIsRootAdded = true;
        }
    }

    private void remoRootLayout() {
        if (this.mIsRootAdded) {
            this.mWindowManager.removeView(this.mRootLayout);
            this.mIsRootAdded = false;
        }
    }

    public void dismiss() {
        if (this.mRootLayout != null) {
            clearView();
            remoRootLayout();
        }
    }

    public void showPowerAbnormalView(Context context, int warnflag) {
        addRootLayout();
        clearView();
        CarPowerAbnormalView abnormalView = new CarPowerAbnormalView(context, warnflag);
        LogUtil.info(TAG, "warnflag: " + warnflag);
        addView(abnormalView);
    }

    private void addView(View view) {
        view.setVisibility(0);
        if (view instanceof BaseLayout) {
            ((BaseLayout) view).onViewAdded();
            ((BaseLayout) view).onResume();
        }
        this.mRootLayout.addView(view);
    }

    private void removeView(View view) {
        if (view instanceof BaseLayout) {
            ((BaseLayout) view).onPause();
            ((BaseLayout) view).dismiss();
        }
        this.mRootLayout.removeView(view);
    }

    private void clearView() {
        for (int i = 0; i < this.mRootLayout.getChildCount(); i++) {
            View childView = this.mRootLayout.getChildAt(i);
            if (childView instanceof BaseLayout) {
                ((BaseLayout) childView).onPause();
                ((BaseLayout) childView).dismiss();
            }
        }
        this.mRootLayout.removeAllViews();
    }
}
