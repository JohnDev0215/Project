package com.amazon.podcast.follow;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "follows")
public class Follow {

    @NonNull
    @ColumnInfo(name = MediaTrack.ROLE_DESCRIPTION)
    private String description;

    @ColumnInfo(name = "followed")
    private boolean followed;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = ContextMappingConstants.IMAGE)
    private String image;

    @NonNull
    @ColumnInfo(name = "podcastShowVariantId")
    private String podcastShowVariantId;

    @NonNull
    @ColumnInfo(name = "publisher")
    private String publisher;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public Follow(@NonNull final String id, @NonNull final String title, @NonNull final String image, @NonNull final String description, @NonNull final String publisher, final boolean followed, final long updatedTime, @NonNull final String podcastShowVariantId) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.followed = followed;
        this.updatedTime = updatedTime;
        this.publisher = publisher;
        this.podcastShowVariantId = podcastShowVariantId;
    }

    public Follow(@NonNull final Follow follow, @NonNull final String id, @NonNull final String title, @NonNull final String image, @NonNull final String description, @NonNull final String publisher, @NonNull final String podcastShowVariantId) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
        this.publisher = publisher;
        this.followed = follow.isFollowed();
        this.updatedTime = follow.getUpdatedTime();
        this.podcastShowVariantId = podcastShowVariantId;
    }

    @NonNull
    public String getId() {
        return this.id;
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
    public String getDescription() {
        return this.description;
    }

    @NonNull
    public String getPublisher() {
        return this.publisher;
    }

    public boolean isFollowed() {
        return this.followed;
    }

    public long getUpdatedTime() {
        return this.updatedTime;
    }

    @NonNull
    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }
}
