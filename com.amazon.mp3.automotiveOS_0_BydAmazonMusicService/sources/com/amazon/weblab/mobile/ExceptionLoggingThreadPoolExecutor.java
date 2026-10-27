package com.amazon.weblab.mobile;

import android.util.Log;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
class ExceptionLoggingThreadPoolExecutor extends ThreadPoolExecutor {
    private final String mClientId;

    public ExceptionLoggingThreadPoolExecutor(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, String str) {
        super(i, i2, j, timeUnit, blockingQueue);
        this.mClientId = str;
    }

    @Override // java.util.concurrent.ThreadPoolExecutor
    protected void afterExecute(Runnable runnable, Throwable e) {
        String message = null;
        if (e != null) {
            message = e.getMessage();
        } else if (runnable instanceof Future) {
            Future future = (Future) runnable;
            if (future.isDone() && !future.isCancelled()) {
                try {
                    future.get();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                } catch (CancellationException e2) {
                    e = e2;
                    message = e.getMessage();
                } catch (ExecutionException e3) {
                    e = e3.getCause();
                    if ((e instanceof RuntimeException) || (e instanceof Error)) {
                        message = e.getMessage();
                    }
                }
            }
        }
        if (message != null) {
            Log.e("MWAC", e.getClass().getSimpleName() + " message: " + message);
        }
    }
}
