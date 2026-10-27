package p;

import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
public final class s23 {
    public final Object a;
    public ArrayDeque b;
    public boolean c;

    public s23(int i) {
        if (i != 1) {
            this.a = new Object();
        } else {
            this.a = new Object();
        }
    }

    public final void a(i03 i03Var) {
        synchronized (this.a) {
            try {
                if (this.b == null) {
                    this.b = new ArrayDeque();
                }
                this.b.add(i03Var);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(r23 r23Var) {
        synchronized (this.a) {
            try {
                if (this.b == null) {
                    this.b = new ArrayDeque();
                }
                this.b.add(r23Var);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c(wc0 wc0Var) {
        h23 h23Var;
        synchronized (this.a) {
            if (this.b != null && !this.c) {
                this.c = true;
                while (true) {
                    synchronized (this.a) {
                        try {
                            h23Var = (h23) this.b.poll();
                            if (h23Var == null) {
                                this.c = false;
                                return;
                            }
                        } finally {
                        }
                    }
                    ((i03) h23Var).a(wc0Var);
                }
            }
        }
    }

    public final void d(aj2 aj2Var) {
        r23 r23Var;
        synchronized (this.a) {
            if (this.b != null && !this.c) {
                this.c = true;
                while (true) {
                    synchronized (this.a) {
                        try {
                            r23Var = (r23) this.b.poll();
                            if (r23Var == null) {
                                this.c = false;
                                return;
                            }
                        } finally {
                        }
                    }
                    r23Var.a(aj2Var);
                }
            }
        }
    }
}
