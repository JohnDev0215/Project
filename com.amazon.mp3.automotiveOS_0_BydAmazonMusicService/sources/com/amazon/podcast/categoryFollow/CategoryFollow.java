package com.amazon.podcast.categoryFollow;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* JADX INFO: loaded from: classes5.dex */
@Entity(tableName = "category_follows")
public class CategoryFollow {

    @ColumnInfo(name = "followed")
    private boolean followed;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "updatedTime")
    private long updatedTime;

    public CategoryFollow(@NonNull final String id, @NonNull final String title, final boolean followed, final long updatedTime) {
        this.id = id;
        this.title = title;
        this.followed = followed;
        this.updatedTime = updatedTime;
    }

    @NonNull
    public String getId() {
        return this.id;
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
}
