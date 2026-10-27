package defpackage;

import J.N;
import android.content.DialogInterface;
import org.chromium.content.browser.input.DateTimeChooserAndroid;

/* JADX INFO: renamed from: nC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DialogInterfaceOnDismissListenerC1205nC implements DialogInterface.OnDismissListener {
    public final /* synthetic */ C1589uC s;

    public DialogInterfaceOnDismissListenerC1205nC(C1589uC c1589uC) {
        this.s = c1589uC;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        C1589uC c1589uC = this.s;
        if (c1589uC.b) {
            return;
        }
        c1589uC.b = true;
        DateTimeChooserAndroid dateTimeChooserAndroid = ((QA) c1589uC.d).a;
        N.MCtaZNwj(dateTimeChooserAndroid.a, dateTimeChooserAndroid);
    }
}
