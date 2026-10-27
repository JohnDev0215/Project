package com.amazon.avod.provider.module.impl.cache;

import android.os.SystemClock;
import com.amazon.avod.config.MemoryCacheConfig;
import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class CacheFactory {
    private static final Ticker REAL_ELAPSED_TIME_NANO_TICKER = new Ticker() { // from class: com.amazon.avod.provider.module.impl.cache.CacheFactory.1
        @Override // com.google.common.base.Ticker
        public long read() {
            return TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
        }
    };

    public static <T, U> LoadingCache<T, U> generateMemoryCache(MemoryCacheConfig memoryCacheConfig, CacheLoader<T, U> cacheLoader) {
        int cacheLimit = memoryCacheConfig.getCacheLimit();
        return (LoadingCache<T, U>) CacheBuilder.newBuilder().maximumSize(cacheLimit).concurrencyLevel(memoryCacheConfig.getConcurrencyLevel()).expireAfterWrite(memoryCacheConfig.getTimeBeforeExpiring(), memoryCacheConfig.getTimeUnit()).ticker(REAL_ELAPSED_TIME_NANO_TICKER).build(cacheLoader);
    }
}
