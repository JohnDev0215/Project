package p;

import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes.dex */
public final class sz2 implements eq1, xp1, pp1 {
    public final Object c = new Object();
    public final int e;
    public final b33 k;
    public int l;
    public int m;
    public int n;
    public Exception o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f184p;

    public sz2(int i, b33 b33Var) {
        this.e = i;
        this.k = b33Var;
    }

    public final void a() {
        int i = this.l + this.m + this.n;
        int i2 = this.e;
        if (i == i2) {
            Exception exc = this.o;
            b33 b33Var = this.k;
            if (exc == null) {
                if (this.f184p) {
                    b33Var.h();
                    return;
                } else {
                    b33Var.g(null);
                    return;
                }
            }
            b33Var.f(new ExecutionException(this.m + " out of " + i2 + " underlying tasks failed", this.o));
        }
    }

    @Override // p.xp1
    public final void d(Exception exc) {
        synchronized (this.c) {
            this.m++;
            this.o = exc;
            a();
        }
    }

    @Override // p.pp1
    public final void g() {
        synchronized (this.c) {
            this.n++;
            this.f184p = true;
            a();
        }
    }

    @Override // p.eq1
    public final void onSuccess(Object obj) {
        synchronized (this.c) {
            this.l++;
            a();
        }
    }
}
