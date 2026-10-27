package com.android.systemui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseDialog extends Dialog {
    protected Context mContext;
    protected int mGravity;
    protected int mHeight;
    protected int mLocationX;
    protected int mLocationY;
    protected int mWidth;

    protected abstract int getLayoutId();

    protected boolean isFullScreen() {
        return true;
    }

    public BaseDialog(Context context) {
        this(context, R.style.dialog_style);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        onCreate();
    }

    public final void onCreate() {
        setContentView(getLayoutId());
        setAttributes();
        addFlag();
    }

    private void addFlag() {
        getWindow().addFlags(402653440);
    }

    private void setAttributes() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        if (isFullScreen()) {
            attributes.width = -1;
            attributes.height = -1;
        } else {
            int i = this.mWidth;
            if (i != 0 && this.mHeight != 0) {
                attributes.width = i;
                attributes.height = this.mHeight;
            } else {
                attributes.width = -2;
                attributes.height = -2;
            }
        }
        attributes.gravity = 17;
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
        attributes.type = setWindowType();
        attributes.packageName = this.mContext.getPackageName();
        attributes.flags = 131072;
        getWindow().setAttributes(attributes);
    }

    protected int setWindowType() {
        return Build.VERSION.SDK_INT >= 26 ? 2024 : 2003;
    }
}
