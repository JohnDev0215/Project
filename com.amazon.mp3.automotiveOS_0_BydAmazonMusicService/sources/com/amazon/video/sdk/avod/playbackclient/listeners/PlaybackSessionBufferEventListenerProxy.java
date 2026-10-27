package com.amazon.video.sdk.avod.playbackclient.listeners;

import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import java.util.Iterator;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class PlaybackSessionBufferEventListenerProxy extends SetListenerProxy<PlaybackSessionBufferEventListener> implements PlaybackSessionBufferEventListener {
    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferStart(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext, @Nullable BufferingAnalysis bufferingAnalysis) {
        Iterator<PlaybackSessionBufferEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onBufferStart(playbackBufferEventType, playbackEventContext, bufferingAnalysis);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferProgress(float f) {
        Iterator<PlaybackSessionBufferEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onBufferProgress(f);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferEnd(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext) {
        Iterator<PlaybackSessionBufferEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onBufferEnd(playbackBufferEventType, playbackEventContext);
        }
    }
}
