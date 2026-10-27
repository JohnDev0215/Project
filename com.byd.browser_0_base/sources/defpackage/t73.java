package defpackage;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextPaint;

/* JADX INFO: compiled from: TextAppearance.java */
/* JADX INFO: loaded from: classes.dex */
public final class t73 extends vd1 {
    public final /* synthetic */ Context k;
    public final /* synthetic */ TextPaint l;
    public final /* synthetic */ vd1 m;
    public final /* synthetic */ s73 n;

    public t73(s73 s73Var, Context context, TextPaint textPaint, vd1 vd1Var) {
        this.n = s73Var;
        this.k = context;
        this.l = textPaint;
        this.m = vd1Var;
    }

    @Override // defpackage.vd1
    public final void s(int i) {
        this.m.s(i);
    }

    @Override // defpackage.vd1
    public final void w(Typeface typeface, boolean z) {
        this.n.g(this.k, this.l, typeface);
        this.m.w(typeface, z);
    }
}
