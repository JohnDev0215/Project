package com.airbnb.lottie.t.k;

/* JADX INFO: loaded from: classes.dex */
public class o implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f860a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f861b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.j.h f862c;
    private final boolean d;

    public o(String str, int i, com.airbnb.lottie.t.j.h hVar, boolean z) {
        this.f860a = str;
        this.f861b = i;
        this.f862c = hVar;
        this.d = z;
    }

    @Override // com.airbnb.lottie.t.k.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar) {
        return new com.airbnb.lottie.r.b.q(fVar, aVar, this);
    }

    public String b() {
        return this.f860a;
    }

    public com.airbnb.lottie.t.j.h c() {
        return this.f862c;
    }

    public boolean d() {
        return this.d;
    }

    public String toString() {
        return "ShapePath{name=" + this.f860a + ", index=" + this.f861b + '}';
    }
}
