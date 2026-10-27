package com.amazon.mp3.voice;

import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.music.station.Rating;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class AlexaTrack extends MusicTrack {
    private StreamInfo mAudioItemStreamInfo;
    private boolean mCanRateTrack;
    private boolean mIsFirstTrackInQueue;
    private Map<String, String> mMetricsContext;
    private String mMpqsTrackId;
    private Rating mRating;

    public AlexaTrack(MusicTrack track) {
        super(track);
    }

    public String getMpqsTrackId() {
        return this.mMpqsTrackId;
    }

    public void setMpqsTrackId(final String mpqsTrackId) {
        this.mMpqsTrackId = mpqsTrackId;
    }

    public boolean canRateTrack() {
        return this.mCanRateTrack;
    }

    public void setCanRateTrack(final boolean canRateTrack) {
        this.mCanRateTrack = canRateTrack;
    }

    public Rating getRating() {
        return this.mRating;
    }

    public void setRating(final Rating rating) {
        this.mRating = rating;
    }

    public boolean isFirstTrackInQueue() {
        return this.mIsFirstTrackInQueue;
    }

    public void setIsFirstTrackInQueue(final boolean isFirstTrackInQueue) {
        this.mIsFirstTrackInQueue = isFirstTrackInQueue;
    }

    public Map<String, String> getMetricsContext() {
        return this.mMetricsContext;
    }

    public void setMetricsContext(final Map<String, String> metricsContext) {
        this.mMetricsContext = metricsContext;
    }

    public StreamInfo getAudioItemStreamInfo() {
        return this.mAudioItemStreamInfo;
    }

    public void setAudioItemStreamInfo(final StreamInfo streamInfo) {
        this.mAudioItemStreamInfo = streamInfo;
    }

    @Override // com.amazon.mp3.library.item.MusicTrack
    public String toString() {
        return "AlexaTrack{mMpqsTrackId='" + this.mMpqsTrackId + "', mCanRateTrack=" + this.mCanRateTrack + ", mRating=" + this.mRating + ", mIsFirstTrackInQueue=" + this.mIsFirstTrackInQueue + ", mMetricsContext=" + this.mMetricsContext + ", mAudioItemStreamToken=" + this.mAudioItemStreamInfo.getStreamToken() + ", mAudioItemStreamUrl='" + this.mAudioItemStreamInfo.getStreamUrl() + "'}";
    }
}
