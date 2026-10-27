package com.amazon.podcast.media.playback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.cast.Casting;
import com.amazon.podcast.cast.ChromecastMediaProvider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class MediaSessionCallback extends MediaSessionCompat.Callback {
    private final AudioFocus audioFocus;
    private final boolean isHarleyEnabled;
    private final Logger logger = LoggerFactory.getLogger("PodcastMediaSessionCallback");
    private final Playback playback;
    private PlaybackController playbackController;
    private final PodcastPlaybackController player;

    public MediaSessionCallback(final Playback playback, final PodcastPlaybackController player, final AudioFocus audioFocus) {
        this.playbackController = null;
        this.player = player;
        this.playback = playback;
        this.audioFocus = audioFocus;
        boolean zIsHarleyEnabled = Podcast.isHarleyEnabled();
        this.isHarleyEnabled = zIsHarleyEnabled;
        if (zIsHarleyEnabled) {
            this.playbackController = com.amazon.music.media.playback.Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onPrepareFromMediaId(final String mediaId, final Bundle extras) {
        ChromecastMediaProvider.setTrackLoadedOnChromecast(false);
        String string = extras.getString("audioUri");
        String string2 = extras.getString("audioStreamFormat");
        this.logger.debug("onPrepareFromMediaID is called with uri: {}, mediaId: {}", string, mediaId);
        this.player.prepareMedia(string, extras, mediaId, string2);
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onPlayFromMediaId(final String mediaId, final Bundle extras) {
        this.logger.debug("onPlayFromMediaId is called with mediaId: {}", mediaId);
        if (Casting.isCasting()) {
            if (ChromecastMediaProvider.isTrackLoadedOnChromecast()) {
                this.logger.debug("onPlayFromMedia is called on Chromecast");
                ChromecastMediaProvider.startPlaybackOnChromecast();
            } else {
                this.logger.debug("onPlayFromMedia is called on Chromecast. LoadingTrack....");
                ChromecastMediaProvider.loadTrackOnChromecast();
            }
        }
        if (!(this.playbackController == null)) {
            boolean zIsDownloaded = this.playback.isDownloaded(mediaId);
            if (StringUtils.isNotBlank(mediaId) && extras != null && extras.containsKey("playOffsetMS")) {
                this.player.playMedia(mediaId, zIsDownloaded, extras.getLong("playOffsetMS"));
                return;
            } else {
                this.player.playMedia(mediaId, zIsDownloaded);
                return;
            }
        }
        this.audioFocus.playAfterAudioFocusGain(mediaId, extras);
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onSeekTo(long pos) {
        this.logger.debug("onSeekTo is called. Seeking to position: {}", Long.valueOf(pos));
        if (Casting.isCasting() && ChromecastMediaProvider.isTrackLoadedOnChromecast()) {
            this.logger.debug("onSeek is called on Chromecast");
            ChromecastMediaProvider.seekToOnChromecast(pos);
        }
        this.player.seekTo(pos);
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onPause() {
        this.logger.debug("onPause is called");
        if (Casting.isCasting() && ChromecastMediaProvider.isTrackLoadedOnChromecast() && ChromecastMediaProvider.isPlaybackStartedOnChromecast()) {
            this.logger.debug("onPause is called on Chromecast");
            ChromecastMediaProvider.pausePlaybackOnChromecast();
        }
        this.player.pause();
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onPlay() {
        this.logger.debug("onPlay is called");
        if (Casting.isCasting()) {
            if (ChromecastMediaProvider.isTrackLoadedOnChromecast()) {
                ChromecastMediaProvider.startPlaybackOnChromecast();
            } else {
                ChromecastMediaProvider.loadTrackOnChromecast();
            }
        }
        if (!(this.playbackController == null)) {
            this.player.play();
        } else {
            this.audioFocus.playAfterAudioFocusGain(null, null);
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onFastForward() {
        this.logger.debug("onFastForward is called");
        long playbackDuration = this.playback.getPlaybackDuration();
        long playbackPosition = this.playback.getPlaybackPosition();
        long jFastForwardOffsetMS = this.player.fastForwardOffsetMS();
        if (Casting.isCasting() && ChromecastMediaProvider.isTrackLoadedOnChromecast()) {
            this.logger.debug("onFastForward is called on Chromecast");
            long j = playbackPosition + jFastForwardOffsetMS;
            if (j >= playbackDuration) {
                return;
            } else {
                ChromecastMediaProvider.seekToOnChromecast(j);
            }
        }
        if (playbackPosition + jFastForwardOffsetMS >= playbackDuration) {
            onSkipToNext();
        } else {
            this.player.fastForward();
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onRewind() {
        this.logger.debug("onRewind is called");
        if (Casting.isCasting() && ChromecastMediaProvider.isTrackLoadedOnChromecast()) {
            this.logger.debug("onRewind is called on Chromecast");
            long playbackPosition = this.playback.getPlaybackPosition() - ((long) this.player.rewindOffsetMS());
            if (playbackPosition <= 0) {
                return;
            } else {
                ChromecastMediaProvider.seekToOnChromecast(playbackPosition);
            }
        }
        this.player.rewind();
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onStop() {
        this.logger.debug("onStop is called");
        if (Casting.isCasting() && ChromecastMediaProvider.isTrackLoadedOnChromecast()) {
            this.logger.debug("onStop is called on Chromecast");
            ChromecastMediaProvider.stopOnChromeCast();
        }
        this.player.stop();
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onSkipToNext() {
        this.logger.debug("onSkipToNext called");
        this.playback.next();
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onSkipToPrevious() {
        this.logger.debug("onSkipToPrevious called");
        this.playback.previous();
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public boolean onMediaButtonEvent(Intent mediaButtonEvent) {
        KeyEvent keyEvent = (KeyEvent) mediaButtonEvent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        this.logger.debug("onMediaButtonEvent:   " + keyEvent.getKeyCode());
        if (keyEvent.getKeyCode() == 87) {
            this.playback.next();
            return true;
        }
        if (keyEvent.getKeyCode() != 88) {
            return false;
        }
        this.playback.previous();
        return true;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public void onCustomAction(final String action, final Bundle extras) {
        this.logger.debug("onCustomAction is called with action: {}", action);
        if ("setPlaybackSpeed".equals(action)) {
            this.player.setPlaybackSpeed(Float.valueOf(extras.getFloat("speed")).floatValue());
            return;
        }
        if ("setMediaMetadata".equals(action)) {
            this.player.setMediaMetadata(extras);
            return;
        }
        if ("setMetadataKeyDuration".equals(action)) {
            this.player.updateMetadataKeyDuration();
            return;
        }
        if ("clearPlayback".equals(action)) {
            this.player.clear();
        } else if ("stopPlayback".equals(action)) {
            this.player.stop();
        } else if ("clearLockScreen".equals(action)) {
            this.player.clearNotifications();
        }
    }
}
