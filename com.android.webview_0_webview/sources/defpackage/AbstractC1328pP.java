package defpackage;

import android.graphics.Rect;

/* JADX INFO: renamed from: pP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1328pP {
    public final Rect s = new Rect();
    public InterfaceC1273oP t;

    public void a() {
        InterfaceC1273oP interfaceC1273oP = this.t;
        if (interfaceC1273oP != null) {
            ((ViewOnTouchListenerC1218nP) interfaceC1273oP).f.dismiss();
        }
    }
}
