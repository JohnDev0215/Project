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
        return (f) dVar.d();
    }

    @Override // androidx.cardview.widget.e
    public float a(d dVar) {
        return p(dVar).c();
    }

    @Override // androidx.cardview.widget.e
    public float b(d dVar) {
        return p(dVar).d();
    }

    @Override // androidx.cardview.widget.e
    public float c(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public float d(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // androidx.cardview.widget.e
    public void e(d dVar) {
        m(dVar, a(dVar));
    }

    @Override // androidx.cardview.widget.e
    public ColorStateList f(d dVar) {
        return p(dVar).b();
    }

    @Override // androidx.cardview.widget.e
    public void g(d dVar, float f2) {
        dVar.b().setElevation(f2);
    }

    @Override // androidx.cardview.widget.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f2, float f3, float f4) {
        dVar.f(new f(colorStateList, f2));
        View viewB = dVar.b();
        viewB.setClipToOutline(true);
        viewB.setElevation(f3);
        m(dVar, f4);
    }

    @Override // androidx.cardview.widget.e
    public void i(d dVar, float f2) {
        p(dVar).h(f2);
    }

    @Override // androidx.cardview.widget.e
    public void j(d dVar) {
        m(dVar, a(dVar));
    }

    @Override // androidx.cardview.widget.e
    public void k(d dVar, ColorStateList colorStateList) {
        p(dVar).f(colorStateList);
    }

    @Override // androidx.cardview.widget.e
    public float l(d dVar) {
        return dVar.b().getElevation();
    }

    @Override // androidx.cardview.widget.e
    public void m(d dVar, float f2) {
        p(dVar).g(f2, dVar.c(), dVar.g());
        n(dVar);
    }

    @Override // androidx.cardview.widget.e
    public void n(d dVar) {
        if (!dVar.c()) {
            dVar.a(0, 0, 0, 0);
            return;
        }
        float fA = a(dVar);
        float fB = b(dVar);
        int iCeil = (int) Math.ceil(g.c(fA, fB, dVar.g()));
        int iCeil2 = (int) Math.ceil(g.d(fA, fB, dVar.g()));
        dVar.a(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // androidx.cardview.widget.e
    public void o() {
    }
}
