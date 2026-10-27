package p;

/* JADX INFO: loaded from: classes.dex */
public final class vf implements yo1 {
    public static final vf a = new vf();
    public static final wt0 b = wt0.b("identifier");
    public static final wt0 c = wt0.b("version");
    public static final wt0 d = wt0.b("displayVersion");
    public static final wt0 e = wt0.b("organization");
    public static final wt0 f = wt0.b("installationUuid");
    public static final wt0 g = wt0.b("developmentPlatform");
    public static final wt0 h = wt0.b("developmentPlatformVersion");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        kh khVar = (kh) ((r30) obj);
        zo1Var.f(b, khVar.a);
        zo1Var.f(c, khVar.b);
        zo1Var.f(d, khVar.c);
        zo1Var.f(e, null);
        zo1Var.f(f, khVar.d);
        zo1Var.f(g, khVar.e);
        zo1Var.f(h, khVar.f);
    }
}
