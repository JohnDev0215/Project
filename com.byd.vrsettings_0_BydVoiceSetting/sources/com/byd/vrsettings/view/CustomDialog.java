package com.byd.vrsettings.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import com.byd.vrsettings.R;

/* JADX INFO: loaded from: classes.dex */
public class CustomDialog extends Dialog {
    public CustomDialog(Context context, View view) {
        super(context, R.style.custom_dialog8155);
        setCanceledOnTouchOutside(false);
        setContentView(view);
    }
}
