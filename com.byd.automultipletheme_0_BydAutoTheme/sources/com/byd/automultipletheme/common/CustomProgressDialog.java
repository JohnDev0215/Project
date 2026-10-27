package com.byd.automultipletheme.common;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.byd.automultipletheme.R;

/* JADX INFO: loaded from: classes.dex */
public class CustomProgressDialog extends Dialog {
    private Context context;

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
    }

    public CustomProgressDialog(Context context) {
        this(context, R.style.Dialog);
    }

    public CustomProgressDialog(Context context, int i) {
        super(context, i);
        this.context = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_progress_dialog, (ViewGroup) null);
        if (getWindow() != null) {
            setContentView(viewInflate);
            getWindow().setLayout(-1, -1);
        } else {
            setContentView(viewInflate, new ViewGroup.LayoutParams(-1, -1));
        }
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().clearFlags(67108864);
    }

    public Context getLocalContext() {
        return this.context;
    }
}
