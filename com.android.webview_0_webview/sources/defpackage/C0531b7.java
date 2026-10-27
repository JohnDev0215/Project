package defpackage;

import java.util.ArrayList;

/* JADX INFO: renamed from: b7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0531b7 extends AbstractC0475a7 {
    public final /* synthetic */ C0971j1 a;
    public final /* synthetic */ ViewTreeObserverOnPreDrawListenerC0587c7 b;

    public C0531b7(ViewTreeObserverOnPreDrawListenerC0587c7 viewTreeObserverOnPreDrawListenerC0587c7, C0971j1 c0971j1) {
        this.b = viewTreeObserverOnPreDrawListenerC0587c7;
        this.a = c0971j1;
    }

    @Override // defpackage.Y6
    public void d(Z6 z6) {
        ((ArrayList) this.a.get(this.b.t)).remove(z6);
    }
}
