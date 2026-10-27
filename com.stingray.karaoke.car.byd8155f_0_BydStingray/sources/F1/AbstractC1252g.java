package f1;

import h5.y;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: f1.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1252g implements y {
    public static final boolean o = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Logger f14540p = Logger.getLogger(AbstractC1252g.class.getName());

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final com.bumptech.glide.c f14541q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Object f14542r;
    public volatile Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile C1248c f14543m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile C1251f f14544n;

    static {
        com.bumptech.glide.c c1250e;
        try {
            c1250e = new C1249d(AtomicReferenceFieldUpdater.newUpdater(C1251f.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(C1251f.class, C1251f.class, "b"), AtomicReferenceFieldUpdater.newUpdater(AbstractC1252g.class, C1251f.class, "n"), AtomicReferenceFieldUpdater.newUpdater(AbstractC1252g.class, C1248c.class, "m"), AtomicReferenceFieldUpdater.newUpdater(AbstractC1252g.class, Object.class, "l"));
            th = null;
        } catch (Throwable th) {
            th = th;
            c1250e = new C1250e();
        }
        f14541q = c1250e;
        if (th != null) {
            f14540p.log(Level.SEVERE, "SafeAtomicHelper is broken!", th);
        }
        f14542r = new Object();
    }

    public static void c(AbstractC1252g abstractC1252g) {
        C1251f c1251f;
        C1248c c1248c;
        C1248c c1248c2;
        C1248c c1248c3;
        do {
            c1251f = abstractC1252g.f14544n;
        } while (!f14541q.K(abstractC1252g, c1251f, C1251f.c));
        while (true) {
            c1248c = null;
            if (c1251f == null) {
                break;
            }
            Thread thread = c1251f.f14538a;
            if (thread != null) {
                c1251f.f14538a = null;
                LockSupport.unpark(thread);
            }
            c1251f = c1251f.f14539b;
        }
        do {
            c1248c2 = abstractC1252g.f14543m;
        } while (!f14541q.I(abstractC1252g, c1248c2, C1248c.d));
        while (true) {
            c1248c3 = c1248c;
            c1248c = c1248c2;
            if (c1248c == null) {
                break;
            }
            c1248c2 = c1248c.c;
            c1248c.c = c1248c3;
        }
        while (c1248c3 != null) {
            C1248c c1248c4 = c1248c3.c;
            d(c1248c3.f14532a, c1248c3.f14533b);
            c1248c3 = c1248c4;
        }
    }

    public static void d(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e10) {
            f14540p.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e10);
        }
    }

    public static Object e(Object obj) throws ExecutionException {
        if (obj instanceof C1246a) {
            CancellationException cancellationException = ((C1246a) obj).f14531a;
            CancellationException cancellationException2 = new CancellationException("Task was cancelled.");
            cancellationException2.initCause(cancellationException);
            throw cancellationException2;
        }
        if (obj instanceof AbstractC1247b) {
            ((AbstractC1247b) obj).getClass();
            throw new ExecutionException((Throwable) null);
        }
        if (obj == f14542r) {
            return null;
        }
        return obj;
    }

    public static Object f(AbstractC1252g abstractC1252g) {
        Object obj;
        boolean z6 = false;
        while (true) {
            try {
                obj = abstractC1252g.get();
                break;
            } catch (InterruptedException unused) {
                z6 = true;
            } catch (Throwable th) {
                if (z6) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z6) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public final void a(StringBuilder sb) {
        try {
            Object objF = f(this);
            sb.append("SUCCESS, result=[");
            sb.append(objF == this ? "this future" : String.valueOf(objF));
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (RuntimeException e10) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e10.getClass());
            sb.append(" thrown from get()]");
        } catch (ExecutionException e11) {
            sb.append("FAILURE, cause=[");
            sb.append(e11.getCause());
            sb.append("]");
        }
    }

    @Override // h5.y
    public final void b(Runnable runnable, Executor executor) {
        executor.getClass();
        C1248c c1248c = this.f14543m;
        C1248c c1248c2 = C1248c.d;
        if (c1248c != c1248c2) {
            C1248c c1248c3 = new C1248c(runnable, executor);
            do {
                c1248c3.c = c1248c;
                if (f14541q.I(this, c1248c, c1248c3)) {
                    return;
                } else {
                    c1248c = this.f14543m;
                }
            } while (c1248c != c1248c2);
        }
        d(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z6) {
        Object obj = this.l;
        if (obj != null) {
            return false;
        }
        if (!f14541q.J(this, obj, o ? new C1246a(z6, new CancellationException("Future.cancel() was called.")) : z6 ? C1246a.f14530b : C1246a.c)) {
            return false;
        }
        c(this);
        return true;
    }

    public final void g(C1251f c1251f) {
        c1251f.f14538a = null;
        while (true) {
            C1251f c1251f2 = this.f14544n;
            if (c1251f2 == C1251f.c) {
                return;
            }
            C1251f c1251f3 = null;
            while (c1251f2 != null) {
                C1251f c1251f4 = c1251f2.f14539b;
                if (c1251f2.f14538a != null) {
                    c1251f3 = c1251f2;
                } else if (c1251f3 != null) {
                    c1251f3.f14539b = c1251f4;
                    if (c1251f3.f14538a == null) {
                        break;
                    }
                } else if (!f14541q.K(this, c1251f2, c1251f4)) {
                    break;
                }
                c1251f2 = c1251f4;
            }
            return;
        }
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j3, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        long nanos = timeUnit.toNanos(j3);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object obj = this.l;
        if (obj != null) {
            return e(obj);
        }
        long jNanoTime = nanos > 0 ? System.nanoTime() + nanos : 0L;
        if (nanos >= 1000) {
            C1251f c1251f = this.f14544n;
            C1251f c1251f2 = C1251f.c;
            if (c1251f != c1251f2) {
                C1251f c1251f3 = new C1251f();
                do {
                    com.bumptech.glide.c cVar = f14541q;
                    cVar.w0(c1251f3, c1251f);
                    if (cVar.K(this, c1251f, c1251f3)) {
                        do {
                            LockSupport.parkNanos(this, nanos);
                            if (Thread.interrupted()) {
                                g(c1251f3);
                                throw new InterruptedException();
                            }
                            Object obj2 = this.l;
                            if (obj2 != null) {
                                return e(obj2);
                            }
                            nanos = jNanoTime - System.nanoTime();
                        } while (nanos >= 1000);
                        g(c1251f3);
                    } else {
                        c1251f = this.f14544n;
                    }
                } while (c1251f != c1251f2);
            }
            return e(this.l);
        }
        while (nanos > 0) {
            Object obj3 = this.l;
            if (obj3 != null) {
                return e(obj3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            nanos = jNanoTime - System.nanoTime();
        }
        String string = toString();
        String string2 = timeUnit.toString();
        Locale locale = Locale.ROOT;
        String lowerCase = string2.toLowerCase(locale);
        String strH = "Waited " + j3 + " " + timeUnit.toString().toLowerCase(locale);
        if (nanos + 1000 < 0) {
            String strH2 = android.hardware.bydauto.ac.tsimpl.a.h(strH, " (plus ");
            long j10 = -nanos;
            long jConvert = timeUnit.convert(j10, TimeUnit.NANOSECONDS);
            long nanos2 = j10 - timeUnit.toNanos(jConvert);
            boolean z6 = jConvert == 0 || nanos2 > 1000;
            if (jConvert > 0) {
                String strH3 = strH2 + jConvert + " " + lowerCase;
                if (z6) {
                    strH3 = android.hardware.bydauto.ac.tsimpl.a.h(strH3, ",");
                }
                strH2 = android.hardware.bydauto.ac.tsimpl.a.h(strH3, " ");
            }
            if (z6) {
                strH2 = strH2 + nanos2 + " nanoseconds ";
            }
            strH = android.hardware.bydauto.ac.tsimpl.a.h(strH2, "delay)");
        }
        if (isDone()) {
            throw new TimeoutException(android.hardware.bydauto.ac.tsimpl.a.h(strH, " but future completed as timeout expired"));
        }
        throw new TimeoutException(strH + " for " + string);
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.l instanceof C1246a;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.l != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.l instanceof C1246a) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            a(sb);
        } else {
            try {
                if (this instanceof ScheduledFuture) {
                    str = "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
                } else {
                    str = null;
                }
            } catch (RuntimeException e10) {
                str = "Exception thrown from implementation: " + e10.getClass();
            }
            if (str != null && !str.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(str);
                sb.append("]");
            } else if (isDone()) {
                a(sb);
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
            Object obj2 = this.l;
            if (obj2 != null) {
                return e(obj2);
            }
            C1251f c1251f = this.f14544n;
            C1251f c1251f2 = C1251f.c;
            if (c1251f != c1251f2) {
                C1251f c1251f3 = new C1251f();
                do {
                    com.bumptech.glide.c cVar = f14541q;
                    cVar.w0(c1251f3, c1251f);
                    if (cVar.K(this, c1251f, c1251f3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.l;
                            } else {
                                g(c1251f3);
                                throw new InterruptedException();
                            }
                        } while (obj == null);
                        return e(obj);
                    }
                    c1251f = this.f14544n;
                } while (c1251f != c1251f2);
            }
            return e(this.l);
        }
        throw new InterruptedException();
    }
}
