package q1;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;
import r1.a;

/* JADX INFO: compiled from: FillContent.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements e, a.b, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Path f7275a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f7276b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final w1.a f7277c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f7278d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f7279e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List<m> f7280f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final r1.a<Integer, Integer> f7281g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final r1.a<Integer, Integer> f7282h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public r1.a<ColorFilter, ColorFilter> f7283i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final o1.g f7284j;

    public g(o1.g gVar, w1.a aVar, v1.i iVar) {
        Path path = new Path();
        this.f7275a = path;
        this.f7276b = new p1.a(1);
        this.f7280f = new ArrayList();
        this.f7277c = aVar;
        this.f7278d = iVar.d();
        this.f7279e = iVar.f();
        this.f7284j = gVar;
        if (iVar.b() == null || iVar.e() == null) {
            this.f7281g = null;
            this.f7282h = null;
            return;
        }
        path.setFillType(iVar.c());
        r1.a<Integer, Integer> aVarA = iVar.b().a();
        this.f7281g = aVarA;
        aVarA.a(this);
        aVar.k(aVarA);
        r1.a<Integer, Integer> aVarA2 = iVar.e().a();
        this.f7282h = aVarA2;
        aVarA2.a(this);
        aVar.k(aVarA2);
    }

    @Override // q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        this.f7275a.reset();
        for (int i8 = 0; i8 < this.f7280f.size(); i8++) {
            this.f7275a.addPath(this.f7280f.get(i8).a(), matrix);
        }
        this.f7275a.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    @Override // r1.a.b
    public void c() {
        this.f7284j.invalidateSelf();
    }

    @Override // q1.c
    public void d(List<c> list, List<c> list2) {
        for (int i8 = 0; i8 < list2.size(); i8++) {
            c cVar = list2.get(i8);
            if (cVar instanceof m) {
                this.f7280f.add((m) cVar);
            }
        }
    }

    @Override // t1.e
    public void f(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        a2.g.l(dVar, i8, list, dVar2, this);
    }

    @Override // q1.e
    public void g(Canvas canvas, Matrix matrix, int i8) {
        if (this.f7279e) {
            return;
        }
        o1.d.a("FillContent#draw");
        this.f7276b.setColor(((r1.b) this.f7281g).o());
        this.f7276b.setAlpha(a2.g.c((int) ((((i8 / 255.0f) * this.f7282h.h().intValue()) / 100.0f) * 255.0f), 0, 255));
        r1.a<ColorFilter, ColorFilter> aVar = this.f7283i;
        if (aVar != null) {
            this.f7276b.setColorFilter(aVar.h());
        }
        this.f7275a.reset();
        for (int i9 = 0; i9 < this.f7280f.size(); i9++) {
            this.f7275a.addPath(this.f7280f.get(i9).a(), matrix);
        }
        canvas.drawPath(this.f7275a, this.f7276b);
        o1.d.b("FillContent#draw");
    }

    @Override // q1.c
    public String h() {
        return this.f7278d;
    }

    @Override // t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        if (t7 == o1.l.f6828a) {
            this.f7281g.m(cVar);
            return;
        }
        if (t7 == o1.l.f6831d) {
            this.f7282h.m(cVar);
            return;
        }
        if (t7 == o1.l.C) {
            r1.a<ColorFilter, ColorFilter> aVar = this.f7283i;
            if (aVar != null) {
                this.f7277c.E(aVar);
            }
            if (cVar == null) {
                this.f7283i = null;
                return;
            }
            r1.p pVar = new r1.p(cVar);
            this.f7283i = pVar;
            pVar.a(this);
            this.f7277c.k(this.f7283i);
        }
    }
}
