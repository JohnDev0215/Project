package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.ThreadingModel;
import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
final class Overlord extends ExecutorCommander<Affinity$OverlordAffinity, OverlordSharedState> {
    Overlord(@Nonnull OverlordSharedState overlordSharedState) {
        super(overlordSharedState, ThreadingModel.ExecutorType.OVERLORD);
    }

    @Override // com.amazon.bolthttp.internal.ExecutorCommander
    ThreadPoolExecutor getExecutor(ThreadingModel.ExecutorType executorType) {
        ThreadPoolExecutor executor = super.getExecutor(executorType);
        if (executor.getCorePoolSize() == 1 && executor.getMaximumPoolSize() == 1) {
            return executor;
        }
        throw new IllegalStateException("Overlord executor has a thread pool greater than 1.");
    }
}
