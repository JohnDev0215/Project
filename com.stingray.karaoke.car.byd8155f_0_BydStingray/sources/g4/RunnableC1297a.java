package g4;

import android.os.Process;
import java.util.logging.Level;
import k7.C1707a;

/* JADX INFO: renamed from: g4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1297a implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Runnable f14851m;

    public /* synthetic */ RunnableC1297a(Runnable runnable, int i7) {
        this.l = i7;
        this.f14851m = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                Process.setThreadPriority(10);
                this.f14851m.run();
                return;
            default:
                try {
                    this.f14851m.run();
                    synchronized (C1707a.class) {
                        try {
                            int i7 = C1707a.f18311p - 1;
                            C1707a.f18311p = i7;
                            if (i7 == 0) {
                                C1707a.o.shutdown();
                                C1707a.o = null;
                                C1707a.f18310n = null;
                            }
                        } finally {
                        }
                        break;
                    }
                    return;
                } catch (Throwable th) {
                    try {
                        C1707a.l.log(Level.SEVERE, "Task threw exception", th);
                        throw th;
                    } catch (Throwable th2) {
                        synchronized (C1707a.class) {
                            try {
                                int i10 = C1707a.f18311p - 1;
                                C1707a.f18311p = i10;
                                if (i10 == 0) {
                                    C1707a.o.shutdown();
                                    C1707a.o = null;
                                    C1707a.f18310n = null;
                                }
                                throw th2;
                            } finally {
                            }
                        }
                    }
                }
        }
    }
}
