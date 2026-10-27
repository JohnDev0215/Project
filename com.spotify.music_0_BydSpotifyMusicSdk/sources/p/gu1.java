package p;

import io.reactivex.rxjava3.core.Scheduler;

/* JADX INFO: loaded from: classes.dex */
public final class gu1 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;

    public /* synthetic */ gu1(wy1 wy1Var, ul0 ul0Var, int i) {
        this.a = i;
        this.b = wy1Var;
        this.c = ul0Var;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.c;
        xy1 xy1Var2 = this.b;
        switch (i) {
            case 0:
                u81 u81VarA = ua0.a(xy1Var2);
                return new sh0((vz) u81VarA.get(), (yc1) xy1Var.get());
            case 1:
                u81 u81VarA2 = ua0.a(xy1Var2);
                return new wh0((vz) u81VarA2.get(), (yc1) xy1Var.get());
            case 2:
                return new xr2((yr2) xy1Var2.get(), (kp2) xy1Var.get());
            default:
                return new te2((re2) xy1Var2.get(), (Scheduler) xy1Var.get());
        }
    }
}
