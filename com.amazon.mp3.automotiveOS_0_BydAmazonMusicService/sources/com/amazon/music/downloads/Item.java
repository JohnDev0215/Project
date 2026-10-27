package com.amazon.music.downloads;

import com.amazon.music.downloads.notification.NotificationInfo;
import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
public class Item {
    private final String mAsin;
    private final String mDestinationDirectory;
    private String mDestinationUri;
    private final boolean mIsBackground;
    private final boolean mIsOwned;
    private final String mLuid;
    private final NotificationInfo mNotificationInfo;
    private final Integer mOriginalDownloadState;
    protected float mPercentageDownloaded;
    private final PriorityInfo mPriorityInfo;
    protected ErrorReason mErrorReason = ErrorReason.UNKNOWN;
    protected DownloadState mDownloadState = DownloadState.QUEUED;

    public Item(String str, String str2, boolean z, String str3, String str4, NotificationInfo notificationInfo, PriorityInfo priorityInfo, boolean z2, Integer num) {
        this.mAsin = str;
        this.mLuid = str2;
        this.mIsOwned = z;
        this.mDestinationUri = str3;
        this.mDestinationDirectory = str4;
        this.mNotificationInfo = notificationInfo;
        this.mPriorityInfo = priorityInfo;
        this.mIsBackground = z2;
        this.mOriginalDownloadState = num;
    }

    public void updateDestinationUriFileName(String str) {
        this.mDestinationUri = this.mDestinationDirectory + File.separator + str;
    }

    public String getAsin() {
        return this.mAsin;
    }

    public String getLuid() {
        return this.mLuid;
    }

    public boolean isOwned() {
        return this.mIsOwned;
    }

    public String getDestinationUri() {
        return this.mDestinationUri;
    }

    public String getDestinationDirectory() {
        return this.mDestinationDirectory;
    }

    public boolean isBackground() {
        return this.mIsBackground;
    }

    public NotificationInfo getNotificationInfo() {
        return this.mNotificationInfo;
    }

    public PriorityInfo getPriorityInfo() {
        return this.mPriorityInfo;
    }

    public DownloadState getDownloadState() {
        return this.mDownloadState;
    }

    public float getPercentageDownloaded() {
        return this.mPercentageDownloaded;
    }

    public ErrorReason getErrorReason() {
        return this.mErrorReason;
    }

    public Integer getOriginalDownloadState() {
        return this.mOriginalDownloadState;
    }

    public String toString() {
        return "Item{mAsin='" + this.mAsin + "', mLuid='" + this.mLuid + "', mIsOwned=" + this.mIsOwned + ", mPriorityInfo=" + this.mPriorityInfo + ", mIsBackground=" + this.mIsBackground + ", mDownloadState=" + this.mDownloadState + ", mOriginalDownloadState=" + this.mOriginalDownloadState + '}';
    }

    public static class Builder {
        private String mAsin;
        private String mDestinationDirectory;
        private String mDestinationUri;
        private boolean mIsBackground;
        private boolean mIsOwned;
        private String mLuid;
        private NotificationInfo mNotificationInfo;
        private Integer mOriginalDownloadState;
        private PriorityInfo mPriorityInfo;

        public Builder addAsin(String str) {
            this.mAsin = str;
            return this;
        }

        public Builder addLuid(String str) {
            this.mLuid = str;
            return this;
        }

        public Builder setIsOwned(boolean z) {
            this.mIsOwned = z;
            return this;
        }

        public Builder addDestinationUri(String str) {
            this.mDestinationUri = str;
            return this;
        }

        public Builder addDestinationDirectory(String str) {
            this.mDestinationDirectory = str;
            return this;
        }

        public Builder addNotificationInfo(NotificationInfo notificationInfo) {
            this.mNotificationInfo = notificationInfo;
            return this;
        }

        public Builder addPriorityInfo(PriorityInfo priorityInfo) {
            this.mPriorityInfo = priorityInfo;
            return this;
        }

        public Builder setIsBackground(boolean z) {
            this.mIsBackground = z;
            return this;
        }

        public Builder setOriginalDownloadState(Integer num) {
            this.mOriginalDownloadState = num;
            return this;
        }

        public Item build() {
            String str = this.mLuid;
            if (str == null && this.mIsOwned) {
                throw new IllegalArgumentException("Owned track downloads should specify a valid Luid");
            }
            String str2 = this.mAsin;
            if (str2 == null && !this.mIsOwned) {
                throw new IllegalArgumentException("Prime track downloads should specify a valid Asin");
            }
            String str3 = this.mDestinationUri;
            if (str3 == null) {
                throw new NullPointerException("Need to specify a valid destination URI to download the item to");
            }
            PriorityInfo priorityInfo = this.mPriorityInfo;
            if (priorityInfo == null) {
                throw new NullPointerException("Need to specify a Priority for the item");
            }
            String str4 = this.mDestinationDirectory;
            if (str4 == null) {
                throw new NullPointerException("Need to specify a valid destination directory to download the item to");
            }
            return new Item(str2, str, this.mIsOwned, str3, str4, this.mNotificationInfo, priorityInfo, this.mIsBackground, this.mOriginalDownloadState);
        }
    }
}
