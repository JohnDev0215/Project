package com.amazon.avod.media.service.cache;

import com.amazon.avod.config.MemoryCacheConfig;
import com.amazon.avod.media.service.PlaybackResourcesValidator;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PlayerResourcesCacheBase<RQ, RS, RW> {
    protected final LoadingCache<RQ, Optional<RW>> mCache;
    protected final PlaybackResourcesValidator mValidator;

    protected interface PlayerResourcesLoader<RQ, RW> extends Function<RQ, Optional<RW>> {
    }

    PlayerResourcesCacheBase(@Nonnull LoadingCache<RQ, Optional<RW>> loadingCache, @Nonnull PlaybackResourcesValidator playbackResourcesValidator) {
        this.mCache = (LoadingCache) Preconditions.checkNotNull(loadingCache, "cache");
        this.mValidator = (PlaybackResourcesValidator) Preconditions.checkNotNull(playbackResourcesValidator, "validator");
    }

    @Nonnull
    public Optional<RW> getIfPresent(@Nonnull RQ rq) {
        Preconditions.checkNotNull(rq, "request");
        return (Optional) MoreObjects.firstNonNull(this.mCache.getIfPresent(rq), Optional.absent());
    }

    @Nonnull
    public Optional<RW> getResponse(@Nonnull RQ rq) {
        Preconditions.checkNotNull(rq, "request");
        return this.mCache.getUnchecked(rq);
    }

    public void clear() {
        this.mCache.invalidateAll();
    }

    static class NoopLocalPlayerResources<RQ, RW> implements PlayerResourcesLoader<RQ, RW> {
        NoopLocalPlayerResources() {
        }

        @Override // com.google.common.base.Function
        @Nonnull
        public Optional<RW> apply(RQ rq) {
            return Optional.absent();
        }
    }

    static class PlayerResourcesCacheConfig implements MemoryCacheConfig {
        private final int mCacheSize;
        private final long mMillisExpire;

        @Override // com.amazon.avod.config.MemoryCacheConfig
        public int getConcurrencyLevel() {
            return 1;
        }

        PlayerResourcesCacheConfig(int i, long j) {
            this.mCacheSize = i;
            this.mMillisExpire = j;
        }

        @Override // com.amazon.avod.config.MemoryCacheConfig
        public int getCacheLimit() {
            return this.mCacheSize;
        }

        @Override // com.amazon.avod.config.MemoryCacheConfig
        public long getTimeBeforeExpiring() {
            return this.mMillisExpire;
        }

        @Override // com.amazon.avod.config.MemoryCacheConfig
        public TimeUnit getTimeUnit() {
            return TimeUnit.MILLISECONDS;
        }
    }
}
