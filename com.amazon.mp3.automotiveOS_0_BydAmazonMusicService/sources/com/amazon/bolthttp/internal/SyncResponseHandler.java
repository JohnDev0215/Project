package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.Response;
import com.google.common.base.Preconditions;
import java.util.concurrent.CountDownLatch;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class SyncResponseHandler<T> implements ResponseHandler<T> {
    private final CountDownLatch mLatch = new CountDownLatch(1);
    private volatile Response<T> mResponse;
    private final ExecutionTracker mTracker;

    public SyncResponseHandler(@Nonnull ExecutionTracker executionTracker) {
        this.mTracker = (ExecutionTracker) Preconditions.checkNotNull(executionTracker, "tracker");
    }

    @Override // com.amazon.bolthttp.internal.ResponseHandler
    public void handleResponse(@Nonnull Response<T> response) {
        this.mResponse = (Response) Preconditions.checkNotNull(response, "response");
        this.mTracker.notifyOnCompletion(response);
        this.mLatch.countDown();
    }

    @Nonnull
    public Response<T> awaitResponse() {
        awaitUninterrupted(this.mLatch);
        return this.mResponse;
    }

    private static void awaitUninterrupted(@Nonnull CountDownLatch countDownLatch) {
        boolean z = false;
        while (true) {
            try {
                countDownLatch.await();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }
}
