package p;

import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public final class q0 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;
    public final xy1 f;

    public /* synthetic */ q0(wy1 wy1Var, wy1 wy1Var2, wy1 wy1Var3, wy1 wy1Var4, wy1 wy1Var5, int i) {
        this.a = i;
        this.b = wy1Var;
        this.c = wy1Var2;
        this.d = wy1Var3;
        this.e = wy1Var4;
        this.f = wy1Var5;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.f;
        xy1 xy1Var2 = this.e;
        xy1 xy1Var3 = this.d;
        xy1 xy1Var4 = this.c;
        xy1 xy1Var5 = this.b;
        switch (i) {
            case 0:
                return new p0((Resources) xy1Var5.get(), (st1) xy1Var4.get(), (z91) xy1Var3.get(), (u6) xy1Var2.get(), (m) xy1Var.get());
            case 1:
                return new u5((Resources) xy1Var5.get(), (vv2) xy1Var4.get(), (ki1) xy1Var3.get(), (mh1) xy1Var2.get(), (w40) xy1Var.get(), 0);
            case 2:
                return new u5((Resources) xy1Var5.get(), (vv2) xy1Var4.get(), (ki1) xy1Var3.get(), (mh1) xy1Var2.get(), (w40) xy1Var.get(), 1);
            case 3:
                return new n12((Resources) xy1Var5.get(), (vv2) xy1Var4.get(), (ki1) xy1Var3.get(), (mh1) xy1Var2.get(), (w40) xy1Var.get());
            case 4:
                return new id2((ps1) xy1Var5.get(), (ki1) xy1Var4.get(), (mh1) xy1Var3.get(), (vj) xy1Var2.get(), (Resources) xy1Var.get());
            case 5:
                return new gz2((Resources) xy1Var5.get(), (vv2) xy1Var4.get(), (ki1) xy1Var3.get(), (mh1) xy1Var2.get(), (w40) xy1Var.get());
            case 6:
                return new hz2((Resources) xy1Var5.get(), (vv2) xy1Var4.get(), (ki1) xy1Var3.get(), (mh1) xy1Var2.get(), (w40) xy1Var.get());
            default:
                return new lz2((Resources) xy1Var5.get(), (ps1) xy1Var4.get(), (ki1) xy1Var3.get(), (mh1) xy1Var2.get(), (as2) xy1Var.get());
        }
    }
}
