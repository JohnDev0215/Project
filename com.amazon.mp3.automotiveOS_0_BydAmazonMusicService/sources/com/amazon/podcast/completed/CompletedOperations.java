package com.amazon.podcast.completed;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "completed_operations")
public class CompletedOperations {

    @NonNull
    @ColumnInfo(name = "authors")
    private String authors;

    @ColumnInfo(name = "completed")
    private boolean completed;

    @NonNull
    @ColumnInfo(name = MediaTrack.ROLE_DESCRIPTION)
    private String description;

    @NonNull
    @ColumnInfo
    private String id;

    @NonNull
    @ColumnInfo(name = ContextMappingConstants.IMAGE)
    private String image;

    @ColumnInfo(name = "isProcessed")
    private boolean isProcessed;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "operationId")
    private String operationId;

    @NonNull
    @ColumnInfo(name = "podcastId")
    private String podcastId;

    @NonNull
    @ColumnInfo(name = "podcastImage")
    private String podcastImage;

    @NonNull
    @ColumnInfo(name = "podcastTitle")
    private String podcastTitle;

    @NonNull
    @ColumnInfo(name = "publishDate")
    private String publishDate;

    @NonNull
    @ColumnInfo(name = "seasonNumber")
    private String seasonNumber;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public CompletedOperations(@NonNull final String operationId, @NonNull final String id, @NonNull final String podcastId, @NonNull final String podcastTitle, @NonNull final String title, @NonNull final String image, @NonNull final String podcastImage, @NonNull final String publishDate, @NonNull final String description, @NonNull final String authors, @NonNull final String seasonNumber, final boolean completed, final long updatedTime, final boolean isProcessed) {
        this.operationId = operationId;
        this.id = id;
        this.podcastId = podcastId;
        this.podcastTitle = podcastTitle;
        this.title = title;
        this.image = image;
        this.podcastImage = podcastImage;
        this.publishDate = publishDate;
        this.description = description;
        this.authors = authors;
        this.seasonNumber = seasonNumber;
        this.completed = completed;
        this.updatedTime = updatedTime;
        this.isProcessed = isProcessed;
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
    public String getPodcastTitle() {
        return this.podcastTitle;
    }

    @NonNull
    public String getTitle() {
        return this.title;
    }

    @NonNull
    public String getImage() {
        return this.image;
    }

    @NonNull
    public String getPodcastImage() {
        return this.podcastImage;
    }

    @NonNull
    public String getPublishDate() {
        return this.publishDate;
    }

    @NonNull
    public String getDescription() {
        return this.description;
    }

    @NonNull
    public String getAuthors() {
        return this.authors;
    }

    @NonNull
    public String getSeasonNumber() {
        return this.seasonNumber;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public long getUpdatedTime() {
        return this.updatedTime;
    }

    public boolean isProcessed() {
        return this.isProcessed;
    }
}
