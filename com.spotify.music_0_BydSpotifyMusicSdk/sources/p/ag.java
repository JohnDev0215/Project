package p;

/* JADX INFO: loaded from: classes.dex */
public final class ag implements yo1 {
    public static final ag a = new ag();
    public static final wt0 b = wt0.b("baseAddress");
    public static final wt0 c = wt0.b("size");
    public static final wt0 d = wt0.b("name");
    public static final wt0 e = wt0.b("uuid");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        qh qhVar = (qh) ((t30) obj);
        zo1Var.c(b, qhVar.a);
        zo1Var.c(c, qhVar.b);
        zo1Var.f(d, qhVar.c);
        String str = qhVar.d;
        zo1Var.f(e, str != null ? str.getBytes(g40.a) : null);
    }
}
