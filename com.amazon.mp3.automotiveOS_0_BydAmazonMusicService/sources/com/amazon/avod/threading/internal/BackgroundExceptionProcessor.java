package com.amazon.avod.threading.internal;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BackgroundExceptionProcessor {
    public static void handleBackgroundExceptionIfNecessary(@Nonnull Runnable runnable) {
        if (runnable instanceof Future) {
            rethrowRuntimeExceptionFromFutureRunnable(runnable);
        }
    }

    static void rethrowRuntimeExceptionFromFutureRunnable(@Nonnull Runnable runnable) {
        try {
            Future future = (Future) runnable;
            if (future.isDone()) {
                future.get();
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        } catch (CancellationException unused2) {
        } catch (ExecutionException e) {
            e = e;
            if (e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof CancellationException) {
                return;
            }
            if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            }
            if (e instanceof Error) {
                throw ((Error) e);
            }
        }
    }
}
