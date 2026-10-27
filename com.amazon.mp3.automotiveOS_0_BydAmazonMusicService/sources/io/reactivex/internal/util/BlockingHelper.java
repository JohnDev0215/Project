package io.reactivex.internal.util;

import io.reactivex.internal.schedulers.NonBlockingThread;
import io.reactivex.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes7.dex */
public final class BlockingHelper {
    private BlockingHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static void verifyNonBlocking() {
        if (RxJavaPlugins.isFailOnNonBlockingScheduler()) {
            if ((Thread.currentThread() instanceof NonBlockingThread) || RxJavaPlugins.onBeforeBlocking()) {
                throw new IllegalStateException("Attempt to block on a Scheduler " + Thread.currentThread().getName() + " that doesn't support blocking operators as they may lead to deadlock");
            }
        }
    }
}
