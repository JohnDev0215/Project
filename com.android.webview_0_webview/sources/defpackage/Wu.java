package defpackage;

import android.view.Choreographer;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class Wu implements InterfaceC0853gv {
    public final Choreographer a;

    public Wu(Choreographer choreographer) {
        this.a = choreographer;
    }

    @Override // defpackage.InterfaceC1020jv
    public void a(Runnable runnable) {
        this.a.postFrameCallback(new Uu(this, runnable));
    }

    @Override // defpackage.InterfaceC0853gv
    public boolean b() {
        try {
            return this.a == Choreographer.getInstance();
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    @Override // defpackage.InterfaceC1020jv
    public void c(Runnable runnable, long j) {
        this.a.postFrameCallbackDelayed(new Vu(this, runnable), j);
    }
}
