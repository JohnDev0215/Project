package com.ts.androidauto.app.proxy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.ts.androidauto.app.R;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseDialog extends Dialog implements View.OnClickListener {
    public abstract void initView();

    public abstract void initWindowView();

    public BaseDialog(Context context) {
        super(context, R.style.CommonDialog);
    }
}
