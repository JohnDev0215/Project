package n2;

import android.os.Looper;
import android.os.SystemClock;
import e2.f0;
import h2.AbstractC1346c;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public final class X {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final W f19602a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final V f19603b;
    public final h2.v c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f19604e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Looper f19605f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f19606g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f19607h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f19608i;

    public X(V v10, W w10, f0 f0Var, int i7, h2.v vVar, Looper looper) {
        this.f19603b = v10;
        this.f19602a = w10;
        this.f19605f = looper;
        this.c = vVar;
    }

    public final synchronized void a(long j3) {
        boolean z6;
        AbstractC1346c.j(this.f19606g);
        AbstractC1346c.j(this.f19605f.getThread() != Thread.currentThread());
        this.c.getClass();
        long jElapsedRealtime = SystemClock.elapsedRealtime() + j3;
        while (true) {
            z6 = this.f19608i;
            if (z6 || j3 <= 0) {
                break;
            }
            this.c.getClass();
            wait(j3);
            this.c.getClass();
            j3 = jElapsedRealtime - SystemClock.elapsedRealtime();
        }
        if (!z6) {
            throw new TimeoutException("Message delivery timed out.");
        }
    }

    public final synchronized void b(boolean z6) {
        this.f19607h = z6 | this.f19607h;
        this.f19608i = true;
        notifyAll();
    }

    public final void c() {
        AbstractC1346c.j(!this.f19606g);
        this.f19606g = true;
        D d = (D) this.f19603b;
        synchronized (d) {
            if (!d.f19499N && d.f19527v.getThread().isAlive()) {
                d.f19525t.a(14, this).b();
                return;
            }
            AbstractC1346c.z("ExoPlayerImplInternal", "Ignoring messages sent after release.");
            b(false);
        }
    }
}
