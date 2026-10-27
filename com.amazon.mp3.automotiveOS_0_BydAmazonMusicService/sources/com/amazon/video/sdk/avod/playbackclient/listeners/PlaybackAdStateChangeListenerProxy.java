package com.amazon.video.sdk.avod.playbackclient.listeners;

import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.playback.PlaybackAdStateChangeListener;
import java.util.Iterator;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class PlaybackAdStateChangeListenerProxy extends SetListenerProxy<PlaybackAdStateChangeListener> implements PlaybackAdStateChangeListener {
    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackLoaded() {
        Iterator<PlaybackAdStateChangeListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onAdPlaybackLoaded();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackPaused() {
        Iterator<PlaybackAdStateChangeListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onAdPlaybackPaused();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackPlaying() {
        Iterator<PlaybackAdStateChangeListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onAdPlaybackPlaying();
        }
    }

    @Override // com.amazon.avod.playback.PlaybackAdStateChangeListener
    public void onAdPlaybackCompleted() {
        Iterator<PlaybackAdStateChangeListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onAdPlaybackCompleted();
        }
    }
}
