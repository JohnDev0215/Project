package k3;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r1 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ s1 f18105m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f18106n;

    public /* synthetic */ r1(s1 s1Var, int i7, int i10) {
        this.l = i10;
        this.f18105m = s1Var;
        this.f18106n = i7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                u1 u1Var = this.f18105m.f18116g;
                if (u1Var.R0(25) || u1Var.R0(33)) {
                    boolean zR0 = u1Var.R0(33);
                    int i7 = this.f18106n;
                    if (!zR0) {
                        u1Var.B0(i7);
                    } else {
                        u1Var.g0(i7, 1);
                    }
                }
                break;
            default:
                u1 u1Var2 = this.f18105m.f18116g;
                if (u1Var2.R0(26) || u1Var2.R0(34)) {
                    int i10 = this.f18106n;
                    if (i10 == -100) {
                        if (!u1Var2.R0(34)) {
                            u1Var2.h0(true);
                        } else {
                            u1Var2.c0(1, true);
                        }
                    } else if (i10 == -1) {
                        if (!u1Var2.R0(34)) {
                            u1Var2.e0();
                        } else {
                            u1Var2.I(1);
                        }
                    } else if (i10 == 1) {
                        if (!u1Var2.R0(34)) {
                            u1Var2.w0();
                        } else {
                            u1Var2.j0(1);
                        }
                    } else if (i10 == 100) {
                        if (!u1Var2.R0(34)) {
                            u1Var2.h0(false);
                        } else {
                            u1Var2.c0(1, false);
                        }
                    } else if (i10 != 101) {
                        AbstractC1347d.p("onAdjustVolume: Ignoring unknown direction: ", i10, "VolumeProviderCompat");
                    } else if (!u1Var2.R0(34)) {
                        u1Var2.h0(!u1Var2.g1());
                    } else {
                        u1Var2.c0(1, !u1Var2.g1());
                    }
                }
                break;
        }
    }
}
