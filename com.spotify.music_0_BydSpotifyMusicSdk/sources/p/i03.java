package p;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class i03 implements h23 {
    public final Executor b;
    public final Object d;
    public final /* synthetic */ int a = 2;
    public final Object c = new Object();

    public i03(Executor executor, wp1 wp1Var) {
        this.b = executor;
        this.d = wp1Var;
    }

    public final void a(wc0 wc0Var) {
        switch (this.a) {
            case 0:
                synchronized (this.c) {
                    try {
                        if (((ay2) this.d) == null) {
                            return;
                        }
                        this.b.execute(new yf1(this, 28, wc0Var));
                        return;
                    } finally {
                    }
                }
            case 1:
                if (wc0Var.e()) {
                    return;
                }
                synchronized (this.c) {
                    try {
                        if (((wp1) this.d) != null) {
                            this.b.execute(new yf1(this, 29, wc0Var));
                        }
                    } finally {
                    }
                }
                return;
            default:
                if (wc0Var.e()) {
                    synchronized (this.c) {
                        try {
                            if (((dq1) this.d) != null) {
                                this.b.execute(new z13(this, 0, wc0Var));
                            }
                        } finally {
                        }
                    }
                    return;
                }
                return;
        }
    }

    public i03(Executor executor, dq1 dq1Var) {
        this.b = executor;
        this.d = dq1Var;
    }

    public i03(y23 y23Var, ay2 ay2Var) {
        this.b = y23Var;
        this.d = ay2Var;
    }
}
