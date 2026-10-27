package p;

/* JADX INFO: loaded from: classes.dex */
public final class xf implements yo1 {
    public static final xf a = new xf();
    public static final wt0 b = wt0.b("arch");
    public static final wt0 c = wt0.b("model");
    public static final wt0 d = wt0.b("cores");
    public static final wt0 e = wt0.b("ram");
    public static final wt0 f = wt0.b("diskSpace");
    public static final wt0 g = wt0.b("simulator");
    public static final wt0 h = wt0.b("state");
    public static final wt0 i = wt0.b("manufacturer");
    public static final wt0 j = wt0.b("modelClass");

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        zo1 zo1Var = (zo1) obj2;
        mh mhVar = (mh) ((s30) obj);
        zo1Var.a(b, mhVar.a);
        zo1Var.f(c, mhVar.b);
        zo1Var.a(d, mhVar.c);
        zo1Var.c(e, mhVar.d);
        zo1Var.c(f, mhVar.e);
        zo1Var.b(g, mhVar.f);
        zo1Var.a(h, mhVar.g);
        zo1Var.f(i, mhVar.h);
        zo1Var.f(j, mhVar.i);
    }
}
