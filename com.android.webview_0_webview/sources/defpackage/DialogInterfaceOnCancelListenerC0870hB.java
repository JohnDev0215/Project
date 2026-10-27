package defpackage;

import android.content.DialogInterface;

/* JADX INFO: renamed from: hB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DialogInterfaceOnCancelListenerC0870hB implements DialogInterface.OnCancelListener {
    public final /* synthetic */ C0926iB s;

    public DialogInterfaceOnCancelListenerC0870hB(C0926iB c0926iB) {
        this.s = c0926iB;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.s.d(null);
    }
}
