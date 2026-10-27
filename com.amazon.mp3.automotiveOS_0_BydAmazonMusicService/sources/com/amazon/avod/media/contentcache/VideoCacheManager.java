package com.amazon.avod.media.contentcache;

import com.amazon.avod.media.contentcache.internal.LiveCache;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.pmet.WhisperCacheMetrics$CacheEviction;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoCacheManager {
    void cacheTitle(CacheRequest cacheRequest);

    void cacheTitleList(ImmutableList<CacheRequest> immutableList);

    void clearCache();

    void evict(@Nonnull VideoSpecification videoSpecification, @Nonnull WhisperCacheMetrics$CacheEviction whisperCacheMetrics$CacheEviction, @Nullable String str);

    @Nonnull
    LiveCache getLiveCache();

    @Nullable
    CacheRecord queryFor(@Nonnull VideoSpecification videoSpecification);

    void setCachingIsEnabled(boolean z);
}
