package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class g implements e, a.InterfaceC0025a, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f738a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f739b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.t.l.a f740c;
    private final String d;
    private final boolean e;
    private final List<m> f;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> g;
    private final com.airbnb.lottie.r.c.a<Integer, Integer> h;

    @Nullable
    private com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> i;
    private final com.airbnb.lottie.f j;

    public g(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.m mVar) {
        Path path = new Path();
        this.f738a = path;
        this.f739b = new com.airbnb.lottie.r.a(1);
        this.f = new ArrayList();
        this.f740c = aVar;
        this.d = mVar.d();
        this.e = mVar.f();
        this.j = fVar;
        if (mVar.b() == null || mVar.e() == null) {
            this.g = null;
            this.h = null;
            return;
        }
        path.setFillType(mVar.c());
        com.airbnb.lottie.r.c.a<Integer, Integer> aVarA = mVar.b().a();
        this.g = aVarA;
        aVarA.a(this);
        aVar.h(aVarA);
        com.airbnb.lottie.r.c.a<Integer, Integer> aVarA2 = mVar.e().a();
        this.h = aVarA2;
        aVarA2.a(this);
        aVar.h(aVarA2);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.f738a.reset();
        for (int i = 0; i < this.f.size(); i++) {
            this.f738a.addPath(this.f.get(i).getPath(), matrix);
        }
        this.f738a.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
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

    @Override // com.airbnb.lottie.t.f
    public void e(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
        com.airbnb.lottie.w.g.l(eVar, i, list, eVar2, this);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void f(Canvas canvas, Matrix matrix, int i) {
        if (this.e) {
            return;
        }
        com.airbnb.lottie.c.a("FillContent#draw");
        this.f739b.setColor(((com.airbnb.lottie.r.c.b) this.g).n());
        this.f739b.setAlpha(com.airbnb.lottie.w.g.c((int) ((((i / 255.0f) * this.h.h().intValue()) / 100.0f) * 255.0f), 0, 255));
        com.airbnb.lottie.r.c.a<ColorFilter, ColorFilter> aVar = this.i;
        if (aVar != null) {
            this.f739b.setColorFilter(aVar.h());
        }
        this.f738a.reset();
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            this.f738a.addPath(this.f.get(i2).getPath(), matrix);
        }
        canvas.drawPath(this.f738a, this.f739b);
        com.airbnb.lottie.c.b("FillContent#draw");
    }

    @Override // com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        com.airbnb.lottie.r.c.a<Integer, Integer> aVar;
        if (t == com.airbnb.lottie.k.f709a) {
            aVar = this.g;
        } else {
            if (t != com.airbnb.lottie.k.d) {
                if (t == com.airbnb.lottie.k.B) {
                    if (cVar == null) {
                        this.i = null;
                        return;
                    }
                    com.airbnb.lottie.r.c.p pVar = new com.airbnb.lottie.r.c.p(cVar);
                    this.i = pVar;
                    pVar.a(this);
                    this.f740c.h(this.i);
                    return;
                }
                return;
            }
            aVar = this.h;
        }
        aVar.m(cVar);
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.d;
    }
}
