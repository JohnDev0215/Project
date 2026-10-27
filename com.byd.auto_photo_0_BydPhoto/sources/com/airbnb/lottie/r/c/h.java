package com.airbnb.lottie.r.c;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class h extends com.airbnb.lottie.x.a<PointF> {

    @Nullable
    private Path o;
    private final com.airbnb.lottie.x.a<PointF> p;

    public h(com.airbnb.lottie.d dVar, com.airbnb.lottie.x.a<PointF> aVar) {
        super(dVar, aVar.f976b, aVar.f977c, aVar.d, aVar.e, aVar.f);
        this.p = aVar;
        i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void i() {
        T t;
        T t2 = this.f977c;
        boolean z = (t2 == 0 || (t = this.f976b) == 0 || !((PointF) t).equals(((PointF) t2).x, ((PointF) t2).y)) ? false : true;
        T t3 = this.f977c;
        if (t3 == 0 || z) {
            return;
        }
        com.airbnb.lottie.x.a<PointF> aVar = this.p;
        this.o = com.airbnb.lottie.w.h.d((PointF) this.f976b, (PointF) t3, aVar.m, aVar.n);
    }

    @Nullable
    Path j() {
        return this.o;
    }
}
