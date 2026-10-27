package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class h implements e, a.InterfaceC0025a, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NonNull
    private final String f741a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f742b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.l.a f743c;
    private final LongSparseArray<LinearGradient> d = new LongSparseArray<>();
    private final LongSparseArray<RadialGradient> e = new LongSparseArray<>();
    private final Path f;
    private final Paint g;
    private final RectF h;
    private final List<m> i;
    private final com.airbnb.lottie.t.k.f j;
    private final com.airbnb.lottie.r.c.a<com.airbnb.lottie.t.k.c, com.airbnb.lottie.t.k.c> k;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> l;
    private final com.airbnb.lottie.r.c.a<PointF, PointF> m;
    private final com.airbnb.lottie.r.c.a<PointF, PointF> n;

    @Nullable
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> o;

    @Nullable
    private com.airbnb.lottie.r.c.p p;
    private final com.airbnb.lottie.f q;
    private final int r;

    public h(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.d dVar) {
        Path path = new Path();
        this.f = path;
        this.g = new com.airbnb.lottie.r.a(1);
        this.h = new RectF();
        this.i = new ArrayList();
        this.f743c = aVar;
        this.f741a = dVar.f();
        this.f742b = dVar.i();
        this.q = fVar;
        this.j = dVar.e();
        path.setFillType(dVar.c());
        this.r = (int) (fVar.j().d() / 32.0f);
        com.airbnb.lottie.r.c.a<com.airbnb.lottie.t.k.c, com.airbnb.lottie.t.k.c> aVarA = dVar.d().a();
        this.k = aVarA;
        aVarA.a(this);
        aVar.h(aVarA);
        com.airbnb.lottie.r.c.a<Integer, Integer> aVarA2 = dVar.g().a();
        this.l = aVarA2;
        aVarA2.a(this);
        aVar.h(aVarA2);
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA3 = dVar.h().a();
        this.m = aVarA3;
        aVarA3.a(this);
        aVar.h(aVarA3);
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA4 = dVar.b().a();
        this.n = aVarA4;
        aVarA4.a(this);
        aVar.h(aVarA4);
    }

    private int[] d(int[] iArr) {
        com.airbnb.lottie.r.c.p pVar = this.p;
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

    private int h() {
        int iRound = Math.round(this.m.f() * this.r);
        int iRound2 = Math.round(this.n.f() * this.r);
        int iRound3 = Math.round(this.k.f() * this.r);
        int i = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i = i * 31 * iRound2;
        }
        return iRound3 != 0 ? i * 31 * iRound3 : i;
    }

    private LinearGradient i() {
        long jH = h();
        LinearGradient linearGradient = this.d.get(jH);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF pointFH = this.m.h();
        PointF pointFH2 = this.n.h();
        com.airbnb.lottie.t.k.c cVarH = this.k.h();
        LinearGradient linearGradient2 = new LinearGradient(pointFH.x, pointFH.y, pointFH2.x, pointFH2.y, d(cVarH.a()), cVarH.b(), Shader.TileMode.CLAMP);
        this.d.put(jH, linearGradient2);
        return linearGradient2;
    }

    private RadialGradient j() {
        long jH = h();
        RadialGradient radialGradient = this.e.get(jH);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF pointFH = this.m.h();
        PointF pointFH2 = this.n.h();
        com.airbnb.lottie.t.k.c cVarH = this.k.h();
        int[] iArrD = d(cVarH.a());
        float[] fArrB = cVarH.b();
        float f = pointFH.x;
        float f2 = pointFH.y;
        float fHypot = (float) Math.hypot(pointFH2.x - f, pointFH2.y - f2);
        if (fHypot <= 0.0f) {
            fHypot = 0.001f;
        }
        RadialGradient radialGradient2 = new RadialGradient(f, f2, fHypot, iArrD, fArrB, Shader.TileMode.CLAMP);
        this.e.put(jH, radialGradient2);
        return radialGradient2;
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.f.reset();
        for (int i = 0; i < this.i.size(); i++) {
            this.f.addPath(this.i.get(i).getPath(), matrix);
        }
        this.f.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
    public void b() {
        this.q.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < list2.size(); i++) {
            c cVar = list2.get(i);
            if (cVar instanceof m) {
                this.i.add((m) cVar);
            }
        }
    }

    @Override // com.airbnb.lottie.t.f
    public void e(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
        com.airbnb.lottie.w.g.l(eVar, i, list, eVar2, this);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void f(Canvas canvas, Matrix matrix, int i) {
        if (this.f742b) {
            return;
        }
        com.airbnb.lottie.c.a("GradientFillContent#draw");
        this.f.reset();
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            this.f.addPath(this.i.get(i2).getPath(), matrix);
        }
        this.f.computeBounds(this.h, false);
        Shader shaderI = this.j == com.airbnb.lottie.t.k.f.LINEAR ? i() : j();
        shaderI.setLocalMatrix(matrix);
        this.g.setShader(shaderI);
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.o;
        if (aVar != null) {
            this.g.setColorFilter(aVar.h());
        }
        this.g.setAlpha(com.airbnb.lottie.w.g.c((int) ((((i / 255.0f) * this.l.h().intValue()) / 100.0f) * 255.0f), 0, 255));
        canvas.drawPath(this.f, this.g);
        com.airbnb.lottie.c.b("GradientFillContent#draw");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        com.airbnb.lottie.t.l.a aVar;
        com.airbnb.lottie.r.c.a<?, ?> aVar2;
        if (t == com.airbnb.lottie.k.d) {
            this.l.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.B) {
            if (cVar == null) {
                this.o = null;
                return;
            }
            com.airbnb.lottie.r.c.p pVar = new com.airbnb.lottie.r.c.p(cVar);
            this.o = pVar;
            pVar.a(this);
            aVar = this.f743c;
            aVar2 = this.o;
        } else {
            if (t != com.airbnb.lottie.k.C) {
                return;
            }
            if (cVar == null) {
                com.airbnb.lottie.r.c.p pVar2 = this.p;
                if (pVar2 != null) {
                    this.f743c.A(pVar2);
                }
                this.p = null;
                return;
            }
            com.airbnb.lottie.r.c.p pVar3 = new com.airbnb.lottie.r.c.p(cVar);
            this.p = pVar3;
            pVar3.a(this);
            aVar = this.f743c;
            aVar2 = this.p;
        }
        aVar.h(aVar2);
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.f741a;
    }
}
