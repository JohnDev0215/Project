package p;

/* JADX INFO: loaded from: classes.dex */
public final class mc1 {
    public static final z90 c = new z90((Object) null);
    public final gu0 a;
    public fu0 b;

    public mc1(gu0 gu0Var) {
        this.a = gu0Var;
        this.b = c;
    }

    public mc1(gu0 gu0Var, String str) {
        this(gu0Var);
        z90 z90Var = c;
        z90Var.b();
        this.b = z90Var;
        if (str == null) {
            return;
        }
        this.b = new oz1(gu0Var.l(str, "userlog"));
    }
}
