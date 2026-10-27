package com.airbnb.lottie.t.k;

import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class k implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f848a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.b f849b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.b f850c;
    private final com.airbnb.lottie.t.j.l d;
    private final boolean e;

    public k(String str, com.airbnb.lottie.t.j.b bVar, com.airbnb.lottie.t.j.b bVar2, com.airbnb.lottie.t.j.l lVar, boolean z) {
        this.f848a = str;
        this.f849b = bVar;
        this.f850c = bVar2;
        this.d = lVar;
        this.e = z;
    }

    @Override // com.airbnb.lottie.t.k.b
    @Nullable
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new com.airbnb.lottie.r.b.p(fVar, aVar, this);
    }

    public com.airbnb.lottie.t.j.b b() {
        return this.f849b;
    }

    public String c() {
        return this.f848a;
    }

    public com.airbnb.lottie.t.j.b d() {
        return this.f850c;
    }

    public com.airbnb.lottie.t.j.l e() {
        return this.d;
    }

    public boolean f() {
        return this.e;
    }
}
