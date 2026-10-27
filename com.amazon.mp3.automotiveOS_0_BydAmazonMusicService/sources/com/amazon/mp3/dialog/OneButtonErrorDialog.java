package com.amazon.mp3.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class OneButtonErrorDialog extends AbstractButtonErrorDialog {
    private final Dialog mDialog;
    private final DialogInterface.OnCancelListener mOnCancelListener;
    private final ErrorDialogButton mPositiveButton;

    public OneButtonErrorDialog(Context context, String title, String message, ErrorDialogButton positiveButton) {
        super(context, title, message);
        this.mOnCancelListener = new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.dialog.OneButtonErrorDialog.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialog) {
                OneButtonErrorDialog.this.mPositiveButton.getOnClickListener().onClick(OneButtonErrorDialog.this.mDialog, -2);
            }
        };
        this.mPositiveButton = positiveButton;
        this.mDialog = build();
    }

    @Override // com.amazon.mp3.dialog.AbstractButtonErrorDialog
    protected Dialog getDialog() {
        return this.mDialog;
    }

    private Dialog build() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this.mContext);
        bauhausDialogBuilder.setOnCancelListener(this.mOnCancelListener);
        bauhausDialogBuilder.setTitle(this.mTitle);
        bauhausDialogBuilder.setMessage(this.mMessage);
        bauhausDialogBuilder.setPositiveButton(this.mPositiveButton.getTitle(), this.mPositiveButton.getOnClickListener());
        return bauhausDialogBuilder.create();
    }
}
