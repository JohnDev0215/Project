package defpackage;

import android.content.DialogInterface;

/* JADX INFO: renamed from: mC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DialogInterfaceOnClickListenerC1150mC implements DialogInterface.OnClickListener {
    public final /* synthetic */ C1589uC s;

    public DialogInterfaceOnClickListenerC1150mC(C1589uC c1589uC) {
        this.s = c1589uC;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        C1589uC c1589uC = this.s;
        c1589uC.b = true;
        ((QA) c1589uC.d).a(Double.NaN);
    }
}
