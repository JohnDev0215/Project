package com.bumptech.glide.load;

import java.security.MessageDigest;

/* JADX INFO: compiled from: Options.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b.d.a<d<?>, Object> f2970b = new com.bumptech.glide.o.b();

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void f(d<T> dVar, Object obj, MessageDigest messageDigest) {
        dVar.g(obj, messageDigest);
    }

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
        for (int i = 0; i < this.f2970b.size(); i++) {
            f(this.f2970b.i(i), this.f2970b.m(i), messageDigest);
        }
    }

    public <T> T c(d<T> dVar) {
        return this.f2970b.containsKey(dVar) ? (T) this.f2970b.get(dVar) : dVar.c();
    }

    public void d(e eVar) {
        this.f2970b.j(eVar.f2970b);
    }

    public <T> e e(d<T> dVar, T t) {
        this.f2970b.put(dVar, t);
        return this;
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (obj instanceof e) {
            return this.f2970b.equals(((e) obj).f2970b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return this.f2970b.hashCode();
    }

    public String toString() {
        return "Options{values=" + this.f2970b + '}';
    }
}
