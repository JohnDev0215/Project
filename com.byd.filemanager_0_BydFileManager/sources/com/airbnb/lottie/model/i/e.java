package com.airbnb.lottie.model.i;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: compiled from: AnimatablePathValue.java */
/* JADX INFO: loaded from: classes.dex */
public class e implements m<PointF, PointF> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<com.airbnb.lottie.v.a<PointF>> f2611a;

    public e(List<com.airbnb.lottie.v.a<PointF>> list) {
        this.f2611a = list;
    }

    @Override // com.airbnb.lottie.model.i.m
    public com.airbnb.lottie.r.c.a<PointF, PointF> a() {
        return this.f2611a.get(0).h() ? new com.airbnb.lottie.r.c.j(this.f2611a) : new com.airbnb.lottie.r.c.i(this.f2611a);
    }

    @Override // com.airbnb.lottie.model.i.m
    public List<com.airbnb.lottie.v.a<PointF>> b() {
        return this.f2611a;
    }

    @Override // com.airbnb.lottie.model.i.m
    public boolean c() {
        return this.f2611a.size() == 1 && this.f2611a.get(0).h();
    }
}
