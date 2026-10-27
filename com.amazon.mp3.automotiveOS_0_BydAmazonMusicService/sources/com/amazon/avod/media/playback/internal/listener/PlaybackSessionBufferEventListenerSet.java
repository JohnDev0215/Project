package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.playback.BufferingAnalysis;
import com.amazon.avod.playback.PlaybackBufferEventType;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackSessionBufferEventListener;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackSessionBufferEventListenerSet extends EventListenerSet<PlaybackSessionBufferEventListener> implements PlaybackSessionBufferEventListener {
    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferStart(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext, @Nullable BufferingAnalysis bufferingAnalysis) {
        Set<PlaybackSessionBufferEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackSessionBufferEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onBufferStart(playbackBufferEventType, playbackEventContext, bufferingAnalysis);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    @Deprecated
    public void onBufferProgress(float f) {
        Set<PlaybackSessionBufferEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackSessionBufferEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onBufferProgress(f);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackSessionBufferEventListener
    public void onBufferEnd(PlaybackBufferEventType playbackBufferEventType, PlaybackEventContext playbackEventContext) {
        Set<PlaybackSessionBufferEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackSessionBufferEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onBufferEnd(playbackBufferEventType, playbackEventContext);
            }
        }
    }
}
