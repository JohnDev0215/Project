package com.bumptech.glide.load;

import java.security.MessageDigest;

/* JADX INFO: compiled from: Options.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c.d.a<d<?>, Object> f2738b = new com.bumptech.glide.m.b();

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void f(d<T> dVar, Object obj, MessageDigest messageDigest) {
        dVar.g(obj, messageDigest);
    }

    @Override // com.bumptech.glide.load.c
    public void b(MessageDigest messageDigest) {
        for (int i = 0; i < this.f2738b.size(); i++) {
            f(this.f2738b.i(i), this.f2738b.m(i), messageDigest);
        }
    }

    public <T> T c(d<T> dVar) {
        return this.f2738b.containsKey(dVar) ? (T) this.f2738b.get(dVar) : dVar.c();
    }

    public void d(e eVar) {
        this.f2738b.j(eVar.f2738b);
    }

    public <T> e e(d<T> dVar, T t) {
        this.f2738b.put(dVar, t);
        return this;
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (obj instanceof e) {
            return this.f2738b.equals(((e) obj).f2738b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return this.f2738b.hashCode();
    }

    public String toString() {
        return "Options{values=" + this.f2738b + '}';
    }
}
