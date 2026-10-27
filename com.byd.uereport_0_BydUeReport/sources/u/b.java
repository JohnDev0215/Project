package u;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: compiled from: CardViewApi21Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements e {
    @Override // u.e
    public float a(d dVar) {
        return p(dVar).c();
    }

    @Override // u.e
    public ColorStateList b(d dVar) {
        return p(dVar).b();
    }

    @Override // u.e
    public void c(d dVar, Context context, ColorStateList colorStateList, float f8, float f9, float f10) {
        dVar.d(new f(colorStateList, f8));
        View viewB = dVar.b();
        viewB.setClipToOutline(true);
        viewB.setElevation(f9);
        o(dVar, f10);
    }

    @Override // u.e
    public void d(d dVar, float f8) {
        p(dVar).h(f8);
    }

    @Override // u.e
    public float e(d dVar) {
        return dVar.b().getElevation();
    }

    @Override // u.e
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

    @Override // u.e
    public void g() {
    }

    @Override // u.e
    public float h(d dVar) {
        return p(dVar).d();
    }

    @Override // u.e
    public float i(d dVar) {
        return h(dVar) * 2.0f;
    }

    @Override // u.e
    public float j(d dVar) {
        return h(dVar) * 2.0f;
    }

    @Override // u.e
    public void k(d dVar) {
        o(dVar, a(dVar));
    }

    @Override // u.e
    public void l(d dVar, float f8) {
        dVar.b().setElevation(f8);
    }

    @Override // u.e
    public void m(d dVar) {
        o(dVar, a(dVar));
    }

    @Override // u.e
    public void n(d dVar, ColorStateList colorStateList) {
        p(dVar).f(colorStateList);
    }

    @Override // u.e
    public void o(d dVar, float f8) {
        p(dVar).g(f8, dVar.f(), dVar.e());
        f(dVar);
    }

    public final f p(d dVar) {
        return (f) dVar.g();
    }
}
