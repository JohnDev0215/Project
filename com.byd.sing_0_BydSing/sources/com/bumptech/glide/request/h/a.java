package com.bumptech.glide.request.h;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.m.k;

/* JADX INFO: compiled from: CustomTarget.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T> implements d<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3231b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3232c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.bumptech.glide.request.c f3233d;

    public a() {
        this(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Override // com.bumptech.glide.manager.i
    public void a() {
    }

    @Override // com.bumptech.glide.request.h.d
    public final void b(c cVar) {
    }

    @Override // com.bumptech.glide.request.h.d
    public void d(Drawable drawable) {
    }

    @Override // com.bumptech.glide.manager.i
    public void e() {
    }

    @Override // com.bumptech.glide.request.h.d
    public void f(Drawable drawable) {
    }

    @Override // com.bumptech.glide.request.h.d
    public final com.bumptech.glide.request.c g() {
        return this.f3233d;
    }

    @Override // com.bumptech.glide.request.h.d
    public final void i(c cVar) {
        cVar.g(this.f3231b, this.f3232c);
    }

    @Override // com.bumptech.glide.request.h.d
    public final void j(com.bumptech.glide.request.c cVar) {
        this.f3233d = cVar;
    }

    @Override // com.bumptech.glide.manager.i
    public void k() {
    }

    public a(int i, int i2) {
        if (k.r(i, i2)) {
            this.f3231b = i;
            this.f3232c = i2;
            return;
        }
        throw new IllegalArgumentException("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: " + i + " and height: " + i2);
    }
}
