package com.amazon.mp3.dialog;

import android.app.Dialog;
import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractButtonErrorDialog {
    protected final Context mContext;
    protected final String mMessage;
    protected final String mTitle;

    protected abstract Dialog getDialog();

    protected AbstractButtonErrorDialog(Context context, String title, String message) {
        this.mContext = context;
        this.mTitle = title;
        this.mMessage = message;
    }

    public void show() {
        Dialog dialog = getDialog();
        if (isShowing(dialog)) {
            return;
        }
        dialog.show();
    }

    private static boolean isShowing(Dialog dialog) {
        return dialog != null && dialog.isShowing();
    }
}
