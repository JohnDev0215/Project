package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d implements e, m, a.InterfaceC0025a, com.airbnb.lottie.t.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Paint f732a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private RectF f733b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f734c;
    private final Path d;
    private final RectF e;
    private final String f;
    private final boolean g;
    private final List<c> h;
    private final com.airbnb.lottie.f i;

    @Nullable
    private List<m> j;

    @Nullable
    private com.airbnb.lottie.r.c.o k;

    public d(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.n nVar) {
        this(fVar, aVar, nVar.c(), nVar.d(), d(fVar, aVar, nVar.b()), h(nVar.b()));
    }

    d(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, String str, boolean z, List<c> list, @Nullable com.airbnb.lottie.t.j.l lVar) {
        this.f732a = new com.airbnb.lottie.r.a();
        this.f733b = new RectF();
        this.f734c = new Matrix();
        this.d = new Path();
        this.e = new RectF();
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

    private static List<c> d(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, List<com.airbnb.lottie.t.k.b> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            c cVarA = list.get(i).a(fVar, aVar);
            if (cVarA != null) {
                arrayList.add(cVarA);
            }
        }
        return arrayList;
    }

    @Nullable
    static com.airbnb.lottie.t.j.l h(List<com.airbnb.lottie.t.k.b> list) {
        for (int i = 0; i < list.size(); i++) {
            com.airbnb.lottie.t.k.b bVar = list.get(i);
            if (bVar instanceof com.airbnb.lottie.t.j.l) {
                return (com.airbnb.lottie.t.j.l) bVar;
            }
        }
        return null;
    }

    private boolean k() {
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
        this.f734c.set(matrix);
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            this.f734c.preConcat(oVar.f());
        }
        this.e.set(0.0f, 0.0f, 0.0f, 0.0f);
        for (int size = this.h.size() - 1; size >= 0; size--) {
            c cVar = this.h.get(size);
            if (cVar instanceof e) {
                ((e) cVar).a(this.e, this.f734c, z);
                rectF.union(this.e);
            }
        }
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
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

    @Override // com.airbnb.lottie.t.f
    public void e(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
        if (eVar.g(getName(), i)) {
            if (!"__container".equals(getName())) {
                eVar2 = eVar2.a(getName());
                if (eVar.c(getName(), i)) {
                    list.add(eVar2.i(this));
                }
            }
            if (eVar.h(getName(), i)) {
                int iE = i + eVar.e(getName(), i);
                for (int i2 = 0; i2 < this.h.size(); i2++) {
                    c cVar = this.h.get(i2);
                    if (cVar instanceof com.airbnb.lottie.t.f) {
                        ((com.airbnb.lottie.t.f) cVar).e(eVar, iE, list, eVar2);
                    }
                }
            }
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    public void f(Canvas canvas, Matrix matrix, int i) {
        if (this.g) {
            return;
        }
        this.f734c.set(matrix);
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            this.f734c.preConcat(oVar.f());
            i = (int) (((((this.k.h() == null ? 100 : this.k.h().h().intValue()) / 100.0f) * i) / 255.0f) * 255.0f);
        }
        boolean z = this.i.C() && k() && i != 255;
        if (z) {
            this.f733b.set(0.0f, 0.0f, 0.0f, 0.0f);
            a(this.f733b, this.f734c, true);
            this.f732a.setAlpha(i);
            com.airbnb.lottie.w.h.m(canvas, this.f733b, this.f732a);
        }
        if (z) {
            i = 255;
        }
        for (int size = this.h.size() - 1; size >= 0; size--) {
            c cVar = this.h.get(size);
            if (cVar instanceof e) {
                ((e) cVar).f(canvas, this.f734c, i);
            }
        }
        if (z) {
            canvas.restore();
        }
    }

    @Override // com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            oVar.c(t, cVar);
        }
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.f;
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path getPath() {
        this.f734c.reset();
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            this.f734c.set(oVar.f());
        }
        this.d.reset();
        if (this.g) {
            return this.d;
        }
        for (int size = this.h.size() - 1; size >= 0; size--) {
            c cVar = this.h.get(size);
            if (cVar instanceof m) {
                this.d.addPath(((m) cVar).getPath(), this.f734c);
            }
        }
        return this.d;
    }

    List<m> i() {
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

    Matrix j() {
        com.airbnb.lottie.r.c.o oVar = this.k;
        if (oVar != null) {
            return oVar.f();
        }
        this.f734c.reset();
        return this.f734c;
    }
}
