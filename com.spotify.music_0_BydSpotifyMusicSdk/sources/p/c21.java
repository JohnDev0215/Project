package p;

import android.content.res.Resources;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class c21 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;
    public final xy1 f;
    public final xy1 g;
    public final xy1 h;

    public /* synthetic */ c21(wy1 wy1Var, ul0 ul0Var, wy1 wy1Var2, ul0 ul0Var2, wy1 wy1Var3, wy1 wy1Var4, wy1 wy1Var5, int i) {
        this.a = i;
        this.b = wy1Var;
        this.c = ul0Var;
        this.d = wy1Var2;
        this.e = ul0Var2;
        this.f = wy1Var3;
        this.g = wy1Var4;
        this.h = wy1Var5;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.h;
        xy1 xy1Var2 = this.g;
        xy1 xy1Var3 = this.f;
        xy1 xy1Var4 = this.e;
        xy1 xy1Var5 = this.d;
        xy1 xy1Var6 = this.c;
        xy1 xy1Var7 = this.b;
        switch (i) {
            case 0:
                return new b21((m) xy1Var7.get(), (Resources) xy1Var6.get(), (vv2) xy1Var5.get(), (ki1) xy1Var4.get(), (mh1) xy1Var3.get(), (u6) xy1Var2.get(), (w40) xy1Var.get());
            default:
                wi0 wi0Var = (wi0) xy1Var7.get();
                Set set = (Set) xy1Var6.get();
                gp2 gp2Var = (gp2) xy1Var5.get();
                xr2 xr2Var = (xr2) xy1Var4.get();
                kp2 kp2Var = (kp2) xy1Var3.get();
                u6 u6Var = (u6) xy1Var2.get();
                qp2 qp2Var = (qp2) xy1Var.get();
                v03.j(wi0Var, "eventPublisher");
                v03.j(set, "interactionObservers");
                v03.j(gp2Var, "eventContextProvider");
                v03.j(xr2Var, "userJourneyManager");
                v03.j(kp2Var, "eventValidator");
                v03.j(u6Var, "clock");
                v03.j(qp2Var, "ubiInteractionExperimentLogger");
                return new tp2(wi0Var, u6Var, gp2Var, kp2Var, qp2Var, xr2Var, set);
        }
    }
}
