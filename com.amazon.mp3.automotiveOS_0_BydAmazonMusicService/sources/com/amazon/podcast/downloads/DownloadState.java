package com.amazon.podcast.downloads;

import com.google.android.gms.cast.MediaError;

/* JADX INFO: loaded from: classes5.dex */
public enum DownloadState {
    QUEUED("QUEUED"),
    IN_PROGRESS("IN_PROGRESS"),
    DOWNLOADED("DOWNLOADED"),
    ERROR(MediaError.ERROR_TYPE_ERROR);

    private String value;

    DownloadState(final String value) {
        this.value = value;
    }

    public static DownloadState lookup(final String state) {
        for (DownloadState downloadState : values()) {
            if (downloadState.value.equals(state)) {
                return downloadState;
            }
        }
        return null;
    }

    public String getValue() {
        return this.value;
    }
}
