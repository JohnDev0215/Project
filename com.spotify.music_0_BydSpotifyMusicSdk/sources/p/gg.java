package p;

/* JADX INFO: loaded from: classes.dex */
public final class gg implements yo1 {
    public static final gg a = new gg();
    public static final wt0 b = wt0.b("batteryLevel");
    public static final wt0 c = wt0.b("batteryVelocity");
    public static final wt0 d = wt0.b("proximityOn");
    public static final wt0 e = wt0.b("orientation");
    public static final wt0 f = wt0.b("ramUsed");
    public static final wt0 g = wt0.b("diskUsed");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        vh vhVar = (vh) ((a40) obj);
        zo1Var.f(b, vhVar.a);
        zo1Var.a(c, vhVar.b);
        zo1Var.b(d, vhVar.c);
        zo1Var.a(e, vhVar.d);
        zo1Var.c(f, vhVar.e);
        zo1Var.c(g, vhVar.f);
    }
}
