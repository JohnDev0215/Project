package com.airbnb.lottie.r.c;

import android.graphics.Path;
import android.graphics.PointF;

/* JADX INFO: compiled from: PathKeyframe.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends com.airbnb.lottie.v.a<PointF> {
    private Path o;
    private final com.airbnb.lottie.v.a<PointF> p;

    public h(com.airbnb.lottie.d dVar, com.airbnb.lottie.v.a<PointF> aVar) {
        super(dVar, aVar.f3198b, aVar.f3199c, aVar.f3200d, aVar.f3201e, aVar.f);
        this.p = aVar;
        i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void i() {
        T t;
        T t2 = this.f3199c;
        boolean z = (t2 == 0 || (t = this.f3198b) == 0 || !((PointF) t).equals(((PointF) t2).x, ((PointF) t2).y)) ? false : true;
        T t3 = this.f3199c;
        if (t3 == 0 || z) {
            return;
        }
        com.airbnb.lottie.v.a<PointF> aVar = this.p;
        this.o = com.airbnb.lottie.u.h.d((PointF) this.f3198b, (PointF) t3, aVar.m, aVar.n);
    }

    Path j() {
        return this.o;
    }
}
