package com.amazon.avod.playback;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackSessionBufferEventListener {
    void onBufferEnd(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext);

    void onBufferProgress(float f);

    void onBufferStart(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext, @Nullable BufferingAnalysis bufferingAnalysis);
}
