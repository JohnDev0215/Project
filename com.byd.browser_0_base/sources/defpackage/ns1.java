package defpackage;

import defpackage.px0;

/* JADX INFO: compiled from: OneShotImpressionListener.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ns1 implements px0.a {
    public final px0.a e;
    public boolean f;

    public ns1(gc3 gc3Var) {
        this.e = gc3Var;
    }

    @Override // px0.a
    public final void c() {
        if (this.f) {
            return;
        }
        this.f = true;
        this.e.c();
    }
}
