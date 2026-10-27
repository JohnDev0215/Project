package com.amazon.podcast.follow;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "follow_operations")
public class FollowOperations {

    @NonNull
    @ColumnInfo(name = MediaTrack.ROLE_DESCRIPTION)
    private String description;

    @NonNull
    @ColumnInfo(name = "elementId")
    private String elementId;

    @ColumnInfo(name = "followed")
    private boolean followed;

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
    @ColumnInfo(name = "publisher")
    private String publisher;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public FollowOperations(@NonNull final String operationId, @NonNull final String elementId, @NonNull final String title, @NonNull final String image, @NonNull final String description, @NonNull final String publisher, final boolean followed, final long updatedTime, final boolean isProcessed) {
        this.operationId = operationId;
        this.elementId = elementId;
        this.title = title;
        this.image = image;
        this.description = description;
        this.publisher = publisher;
        this.followed = followed;
        this.updatedTime = updatedTime;
        this.isProcessed = isProcessed;
    }

    @NonNull
    public String getOperationId() {
        return this.operationId;
    }

    @NonNull
    public String getElementId() {
        return this.elementId;
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

    public boolean isProcessed() {
        return this.isProcessed;
    }
}
