package com.amazon.mp3.dialog;

import android.content.Context;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes3.dex */
public class ErrorDialogButton {
    private static final DialogInterface.OnClickListener NOOP_ONCLICK_LISTENER = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.dialog.ErrorDialogButton.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(final DialogInterface dialog, final int which) {
        }
    };
    private final DialogInterface.OnClickListener mOnClickListener;
    private final String mText;

    public ErrorDialogButton(String text) {
        this(text, NOOP_ONCLICK_LISTENER);
    }

    public ErrorDialogButton(String text, DialogInterface.OnClickListener listener) {
        this.mText = text;
        this.mOnClickListener = listener;
    }

    public ErrorDialogButton(Context context, int textResId, DialogInterface.OnClickListener listener) {
        this(context.getString(textResId), listener);
    }

    public String getTitle() {
        return this.mText;
    }

    public DialogInterface.OnClickListener getOnClickListener() {
        return this.mOnClickListener;
    }
}
