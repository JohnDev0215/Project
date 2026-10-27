package com.amazon.mp3.library.service.sync.blacklist.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/* JADX INFO: loaded from: classes3.dex */
@Entity(tableName = "local_uris")
public class LocalUri {

    @ColumnInfo(name = "local_uri")
    private String localUri;

    @PrimaryKey(autoGenerate = true)
    private long uid;

    public LocalUri(String localUri) {
        this.localUri = localUri;
    }

    public long getUid() {
        return this.uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getLocalUri() {
        return this.localUri;
    }
}
