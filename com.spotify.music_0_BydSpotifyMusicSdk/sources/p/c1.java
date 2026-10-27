package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class c1 implements x10 {
    public final ry0 c;
    public final x10 e;

    public c1(x10 x10Var, ry0 ry0Var) {
        v03.j(x10Var, "baseKey");
        v03.j(ry0Var, "safeCast");
        this.c = ry0Var;
        this.e = x10Var instanceof c1 ? ((c1) x10Var).e : x10Var;
    }
}
