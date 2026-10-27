package com.airbnb.lottie.r.c;

import android.graphics.PointF;
import java.util.Collections;

/* JADX INFO: compiled from: SplitDimensionPathKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends a<PointF, PointF> {
    private final PointF i;
    private final a<Float, Float> j;
    private final a<Float, Float> k;

    public m(a<Float, Float> aVar, a<Float, Float> aVar2) {
        super(Collections.emptyList());
        this.i = new PointF();
        this.j = aVar;
        this.k = aVar2;
        l(f());
    }

    @Override // com.airbnb.lottie.r.c.a
    public void l(float f) {
        this.j.l(f);
        this.k.l(f);
        this.i.set(this.j.h().floatValue(), this.k.h().floatValue());
        for (int i = 0; i < this.f2725a.size(); i++) {
            this.f2725a.get(i).b();
        }
    }

    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public PointF h() {
        return i(null, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
    public PointF i(com.airbnb.lottie.v.a<PointF> aVar, float f) {
        return this.i;
    }
}
