package com.bumptech.glide.load.n;

import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
final class d implements com.bumptech.glide.load.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.g f2108b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.g f2109c;

    d(com.bumptech.glide.load.g gVar, com.bumptech.glide.load.g gVar2) {
        this.f2108b = gVar;
        this.f2109c = gVar2;
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
        this.f2108b.a(messageDigest);
        this.f2109c.a(messageDigest);
    }

    @Override // com.bumptech.glide.load.g
    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.f2108b.equals(dVar.f2108b) && this.f2109c.equals(dVar.f2109c);
    }

    @Override // com.bumptech.glide.load.g
    public int hashCode() {
        return (this.f2108b.hashCode() * 31) + this.f2109c.hashCode();
    }

    public String toString() {
        return "DataCacheKey{sourceKey=" + this.f2108b + ", signature=" + this.f2109c + '}';
    }
}
