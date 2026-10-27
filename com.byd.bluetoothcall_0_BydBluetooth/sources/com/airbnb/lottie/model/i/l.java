package com.airbnb.lottie.model.i;

import android.graphics.PointF;
import com.airbnb.lottie.r.c.o;

/* JADX INFO: compiled from: AnimatableTransform.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements com.airbnb.lottie.model.content.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e f3002a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m<PointF, PointF> f3003b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f3004c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final b f3005d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d f3006e;
    private final b f;
    private final b g;
    private final b h;
    private final b i;

    public l() {
        this(null, null, null, null, null, null, null, null, null);
    }

    @Override // com.airbnb.lottie.model.content.b
    public com.airbnb.lottie.r.b.c a(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar) {
        return null;
    }

    public o b() {
        return new o(this);
    }

    public e c() {
        return this.f3002a;
    }

    public b d() {
        return this.i;
    }

    public d e() {
        return this.f3006e;
    }

    public m<PointF, PointF> f() {
        return this.f3003b;
    }

    public b g() {
        return this.f3005d;
    }

    public g h() {
        return this.f3004c;
    }

    public b i() {
        return this.f;
    }

    public b j() {
        return this.g;
    }

    public b k() {
        return this.h;
    }

    public l(e eVar, m<PointF, PointF> mVar, g gVar, b bVar, d dVar, b bVar2, b bVar3, b bVar4, b bVar5) {
        this.f3002a = eVar;
        this.f3003b = mVar;
        this.f3004c = gVar;
        this.f3005d = bVar;
        this.f3006e = dVar;
        this.h = bVar2;
        this.i = bVar3;
        this.f = bVar4;
        this.g = bVar5;
    }
}
