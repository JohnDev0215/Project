package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ContentGroup.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements e, m, a.b, com.airbnb.lottie.model.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Paint f2673a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private RectF f2674b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f2675c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Path f2676d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final RectF f2677e;
    private final String f;
    private final boolean g;
    private final List<c> h;
    private final com.airbnb.lottie.f i;
    private List<m> j;
    private com.airbnb.lottie.r.c.o k;

    public d(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.j jVar) {
        this(fVar, aVar, jVar.c(), jVar.d(), d(fVar, aVar, jVar.b()), j(jVar.b()));
    }

    private static List<c> d(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, List<com.airbnb.lottie.model.content.b> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            c cVarA = list.get(i).a(fVar, aVar);
            if (cVarA != null) {
                arrayList.add(cVarA);
            }
        }
        return arrayList;
    }

    static com.airbnb.lottie.model.i.l j(List<com.airbnb.lottie.model.content.b> list) {
        for (int i = 0; i < list.size(); i++) {
            com.airbnb.lottie.model.content.b bVar = list.get(i);
            if (bVar instanceof com.airbnb.lottie.model.i.l) {
                return (com.airbnb.lottie.model.i.l) bVar;
            }
        }
        return null;
    }

    private boolean m() {
        int i = 0;
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            if ((this.h.get(i2) instanceof e) && (i = i + 1) >= 2) {
                return true;
            }
        }
        return false;
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.f2675c.set(matrix);
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            this.f2675c.preConcat(oVar.f());
        }
        this.f2677e.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int size = this.h.size() - 1; size >= 0; size--) {
            c cVar = this.h.get(size);
            if (cVar instanceof e) {
                ((e) cVar).a(this.f2677e, this.f2675c, z);
                rectF.union(this.f2677e);
            }
        }
    }

    @Override // com.airbnb.lottie.r.c.a.b
    public void b() {
        this.i.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        ArrayList arrayList = new ArrayList(list.size() + this.h.size());
        arrayList.addAll(list);
        for (int size = this.h.size() - 1; size >= 0; size--) {
            c cVar = this.h.get(size);
            cVar.c(arrayList, this.h.subList(0, size));
            arrayList.add(cVar);
        }
    }

    @Override // com.airbnb.lottie.model.e
    public void e(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
        if (dVar.g(i(), i)) {
            if (!"__container".equals(i())) {
                dVar2 = dVar2.a(i());
                if (dVar.c(i(), i)) {
                    list.add(dVar2.i(this));
                }
            }
            if (dVar.h(i(), i)) {
                int iE = i + dVar.e(i(), i);
                for (int i2 = 0; i2 < this.h.size(); i2++) {
                    c cVar = this.h.get(i2);
                    if (cVar instanceof com.airbnb.lottie.model.e) {
                        ((com.airbnb.lottie.model.e) cVar).e(dVar, iE, list, dVar2);
                    }
                }
            }
        }
    }

    @Override // com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            oVar.c(t, cVar);
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    public void g(Canvas canvas, Matrix matrix, int i) {
        if (this.g) {
            return;
        }
        this.f2675c.set(matrix);
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            this.f2675c.preConcat(oVar.f());
            i = (int) (((((this.k.h() == null ? 100 : this.k.h().h().intValue()) / 100.0f) * i) / 255.0f) * 255.0f);
        }
        boolean z = this.i.F() && m() && i != 255;
        if (z) {
            this.f2674b.set(0.0f, 0.0f, 0.0f, 0.0f);
            a(this.f2674b, this.f2675c, true);
            this.f2673a.setAlpha(i);
            com.airbnb.lottie.u.h.m(canvas, this.f2674b, this.f2673a);
        }
        if (z) {
            i = 255;
        }
        for (int size = this.h.size() - 1; size >= 0; size--) {
            c cVar = this.h.get(size);
            if (cVar instanceof e) {
                ((e) cVar).g(canvas, this.f2675c, i);
            }
        }
        if (z) {
            canvas.restore();
        }
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path h() {
        this.f2675c.reset();
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            this.f2675c.set(oVar.f());
        }
        this.f2676d.reset();
        if (this.g) {
            return this.f2676d;
        }
        for (int size = this.h.size() - 1; size >= 0; size--) {
            c cVar = this.h.get(size);
            if (cVar instanceof m) {
                this.f2676d.addPath(((m) cVar).h(), this.f2675c);
            }
        }
        return this.f2676d;
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.f;
    }

    List<m> k() {
        if (this.j == null) {
            this.j = new ArrayList();
            for (int i = 0; i < this.h.size(); i++) {
                c cVar = this.h.get(i);
                if (cVar instanceof m) {
                    this.j.add((m) cVar);
                }
            }
        }
        return this.j;
    }

    Matrix l() {
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            return oVar.f();
        }
        this.f2675c.reset();
        return this.f2675c;
    }

    d(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, String str, boolean z, List<c> list, com.airbnb.lottie.model.i.l lVar) {
        this.f2673a = new com.airbnb.lottie.r.a();
        this.f2674b = new RectF();
        this.f2675c = new Matrix();
        this.f2676d = new Path();
        this.f2677e = new RectF();
        this.f = str;
        this.i = fVar;
        this.g = z;
        this.h = list;
        if (lVar != null) {
            com.airbnb.lottie.r.c.o oVarB = lVar.b();
            this.k = oVarB;
            oVarB.a(aVar);
            this.k.b(this);
        }
        ArrayList arrayList = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            c cVar = list.get(size);
            if (cVar instanceof j) {
                arrayList.add((j) cVar);
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            ((j) arrayList.get(size2)).d(list.listIterator(list.size()));
        }
    }
}
