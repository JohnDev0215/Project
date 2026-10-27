package com.airbnb.lottie.r.c;

import android.graphics.Path;
import java.util.List;

/* JADX INFO: compiled from: ShapeKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class l extends a<com.airbnb.lottie.model.content.h, Path> {
    private final com.airbnb.lottie.model.content.h i;
    private final Path j;

    public l(List<com.airbnb.lottie.v.a<com.airbnb.lottie.model.content.h>> list) {
        super(list);
        this.i = new com.airbnb.lottie.model.content.h();
        this.j = new Path();
    }

    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public Path i(com.airbnb.lottie.v.a<com.airbnb.lottie.model.content.h> aVar, float f) {
        this.i.c(aVar.f2818b, aVar.f2819c, f);
        com.airbnb.lottie.u.g.h(this.i, this.j);
        return this.j;
    }
}
