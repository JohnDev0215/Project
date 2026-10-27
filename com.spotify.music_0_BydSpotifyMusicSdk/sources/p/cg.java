package p;

import com.spotify.connectivity.productstate.RxProductState;

/* JADX INFO: loaded from: classes.dex */
public final class cg implements yo1 {
    public static final cg a = new cg();
    public static final wt0 b = wt0.b(RxProductState.Keys.KEY_TYPE);
    public static final wt0 c = wt0.b("reason");
    public static final wt0 d = wt0.b("frames");
    public static final wt0 e = wt0.b("causedBy");
    public static final wt0 f = wt0.b("overflowCount");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        rh rhVar = (rh) ((u30) obj);
        zo1Var.f(b, rhVar.a);
        zo1Var.f(c, rhVar.b);
        zo1Var.f(d, rhVar.c);
        zo1Var.f(e, rhVar.d);
        zo1Var.a(f, rhVar.e);
    }
}
