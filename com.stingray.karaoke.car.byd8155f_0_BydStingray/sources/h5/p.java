package h5;

import i5.AbstractC1411a;
import java.util.Objects;
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
public abstract class p extends AbstractC1411a implements y {
    public static final boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final x f15556p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final l9.d f15557q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Object f15558r;
    public volatile Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile C1378d f15559m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile o f15560n;

    static {
        boolean z6;
        l9.d c1381g;
        try {
            z6 = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
        } catch (SecurityException unused) {
            z6 = false;
        }
        o = z6;
        f15556p = new x(p.class);
        Throwable th = null;
        try {
            c1381g = new n();
            e = null;
        } catch (Error | Exception e10) {
            e = e10;
            try {
                c1381g = new C1379e(AtomicReferenceFieldUpdater.newUpdater(o.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(o.class, o.class, "b"), AtomicReferenceFieldUpdater.newUpdater(p.class, o.class, "n"), AtomicReferenceFieldUpdater.newUpdater(p.class, C1378d.class, "m"), AtomicReferenceFieldUpdater.newUpdater(p.class, Object.class, "l"));
            } catch (Error | Exception e11) {
                th = e11;
                c1381g = new C1381g();
            }
        }
        f15557q = c1381g;
        if (th != null) {
            x xVar = f15556p;
            Logger loggerA = xVar.a();
            Level level = Level.SEVERE;
            loggerA.log(level, "UnsafeAtomicHelper is broken!", e);
            xVar.a().log(level, "SafeAtomicHelper is broken!", th);
        }
        f15558r = new Object();
    }

    public static void e(p pVar, boolean z6) {
        C1378d c1378d = null;
        while (true) {
            pVar.getClass();
            for (o oVarT = f15557q.T(pVar); oVarT != null; oVarT = oVarT.f15555b) {
                Thread thread = oVarT.f15554a;
                if (thread != null) {
                    oVarT.f15554a = null;
                    LockSupport.unpark(thread);
                }
            }
            if (z6) {
                z6 = false;
            }
            pVar.c();
            C1378d c1378d2 = c1378d;
            C1378d c1378dS = f15557q.S(pVar);
            C1378d c1378d3 = c1378d2;
            while (c1378dS != null) {
                C1378d c1378d4 = c1378dS.c;
                c1378dS.c = c1378d3;
                c1378d3 = c1378dS;
                c1378dS = c1378d4;
            }
            while (c1378d3 != null) {
                c1378d = c1378d3.c;
                Runnable runnable = c1378d3.f15544a;
                Objects.requireNonNull(runnable);
                if (runnable instanceof RunnableC1380f) {
                    RunnableC1380f runnableC1380f = (RunnableC1380f) runnable;
                    pVar = runnableC1380f.l;
                    if (pVar.l == runnableC1380f) {
                        if (f15557q.F(pVar, runnableC1380f, h(runnableC1380f.f15549m))) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    Executor executor = c1378d3.f15545b;
                    Objects.requireNonNull(executor);
                    f(runnable, executor);
                }
                c1378d3 = c1378d;
            }
            return;
        }
    }

    public static void f(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Exception e10) {
            f15556p.a().log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e10);
        }
    }

