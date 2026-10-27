package p;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class g2 implements lb1 {
    public static final boolean l = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Logger m = Logger.getLogger(g2.class.getName());
    public static final q30 n;
    public static final Object o;
    public volatile Object c;
    public volatile c2 e;
    public volatile f2 k;

    static {
        q30 e2Var;
        try {
            e2Var = new d2(AtomicReferenceFieldUpdater.newUpdater(f2.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(f2.class, f2.class, "b"), AtomicReferenceFieldUpdater.newUpdater(g2.class, f2.class, "k"), AtomicReferenceFieldUpdater.newUpdater(g2.class, c2.class, "e"), AtomicReferenceFieldUpdater.newUpdater(g2.class, Object.class, "c"));
            th = null;
        } catch (Throwable th) {
            th = th;
            e2Var = new e2();
        }
        n = e2Var;
        if (th != null) {
            m.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        o = new Object();
    }

    public static void d(g2 g2Var) {
        f2 f2Var;
        c2 c2Var;
        c2 c2Var2;
        c2 c2Var3;
        do {
            f2Var = g2Var.k;
        } while (!n.h(g2Var, f2Var, f2.c));
        while (true) {
            c2Var = null;
            if (f2Var == null) {
                break;
            }
            Thread thread = f2Var.a;
            if (thread != null) {
                f2Var.a = null;
                LockSupport.unpark(thread);
            }
            f2Var = f2Var.b;
        }
        g2Var.c();
        do {
            c2Var2 = g2Var.e;
        } while (!n.f(g2Var, c2Var2, c2.d));
        while (true) {
            c2Var3 = c2Var;
            c2Var = c2Var2;
            if (c2Var == null) {
                break;
            }
            c2Var2 = c2Var.c;
            c2Var.c = c2Var3;
        }
        while (c2Var3 != null) {
            c2 c2Var4 = c2Var3.c;
            e(c2Var3.a, c2Var3.b);
            c2Var3 = c2Var4;
        }
    }

    public static void e(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            m.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    public static Object f(Object obj) throws ExecutionException {
        if (obj instanceof b2) {
            Throwable th = ((b2) obj).b;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof androidx.concurrent.futures.a) {
            throw new ExecutionException(((androidx.concurrent.futures.a) obj).a);
        }
        if (obj == o) {
            return null;
        }
        return obj;
    }

    @Override // p.lb1
    public final void a(Runnable runnable, Executor executor) {
        executor.getClass();
        c2 c2Var = this.e;
        c2 c2Var2 = c2.d;
        if (c2Var != c2Var2) {
            c2 c2Var3 = new c2(runnable, executor);
            do {
                c2Var3.c = c2Var;
                if (n.f(this, c2Var, c2Var3)) {
                    return;
                } else {
                    c2Var = this.e;
                }
            } while (c2Var != c2Var2);
        }
        e(runnable, executor);
    }

    public final void b(StringBuilder sb) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                try {
                    obj = get();
                    break;
                } catch (CancellationException unused) {
                    sb.append("CANCELLED");
                    return;
                } catch (RuntimeException e) {
                    sb.append("UNKNOWN, cause=[");
                    sb.append(e.getClass());
                    sb.append(" thrown from get()]");
                    return;
                } catch (ExecutionException e2) {
                    sb.append("FAILURE, cause=[");
                    sb.append(e2.getCause());
                    sb.append("]");
                    return;
                }
            } catch (InterruptedException unused2) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        sb.append("SUCCESS, result=[");
        sb.append(obj == this ? "this future" : String.valueOf(obj));
        sb.append("]");
    }

    public void c() {
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object obj = this.c;
        if (obj == null) {
            if (n.g(this, obj, l ? new b2(new CancellationException("Future.cancel() was called."), z) : z ? b2.c : b2.d)) {
                d(this);
                return true;
            }
        }
        return false;
    }

    public final void g(f2 f2Var) {
        f2Var.a = null;
        while (true) {
            f2 f2Var2 = this.k;
            if (f2Var2 == f2.c) {
                return;
            }
            f2 f2Var3 = null;
            while (f2Var2 != null) {
                f2 f2Var4 = f2Var2.b;
                if (f2Var2.a != null) {
                    f2Var3 = f2Var2;
                } else if (f2Var3 != null) {
                    f2Var3.b = f2Var4;
                    if (f2Var3.a == null) {
                        break;
                    }
                } else if (!n.h(this, f2Var2, f2Var4)) {
                    break;
                }
                f2Var2 = f2Var4;
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ac  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x009f -> B:36:0x006e). Please report as a decompilation issue!!! */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get(long r20, java.util.concurrent.TimeUnit r22) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.g2.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.c instanceof b2;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (this.c != null) & true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.c instanceof b2) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            b(sb);
        } else {
            try {
                if (this instanceof ScheduledFuture) {
                    str = "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
                } else {
                    str = null;
                }
            } catch (RuntimeException e) {
                str = "Exception thrown from implementation: " + e.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                b(sb);
            } else {
                sb.append("PENDING");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.util.concurrent.Future
    public final Object get() throws InterruptedException {
        Object obj;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj2 = this.c;
        if ((obj2 != null) & true) {
            return f(obj2);
        }
        f2 f2Var = this.k;
        f2 f2Var2 = f2.c;
        if (f2Var != f2Var2) {
            f2 f2Var3 = new f2();
            do {
                q30 q30Var = n;
                q30Var.p(f2Var3, f2Var);
                if (q30Var.h(this, f2Var, f2Var3)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            g(f2Var3);
                            throw new InterruptedException();
                        }
                        obj = this.c;
                    } while (!((obj != null) & true));
                    return f(obj);
                }
                f2Var = this.k;
            } while (f2Var != f2Var2);
        }
        return f(this.c);
    }
}
