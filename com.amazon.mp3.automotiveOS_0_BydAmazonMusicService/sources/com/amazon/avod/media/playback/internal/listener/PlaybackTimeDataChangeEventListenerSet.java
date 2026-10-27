package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.playback.PlaybackTimeDataEventListener;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackTimeDataChangeEventListenerSet extends EventListenerSet<PlaybackTimeDataEventListener> implements PlaybackTimeDataEventListener {
    @Override // com.amazon.avod.playback.PlaybackTimeDataEventListener
    public void onTimeDataChange(long j) {
        Set<PlaybackTimeDataEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackTimeDataEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onTimeDataChange(j);
            }
        }
    }
}
