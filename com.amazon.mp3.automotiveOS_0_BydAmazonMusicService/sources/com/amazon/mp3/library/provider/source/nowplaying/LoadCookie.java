package com.amazon.mp3.library.provider.source.nowplaying;

import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;

/* JADX INFO: loaded from: classes3.dex */
public class LoadCookie {
    private final int mCollectionPosition;
    private final PlaybackMetricInformation mPlaybackMetricInformation;
    private final int mPlaybackPosition;
    private final boolean mRefreshCollection;
    private final int mRequestId;
    private final boolean mRestoreFromCasting;
    private final boolean mShuffle;

    public LoadCookie(final int requestId, final int playbackPosition, final int collectionPosition, final PlaybackMetricInformation playbackMetricInformation, final boolean shuffle, final boolean restoreFromCasting, final boolean refreshCollection) {
        this.mRequestId = requestId;
        this.mPlaybackPosition = playbackPosition;
        this.mCollectionPosition = collectionPosition;
        this.mPlaybackMetricInformation = playbackMetricInformation;
        this.mShuffle = shuffle;
        this.mRestoreFromCasting = restoreFromCasting;
        this.mRefreshCollection = refreshCollection;
    }

    public int getRequestId() {
        return this.mRequestId;
    }

    public int getPlaybackPosition() {
        return this.mPlaybackPosition;
    }

    public int getCollectionPosition() {
        return this.mCollectionPosition;
    }

    public boolean getShuffle() {
        return this.mShuffle;
    }

    public boolean shouldRestoreFromCasting() {
        return this.mRestoreFromCasting;
    }

    public boolean shouldRefreshCollection() {
        return this.mRefreshCollection;
    }

    public String toString() {
        return "LoadCookie{mRequestId=" + this.mRequestId + ", mPlaybackPosition=" + this.mPlaybackPosition + ", mCollectionPosition=" + this.mCollectionPosition + ", mPlaybackMetricInformation=" + this.mPlaybackMetricInformation + ", mShuffle=" + this.mShuffle + ", mRestoreFromCasting=" + this.mRestoreFromCasting + ", mRefreshCollection=" + this.mRefreshCollection + '}';
    }
}
