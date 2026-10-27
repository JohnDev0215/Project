package defpackage;

import android.view.ViewTreeObserver;

/* JADX INFO: renamed from: xq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewTreeObserverOnPreDrawListenerC1786xq implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ C1896zq s;

    public ViewTreeObserverOnPreDrawListenerC1786xq(C1896zq c1896zq) {
        this.s = c1896zq;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        this.s.b();
        return true;
    }
}
