package p;

import android.content.Context;
import com.spotify.connectivity.connectiontype.ConnectionApis;
import com.spotify.player.model.PlayOrigin;

/* JADX INFO: loaded from: classes.dex */
public final class id1 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;
    public final xy1 f;
    public final xy1 g;
    public final xy1 h;
    public final xy1 i;

    public /* synthetic */ id1(ul0 ul0Var, wy1 wy1Var, wy1 wy1Var2, wy1 wy1Var3, wy1 wy1Var4, wy1 wy1Var5, wy1 wy1Var6, wy1 wy1Var7, int i) {
        this.a = i;
        this.b = ul0Var;
        this.c = wy1Var;
        this.d = wy1Var2;
        this.e = wy1Var3;
        this.f = wy1Var4;
        this.g = wy1Var5;
        this.h = wy1Var6;
        this.i = wy1Var7;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.i;
        xy1 xy1Var2 = this.h;
        xy1 xy1Var3 = this.g;
        xy1 xy1Var4 = this.f;
        xy1 xy1Var5 = this.e;
        xy1 xy1Var6 = this.d;
        xy1 xy1Var7 = this.c;
        xy1 xy1Var8 = this.b;
        switch (i) {
            case 0:
                return new com.spotify.automotive.auth.b((Context) xy1Var8.get(), (ut) xy1Var7.get(), (vb2) xy1Var6.get(), (qm) xy1Var5.get(), (td1) xy1Var4.get(), (j) xy1Var3.get(), (sk2) xy1Var2.get(), (ConnectionApis) xy1Var.get());
            case 1:
                return new com.spotify.automotive.playback.c((zh0) xy1Var8.get(), (m) xy1Var7.get(), (vx1) xy1Var6.get(), (PlayOrigin) xy1Var5.get(), (u6) xy1Var4.get(), (uj) xy1Var3.get(), (qx) xy1Var2.get(), (e20) xy1Var.get());
            default:
                return new com.spotify.automotive.playerapi.c((jh0) xy1Var8.get(), (zh0) xy1Var7.get(), (wh0) xy1Var6.get(), (eu1) xy1Var5.get(), (fu1) xy1Var4.get(), (ut) xy1Var3.get(), (fh0) xy1Var2.get(), (u6) xy1Var.get());
        }
    }
}
