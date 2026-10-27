package p;

import com.spotify.connectivity.productstate.RxProductState;

/* JADX INFO: loaded from: classes.dex */
public final class hg implements yo1 {
    public static final hg a = new hg();
    public static final wt0 b = wt0.b("timestamp");
    public static final wt0 c = wt0.b(RxProductState.Keys.KEY_TYPE);
    public static final wt0 d = wt0.b("app");
    public static final wt0 e = wt0.b("device");
    public static final wt0 f = wt0.b("log");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        nh nhVar = (nh) ((c40) obj);
        zo1Var.c(b, nhVar.a);
        zo1Var.f(c, nhVar.b);
        zo1Var.f(d, nhVar.c);
        zo1Var.f(e, nhVar.d);
        zo1Var.f(f, nhVar.e);
    }
}
