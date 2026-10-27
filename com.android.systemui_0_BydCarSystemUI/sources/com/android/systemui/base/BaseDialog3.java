package com.android.systemui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import com.android.systemui.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseDialog3 extends Dialog {
    protected Context mContext;
    protected int mGravity;
    protected int mHeight;
    protected int mLocationX;
    protected int mLocationY;
    protected int mWidth;

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initSize();

    public BaseDialog3(Context context) {
        this(context, R.style.dialog_style);
    }

    public BaseDialog3(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        onCreate();
    }

    public final void onCreate() {
        BydLogHelper.logD("uiMode = " + this.mContext.getResources().getConfiguration().uiMode);
        setContentView(getLayoutId());
        setAttributes();
        addFlag();
        initData();
    }

    private void addFlag() {
        getWindow().addFlags(402653440);
    }

    protected void setAttributes() {
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
        attributes.type = getWindowType();
        attributes.packageName = this.mContext.getPackageName();
        attributes.flags = 787752;
        getWindow().setAttributes(attributes);
    }

    protected int getWindowType() {
        return Build.VERSION.SDK_INT >= 26 ? 2008 : 2003;
    }
}
