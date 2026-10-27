package com.android.systemui;

import android.util.Pools;
import android.view.SurfaceControl;
import javax.inject.Singleton;

/* JADX INFO: loaded from: classes.dex */
@Singleton
public class TransactionPool {
    private final Pools.SynchronizedPool<SurfaceControl.Transaction> mTransactionPool = new Pools.SynchronizedPool<>(4);

    public SurfaceControl.Transaction acquire() {
        SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) this.mTransactionPool.acquire();
        return transaction == null ? new SurfaceControl.Transaction() : transaction;
    }

    public void release(SurfaceControl.Transaction t) {
        if (this.mTransactionPool.release(t)) {
            return;
        }
        t.close();
    }
}
