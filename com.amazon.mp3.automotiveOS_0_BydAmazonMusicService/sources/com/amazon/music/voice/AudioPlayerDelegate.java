package com.amazon.music.voice;

import com.amazon.alexa.voice.core.AudioPlayer;
import com.amazon.alexa.voice.core.AudioSource;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.firebase.perf.plugin.util.AsmString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AudioPlayerDelegate implements AudioPlayer {
    private static final Logger LOG = LoggerFactory.getLogger(AudioPlayerDelegate.class.getSimpleName());
    private final PlaybackEventProcessor eventProcessor;
    private AlexaPlaybackController playbackController;
    private AlexaPlaystateProvider playstateProvider;
    private AlexaTrackReceiver trackReceiver;
    private AlexaVolumeController volumeController;

    public AudioPlayerDelegate(PlaybackEventProcessor playbackEventProcessor) {
        this.eventProcessor = playbackEventProcessor;
    }

    public void setAlexaTrackReceiver(AlexaTrackReceiver alexaTrackReceiver) {
        this.trackReceiver = alexaTrackReceiver;
    }

    public void setAlexaPlaystateProvider(AlexaPlaystateProvider alexaPlaystateProvider) {
        this.playstateProvider = alexaPlaystateProvider;
    }

    public void setAlexaPlaybackController(AlexaPlaybackController alexaPlaybackController) {
        this.playbackController = alexaPlaybackController;
    }

    public void setVolumeController(AlexaVolumeController alexaVolumeController) {
        this.volumeController = alexaVolumeController;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    @Deprecated
    public void prepare(String str) {
        LOG.info("Prepare url: " + str);
        prepare(str, "");
    }

    public void prepare(String str, String str2) {
        LOG.info("Prepare url: " + str + " token:" + str2);
        AlexaTrackReceiver alexaTrackReceiver = this.trackReceiver;
        if (alexaTrackReceiver != null) {
            alexaTrackReceiver.playTrack(str, str2);
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void prepare(AudioSource audioSource) {
        LOG.info("prepare audioSource");
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void play() {
        LOG.info(PageUriUtils.DO_PLAY_VALUE);
        AlexaPlaybackController alexaPlaybackController = this.playbackController;
        if (alexaPlaybackController != null) {
            alexaPlaybackController.resume();
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void pause() {
        LOG.info("pause");
        AlexaPlaybackController alexaPlaybackController = this.playbackController;
        if (alexaPlaybackController != null) {
            alexaPlaybackController.pause();
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void stop() {
        LOG.info(AsmString.METHOD_FIREBASE_PERF_TRACE_STOP);
        AlexaPlaybackController alexaPlaybackController = this.playbackController;
        if (alexaPlaybackController != null) {
            alexaPlaybackController.stop();
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void seek(long j) {
        LOG.info("seek");
        AlexaPlaybackController alexaPlaybackController = this.playbackController;
        if (alexaPlaybackController != null) {
            alexaPlaybackController.seek(j);
        }
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void setVolume(float f) {
        LOG.info("setVolume " + f);
        this.volumeController.setVolume(f);
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public float getVolume() {
        LOG.info("getVolume");
        return this.volumeController.getVolume();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public float getMinimumVolume() {
        return this.volumeController.getMinimumVolume();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public float getMaximumVolume() {
        return this.volumeController.getMaximumVolume();
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void fadeVolume(float f) {
        this.volumeController.fadeVolume(f);
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isIdle() {
        LOG.debug("isIdle");
        AlexaPlaystateProvider alexaPlaystateProvider = this.playstateProvider;
        if (alexaPlaystateProvider != null) {
            return alexaPlaystateProvider.isIdle();
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isPlaying() {
        LOG.debug("isPlaying");
        AlexaPlaystateProvider alexaPlaystateProvider = this.playstateProvider;
        if (alexaPlaystateProvider != null) {
            return alexaPlaystateProvider.isPlaying();
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isPaused() {
        LOG.debug("isPaused");
        AlexaPlaystateProvider alexaPlaystateProvider = this.playstateProvider;
        if (alexaPlaystateProvider != null) {
            return alexaPlaystateProvider.isPaused();
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isEnded() {
        LOG.debug("isEnded");
        AlexaPlaystateProvider alexaPlaystateProvider = this.playstateProvider;
        if (alexaPlaystateProvider != null) {
            return alexaPlaystateProvider.isEnded();
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public boolean isBuffering() {
        LOG.debug("isBuffering");
        AlexaPlaystateProvider alexaPlaystateProvider = this.playstateProvider;
        if (alexaPlaystateProvider != null) {
            return alexaPlaystateProvider.isBuffering();
        }
        return false;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public long getCurrentPosition() {
        LOG.debug("getCurrentPosition");
        AlexaPlaystateProvider alexaPlaystateProvider = this.playstateProvider;
        if (alexaPlaystateProvider != null) {
            return alexaPlaystateProvider.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public long getDuration() {
        LOG.debug("getDuration");
        AlexaPlaystateProvider alexaPlaystateProvider = this.playstateProvider;
        if (alexaPlaystateProvider != null) {
            return alexaPlaystateProvider.getDuration();
        }
        return 0L;
    }

    @Override // com.amazon.alexa.voice.core.AudioPlayer
    public void addCallback(AudioPlayer.Callback callback) {
        LOG.debug("addCallback");
        this.eventProcessor.addCallback(callback);
    }
}
