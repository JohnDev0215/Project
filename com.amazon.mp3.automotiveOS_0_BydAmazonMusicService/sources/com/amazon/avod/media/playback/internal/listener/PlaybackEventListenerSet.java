package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.playback.PlaybackEventListener;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class PlaybackEventListenerSet extends EventListenerSet<PlaybackEventListener> implements PlaybackEventListener {
    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onCompletion() {
        Set<PlaybackEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onCompletion();
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onError(MediaErrorCode mediaErrorCode) {
        Set<PlaybackEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onError(mediaErrorCode);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onPrepared(PlaybackDataSource playbackDataSource) {
        Set<PlaybackEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onPrepared(playbackDataSource);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onStarted(@Nonnull PlaybackDataSource playbackDataSource) {
        Set<PlaybackEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onStarted(playbackDataSource);
            }
        }
    }

    @Override // com.amazon.avod.playback.PlaybackEventListener
    public void onTerminated() {
        Set<PlaybackEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<PlaybackEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onTerminated();
            }
        }
    }
}
