package j4;

import android.os.Process;
import java.util.concurrent.locks.ReentrantLock;
import l9.n;
import r9.C2315e;

/* JADX INFO: renamed from: j4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1540a extends Thread {
    public final /* synthetic */ int l = 0;

    public /* synthetic */ C1540a(Runnable runnable) {
        super(runnable);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        ReentrantLock reentrantLock;
        C2315e c2315eH;
        switch (this.l) {
            case 0:
                Process.setThreadPriority(9);
                super.run();
                return;
        }
        while (true) {
            try {
                ReentrantLock reentrantLock2 = C2315e.f21919h;
                reentrantLock = C2315e.f21919h;
                reentrantLock.lock();
                try {
                    c2315eH = n.H();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            } catch (InterruptedException unused) {
            }
            if (c2315eH == C2315e.l) {
                C2315e.l = null;
                reentrantLock.unlock();
                return;
            } else {
                reentrantLock.unlock();
                if (c2315eH != null) {
                    c2315eH.k();
                }
            }
        }
    }

    public /* synthetic */ C1540a(String str) {
        super(str);
    }
}
