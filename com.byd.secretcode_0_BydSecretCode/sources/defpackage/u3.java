package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;

/* JADX INFO: compiled from: CardViewApi21Impl.java */
/* JADX INFO: loaded from: classes.dex */
public class u3 implements x3 {
    @Override // defpackage.x3
    public float a(w3 w3Var) {
        return p(w3Var).c();
    }

    @Override // defpackage.x3
    public float b(w3 w3Var) {
        return p(w3Var).d();
    }

    @Override // defpackage.x3
    public float c(w3 w3Var) {
        return b(w3Var) * 2.0f;
    }

    @Override // defpackage.x3
    public float d(w3 w3Var) {
        return b(w3Var) * 2.0f;
    }

    @Override // defpackage.x3
    public void e(w3 w3Var) {
        m(w3Var, a(w3Var));
    }

    @Override // defpackage.x3
    public ColorStateList f(w3 w3Var) {
        return p(w3Var).b();
    }

    @Override // defpackage.x3
    public void g(w3 w3Var, float f) {
        w3Var.b().setElevation(f);
    }

    @Override // defpackage.x3
    public void h(w3 w3Var, Context context, ColorStateList colorStateList, float f, float f2, float f3) {
        w3Var.f(new y3(colorStateList, f));
        View viewB = w3Var.b();
        viewB.setClipToOutline(true);
        viewB.setElevation(f2);
        m(w3Var, f3);
    }

    @Override // defpackage.x3
    public void i(w3 w3Var, float f) {
        p(w3Var).h(f);
    }

    @Override // defpackage.x3
    public void j(w3 w3Var) {
        m(w3Var, a(w3Var));
    }

    @Override // defpackage.x3
    public void k(w3 w3Var, ColorStateList colorStateList) {
        p(w3Var).f(colorStateList);
    }

    @Override // defpackage.x3
    public float l(w3 w3Var) {
        return w3Var.b().getElevation();
    }

    @Override // defpackage.x3
    public void m(w3 w3Var, float f) {
        p(w3Var).g(f, w3Var.c(), w3Var.g());
        n(w3Var);
    }

    @Override // defpackage.x3
    public void n(w3 w3Var) {
        if (!w3Var.c()) {
            w3Var.a(0, 0, 0, 0);
            return;
        }
        float fA = a(w3Var);
        float fB = b(w3Var);
        int iCeil = (int) Math.ceil(z3.c(fA, fB, w3Var.g()));
        int iCeil2 = (int) Math.ceil(z3.d(fA, fB, w3Var.g()));
        w3Var.a(iCeil, iCeil2, iCeil, iCeil2);
    }

    @Override // defpackage.x3
    public void o() {
    }

    public final y3 p(w3 w3Var) {
        return (y3) w3Var.d();
    }
}
