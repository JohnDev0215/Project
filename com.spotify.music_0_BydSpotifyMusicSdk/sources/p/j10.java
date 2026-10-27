package p;

import com.spotify.connectivity.connectiontype.ConnectivityManager;
import com.spotify.player.model.PlayOrigin;
import com.spotify.playerlimited.playerimpl.services.SessionEndpoint;

/* JADX INFO: loaded from: classes.dex */
public final class j10 implements ul0 {
    public final xy1 a;
    public final xy1 b;
    public final xy1 c;
    public final xy1 d;
    public final xy1 e;
    public final xy1 f;
    public final xy1 g;
    public final xy1 h;
    public final xy1 i;
    public final xy1 j;
    public final xy1 k;
    public final xy1 l;
    public final xy1 m;
    public final xy1 n;
    public final xy1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final xy1 f138p;
    public final xy1 q;

    public j10(wy1 wy1Var, wy1 wy1Var2, wy1 wy1Var3, wy1 wy1Var4, wy1 wy1Var5, wy1 wy1Var6, wy1 wy1Var7, wy1 wy1Var8, wy1 wy1Var9, wy1 wy1Var10, wy1 wy1Var11, wy1 wy1Var12, wy1 wy1Var13, wy1 wy1Var14, wy1 wy1Var15, wy1 wy1Var16, wy1 wy1Var17) {
        this.a = wy1Var;
        this.b = wy1Var2;
        this.c = wy1Var3;
        this.d = wy1Var4;
        this.e = wy1Var5;
        this.f = wy1Var6;
        this.g = wy1Var7;
        this.h = wy1Var8;
        this.i = wy1Var9;
        this.j = wy1Var10;
        this.k = wy1Var11;
        this.l = wy1Var12;
        this.m = wy1Var13;
        this.n = wy1Var14;
        this.o = wy1Var15;
        this.f138p = wy1Var16;
        this.q = wy1Var17;
    }

    @Override // p.xy1
    public final Object get() {
        return new i10((SessionEndpoint) this.a.get(), (p72) this.b.get(), (zh0) this.c.get(), (eu1) this.d.get(), (fu1) this.e.get(), (jh0) this.f.get(), (wh0) this.g.get(), (zw1) this.h.get(), (dg2) this.i.get(), (ConnectivityManager) this.j.get(), (da1) this.k.get(), (xx1) this.l.get(), (ut) this.m.get(), (u6) this.n.get(), (PlayOrigin) this.o.get(), (ih0) this.f138p.get(), (hh0) this.q.get());
    }
}
