package com.byd.vrsettings.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class DetachableDialogDismissListener implements DialogInterface.OnDismissListener {
    private DialogInterface.OnDismissListener delegateOrNull;

    public static DetachableDialogDismissListener wrap(DialogInterface.OnDismissListener onDismissListener) {
        return new DetachableDialogDismissListener(onDismissListener);
    }

    public DetachableDialogDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.delegateOrNull = onDismissListener;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        DialogInterface.OnDismissListener onDismissListener = this.delegateOrNull;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
            this.delegateOrNull = null;
        }
    }

    public void clearOnDetach(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        dialog.getWindow().getDecorView().getViewTreeObserver().addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() { // from class: com.byd.vrsettings.dialog.DetachableDialogDismissListener.1
            @Override // android.view.ViewTreeObserver.OnWindowAttachListener
            public void onWindowAttached() {
            }

            @Override // android.view.ViewTreeObserver.OnWindowAttachListener
            public void onWindowDetached() {
                DetachableDialogDismissListener.this.delegateOrNull = null;
            }
        });
    }
}
