package p;

/* JADX INFO: loaded from: classes.dex */
public final class j23 {
    public final wc0 a = new wc0(3);

    public final void a(Exception exc) {
        wc0 wc0Var = this.a;
        synchronized (wc0Var.b) {
            try {
                if (wc0Var.a) {
                    return;
                }
                wc0Var.a = true;
                wc0Var.e = exc;
                ((s23) wc0Var.c).c(wc0Var);
            } finally {
            }
        }
    }

    public final void b(Object obj) {
        wc0 wc0Var = this.a;
        synchronized (wc0Var.b) {
            try {
                if (wc0Var.a) {
                    return;
                }
                wc0Var.a = true;
                wc0Var.d = obj;
                ((s23) wc0Var.c).c(wc0Var);
            } finally {
            }
        }
    }
}
