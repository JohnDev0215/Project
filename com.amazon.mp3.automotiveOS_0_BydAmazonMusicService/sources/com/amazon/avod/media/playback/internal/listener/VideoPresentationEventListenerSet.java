package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.media.playback.PlaybackDataSource;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class VideoPresentationEventListenerSet extends EventListenerSet<VideoPresentationEventListener> implements VideoPresentationEventListener {
    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onCompletion(VideoPresentation videoPresentation) {
        Set<VideoPresentationEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<VideoPresentationEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onCompletion(videoPresentation);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onError(VideoPresentation videoPresentation, MediaErrorCode mediaErrorCode) {
        Set<VideoPresentationEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<VideoPresentationEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onError(videoPresentation, mediaErrorCode);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onPrepared(VideoPresentation videoPresentation, PlaybackDataSource playbackDataSource) {
        Set<VideoPresentationEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<VideoPresentationEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onPrepared(videoPresentation, playbackDataSource);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onStarted(@Nonnull VideoPresentation videoPresentation, @Nonnull PlaybackDataSource playbackDataSource) {
        Set<VideoPresentationEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<VideoPresentationEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onStarted(videoPresentation, playbackDataSource);
            }
        }
    }

    @Override // com.amazon.avod.media.playback.VideoPresentationEventListener
    public void onTerminated() {
        Set<VideoPresentationEventListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<VideoPresentationEventListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onTerminated();
            }
        }
    }
}
