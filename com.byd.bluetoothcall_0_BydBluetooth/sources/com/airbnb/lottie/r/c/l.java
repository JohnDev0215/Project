package com.airbnb.lottie.r.c;

import android.graphics.Path;
import java.util.List;

/* JADX INFO: compiled from: ShapeKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends a<com.airbnb.lottie.model.content.h, Path> {
    private final com.airbnb.lottie.model.content.h l;
    private final Path m;

    public l(List<com.airbnb.lottie.v.a<com.airbnb.lottie.model.content.h>> list) {
        super(list);
        this.l = new com.airbnb.lottie.model.content.h();
        this.m = new Path();
    }

    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public Path i(com.airbnb.lottie.v.a<com.airbnb.lottie.model.content.h> aVar, float f) {
        this.l.c(aVar.f3198b, aVar.f3199c, f);
        com.airbnb.lottie.u.g.h(this.l, this.m);
        return this.m;
    }
}
