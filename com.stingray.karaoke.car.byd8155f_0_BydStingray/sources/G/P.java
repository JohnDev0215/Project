package G;

import T.C0735d;
import T.C0738e0;
import T.Q0;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class P implements Q0 {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2517m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C0738e0 f2518n;
    public int o;

    public P(int i7, int i10, int i11) {
        this.l = i10;
        this.f2517m = i11;
        int i12 = (i7 / i10) * i10;
        this.f2518n = C0735d.O(AbstractC2673a.b0(Math.max(i12 - i11, 0), i12 + i10 + i11), T.S.f8224q);
        this.o = i7;
    }

    public final void b(int i7) {
        if (i7 != this.o) {
            this.o = i7;
            int i10 = this.l;
            int i11 = (i7 / i10) * i10;
            int i12 = this.f2517m;
            this.f2518n.setValue(AbstractC2673a.b0(Math.max(i11 - i12, 0), i11 + i10 + i12));
        }
    }

    @Override // T.Q0
    public final Object getValue() {
        return (G7.d) this.f2518n.getValue();
    }
}
