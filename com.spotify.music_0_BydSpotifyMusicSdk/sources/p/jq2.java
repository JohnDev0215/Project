package p;

import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class jq2 extends o72 {
    public final ThreadLocal m;
    private volatile boolean threadLocalIsSet;

    /* JADX WARN: Illegal instructions before constructor call */
    public jq2(c00 c00Var, y10 y10Var) {
        kq2 kq2Var = kq2.c;
        super(c00Var, y10Var.E0(kq2Var) == null ? y10Var.o0(kq2Var) : y10Var);
        this.m = new ThreadLocal();
        if (c00Var.getContext().E0(qo.l) instanceof kotlinx.coroutines.b) {
            return;
        }
        Object objC = kotlinx.coroutines.internal.c.c(y10Var, null);
        kotlinx.coroutines.internal.c.a(y10Var, objC);
        c0(y10Var, objC);
    }

    public final boolean b0() {
        boolean z = this.threadLocalIsSet && this.m.get() == null;
        this.m.remove();
        return !z;
    }

    public final void c0(y10 y10Var, Object obj) {
        this.threadLocalIsSet = true;
        this.m.set(new Pair(y10Var, obj));
    }

    @Override // p.o72, kotlinx.coroutines.g
    public final void s(Object obj) {
        if (this.threadLocalIsSet) {
            Pair pair = (Pair) this.m.get();
            if (pair != null) {
                kotlinx.coroutines.internal.c.a((y10) pair.a(), pair.b());
            }
            this.m.remove();
        }
        Object objL = he1.L(obj);
        c00 c00Var = this.l;
        y10 context = c00Var.getContext();
        Object objC = kotlinx.coroutines.internal.c.c(context, null);
        jq2 jq2VarE = objC != kotlinx.coroutines.internal.c.a ? kotlinx.coroutines.a.e(c00Var, context, objC) : null;
        try {
            this.l.k(objL);
        } finally {
            if (jq2VarE == null || jq2VarE.b0()) {
                kotlinx.coroutines.internal.c.a(context, objC);
            }
        }
    }
}
