package com.android.provision.base;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.android.provision.R;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseDialog extends Dialog {
    protected Context mContext;
    protected View mView;

    protected abstract int getLayoutId();

    protected void initData() {
    }

    public BaseDialog(Context context) {
        this(context, R.style.dialog_style);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        onCreate();
    }

    public void onCreate() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(getLayoutId(), (ViewGroup) null);
        this.mView = viewInflate;
        setContentView(viewInflate);
        setAttributes();
        initData();
    }

    private void setAttributes() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        getWindow().addFlags(263432);
        setCanceledOnTouchOutside(true);
    }
}
