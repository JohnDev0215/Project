package com.amazon.avod.playback.event;

import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.playback.VideoSpecification;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CacheStatusEvent {
    private final CacheRecord mCacheRecord;
    private final VideoSpecification mVideoSpec;

    public CacheStatusEvent(@Nonnull VideoSpecification videoSpecification, @Nullable CacheRecord cacheRecord) {
        this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification);
        this.mCacheRecord = cacheRecord;
    }

    @Nullable
    public CacheRecord getRecord() {
        return this.mCacheRecord;
    }

    @Nonnull
    public VideoSpecification getVideoSpec() {
        return this.mVideoSpec;
    }
}
