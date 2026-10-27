package p;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class i23 implements r23, eq1, xp1, pp1 {
    public final /* synthetic */ int c;
    public final Executor e;
    public final Object k;
    public final Object l;

    public i23(Executor executor, ah2 ah2Var, b33 b33Var) {
        this.c = 3;
        this.e = executor;
        this.k = ah2Var;
        this.l = b33Var;
    }

    @Override // p.r23
    public final void a(aj2 aj2Var) {
        switch (this.c) {
            case 0:
                if (((b33) aj2Var).d) {
                    synchronized (this.k) {
                        try {
                            if (((pp1) this.l) != null) {
                                this.e.execute(new rv(12, this));
                            }
                        } finally {
                        }
                    }
                    return;
                }
                return;
            case 1:
                if (aj2Var.d() || ((b33) aj2Var).d) {
                    return;
                }
                synchronized (this.k) {
                    try {
                        if (((xp1) this.l) != null) {
                            this.e.execute(new yf1(this, 15, aj2Var));
                        }
                    } finally {
                    }
                }
                return;
            case 2:
                if (aj2Var.d()) {
                    synchronized (this.k) {
                        try {
                            if (((eq1) this.l) != null) {
                                this.e.execute(new yf1(this, 16, aj2Var));
                            }
                        } finally {
                        }
                    }
                    return;
                }
                return;
            default:
                this.e.execute(new yf1(this, 17, aj2Var));
                return;
        }
    }

    @Override // p.xp1
    public final void d(Exception exc) {
        ((b33) this.l).f(exc);
    }

    @Override // p.pp1
    public final void g() {
        ((b33) this.l).h();
    }

    @Override // p.eq1
    public final void onSuccess(Object obj) {
        ((b33) this.l).g(obj);
    }

    public i23(wa waVar, pp1 pp1Var) {
        this.c = 0;
        this.k = new Object();
        this.e = waVar;
        this.l = pp1Var;
    }

    public i23(wa waVar, xp1 xp1Var) {
        this.c = 1;
        this.k = new Object();
        this.e = waVar;
        this.l = xp1Var;
    }

    public i23(wa waVar, eq1 eq1Var) {
        this.c = 2;
        this.k = new Object();
        this.e = waVar;
        this.l = eq1Var;
    }
}
