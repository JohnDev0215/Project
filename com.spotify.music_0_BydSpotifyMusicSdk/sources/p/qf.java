package p;

/* JADX INFO: loaded from: classes.dex */
public final class qf implements yo1 {
    public static final qf a = new qf();
    public static final wt0 b = wt0.b("pid");
    public static final wt0 c = wt0.b("processName");
    public static final wt0 d = wt0.b("reasonCode");
    public static final wt0 e = wt0.b("importance");
    public static final wt0 f = wt0.b("pss");
    public static final wt0 g = wt0.b("rss");
    public static final wt0 h = wt0.b("timestamp");
    public static final wt0 i = wt0.b("traceFile");
    public static final wt0 j = wt0.b("buildIdMappingForArch");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        dh dhVar = (dh) ((m30) obj);
        zo1Var.a(b, dhVar.a);
        zo1Var.f(c, dhVar.b);
        zo1Var.a(d, dhVar.c);
        zo1Var.a(e, dhVar.d);
        zo1Var.c(f, dhVar.e);
        zo1Var.c(g, dhVar.f);
        zo1Var.c(h, dhVar.g);
        zo1Var.f(i, dhVar.h);
        zo1Var.f(j, dhVar.i);
    }
}
