package G8;

import F8.AbstractC0268c;
import F8.C0278m;
import F8.I;
import F8.a0;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public static final p l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final n f2739m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final q f2740n;
    public static final o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ r[] f2741p;

    static {
        p pVar = new p();
        l = pVar;
        n nVar = new n();
        f2739m = nVar;
        q qVar = new q();
        f2740n = qVar;
        o oVar = new o();
        o = oVar;
        f2741p = new r[]{pVar, nVar, qVar, oVar};
    }

    public static r b(a0 a0Var) {
        A7.m.f(a0Var, "<this>");
        if (a0Var.F0()) {
            return f2739m;
        }
        if (a0Var instanceof C0278m) {
        }
        return AbstractC0268c.f(g.l(false, true, e.f2723m, null, null, 24), AbstractC0268c.k(a0Var), I.f2439b) ? o : f2740n;
    }

    public static r valueOf(String str) {
        return (r) Enum.valueOf(r.class, str);
    }

    public static r[] values() {
        return (r[]) f2741p.clone();
    }

    public abstract r a(a0 a0Var);
}
