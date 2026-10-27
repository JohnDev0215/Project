package p;

import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public final class rc extends Thread {
    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        ReentrantLock reentrantLock;
        uc ucVarP;
        while (true) {
            try {
                ReentrantLock reentrantLock2 = uc.h;
                reentrantLock = uc.h;
                reentrantLock.lock();
                try {
                    ucVarP = u11.p();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            } catch (InterruptedException unused) {
                continue;
            }
            if (ucVarP == uc.l) {
                uc.l = null;
                reentrantLock.unlock();
                return;
            } else {
                reentrantLock.unlock();
                if (ucVarP != null) {
                    ucVarP.l();
                }
            }
        }
    }
}
