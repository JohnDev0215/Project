package q1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.List;
import r1.a;

/* JADX INFO: compiled from: ContentGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements e, m, a.b, t1.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Paint f7256a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RectF f7257b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f7258c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Path f7259d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f7260e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f7261f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f7262g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final List<c> f7263h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final o1.g f7264i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public List<m> f7265j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public r1.o f7266k;

    public d(o1.g gVar, w1.a aVar, v1.j jVar) {
        this(gVar, aVar, jVar.c(), jVar.d(), e(gVar, aVar, jVar.b()), j(jVar.b()));
    }

    public static List<c> e(o1.g gVar, w1.a aVar, List<v1.b> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i8 = 0; i8 < list.size(); i8++) {
            c cVarA = list.get(i8).a(gVar, aVar);
            if (cVarA != null) {
                arrayList.add(cVarA);
            }
        }
        return arrayList;
    }

    public static u1.l j(List<v1.b> list) {
        for (int i8 = 0; i8 < list.size(); i8++) {
            v1.b bVar = list.get(i8);
            if (bVar instanceof u1.l) {
                return (u1.l) bVar;
            }
        }
        return null;
    }

    @Override // q1.m
    public Path a() {
        this.f7258c.reset();
        r1.o oVar = this.f7266k;
        if (oVar != null) {
            this.f7258c.set(oVar.f());
        }
        this.f7259d.reset();
        if (this.f7262g) {
            return this.f7259d;
        }
        for (int size = this.f7263h.size() - 1; size >= 0; size--) {
            c cVar = this.f7263h.get(size);
            if (cVar instanceof m) {
                this.f7259d.addPath(((m) cVar).a(), this.f7258c);
            }
        }
        return this.f7259d;
    }

    @Override // q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        this.f7258c.set(matrix);
        r1.o oVar = this.f7266k;
        if (oVar != null) {
            this.f7258c.preConcat(oVar.f());
        }
        this.f7260e.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int size = this.f7263h.size() - 1; size >= 0; size--) {
            c cVar = this.f7263h.get(size);
            if (cVar instanceof e) {
                ((e) cVar).b(this.f7260e, this.f7258c, z7);
                rectF.union(this.f7260e);
            }
        }
    }

    @Override // r1.a.b
    public void c() {
        this.f7264i.invalidateSelf();
    }

    @Override // q1.c
    public void d(List<c> list, List<c> list2) {
        ArrayList arrayList = new ArrayList(list.size() + this.f7263h.size());
        arrayList.addAll(list);
        for (int size = this.f7263h.size() - 1; size >= 0; size--) {
            c cVar = this.f7263h.get(size);
            cVar.d(arrayList, this.f7263h.subList(0, size));
            arrayList.add(cVar);
        }
    }

    @Override // t1.e
    public void f(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        if (dVar.g(h(), i8)) {
            if (!"__container".equals(h())) {
                dVar2 = dVar2.a(h());
                if (dVar.c(h(), i8)) {
                    list.add(dVar2.i(this));
                }
            }
            if (dVar.h(h(), i8)) {
                int iE = i8 + dVar.e(h(), i8);
                for (int i9 = 0; i9 < this.f7263h.size(); i9++) {
                    c cVar = this.f7263h.get(i9);
                    if (cVar instanceof t1.e) {
                        ((t1.e) cVar).f(dVar, iE, list, dVar2);
                    }
                }
            }
        }
    }

    @Override // q1.e
    public void g(Canvas canvas, Matrix matrix, int i8) {
        if (this.f7262g) {
            return;
        }
        this.f7258c.set(matrix);
        r1.o oVar = this.f7266k;
        if (oVar != null) {
            this.f7258c.preConcat(oVar.f());
            i8 = (int) (((((this.f7266k.h() == null ? 100 : this.f7266k.h().h().intValue()) / 100.0f) * i8) / 255.0f) * 255.0f);
        }
        boolean z7 = this.f7264i.F() && m() && i8 != 255;
        if (z7) {
            this.f7257b.set(0.0f, 0.0f, 0.0f, 0.0f);
            b(this.f7257b, this.f7258c, true);
            this.f7256a.setAlpha(i8);
            a2.h.m(canvas, this.f7257b, this.f7256a);
        }
        if (z7) {
            i8 = 255;
        }
        for (int size = this.f7263h.size() - 1; size >= 0; size--) {
            c cVar = this.f7263h.get(size);
            if (cVar instanceof e) {
                ((e) cVar).g(canvas, this.f7258c, i8);
            }
        }
        if (z7) {
            canvas.restore();
        }
    }

    @Override // q1.c
    public String h() {
        return this.f7261f;
    }

    @Override // t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        r1.o oVar = this.f7266k;
        if (oVar != null) {
            oVar.c(t7, cVar);
        }
    }

    public List<m> k() {
        if (this.f7265j == null) {
            this.f7265j = new ArrayList();
            for (int i8 = 0; i8 < this.f7263h.size(); i8++) {
                c cVar = this.f7263h.get(i8);
                if (cVar instanceof m) {
                    this.f7265j.add((m) cVar);
                }
            }
        }
        return this.f7265j;
    }

    public Matrix l() {
        r1.o oVar = this.f7266k;
        if (oVar != null) {
            return oVar.f();
        }
        this.f7258c.reset();
        return this.f7258c;
    }

    public final boolean m() {
        int i8 = 0;
        for (int i9 = 0; i9 < this.f7263h.size(); i9++) {
            if ((this.f7263h.get(i9) instanceof e) && (i8 = i8 + 1) >= 2) {
                return true;
            }
        }
        return false;
    }

    public d(o1.g gVar, w1.a aVar, String str, boolean z7, List<c> list, u1.l lVar) {
        this.f7256a = new p1.a();
        this.f7257b = new RectF();
        this.f7258c = new Matrix();
        this.f7259d = new Path();
        this.f7260e = new RectF();
        this.f7261f = str;
        this.f7264i = gVar;
        this.f7262g = z7;
        this.f7263h = list;
        if (lVar != null) {
            r1.o oVarB = lVar.b();
            this.f7266k = oVarB;
            oVarB.a(aVar);
            this.f7266k.b(this);
        }
        ArrayList arrayList = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            c cVar = list.get(size);
            if (cVar instanceof j) {
                arrayList.add((j) cVar);
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            ((j) arrayList.get(size2)).e(list.listIterator(list.size()));
        }
    }
}
