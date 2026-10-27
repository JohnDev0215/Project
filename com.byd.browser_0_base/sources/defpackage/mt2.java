package defpackage;

import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: StatusIndicatorMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mt2 extends vl {
    public final /* synthetic */ String f;
    public final /* synthetic */ Drawable g;
    public final /* synthetic */ int h;
    public final /* synthetic */ int i;
    public final /* synthetic */ qt2 j;

    public mt2(qt2 qt2Var, String str, Drawable drawable, int i, int i2) {
        this.j = qt2Var;
        this.f = str;
        this.g = drawable;
        this.h = i;
        this.i = i2;
    }

    @Override // defpackage.vl
    public final void b() {
        qt2 qt2Var = this.j;
        qt2Var.e.p(rt2.a, this.f);
        qt2Var.e.p(rt2.b, this.g);
        qt2Var.e.n(rt2.g, this.h);
        qt2Var.e.n(rt2.h, this.i);
    }
}
