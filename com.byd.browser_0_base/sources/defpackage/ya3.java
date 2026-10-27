package defpackage;

import android.content.Context;
import defpackage.lw;

/* JADX INFO: compiled from: TintedCompositorButton.java */
/* JADX INFO: loaded from: classes.dex */
public final class ya3 extends lw {
    public final Context n;

    public ya3(Context context, float f, float f2, lw.c cVar) {
        super(context, f, f2, cVar);
        this.n = context;
    }

    public final void g(int i, int i2, int i3, int i4) {
        Context context = this.n;
        bz.b(context, i).getDefaultColor();
        bz.b(context, i2).getDefaultColor();
        bz.b(context, i3).getDefaultColor();
        bz.b(context, i4).getDefaultColor();
    }
}
