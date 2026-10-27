package com.bumptech.glide.load.n;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
final class x implements com.bumptech.glide.load.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final com.bumptech.glide.t.g<Class<?>, byte[]> f2196b = new com.bumptech.glide.t.g<>(50);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.b f2197c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.load.g f2198d;
    private final com.bumptech.glide.load.g e;
    private final int f;
    private final int g;
    private final Class<?> h;
    private final com.bumptech.glide.load.i i;
    private final com.bumptech.glide.load.m<?> j;

    x(com.bumptech.glide.load.n.a0.b bVar, com.bumptech.glide.load.g gVar, com.bumptech.glide.load.g gVar2, int i, int i2, com.bumptech.glide.load.m<?> mVar, Class<?> cls, com.bumptech.glide.load.i iVar) {
        this.f2197c = bVar;
        this.f2198d = gVar;
        this.e = gVar2;
        this.f = i;
        this.g = i2;
        this.j = mVar;
        this.h = cls;
        this.i = iVar;
    }

    private byte[] c() {
        com.bumptech.glide.t.g<Class<?>, byte[]> gVar = f2196b;
        byte[] bArrG = gVar.g(this.h);
        if (bArrG != null) {
            return bArrG;
        }
        byte[] bytes = this.h.getName().getBytes(com.bumptech.glide.load.g.f2011a);
        gVar.k(this.h, bytes);
        return bytes;
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.f2197c.c(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.f).putInt(this.g).array();
        this.e.a(messageDigest);
        this.f2198d.a(messageDigest);
        messageDigest.update(bArr);
        com.bumptech.glide.load.m<?> mVar = this.j;
        if (mVar != null) {
            mVar.a(messageDigest);
        }
        this.i.a(messageDigest);
        messageDigest.update(c());
        this.f2197c.d(bArr);
    }

    @Override // com.bumptech.glide.load.g
    public boolean equals(Object obj) {
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return this.g == xVar.g && this.f == xVar.f && com.bumptech.glide.t.k.c(this.j, xVar.j) && this.h.equals(xVar.h) && this.f2198d.equals(xVar.f2198d) && this.e.equals(xVar.e) && this.i.equals(xVar.i);
    }

    @Override // com.bumptech.glide.load.g
    public int hashCode() {
        int iHashCode = (((((this.f2198d.hashCode() * 31) + this.e.hashCode()) * 31) + this.f) * 31) + this.g;
        com.bumptech.glide.load.m<?> mVar = this.j;
        if (mVar != null) {
            iHashCode = (iHashCode * 31) + mVar.hashCode();
        }
        return (((iHashCode * 31) + this.h.hashCode()) * 31) + this.i.hashCode();
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.f2198d + ", signature=" + this.e + ", width=" + this.f + ", height=" + this.g + ", decodedResourceClass=" + this.h + ", transformation='" + this.j + "', options=" + this.i + '}';
    }
}
