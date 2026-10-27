package p;

/* JADX INFO: loaded from: classes.dex */
public final class lf implements yo1 {
    public static final lf a = new lf();
    public static final wt0 b = wt0.b("eventTimeMs");
    public static final wt0 c = wt0.b("eventCode");
    public static final wt0 d = wt0.b("eventUptimeMs");
    public static final wt0 e = wt0.b("sourceExtension");
    public static final wt0 f = wt0.b("sourceExtensionJsonProto3");
    public static final wt0 g = wt0.b("timezoneOffsetSeconds");
    public static final wt0 h = wt0.b("networkConnectionInfo");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        fi fiVar = (fi) ((kc1) obj);
        zo1Var.c(b, fiVar.a);
        zo1Var.f(c, fiVar.b);
        zo1Var.c(d, fiVar.c);
        zo1Var.f(e, fiVar.d);
        zo1Var.f(f, fiVar.e);
        zo1Var.c(g, fiVar.f);
        zo1Var.f(h, fiVar.g);
    }
}
