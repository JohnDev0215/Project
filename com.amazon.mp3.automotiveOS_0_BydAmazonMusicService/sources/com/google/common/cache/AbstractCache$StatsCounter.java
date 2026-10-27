package com.google.common.cache;

/* JADX INFO: loaded from: classes6.dex */
public interface AbstractCache$StatsCounter {
    void recordEviction();

    void recordHits(int i);

    void recordLoadException(long j);

    void recordLoadSuccess(long j);

    void recordMisses(int i);
}
