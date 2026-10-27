package io.sentry.transport;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/* JADX INFO: loaded from: classes.dex */
public final class n extends AbstractQueuedSynchronizer {
    public static final /* synthetic */ int l = 0;

    public n() {
        setState(0);
    }

    public static int a(n nVar) {
        return nVar.getState();
    }

    public static void b(n nVar) {
        int state;
        do {
            state = nVar.getState();
        } while (!nVar.compareAndSetState(state, state + 1));
    }

    @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
    public final int tryAcquireShared(int i7) {
        return getState() == 0 ? 1 : -1;
    }

    @Override // java.util.concurrent.locks.AbstractQueuedSynchronizer
    public final boolean tryReleaseShared(int i7) {
        int state;
        int i10;
        do {
            state = getState();
            if (state == 0) {
                return false;
            }
            i10 = state - 1;
        } while (!compareAndSetState(state, i10));
        return i10 == 0;
    }
}
