package m;

import android.content.res.ColorStateList;

/* JADX INFO: compiled from: CardViewApi21Impl.java */
/* JADX INFO: loaded from: classes.dex */
class b implements d {
    b() {
    }

    private e n(c cVar) {
        return (e) cVar.d();
    }

    @Override // m.d
    public float a(c cVar) {
        return n(cVar).c();
    }

    @Override // m.d
    public float b(c cVar) {
        return n(cVar).d();
    }

    @Override // m.d
    public void c(c cVar, float f7) {
        n(cVar).h(f7);
    }

    @Override // m.d
    public float d(c cVar) {
        return b(cVar) * 2.0f;
    }

    @Override // m.d
    public ColorStateList e(c cVar) {
        return n(cVar).b();
    }

    @Override // m.d
    public float f(c cVar) {
        return b(cVar) * 2.0f;
    }

    @Override // m.d
    public float g(c cVar) {
        return cVar.b().getElevation();
    }

    @Override // m.d
    public void h(c cVar, float f7) {
        cVar.b().setElevation(f7);
    }

    @Override // m.d
    public void i(c cVar, ColorStateList colorStateList) {
        n(cVar).f(colorStateList);
    }

    @Override // m.d
    public void j(c cVar) {
        m(cVar, a(cVar));
    }

    @Override // m.d
    public void k(c cVar) {
        m(cVar, a(cVar));
    }

    @Override // m.d
    public void l() {
    }

    @Override // m.d
    public void m(c cVar, float f7) {
        n(cVar).g(f7, cVar.c(), cVar.e());
        o(cVar);
    }

    public void o(c cVar) {
        if (!cVar.c()) {
            cVar.a(0, 0, 0, 0);
            return;
        }
        float fA = a(cVar);
        float fB = b(cVar);
        int iCeil = (int) Math.ceil(f.a(fA, fB, cVar.e()));
        int iCeil2 = (int) Math.ceil(f.b(fA, fB, cVar.e()));
        cVar.a(iCeil, iCeil2, iCeil, iCeil2);
    }
}
