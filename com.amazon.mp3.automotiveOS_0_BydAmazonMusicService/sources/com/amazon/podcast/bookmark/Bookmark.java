package com.amazon.podcast.bookmark;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "bookmarks")
public class Bookmark {

    @NonNull
    @ColumnInfo(name = "authors")
    private String authors;

    @NonNull
    @ColumnInfo(name = MediaTrack.ROLE_DESCRIPTION)
    private String description;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = ContextMappingConstants.IMAGE)
    private String image;

    @NonNull
    @ColumnInfo(name = "podcastId")
    private String podcastId;

    @NonNull
    @ColumnInfo(name = "podcastImage")
    private String podcastImage;

    @NonNull
    @ColumnInfo(name = "podcastTitle")
    private String podcastTitle;

    @ColumnInfo(name = "progressMilliseconds")
    private long progressMilliseconds;

    @NonNull
    @ColumnInfo(name = "publishDate")
    private String publishDate;

    @NonNull
    @ColumnInfo(name = "seasonNumber")
    private String seasonNumber;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "totalDurationMilliseconds")
    private long totalDurationMilliseconds;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public Bookmark(@NonNull final String id, @NonNull final String podcastId, @NonNull final String podcastTitle, @NonNull final String title, @NonNull final String image, @NonNull final String podcastImage, @NonNull final String publishDate, @NonNull final String description, @NonNull final String authors, final String seasonNumber, final long totalDurationMilliseconds, final long progressMilliseconds, final long updatedTime) {
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
        this.totalDurationMilliseconds = totalDurationMilliseconds;
        this.progressMilliseconds = progressMilliseconds;
        this.updatedTime = updatedTime;
    }

    public Bookmark(@NonNull final Bookmark bookmark, @NonNull final String id, @NonNull final String podcastId, @NonNull final String podcastTitle, @NonNull final String title, @NonNull final String image, @NonNull final String podcastImage, @NonNull final String publishDate, @NonNull final String description, @NonNull final String authors, final String seasonNumber) {
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
        this.totalDurationMilliseconds = bookmark.getTotalDurationMilliseconds();
        this.progressMilliseconds = bookmark.getProgressMilliseconds();
        this.updatedTime = bookmark.getUpdatedTime();
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
        sb2.append("getPodcastTitle : ");
        sb2.append(getPodcastTitle());
        sb.append(sb2.toString());
        sb.append("getTitle : " + getTitle());
        sb.append("getImage : " + getImage());
        sb.append("getPodcastImage : " + getPodcastImage());
        sb.append("getPublishDate : " + getPublishDate());
        sb.append("getDescription : " + getDescription());
        sb.append("getAuthors : " + getAuthors());
        sb.append("getTotalDurationMilliseconds : " + getTotalDurationMilliseconds());
        sb.append("getProgressMilliseconds : " + getProgressMilliseconds());
        sb.append("getUpdatedTime : " + getUpdatedTime());
        return sb.toString();
    }
}
