package com.bumptech.glide.load.n;

import java.security.MessageDigest;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class n implements com.bumptech.glide.load.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f2173b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f2174c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f2175d;
    private final Class<?> e;
    private final Class<?> f;
    private final com.bumptech.glide.load.g g;
    private final Map<Class<?>, com.bumptech.glide.load.m<?>> h;
    private final com.bumptech.glide.load.i i;
    private int j;

    n(Object obj, com.bumptech.glide.load.g gVar, int i, int i2, Map<Class<?>, com.bumptech.glide.load.m<?>> map, Class<?> cls, Class<?> cls2, com.bumptech.glide.load.i iVar) {
        this.f2173b = com.bumptech.glide.t.j.d(obj);
        this.g = (com.bumptech.glide.load.g) com.bumptech.glide.t.j.e(gVar, "Signature must not be null");
        this.f2174c = i;
        this.f2175d = i2;
        this.h = (Map) com.bumptech.glide.t.j.d(map);
        this.e = (Class) com.bumptech.glide.t.j.e(cls, "Resource class must not be null");
        this.f = (Class) com.bumptech.glide.t.j.e(cls2, "Transcode class must not be null");
        this.i = (com.bumptech.glide.load.i) com.bumptech.glide.t.j.d(iVar);
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }

    @Override // com.bumptech.glide.load.g
    public boolean equals(Object obj) {
        if (!(obj instanceof n)) {
            return false;
        }
        n nVar = (n) obj;
        return this.f2173b.equals(nVar.f2173b) && this.g.equals(nVar.g) && this.f2175d == nVar.f2175d && this.f2174c == nVar.f2174c && this.h.equals(nVar.h) && this.e.equals(nVar.e) && this.f.equals(nVar.f) && this.i.equals(nVar.i);
    }

    @Override // com.bumptech.glide.load.g
    public int hashCode() {
        if (this.j == 0) {
            int iHashCode = this.f2173b.hashCode();
            this.j = iHashCode;
            int iHashCode2 = (iHashCode * 31) + this.g.hashCode();
            this.j = iHashCode2;
            int i = (iHashCode2 * 31) + this.f2174c;
            this.j = i;
            int i2 = (i * 31) + this.f2175d;
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
        return "EngineKey{model=" + this.f2173b + ", width=" + this.f2174c + ", height=" + this.f2175d + ", resourceClass=" + this.e + ", transcodeClass=" + this.f + ", signature=" + this.g + ", hashCode=" + this.j + ", transformations=" + this.h + ", options=" + this.i + '}';
    }
}
