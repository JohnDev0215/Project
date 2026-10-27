package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import com.airbnb.lottie.model.content.ShapeStroke;

/* JADX INFO: compiled from: StrokeContent.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends a {
    private final com.airbnb.lottie.model.layer.a o;
    private final String p;
    private final boolean q;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> r;
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> s;

    public r(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, ShapeStroke shapeStroke) {
        super(fVar, aVar, shapeStroke.b().toPaintCap(), shapeStroke.e().toPaintJoin(), shapeStroke.g(), shapeStroke.i(), shapeStroke.j(), shapeStroke.f(), shapeStroke.d());
        this.o = aVar;
        this.p = shapeStroke.h();
        this.q = shapeStroke.k();
        com.airbnb.lottie.r.c.a<Integer, Integer> aVarA = shapeStroke.c().a();
        this.r = aVarA;
        aVarA.a(this);
        aVar.j(aVarA);
    }

    @Override // com.airbnb.lottie.r.b.a, com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        super.f(t, cVar);
        if (t == com.airbnb.lottie.k.f2881b) {
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
            this.o.j(this.r);
        }
    }

    @Override // com.airbnb.lottie.r.b.a, com.airbnb.lottie.r.b.e
    public void g(Canvas canvas, Matrix matrix, int i) {
        if (this.q) {
            return;
        }
        this.i.setColor(((com.airbnb.lottie.r.c.b) this.r).n());
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.s;
        if (aVar != null) {
            this.i.setColorFilter(aVar.h());
        }
        super.g(canvas, matrix, i);
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.p;
    }
}
