package com.android.systemui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseToast extends Dialog {
    protected Context mContext;
    protected int mGravity;
    protected int mHeight;
    protected int mLocationX;
    protected int mLocationY;
    protected View mView;
    protected int mWidth;

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initSize();

    public BaseToast(Context context) {
        this(context, R.style.dialog_style);
    }

    public BaseToast(Context context, int themeResId) {
        super(context, themeResId);
        this.mGravity = 17;
        this.mContext = context;
        onCreate();
    }

    protected final void onCreate() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(getLayoutId(), (ViewGroup) null);
        this.mView = viewInflate;
        setContentView(viewInflate);
        setAttributes();
        addFlag();
        initData();
    }

    private void addFlag() {
        getWindow().addFlags(402653440);
    }

    private void setAttributes() {
        initSize();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        int i = this.mWidth;
        if (i != 0 && this.mHeight != 0) {
            attributes.width = i;
            attributes.height = this.mHeight;
        } else {
            attributes.width = -2;
            attributes.height = -2;
        }
        int i2 = this.mLocationX;
        if (i2 != 0) {
            attributes.x = i2;
        }
        int i3 = this.mLocationY;
        if (i3 != 0) {
            attributes.y = i3;
        }
        attributes.gravity = this.mGravity;
        attributes.format = -3;
        attributes.dimAmount = 0.0f;
        if (Build.VERSION.SDK_INT >= 26) {
            attributes.type = 2024;
        } else {
            attributes.type = 2003;
        }
        attributes.packageName = this.mContext.getPackageName();
        attributes.flags = 787752;
        getWindow().setAttributes(attributes);
    }
}
