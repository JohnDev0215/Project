package com.bumptech.glide.load;

import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class i implements g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.d.a<h<?>, Object> f2016b = new com.bumptech.glide.t.b();

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void f(h<T> hVar, Object obj, MessageDigest messageDigest) {
        hVar.g(obj, messageDigest);
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
        for (int i = 0; i < this.f2016b.size(); i++) {
            f(this.f2016b.i(i), this.f2016b.m(i), messageDigest);
        }
    }

    public <T> T c(h<T> hVar) {
        return this.f2016b.containsKey(hVar) ? (T) this.f2016b.get(hVar) : hVar.c();
    }

    public void d(i iVar) {
        this.f2016b.j(iVar.f2016b);
    }

    public <T> i e(h<T> hVar, T t) {
        this.f2016b.put(hVar, t);
        return this;
    }

    @Override // com.bumptech.glide.load.g
    public boolean equals(Object obj) {
        if (obj instanceof i) {
            return this.f2016b.equals(((i) obj).f2016b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.g
    public int hashCode() {
        return this.f2016b.hashCode();
    }

    public String toString() {
        return "Options{values=" + this.f2016b + '}';
    }
}
