package com.byd.vrsettings.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class DetachableDialogCancelListener implements DialogInterface.OnCancelListener {
    private DialogInterface.OnCancelListener delegateOrNull;

    public static DetachableDialogCancelListener wrap(DialogInterface.OnCancelListener onCancelListener) {
        return new DetachableDialogCancelListener(onCancelListener);
    }

    public DetachableDialogCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.delegateOrNull = onCancelListener;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.delegateOrNull;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
            this.delegateOrNull = null;
        }
    }

    public void clearOnDetach(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        dialog.getWindow().getDecorView().getViewTreeObserver().addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() { // from class: com.byd.vrsettings.dialog.DetachableDialogCancelListener.1
            @Override // android.view.ViewTreeObserver.OnWindowAttachListener
            public void onWindowAttached() {
            }

            @Override // android.view.ViewTreeObserver.OnWindowAttachListener
            public void onWindowDetached() {
                DetachableDialogCancelListener.this.delegateOrNull = null;
            }
        });
    }
}
