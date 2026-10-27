package com.airbnb.lottie.r.c;

import android.graphics.PointF;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public class m extends a<PointF, PointF> {
    private final PointF l;
    private final a<Float, Float> m;
    private final a<Float, Float> n;

    public m(a<Float, Float> aVar, a<Float, Float> aVar2) {
        super(Collections.emptyList());
        this.l = new PointF();
        this.m = aVar;
        this.n = aVar2;
        l(f());
    }

    @Override // com.airbnb.lottie.r.c.a
    public void l(float f) {
        this.m.l(f);
        this.n.l(f);
        this.l.set(this.m.h().floatValue(), this.n.h().floatValue());
        for (int i = 0; i < this.f764a.size(); i++) {
            this.f764a.get(i).b();
        }
    }

    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public PointF h() {
        return i(null, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public PointF i(com.airbnb.lottie.x.a<PointF> aVar, float f) {
        return this.l;
    }
}
