package org.libpag;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/* JADX INFO: loaded from: classes3.dex */
public class u extends Handler {

    public static final class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f4089a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f4090b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f4091c = false;

        public a(b bVar) {
            this.f4089a = bVar;
        }

        public boolean a(Handler handler, long j) {
            if (!handler.post(this)) {
                return false;
            }
            synchronized (this) {
                if (j > 0) {
                    long jUptimeMillis = SystemClock.uptimeMillis() + j;
                    while (!this.f4090b) {
                        long jUptimeMillis2 = jUptimeMillis - SystemClock.uptimeMillis();
                        if (jUptimeMillis2 <= 0) {
                            this.f4091c = true;
                            return false;
                        }
                        try {
                            wait(jUptimeMillis2);
                        } catch (InterruptedException unused) {
                        }
                    }
                } else {
                    while (!this.f4090b) {
                        try {
                            wait();
                        } catch (InterruptedException unused2) {
                        }
                    }
                }
                return true;
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f4089a.run();
                synchronized (this) {
                    this.f4090b = true;
                    notifyAll();
                    this.f4089a.a(this.f4091c);
                }
            } catch (Throwable th) {
                synchronized (this) {
                    this.f4090b = true;
                    notifyAll();
                    this.f4089a.a(this.f4091c);
                    throw th;
                }
            }
        }
    }

    public interface b extends Runnable {
        void a(boolean z);
    }

    public u(Looper looper) {
        super(looper);
    }

    public final boolean a(b bVar, long j) {
        if (bVar == null) {
            throw new IllegalArgumentException("runnable must not be null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("timeout must be non-negative");
        }
        if (Looper.myLooper() != getLooper()) {
            return new a(bVar).a(this, j);
        }
        bVar.run();
        return true;
    }
}
