package com.amazon.bolthttp;

import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ThreadingModel {

    public enum ExecutorType {
        OVERLORD,
        NETWORK,
        CPU,
        DISK
    }

    @Nonnull
    ThreadPoolExecutor getExecutor(@Nonnull ExecutorType executorType);
}
