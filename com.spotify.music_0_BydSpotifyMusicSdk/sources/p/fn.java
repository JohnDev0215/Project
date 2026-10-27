package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class fn implements in {
    public static final List h = v03.B("com.spotify.music");
    public final nt a;
    public final e52 b;
    public final a42 c;
    public final u6 d;
    public final zm e;
    public final sk2 f;
    public long g;

    public fn(nt ntVar, e52 e52Var, a42 a42Var, u6 u6Var, zm zmVar, sk2 sk2Var) {
        v03.j(ntVar, "data");
        v03.j(e52Var, "retrofit");
        v03.j(a42Var, "bootstrapInjector");
        v03.j(u6Var, "clock");
        v03.j(zmVar, "eventLogger");
        v03.j(sk2Var, "timeKeeper");
        this.a = ntVar;
        this.b = e52Var;
        this.c = a42Var;
        this.d = u6Var;
        this.e = zmVar;
        this.f = sk2Var;
    }
}
