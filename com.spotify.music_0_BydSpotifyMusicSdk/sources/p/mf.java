package p;

/* JADX INFO: loaded from: classes.dex */
public final class mf implements yo1 {
    public static final mf a = new mf();
    public static final wt0 b = wt0.b("requestTimeMs");
    public static final wt0 c = wt0.b("requestUptimeMs");
    public static final wt0 d = wt0.b("clientInfo");
    public static final wt0 e = wt0.b("logSource");
    public static final wt0 f = wt0.b("logSourceName");
    public static final wt0 g = wt0.b("logEvent");
    public static final wt0 h = wt0.b("qosTier");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        gi giVar = (gi) ((oc1) obj);
        zo1Var.c(b, giVar.a);
        zo1Var.c(c, giVar.b);
        zo1Var.f(d, giVar.c);
        zo1Var.f(e, giVar.d);
        zo1Var.f(f, giVar.e);
        zo1Var.f(g, giVar.f);
        zo1Var.f(h, giVar.g);
    }
}
