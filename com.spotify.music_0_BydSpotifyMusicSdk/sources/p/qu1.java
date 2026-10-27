package p;

import android.content.res.Resources;
import com.spotify.player.model.PlayOrigin;

/* JADX INFO: loaded from: classes.dex */
public final class qu1 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;
    public final xy1 f;
    public final xy1 g;

    public /* synthetic */ qu1(wy1 wy1Var, wy1 wy1Var2, wy1 wy1Var3, wy1 wy1Var4, wy1 wy1Var5, wy1 wy1Var6, int i) {
        this.a = i;
        this.b = wy1Var;
        this.c = wy1Var2;
        this.d = wy1Var3;
        this.e = wy1Var4;
        this.f = wy1Var5;
        this.g = wy1Var6;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.g;
        xy1 xy1Var2 = this.f;
        xy1 xy1Var3 = this.e;
        xy1 xy1Var4 = this.d;
        xy1 xy1Var5 = this.c;
        xy1 xy1Var6 = this.b;
        switch (i) {
            case 0:
                return new pu1((Resources) xy1Var6.get(), (vv2) xy1Var5.get(), (ki1) xy1Var4.get(), (vj) xy1Var3.get(), (mh1) xy1Var2.get(), (w40) xy1Var.get());
            case 1:
                return new h82((Resources) xy1Var6.get(), (ps1) xy1Var5.get(), (ki1) xy1Var4.get(), (mh1) xy1Var3.get(), (m) xy1Var2.get(), (w40) xy1Var.get());
            case 2:
                return new kz2((Resources) xy1Var6.get(), (ps1) xy1Var5.get(), (ki1) xy1Var4.get(), (as2) xy1Var3.get(), (mh1) xy1Var2.get(), (w40) xy1Var.get());
            case 3:
                return new ia1((x00) xy1Var6.get(), (zh0) xy1Var5.get(), (m) xy1Var4.get(), (vx1) xy1Var3.get(), (PlayOrigin) xy1Var2.get(), (u6) xy1Var.get());
            default:
                return new fn((nt) xy1Var6.get(), (e52) xy1Var5.get(), (a42) xy1Var4.get(), (u6) xy1Var3.get(), (zm) xy1Var2.get(), (sk2) xy1Var.get());
        }
    }
}
