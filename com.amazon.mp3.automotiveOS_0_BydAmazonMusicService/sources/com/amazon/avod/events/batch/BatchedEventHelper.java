package com.amazon.avod.events.batch;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BatchedEventHelper {
    private final BatchedEventConfig mBatchedEventConfig;

    public BatchedEventHelper(@Nonnull BatchedEventConfig batchedEventConfig) {
        this.mBatchedEventConfig = (BatchedEventConfig) Preconditions.checkNotNull(batchedEventConfig);
    }
}
