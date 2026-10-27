package defpackage;

import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: renamed from: sc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1497sc extends E3 {
    public Dialog y0;
    public DialogInterface.OnCancelListener z0;

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.z0;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }
}
