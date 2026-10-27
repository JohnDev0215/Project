package h5;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;

/* JADX INFO: loaded from: classes.dex */
public final class w extends AbstractOwnableSynchronizer implements Runnable {
    public final RunnableC1374F l;

    public w(RunnableC1374F runnableC1374F) {
        this.l = runnableC1374F;
    }

    public static void a(w wVar, Thread thread) {
        wVar.setExclusiveOwnerThread(thread);
    }

    @Override // java.lang.Runnable
    public final void run() {
    }

    public final String toString() {
        return this.l.toString();
    }
}
