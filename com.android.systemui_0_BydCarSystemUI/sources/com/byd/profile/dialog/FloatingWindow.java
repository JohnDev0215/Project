package com.byd.profile.dialog;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FloatingWindow {
    protected static final int HEIGHT = 1;
    protected static final int WIDTH = 0;
    protected boolean isFullScreen;
    protected boolean isShowing;
    protected Context mContext;
    protected int mHeight;
    protected WindowManager.LayoutParams mLayoutParams;
    protected int mType;
    protected int mWidth;
    protected WindowManager mWindowManager;

    protected abstract void initView();

    protected abstract boolean isFullScreen();

    protected abstract void setWidthAndHeight(int[] size);

    public FloatingWindow(Context context) {
        this(context, 0);
    }

    public FloatingWindow(Context context, int type) {
        this.mContext = context;
        this.mType = type;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.isFullScreen = isFullScreen();
        initSize();
        initLayoutParams();
    }

    private void initLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.type = 2024;
        this.mLayoutParams.format = -3;
        this.mLayoutParams.flags = 142608168;
        this.mLayoutParams.flags |= 16777216;
        this.mLayoutParams.gravity = 17;
        this.mLayoutParams.softInputMode = 16;
        this.mLayoutParams.setTitle("SafetyWarningWindowManager");
        this.mLayoutParams.packageName = this.mContext.getPackageName();
        if (this.isFullScreen) {
            this.mLayoutParams.width = -1;
            this.mLayoutParams.height = -1;
        } else {
            this.mLayoutParams.width = this.mWidth;
            this.mLayoutParams.height = this.mHeight;
        }
    }

    public void show(View view) {
        if (view != null) {
            this.isShowing = true;
            this.mWindowManager.addView(view, this.mLayoutParams);
        }
    }

    public void show(View view, int x, int y) {
        if (view != null) {
            this.mLayoutParams.x = x;
            this.mLayoutParams.y = y;
            this.mWindowManager.addView(view, this.mLayoutParams);
        }
    }

    public void dismiss(View view) {
        if (view != null) {
            this.isShowing = false;
            this.mWindowManager.removeView(view);
        }
    }

    private void initSize() {
        int[] iArr = new int[2];
        setWidthAndHeight(iArr);
        this.mWidth = iArr[0] == 0 ? -2 : this.mContext.getResources().getDimensionPixelSize(iArr[0]);
        this.mHeight = iArr[1] != 0 ? this.mContext.getResources().getDimensionPixelSize(iArr[1]) : -2;
    }

    public boolean isShowing() {
        return this.isShowing;
    }
}
