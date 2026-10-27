package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackQualityChangedEventListener;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackQualityChangedEventListenerSet extends EventListenerSet<PlaybackQualityChangedEventListener> implements PlaybackQualityChangedEventListener {
    @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
    public void onVideoQualityChanged(int i, VideoResolution videoResolution, VideoResolution[] videoResolutionArr, PlaybackEventContext playbackEventContext) {
        Set<PlaybackQualityChangedEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackQualityChangedEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onVideoQualityChanged(i, videoResolution, videoResolutionArr, playbackEventContext);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
    public void onAudioQualityChanged(int i, AudioFormat audioFormat, PlaybackEventContext playbackEventContext) {
        Set<PlaybackQualityChangedEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackQualityChangedEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onAudioQualityChanged(i, audioFormat, playbackEventContext);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackQualityChangedEventListener
    public void onDynamicRangeChange(boolean z) {
        Set<PlaybackQualityChangedEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackQualityChangedEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onDynamicRangeChange(z);
            }
        }
    }
}
