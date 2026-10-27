package com.amazon.avod.events.batch;

/* JADX INFO: loaded from: classes2.dex */
public interface BatchedEventConfig {
    int getMaxBatchChildCount();

    float getMaxBatchDataSizeKb();
}
