package com.airbnb.lottie.model.content;

import android.graphics.Path;

/* JADX INFO: compiled from: GradientFill.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final GradientType f2950a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path.FillType f2951b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.c f2952c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.d f2953d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.f f2954e;
    private final com.airbnb.lottie.model.i.f f;
    private final String g;
    private final com.airbnb.lottie.model.i.b h;
    private final com.airbnb.lottie.model.i.b i;
    private final boolean j;

    public d(String str, GradientType gradientType, Path.FillType fillType, com.airbnb.lottie.model.i.c cVar, com.airbnb.lottie.model.i.d dVar, com.airbnb.lottie.model.i.f fVar, com.airbnb.lottie.model.i.f fVar2, com.airbnb.lottie.model.i.b bVar, com.airbnb.lottie.model.i.b bVar2, boolean z) {
        this.f2950a = gradientType;
        this.f2951b = fillType;
        this.f2952c = cVar;
        this.f2953d = dVar;
        this.f2954e = fVar;
        this.f = fVar2;
        this.g = str;
        this.h = bVar;
        this.i = bVar2;
        this.j = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new com.airbnb.lottie.r.b.h(fVar, aVar, this);
    }

    public com.airbnb.lottie.model.i.f b() {
        return this.f;
    }

    public Path.FillType c() {
        return this.f2951b;
    }

    public com.airbnb.lottie.model.i.c d() {
        return this.f2952c;
    }

    public GradientType e() {
        return this.f2950a;
    }

    public String f() {
        return this.g;
    }

    public com.airbnb.lottie.model.i.d g() {
        return this.f2953d;
    }

    public com.airbnb.lottie.model.i.f h() {
        return this.f2954e;
    }

    public boolean i() {
        return this.j;
    }
}
