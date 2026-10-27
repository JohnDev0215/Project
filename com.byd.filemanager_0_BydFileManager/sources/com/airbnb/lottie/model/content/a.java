package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.model.i.m;

/* JADX INFO: compiled from: CircleShape.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2559a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m<PointF, PointF> f2560b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.f f2561c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f2562d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f2563e;

    public a(String str, m<PointF, PointF> mVar, com.airbnb.lottie.model.i.f fVar, boolean z, boolean z2) {
        this.f2559a = str;
        this.f2560b = mVar;
        this.f2561c = fVar;
        this.f2562d = z;
        this.f2563e = z2;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new com.airbnb.lottie.r.b.f(fVar, aVar, this);
    }

    public String b() {
        return this.f2559a;
    }

    public m<PointF, PointF> c() {
        return this.f2560b;
    }

    public com.airbnb.lottie.model.i.f d() {
        return this.f2561c;
    }

    public boolean e() {
        return this.f2563e;
    }

    public boolean f() {
        return this.f2562d;
    }
}
