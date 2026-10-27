package com.bumptech.glide.r.j;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.t.k;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements e<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2516b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f2517c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.r.c f2518d;

    public a() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public a(int i, int i2) {
        if (k.r(i, i2)) {
            this.f2516b = i;
            this.f2517c = i2;
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + i + " and height: " + i2);
    }

    @Override // com.bumptech.glide.o.i
    public void a() {
    }

    @Override // com.bumptech.glide.r.j.e
    public final void b(d dVar) {
    }

    @Override // com.bumptech.glide.r.j.e
    public void c(Drawable drawable) {
    }

    @Override // com.bumptech.glide.r.j.e
    public void e(Drawable drawable) {
    }

    @Override // com.bumptech.glide.o.i
    public void f() {
    }

    @Override // com.bumptech.glide.r.j.e
    public final com.bumptech.glide.r.c g() {
        return this.f2518d;
    }

    @Override // com.bumptech.glide.r.j.e
    public final void i(d dVar) {
        dVar.g(this.f2516b, this.f2517c);
    }

    @Override // com.bumptech.glide.r.j.e
    public final void j(com.bumptech.glide.r.c cVar) {
        this.f2518d = cVar;
    }

    @Override // com.bumptech.glide.o.i
    public void k() {
    }
}
