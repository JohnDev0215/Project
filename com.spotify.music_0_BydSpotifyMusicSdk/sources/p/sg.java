package p;

/* JADX INFO: loaded from: classes.dex */
public final class sg implements yo1 {
    public static final sg a = new sg();
    public static final wt0 b;
    public static final wt0 c;

    static {
        ay2 ay2VarA = wt0.a("currentCacheSizeBytes");
        j6 j6VarC = j6.c();
        j6VarC.c = 1;
        ay2VarA.e(j6VarC.b());
        b = ay2VarA.c();
        ay2 ay2VarA2 = wt0.a("maxCacheSizeBytes");
        j6 j6VarC2 = j6.c();
        j6VarC2.c = 2;
        ay2VarA2.e(j6VarC2.b());
        c = ay2VarA2.c();
    }

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        fg2 fg2Var = (fg2) obj;
        zo1 zo1Var = (zo1) obj2;
        zo1Var.c(b, fg2Var.a);
        zo1Var.c(c, fg2Var.b);
    }
}
