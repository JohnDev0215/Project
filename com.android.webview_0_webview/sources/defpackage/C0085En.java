package defpackage;

import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: En, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0085En extends ID implements JD {
    public final /* synthetic */ AwContents a;

    public C0085En(AwContents awContents, C1838yn c1838yn) {
        this.a = awContents;
    }

    @Override // defpackage.ID, defpackage.JD
    public void a() {
        C1347pq c1347pq = this.a.T;
        if (!c1347pq.b) {
            c1347pq.b = true;
            c1347pq.a.sendMessageDelayed(c1347pq.a.obtainMessage(1), 100L);
        }
        this.a.O.b();
    }

    @Override // defpackage.JD
    public void b(int i, int i2) {
        this.a.O.b();
    }

    @Override // defpackage.ID, defpackage.JD
    public void d() {
        C0265Oo c0265Oo = this.a.N;
        c0265Oo.f = false;
        if (c0265Oo.g) {
            c0265Oo.g = false;
            ((C0103Fn) c0265Oo.k).a.u.requestLayout();
        }
    }

    @Override // defpackage.ID, defpackage.JD
    public void g(int i, int i2) {
        this.a.O.c();
    }

    @Override // defpackage.ID, defpackage.JD
    public void h(float f, float f2) {
        this.a.O.c();
    }

    @Override // defpackage.ID, defpackage.JD
    public void j() {
        C0265Oo c0265Oo = this.a.N;
        c0265Oo.f = true;
        c0265Oo.g = false;
    }
}
