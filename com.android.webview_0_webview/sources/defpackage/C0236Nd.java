package defpackage;

import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: Nd, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0236Nd extends AbstractC0669de {
    public WeakReference a;

    public C0236Nd(C0165Jd c0165Jd) {
        this.a = new WeakReference(c0165Jd);
    }

    @Override // defpackage.AbstractC0669de
    public final void a() {
        C0165Jd c0165Jd = (C0165Jd) this.a.get();
        if (c0165Jd == null) {
            return;
        }
        C0165Jd.m(c0165Jd);
    }
}
