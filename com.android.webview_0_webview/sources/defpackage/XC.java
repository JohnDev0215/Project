package defpackage;

import android.view.View;
import android.widget.Magnifier;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class XC implements WC {
    public Magnifier a;
    public InterfaceC0816gD b;

    public XC(InterfaceC0816gD interfaceC0816gD) {
        this.b = interfaceC0816gD;
    }

    public void a(float f, float f2) {
        View view = ((C0593cD) this.b).a.D;
        if (view == null) {
            return;
        }
        if (this.a == null) {
            this.a = new Magnifier(view);
        }
        this.a.show(f, f2);
    }
}
