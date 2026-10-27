package com.airbnb.lottie.model.content;

import android.graphics.Path;

/* JADX INFO: compiled from: ShapeFill.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f2973a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path.FillType f2974b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f2975c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.a f2976d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.d f2977e;
    private final boolean f;

    public i(String str, boolean z, Path.FillType fillType, com.airbnb.lottie.model.i.a aVar, com.airbnb.lottie.model.i.d dVar, boolean z2) {
        this.f2975c = str;
        this.f2973a = z;
        this.f2974b = fillType;
        this.f2976d = aVar;
        this.f2977e = dVar;
        this.f = z2;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new com.airbnb.lottie.r.b.g(fVar, aVar, this);
    }

    public com.airbnb.lottie.model.i.a b() {
        return this.f2976d;
    }

    public Path.FillType c() {
        return this.f2974b;
    }

    public String d() {
        return this.f2975c;
    }

    public com.airbnb.lottie.model.i.d e() {
        return this.f2977e;
    }

    public boolean f() {
        return this.f;
    }

    public String toString() {
        return "ShapeFill{color=, fillEnabled=" + this.f2973a + '}';
    }
}
