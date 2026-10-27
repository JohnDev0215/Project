package com.amazon.podcast.categoryFollow;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "category_follow_operations")
public class CategoryFollowOperations {

    @NonNull
    @ColumnInfo(name = "elementId")
    private String elementId;

    @ColumnInfo(name = "followed")
    private boolean followed;

    @ColumnInfo(name = "isProcessed")
    private boolean isProcessed;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "operationId")
    private String operationId;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public CategoryFollowOperations(@NonNull final String operationId, @NonNull final String elementId, @NonNull final String title, final boolean followed, final long updatedTime, final boolean isProcessed) {
        this.operationId = operationId;
        this.elementId = elementId;
        this.title = title;
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
