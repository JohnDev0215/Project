package com.amazon.music.voice.playbackstate;

import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.alexa.voice.core.processor.superbowl.directives.AudioPlayDirective;
import com.amazon.alexa.voice.superbowl.VoicePlugin;
import com.amazon.music.voice.MusicAudioPlayerPlugin;
import com.amazon.music.voice.playbackstate.GUIPlaybackStateEvent;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackStateReporterPlugin extends VoicePlugin {
    private static final Logger LOG = LoggerFactory.getLogger(PlaybackStateReporterPlugin.class.getSimpleName());
    private PlayerStateProvider playerStateProvider;
    private boolean sentChangeReport = false;
    private AudioPlayer.SimpleCallback guiPlayerCallback = new AudioPlayer.SimpleCallback() { // from class: com.amazon.music.voice.playbackstate.PlaybackStateReporterPlugin.1
        @Override // com.amazon.alexa.voice.core.AudioPlayer.SimpleCallback, com.amazon.alexa.voice.core.AudioPlayer.Callback
        public void onPlaybackStarted() {
            super.onPlaybackStarted();
            PlaybackStateReporterPlugin.LOG.debug("PlaybackStateReporterPlugin detects start of GUI Playback");
            if (PlaybackStateReporterPlugin.this.playerStateProvider == null) {
                PlaybackStateReporterPlugin.LOG.error("PlaybackStateProvider is null");
                return;
            }
            if (PlaybackStateReporterPlugin.this.sentChangeReport) {
                PlaybackStateReporterPlugin.LOG.debug("Already sent change report for playbackStarted, omitting this event");
                return;
            }
            try {
                MediaMetadata media = PlaybackStateReporterPlugin.this.playerStateProvider.getMedia();
                if (media == null) {
                    PlaybackStateReporterPlugin.LOG.error("media is null");
                    return;
                }
                PlaybackStateReporterPlugin.LOG.debug("media: " + media.toJsonObject().toString());
                PlaybackStateReporterPlugin.this.submitEvent(new GUIPlaybackStateEvent.Builder().media(media).build());
                PlaybackStateReporterPlugin.this.sentChangeReport = true;
            } catch (JSONException e) {
                PlaybackStateReporterPlugin.LOG.error(e.getMessage(), (Throwable) e);
            }
        }
    };
    private MusicAudioPlayerPlugin.AudioPlayerListener alexaPlayerListener = new MusicAudioPlayerPlugin.SimpleAudioPlayerListener() { // from class: com.amazon.music.voice.playbackstate.PlaybackStateReporterPlugin.2
        @Override // com.amazon.music.voice.MusicAudioPlayerPlugin.SimpleAudioPlayerListener, com.amazon.music.voice.MusicAudioPlayerPlugin.AudioPlayerListener
        public void onAudioPlaybackStarted(AudioPlayDirective.AudioItem audioItem) {
            PlaybackStateReporterPlugin.this.sentChangeReport = false;
        }
    };

    public MusicAudioPlayerPlugin.AudioPlayerListener getAlexaPlayerListener() {
        return this.alexaPlayerListener;
    }

    public PlaybackStateReporterPlugin(PlayerStateProvider playerStateProvider) {
        this.playerStateProvider = playerStateProvider;
        playerStateProvider.add(this.guiPlayerCallback);
    }

    protected void submitEvent(GUIPlaybackStateEvent gUIPlaybackStateEvent) {
        postEvent(gUIPlaybackStateEvent);
    }
}
