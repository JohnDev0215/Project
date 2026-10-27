package r4;

import A4.q;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;

/* JADX INFO: renamed from: r4.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2292e implements x4.c {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f21824m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public w4.c f21825n;
    public final Handler o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f21826p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f21827q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Bitmap f21828r;

    public C2292e(Handler handler, int i7, long j3) {
        if (!q.i(Integer.MIN_VALUE, Integer.MIN_VALUE)) {
            throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: -2147483648 and height: -2147483648");
        }
        this.l = Integer.MIN_VALUE;
        this.f21824m = Integer.MIN_VALUE;
        this.o = handler;
        this.f21826p = i7;
        this.f21827q = j3;
    }

    @Override // x4.c
    public final w4.c D() {
        return this.f21825n;
    }

    @Override // x4.c
    public final void E(Drawable drawable) {
        this.f21828r = null;
    }

    @Override // x4.c
    public final void F(Object obj) {
        this.f21828r = (Bitmap) obj;
        Handler handler = this.o;
        handler.sendMessageAtTime(handler.obtainMessage(1, this), this.f21827q);
    }

    @Override // t4.b
    public final void J() {
    }

    @Override // x4.c
    public final void g(w4.g gVar) {
        gVar.m(this.l, this.f21824m);
    }

    @Override // x4.c
    public final void i(Drawable drawable) {
    }

    @Override // x4.c
    public final void n(w4.c cVar) {
        this.f21825n = cVar;
    }

    @Override // x4.c
    public final void t(Drawable drawable) {
    }

    @Override // x4.c
    public final void u(w4.g gVar) {
    }
}
