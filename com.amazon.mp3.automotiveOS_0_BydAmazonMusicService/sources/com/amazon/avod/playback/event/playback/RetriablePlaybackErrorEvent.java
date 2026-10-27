package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;

/* JADX INFO: loaded from: classes2.dex */
public class RetriablePlaybackErrorEvent extends PlaybackErrorEvent {
    @Override // com.amazon.avod.playback.event.playback.PlaybackErrorEvent
    public boolean isRetriable() {
        return true;
    }

    public RetriablePlaybackErrorEvent(TimeSpan timeSpan, MediaException mediaException) {
        super(timeSpan, mediaException, null, 0);
    }
}
