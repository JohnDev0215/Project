package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackAdStateChangeListenerSet extends EventListenerSet<PlaybackAdStateChangeListener> implements PlaybackAdStateChangeListener {
    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackLoaded() {
        Set<PlaybackAdStateChangeListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackAdStateChangeListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onAdPlaybackLoaded();
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackPaused() {
        Set<PlaybackAdStateChangeListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackAdStateChangeListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onAdPlaybackPaused();
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackPlaying() {
        Set<PlaybackAdStateChangeListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackAdStateChangeListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onAdPlaybackPlaying();
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackCompleted() {
        Set<PlaybackAdStateChangeListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackAdStateChangeListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onAdPlaybackCompleted();
            }
        }
    }
}
