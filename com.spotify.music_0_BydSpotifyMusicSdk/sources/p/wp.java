package p;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public final class wp extends zu {
    public final Typeface i;
    public final hk0 j;
    public boolean k;

    public wp(hk0 hk0Var, Typeface typeface) {
        this.i = typeface;
        this.j = hk0Var;
    }

    @Override // p.zu
    public final void u0(int i) {
        if (this.k) {
            return;
        }
        iu iuVar = (iu) this.j.e;
        wp wpVar = iuVar.v;
        if (wpVar != null) {
            wpVar.k = true;
        }
        Typeface typeface = iuVar.s;
        Typeface typeface2 = this.i;
        if (typeface != typeface2) {
            iuVar.s = typeface2;
            iuVar.g();
        }
    }

    @Override // p.zu
    public final void w0(Typeface typeface, boolean z) {
        if (this.k) {
            return;
        }
        iu iuVar = (iu) this.j.e;
        wp wpVar = iuVar.v;
        if (wpVar != null) {
            wpVar.k = true;
        }
        if (iuVar.s != typeface) {
            iuVar.s = typeface;
            iuVar.g();
        }
    }
}
