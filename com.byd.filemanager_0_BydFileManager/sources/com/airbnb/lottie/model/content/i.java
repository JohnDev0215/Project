package com.airbnb.lottie.model.content;

import android.graphics.Path;

/* JADX INFO: compiled from: ShapeFill.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f2589a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path.FillType f2590b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2591c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.a f2592d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.d f2593e;
    private final boolean f;

    public i(String str, boolean z, Path.FillType fillType, com.airbnb.lottie.model.i.a aVar, com.airbnb.lottie.model.i.d dVar, boolean z2) {
        this.f2591c = str;
        this.f2589a = z;
        this.f2590b = fillType;
        this.f2592d = aVar;
        this.f2593e = dVar;
        this.f = z2;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new com.airbnb.lottie.r.b.g(fVar, aVar, this);
    }

    public com.airbnb.lottie.model.i.a b() {
        return this.f2592d;
    }

    public Path.FillType c() {
        return this.f2590b;
    }

    public String d() {
        return this.f2591c;
    }

    public com.airbnb.lottie.model.i.d e() {
        return this.f2593e;
    }

    public boolean f() {
        return this.f;
    }

    public String toString() {
        return "ShapeFill{color=, fillEnabled=" + this.f2589a + '}';
    }
}
