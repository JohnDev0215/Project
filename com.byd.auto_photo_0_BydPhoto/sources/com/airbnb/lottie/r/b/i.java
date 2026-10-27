package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;

/* JADX INFO: loaded from: classes.dex */
public class i extends a {
    private final String o;
    private final boolean p;
    private final LongSparseArray<LinearGradient> q;
    private final LongSparseArray<RadialGradient> r;
    private final RectF s;
    private final com.airbnb.lottie.t.k.f t;
    private final int u;
    private final com.airbnb.lottie.r.c.a<com.airbnb.lottie.t.k.c, com.airbnb.lottie.t.k.c> v;
    private final com.airbnb.lottie.r.c.a<PointF, PointF> w;
    private final com.airbnb.lottie.r.c.a<PointF, PointF> x;

    @Nullable
    private com.airbnb.lottie.r.c.p y;

    public i(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.e eVar) {
        super(fVar, aVar, eVar.b().a(), eVar.g().a(), eVar.i(), eVar.k(), eVar.m(), eVar.h(), eVar.c());
        this.q = new LongSparseArray<>();
        this.r = new LongSparseArray<>();
        this.s = new RectF();
        this.o = eVar.j();
        this.t = eVar.f();
        this.p = eVar.n();
        this.u = (int) (fVar.j().d() / 32.0f);
        com.airbnb.lottie.r.c.a<com.airbnb.lottie.t.k.c, com.airbnb.lottie.t.k.c> aVarA = eVar.e().a();
        this.v = aVarA;
        aVarA.a(this);
        aVar.h(aVarA);
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA2 = eVar.l().a();
        this.w = aVarA2;
        aVarA2.a(this);
        aVar.h(aVarA2);
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA3 = eVar.d().a();
        this.x = aVarA3;
        aVarA3.a(this);
        aVar.h(aVarA3);
    }

    private int[] i(int[] iArr) {
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

    private int j() {
        int iRound = Math.round(this.w.f() * this.u);
        int iRound2 = Math.round(this.x.f() * this.u);
        int iRound3 = Math.round(this.v.f() * this.u);
        int i = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i = i * 31 * iRound2;
        }
        return iRound3 != 0 ? i * 31 * iRound3 : i;
    }

    private LinearGradient k() {
        long j = j();
        LinearGradient linearGradient = this.q.get(j);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF pointFH = this.w.h();
        PointF pointFH2 = this.x.h();
        com.airbnb.lottie.t.k.c cVarH = this.v.h();
        LinearGradient linearGradient2 = new LinearGradient(pointFH.x, pointFH.y, pointFH2.x, pointFH2.y, i(cVarH.a()), cVarH.b(), Shader.TileMode.CLAMP);
        this.q.put(j, linearGradient2);
        return linearGradient2;
    }

    private RadialGradient l() {
        long j = j();
        RadialGradient radialGradient = this.r.get(j);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF pointFH = this.w.h();
        PointF pointFH2 = this.x.h();
        com.airbnb.lottie.t.k.c cVarH = this.v.h();
        int[] iArrI = i(cVarH.a());
        float[] fArrB = cVarH.b();
        RadialGradient radialGradient2 = new RadialGradient(pointFH.x, pointFH.y, (float) Math.hypot(pointFH2.x - r7, pointFH2.y - r8), iArrI, fArrB, Shader.TileMode.CLAMP);
        this.r.put(j, radialGradient2);
        return radialGradient2;
    }

    @Override // com.airbnb.lottie.r.b.a, com.airbnb.lottie.r.b.e
    public void f(Canvas canvas, Matrix matrix, int i) {
        if (this.p) {
            return;
        }
        a(this.s, matrix, false);
        Shader shaderK = this.t == com.airbnb.lottie.t.k.f.LINEAR ? k() : l();
        shaderK.setLocalMatrix(matrix);
        this.i.setShader(shaderK);
        super.f(canvas, matrix, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.r.b.a, com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        super.g(t, cVar);
        if (t == com.airbnb.lottie.k.C) {
            if (cVar == null) {
                com.airbnb.lottie.r.c.p pVar = this.y;
                if (pVar != null) {
                    this.f.A(pVar);
                }
                this.y = null;
                return;
            }
            com.airbnb.lottie.r.c.p pVar2 = new com.airbnb.lottie.r.c.p(cVar);
            this.y = pVar2;
            pVar2.a(this);
            this.f.h(this.y);
        }
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.o;
    }
}
