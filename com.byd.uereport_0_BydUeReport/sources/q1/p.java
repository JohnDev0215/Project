package q1;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import r1.a;

/* JADX INFO: compiled from: RepeaterContent.java */
/* JADX INFO: loaded from: classes.dex */
public class p implements e, m, j, a.b, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f7346a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f7347b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o1.g f7348c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final w1.a f7349d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f7350e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f7351f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final r1.a<Float, Float> f7352g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final r1.a<Float, Float> f7353h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r1.o f7354i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public d f7355j;

    public p(o1.g gVar, w1.a aVar, v1.g gVar2) {
        this.f7348c = gVar;
        this.f7349d = aVar;
        this.f7350e = gVar2.c();
        this.f7351f = gVar2.f();
        r1.a<Float, Float> aVarA = gVar2.b().a();
        this.f7352g = aVarA;
        aVar.k(aVarA);
        aVarA.a(this);
        r1.a<Float, Float> aVarA2 = gVar2.d().a();
        this.f7353h = aVarA2;
        aVar.k(aVarA2);
        aVarA2.a(this);
        r1.o oVarB = gVar2.e().b();
        this.f7354i = oVarB;
        oVarB.a(aVar);
        oVarB.b(this);
    }

    @Override // q1.m
    public Path a() {
        Path pathA = this.f7355j.a();
        this.f7347b.reset();
        float fFloatValue = this.f7352g.h().floatValue();
        float fFloatValue2 = this.f7353h.h().floatValue();
        for (int i8 = ((int) fFloatValue) - 1; i8 >= 0; i8--) {
            this.f7346a.set(this.f7354i.g(i8 + fFloatValue2));
            this.f7347b.addPath(pathA, this.f7346a);
        }
        return this.f7347b;
    }

    @Override // q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        this.f7355j.b(rectF, matrix, z7);
    }

    @Override // r1.a.b
    public void c() {
        this.f7348c.invalidateSelf();
    }

    @Override // q1.c
    public void d(List<c> list, List<c> list2) {
        this.f7355j.d(list, list2);
    }

    @Override // q1.j
    public void e(ListIterator<c> listIterator) {
        if (this.f7355j != null) {
            return;
        }
        while (listIterator.hasPrevious() && listIterator.previous() != this) {
        }
        ArrayList arrayList = new ArrayList();
        while (listIterator.hasPrevious()) {
            arrayList.add(listIterator.previous());
            listIterator.remove();
        }
        Collections.reverse(arrayList);
        this.f7355j = new d(this.f7348c, this.f7349d, "Repeater", this.f7351f, arrayList, null);
    }

    @Override // t1.e
    public void f(t1.d dVar, int i8, List<t1.d> list, t1.d dVar2) {
        a2.g.l(dVar, i8, list, dVar2, this);
    }

    @Override // q1.e
    public void g(Canvas canvas, Matrix matrix, int i8) {
        float fFloatValue = this.f7352g.h().floatValue();
        float fFloatValue2 = this.f7353h.h().floatValue();
        float fFloatValue3 = this.f7354i.i().h().floatValue() / 100.0f;
        float fFloatValue4 = this.f7354i.e().h().floatValue() / 100.0f;
        for (int i9 = ((int) fFloatValue) - 1; i9 >= 0; i9--) {
            this.f7346a.set(matrix);
            float f8 = i9;
            this.f7346a.preConcat(this.f7354i.g(f8 + fFloatValue2));
            this.f7355j.g(canvas, this.f7346a, (int) (i8 * a2.g.j(fFloatValue3, fFloatValue4, f8 / fFloatValue)));
        }
    }

    @Override // q1.c
    public String h() {
        return this.f7350e;
    }

    @Override // t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        if (this.f7354i.c(t7, cVar)) {
            return;
        }
        if (t7 == o1.l.f6844q) {
            this.f7352g.m(cVar);
        } else if (t7 == o1.l.f6845r) {
            this.f7353h.m(cVar);
        }
    }
}
