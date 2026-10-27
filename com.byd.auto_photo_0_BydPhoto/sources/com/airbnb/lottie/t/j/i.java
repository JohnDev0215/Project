package com.airbnb.lottie.t.j;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class i implements m<PointF, PointF> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f804a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f805b;

    public i(b bVar, b bVar2) {
        this.f804a = bVar;
        this.f805b = bVar2;
    }

    @Override // com.airbnb.lottie.t.j.m
    public com.airbnb.lottie.r.c.a<PointF, PointF> a() {
        return new com.airbnb.lottie.r.c.m(this.f804a.a(), this.f805b.a());
    }

    @Override // com.airbnb.lottie.t.j.m
    public List<com.airbnb.lottie.x.a<PointF>> b() {
        throw new UnsupportedOperationException("Cannot call getKeyframes on AnimatableSplitDimensionPathValue.");
    }

    @Override // com.airbnb.lottie.t.j.m
    public boolean c() {
        return this.f804a.c() && this.f805b.c();
    }
}
