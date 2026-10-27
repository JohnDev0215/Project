package com.amazon.music.voice;

import com.amazon.alexa.voice.core.AudioPlayer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackEventProcessor implements AudioPlayer.Callback {
    private final List<AudioPlayer.Callback> callbacks = new ArrayList();

    public void addCallback(AudioPlayer.Callback callback) {
        this.callbacks.add(callback);
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
    public void onPlaybackIdle() {
        Iterator<AudioPlayer.Callback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackIdle();
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
    public void onPlaybackReady() {
        Iterator<AudioPlayer.Callback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackReady();
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
    public void onPlaybackStarted() {
        Iterator<AudioPlayer.Callback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackStarted();
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
    public void onPlaybackPaused() {
        Iterator<AudioPlayer.Callback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackPaused();
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
    public void onPlaybackEnded() {
        Iterator<AudioPlayer.Callback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackEnded();
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
    public void onPlaybackError(Throwable th) {
        Iterator<AudioPlayer.Callback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlaybackError(th);
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer.Callback
    public void onPlayerBuffering() {
        Iterator<AudioPlayer.Callback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onPlayerBuffering();
        }
    }
}
