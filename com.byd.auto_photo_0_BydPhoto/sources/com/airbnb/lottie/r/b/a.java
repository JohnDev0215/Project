package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements a.InterfaceC0025a, k, e {
    private final com.airbnb.lottie.f e;
    protected final com.airbnb.lottie.t.l.a f;
    private final float[] h;
    final Paint i;
    private final com.airbnb.lottie.r.c.a<?, Float> j;
    private final com.airbnb.lottie.r.c.a<?, Integer> k;
    private final List<com.airbnb.lottie.r.c.a<?, Float>> l;

    @Nullable
    private final com.airbnb.lottie.r.c.a<?, Float> m;

    @Nullable
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final PathMeasure f726a = new PathMeasure();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path f727b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Path f728c = new Path();
    private final RectF d = new RectF();
    private final List<b> g = new ArrayList();

    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<m> f729a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        private final s f730b;

        private b(@Nullable s sVar) {
            this.f729a = new ArrayList();
            this.f730b = sVar;
        }
    }

    a(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, Paint.Cap cap, Paint.Join join, float f, com.airbnb.lottie.t.j.d dVar, com.airbnb.lottie.t.j.b bVar, List<com.airbnb.lottie.t.j.b> list, com.airbnb.lottie.t.j.b bVar2) {
        com.airbnb.lottie.r.a aVar2 = new com.airbnb.lottie.r.a(1);
        this.i = aVar2;
        this.e = fVar;
        this.f = aVar;
        aVar2.setStyle(Paint.Style.STROKE);
        aVar2.setStrokeCap(cap);
        aVar2.setStrokeJoin(join);
        aVar2.setStrokeMiter(f);
        this.k = dVar.a();
        this.j = bVar.a();
        this.m = bVar2 == null ? null : bVar2.a();
        this.l = new ArrayList(list.size());
        this.h = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.l.add(list.get(i).a());
        }
        aVar.h(this.k);
        aVar.h(this.j);
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            aVar.h(this.l.get(i2));
        }
        com.airbnb.lottie.r.c.a<?, Float> aVar3 = this.m;
        if (aVar3 != null) {
            aVar.h(aVar3);
        }
        this.k.a(this);
        this.j.a(this);
        for (int i3 = 0; i3 < list.size(); i3++) {
            this.l.get(i3).a(this);
        }
        com.airbnb.lottie.r.c.a<?, Float> aVar4 = this.m;
        if (aVar4 != null) {
            aVar4.a(this);
        }
    }

    private void d(Matrix matrix) {
        com.airbnb.lottie.c.a("StrokeContent#applyDashPattern");
        if (this.l.isEmpty()) {
            com.airbnb.lottie.c.b("StrokeContent#applyDashPattern");
            return;
        }
        float fG = com.airbnb.lottie.w.h.g(matrix);
        for (int i = 0; i < this.l.size(); i++) {
            this.h[i] = this.l.get(i).h().floatValue();
            if (i % 2 == 0) {
                float[] fArr = this.h;
                if (fArr[i] < 1.0f) {
                    fArr[i] = 1.0f;
                }
            } else {
                float[] fArr2 = this.h;
                if (fArr2[i] < 0.1f) {
                    fArr2[i] = 0.1f;
                }
            }
            float[] fArr3 = this.h;
            fArr3[i] = fArr3[i] * fG;
        }
        com.airbnb.lottie.r.c.a<?, Float> aVar = this.m;
        this.i.setPathEffect(new DashPathEffect(this.h, aVar == null ? 0.0f : fG * aVar.h().floatValue()));
        com.airbnb.lottie.c.b("StrokeContent#applyDashPattern");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void h(android.graphics.Canvas r13, com.airbnb.lottie.r.b.a.b r14, android.graphics.Matrix r15) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.r.b.a.h(android.graphics.Canvas, com.airbnb.lottie.r.b.a$b, android.graphics.Matrix):void");
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        com.airbnb.lottie.c.a("StrokeContent#getBounds");
        this.f727b.reset();
        for (int i = 0; i < this.g.size(); i++) {
            b bVar = this.g.get(i);
            for (int i2 = 0; i2 < bVar.f729a.size(); i2++) {
                this.f727b.addPath(((m) bVar.f729a.get(i2)).getPath(), matrix);
            }
        }
        this.f727b.computeBounds(this.d, false);
        float fN = ((com.airbnb.lottie.r.c.c) this.j).n();
        RectF rectF2 = this.d;
        float f = fN / 2.0f;
        rectF2.set(rectF2.left - f, rectF2.top - f, rectF2.right + f, rectF2.bottom + f);
        rectF.set(this.d);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
        com.airbnb.lottie.c.b("StrokeContent#getBounds");
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
    public void b() {
        this.e.invalidateSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0055  */
    @Override // com.airbnb.lottie.r.b.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(java.util.List<com.airbnb.lottie.r.b.c> r8, java.util.List<com.airbnb.lottie.r.b.c> r9) {
        /*
            r7 = this;
            int r0 = r8.size()
            int r0 = r0 + (-1)
            r1 = 0
            r2 = r1
        L8:
            if (r0 < 0) goto L22
            java.lang.Object r3 = r8.get(r0)
            com.airbnb.lottie.r.b.c r3 = (com.airbnb.lottie.r.b.c) r3
            boolean r4 = r3 instanceof com.airbnb.lottie.r.b.s
            if (r4 == 0) goto L1f
            com.airbnb.lottie.r.b.s r3 = (com.airbnb.lottie.r.b.s) r3
            com.airbnb.lottie.t.k.q$a r4 = r3.i()
            com.airbnb.lottie.t.k.q$a r5 = com.airbnb.lottie.t.k.q.a.INDIVIDUALLY
            if (r4 != r5) goto L1f
            r2 = r3
        L1f:
            int r0 = r0 + (-1)
            goto L8
        L22:
            if (r2 == 0) goto L27
            r2.d(r7)
        L27:
            int r8 = r9.size()
            int r8 = r8 + (-1)
            r0 = r1
        L2e:
            if (r8 < 0) goto L6c
            java.lang.Object r3 = r9.get(r8)
            com.airbnb.lottie.r.b.c r3 = (com.airbnb.lottie.r.b.c) r3
            boolean r4 = r3 instanceof com.airbnb.lottie.r.b.s
            if (r4 == 0) goto L55
            r4 = r3
            com.airbnb.lottie.r.b.s r4 = (com.airbnb.lottie.r.b.s) r4
            com.airbnb.lottie.t.k.q$a r5 = r4.i()
            com.airbnb.lottie.t.k.q$a r6 = com.airbnb.lottie.t.k.q.a.INDIVIDUALLY
            if (r5 != r6) goto L55
            if (r0 == 0) goto L4c
            java.util.List<com.airbnb.lottie.r.b.a$b> r3 = r7.g
            r3.add(r0)
        L4c:
            com.airbnb.lottie.r.b.a$b r0 = new com.airbnb.lottie.r.b.a$b
            r0.<init>(r4)
            r4.d(r7)
            goto L69
        L55:
            boolean r4 = r3 instanceof com.airbnb.lottie.r.b.m
            if (r4 == 0) goto L69
            if (r0 != 0) goto L60
            com.airbnb.lottie.r.b.a$b r0 = new com.airbnb.lottie.r.b.a$b
            r0.<init>(r2)
        L60:
            java.util.List r4 = com.airbnb.lottie.r.b.a.b.a(r0)
            com.airbnb.lottie.r.b.m r3 = (com.airbnb.lottie.r.b.m) r3
            r4.add(r3)
        L69:
            int r8 = r8 + (-1)
            goto L2e
        L6c:
            if (r0 == 0) goto L73
            java.util.List<com.airbnb.lottie.r.b.a$b> r8 = r7.g
            r8.add(r0)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.r.b.a.c(java.util.List, java.util.List):void");
    }

    @Override // com.airbnb.lottie.t.f
    public void e(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
        com.airbnb.lottie.w.g.l(eVar, i, list, eVar2, this);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void f(Canvas canvas, Matrix matrix, int i) {
        com.airbnb.lottie.c.a("StrokeContent#draw");
        if (com.airbnb.lottie.w.h.h(matrix)) {
            com.airbnb.lottie.c.b("StrokeContent#draw");
            return;
        }
        this.i.setAlpha(com.airbnb.lottie.w.g.c((int) ((((i / 255.0f) * ((com.airbnb.lottie.r.c.e) this.k).n()) / 100.0f) * 255.0f), 0, 255));
        this.i.setStrokeWidth(((com.airbnb.lottie.r.c.c) this.j).n() * com.airbnb.lottie.w.h.g(matrix));
        if (this.i.getStrokeWidth() <= 0.0f) {
            com.airbnb.lottie.c.b("StrokeContent#draw");
            return;
        }
        d(matrix);
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.n;
        if (aVar != null) {
            this.i.setColorFilter(aVar.h());
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            b bVar = this.g.get(i2);
            if (bVar.f730b != null) {
                h(canvas, bVar, matrix);
            } else {
                com.airbnb.lottie.c.a("StrokeContent#buildPath");
                this.f727b.reset();
                for (int size = bVar.f729a.size() - 1; size >= 0; size--) {
                    this.f727b.addPath(((m) bVar.f729a.get(size)).getPath(), matrix);
                }
                com.airbnb.lottie.c.b("StrokeContent#buildPath");
                com.airbnb.lottie.c.a("StrokeContent#drawPath");
                canvas.drawPath(this.f727b, this.i);
                com.airbnb.lottie.c.b("StrokeContent#drawPath");
            }
        }
        com.airbnb.lottie.c.b("StrokeContent#draw");
    }

    @Override // com.airbnb.lottie.t.f
    @CallSuper
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        com.airbnb.lottie.r.c.a aVar;
        if (t == com.airbnb.lottie.k.d) {
            aVar = this.k;
        } else {
            if (t != com.airbnb.lottie.k.o) {
                if (t == com.airbnb.lottie.k.B) {
                    if (cVar == null) {
                        this.n = null;
                        return;
                    }
                    com.airbnb.lottie.r.c.p pVar = new com.airbnb.lottie.r.c.p(cVar);
                    this.n = pVar;
                    pVar.a(this);
                    this.f.h(this.n);
                    return;
                }
                return;
            }
            aVar = this.j;
        }
        aVar.m(cVar);
    }
}
