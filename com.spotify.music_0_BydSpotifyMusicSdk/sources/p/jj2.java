package p;

import android.graphics.Typeface;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public final class jj2 extends zu {
    public final /* synthetic */ TextPaint i;
    public final /* synthetic */ zu j;
    public final /* synthetic */ kj2 k;

    public jj2(kj2 kj2Var, TextPaint textPaint, zu zuVar) {
        this.k = kj2Var;
        this.i = textPaint;
        this.j = zuVar;
    }

    @Override // p.zu
    public final void u0(int i) {
        this.j.u0(i);
    }

    @Override // p.zu
    public final void w0(Typeface typeface, boolean z) {
        this.k.g(this.i, typeface);
        this.j.w0(typeface, z);
    }
}
