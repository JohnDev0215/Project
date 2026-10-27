package com.amazon.podcast.media.playback;

import android.os.Bundle;

/* JADX INFO: loaded from: classes5.dex */
public interface PodcastPlaybackController {
    void clear();

    void clearNotifications();

    void duck();

    void fastForward();

    int fastForwardOffsetMS();

    boolean isDucking();

    boolean isPlaying();

    void pause();

    void play();

    void playMedia(final String mediaId, final boolean isDownloaded);

    void playMedia(final String mediaId, final boolean isDownloaded, final long playOffset);

    void prepareMedia(final String uriString, final Bundle extras, final String mediaId, final String audioStreamFormat);

    void removeCallback();

    void rewind();

    int rewindOffsetMS();

    void seekTo(final long position);

    void setMediaMetadata(final Bundle extras);

    void setPlaybackSpeed(final float speed);

    void setVolume(final float volume);

    void stop();

    void unDuck();

    void updateMetadataKeyDuration();
}
