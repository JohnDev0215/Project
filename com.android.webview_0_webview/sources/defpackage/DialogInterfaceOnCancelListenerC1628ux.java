package defpackage;

import android.content.DialogInterface;

/* JADX INFO: renamed from: ux, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DialogInterfaceOnCancelListenerC1628ux implements DialogInterface.OnCancelListener {
    public final /* synthetic */ AlertDialogC1738wx s;

    public DialogInterfaceOnCancelListenerC1628ux(AlertDialogC1738wx alertDialogC1738wx) {
        this.s = alertDialogC1738wx;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        AlertDialogC1738wx alertDialogC1738wx = this.s;
        int i = alertDialogC1738wx.y;
        InterfaceC1903zx interfaceC1903zx = alertDialogC1738wx.x;
        if (interfaceC1903zx != null) {
            interfaceC1903zx.a(i);
        }
    }
}
