package com.amazon.podcast.metrics.playback;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "playback_metric_operations")
public class PlaybackMetricOperations {

    @NonNull
    @ColumnInfo
    private String audioUri;

    @NonNull
    @ColumnInfo
    private float currentPlaybackSpeed;

    @NonNull
    @ColumnInfo
    private long currentProgressMilliseconds;

    @NonNull
    @ColumnInfo
    private String errorException;

    @NonNull
    @ColumnInfo
    private String id;

    @NonNull
    @ColumnInfo
    private String ingressSource;

    @NonNull
    @ColumnInfo
    private long initialPlaybackStartDelayMilliseconds;

    @NonNull
    @ColumnInfo
    private boolean isMediaDownloaded;

    @NonNull
    @ColumnInfo
    private String mediaCollectionId;

    @NonNull
    @ColumnInfo
    private String mediaCollectionType;

    @NonNull
    @ColumnInfo
    private String mediaId;

    @NonNull
    @ColumnInfo
    private String mediaSubTitle;

    @NonNull
    @ColumnInfo
    private String mediaTitle;

    @NonNull
    @ColumnInfo
    private String metricsPreset;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "operationId")
    private String operationId;

    @NonNull
    @ColumnInfo
    private String playbackEngineType;

    @NonNull
    @ColumnInfo
    private String playbackInstanceId;

    @NonNull
    @ColumnInfo
    private long playbackRequestedAtMillisecocnds;

    @NonNull
    @ColumnInfo
    private String playbackSignalType;

    @NonNull
    @ColumnInfo
    private long playbackStartOffsetMilliseconds;

    @NonNull
    @ColumnInfo
    private long playbackStartedAtMillisecocnds;

    @NonNull
    @ColumnInfo
    private boolean processed;

    @NonNull
    @ColumnInfo
    private int rebufferCount;

    @NonNull
    @ColumnInfo
    private long rebufferDurationMilliseconds;

    @NonNull
    @ColumnInfo
    private long updatedTime;

    public PlaybackMetricOperations(@NonNull final String operationId, @NonNull final String id, @NonNull final String mediaId, @NonNull final String mediaCollectionType, @NonNull final String playbackSignalType, @NonNull final String metricsPreset, final long playbackRequestedAtMillisecocnds, final long playbackStartedAtMillisecocnds, final boolean isMediaDownloaded, final long currentProgressMilliseconds, final float currentPlaybackSpeed, final long playbackStartOffsetMilliseconds, final long initialPlaybackStartDelayMilliseconds, final long rebufferDurationMilliseconds, final int rebufferCount, final long updatedTime, final String ingressSource, final String audioUri, final String mediaTitle, final String mediaCollectionId, final String mediaSubTitle, final String errorException, final boolean processed, final String playbackInstanceId, final String playbackEngineType) {
        this.operationId = operationId;
        this.id = id;
        this.mediaId = mediaId;
        this.mediaCollectionType = mediaCollectionType;
        this.playbackSignalType = playbackSignalType;
        this.metricsPreset = metricsPreset;
        this.playbackRequestedAtMillisecocnds = playbackRequestedAtMillisecocnds;
        this.playbackStartedAtMillisecocnds = playbackStartedAtMillisecocnds;
        this.isMediaDownloaded = isMediaDownloaded;
        this.currentProgressMilliseconds = currentProgressMilliseconds;
        this.currentPlaybackSpeed = currentPlaybackSpeed;
        this.playbackStartOffsetMilliseconds = playbackStartOffsetMilliseconds;
        this.initialPlaybackStartDelayMilliseconds = initialPlaybackStartDelayMilliseconds;
        this.rebufferDurationMilliseconds = rebufferDurationMilliseconds;
        this.rebufferCount = rebufferCount;
        this.updatedTime = updatedTime;
        this.ingressSource = ingressSource;
        this.audioUri = audioUri;
        this.errorException = errorException;
        this.mediaTitle = mediaTitle;
        this.mediaCollectionId = mediaCollectionId;
        this.mediaSubTitle = mediaSubTitle;
        this.processed = processed;
        this.playbackInstanceId = playbackInstanceId;
        this.playbackEngineType = playbackEngineType;
    }

    @NonNull
    public String getOperationId() {
        return this.operationId;
    }

    @NonNull
    public String getId() {
        return this.id;
    }

    @NonNull
    public String getMediaId() {
        return this.mediaId;
    }

    @NonNull
    public String getMediaCollectionType() {
        return this.mediaCollectionType;
    }

    @NonNull
    public String getPlaybackSignalType() {
        return this.playbackSignalType;
    }

    @NonNull
    public String getMetricsPreset() {
        return this.metricsPreset;
    }

    public long getPlaybackRequestedAtMillisecocnds() {
        return this.playbackRequestedAtMillisecocnds;
    }

    public long getPlaybackStartedAtMillisecocnds() {
        return this.playbackStartedAtMillisecocnds;
    }

    public boolean isMediaDownloaded() {
        return this.isMediaDownloaded;
    }

    public long getCurrentProgressMilliseconds() {
        return this.currentProgressMilliseconds;
    }

    public float getCurrentPlaybackSpeed() {
        return this.currentPlaybackSpeed;
    }

    public long getPlaybackStartOffsetMilliseconds() {
        return this.playbackStartOffsetMilliseconds;
    }

    public long getInitialPlaybackStartDelayMilliseconds() {
        return this.initialPlaybackStartDelayMilliseconds;
    }

    public long getRebufferDurationMilliseconds() {
        return this.rebufferDurationMilliseconds;
    }

    public int getRebufferCount() {
        return this.rebufferCount;
    }

    public long getUpdatedTime() {
        return this.updatedTime;
    }

    public String getIngressSource() {
        return this.ingressSource;
    }

    public String getAudioUri() {
        return this.audioUri;
    }

    public String getErrorException() {
        return this.errorException;
    }

    public String getMediaTitle() {
        return this.mediaTitle;
    }

    public String getMediaCollectionId() {
        return this.mediaCollectionId;
    }

    public String getMediaSubTitle() {
        return this.mediaSubTitle;
    }

    public boolean isProcessed() {
        return this.processed;
    }

    public String getPlaybackInstanceId() {
        return this.playbackInstanceId;
    }

    @NonNull
    public String getPlaybackEngineType() {
        return this.playbackEngineType;
    }
}
