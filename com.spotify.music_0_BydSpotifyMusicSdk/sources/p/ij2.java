package p;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public final class ij2 extends zu {
    public final /* synthetic */ zu i;
    public final /* synthetic */ kj2 j;

    public ij2(kj2 kj2Var, zu zuVar) {
        this.j = kj2Var;
        this.i = zuVar;
    }

    @Override // p.zu
    public final void u0(int i) {
        this.j.m = true;
        this.i.u0(i);
    }

    @Override // p.zu
    public final void v0(Typeface typeface) {
        kj2 kj2Var = this.j;
        kj2Var.n = Typeface.create(typeface, kj2Var.c);
        kj2Var.m = true;
        this.i.w0(kj2Var.n, false);
    }
}
