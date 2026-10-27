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
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: GradientFillContent.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements e, a.InterfaceC0077a, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3074a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f3075b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.layer.a f3076c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final b.d.d<LinearGradient> f3077d = new b.d.d<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final b.d.d<RadialGradient> f3078e = new b.d.d<>();
    private final Path f;
    private final Paint g;
    private final RectF h;
    private final List<m> i;
    private final GradientType j;
    private final com.airbnb.lottie.r.c.a<com.airbnb.lottie.model.content.c, com.airbnb.lottie.model.content.c> k;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> l;
    private final com.airbnb.lottie.r.c.a<PointF, PointF> m;
    private final com.airbnb.lottie.r.c.a<PointF, PointF> n;
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> o;
    private com.airbnb.lottie.r.c.p p;
    private final com.airbnb.lottie.f q;
    private final int r;

    public h(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.d dVar) {
        Path path = new Path();
        this.f = path;
        this.g = new com.airbnb.lottie.r.a(1);
        this.h = new RectF();
        this.i = new ArrayList();
        this.f3076c = aVar;
        this.f3074a = dVar.f();
        this.f3075b = dVar.i();
        this.q = fVar;
        this.j = dVar.e();
        path.setFillType(dVar.c());
        this.r = (int) (fVar.j().d() / 32.0f);
        com.airbnb.lottie.r.c.a<com.airbnb.lottie.model.content.c, com.airbnb.lottie.model.content.c> aVarA = dVar.d().a();
        this.k = aVarA;
        aVarA.a(this);
        aVar.j(aVarA);
        com.airbnb.lottie.r.c.a<Integer, Integer> aVarA2 = dVar.g().a();
        this.l = aVarA2;
        aVarA2.a(this);
        aVar.j(aVarA2);
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA3 = dVar.h().a();
        this.m = aVarA3;
        aVarA3.a(this);
        aVar.j(aVarA3);
        com.airbnb.lottie.r.c.a<PointF, PointF> aVarA4 = dVar.b().a();
        this.n = aVarA4;
        aVarA4.a(this);
        aVar.j(aVarA4);
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

    private int j() {
        int iRound = Math.round(this.m.f() * this.r);
        int iRound2 = Math.round(this.n.f() * this.r);
        int iRound3 = Math.round(this.k.f() * this.r);
        int i = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i = i * 31 * iRound2;
        }
        return iRound3 != 0 ? i * 31 * iRound3 : i;
    }

    private LinearGradient k() {
        long j = j();
        LinearGradient linearGradientF = this.f3077d.f(j);
        if (linearGradientF != null) {
            return linearGradientF;
        }
        PointF pointFH = this.m.h();
        PointF pointFH2 = this.n.h();
        com.airbnb.lottie.model.content.c cVarH = this.k.h();
        LinearGradient linearGradient = new LinearGradient(pointFH.x, pointFH.y, pointFH2.x, pointFH2.y, d(cVarH.a()), cVarH.b(), Shader.TileMode.CLAMP);
        this.f3077d.j(j, linearGradient);
        return linearGradient;
    }

    private RadialGradient l() {
        long j = j();
        RadialGradient radialGradientF = this.f3078e.f(j);
        if (radialGradientF != null) {
            return radialGradientF;
        }
        PointF pointFH = this.m.h();
        PointF pointFH2 = this.n.h();
        com.airbnb.lottie.model.content.c cVarH = this.k.h();
        int[] iArrD = d(cVarH.a());
        float[] fArrB = cVarH.b();
        float f = pointFH.x;
        float f2 = pointFH.y;
        float fHypot = (float) Math.hypot(pointFH2.x - f, pointFH2.y - f2);
        if (fHypot <= 0.0f) {
            fHypot = 0.001f;
        }
        RadialGradient radialGradient = new RadialGradient(f, f2, fHypot, iArrD, fArrB, Shader.TileMode.CLAMP);
        this.f3078e.j(j, radialGradient);
        return radialGradient;
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.f.reset();
        for (int i = 0; i < this.i.size(); i++) {
            this.f.addPath(this.i.get(i).h(), matrix);
        }
        this.f.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0077a
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

    @Override // com.airbnb.lottie.model.e
    public void e(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
        com.airbnb.lottie.u.g.l(dVar, i, list, dVar2, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        if (t == com.airbnb.lottie.k.f2883d) {
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
            this.f3076c.j(this.o);
            return;
        }
        if (t == com.airbnb.lottie.k.C) {
            if (cVar == null) {
                com.airbnb.lottie.r.c.p pVar2 = this.p;
                if (pVar2 != null) {
                    this.f3076c.C(pVar2);
                }
                this.p = null;
                return;
            }
            com.airbnb.lottie.r.c.p pVar3 = new com.airbnb.lottie.r.c.p(cVar);
            this.p = pVar3;
            pVar3.a(this);
            this.f3076c.j(this.p);
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    public void g(Canvas canvas, Matrix matrix, int i) {
        if (this.f3075b) {
            return;
        }
        com.airbnb.lottie.c.a("GradientFillContent#draw");
        this.f.reset();
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            this.f.addPath(this.i.get(i2).h(), matrix);
        }
        this.f.computeBounds(this.h, false);
        Shader shaderK = this.j == GradientType.LINEAR ? k() : l();
        shaderK.setLocalMatrix(matrix);
        this.g.setShader(shaderK);
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.o;
        if (aVar != null) {
            this.g.setColorFilter(aVar.h());
        }
        this.g.setAlpha(com.airbnb.lottie.u.g.c((int) ((((i / 255.0f) * this.l.h().intValue()) / 100.0f) * 255.0f), 0, 255));
        canvas.drawPath(this.f, this.g);
        com.airbnb.lottie.c.b("GradientFillContent#draw");
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.f3074a;
    }
}
