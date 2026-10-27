package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class RetriableContentEventError extends ContentEventErrorBase {
    private final long mElapsedNanos;
    private final int mRetryCount;

    @Override // com.amazon.avod.content.event.ContentEventErrorBase
    public boolean isRetriable() {
        return true;
    }

    public RetriableContentEventError(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull ContentException contentException, int i) {
        this((PlayableContent) Preconditions.checkNotNull(playableContent, "content"), (ContentSessionType) Preconditions.checkNotNull(contentSessionType, "sessionType"), (ContentException) Preconditions.checkNotNull(contentException, "error"), null, null, i, null);
    }

    public RetriableContentEventError(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull ContentException contentException, @Nullable ContentUrl contentUrl, @Nullable SmoothStreamingURI smoothStreamingURI, int i, @Nullable Map<String, List<String>> map) {
        this((PlayableContent) Preconditions.checkNotNull(playableContent, "content"), (ContentSessionType) Preconditions.checkNotNull(contentSessionType, "sessionType"), (ContentException) Preconditions.checkNotNull(contentException, "error"), contentUrl, smoothStreamingURI, i, map, 0L);
    }

    public RetriableContentEventError(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull ContentException contentException, @Nullable ContentUrl contentUrl, @Nullable SmoothStreamingURI smoothStreamingURI, int i, @Nullable Map<String, List<String>> map, long j) {
        super(playableContent, contentSessionType, contentException, contentUrl, smoothStreamingURI, map);
        this.mRetryCount = i;
        this.mElapsedNanos = j;
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    public long getElapsedNanos() {
        return this.mElapsedNanos;
    }
}
