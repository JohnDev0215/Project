package p;

/* JADX INFO: loaded from: classes.dex */
public final class yf implements yo1 {
    public static final yf a = new yf();
    public static final wt0 b = wt0.b("generator");
    public static final wt0 c = wt0.b("identifier");
    public static final wt0 d = wt0.b("startedAt");
    public static final wt0 e = wt0.b("endedAt");
    public static final wt0 f = wt0.b("crashed");
    public static final wt0 g = wt0.b("app");
    public static final wt0 h = wt0.b("user");
    public static final wt0 i = wt0.b("os");
    public static final wt0 j = wt0.b("device");
    public static final wt0 k = wt0.b("events");
    public static final wt0 l = wt0.b("generatorType");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        jh jhVar = (jh) ((f40) obj);
        zo1Var.f(b, jhVar.a);
        zo1Var.f(c, jhVar.b.getBytes(g40.a));
        zo1Var.c(d, jhVar.c);
        zo1Var.f(e, jhVar.d);
        zo1Var.b(f, jhVar.e);
        zo1Var.f(g, jhVar.f);
        zo1Var.f(h, jhVar.g);
        zo1Var.f(i, jhVar.h);
        zo1Var.f(j, jhVar.i);
        zo1Var.f(k, jhVar.j);
        zo1Var.a(l, jhVar.k);
    }
}
