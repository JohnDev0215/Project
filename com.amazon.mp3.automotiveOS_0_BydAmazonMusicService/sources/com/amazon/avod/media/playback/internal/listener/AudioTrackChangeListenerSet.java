package com.amazon.avod.media.playback.internal.listener;

import com.amazon.avod.media.framework.event.EventListenerSet;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.google.common.base.Optional;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class AudioTrackChangeListenerSet extends EventListenerSet<AudioTrackChangeListener> implements AudioTrackChangeListener {
    @Override // com.amazon.avod.playback.AudioTrackChangeListener
    public void onAudioTrackChangeStarted(@Nonnull Optional<String> optional, @Nonnull Optional<String> optional2) {
        Set<AudioTrackChangeListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<AudioTrackChangeListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onAudioTrackChangeStarted(optional, optional2);
            }
        }
    }

    @Override // com.amazon.avod.playback.AudioTrackChangeListener
    public void onAudioTrackChangeCompleted(@Nonnull AudioTrackChangeListener.Status status) {
        Set<AudioTrackChangeListener> listeners = getListeners();
        synchronized (listeners) {
            Iterator<AudioTrackChangeListener> it = listeners.iterator();
            while (it.hasNext()) {
                it.next().onAudioTrackChangeCompleted(status);
            }
        }
    }
}
