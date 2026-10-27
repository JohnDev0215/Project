package com.bumptech.glide.load.engine;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: compiled from: ResourceCacheKey.java */
/* JADX INFO: loaded from: classes.dex */
final class u implements com.bumptech.glide.load.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final com.bumptech.glide.m.g<Class<?>, byte[]> f2834b = new com.bumptech.glide.m.g<>(50);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.b f2835c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.load.c f2836d;
    private final com.bumptech.glide.load.c e;
    private final int f;
    private final int g;
    private final Class<?> h;
    private final com.bumptech.glide.load.e i;
    private final com.bumptech.glide.load.h<?> j;

    u(com.bumptech.glide.load.engine.x.b bVar, com.bumptech.glide.load.c cVar, com.bumptech.glide.load.c cVar2, int i, int i2, com.bumptech.glide.load.h<?> hVar, Class<?> cls, com.bumptech.glide.load.e eVar) {
        this.f2835c = bVar;
        this.f2836d = cVar;
        this.e = cVar2;
        this.f = i;
        this.g = i2;
        this.j = hVar;
        this.h = cls;
        this.i = eVar;
    }

    private byte[] c() {
        com.bumptech.glide.m.g<Class<?>, byte[]> gVar = f2834b;
        byte[] bArrG = gVar.g(this.h);
        if (bArrG != null) {
            return bArrG;
        }
        byte[] bytes = this.h.getName().getBytes(com.bumptech.glide.load.c.f2733a);
        gVar.k(this.h, bytes);
        return bytes;
    }

    @Override // com.bumptech.glide.load.c
    public void b(MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.f2835c.c(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.f).putInt(this.g).array();
        this.e.b(messageDigest);
        this.f2836d.b(messageDigest);
        messageDigest.update(bArr);
        com.bumptech.glide.load.h<?> hVar = this.j;
        if (hVar != null) {
            hVar.b(messageDigest);
        }
        this.i.b(messageDigest);
        messageDigest.update(c());
        this.f2835c.put(bArr);
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return this.g == uVar.g && this.f == uVar.f && com.bumptech.glide.m.k.c(this.j, uVar.j) && this.h.equals(uVar.h) && this.f2836d.equals(uVar.f2836d) && this.e.equals(uVar.e) && this.i.equals(uVar.i);
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        int iHashCode = (((((this.f2836d.hashCode() * 31) + this.e.hashCode()) * 31) + this.f) * 31) + this.g;
        com.bumptech.glide.load.h<?> hVar = this.j;
        if (hVar != null) {
            iHashCode = (iHashCode * 31) + hVar.hashCode();
        }
        return (((iHashCode * 31) + this.h.hashCode()) * 31) + this.i.hashCode();
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.f2836d + ", signature=" + this.e + ", width=" + this.f + ", height=" + this.g + ", decodedResourceClass=" + this.h + ", transformation='" + this.j + "', options=" + this.i + '}';
    }
}
