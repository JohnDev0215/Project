package h2;

import android.graphics.drawable.Drawable;
import k2.k;

/* JADX INFO: compiled from: CustomTarget.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c<T> implements h<T> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f6594e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f6595g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private g2.c f6596h;

    public c() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Override // d2.i
    public void a() {
    }

    @Override // h2.h
    public void d(Drawable drawable) {
    }

    @Override // h2.h
    public final void e(g gVar) {
        gVar.f(this.f6594e, this.f6595g);
    }

    @Override // h2.h
    public void f(Drawable drawable) {
    }

    @Override // d2.i
    public void g() {
    }

    @Override // h2.h
    public final g2.c h() {
        return this.f6596h;
    }

    @Override // h2.h
    public final void j(g gVar) {
    }

    @Override // h2.h
    public final void l(g2.c cVar) {
        this.f6596h = cVar;
    }

    @Override // d2.i
    public void onDestroy() {
    }

    public c(int i7, int i8) {
        if (k.s(i7, i8)) {
            this.f6594e = i7;
            this.f6595g = i8;
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + i7 + " and height: " + i8);
    }
}
