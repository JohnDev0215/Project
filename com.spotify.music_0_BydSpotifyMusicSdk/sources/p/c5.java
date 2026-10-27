package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class c5 {
    public static final w1 a;
    public static final w1 b;
    public static final w1 c;
    public static final w1 d;
    public static final so1 e;
    public static final so1 f;
    public static final so1 g;

    static {
        w1 w1Var = new w1(6);
        a = w1Var;
        w1 w1Var2 = new w1(4);
        b = w1Var2;
        w1 w1Var3 = new w1(2);
        w1 w1Var4 = new w1(1);
        c = w1Var4;
        w1 w1Var5 = new w1(0);
        d = w1Var5;
        e = a(w1Var);
        a(w1Var3);
        f = a(w1Var2);
        g = a(w1Var4);
        a(w1Var5);
    }

    public static final so1 a(y4 y4Var) {
        v03.j(y4Var, "<this>");
        return new so1(y4Var);
    }

    public static final wo1 b(y4 y4Var, boolean z) {
        return new wo1(y4Var, z);
    }
}
