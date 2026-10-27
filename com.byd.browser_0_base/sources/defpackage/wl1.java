package defpackage;

import J.N;
import android.graphics.Bitmap;
import defpackage.ax0;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: NetworkImageFetcher.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wl1 extends ax0 {
    public wl1(cx0 cx0Var) {
        super(cx0Var);
    }

    @Override // defpackage.ax0
    public final void c(ax0.a aVar, Callback<od> callback) {
        N.Mno1Q7sp(this.a.a, 0, aVar.a, aVar.b, aVar.f, new t02(callback, 10));
    }

    @Override // defpackage.ax0
    public final void d(ax0.a aVar, Callback<Bitmap> callback) {
        N.M3LHmG_m(this.a.a, 0, aVar.a, aVar.b, aVar.c, aVar.d, aVar.f, new p62(aVar, 16, new wk(this, callback, aVar, System.currentTimeMillis(), 1)));
    }

    @Override // defpackage.ax0
    public final int e() {
        return 0;
    }

    @Override // defpackage.ax0
    public final void a() {
    }

    @Override // defpackage.ax0
    public final void b() {
    }
}
