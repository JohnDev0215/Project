package com.amazon.avod.content.event;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class StaleManifestErrorEvent extends ContentEventErrorBase {
    private final int mConsecutiveStaleManifestCount;
    private final long mtimeElapsedSinceLastKnownGoodManifestNanos;

    @Override // com.amazon.avod.content.event.ContentEventErrorBase
    public boolean isRetriable() {
        return true;
    }

    public StaleManifestErrorEvent(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull ContentException contentException, int i, long j) {
        super((PlayableContent) Preconditions.checkNotNull(playableContent, "content"), (ContentSessionType) Preconditions.checkNotNull(contentSessionType, "sessionType"), (ContentException) Preconditions.checkNotNull(contentException, "error"), null, null, null);
        this.mtimeElapsedSinceLastKnownGoodManifestNanos = j;
        this.mConsecutiveStaleManifestCount = i;
    }

    public long getElapsedTimeInNanos() {
        return this.mtimeElapsedSinceLastKnownGoodManifestNanos;
    }

    public int getConsecutiveStaleManifestCount() {
        return this.mConsecutiveStaleManifestCount;
    }
}
