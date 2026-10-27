package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackStateEventListener;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackStateEventListenerSet extends EventListenerSet<PlaybackStateEventListener> implements PlaybackStateEventListener {
    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStart(PlaybackEventContext playbackEventContext) {
        Set<PlaybackStateEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackStateEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onStart(playbackEventContext);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStop(PlaybackEventContext playbackEventContext) {
        Set<PlaybackStateEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackStateEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onStop(playbackEventContext);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onResume(PlaybackEventContext playbackEventContext) {
        Set<PlaybackStateEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackStateEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onResume(playbackEventContext);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onPause(PlaybackEventContext playbackEventContext) {
        Set<PlaybackStateEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackStateEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onPause(playbackEventContext);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekStart(TimeSpan timeSpan, PlaybackEventContext playbackEventContext) {
        Set<PlaybackStateEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackStateEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onSeekStart(timeSpan, playbackEventContext);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekEnd(PlaybackEventContext playbackEventContext) {
        Set<PlaybackStateEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackStateEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onSeekEnd(playbackEventContext);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onRestartSuccess() {
        Set<PlaybackStateEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackStateEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onRestartSuccess();
            }
        }
    }
}
