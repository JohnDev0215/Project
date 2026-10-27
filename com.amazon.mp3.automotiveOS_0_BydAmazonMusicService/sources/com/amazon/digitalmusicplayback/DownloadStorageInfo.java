package com.amazon.digitalmusicplayback;

/* JADX INFO: loaded from: classes2.dex */
public final class DownloadStorageInfo {
    final String location;
    final int numberOfTracks;
    final long size;

    public DownloadStorageInfo(long j, String str, int i) {
        this.size = j;
        this.location = str;
        this.numberOfTracks = i;
    }

    public long getSize() {
        return this.size;
    }

    public String getLocation() {
        return this.location;
    }

    public int getNumberOfTracks() {
        return this.numberOfTracks;
    }

    public String toString() {
        return "DownloadStorageInfo{size=" + this.size + ",location=" + this.location + ",numberOfTracks=" + this.numberOfTracks + "}";
    }
}
