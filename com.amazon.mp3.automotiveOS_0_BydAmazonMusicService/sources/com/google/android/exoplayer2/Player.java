package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;

/* JADX INFO: loaded from: classes6.dex */
public interface Player {

    public interface EventListener {
        void onLoadingChanged(boolean z);

        void onPlaybackParametersChanged(PlaybackParameters playbackParameters);

        void onPlayerError(ExoPlaybackException exoPlaybackException);

        void onPlayerStateChanged(boolean z, int i);

        void onPositionDiscontinuity();

        void onRepeatModeChanged(int i);

        void onTimelineChanged(Timeline timeline, Object obj);

        void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray);
    }

    void addListener(EventListener eventListener);

    int getBufferedPercentage();

    long getBufferedPosition();

    int getCurrentAdGroupIndex();

    int getCurrentAdIndexInAdGroup();

    int getCurrentPeriodIndex();

    long getCurrentPosition();

    int getCurrentWindowIndex();

    long getDuration();

    boolean getPlayWhenReady();

    PlaybackParameters getPlaybackParameters();

    int getPlaybackState();

    int getRendererCount();

    int getRepeatMode();

    void release();

    void removeListener(EventListener eventListener);

    void seekTo(long j);

    void setPlayWhenReady(boolean z);

    void setPlaybackParameters(PlaybackParameters playbackParameters);

    void setRepeatMode(int i);

    void stop();
}
