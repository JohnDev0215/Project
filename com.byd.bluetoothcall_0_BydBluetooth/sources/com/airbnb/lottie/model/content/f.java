package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.model.i.m;
import com.airbnb.lottie.r.b.o;

/* JADX INFO: compiled from: RectangleShape.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f2960a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m<PointF, PointF> f2961b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.f f2962c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.i.b f2963d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f2964e;

    public f(String str, m<PointF, PointF> mVar, com.airbnb.lottie.model.i.f fVar, com.airbnb.lottie.model.i.b bVar, boolean z) {
        this.f2960a = str;
        this.f2961b = mVar;
        this.f2962c = fVar;
        this.f2963d = bVar;
        this.f2964e = z;
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return new o(fVar, aVar, this);
    }

    public com.airbnb.lottie.model.i.b b() {
        return this.f2963d;
    }

    public String c() {
        return this.f2960a;
    }

    public m<PointF, PointF> d() {
        return this.f2961b;
    }

    public com.airbnb.lottie.model.i.f e() {
        return this.f2962c;
    }

    public boolean f() {
        return this.f2964e;
    }

    public String toString() {
        return "RectangleShape{position=" + this.f2961b + ", size=" + this.f2962c + '}';
    }
}
