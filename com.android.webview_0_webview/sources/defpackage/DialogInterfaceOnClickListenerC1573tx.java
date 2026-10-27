package defpackage;

import android.content.DialogInterface;

/* JADX INFO: renamed from: tx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DialogInterfaceOnClickListenerC1573tx implements DialogInterface.OnClickListener {
    public final /* synthetic */ AlertDialogC1738wx s;

    public DialogInterfaceOnClickListenerC1573tx(AlertDialogC1738wx alertDialogC1738wx) {
        this.s = alertDialogC1738wx;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        AlertDialogC1738wx alertDialogC1738wx = this.s;
        int i2 = alertDialogC1738wx.y;
        InterfaceC1903zx interfaceC1903zx = alertDialogC1738wx.x;
        if (interfaceC1903zx != null) {
            interfaceC1903zx.a(i2);
        }
    }
}
