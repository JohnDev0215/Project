package defpackage;

import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: s, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AbstractResolvableFuture.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0208s<V> implements c91<V> {
    public static final boolean h = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    public static final a i;
    public static final Object j;
    public volatile Object e;
    public volatile d f;
    public volatile h g;

    /* JADX INFO: renamed from: s$a */
    /* JADX INFO: compiled from: AbstractResolvableFuture.java */
    public static abstract class a {
        public abstract boolean a(AbstractC0208s<?> abstractC0208s, d dVar, d dVar2);

        public abstract boolean b(AbstractC0208s<?> abstractC0208s, Object obj, Object obj2);

        public abstract boolean c(AbstractC0208s<?> abstractC0208s, h hVar, h hVar2);

        public abstract void d(h hVar, h hVar2);

        public abstract void e(h hVar, Thread thread);
    }

    /* JADX INFO: renamed from: s$b */
    /* JADX INFO: compiled from: AbstractResolvableFuture.java */
    public static final class b {
        public static final b b;
        public static final b c;
        public final Throwable a;

        static {
            if (AbstractC0208s.h) {
                c = null;
                b = null;
            } else {
                c = new b(false, null);
                b = new b(true, null);
            }
        }

        public b(boolean z, Throwable th) {
            this.a = th;
        }
    }

    /* JADX INFO: renamed from: s$c */
    /* JADX INFO: compiled from: AbstractResolvableFuture.java */
    public static final class c {
        public final Throwable a;

        /* JADX INFO: renamed from: s$c$a */
        /* JADX INFO: compiled from: AbstractResolvableFuture.java */
        public class a extends Throwable {
            public a() {
                super("Failure occurred while trying to finish a future.");
            }

            @Override // java.lang.Throwable
            public final synchronized Throwable fillInStackTrace() {
                return this;
            }
        }

        static {
            new c(new a());
        }

        public c(Throwable th) {
            boolean z = AbstractC0208s.h;
            th.getClass();
            this.a = th;
        }
    }

    /* JADX INFO: renamed from: s$d */
    /* JADX INFO: compiled from: AbstractResolvableFuture.java */
    public static final class d {
        public static final d d = new d(null, null);
        public final Runnable a;
        public final Executor b;
        public d c;

        public d(Runnable runnable, Executor executor) {
            this.a = runnable;
            this.b = executor;
        }
    }

    /* JADX INFO: renamed from: s$e */
    /* JADX INFO: compiled from: AbstractResolvableFuture.java */
    public static final class e extends a {
        public final AtomicReferenceFieldUpdater<h, Thread> a;
        public final AtomicReferenceFieldUpdater<h, h> b;
        public final AtomicReferenceFieldUpdater<AbstractC0208s, h> c;
        public final AtomicReferenceFieldUpdater<AbstractC0208s, d> d;
        public final AtomicReferenceFieldUpdater<AbstractC0208s, Object> e;

        public e(AtomicReferenceFieldUpdater<h, Thread> atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater<h, h> atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater<AbstractC0208s, h> atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater<AbstractC0208s, d> atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater<AbstractC0208s, Object> atomicReferenceFieldUpdater5) {
            this.a = atomicReferenceFieldUpdater;
            this.b = atomicReferenceFieldUpdater2;
            this.c = atomicReferenceFieldUpdater3;
            this.d = atomicReferenceFieldUpdater4;
            this.e = atomicReferenceFieldUpdater5;
        }

        @Override // defpackage.AbstractC0208s.a
        public final boolean a(AbstractC0208s<?> abstractC0208s, d dVar, d dVar2) {
            AtomicReferenceFieldUpdater<AbstractC0208s, d> atomicReferenceFieldUpdater;
            do {
                atomicReferenceFieldUpdater = this.d;
                if (atomicReferenceFieldUpdater.compareAndSet(abstractC0208s, dVar, dVar2)) {
                    return true;
                }
            } while (atomicReferenceFieldUpdater.get(abstractC0208s) == dVar);
            return false;
        }

        @Override // defpackage.AbstractC0208s.a
        public final boolean b(AbstractC0208s<?> abstractC0208s, Object obj, Object obj2) {
            AtomicReferenceFieldUpdater<AbstractC0208s, Object> atomicReferenceFieldUpdater;
            do {
                atomicReferenceFieldUpdater = this.e;
                if (atomicReferenceFieldUpdater.compareAndSet(abstractC0208s, obj, obj2)) {
                    return true;
                }
            } while (atomicReferenceFieldUpdater.get(abstractC0208s) == obj);
            return false;
        }

        @Override // defpackage.AbstractC0208s.a
        public final boolean c(AbstractC0208s<?> abstractC0208s, h hVar, h hVar2) {
            AtomicReferenceFieldUpdater<AbstractC0208s, h> atomicReferenceFieldUpdater;
            do {
                atomicReferenceFieldUpdater = this.c;
                if (atomicReferenceFieldUpdater.compareAndSet(abstractC0208s, hVar, hVar2)) {
                    return true;
                }
            } while (atomicReferenceFieldUpdater.get(abstractC0208s) == hVar);
            return false;
        }

        @Override // defpackage.AbstractC0208s.a
        public final void d(h hVar, h hVar2) {
            this.b.lazySet(hVar, hVar2);
        }

        @Override // defpackage.AbstractC0208s.a
        public final void e(h hVar, Thread thread) {
            this.a.lazySet(hVar, thread);
        }
    }

    /* JADX INFO: renamed from: s$f */
    /* JADX INFO: compiled from: AbstractResolvableFuture.java */
    public static final class f<V> implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            throw null;
        }
    }

    /* JADX INFO: renamed from: s$g */
    /* JADX INFO: compiled from: AbstractResolvableFuture.java */
    public static final class g extends a {
        @Override // defpackage.AbstractC0208s.a
        public final boolean a(AbstractC0208s<?> abstractC0208s, d dVar, d dVar2) {
            synchronized (abstractC0208s) {
                if (abstractC0208s.f != dVar) {
                    return false;
                }
                abstractC0208s.f = dVar2;
                return true;
            }
        }

        @Override // defpackage.AbstractC0208s.a
        public final boolean b(AbstractC0208s<?> abstractC0208s, Object obj, Object obj2) {
            synchronized (abstractC0208s) {
                if (abstractC0208s.e != obj) {
                    return false;
                }
                abstractC0208s.e = obj2;
                return true;
            }
        }

        @Override // defpackage.AbstractC0208s.a
        public final boolean c(AbstractC0208s<?> abstractC0208s, h hVar, h hVar2) {
            synchronized (abstractC0208s) {
                if (abstractC0208s.g != hVar) {
                    return false;
                }
                abstractC0208s.g = hVar2;
                return true;
            }
        }

        @Override // defpackage.AbstractC0208s.a
        public final void d(h hVar, h hVar2) {
            hVar.b = hVar2;
        }

        @Override // defpackage.AbstractC0208s.a
        public final void e(h hVar, Thread thread) {
            hVar.a = thread;
        }
    }

    /* JADX INFO: renamed from: s$h */
    /* JADX INFO: compiled from: AbstractResolvableFuture.java */
    public static final class h {
        public static final h c = new h(0);
        public volatile Thread a;
        public volatile h b;

        public h(int i) {
        }

        public h() {
            AbstractC0208s.i.e(this, Thread.currentThread());
        }
    }

    static {
        a gVar;
        try {
            gVar = new e(AtomicReferenceFieldUpdater.newUpdater(h.class, Thread.class, "a"), AtomicReferenceFieldUpdater.newUpdater(h.class, h.class, "b"), AtomicReferenceFieldUpdater.newUpdater(AbstractC0208s.class, h.class, "g"), AtomicReferenceFieldUpdater.newUpdater(AbstractC0208s.class, d.class, "f"), AtomicReferenceFieldUpdater.newUpdater(AbstractC0208s.class, Object.class, "e"));
        } catch (Throwable unused) {
            gVar = new g();
        }
        i = gVar;
        j = new Object();
    }

    public static void c(AbstractC0208s<?> abstractC0208s) {
        h hVar;
        d dVar;
        do {
            hVar = abstractC0208s.g;
        } while (!i.c(abstractC0208s, hVar, h.c));
        while (hVar != null) {
            Thread thread = hVar.a;
            if (thread != null) {
                hVar.a = null;
                LockSupport.unpark(thread);
            }
            hVar = hVar.b;
        }
        do {
            dVar = abstractC0208s.f;
        } while (!i.a(abstractC0208s, dVar, d.d));
        d dVar2 = null;
        while (dVar != null) {
            d dVar3 = dVar.c;
            dVar.c = dVar2;
            dVar2 = dVar;
            dVar = dVar3;
        }
        while (dVar2 != null) {
            d dVar4 = dVar2.c;
            Runnable runnable = dVar2.a;
            if (runnable instanceof f) {
                ((f) runnable).getClass();
                throw null;
            }
            Executor executor = dVar2.b;
            try {
                executor.execute(runnable);
            } catch (RuntimeException unused) {
                Objects.toString(runnable);
                Objects.toString(executor);
            }
            dVar2 = dVar4;
        }
    }

    public static Object d(Object obj) throws ExecutionException {
        if (obj instanceof b) {
            Throwable th = ((b) obj).a;
            CancellationException cancellationException = new CancellationException("Task was cancelled.");
            cancellationException.initCause(th);
            throw cancellationException;
        }
        if (obj instanceof c) {
            throw new ExecutionException(((c) obj).a);
        }
        if (obj == j) {
            return null;
        }
        return obj;
    }

    @Override // defpackage.c91
    public final void a(Runnable runnable, Executor executor) {
        d dVar = this.f;
        d dVar2 = d.d;
        if (dVar != dVar2) {
            d dVar3 = new d(runnable, executor);
            do {
                dVar3.c = dVar;
                if (i.a(this, dVar, dVar3)) {
                    return;
                } else {
                    dVar = this.f;
                }
            } while (dVar != dVar2);
        }
        try {
            executor.execute(runnable);
        } catch (RuntimeException unused) {
            Objects.toString(runnable);
            Objects.toString(executor);
        }
    }

    public final void b(StringBuilder sb) {
        V v;
        boolean z = false;
        while (true) {
            try {
                try {
                    v = get();
                    break;
                } catch (CancellationException unused) {
                    sb.append("CANCELLED");
                    return;
                } catch (RuntimeException e2) {
                    sb.append("UNKNOWN, cause=[");
                    sb.append(e2.getClass());
                    sb.append(" thrown from get()]");
                    return;
                } catch (ExecutionException e3) {
                    sb.append("FAILURE, cause=[");
                    sb.append(e3.getCause());
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
        sb.append(v == this ? "this future" : String.valueOf(v));
        sb.append("]");
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        Object obj = this.e;
        if ((obj == null) | (obj instanceof f)) {
            b bVar = h ? new b(z, new CancellationException("Future.cancel() was called.")) : z ? b.b : b.c;
            while (!i.b(this, obj, bVar)) {
                obj = this.e;
                if (!(obj instanceof f)) {
                }
            }
            c(this);
            if (!(obj instanceof f)) {
                return true;
            }
            ((f) obj).getClass();
            throw null;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String e() {
        Object obj = this.e;
        if (obj instanceof f) {
            StringBuilder sb = new StringBuilder("setFuture=[");
            ((f) obj).getClass();
            sb.append("null");
            sb.append("]");
            return sb.toString();
        }
        if (!(this instanceof ScheduledFuture)) {
            return null;
        }
        return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
    }

    public final void f(h hVar) {
        hVar.a = null;
        while (true) {
            h hVar2 = this.g;
            if (hVar2 == h.c) {
                return;
            }
            h hVar3 = null;
            while (hVar2 != null) {
                h hVar4 = hVar2.b;
                if (hVar2.a != null) {
                    hVar3 = hVar2;
                } else if (hVar3 != null) {
                    hVar3.b = hVar4;
                    if (hVar3.a == null) {
                        break;
                    }
                } else if (!i.c(this, hVar2, hVar4)) {
                    break;
                }
                hVar2 = hVar4;
            }
            return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x00b6 -> B:56:0x00bc). Please report as a decompilation issue!!! */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final V get(long r18, java.util.concurrent.TimeUnit r20) throws java.lang.InterruptedException, java.util.concurrent.TimeoutException {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC0208s.get(long, java.util.concurrent.TimeUnit):java.lang.Object");
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.e instanceof b;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return (!(r2 instanceof f)) & (this.e != null);
    }

    public final String toString() {
        String strE;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[status=");
        if (this.e instanceof b) {
            sb.append("CANCELLED");
        } else if (isDone()) {
            b(sb);
        } else {
            try {
                strE = e();
            } catch (RuntimeException e2) {
                strE = "Exception thrown from implementation: " + e2.getClass();
            }
            if (strE != null && !strE.isEmpty()) {
                sb.append("PENDING, info=[");
                sb.append(strE);
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
    public final V get() throws InterruptedException {
        Object obj;
        if (!Thread.interrupted()) {
            Object obj2 = this.e;
            if ((obj2 != null) & (!(obj2 instanceof f))) {
                return (V) d(obj2);
            }
            h hVar = this.g;
            h hVar2 = h.c;
            if (hVar != hVar2) {
                h hVar3 = new h();
                do {
                    a aVar = i;
                    aVar.d(hVar3, hVar);
                    if (aVar.c(this, hVar, hVar3)) {
                        do {
                            LockSupport.park(this);
                            if (!Thread.interrupted()) {
                                obj = this.e;
                            } else {
                                f(hVar3);
                                throw new InterruptedException();
                            }
                        } while (!((obj != null) & (!(obj instanceof f))));
                        return (V) d(obj);
                    }
                    hVar = this.g;
                } while (hVar != hVar2);
            }
            return (V) d(this.e);
        }
        throw new InterruptedException();
    }
}
