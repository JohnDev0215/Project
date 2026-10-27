package com.amazon.video.sdk.avod.playbackclient.listeners;

import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.playback.AudioTrackChangeListener;
import com.google.common.base.Optional;
import java.util.Iterator;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class AudioTrackChangeListenerProxy extends SetListenerProxy<AudioTrackChangeListener> implements AudioTrackChangeListener {
    @Override // com.amazon.avod.playback.AudioTrackChangeListener
    public void onAudioTrackChangeStarted(Optional<String> optional, Optional<String> optional2) {
        Iterator<AudioTrackChangeListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onAudioTrackChangeStarted(optional, optional2);
        }
    }

    @Override // com.amazon.avod.playback.AudioTrackChangeListener
    public void onAudioTrackChangeCompleted(AudioTrackChangeListener.Status status) {
        Iterator<AudioTrackChangeListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onAudioTrackChangeCompleted(status);
        }
    }
}
