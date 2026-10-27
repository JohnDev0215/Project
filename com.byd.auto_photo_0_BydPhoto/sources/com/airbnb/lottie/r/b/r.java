package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class r extends a {
    private final com.airbnb.lottie.t.l.a o;
    private final String p;
    private final boolean q;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> r;

    @Nullable
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> s;

    public r(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.p pVar) {
        super(fVar, aVar, pVar.b().a(), pVar.e().a(), pVar.g(), pVar.i(), pVar.j(), pVar.f(), pVar.d());
        this.o = aVar;
        this.p = pVar.h();
        this.q = pVar.k();
        com.airbnb.lottie.r.c.a<Integer, Integer> aVarA = pVar.c().a();
        this.r = aVarA;
        aVarA.a(this);
        aVar.h(aVarA);
    }

    @Override // com.airbnb.lottie.r.b.a, com.airbnb.lottie.r.b.e
    public void f(Canvas canvas, Matrix matrix, int i) {
        if (this.q) {
            return;
        }
        this.i.setColor(((com.airbnb.lottie.r.c.b) this.r).n());
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.s;
        if (aVar != null) {
            this.i.setColorFilter(aVar.h());
        }
        super.f(canvas, matrix, i);
    }

    @Override // com.airbnb.lottie.r.b.a, com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        super.g(t, cVar);
        if (t == com.airbnb.lottie.k.f710b) {
            this.r.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.B) {
            if (cVar == null) {
                this.s = null;
                return;
            }
            com.airbnb.lottie.r.c.p pVar = new com.airbnb.lottie.r.c.p(cVar);
            this.s = pVar;
            pVar.a(this);
            this.o.h(this.r);
        }
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.p;
    }
}
