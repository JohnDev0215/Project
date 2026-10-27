package com.airbnb.lottie.model.content;

import com.airbnb.lottie.r.b.q;

/* JADX INFO: compiled from: ShapePath.java */
/* JADX INFO: loaded from: classes.dex */
public class k implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2597a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2598b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.h f2599c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f2600d;

    public k(String str, int i, com.airbnb.lottie.model.i.h hVar, boolean z) {
        this.f2597a = str;
        this.f2598b = i;
        this.f2599c = hVar;
        this.f2600d = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new q(fVar, aVar, this);
    }

    public String b() {
        return this.f2597a;
    }

    public com.airbnb.lottie.model.i.h c() {
        return this.f2599c;
    }

    public boolean d() {
        return this.f2600d;
    }

    public String toString() {
        return "ShapePath{name=" + this.f2597a + ", index=" + this.f2598b + '}';
    }
}