    public static Object g(Object obj) throws ExecutionException {
        if (obj instanceof C1375a) {
            RuntimeException runtimeException = ((C1375a) obj).f15541b;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(runtimeException);
            throw cancellationException;
        }
        if (obj instanceof C1377c) {
            throw new ExecutionException(((C1377c) obj).f15543a);
        }
        if (obj == f15558r) {
            return null;
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object h(h5.y r6) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h5.p.h(h5.y):java.lang.Object");
    }

    public static Object i(y yVar) {
        Object obj;
        boolean z6 = false;
        while (true) {
            try {
                obj = yVar.get();
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
            Object objI = i(this);
            sb.append("SUCCESS, result=[");
            d(sb, objI);
            sb.append("]");
        } catch (CancellationException unused) {
            sb.append("CANCELLED");
        } catch (ExecutionException e10) {
            sb.append("FAILURE, cause=[");
            sb.append(e10.getCause());
            sb.append("]");
        } catch (Exception e11) {
            sb.append("UNKNOWN, cause=[");
            sb.append(e11.getClass());
            sb.append(" thrown from get()]");
        }
    }

    @Override // h5.y
    public void b(Runnable runnable, Executor executor) {
        C1378d c1378d;
        C1378d c1378d2;
        P8.n.q(executor, "Executor was null.");
        if (!isDone() && (c1378d = this.f15559m) != (c1378d2 = C1378d.d)) {
            C1378d c1378d3 = new C1378d(runnable, executor);
            do {
                c1378d3.c = c1378d;
                if (f15557q.E(this, c1378d, c1378d3)) {
                    return;
                } else {
                    c1378d = this.f15559m;
                }
            } while (c1378d != c1378d2);
        }
        f(runnable, executor);
    }

    public void c() {
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z6) {
        C1375a c1375a;
        Object obj = this.l;
        if (!(obj == null) && !(obj instanceof RunnableC1380f)) {
            return false;
        }
        if (o) {
            c1375a = new C1375a(z6, new CancellationException("Future.cancel() was called."));
        } else {
            c1375a = z6 ? C1375a.c : C1375a.d;
            Objects.requireNonNull(c1375a);
        }
        boolean z10 = false;
        while (true) {
            if (f15557q.F(this, obj, c1375a)) {
                e(this, z6);
                if (!(obj instanceof RunnableC1380f)) {
                    return true;
                }
                y yVar = ((RunnableC1380f) obj).f15549m;
                if (!(yVar instanceof h)) {
                    yVar.cancel(z6);
                    return true;
                }
                this = (p) yVar;
                obj = this.l;
                if (!(obj == null) && !(obj instanceof RunnableC1380f)) {
                    return true;
                }
                z10 = true;
            } else {
                obj = this.l;
                if (!(obj instanceof RunnableC1380f)) {
                    return z10;
                }
            }
        }
    }

    public final void d(StringBuilder sb, Object obj) {
        if (obj == null) {
            sb.append("null");
        } else {
            if (obj == this) {
                sb.append("this future");
                return;
            }
            sb.append(obj.getClass().getName());
            sb.append("@");
            sb.append(Integer.toHexString(System.identityHashCode(obj)));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c1  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x00b4 -> B:36:0x007d). Please report as a decompilation issue!!! */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object get(long r20, java.util.concurrent.TimeUnit r22) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h5.p.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    public boolean isCancelled() {
        return this.l instanceof C1375a;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return (!(r2 instanceof RunnableC1380f)) & (this.l != null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String j() {
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public final void k(o oVar) {
        oVar.f15554a = null;
        while (true) {
            o oVar2 = this.f15560n;
            if (oVar2 == o.c) {
                return;
            }
            o oVar3 = null;
            while (oVar2 != null) {
                o oVar4 = oVar2.f15555b;
                if (oVar2.f15554a != null) {
                    oVar3 = oVar2;
                } else if (oVar3 != null) {
                    oVar3.f15555b = oVar4;
                    if (oVar3.f15554a == null) {
                        break;
                    }
                } else if (!f15557q.G(this, oVar2, oVar4)) {
                    break;
                }
                oVar2 = oVar4;
            }
            return;
        }
    }

    public boolean l(Object obj) {
        if (obj == null) {
            obj = f15558r;
        }
        if (!f15557q.F(this, null, obj)) {
            return false;
        }
        e(this, false);
        return true;
    }

    public boolean m(Throwable th) {
        if (!f15557q.F(this, null, new C1377c(th))) {
            return false;
        }
        e(this, false);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h5.p.toString():java.lang.String");
    }

    @Override // java.util.concurrent.Future
    public Object get() throws InterruptedException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.l;
            if ((obj2 != null) & (!(obj2 instanceof RunnableC1380f))) {
                return g(obj2);
            }
            o oVar = this.f15560n;
            o oVar2 = o.c;
            if (oVar != oVar2) {
                o oVar3 = new o();
                do {
                    l9.d dVar = f15557q;
                    dVar.Y(oVar3, oVar);
                    if (dVar.G(this, oVar, oVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.l;
                            } else {
                                k(oVar3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof RunnableC1380f))));
                        return g(obj);
                    }
                    oVar = this.f15560n;
                } while (oVar != oVar2);
            }
            Object obj3 = this.l;
            Objects.requireNonNull(obj3);
            return g(obj3);
        }
        throw new InterruptedException();
    }
}
