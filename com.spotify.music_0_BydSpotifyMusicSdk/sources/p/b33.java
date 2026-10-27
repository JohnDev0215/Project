package p;

import com.google.android.gms.tasks.DuplicateTaskCompletionException;
import com.google.android.gms.tasks.RuntimeExecutionException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class b33 extends aj2 {
    public final Object a = new Object();
    public final s23 b = new s23(0);
    public boolean c;
    public volatile boolean d;
    public Object e;
    public Exception f;

    @Override // p.aj2
    public final b33 a(Executor executor, d00 d00Var) {
        b33 b33Var = new b33();
        this.b.b(new m13(executor, d00Var, b33Var, 0));
        j();
        return b33Var;
    }

    @Override // p.aj2
    public final Exception b() {
        Exception exc;
        synchronized (this.a) {
            exc = this.f;
        }
        return exc;
    }

    @Override // p.aj2
    public final Object c() {
        Object obj;
        synchronized (this.a) {
            try {
                if (!this.c) {
                    throw new IllegalStateException("Task is not yet complete");
                }
                if (this.d) {
                    throw new CancellationException("Task is already canceled.");
                }
                Exception exc = this.f;
                if (exc != null) {
                    throw new RuntimeExecutionException(exc);
                }
                obj = this.e;
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    @Override // p.aj2
    public final boolean d() {
        boolean z;
        synchronized (this.a) {
            try {
                z = false;
                if (this.c && !this.d && this.f == null) {
                    z = true;
                }
            } finally {
            }
        }
        return z;
    }

    @Override // p.aj2
    public final b33 e(Executor executor, ah2 ah2Var) {
        b33 b33Var = new b33();
        this.b.b(new i23(executor, ah2Var, b33Var));
        j();
        return b33Var;
    }

    public final void f(Exception exc) {
        if (exc == null) {
            throw new NullPointerException("Exception must not be null");
        }
        synchronized (this.a) {
            i();
            this.c = true;
            this.f = exc;
        }
        this.b.d(this);
    }

    public final void g(Object obj) {
        synchronized (this.a) {
            i();
            this.c = true;
            this.e = obj;
        }
        this.b.d(this);
    }

    public final void h() {
        synchronized (this.a) {
            try {
                if (this.c) {
                    return;
                }
                this.c = true;
                this.d = true;
                this.b.d(this);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void i() {
        boolean z;
        if (this.c) {
            int i = DuplicateTaskCompletionException.c;
            synchronized (this.a) {
                z = this.c;
            }
            if (!z) {
                throw new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
            }
            Exception excB = b();
        }
    }

    public final void j() {
        synchronized (this.a) {
            try {
                if (this.c) {
                    this.b.d(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
