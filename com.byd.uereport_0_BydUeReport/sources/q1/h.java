package q1;

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
import java.util.ArrayList;
import java.util.List;
import r1.a;

/* JADX INFO: compiled from: GradientFillContent.java */
/* JADX INFO: loaded from: classes.dex */
public class h implements e, a.b, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7285a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f7286b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w1.a f7287c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final v.d<LinearGradient> f7288d = new v.d<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final v.d<RadialGradient> f7289e = new v.d<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Path f7290f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f7291g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final RectF f7292h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List<m> f7293i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final GradientType f7294j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final r1.a<v1.c, v1.c> f7295k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final r1.a<Integer, Integer> f7296l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r1.a<PointF, PointF> f7297m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final r1.a<PointF, PointF> f7298n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public r1.a<ColorFilter, ColorFilter> f7299o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public r1.p f7300p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final o1.g f7301q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f7302r;

    public h(o1.g gVar, w1.a aVar, v1.d dVar) {
        Path path = new Path();
        this.f7290f = path;
        this.f7291g = new p1.a(1);
        this.f7292h = new RectF();
        this.f7293i = new ArrayList();
        this.f7287c = aVar;
        this.f7285a = dVar.f();
        this.f7286b = dVar.i();
        this.f7301q = gVar;
        this.f7294j = dVar.e();
        path.setFillType(dVar.c());
        this.f7302r = (int) (gVar.m().d() / 32.0f);
        r1.a<v1.c, v1.c> aVarA = dVar.d().a();
        this.f7295k = aVarA;
        aVarA.a(this);
        aVar.k(aVarA);
        r1.a<Integer, Integer> aVarA2 = dVar.g().a();
        this.f7296l = aVarA2;
        aVarA2.a(this);
        aVar.k(aVarA2);
        r1.a<PointF, PointF> aVarA3 = dVar.h().a();
        this.f7297m = aVarA3;
        aVarA3.a(this);
        aVar.k(aVarA3);
        r1.a<PointF, PointF> aVarA4 = dVar.b().a();
        this.f7298n = aVarA4;
        aVarA4.a(this);
        aVar.k(aVarA4);
    }

    @Override // q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        this.f7290f.reset();
        for (int i8 = 0; i8 < this.f7293i.size(); i8++) {
            this.f7290f.addPath(this.f7293i.get(i8).a(), matrix);
        }
        this.f7290f.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // r1.a.b
    public void c() {
        this.f7301q.invalidateSelf();
    }

    @Override // q1.c
    public void d(List<c> list, List<c> list2) {
        for (int i8 = 0; i8 < list2.size(); i8++) {
            c cVar = list2.get(i8);
            if (cVar instanceof m) {
                this.f7293i.add((m) cVar);
            }
        }
    }

    public final int[] e(int[] iArr) {
        r1.p pVar = this.f7300p;
        if (pVar != null) {
            Integer[] numArr = (Integer[]) pVar.h();
            int i8 = 0;
            if (iArr.length == numArr.length) {
                while (i8 < iArr.length) {
                    iArr[i8] = numArr[i8].intValue();
                    i8++;
                }
            } else {
                iArr = new int[numArr.length];
                while (i8 < numArr.length) {
                    iArr[i8] = numArr[i8].intValue();
                    i8++;
                }
            }
        }
        return iArr;
    }

    @Override // t1.e
    public void f(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        a2.g.l(dVar, i8, list, dVar2, this);
    }

    @Override // q1.e
    public void g(Canvas canvas, Matrix matrix, int i8) {
        if (this.f7286b) {
            return;
        }
        o1.d.a("GradientFillContent#draw");
        this.f7290f.reset();
        for (int i9 = 0; i9 < this.f7293i.size(); i9++) {
            this.f7290f.addPath(this.f7293i.get(i9).a(), matrix);
        }
        this.f7290f.computeBounds(this.f7292h, false);
        Shader shaderK = this.f7294j == GradientType.LINEAR ? k() : l();
        shaderK.setLocalMatrix(matrix);
        this.f7291g.setShader(shaderK);
        r1.a<ColorFilter, ColorFilter> aVar = this.f7299o;
        if (aVar != null) {
            this.f7291g.setColorFilter(aVar.h());
        }
        this.f7291g.setAlpha(a2.g.c((int) ((((i8 / 255.0f) * this.f7296l.h().intValue()) / 100.0f) * 255.0f), 0, 255));
        canvas.drawPath(this.f7290f, this.f7291g);
        o1.d.b("GradientFillContent#draw");
    }

    @Override // q1.c
    public String h() {
        return this.f7285a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        if (t7 == o1.l.f6831d) {
            this.f7296l.m(cVar);
            return;
        }
        if (t7 == o1.l.C) {
            r1.a<ColorFilter, ColorFilter> aVar = this.f7299o;
            if (aVar != null) {
                this.f7287c.E(aVar);
            }
            if (cVar == null) {
                this.f7299o = null;
                return;
            }
            r1.p pVar = new r1.p(cVar);
            this.f7299o = pVar;
            pVar.a(this);
            this.f7287c.k(this.f7299o);
            return;
        }
        if (t7 == o1.l.D) {
            r1.p pVar2 = this.f7300p;
            if (pVar2 != null) {
                this.f7287c.E(pVar2);
            }
            if (cVar == null) {
                this.f7300p = null;
                return;
            }
            r1.p pVar3 = new r1.p(cVar);
            this.f7300p = pVar3;
            pVar3.a(this);
            this.f7287c.k(this.f7300p);
        }
    }

    public final int j() {
        int iRound = Math.round(this.f7297m.f() * this.f7302r);
        int iRound2 = Math.round(this.f7298n.f() * this.f7302r);
        int iRound3 = Math.round(this.f7295k.f() * this.f7302r);
        int i8 = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i8 = i8 * 31 * iRound2;
        }
        return iRound3 != 0 ? i8 * 31 * iRound3 : i8;
    }

    public final LinearGradient k() {
        long j8 = j();
        LinearGradient linearGradientF = this.f7288d.f(j8);
        if (linearGradientF != null) {
            return linearGradientF;
        }
        PointF pointFH = this.f7297m.h();
        PointF pointFH2 = this.f7298n.h();
        v1.c cVarH = this.f7295k.h();
        LinearGradient linearGradient = new LinearGradient(pointFH.x, pointFH.y, pointFH2.x, pointFH2.y, e(cVarH.a()), cVarH.b(), Shader.TileMode.CLAMP);
        this.f7288d.k(j8, linearGradient);
        return linearGradient;
    }

    public final RadialGradient l() {
        long j8 = j();
        RadialGradient radialGradientF = this.f7289e.f(j8);
        if (radialGradientF != null) {
            return radialGradientF;
        }
        PointF pointFH = this.f7297m.h();
        PointF pointFH2 = this.f7298n.h();
        v1.c cVarH = this.f7295k.h();
        int[] iArrE = e(cVarH.a());
        float[] fArrB = cVarH.b();
        float f8 = pointFH.x;
        float f9 = pointFH.y;
        float fHypot = (float) Math.hypot(pointFH2.x - f8, pointFH2.y - f9);
        if (fHypot <= 0.0f) {
            fHypot = 0.001f;
        }
        RadialGradient radialGradient = new RadialGradient(f8, f9, fHypot, iArrE, fArrB, Shader.TileMode.CLAMP);
        this.f7289e.k(j8, radialGradient);
        return radialGradient;
    }
}
