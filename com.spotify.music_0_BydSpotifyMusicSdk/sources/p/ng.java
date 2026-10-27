package p;

/* JADX INFO: loaded from: classes.dex */
public final class ng implements yo1 {
    public static final ng a = new ng();
    public static final wt0 b;
    public static final wt0 c;
    public static final wt0 d;
    public static final wt0 e;

    static {
        ay2 ay2VarA = wt0.a("window");
        j6 j6VarC = j6.c();
        j6VarC.c = 1;
        ay2VarA.e(j6VarC.b());
        b = ay2VarA.c();
        ay2 ay2VarA2 = wt0.a("logSourceMetrics");
        j6 j6VarC2 = j6.c();
        j6VarC2.c = 2;
        ay2VarA2.e(j6VarC2.b());
        c = ay2VarA2.c();
        ay2 ay2VarA3 = wt0.a("globalMetrics");
        j6 j6VarC3 = j6.c();
        j6VarC3.c = 3;
        ay2VarA3.e(j6VarC3.b());
        d = ay2VarA3.c();
        ay2 ay2VarA4 = wt0.a("appNamespace");
        j6 j6VarC4 = j6.c();
        j6VarC4.c = 4;
        ay2VarA4.e(j6VarC4.b());
        e = ay2VarA4.c();
    }

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        wt wtVar = (wt) obj;
        zo1 zo1Var = (zo1) obj2;
        zo1Var.f(b, wtVar.a);
        zo1Var.f(c, wtVar.b);
        zo1Var.f(d, wtVar.c);
        zo1Var.f(e, wtVar.d);
    }
}
