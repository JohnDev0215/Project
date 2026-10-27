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
public class g implements e, a.InterfaceC0077a, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f3069a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f3070b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.model.layer.a f3071c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f3072d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f3073e;
    private final List<m> f;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> g;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> h;
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> i;
    private final com.airbnb.lottie.f j;

    public g(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.i iVar) {
        Path path = new Path();
        this.f3069a = path;
        this.f3070b = new com.airbnb.lottie.r.a(1);
        this.f = new ArrayList();
        this.f3071c = aVar;
        this.f3072d = iVar.d();
        this.f3073e = iVar.f();
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
        aVar.j(aVarA);
        com.airbnb.lottie.r.c.a<Integer, Integer> aVarA2 = iVar.e().a();
        this.h = aVarA2;
        aVarA2.a(this);
        aVar.j(aVarA2);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.f3069a.reset();
        for (int i = 0; i < this.f.size(); i++) {
            this.f3069a.addPath(this.f.get(i).h(), matrix);
        }
        this.f3069a.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0077a
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
        if (t == com.airbnb.lottie.k.f2880a) {
            this.g.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.f2883d) {
            this.h.m(cVar);
            return;
        }
        if (t == com.airbnb.lottie.k.B) {
            if (cVar == null) {
                this.i = null;
                return;
            }
            com.airbnb.lottie.r.c.p pVar = new com.airbnb.lottie.r.c.p(cVar);
            this.i = pVar;
            pVar.a(this);
            this.f3071c.j(this.i);
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    public void g(Canvas canvas, Matrix matrix, int i) {
        if (this.f3073e) {
            return;
        }
        com.airbnb.lottie.c.a("FillContent#draw");
        this.f3070b.setColor(((com.airbnb.lottie.r.c.b) this.g).n());
        this.f3070b.setAlpha(com.airbnb.lottie.u.g.c((int) ((((i / 255.0f) * this.h.h().intValue()) / 100.0f) * 255.0f), 0, 255));
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.i;
        if (aVar != null) {
            this.f3070b.setColorFilter(aVar.h());
        }
        this.f3069a.reset();
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            this.f3069a.addPath(this.f.get(i2).h(), matrix);
        }
        canvas.drawPath(this.f3069a, this.f3070b);
        com.airbnb.lottie.c.b("FillContent#draw");
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.f3072d;
    }
}
