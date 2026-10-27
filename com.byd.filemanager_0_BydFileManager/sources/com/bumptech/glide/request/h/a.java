package com.bumptech.glide.request.h;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.o.k;

/* JADX INFO: compiled from: CustomTarget.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements e<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3497b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3498c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.request.c f3499d;

    public a() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Override // com.bumptech.glide.manager.i
    public void a() {
    }

    @Override // com.bumptech.glide.request.h.e
    public final void b(d dVar) {
    }

    @Override // com.bumptech.glide.request.h.e
    public void c(Drawable drawable) {
    }

    @Override // com.bumptech.glide.request.h.e
    public void e(Drawable drawable) {
    }

    @Override // com.bumptech.glide.manager.i
    public void f() {
    }

    @Override // com.bumptech.glide.request.h.e
    public final com.bumptech.glide.request.c g() {
        return this.f3499d;
    }

    @Override // com.bumptech.glide.request.h.e
    public final void i(d dVar) {
        dVar.g(this.f3497b, this.f3498c);
    }

    @Override // com.bumptech.glide.request.h.e
    public final void j(com.bumptech.glide.request.c cVar) {
        this.f3499d = cVar;
    }

    @Override // com.bumptech.glide.manager.i
    public void k() {
    }

    public a(int i, int i2) {
        if (k.r(i, i2)) {
            this.f3497b = i;
            this.f3498c = i2;
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + i + " and height: " + i2);
    }
}
