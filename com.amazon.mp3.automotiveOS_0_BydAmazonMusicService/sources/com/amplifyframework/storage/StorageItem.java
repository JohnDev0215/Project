package com.amplifyframework.storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public final class StorageItem {
    private final String eTag;
    private final String key;
    private final Date lastModified;
    private final Object pluginResults;
    private final long size;

    public StorageItem(@NonNull String str, long j, @NonNull Date date, @NonNull String str2, @Nullable Object obj) {
        this.key = str;
        this.size = j;
        this.lastModified = date;
        this.eTag = str2;
        this.pluginResults = obj;
    }

    @NonNull
    public String getKey() {
        return this.key;
    }

    public long getSize() {
        return this.size;
    }

    @NonNull
    public Date getLastModified() {
        return this.lastModified;
    }

    @NonNull
    public String getETag() {
        return this.eTag;
    }

    @Nullable
    public Object getPluginResults() {
        return this.pluginResults;
    }

    @NonNull
    public String toString() {
        return "StorageItem{key='" + this.key + "', size=" + this.size + ", lastModified=" + this.lastModified.toString() + ", eTag='" + this.eTag + "', pluginResults=" + this.pluginResults + '}';
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorageItem)) {
            return false;
        }
        StorageItem storageItem = (StorageItem) obj;
        if (ObjectsCompat.equals(this.key, storageItem.key) && this.size == storageItem.size && ObjectsCompat.equals(this.lastModified, storageItem.lastModified) && ObjectsCompat.equals(this.eTag, storageItem.eTag)) {
            return ObjectsCompat.equals(this.pluginResults, storageItem.pluginResults);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = ((((((this.key.hashCode() * 31) + ((int) this.size)) * 31) + this.lastModified.hashCode()) * 31) + this.eTag.hashCode()) * 31;
        Object obj = this.pluginResults;
        return iHashCode + (obj != null ? obj.hashCode() : 0);
    }
}
