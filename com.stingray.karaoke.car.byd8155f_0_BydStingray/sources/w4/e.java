package w4;

import A4.q;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import g4.t;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public final class e implements Future, x4.c {
    public Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c f23292m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f23293n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f23294p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public t f23295q;

    @Override // x4.c
    public final synchronized c D() {
        return this.f23292m;
    }

    @Override // x4.c
    public final void E(Drawable drawable) {
    }

    @Override // x4.c
    public final synchronized void F(Object obj) {
    }

    @Override // t4.b
    public final void J() {
    }

    public final synchronized Object a(Long l) {
        if (!isDone()) {
            char[] cArr = q.f432a;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                throw new IllegalArgumentException("You must call this method on a background thread");
            }
        }
        if (this.f23293n) {
            throw new CancellationException();
        }
        if (this.f23294p) {
            throw new ExecutionException(this.f23295q);
        }
        if (this.o) {
            return this.l;
        }
        if (l == null) {
            wait(0L);
        } else if (l.longValue() > 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jLongValue = l.longValue() + jCurrentTimeMillis;
            while (!isDone() && jCurrentTimeMillis < jLongValue) {
                wait(jLongValue - jCurrentTimeMillis);
                jCurrentTimeMillis = System.currentTimeMillis();
            }
        }
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (this.f23294p) {
            throw new ExecutionException(this.f23295q);
        }
        if (this.f23293n) {
            throw new CancellationException();
        }
        if (this.o) {
            return this.l;
        }
        throw new TimeoutException();
    }

    public final synchronized void c(t tVar) {
        this.f23294p = true;
        this.f23295q = tVar;
        notifyAll();
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z6) {
        synchronized (this) {
            try {
                if (isDone()) {
                    return false;
                }
                this.f23293n = true;
                notifyAll();
                c cVar = null;
                if (z6) {
                    c cVar2 = this.f23292m;
                    this.f23292m = null;
                    cVar = cVar2;
                }
                if (cVar != null) {
                    cVar.clear();
                }
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final synchronized void d(Object obj) {
        this.o = true;
        this.l = obj;
        notifyAll();
    }

    @Override // x4.c
    public final void g(g gVar) {
        gVar.m(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        try {
            return a(null);
        } catch (TimeoutException e10) {
            throw new AssertionError(e10);
        }
    }

    @Override // x4.c
    public final synchronized void i(Drawable drawable) {
    }

    @Override // java.util.concurrent.Future
    public final synchronized boolean isCancelled() {
        return this.f23293n;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0012  */
    @Override // java.util.concurrent.Future
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized boolean isDone() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.f23293n     // Catch: java.lang.Throwable -> L10
            if (r0 != 0) goto L12
            boolean r0 = r1.o     // Catch: java.lang.Throwable -> L10
            if (r0 != 0) goto L12
            boolean r0 = r1.f23294p     // Catch: java.lang.Throwable -> L10
            if (r0 == 0) goto Le
            goto L12
        Le:
            r0 = 0
            goto L13
        L10:
            r0 = move-exception
            goto L15
        L12:
            r0 = 1
        L13:
            monitor-exit(r1)
            return r0
        L15:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L10
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: w4.e.isDone():boolean");
    }

    @Override // x4.c
    public final synchronized void n(c cVar) {
        this.f23292m = cVar;
    }

    @Override // x4.c
    public final void t(Drawable drawable) {
    }

    public final String toString() {
        c cVar;
        String str;
        String strL = android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), super.toString(), "[status=");
        synchronized (this) {
            try {
                cVar = null;
                if (this.f23293n) {
                    str = "CANCELLED";
                } else if (this.f23294p) {
                    str = "FAILURE";
                } else if (this.o) {
                    str = "SUCCESS";
                } else {
                    str = "PENDING";
                    cVar = this.f23292m;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (cVar == null) {
            return strL + str + "]";
        }
        return strL + str + ", request=[" + cVar + "]]";
    }

    @Override // x4.c
    public final void u(g gVar) {
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j3, TimeUnit timeUnit) {
        return a(Long.valueOf(timeUnit.toMillis(j3)));
    }
}
