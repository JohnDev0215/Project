package com.amazon.mp3.download;

import android.net.Uri;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.ErrorReason;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.notification.NotificationInfo;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadItem {
    private DownloadState mDownloadState;
    private ErrorReason mErrorReason;
    private final boolean mIsAnyOwned;
    private final NotificationInfo mNotificationInfo;
    private final String mRequestId;
    private final RequestItem.RequestType mRequestType;
    private final int mSize;
    private final long mTimestamp;
    private final Uri mTracksUri;

    public DownloadItem(String requestId, RequestItem.RequestType requestType, Uri tracksUri, DownloadState downloadState, ErrorReason errorReason, NotificationInfo info, boolean isAnyOwned, int size, long timestamp) {
        this.mRequestId = requestId;
        this.mRequestType = requestType;
        this.mDownloadState = downloadState;
        this.mNotificationInfo = info;
        this.mTracksUri = tracksUri;
        this.mIsAnyOwned = isAnyOwned;
        this.mSize = size;
        this.mTimestamp = timestamp;
        this.mErrorReason = errorReason;
    }

    public String getRequestId() {
        return this.mRequestId;
    }

    public DownloadState getDownloadState() {
        return this.mDownloadState;
    }

    public void setDownloadState(final DownloadState downloadState) {
        this.mDownloadState = downloadState;
    }

    public NotificationInfo getNotificationInfo() {
        return this.mNotificationInfo;
    }

    public boolean isAnyOwned() {
        return this.mIsAnyOwned;
    }

    public int getSize() {
        return this.mSize;
    }

    public Uri getTracksUri() {
        return this.mTracksUri;
    }

    public ErrorReason getErrorReason() {
        return this.mErrorReason;
    }

    public void setErrorReason(ErrorReason errorReason) {
        this.mErrorReason = errorReason;
    }
}
