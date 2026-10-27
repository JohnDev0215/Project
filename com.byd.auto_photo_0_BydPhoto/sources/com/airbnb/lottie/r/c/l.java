package com.airbnb.lottie.r.c;

import android.graphics.Path;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class l extends a<com.airbnb.lottie.t.k.l, Path> {
    private final com.airbnb.lottie.t.k.l l;
    private final Path m;

    public l(List<com.airbnb.lottie.x.a<com.airbnb.lottie.t.k.l>> list) {
        super(list);
        this.l = new com.airbnb.lottie.t.k.l();
        this.m = new Path();
    }

    @Override // com.airbnb.lottie.r.c.a
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public Path i(com.airbnb.lottie.x.a<com.airbnb.lottie.t.k.l> aVar, float f) {
        this.l.c(aVar.f976b, aVar.f977c, f);
        com.airbnb.lottie.w.g.h(this.l, this.m);
        return this.m;
    }
}
