package x2;

import android.hardware.bydauto.TsNormalNum;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.os.Trace;
import h2.AbstractC1346c;
import i3.C1406e;
import java.io.IOException;
import y2.ExecutorC2739a;

/* JADX INFO: loaded from: classes.dex */
public final class k extends Handler implements Runnable {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final l f23749m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f23750n;
    public IOException o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23751p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Thread f23752q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f23753r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public volatile boolean f23754s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ n f23755t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(n nVar, Looper looper, l lVar, j jVar, int i7, long j3) {
        super(looper);
        this.f23755t = nVar;
        this.f23749m = lVar;
        this.f23750n = jVar;
        this.l = i7;
    }

    /* JADX WARN: Type inference failed for: r4v3, types: [java.lang.Object, x2.j] */
    public final void a(boolean z6) {
        this.f23754s = z6;
        this.o = null;
        if (hasMessages(1)) {
            this.f23753r = true;
            removeMessages(1);
            if (!z6) {
                sendEmptyMessage(2);
            }
        } else {
            synchronized (this) {
                try {
                    this.f23753r = true;
                    this.f23749m.b();
                    Thread thread = this.f23752q;
                    if (thread != null) {
                        thread.interrupt();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        if (z6) {
            this.f23755t.f23758b = null;
            SystemClock.elapsedRealtime();
            ?? r42 = this.f23750n;
            r42.getClass();
            r42.n(this.f23749m, true);
            this.f23750n = null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, x2.j] */
    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (this.f23754s) {
            return;
        }
        int i7 = message.what;
        if (i7 == 1) {
            SystemClock.elapsedRealtime();
            this.f23750n.getClass();
            this.o = null;
            n nVar = this.f23755t;
            ExecutorC2739a executorC2739a = nVar.f23757a;
            k kVar = nVar.f23758b;
            kVar.getClass();
            executorC2739a.execute(kVar);
            return;
        }
        if (i7 == 4) {
            throw ((Error) message.obj);
        }
        this.f23755t.f23758b = null;
        SystemClock.elapsedRealtime();
        ?? r02 = this.f23750n;
        r02.getClass();
        if (this.f23753r) {
            r02.n(this.f23749m, false);
            return;
        }
        int i10 = message.what;
        if (i10 == 2) {
            try {
                r02.c(this.f23749m);
                return;
            } catch (RuntimeException e10) {
                AbstractC1346c.q("LoadTask", "Unexpected exception handling load completed", e10);
                this.f23755t.c = new m(e10);
                return;
            }
        }
        if (i10 != 3) {
            return;
        }
        IOException iOException = (IOException) message.obj;
        this.o = iOException;
        int i11 = this.f23751p + 1;
        this.f23751p = i11;
        C1406e c1406eF = r02.f(this.f23749m, iOException, i11);
        int i12 = c1406eF.f15742a;
        if (i12 == 3) {
            this.f23755t.c = this.o;
            return;
        }
        if (i12 != 2) {
            if (i12 == 1) {
                this.f23751p = 1;
            }
            long jMin = c1406eF.f15743b;
            if (jMin == -9223372036854775807L) {
                jMin = Math.min((this.f23751p - 1) * 1000, TsNormalNum.THOUSAND_FIVE);
            }
            n nVar2 = this.f23755t;
            AbstractC1346c.j(nVar2.f23758b == null);
            nVar2.f23758b = this;
            if (jMin > 0) {
                sendEmptyMessageDelayed(1, jMin);
                return;
            }
            SystemClock.elapsedRealtime();
            this.f23750n.getClass();
            this.o = null;
            ExecutorC2739a executorC2739a2 = nVar2.f23757a;
            k kVar2 = nVar2.f23758b;
            kVar2.getClass();
            executorC2739a2.execute(kVar2);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z6;
        try {
            synchronized (this) {
                z6 = this.f23753r;
                this.f23752q = Thread.currentThread();
            }
            if (!z6) {
                Trace.beginSection("load:".concat(this.f23749m.getClass().getSimpleName()));
                try {
                    this.f23749m.a();
                    Trace.endSection();
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            }
            synchronized (this) {
                this.f23752q = null;
                Thread.interrupted();
            }
            if (this.f23754s) {
                return;
            }
            sendEmptyMessage(2);
        } catch (IOException e10) {
            if (this.f23754s) {
                return;
            }
            obtainMessage(3, e10).sendToTarget();
        } catch (Exception e11) {
            if (this.f23754s) {
                return;
            }
            AbstractC1346c.q("LoadTask", "Unexpected exception loading stream", e11);
            obtainMessage(3, new m(e11)).sendToTarget();
        } catch (OutOfMemoryError e12) {
            if (this.f23754s) {
                return;
            }
            AbstractC1346c.q("LoadTask", "OutOfMemory error loading stream", e12);
            obtainMessage(3, new m(e12)).sendToTarget();
        } catch (Error e13) {
            if (!this.f23754s) {
                AbstractC1346c.q("LoadTask", "Unexpected error loading stream", e13);
                obtainMessage(4, e13).sendToTarget();
            }
            throw e13;
        }
    }
}
