package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public class p implements e, m, j, a.InterfaceC0025a, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Matrix f755a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path f756b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.f f757c;
    private final com.airbnb.lottie.t.l.a d;
    private final String e;
    private final boolean f;
    private final com.airbnb.lottie.r.c.a<Float, Float> g;
    private final com.airbnb.lottie.r.c.a<Float, Float> h;
    private final com.airbnb.lottie.r.c.o i;
    private d j;

    public p(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.k kVar) {
        this.f757c = fVar;
        this.d = aVar;
        this.e = kVar.c();
        this.f = kVar.f();
        com.airbnb.lottie.r.c.a<Float, Float> aVarA = kVar.b().a();
        this.g = aVarA;
        aVar.h(aVarA);
        aVarA.a(this);
        com.airbnb.lottie.r.c.a<Float, Float> aVarA2 = kVar.d().a();
        this.h = aVarA2;
        aVar.h(aVarA2);
        aVarA2.a(this);
        com.airbnb.lottie.r.c.o oVarB = kVar.e().b();
        this.i = oVarB;
        oVarB.a(aVar);
        oVarB.b(this);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.j.a(rectF, matrix, z);
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
    public void b() {
        this.f757c.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        this.j.c(list, list2);
    }

    @Override // com.airbnb.lottie.r.b.j
    public void d(ListIterator<c> listIterator) {
        if (this.j != null) {
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
        this.j = new d(this.f757c, this.d, "Repeater", this.f, arrayList, null);
    }

    @Override // com.airbnb.lottie.t.f
    public void e(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
        com.airbnb.lottie.w.g.l(eVar, i, list, eVar2, this);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void f(Canvas canvas, Matrix matrix, int i) {
        float fFloatValue = this.g.h().floatValue();
        float fFloatValue2 = this.h.h().floatValue();
        float fFloatValue3 = this.i.i().h().floatValue() / 100.0f;
        float fFloatValue4 = this.i.e().h().floatValue() / 100.0f;
        for (int i2 = ((int) fFloatValue) - 1; i2 >= 0; i2--) {
            this.f755a.set(matrix);
            float f = i2;
            this.f755a.preConcat(this.i.g(f + fFloatValue2));
            this.j.f(canvas, this.f755a, (int) (i * com.airbnb.lottie.w.g.j(fFloatValue3, fFloatValue4, f / fFloatValue)));
        }
    }

    @Override // com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        com.airbnb.lottie.r.c.a<Float, Float> aVar;
        if (this.i.c(t, cVar)) {
            return;
        }
        if (t == com.airbnb.lottie.k.q) {
            aVar = this.g;
        } else if (t != com.airbnb.lottie.k.r) {
            return;
        } else {
            aVar = this.h;
        }
        aVar.m(cVar);
    }

    @Override // com.airbnb.lottie.r.b.c
    public String getName() {
        return this.e;
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path getPath() {
        Path path = this.j.getPath();
        this.f756b.reset();
        float fFloatValue = this.g.h().floatValue();
        float fFloatValue2 = this.h.h().floatValue();
        for (int i = ((int) fFloatValue) - 1; i >= 0; i--) {
            this.f755a.set(this.i.g(i + fFloatValue2));
            this.f756b.addPath(path, this.f755a);
        }
        return this.f756b;
    }
}
