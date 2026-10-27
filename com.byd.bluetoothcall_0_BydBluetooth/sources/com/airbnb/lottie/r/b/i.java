package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import com.airbnb.lottie.model.content.GradientType;

/* JADX INFO: compiled from: GradientStrokeContent.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends a {
    private final String o;
    private final boolean p;
    private final b.d.d<LinearGradient> q;
    private final b.d.d<RadialGradient> r;
    private final RectF s;
    private final GradientType t;
    private final int u;
    private final com.airbnb.lottie.r.c.a<com.airbnb.lottie.model.content.c, com.airbnb.lottie.model.content.c> v;
    private final com.airbnb.lottie.r.c.a<PointF, PointF> w;
    private final com.airbnb.lottie.r.c.a<PointF, PointF> x;
    private com.airbnb.lottie.r.c.p y;

    public i(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.e eVar) {
        super(fVar, aVar, eVar.b().toPaintCap(), eVar.g().toPaintJoin(), eVar.i(), eVar.k(), eVar.m(), eVar.h(), eVar.c());
        this.q = new b.d.d<>();
        this.r = new b.d.d<>();
        this.s = new RectF();
        this.o = eVar.j();
        this.t = eVar.f();
        this.p = eVar.n();
        this.u = (int) (fVar.j().d() / 32.0f);
        com.airbnb.lottie.r.c.a<com.airbnb.lottie.model.content.c, com.airbnb.lottie.model.content.c> aVarA = eVar.e().a();
        this.v = aVarA;
        aVarA.a(this);
        aVar.j(aVarA);
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA2 = eVar.l().a();
        this.w = aVarA2;
        aVarA2.a(this);
        aVar.j(aVarA2);
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA3 = eVar.d().a();
        this.x = aVarA3;
        aVarA3.a(this);
        aVar.j(aVarA3);
    }

    private int[] k(int[] iArr) {
        com.airbnb.lottie.r.c.p pVar = this.y;
        if (pVar != null) {
            Integer[] numArr = (Integer[]) pVar.h();
            int i = 0;
            if (iArr.length == numArr.length) {
                while (i < iArr.length) {
                    iArr[i] = numArr[i].intValue();
                    i++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i < numArr.length) {
                    iArr[i] = numArr[i].intValue();
                    i++;
                }
            }
        }
        return iArr;
    }

    private int l() {
        int iRound = Math.round(this.w.f() * this.u);
        int iRound2 = Math.round(this.x.f() * this.u);
        int iRound3 = Math.round(this.v.f() * this.u);
        int i = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i = i * 31 * iRound2;
        }
        return iRound3 != 0 ? i * 31 * iRound3 : i;
    }

    private LinearGradient m() {
        long jL = l();
        LinearGradient linearGradientF = this.q.f(jL);
        if (linearGradientF != null) {
            return linearGradientF;
        }
        PointF pointFH = this.w.h();
        PointF pointFH2 = this.x.h();
        com.airbnb.lottie.model.content.c cVarH = this.v.h();
        LinearGradient linearGradient = new LinearGradient(pointFH.x, pointFH.y, pointFH2.x, pointFH2.y, k(cVarH.a()), cVarH.b(), Shader.TileMode.CLAMP);
        this.q.j(jL, linearGradient);
        return linearGradient;
    }

    private RadialGradient n() {
        long jL = l();
        RadialGradient radialGradientF = this.r.f(jL);
        if (radialGradientF != null) {
            return radialGradientF;
        }
        PointF pointFH = this.w.h();
        PointF pointFH2 = this.x.h();
        com.airbnb.lottie.model.content.c cVarH = this.v.h();
        int[] iArrK = k(cVarH.a());
        float[] fArrB = cVarH.b();
        RadialGradient radialGradient = new RadialGradient(pointFH.x, pointFH.y, (float) Math.hypot(pointFH2.x - r7, pointFH2.y - r8), iArrK, fArrB, Shader.TileMode.CLAMP);
        this.r.j(jL, radialGradient);
        return radialGradient;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.r.b.a, com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        super.f(t, cVar);
        if (t == com.airbnb.lottie.k.C) {
            if (cVar == null) {
                com.airbnb.lottie.r.c.p pVar = this.y;
                if (pVar != null) {
                    this.f.C(pVar);
                }
                this.y = null;
                return;
            }
            com.airbnb.lottie.r.c.p pVar2 = new com.airbnb.lottie.r.c.p(cVar);
            this.y = pVar2;
            pVar2.a(this);
            this.f.j(this.y);
        }
    }

    @Override // com.airbnb.lottie.r.b.a, com.airbnb.lottie.r.b.e
    public void g(Canvas canvas, Matrix matrix, int i) {
        if (this.p) {
            return;
        }
        a(this.s, matrix, false);
        Shader shaderM = this.t == GradientType.LINEAR ? m() : n();
        shaderM.setLocalMatrix(matrix);
        this.i.setShader(shaderM);
        super.g(canvas, matrix, i);
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.o;
    }
}
