package com.android.systemui.stackdivider;

import android.os.Handler;
import android.util.Slog;
import android.view.SurfaceControl;
import android.window.WindowContainerTransaction;
import android.window.WindowContainerTransactionCallback;
import android.window.WindowOrganizer;
import com.android.systemui.TransactionPool;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class SyncTransactionQueue {
    private static final boolean DEBUG = true;
    private static final int REPLY_TIMEOUT = 1000;
    private static final String TAG = "SyncTransactionQueue";
    private final Handler mHandler;
    private final TransactionPool mTransactionPool;
    private final ArrayList<SyncCallback> mQueue = new ArrayList<>();
    private SyncCallback mInFlight = null;
    private final ArrayList<TransactionRunnable> mRunnables = new ArrayList<>();
    private final Runnable mOnReplyTimeout = new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SyncTransactionQueue$v8dGaUwc5zbeg2ZdFERjyMCQzAs
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$SyncTransactionQueue();
        }
    };

    interface TransactionRunnable {
        void runWithTransaction(SurfaceControl.Transaction t);
    }

    public /* synthetic */ void lambda$new$0$SyncTransactionQueue() {
        synchronized (this.mQueue) {
            SyncCallback syncCallback = this.mInFlight;
            if (syncCallback != null && this.mQueue.contains(syncCallback)) {
                BydLogHelper.logD(TAG, "Sync Transaction timed-out: " + this.mInFlight.mWCT);
                SyncCallback syncCallback2 = this.mInFlight;
                syncCallback2.onTransactionReady(syncCallback2.mId, new SurfaceControl.Transaction());
            }
        }
    }

    SyncTransactionQueue(TransactionPool pool, Handler handler) {
        this.mTransactionPool = pool;
        this.mHandler = handler;
    }

    void queue(WindowContainerTransaction wct) {
        SyncCallback syncCallback = new SyncCallback(wct);
        synchronized (this.mQueue) {
            BydLogHelper.logD(TAG, "Queueing up " + wct);
            this.mQueue.add(syncCallback);
            BydLogHelper.logD(TAG, "Queueing up mQueue.size() : " + this.mQueue.size());
            if (this.mQueue.size() == 1) {
                syncCallback.send();
            }
        }
    }

    boolean queueIfWaiting(WindowContainerTransaction wct) {
        synchronized (this.mQueue) {
            if (this.mQueue.isEmpty()) {
                BydLogHelper.logD(TAG, "Nothing in queue, so skip queueing up " + wct);
                return false;
            }
            BydLogHelper.logD(TAG, "Queue is non-empty, so queueing up " + wct);
            SyncCallback syncCallback = new SyncCallback(wct);
            this.mQueue.add(syncCallback);
            if (this.mQueue.size() == 1) {
                syncCallback.send();
            }
            return true;
        }
    }

    void runInSync(TransactionRunnable runnable) {
        synchronized (this.mQueue) {
            BydLogHelper.logD(TAG, "Run in sync. mInFlight=" + this.mInFlight);
            if (this.mInFlight != null) {
                this.mRunnables.add(runnable);
                return;
            }
            SurfaceControl.Transaction transactionAcquire = this.mTransactionPool.acquire();
            runnable.runWithTransaction(transactionAcquire);
            transactionAcquire.apply();
            this.mTransactionPool.release(transactionAcquire);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTransactionReceived(SurfaceControl.Transaction t) {
        BydLogHelper.logD(TAG, "  Running " + this.mRunnables.size() + " sync runnables");
        int size = this.mRunnables.size();
        for (int i = 0; i < size; i++) {
            this.mRunnables.get(i).runWithTransaction(t);
        }
        this.mRunnables.clear();
        t.apply();
        t.close();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class SyncCallback extends WindowContainerTransactionCallback {
        int mId = -1;
        final WindowContainerTransaction mWCT;

        SyncCallback(WindowContainerTransaction wct) {
            this.mWCT = wct;
        }

        void send() {
            if (SyncTransactionQueue.this.mInFlight == null) {
                SyncTransactionQueue.this.mInFlight = this;
                BydLogHelper.logD(SyncTransactionQueue.TAG, "Sending sync transaction: " + this.mWCT);
                try {
                    BydLogHelper.logD("bounds applySyncTransaction mWCT : " + this.mWCT);
                    this.mId = new WindowOrganizer().applySyncTransaction(this.mWCT, this);
                } catch (Exception e) {
                    BydLogHelper.logE("send Transaction  e:" + e.toString());
                    e.printStackTrace();
                }
                BydLogHelper.logD(SyncTransactionQueue.TAG, " Sent sync transaction. Got id=" + this.mId);
                SyncTransactionQueue.this.mHandler.postDelayed(SyncTransactionQueue.this.mOnReplyTimeout, 1000L);
                return;
            }
            throw new IllegalStateException("Sync Transactions must be serialized. In Flight: " + SyncTransactionQueue.this.mInFlight.mId + " - " + SyncTransactionQueue.this.mInFlight.mWCT);
        }

        public void onTransactionReady(final int id, final SurfaceControl.Transaction t) {
            SyncTransactionQueue.this.mHandler.post(new Runnable() { // from class: com.android.systemui.stackdivider.-$$Lambda$SyncTransactionQueue$SyncCallback$vZDM9Fq7TYl-FC1knJbGvZ-EEIQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onTransactionReady$0$SyncTransactionQueue$SyncCallback(id, t);
                }
            });
        }

        public /* synthetic */ void lambda$onTransactionReady$0$SyncTransactionQueue$SyncCallback(int i, SurfaceControl.Transaction transaction) {
            synchronized (SyncTransactionQueue.this.mQueue) {
                if (this.mId == i) {
                    SyncTransactionQueue.this.mInFlight = null;
                    SyncTransactionQueue.this.mHandler.removeCallbacks(SyncTransactionQueue.this.mOnReplyTimeout);
                    BydLogHelper.logD(SyncTransactionQueue.TAG, "onTransactionReady id=" + this.mId);
                    SyncTransactionQueue.this.mQueue.remove(this);
                    SyncTransactionQueue.this.onTransactionReceived(transaction);
                    BydLogHelper.logD(SyncTransactionQueue.TAG, "onTransactionReady mQueue isEmpty()=" + SyncTransactionQueue.this.mQueue.isEmpty());
                    if (!SyncTransactionQueue.this.mQueue.isEmpty()) {
                        ((SyncCallback) SyncTransactionQueue.this.mQueue.get(0)).send();
                    }
                    return;
                }
                Slog.e(SyncTransactionQueue.TAG, "Got an unexpected onTransactionReady. Expected " + this.mId + " but got " + i);
            }
        }
    }
}
