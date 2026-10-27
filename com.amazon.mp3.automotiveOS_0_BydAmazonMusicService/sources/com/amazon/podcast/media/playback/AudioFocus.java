package com.amazon.podcast.media.playback;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import com.amazon.podcast.Podcast;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class AudioFocus implements AudioManager.OnAudioFocusChangeListener {
    private AudioFocusRequest audioFocusRequest;
    private final AudioManager audioManager;
    private String currentMediaId;
    private MediaSessionCompat mediaSession;
    private final PodcastPlaybackController player;
    private boolean resumeOnGain;
    private final Logger logger = LoggerFactory.getLogger("AudioFocus");
    private final Object focusLock = new Object();
    private boolean playbackDelayed = false;

    public AudioFocus(final PodcastPlaybackController player, final AudioManager audioManager, final MediaSessionCompat mediaSession) {
        this.player = player;
        this.audioManager = audioManager;
        this.mediaSession = mediaSession;
    }

    public void playAfterAudioFocusGain(final String mediaId, final Bundle bundle) {
        if (Podcast.isHarleyEnabled()) {
            return;
        }
        if (StringUtils.isNotBlank(mediaId) && bundle != null && bundle.containsKey("playOffsetMS")) {
            this.player.seekTo(bundle.getLong("playOffsetMS"));
        }
        Playback playback = Podcast.getPlayback();
        boolean zIsDownloaded = playback.isDownloaded(mediaId);
        int i = 1;
        if (Build.VERSION.SDK_INT >= 26) {
            AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(i).setFocusGain(1).setAudioAttributes(new AudioAttributes.Builder().setContentType(2).setUsage(1).build()).setOnAudioFocusChangeListener(this).setAcceptsDelayedFocusGain(true).build();
            this.audioFocusRequest = audioFocusRequestBuild;
            int iRequestAudioFocus = this.audioManager.requestAudioFocus(audioFocusRequestBuild);
            this.logger.debug("playAfterAudioFocusGain: result {}", Integer.valueOf(iRequestAudioFocus));
            synchronized (this.focusLock) {
                try {
                    if (iRequestAudioFocus == 0) {
                        this.logger.debug("playAfterAudioFocusGain: API level 26 or higher. Audio focus request failed. Do nothing.");
                    } else if (iRequestAudioFocus == 1) {
                        this.logger.debug("playAfterAudioFocusGain: API level 26 or higher. Audio focus request granted. Playing AudioMedia.");
                        if (mediaId == null) {
                            this.player.play();
                        } else {
                            this.player.playMedia(mediaId, zIsDownloaded);
                        }
                        playback.setHasAudioFocus(true);
                    } else if (iRequestAudioFocus == 2) {
                        this.logger.debug("playAfterAudioFocusGain: API level 26 or higher. Audio focus request delayed.");
                        this.playbackDelayed = true;
                        this.currentMediaId = mediaId;
                    }
                } finally {
                }
            }
            return;
        }
        int iRequestAudioFocus2 = this.audioManager.requestAudioFocus(this, 3, 1);
        this.logger.debug("playAfterAudioFocusGain: result {}", Integer.valueOf(iRequestAudioFocus2));
        if (iRequestAudioFocus2 == 1) {
            this.logger.debug("playAfterAudioFocusGain: API level less than 26. Audio focus request delayed.");
            if (mediaId == null) {
                this.player.play();
            } else {
                this.player.playMedia(mediaId, zIsDownloaded);
            }
            playback.setHasAudioFocus(true);
            return;
        }
        this.logger.debug("playAfterAudioFocusGain: API level less than 26. Audio focus request failed. Do nothing.");
    }

    public void abandonAudioFocus() {
        if (Build.VERSION.SDK_INT >= 26) {
            AudioFocusRequest audioFocusRequest = this.audioFocusRequest;
            if (audioFocusRequest == null) {
                return;
            } else {
                this.audioManager.abandonAudioFocusRequest(audioFocusRequest);
            }
        } else {
            this.audioManager.abandonAudioFocus(this);
        }
        Podcast.getPlayback().setHasAudioFocus(false);
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(final int focusChange) {
        if (Podcast.isHarleyEnabled()) {
            return;
        }
        this.logger.debug("onAudioFocusChange: focusChange: {}", Integer.valueOf(focusChange));
        Playback playback = Podcast.getPlayback();
        if (1 == focusChange) {
            playback.setHasAudioFocus(true);
            this.mediaSession.setActive(true);
            if (this.player.isDucking()) {
                this.logger.debug("onAudioFocusChange to AUDIOFOCUS_GAIN: unducking");
                this.player.unDuck();
            }
            if (this.resumeOnGain && !this.player.isPlaying()) {
                this.player.play();
                this.logger.debug("onAudioFocusChange to AUDIOFOCUS_GAIN: staring playback");
                return;
            }
            if (this.playbackDelayed) {
                synchronized (this.focusLock) {
                    this.playbackDelayed = false;
                }
                String str = this.currentMediaId;
                if (str != null) {
                    this.player.playMedia(this.currentMediaId, playback.isDownloaded(str));
                } else {
                    this.player.play();
                }
            }
            this.logger.debug("onAudioFocusChange to AUDIOFOCUS_GAIN: staring playback");
            return;
        }
        if (-1 == focusChange) {
            playback.setHasAudioFocus(false);
            synchronized (this.focusLock) {
                this.playbackDelayed = false;
            }
            this.resumeOnGain = false;
            this.player.pause();
            this.mediaSession.setActive(false);
            this.logger.debug("onAudioFocusChange to AUDIOFOCUS_LOSS: pausing playback");
            return;
        }
        if (-2 == focusChange) {
            synchronized (this.focusLock) {
                this.playbackDelayed = false;
            }
            this.resumeOnGain = this.player.isPlaying();
            this.player.pause();
            this.logger.debug("onAudioFocusChange to AUDIOFOCUS_LOSS_TRANSIENT: pausing playback");
            return;
        }
        if (-3 == focusChange) {
            synchronized (this.focusLock) {
                this.playbackDelayed = false;
            }
            this.resumeOnGain = this.player.isPlaying();
            this.player.duck();
            this.logger.debug("onAudioFocusChange to AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: ducking audio");
        }
    }
}
