package com.amazon.podcast.biteBookmark;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "bite_bookmark_operations")
public class BiteBookmarkOperations {

    @NonNull
    @ColumnInfo(name = "episodeId")
    private String episodeId;

    @NonNull
    @ColumnInfo
    private String id;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "operationId")
    private String operationId;

    @NonNull
    @ColumnInfo(name = "podcastId")
    private String podcastId;

    @ColumnInfo(name = "isProcessed")
    private boolean processed;

    @ColumnInfo(name = "progressMilliseconds")
    private long progressMilliseconds;

    @ColumnInfo(name = "totalDurationMilliseconds")
    private long totalDurationMilliseconds;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public BiteBookmarkOperations(@NonNull final String operationId, @NonNull final String id, @NonNull final String podcastId, @NonNull final String episodeId, final long totalDurationMilliseconds, final long progressMilliseconds, final long updatedTime, final boolean processed) {
        this.operationId = operationId;
        this.id = id;
        this.podcastId = podcastId;
        this.episodeId = episodeId;
        this.totalDurationMilliseconds = totalDurationMilliseconds;
        this.progressMilliseconds = progressMilliseconds;
        this.updatedTime = updatedTime;
        this.processed = processed;
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
    public String getPodcastId() {
        return this.podcastId;
    }

    @NonNull
    public String getEpisodeId() {
        return this.episodeId;
    }

    public long getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    public long getProgressMilliseconds() {
        return this.progressMilliseconds;
    }

    public long getUpdatedTime() {
        return this.updatedTime;
    }

    public boolean isProcessed() {
        return this.processed;
    }
}
