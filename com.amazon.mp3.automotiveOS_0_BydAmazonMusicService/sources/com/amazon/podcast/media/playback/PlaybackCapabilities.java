package com.amazon.podcast.media.playback;

/* JADX INFO: loaded from: classes5.dex */
public class PlaybackCapabilities {
    private final Playback playback;
    private PlaybackSpeed previousPlaybackSpeed;
    private boolean isFastForwardEnabled = true;
    private boolean isLockScreenEnabled = true;
    private boolean isMiniPlayerEnabled = true;
    private boolean isPlaybackSpeedEnabled = true;
    private boolean isRewindEnabled = true;
    private boolean isSleepTimerEnabled = true;
    private boolean isScrubberEnabled = true;
    private boolean isTranscriptEnabled = true;

    public PlaybackCapabilities(final Playback playback) {
        this.playback = playback;
        this.previousPlaybackSpeed = playback.getPlaybackSpeed();
    }

    public void setFastForwardEnabled(final boolean isFastForward) {
        if (this.isFastForwardEnabled == isFastForward) {
            return;
        }
        this.isFastForwardEnabled = isFastForward;
    }

    public boolean isFastForwardEnabled() {
        return this.isFastForwardEnabled;
    }

    public void setLockScreenEnabled(final boolean isLockScreen) {
        if (this.isLockScreenEnabled == isLockScreen) {
            return;
        }
        if (isLockScreen) {
            this.isLockScreenEnabled = true;
        } else {
            this.playback.clearLockScreen();
            this.isLockScreenEnabled = false;
        }
    }

    public boolean isLockScreenEnabled() {
        return this.isLockScreenEnabled;
    }

    public void setMiniPlayerEnabled(final boolean isMiniplayer) {
        if (this.isMiniPlayerEnabled == isMiniplayer) {
            return;
        }
        this.isMiniPlayerEnabled = isMiniplayer;
    }

    public boolean isMiniPlayerEnabled() {
        return this.isMiniPlayerEnabled;
    }

    public void setPlaybackSpeedEnabled(final boolean isPlaybackSpeed) {
        if (this.isPlaybackSpeedEnabled == isPlaybackSpeed) {
            return;
        }
        if (isPlaybackSpeed) {
            this.isPlaybackSpeedEnabled = true;
            PlaybackSpeed playbackSpeed = this.playback.getPlaybackSpeed();
            PlaybackSpeed playbackSpeed2 = this.previousPlaybackSpeed;
            if (playbackSpeed == playbackSpeed2) {
                return;
            }
            this.playback.setPlaybackSpeed(playbackSpeed2);
            return;
        }
        PlaybackSpeed playbackSpeed3 = this.playback.getPlaybackSpeed();
        this.previousPlaybackSpeed = playbackSpeed3;
        PlaybackSpeed playbackSpeed4 = PlaybackSpeed.ONE;
        if (!(playbackSpeed3 == playbackSpeed4)) {
            this.playback.setPlaybackSpeed(playbackSpeed4);
        }
        this.isPlaybackSpeedEnabled = false;
    }

    public boolean isPlaybackSpeedEnabled() {
        return this.isPlaybackSpeedEnabled;
    }

    public void setRewindEnabled(final boolean isRewind) {
        if (this.isRewindEnabled == isRewind) {
            return;
        }
        this.isRewindEnabled = isRewind;
    }

    public boolean isRewindEnabled() {
        return this.isRewindEnabled;
    }

    public void setSleepTimerEnabled(final boolean isSleepTimer) {
        if (this.isSleepTimerEnabled == isSleepTimer) {
            return;
        }
        if (isSleepTimer) {
            this.isSleepTimerEnabled = true;
            return;
        }
        if (this.playback.getSleepTimerRemainingDuration() > 0) {
            this.playback.stopSleepTimer();
        }
        this.isSleepTimerEnabled = false;
    }

    public boolean isSleepTimerEnabled() {
        return this.isPlaybackSpeedEnabled;
    }

    public void setScrubberEnabled(final boolean isScrubber) {
        if (this.isScrubberEnabled == isScrubber) {
            return;
        }
        this.isScrubberEnabled = isScrubber;
    }

    public boolean isScrubberEnabled() {
        return this.isScrubberEnabled;
    }

    public void setTranscriptEnabled(final boolean isTranscript) {
        if (this.isTranscriptEnabled == isTranscript) {
            return;
        }
        this.isTranscriptEnabled = isTranscript;
    }

    public boolean isTranscriptEnabled() {
        return this.isTranscriptEnabled;
    }
}
