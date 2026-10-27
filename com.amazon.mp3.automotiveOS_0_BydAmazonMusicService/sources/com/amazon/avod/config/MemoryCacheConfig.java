package com.amazon.avod.config;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public interface MemoryCacheConfig {
    int getCacheLimit();

    int getConcurrencyLevel();

    long getTimeBeforeExpiring();

    TimeUnit getTimeUnit();
}
