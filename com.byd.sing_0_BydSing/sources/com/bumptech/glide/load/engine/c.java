package com.bumptech.glide.load.engine;

import java.security.MessageDigest;

/* JADX INFO: compiled from: DataCacheKey.java */
/* JADX INFO: loaded from: classes.dex */
final class c implements com.bumptech.glide.load.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.c f2771b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.c f2772c;

    c(com.bumptech.glide.load.c cVar, com.bumptech.glide.load.c cVar2) {
        this.f2771b = cVar;
        this.f2772c = cVar2;
    }

    @Override // com.bumptech.glide.load.c
    public void b(MessageDigest messageDigest) {
        this.f2771b.b(messageDigest);
        this.f2772c.b(messageDigest);
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f2771b.equals(cVar.f2771b) && this.f2772c.equals(cVar.f2772c);
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return (this.f2771b.hashCode() * 31) + this.f2772c.hashCode();
    }

    public String toString() {
        return "DataCacheKey{sourceKey=" + this.f2771b + ", signature=" + this.f2772c + '}';
    }
}
