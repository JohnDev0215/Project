package com.amazon.podcast.biteBookmark;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "bite_bookmarks")
public class BiteBookmark {

    @NonNull
    @ColumnInfo(name = "episodeId")
    private String episodeId;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = "podcastId")
    private String podcastId;

    @ColumnInfo(name = "progressMilliseconds")
    private long progressMilliseconds;

    @ColumnInfo(name = "totalDurationMilliseconds")
    private long totalDurationMilliseconds;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public BiteBookmark(@NonNull final String id, @NonNull final String podcastId, @NonNull final String episodeId, final long totalDurationMilliseconds, final long progressMilliseconds, final long updatedTime) {
        this.id = id;
        this.podcastId = podcastId;
        this.episodeId = episodeId;
        this.totalDurationMilliseconds = totalDurationMilliseconds;
        this.progressMilliseconds = progressMilliseconds;
        this.updatedTime = updatedTime;
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

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id : " + getId() + " ");
        sb.append("getPodcastId : " + getPodcastId() + " ");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("getEpisodeId : ");
        sb2.append(getEpisodeId());
        sb.append(sb2.toString());
        sb.append("getTotalDurationMilliseconds : " + getTotalDurationMilliseconds());
        sb.append("getProgressMilliseconds : " + getProgressMilliseconds());
        sb.append("getUpdatedTime : " + getUpdatedTime());
        return sb.toString();
    }
}
