package defpackage;

import java.util.HashMap;

/* JADX INFO: compiled from: DefaultTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public final class d80 implements y63 {
    public final HashMap a = new HashMap();

    @Override // defpackage.y63
    public final b73 a(d73 d73Var) {
        c73 c73Var = new c73(d73Var, "TaskRunnerImpl", 0);
        c73.d();
        return c73Var;
    }

    @Override // defpackage.y63
    public final oi2 b(d73 d73Var) {
        return new pi2(d73Var);
    }

    @Override // defpackage.y63
    public final synchronized void c(d73 d73Var, Runnable runnable, long j) {
        if (d73Var.d != 0) {
            ((c73) a(d73Var)).a(runnable, j);
        } else {
            b73 b73VarA = (b73) this.a.get(d73Var);
            if (b73VarA == null) {
                b73VarA = a(d73Var);
                this.a.put(d73Var, b73VarA);
            }
            b73VarA.a(runnable, j);
        }
    }

    @Override // defpackage.y63
    public final boolean d(d73 d73Var) {
        return false;
    }
}
