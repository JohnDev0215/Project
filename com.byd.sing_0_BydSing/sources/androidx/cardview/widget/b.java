package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: compiled from: CardViewApi21Impl.java */
/* JADX INFO: loaded from: classes.dex */
class b implements e {
    b() {
    }

    private f p(d dVar) {
        return (f) dVar.g();
    }

    @Override // androidx.cardview.widget.e
    public float a(d dVar) {
        return p(dVar).c();
    }

    @Override // androidx.cardview.widget.e
    public ColorStateList b(d dVar) {
        return p(dVar).b();
    }

    @Override // androidx.cardview.widget.e
    public void c(d dVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        dVar.d(new f(colorStateList, f));
        View viewB = dVar.b();
        viewB.setClipToOutline(true);
        viewB.setElevation(f2);
        o(dVar, f3);
    }

    @Override // androidx.cardview.widget.e
    public void d(d dVar, float f) {
        p(dVar).h(f);
    }

    @Override // androidx.cardview.widget.e
    public float e(d dVar) {
        return dVar.b().getElevation();
    }

    @Override // androidx.cardview.widget.e
    public void f(d dVar) {
        if (!dVar.f()) {
            dVar.a(0, 0, 0, 0);
            return;
        }
        float fA = a(dVar);
        float fH = h(dVar);
        int iCeil = (int) Math.ceil(g.c(fA, fH, dVar.e()));
        int iCeil2 = (int) Math.ceil(g.d(fA, fH, dVar.e()));
        dVar.a(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // androidx.cardview.widget.e
    public void g() {
    }

    @Override // androidx.cardview.widget.e
    public float h(d dVar) {
        return p(dVar).d();
    }

    @Override // androidx.cardview.widget.e
    public float i(d dVar) {
        return h(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public float j(d dVar) {
        return h(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public void k(d dVar) {
        o(dVar, a(dVar));
    }

    @Override // androidx.cardview.widget.e
    public void l(d dVar, float f) {
        dVar.b().setElevation(f);
    }

    @Override // androidx.cardview.widget.e
    public void m(d dVar) {
        o(dVar, a(dVar));
    }

    @Override // androidx.cardview.widget.e
    public void n(d dVar, ColorStateList colorStateList) {
        p(dVar).f(colorStateList);
    }

    @Override // androidx.cardview.widget.e
    public void o(d dVar, float f) {
        p(dVar).g(f, dVar.f(), dVar.e());
        f(dVar);
    }
}
