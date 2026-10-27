package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.framework.error.MediaException;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackNeedsTerminationEvent {
    private final MediaException mMediaException;

    public PlaybackNeedsTerminationEvent(@Nonnull MediaException mediaException) {
        this.mMediaException = (MediaException) Preconditions.checkNotNull(mediaException, "mediaException");
    }

    @Nonnull
    public MediaException getCause() {
        return this.mMediaException;
    }
}
