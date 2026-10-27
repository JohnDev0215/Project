package defpackage;

import org.chromium.gfx.mojom.Rect;

/* JADX INFO: compiled from: StylusWritingGestureData.java */
/* JADX INFO: loaded from: classes.dex */
public final class xv2 extends hv2 {
    public static final t50 h = new t50(48, 0);
    public int b;
    public int c;
    public Rect d;
    public Rect e;
    public mu2 f;
    public mu2 g;

    public xv2() {
        super(48);
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(h);
        qh0VarT.b(this.b, 8);
        qh0VarT.b(this.c, 12);
        qh0VarT.k(this.d, 16, false);
        qh0VarT.k(this.e, 24, true);
        qh0VarT.k(this.f, 32, false);
        qh0VarT.k(this.g, 40, true);
    }
}
