package com.bumptech.glide.load.engine;

import java.security.MessageDigest;
import java.util.Map;

/* JADX INFO: compiled from: EngineKey.java */
/* JADX INFO: loaded from: classes.dex */
class l implements com.bumptech.glide.load.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f2816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f2817c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f2818d;
    private final Class<?> e;
    private final Class<?> f;
    private final com.bumptech.glide.load.c g;
    private final Map<Class<?>, com.bumptech.glide.load.h<?>> h;
    private final com.bumptech.glide.load.e i;
    private int j;

    l(Object obj, com.bumptech.glide.load.c cVar, int i, int i2, Map<Class<?>, com.bumptech.glide.load.h<?>> map, Class<?> cls, Class<?> cls2, com.bumptech.glide.load.e eVar) {
        this.f2816b = com.bumptech.glide.m.j.d(obj);
        this.g = (com.bumptech.glide.load.c) com.bumptech.glide.m.j.e(cVar, "Signature must not be null");
        this.f2817c = i;
        this.f2818d = i2;
        this.h = (Map) com.bumptech.glide.m.j.d(map);
        this.e = (Class) com.bumptech.glide.m.j.e(cls, "Resource class must not be null");
        this.f = (Class) com.bumptech.glide.m.j.e(cls2, "Transcode class must not be null");
        this.i = (com.bumptech.glide.load.e) com.bumptech.glide.m.j.d(eVar);
    }

    @Override // com.bumptech.glide.load.c
    public void b(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return this.f2816b.equals(lVar.f2816b) && this.g.equals(lVar.g) && this.f2818d == lVar.f2818d && this.f2817c == lVar.f2817c && this.h.equals(lVar.h) && this.e.equals(lVar.e) && this.f.equals(lVar.f) && this.i.equals(lVar.i);
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        if (this.j == 0) {
            int iHashCode = this.f2816b.hashCode();
            this.j = iHashCode;
            int iHashCode2 = (iHashCode * 31) + this.g.hashCode();
            this.j = iHashCode2;
            int i = (iHashCode2 * 31) + this.f2817c;
            this.j = i;
            int i2 = (i * 31) + this.f2818d;
            this.j = i2;
            int iHashCode3 = (i2 * 31) + this.h.hashCode();
            this.j = iHashCode3;
            int iHashCode4 = (iHashCode3 * 31) + this.e.hashCode();
            this.j = iHashCode4;
            int iHashCode5 = (iHashCode4 * 31) + this.f.hashCode();
            this.j = iHashCode5;
            this.j = (iHashCode5 * 31) + this.i.hashCode();
        }
        return this.j;
    }

    public String toString() {
        return "EngineKey{model=" + this.f2816b + ", width=" + this.f2817c + ", height=" + this.f2818d + ", resourceClass=" + this.e + ", transcodeClass=" + this.f + ", signature=" + this.g + ", hashCode=" + this.j + ", transformations=" + this.h + ", options=" + this.i + '}';
    }
}
