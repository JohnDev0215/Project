package p;

/* JADX INFO: loaded from: classes.dex */
public final class pg implements yo1 {
    public static final pg a = new pg();
    public static final wt0 b;
    public static final wt0 c;

    static {
        ay2 ay2VarA = wt0.a("eventsDroppedCount");
        j6 j6VarC = j6.c();
        j6VarC.c = 1;
        ay2VarA.e(j6VarC.b());
        b = ay2VarA.c();
        ay2 ay2VarA2 = wt0.a("reason");
        j6 j6VarC2 = j6.c();
        j6VarC2.c = 3;
        ay2VarA2.e(j6VarC2.b());
        c = ay2VarA2.c();
    }

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        lc1 lc1Var = (lc1) obj;
        zo1 zo1Var = (zo1) obj2;
        zo1Var.c(b, lc1Var.a);
        zo1Var.f(c, lc1Var.b);
    }
}
