package com.airbnb.lottie.t.j;

import android.graphics.PointF;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class e implements m<PointF, PointF> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<com.airbnb.lottie.x.a<PointF>> f803a;

    public e(List<com.airbnb.lottie.x.a<PointF>> list) {
        this.f803a = list;
    }

    @Override // com.airbnb.lottie.t.j.m
    public com.airbnb.lottie.r.c.a<PointF, PointF> a() {
        return this.f803a.get(0).h() ? new com.airbnb.lottie.r.c.j(this.f803a) : new com.airbnb.lottie.r.c.i(this.f803a);
    }

    @Override // com.airbnb.lottie.t.j.m
    public List<com.airbnb.lottie.x.a<PointF>> b() {
        return this.f803a;
    }

    @Override // com.airbnb.lottie.t.j.m
    public boolean c() {
        return this.f803a.size() == 1 && this.f803a.get(0).h();
    }
}
