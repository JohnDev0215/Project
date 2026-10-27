package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FillContent.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements e, a.b, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f2683a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f2684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.layer.a f2685c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f2686d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f2687e;
    private final List<m> f;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> g;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> h;
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> i;
    private final com.airbnb.lottie.f j;

    public g(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.i iVar) {
        Path path = new Path();
        this.f2683a = path;
        this.f2684b = new com.airbnb.lottie.r.a(1);
        this.f = new ArrayList();
        this.f2685c = aVar;
        this.f2686d = iVar.d();
        this.f2687e = iVar.f();
        this.j = fVar;
        if (iVar.b() == null || iVar.e() == null) {
            this.g = null;
            this.h = null;
            return;
        }
        path.setFillType(iVar.c());
        com.airbnb.lottie.r.c.a<Integer, Integer> aVarA = iVar.b().a();
        this.g = aVarA;
        aVarA.a(this);
        aVar.k(aVarA);
        com.airbnb.lottie.r.c.a<Integer, Integer> aVarA2 = iVar.e().a();
        this.h = aVarA2;
        aVarA2.a(this);
        aVar.k(aVarA2);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.f2683a.reset();
        for (int i = 0; i < this.f.size(); i++) {
            this.f2683a.addPath(this.f.get(i).h(), matrix);
        }
        this.f2683a.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.r.c.a.b
    public void b() {
        this.j.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < list2.size(); i++) {
            c cVar = list2.get(i);
            if (cVar instanceof m) {
                this.f.add((m) cVar);
            }
        }
    }

    @Override // com.airbnb.lottie.model.e
    public void e(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
        com.airbnb.lottie.u.g.l(dVar, i, list, dVar2, this);
    }

    @Override // com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        if (t == com.airbnb.lottie.k.f2496a) {
            this.g.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.f2499d) {
            this.h.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.C) {
            com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.i;
            if (aVar != null) {
                this.f2685c.E(aVar);
            }
            if (cVar == null) {
                this.i = null;
                return;
            }
            com.airbnb.lottie.r.c.p pVar = new com.airbnb.lottie.r.c.p(cVar);
            this.i = pVar;
            pVar.a(this);
            this.f2685c.k(this.i);
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    public void g(Canvas canvas, Matrix matrix, int i) {
        if (this.f2687e) {
            return;
        }
        com.airbnb.lottie.c.a("FillContent#draw");
        this.f2684b.setColor(((com.airbnb.lottie.r.c.b) this.g).o());
        this.f2684b.setAlpha(com.airbnb.lottie.u.g.c((int) ((((i / 255.0f) * this.h.h().intValue()) / 100.0f) * 255.0f), 0, 255));
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.i;
        if (aVar != null) {
            this.f2684b.setColorFilter(aVar.h());
        }
        this.f2683a.reset();
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            this.f2683a.addPath(this.f.get(i2).h(), matrix);
        }
        canvas.drawPath(this.f2683a, this.f2684b);
        com.airbnb.lottie.c.b("FillContent#draw");
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.f2686d;
    }
}
