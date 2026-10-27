package com.airbnb.lottie.r.c;

import android.graphics.Path;
import android.graphics.PointF;

/* JADX INFO: compiled from: PathKeyframe.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends com.airbnb.lottie.v.a<PointF> {
    private Path o;
    private final com.airbnb.lottie.v.a<PointF> p;

    public h(com.airbnb.lottie.d dVar, com.airbnb.lottie.v.a<PointF> aVar) {
        super(dVar, aVar.f2818b, aVar.f2819c, aVar.f2820d, aVar.f2821e, aVar.f);
        this.p = aVar;
        i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void i() {
        T t;
        T t2 = this.f2819c;
        boolean z = (t2 == 0 || (t = this.f2818b) == 0 || !((PointF) t).equals(((PointF) t2).x, ((PointF) t2).y)) ? false : true;
        T t3 = this.f2819c;
        if (t3 == 0 || z) {
            return;
        }
        com.airbnb.lottie.v.a<PointF> aVar = this.p;
        this.o = com.airbnb.lottie.u.h.d((PointF) this.f2818b, (PointF) t3, aVar.m, aVar.n);
    }

    Path j() {
        return this.o;
    }
}
