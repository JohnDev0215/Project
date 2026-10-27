package com.neusoft.optimus.utility.view;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: loaded from: classes2.dex */
public class MessageDialog extends AlertDialog {
    public static final int cResultCancel = -1;
    public static final int cResultOK = 0;
    private Handler _handler;
    private final boolean _isModal;
    private int _result;

    public MessageDialog(Activity activity, String str, String str2, boolean z) {
        super(activity);
        this._isModal = z;
        setOwnerActivity(activity);
        onCreate(activity, str, str2);
    }

    public int getDialogResult() {
        return this._result;
    }

    private void setDialogResult(int i) {
        this._result = i;
    }

    public void onCreate(Context context, String str, String str2) {
        new AlertDialog.Builder(context).setTitle(str).setMessage(str2).setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.neusoft.optimus.utility.view.MessageDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MessageDialog.this.endDialog(0);
            }
        }).setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.neusoft.optimus.utility.view.MessageDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MessageDialog.this.endDialog(-1);
            }
        }).setCancelable(!this._isModal).show();
    }

    public void endDialog(int i) {
        dismiss();
        setDialogResult(i);
        if (this._isModal) {
            this._handler.sendMessage(this._handler.obtainMessage());
        }
    }

    public void showDialog() {
        if (this._isModal) {
            this._handler = new Handler() { // from class: com.neusoft.optimus.utility.view.MessageDialog.3
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    throw new RuntimeException();
                }
            };
            try {
                Looper.loop();
            } catch (RuntimeException unused) {
            }
        }
    }
}
