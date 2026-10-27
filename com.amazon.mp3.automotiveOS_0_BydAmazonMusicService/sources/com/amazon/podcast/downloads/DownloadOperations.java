package com.amazon.podcast.downloads;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "download_operations")
public class DownloadOperations {

    @NonNull
    @ColumnInfo(name = "authors")
    private String authors;

    @NonNull
    @ColumnInfo(name = MediaTrack.ROLE_DESCRIPTION)
    private String description;

    @ColumnInfo(name = "durationMilliseconds")
    private long durationMilliseconds;

    @NonNull
    @ColumnInfo(name = "explicit")
    private boolean explicit;

    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = ContextMappingConstants.IMAGE)
    private String image;

    @NonNull
    @ColumnInfo(name = "localAudioUrl")
    private String localAudioUri;

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

    @ColumnInfo(name = "isProcessed")
    private boolean processed;

    @NonNull
    @ColumnInfo(name = "publishDate")
    private String publishDate;

    @NonNull
    @ColumnInfo(name = "seasonNumber")
    private String seasonNumber;

    @NonNull
    @ColumnInfo(name = "state")
    private String state;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public DownloadOperations(@NonNull final String operationId, @NonNull final String id, @NonNull final String podcastId, @NonNull final String podcastTitle, @NonNull final String title, @NonNull final String image, @NonNull final String podcastImage, @NonNull final String publishDate, @NonNull final String description, @NonNull final String authors, @NonNull final String seasonNumber, final long durationMilliseconds, final boolean explicit, @NonNull final String state, @NonNull final String localAudioUri, final long updatedTime, final boolean processed) {
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
        this.durationMilliseconds = durationMilliseconds;
        this.explicit = explicit;
        this.state = state;
        this.localAudioUri = localAudioUri;
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

    public long getDurationMilliseconds() {
        return this.durationMilliseconds;
    }

    public boolean isExplicit() {
        return this.explicit;
    }

    @NonNull
    public String getState() {
        return this.state;
    }

    @NonNull
    public String getLocalAudioUri() {
        return this.localAudioUri;
    }

    public long getUpdatedTime() {
        return this.updatedTime;
    }

    public boolean isProcessed() {
        return this.processed;
    }
}
