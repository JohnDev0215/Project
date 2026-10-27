package r;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: compiled from: CardViewApi21Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements e {
    @Override // r.e
    public float a(d dVar) {
        return p(dVar).c();
    }

    @Override // r.e
    public float b(d dVar) {
        return p(dVar).d();
    }

    @Override // r.e
    public float c(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // r.e
    public float d(d dVar) {
        return b(dVar) * 2.0f;
    }

    @Override // r.e
    public void e(d dVar) {
        m(dVar, a(dVar));
    }

    @Override // r.e
    public ColorStateList f(d dVar) {
        return p(dVar).b();
    }

    @Override // r.e
    public void g(d dVar, float f3) {
        dVar.b().setElevation(f3);
    }

    @Override // r.e
    public void h(d dVar, Context context, ColorStateList colorStateList, float f3, float f4, float f5) {
        dVar.f(new f(colorStateList, f3));
        View viewB = dVar.b();
        viewB.setClipToOutline(true);
        viewB.setElevation(f4);
        m(dVar, f5);
    }

    @Override // r.e
    public void i(d dVar, float f3) {
        p(dVar).h(f3);
    }

    @Override // r.e
    public void j(d dVar) {
        m(dVar, a(dVar));
    }

    @Override // r.e
    public void k(d dVar, ColorStateList colorStateList) {
        p(dVar).f(colorStateList);
    }

    @Override // r.e
    public float l(d dVar) {
        return dVar.b().getElevation();
    }

    @Override // r.e
    public void m(d dVar, float f3) {
        p(dVar).g(f3, dVar.c(), dVar.g());
        n(dVar);
    }

    @Override // r.e
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

    @Override // r.e
    public void o() {
    }

    public final f p(d dVar) {
        return (f) dVar.d();
    }
}
