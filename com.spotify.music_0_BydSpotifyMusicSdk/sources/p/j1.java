package p;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public abstract class j1 implements lb1 {
    public static final boolean l = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final Logger m = Logger.getLogger(j1.class.getName());
    public static final zu n;
    public static final Object o;
    public volatile Object c;
    public volatile e1 e;
    public volatile i1 k;

    static {
        zu h1Var;
        try {
            h1Var = new f1(AtomicReferenceFieldUpdater.newUpdater(i1.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(i1.class, i1.class, "b"), AtomicReferenceFieldUpdater.newUpdater(j1.class, i1.class, "k"), AtomicReferenceFieldUpdater.newUpdater(j1.class, e1.class, "e"), AtomicReferenceFieldUpdater.newUpdater(j1.class, Object.class, "c"));
            th = null;
        } catch (Throwable th) {
            th = th;
            h1Var = new h1();
        }
        n = h1Var;
        if (th != null) {
            m.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        o = new Object();
    }

    public static void c(j1 j1Var) {
        e1 e1Var;
        e1 e1Var2;
        e1 e1Var3 = null;
        while (true) {
            i1 i1Var = j1Var.k;
            if (n.o(j1Var, i1Var, i1.c)) {
                while (i1Var != null) {
                    Thread thread = i1Var.a;
                    if (thread != null) {
                        i1Var.a = null;
                        LockSupport.unpark(thread);
                    }
                    i1Var = i1Var.b;
                }
                do {
                    e1Var = j1Var.e;
                } while (!n.m(j1Var, e1Var, e1.d));
                while (true) {
                    e1Var2 = e1Var3;
                    e1Var3 = e1Var;
                    if (e1Var3 == null) {
                        break;
                    }
                    e1Var = e1Var3.c;
                    e1Var3.c = e1Var2;
                }
                while (e1Var2 != null) {
                    e1Var3 = e1Var2.c;
                    Runnable runnable = e1Var2.a;
                    if (runnable instanceof g1) {
                        g1 g1Var = (g1) runnable;
                        j1Var = g1Var.c;
                        if (j1Var.c == g1Var) {
                            if (n.n(j1Var, g1Var, f(g1Var.e))) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        d(runnable, e1Var2.b);
                    }
                    e1Var2 = e1Var3;
                }
                return;
            }
        }
    }

    public static void d(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            m.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    public static Object e(Object obj) throws ExecutionException {
        if (obj instanceof d1) {
            Throwable th = ((d1) obj).b;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof androidx.work.impl.utils.futures.a) {
            throw new ExecutionException(((androidx.work.impl.utils.futures.a) obj).a);
        }
        if (obj == o) {
            return null;
        }
        return obj;
    }

    public static Object f(lb1 lb1Var) {
        if (lb1Var instanceof j1) {
            Object obj = ((j1) lb1Var).c;
            if (!(obj instanceof d1)) {
                return obj;
            }
            d1 d1Var = (d1) obj;
            return d1Var.a ? d1Var.b != null ? new d1(d1Var.b, false) : d1.d : obj;
        }
        boolean zIsCancelled = lb1Var.isCancelled();
        if ((!l) && zIsCancelled) {
            return d1.d;
        }
        try {
            Object objG = g(lb1Var);
            return objG == null ? o : objG;
        } catch (CancellationException e) {
            if (zIsCancelled) {
                return new d1(e, false);
            }
            return new androidx.work.impl.utils.futures.a(new IllegalArgumentException("get() threw CancellationException, despite reporting isCancelled() == false: " + lb1Var, e));
        } catch (ExecutionException e2) {
            return new androidx.work.impl.utils.futures.a(e2.getCause());
        } catch (Throwable th) {
            return new androidx.work.impl.utils.futures.a(th);
        }
    }

    public static Object g(Future future) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
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
        return obj;
    }

    @Override // p.lb1
    public final void a(Runnable runnable, Executor executor) {
        runnable.getClass();
        executor.getClass();
        e1 e1Var = this.e;
        e1 e1Var2 = e1.d;
        if (e1Var != e1Var2) {
            e1 e1Var3 = new e1(runnable, executor);
            do {
                e1Var3.c = e1Var;
                if (n.m(this, e1Var, e1Var3)) {
                    return;
                } else {
                    e1Var = this.e;
                }
            } while (e1Var != e1Var2);
        }
        d(runnable, executor);
    }

    public final void b(StringBuilder sb) {
        try {
            Object objG = g(this);
            sb.append("SUCCESS, result=[");
            sb.append(objG == this ? "this future" : String.valueOf(objG));
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e2) {
            sb.append("FAILURE, cause=[");
            sb.append(e2.getCause());
            sb.append("]");
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object obj = this.c;
        if (!(obj == null) && !(obj instanceof g1)) {
            return false;
        }
        d1 d1Var = l ? new d1(new CancellationException("Future.cancel() was called."), z) : z ? d1.c : d1.d;
        j1 j1Var = this;
        boolean z2 = false;
        while (true) {
            if (n.n(j1Var, obj, d1Var)) {
                c(j1Var);
                if (!(obj instanceof g1)) {
                    return true;
                }
                lb1 lb1Var = ((g1) obj).e;
                if (!(lb1Var instanceof j1)) {
                    lb1Var.cancel(z);
                    return true;
                }
                j1Var = (j1) lb1Var;
                obj = j1Var.c;
                if (!(obj == null) && !(obj instanceof g1)) {
                    return true;
                }
                z2 = true;
            } else {
                obj = j1Var.c;
                if (!(obj instanceof g1)) {
                    return z2;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00a8 -> B:36:0x0074). Please report as a decompilation issue!!! */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get(long r20, java.util.concurrent.TimeUnit r22) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            Method dump skipped, instruction units count: 379
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.j1.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String h() {
        Object obj = this.c;
        if (obj instanceof g1) {
            StringBuilder sb = new StringBuilder("setFuture=[");
            lb1 lb1Var = ((g1) obj).e;
            return u4.m(sb, lb1Var == this ? "this future" : String.valueOf(lb1Var), "]");
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public final void i(i1 i1Var) {
        i1Var.a = null;
        while (true) {
            i1 i1Var2 = this.k;
            if (i1Var2 == i1.c) {
                return;
            }
            i1 i1Var3 = null;
            while (i1Var2 != null) {
                i1 i1Var4 = i1Var2.b;
                if (i1Var2.a != null) {
                    i1Var3 = i1Var2;
                } else if (i1Var3 != null) {
                    i1Var3.b = i1Var4;
                    if (i1Var3.a == null) {
                        break;
                    }
                } else if (!n.o(this, i1Var2, i1Var4)) {
                    break;
                }
                i1Var2 = i1Var4;
            }
            return;
        }
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.c instanceof d1;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (!(r0 instanceof g1)) & (this.c != null);
    }

    public final String toString() {
        String strH;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.c instanceof d1) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            b(sb);
        } else {
            try {
                strH = h();
            } catch (RuntimeException e) {
                strH = "Exception thrown from implementation: " + e.getClass();
            }
            if (strH != null && !strH.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(strH);
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
        if (!Thread.interrupted()) {
            Object obj2 = this.c;
            if ((obj2 != null) & (!(obj2 instanceof g1))) {
                return e(obj2);
            }
            i1 i1Var = this.k;
            i1 i1Var2 = i1.c;
            if (i1Var != i1Var2) {
                i1 i1Var3 = new i1();
                do {
                    zu zuVar = n;
                    zuVar.A0(i1Var3, i1Var);
                    if (zuVar.o(this, i1Var, i1Var3)) {
                        do {
                            LockSupport.park(this);
                            if (Thread.interrupted()) {
                                i(i1Var3);
                                throw new InterruptedException();
                            }
                            obj = this.c;
                        } while (!((obj != null) & (!(obj instanceof g1))));
                        return e(obj);
                    }
                    i1Var = this.k;
                } while (i1Var != i1Var2);
            }
            return e(this.c);
        }
        throw new InterruptedException();
    }
}
