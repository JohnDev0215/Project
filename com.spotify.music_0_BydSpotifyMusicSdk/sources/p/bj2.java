package p;

/* JADX INFO: loaded from: classes.dex */
public final class bj2 {
    public final b33 a = new b33();

    public final void a(Exception exc) {
        b33 b33Var = this.a;
        b33Var.getClass();
        if (exc == null) {
            throw new NullPointerException("Exception must not be null");
        }
        synchronized (b33Var.a) {
            try {
                if (b33Var.c) {
                    return;
                }
                b33Var.c = true;
                b33Var.f = exc;
                b33Var.b.d(b33Var);
            } finally {
            }
        }
    }

    public final void b(Object obj) {
        b33 b33Var = this.a;
        synchronized (b33Var.a) {
            try {
                if (b33Var.c) {
                    return;
                }
                b33Var.c = true;
                b33Var.e = obj;
                b33Var.b.d(b33Var);
            } finally {
            }
        }
    }
}
