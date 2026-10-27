package b.d.a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: compiled from: CardViewApi21Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements e {
    @Override // b.d.a.e
    public float a(d dVar) {
        return p(dVar).c();
    }

    @Override // b.d.a.e
    public float b(d dVar) {
        return p(dVar).d();
    }

    @Override // b.d.a.e
    public float c(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // b.d.a.e
    public float d(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // b.d.a.e
    public void e(d dVar) {
        m(dVar, a(dVar));
    }

    @Override // b.d.a.e
    public ColorStateList f(d dVar) {
        return p(dVar).b();
    }

    @Override // b.d.a.e
    public void g(d dVar, float f) {
        dVar.b().setElevation(f);
    }

    @Override // b.d.a.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        dVar.f(new f(colorStateList, f));
        View viewB = dVar.b();
        viewB.setClipToOutline(true);
        viewB.setElevation(f2);
        m(dVar, f3);
    }

    @Override // b.d.a.e
    public void i(d dVar, float f) {
        p(dVar).h(f);
    }

    @Override // b.d.a.e
    public void j(d dVar) {
        m(dVar, a(dVar));
    }

    @Override // b.d.a.e
    public void k(d dVar, ColorStateList colorStateList) {
        p(dVar).f(colorStateList);
    }

    @Override // b.d.a.e
    public float l(d dVar) {
        return dVar.b().getElevation();
    }

    @Override // b.d.a.e
    public void m(d dVar, float f) {
        p(dVar).g(f, dVar.c(), dVar.g());
        n(dVar);
    }

    @Override // b.d.a.e
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

    @Override // b.d.a.e
    public void o() {
    }

    public final f p(d dVar) {
        return (f) dVar.d();
    }
}
