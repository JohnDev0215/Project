package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RetriableManifestDownloadErrorEvent extends ContentEventErrorBase {
    private final long mElapsedNanos;
    private final long mLastSuccessfulManifestDownloadTimeNanos;
    private final int mRetryCount;

    @Override // com.amazon.avod.content.event.ContentEventErrorBase
    public boolean isRetriable() {
        return true;
    }

    public RetriableManifestDownloadErrorEvent(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull ContentException contentException, int i, long j, long j2) {
        super((PlayableContent) Preconditions.checkNotNull(playableContent, "content"), (ContentSessionType) Preconditions.checkNotNull(contentSessionType, "sessionType"), (ContentException) Preconditions.checkNotNull(contentException, "error"), null, null, null);
        this.mRetryCount = i;
        this.mElapsedNanos = j;
        this.mLastSuccessfulManifestDownloadTimeNanos = j2;
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    public long getElapsedNanos() {
        return this.mElapsedNanos;
    }

    public long getLastSuccessfulManifestDownloadTimeNanos() {
        return this.mLastSuccessfulManifestDownloadTimeNanos;
    }
}
