package com.bumptech.glide.load.engine;

import java.security.MessageDigest;

/* JADX INFO: compiled from: DataCacheKey.java */
/* JADX INFO: loaded from: classes.dex */
final class c implements com.bumptech.glide.load.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.c f3006b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.c f3007c;

    c(com.bumptech.glide.load.c cVar, com.bumptech.glide.load.c cVar2) {
        this.f3006b = cVar;
        this.f3007c = cVar2;
    }

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
        this.f3006b.a(messageDigest);
        this.f3007c.a(messageDigest);
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f3006b.equals(cVar.f3006b) && this.f3007c.equals(cVar.f3007c);
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return (this.f3006b.hashCode() * 31) + this.f3007c.hashCode();
    }

    public String toString() {
        return "DataCacheKey{sourceKey=" + this.f3006b + ", signature=" + this.f3007c + '}';
    }
}
