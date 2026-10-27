package com.airbnb.lottie.model.i;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: compiled from: AnimatableSplitDimensionPathValue.java */
/* JADX INFO: loaded from: classes.dex */
public class i implements m<PointF, PointF> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f2612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f2613b;

    public i(b bVar, b bVar2) {
        this.f2612a = bVar;
        this.f2613b = bVar2;
    }

    @Override // com.airbnb.lottie.model.i.m
    public com.airbnb.lottie.r.c.a<PointF, PointF> a() {
        return new com.airbnb.lottie.r.c.m(this.f2612a.a(), this.f2613b.a());
    }

    @Override // com.airbnb.lottie.model.i.m
    public List<com.airbnb.lottie.v.a<PointF>> b() {
        throw new UnsupportedOperationException("Cannot call getKeyframes on AnimatableSplitDimensionPathValue.");
    }

    @Override // com.airbnb.lottie.model.i.m
    public boolean c() {
        return this.f2612a.c() && this.f2613b.c();
    }
}
