package com.airbnb.lottie.r.b;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.r.c.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: compiled from: RepeaterContent.java */
/* JADX INFO: loaded from: classes.dex */
public class p implements e, m, j, a.InterfaceC0077a, k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Matrix f3096a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Path f3097b = new Path();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.airbnb.lottie.f f3098c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.model.layer.a f3099d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3100e;
    private final boolean f;
    private final com.airbnb.lottie.r.c.a<Float, Float> g;
    private final com.airbnb.lottie.r.c.a<Float, Float> h;
    private final com.airbnb.lottie.r.c.o i;
    private d j;

    public p(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.g gVar) {
        this.f3098c = fVar;
        this.f3099d = aVar;
        this.f3100e = gVar.c();
        this.f = gVar.f();
        com.airbnb.lottie.r.c.a<Float, Float> aVarA = gVar.b().a();
        this.g = aVarA;
        aVar.j(aVarA);
        aVarA.a(this);
        com.airbnb.lottie.r.c.a<Float, Float> aVarA2 = gVar.d().a();
        this.h = aVarA2;
        aVar.j(aVarA2);
        aVarA2.a(this);
        com.airbnb.lottie.r.c.o oVarB = gVar.e().b();
        this.i = oVarB;
        oVarB.a(aVar);
        oVarB.b(this);
    }

    @Override // com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        this.j.a(rectF, matrix, z);
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0077a
    public void b() {
        this.f3098c.invalidateSelf();
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
        this.j = new d(this.f3098c, this.f3099d, "Repeater", this.f, arrayList, null);
    }

    @Override // com.airbnb.lottie.model.e
    public void e(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
        com.airbnb.lottie.u.g.l(dVar, i, list, dVar2, this);
    }

    @Override // com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        if (this.i.c(t, cVar)) {
            return;
        }
        if (t == com.airbnb.lottie.k.q) {
            this.g.m(cVar);
        } else if (t == com.airbnb.lottie.k.r) {
            this.h.m(cVar);
        }
    }

    @Override // com.airbnb.lottie.r.b.e
    public void g(Canvas canvas, Matrix matrix, int i) {
        float fFloatValue = this.g.h().floatValue();
        float fFloatValue2 = this.h.h().floatValue();
        float fFloatValue3 = this.i.i().h().floatValue() / 100.0f;
        float fFloatValue4 = this.i.e().h().floatValue() / 100.0f;
        for (int i2 = ((int) fFloatValue) - 1; i2 >= 0; i2--) {
            this.f3096a.set(matrix);
            float f = i2;
            this.f3096a.preConcat(this.i.g(f + fFloatValue2));
            this.j.g(canvas, this.f3096a, (int) (i * com.airbnb.lottie.u.g.j(fFloatValue3, fFloatValue4, f / fFloatValue)));
        }
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path h() {
        Path pathH = this.j.h();
        this.f3097b.reset();
        float fFloatValue = this.g.h().floatValue();
        float fFloatValue2 = this.h.h().floatValue();
        for (int i = ((int) fFloatValue) - 1; i >= 0; i--) {
            this.f3096a.set(this.i.g(i + fFloatValue2));
            this.f3097b.addPath(pathH, this.f3096a);
        }
        return this.f3097b;
    }

    @Override // com.airbnb.lottie.r.b.c
    public String i() {
        return this.f3100e;
    }
}
