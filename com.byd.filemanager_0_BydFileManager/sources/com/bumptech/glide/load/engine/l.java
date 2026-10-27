package com.bumptech.glide.load.engine;

import java.security.MessageDigest;
import java.util.Map;

/* JADX INFO: compiled from: EngineKey.java */
/* JADX INFO: loaded from: classes.dex */
class l implements com.bumptech.glide.load.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f3057b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3058c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f3059d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Class<?> f3060e;
    private final Class<?> f;
    private final com.bumptech.glide.load.c g;
    private final Map<Class<?>, com.bumptech.glide.load.h<?>> h;
    private final com.bumptech.glide.load.e i;
    private int j;

    l(Object obj, com.bumptech.glide.load.c cVar, int i, int i2, Map<Class<?>, com.bumptech.glide.load.h<?>> map, Class<?> cls, Class<?> cls2, com.bumptech.glide.load.e eVar) {
        this.f3057b = com.bumptech.glide.o.j.d(obj);
        this.g = (com.bumptech.glide.load.c) com.bumptech.glide.o.j.e(cVar, "Signature must not be null");
        this.f3058c = i;
        this.f3059d = i2;
        this.h = (Map) com.bumptech.glide.o.j.d(map);
        this.f3060e = (Class) com.bumptech.glide.o.j.e(cls, "Resource class must not be null");
        this.f = (Class) com.bumptech.glide.o.j.e(cls2, "Transcode class must not be null");
        this.i = (com.bumptech.glide.load.e) com.bumptech.glide.o.j.d(eVar);
    }

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (!(obj instanceof l)) {
            return false;
        }
        l lVar = (l) obj;
        return this.f3057b.equals(lVar.f3057b) && this.g.equals(lVar.g) && this.f3059d == lVar.f3059d && this.f3058c == lVar.f3058c && this.h.equals(lVar.h) && this.f3060e.equals(lVar.f3060e) && this.f.equals(lVar.f) && this.i.equals(lVar.i);
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        if (this.j == 0) {
            int iHashCode = this.f3057b.hashCode();
            this.j = iHashCode;
            int iHashCode2 = (iHashCode * 31) + this.g.hashCode();
            this.j = iHashCode2;
            int i = (iHashCode2 * 31) + this.f3058c;
            this.j = i;
            int i2 = (i * 31) + this.f3059d;
            this.j = i2;
            int iHashCode3 = (i2 * 31) + this.h.hashCode();
            this.j = iHashCode3;
            int iHashCode4 = (iHashCode3 * 31) + this.f3060e.hashCode();
            this.j = iHashCode4;
            int iHashCode5 = (iHashCode4 * 31) + this.f.hashCode();
            this.j = iHashCode5;
            this.j = (iHashCode5 * 31) + this.i.hashCode();
        }
        return this.j;
    }

    public String toString() {
        return "EngineKey{model=" + this.f3057b + ", width=" + this.f3058c + ", height=" + this.f3059d + ", resourceClass=" + this.f3060e + ", transcodeClass=" + this.f + ", signature=" + this.g + ", hashCode=" + this.j + ", transformations=" + this.h + ", options=" + this.i + '}';
    }
}
