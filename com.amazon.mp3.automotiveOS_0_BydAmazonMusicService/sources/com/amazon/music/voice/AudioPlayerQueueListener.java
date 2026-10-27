package com.amazon.music.voice;

import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioPlayDirective;
import com.amazon.music.voice.MusicAudioPlayerPlugin;

/* JADX INFO: loaded from: classes4.dex */
public class AudioPlayerQueueListener extends MusicAudioPlayerPlugin.SimpleAudioPlayerListener {
    private AlexaTrackReceiver trackReceiver;

    public void setAlexaTrackReceiver(AlexaTrackReceiver alexaTrackReceiver) {
        this.trackReceiver = alexaTrackReceiver;
    }

    @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.SimpleAudioPlayerListener, com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
    public void onAudioItemEnqueued(AudioPlayDirective.AudioItem audioItem) {
        if (audioItem == null || audioItem.getStream() == null || audioItem.getStream().hasAttachedAudio()) {
            return;
        }
        this.trackReceiver.enqueueTrack(audioItem.getStream().getUrl(), audioItem.getStream().getToken());
    }

    public void clearQueue() {
        this.trackReceiver.clearQueue();
    }
}
