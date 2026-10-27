package q1;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;
import r1.a;

/* JADX INFO: compiled from: BaseStrokeContent.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements a.b, k, e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o1.g f7243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final w1.a f7244f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f7246h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Paint f7247i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final r1.a<?, Float> f7248j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final r1.a<?, Integer> f7249k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final List<r1.a<?, Float>> f7250l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r1.a<?, Float> f7251m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public r1.a<ColorFilter, ColorFilter> f7252n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PathMeasure f7239a = new PathMeasure();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f7240b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Path f7241c = new Path();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RectF f7242d = new RectF();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List<b> f7245g = new ArrayList();

    /* JADX INFO: compiled from: BaseStrokeContent.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<m> f7253a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final s f7254b;

        public b(s sVar) {
            this.f7253a = new ArrayList();
            this.f7254b = sVar;
        }
    }

    public a(o1.g gVar, w1.a aVar, Paint.Cap cap, Paint.Join join, float f8, u1.d dVar, u1.b bVar, List<u1.b> list, u1.b bVar2) {
        p1.a aVar2 = new p1.a(1);
        this.f7247i = aVar2;
        this.f7243e = gVar;
        this.f7244f = aVar;
        aVar2.setStyle(Paint.Style.STROKE);
        aVar2.setStrokeCap(cap);
        aVar2.setStrokeJoin(join);
        aVar2.setStrokeMiter(f8);
        this.f7249k = dVar.a();
        this.f7248j = bVar.a();
        if (bVar2 == null) {
            this.f7251m = null;
        } else {
            this.f7251m = bVar2.a();
        }
        this.f7250l = new ArrayList(list.size());
        this.f7246h = new float[list.size()];
        for (int i8 = 0; i8 < list.size(); i8++) {
            this.f7250l.add(list.get(i8).a());
        }
        aVar.k(this.f7249k);
        aVar.k(this.f7248j);
        for (int i9 = 0; i9 < this.f7250l.size(); i9++) {
            aVar.k(this.f7250l.get(i9));
        }
        r1.a<?, Float> aVar3 = this.f7251m;
        if (aVar3 != null) {
            aVar.k(aVar3);
        }
        this.f7249k.a(this);
        this.f7248j.a(this);
        for (int i10 = 0; i10 < list.size(); i10++) {
            this.f7250l.get(i10).a(this);
        }
        r1.a<?, Float> aVar4 = this.f7251m;
        if (aVar4 != null) {
            aVar4.a(this);
        }
    }

    @Override // q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        o1.d.a("StrokeContent#getBounds");
        this.f7240b.reset();
        for (int i8 = 0; i8 < this.f7245g.size(); i8++) {
            b bVar = this.f7245g.get(i8);
            for (int i9 = 0; i9 < bVar.f7253a.size(); i9++) {
                this.f7240b.addPath(((m) bVar.f7253a.get(i9)).a(), matrix);
            }
        }
        this.f7240b.computeBounds(this.f7242d, false);
        float fO = ((r1.c) this.f7248j).o();
        RectF rectF2 = this.f7242d;
        float f8 = fO / 2.0f;
        rectF2.set(rectF2.left - f8, rectF2.top - f8, rectF2.right + f8, rectF2.bottom + f8);
        rectF.set(this.f7242d);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
        o1.d.b("StrokeContent#getBounds");
    }

    @Override // r1.a.b
    public void c() {
        this.f7243e.invalidateSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0055  */
    @Override // q1.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void d(java.util.List<q1.c> r8, java.util.List<q1.c> r9) {
        /*
            r7 = this;
            int r0 = r8.size()
            int r0 = r0 + (-1)
            r1 = 0
            r2 = r1
        L8:
            if (r0 < 0) goto L22
            java.lang.Object r3 = r8.get(r0)
            q1.c r3 = (q1.c) r3
            boolean r4 = r3 instanceof q1.s
            if (r4 == 0) goto L1f
            q1.s r3 = (q1.s) r3
            com.airbnb.lottie.model.content.ShapeTrimPath$Type r4 = r3.k()
            com.airbnb.lottie.model.content.ShapeTrimPath$Type r5 = com.airbnb.lottie.model.content.ShapeTrimPath.Type.INDIVIDUALLY
            if (r4 != r5) goto L1f
            r2 = r3
        L1f:
            int r0 = r0 + (-1)
            goto L8
        L22:
            if (r2 == 0) goto L27
            r2.e(r7)
        L27:
            int r8 = r9.size()
            int r8 = r8 + (-1)
            r0 = r1
        L2e:
            if (r8 < 0) goto L6c
            java.lang.Object r3 = r9.get(r8)
            q1.c r3 = (q1.c) r3
            boolean r4 = r3 instanceof q1.s
            if (r4 == 0) goto L55
            r4 = r3
            q1.s r4 = (q1.s) r4
            com.airbnb.lottie.model.content.ShapeTrimPath$Type r5 = r4.k()
            com.airbnb.lottie.model.content.ShapeTrimPath$Type r6 = com.airbnb.lottie.model.content.ShapeTrimPath.Type.INDIVIDUALLY
            if (r5 != r6) goto L55
            if (r0 == 0) goto L4c
            java.util.List<q1.a$b> r3 = r7.f7245g
            r3.add(r0)
        L4c:
            q1.a$b r0 = new q1.a$b
            r0.<init>(r4)
            r4.e(r7)
            goto L69
        L55:
            boolean r4 = r3 instanceof q1.m
            if (r4 == 0) goto L69
            if (r0 != 0) goto L60
            q1.a$b r0 = new q1.a$b
            r0.<init>(r2)
        L60:
            java.util.List r4 = q1.a.b.a(r0)
            q1.m r3 = (q1.m) r3
            r4.add(r3)
        L69:
            int r8 = r8 + (-1)
            goto L2e
        L6c:
            if (r0 == 0) goto L73
            java.util.List<q1.a$b> r8 = r7.f7245g
            r8.add(r0)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.a.d(java.util.List, java.util.List):void");
    }

    public final void e(Matrix matrix) {
        o1.d.a("StrokeContent#applyDashPattern");
        if (this.f7250l.isEmpty()) {
            o1.d.b("StrokeContent#applyDashPattern");
            return;
        }
        float fG = a2.h.g(matrix);
        for (int i8 = 0; i8 < this.f7250l.size(); i8++) {
            this.f7246h[i8] = this.f7250l.get(i8).h().floatValue();
            if (i8 % 2 == 0) {
                float[] fArr = this.f7246h;
                if (fArr[i8] < 1.0f) {
                    fArr[i8] = 1.0f;
                }
            } else {
                float[] fArr2 = this.f7246h;
                if (fArr2[i8] < 0.1f) {
                    fArr2[i8] = 0.1f;
                }
            }
            float[] fArr3 = this.f7246h;
            fArr3[i8] = fArr3[i8] * fG;
        }
        r1.a<?, Float> aVar = this.f7251m;
        this.f7247i.setPathEffect(new DashPathEffect(this.f7246h, aVar == null ? 0.0f : fG * aVar.h().floatValue()));
        o1.d.b("StrokeContent#applyDashPattern");
    }

    @Override // t1.e
    public void f(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        a2.g.l(dVar, i8, list, dVar2, this);
    }

    @Override // q1.e
    public void g(Canvas canvas, Matrix matrix, int i8) {
        o1.d.a("StrokeContent#draw");
        if (a2.h.h(matrix)) {
            o1.d.b("StrokeContent#draw");
            return;
        }
        this.f7247i.setAlpha(a2.g.c((int) ((((i8 / 255.0f) * ((r1.e) this.f7249k).o()) / 100.0f) * 255.0f), 0, 255));
        this.f7247i.setStrokeWidth(((r1.c) this.f7248j).o() * a2.h.g(matrix));
        if (this.f7247i.getStrokeWidth() <= 0.0f) {
            o1.d.b("StrokeContent#draw");
            return;
        }
        e(matrix);
        r1.a<ColorFilter, ColorFilter> aVar = this.f7252n;
        if (aVar != null) {
            this.f7247i.setColorFilter(aVar.h());
        }
        for (int i9 = 0; i9 < this.f7245g.size(); i9++) {
            b bVar = this.f7245g.get(i9);
            if (bVar.f7254b != null) {
                j(canvas, bVar, matrix);
            } else {
                o1.d.a("StrokeContent#buildPath");
                this.f7240b.reset();
                for (int size = bVar.f7253a.size() - 1; size >= 0; size--) {
                    this.f7240b.addPath(((m) bVar.f7253a.get(size)).a(), matrix);
                }
                o1.d.b("StrokeContent#buildPath");
                o1.d.a("StrokeContent#drawPath");
                canvas.drawPath(this.f7240b, this.f7247i);
                o1.d.b("StrokeContent#drawPath");
            }
        }
        o1.d.b("StrokeContent#draw");
    }

    @Override // t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        if (t7 == o1.l.f6831d) {
            this.f7249k.m(cVar);
            return;
        }
        if (t7 == o1.l.f6842o) {
            this.f7248j.m(cVar);
            return;
        }
        if (t7 == o1.l.C) {
            r1.a<ColorFilter, ColorFilter> aVar = this.f7252n;
            if (aVar != null) {
                this.f7244f.E(aVar);
            }
            if (cVar == null) {
                this.f7252n = null;
                return;
            }
            r1.p pVar = new r1.p(cVar);
            this.f7252n = pVar;
            pVar.a(this);
            this.f7244f.k(this.f7252n);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j(android.graphics.Canvas r13, q1.a.b r14, android.graphics.Matrix r15) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q1.a.j(android.graphics.Canvas, q1.a$b, android.graphics.Matrix):void");
    }
}
