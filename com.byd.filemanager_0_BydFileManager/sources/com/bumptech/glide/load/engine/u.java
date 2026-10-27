package com.bumptech.glide.load.engine;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: compiled from: ResourceCacheKey.java */
/* JADX INFO: loaded from: classes.dex */
final class u implements com.bumptech.glide.load.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final com.bumptech.glide.o.g<Class<?>, byte[]> f3079b = new com.bumptech.glide.o.g<>(50);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.b f3080c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.bumptech.glide.load.c f3081d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.bumptech.glide.load.c f3082e;
    private final int f;
    private final int g;
    private final Class<?> h;
    private final com.bumptech.glide.load.e i;
    private final com.bumptech.glide.load.h<?> j;

    u(com.bumptech.glide.load.engine.x.b bVar, com.bumptech.glide.load.c cVar, com.bumptech.glide.load.c cVar2, int i, int i2, com.bumptech.glide.load.h<?> hVar, Class<?> cls, com.bumptech.glide.load.e eVar) {
        this.f3080c = bVar;
        this.f3081d = cVar;
        this.f3082e = cVar2;
        this.f = i;
        this.g = i2;
        this.j = hVar;
        this.h = cls;
        this.i = eVar;
    }

    private byte[] c() {
        com.bumptech.glide.o.g<Class<?>, byte[]> gVar = f3079b;
        byte[] bArrG = gVar.g(this.h);
        if (bArrG != null) {
            return bArrG;
        }
        byte[] bytes = this.h.getName().getBytes(com.bumptech.glide.load.c.f2926a);
        gVar.k(this.h, bytes);
        return bytes;
    }

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.f3080c.c(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.f).putInt(this.g).array();
        this.f3082e.a(messageDigest);
        this.f3081d.a(messageDigest);
        messageDigest.update(bArr);
        com.bumptech.glide.load.h<?> hVar = this.j;
        if (hVar != null) {
            hVar.a(messageDigest);
        }
        this.i.a(messageDigest);
        messageDigest.update(c());
        this.f3080c.d(bArr);
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return this.g == uVar.g && this.f == uVar.f && com.bumptech.glide.o.k.c(this.j, uVar.j) && this.h.equals(uVar.h) && this.f3081d.equals(uVar.f3081d) && this.f3082e.equals(uVar.f3082e) && this.i.equals(uVar.i);
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        int iHashCode = (((((this.f3081d.hashCode() * 31) + this.f3082e.hashCode()) * 31) + this.f) * 31) + this.g;
        com.bumptech.glide.load.h<?> hVar = this.j;
        if (hVar != null) {
            iHashCode = (iHashCode * 31) + hVar.hashCode();
        }
        return (((iHashCode * 31) + this.h.hashCode()) * 31) + this.i.hashCode();
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.f3081d + ", signature=" + this.f3082e + ", width=" + this.f + ", height=" + this.g + ", decodedResourceClass=" + this.h + ", transformation='" + this.j + "', options=" + this.i + '}';
    }
}
