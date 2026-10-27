package p;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class m13 implements r23, eq1, xp1, pp1 {
    public final /* synthetic */ int c;
    public final Executor e;
    public final d00 k;
    public final b33 l;

    public /* synthetic */ m13(Executor executor, d00 d00Var, b33 b33Var, int i) {
        this.c = i;
        this.e = executor;
        this.k = d00Var;
        this.l = b33Var;
    }

    @Override // p.r23
    public final void a(aj2 aj2Var) {
        int i = this.c;
        Executor executor = this.e;
        switch (i) {
            case 0:
                executor.execute(new yf1(this, 13, aj2Var));
                break;
            default:
                executor.execute(new yf1(this, 14, aj2Var));
                break;
        }
    }

    @Override // p.xp1
    public final void d(Exception exc) {
        this.l.f(exc);
    }

    @Override // p.pp1
    public final void g() {
        this.l.h();
    }

    @Override // p.eq1
    public final void onSuccess(Object obj) {
        this.l.g(obj);
    }
}
