package com.amazon.video.sdk.avod.playbackclient.listeners;

import com.amazon.avod.listeners.SetListenerProxy;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.PlaybackEventContext;
import com.amazon.avod.playback.PlaybackStateEventListener;
import java.util.Iterator;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
@ThreadSafe
public class PlaybackStateEventListenerProxy extends SetListenerProxy<PlaybackStateEventListener> implements PlaybackStateEventListener {
    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStart(PlaybackEventContext playbackEventContext) {
        Iterator<PlaybackStateEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onStart(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onStop(PlaybackEventContext playbackEventContext) {
        Iterator<PlaybackStateEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onStop(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onResume(PlaybackEventContext playbackEventContext) {
        Iterator<PlaybackStateEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onResume(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onPause(PlaybackEventContext playbackEventContext) {
        Iterator<PlaybackStateEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onPause(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekStart(TimeSpan timeSpan, PlaybackEventContext playbackEventContext) {
        Iterator<PlaybackStateEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onSeekStart(timeSpan, playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onSeekEnd(PlaybackEventContext playbackEventContext) {
        Iterator<PlaybackStateEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onSeekEnd(playbackEventContext);
        }
    }

    @Override // com.amazon.avod.playback.PlaybackStateEventListener
    public void onRestartSuccess() {
        Iterator<PlaybackStateEventListener> it = getListeners().iterator();
        while (it.hasNext()) {
            it.next().onRestartSuccess();
        }
    }
}
