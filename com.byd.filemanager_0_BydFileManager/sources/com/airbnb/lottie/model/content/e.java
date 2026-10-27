package com.airbnb.lottie.model.content;

import com.airbnb.lottie.model.content.ShapeStroke;
import java.util.List;

/* JADX INFO: compiled from: GradientStroke.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final GradientType f2572b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.c f2573c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.d f2574d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.f f2575e;
    private final com.airbnb.lottie.model.i.f f;
    private final com.airbnb.lottie.model.i.b g;
    private final ShapeStroke.LineCapType h;
    private final ShapeStroke.LineJoinType i;
    private final float j;
    private final List<com.airbnb.lottie.model.i.b> k;
    private final com.airbnb.lottie.model.i.b l;
    private final boolean m;

    public e(String str, GradientType gradientType, com.airbnb.lottie.model.i.c cVar, com.airbnb.lottie.model.i.d dVar, com.airbnb.lottie.model.i.f fVar, com.airbnb.lottie.model.i.f fVar2, com.airbnb.lottie.model.i.b bVar, ShapeStroke.LineCapType lineCapType, ShapeStroke.LineJoinType lineJoinType, float f, List<com.airbnb.lottie.model.i.b> list, com.airbnb.lottie.model.i.b bVar2, boolean z) {
        this.f2571a = str;
        this.f2572b = gradientType;
        this.f2573c = cVar;
        this.f2574d = dVar;
        this.f2575e = fVar;
        this.f = fVar2;
        this.g = bVar;
        this.h = lineCapType;
        this.i = lineJoinType;
        this.j = f;
        this.k = list;
        this.l = bVar2;
        this.m = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new com.airbnb.lottie.r.b.i(fVar, aVar, this);
    }

    public ShapeStroke.LineCapType b() {
        return this.h;
    }

    public com.airbnb.lottie.model.i.b c() {
        return this.l;
    }

    public com.airbnb.lottie.model.i.f d() {
        return this.f;
    }

    public com.airbnb.lottie.model.i.c e() {
        return this.f2573c;
    }

    public GradientType f() {
        return this.f2572b;
    }

    public ShapeStroke.LineJoinType g() {
        return this.i;
    }

    public List<com.airbnb.lottie.model.i.b> h() {
        return this.k;
    }

    public float i() {
        return this.j;
    }

    public String j() {
        return this.f2571a;
    }

    public com.airbnb.lottie.model.i.d k() {
        return this.f2574d;
    }

    public com.airbnb.lottie.model.i.f l() {
        return this.f2575e;
    }

    public com.airbnb.lottie.model.i.b m() {
        return this.g;
    }

    public boolean n() {
        return this.m;
    }
}
