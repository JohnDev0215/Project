package com.amazon.music.metrics.mts.event.definition.playback;

import com.amazon.music.metrics.util.StringUtil;
import com.amazon.music.platform.data.subscription.NoSubscriptionTracking;

/* JADX INFO: loaded from: classes4.dex */
public class TrackPlaybackInfo {
    private final String mAsin;
    private final String mCloudDriveObjectId;
    private String mContentSubscriptionMode;
    private String mEntityType;
    private String mSelectionSourceType;
    private String mSubContentSubscriptionMode;

    public TrackPlaybackInfo(String str, String str2, String str3) {
        if (StringUtil.isNullOrWhiteSpace(str) && StringUtil.isNullOrWhiteSpace(str2)) {
            this.mCloudDriveObjectId = "0";
            this.mAsin = "0";
            this.mContentSubscriptionMode = new NoSubscriptionTracking().contentSubscriptionMode();
            this.mSubContentSubscriptionMode = new NoSubscriptionTracking().subContentSubscriptionMode();
            return;
        }
        this.mAsin = str;
        this.mCloudDriveObjectId = str2;
        this.mContentSubscriptionMode = str3;
    }

    public TrackPlaybackInfo(String str, String str2, String str3, String str4, String str5) {
        this(str, str2, str3);
        this.mSelectionSourceType = str4;
        this.mEntityType = str5;
    }

    public String getAsin() {
        return this.mAsin;
    }

    public String getCloudDriveObjectId() {
        return this.mCloudDriveObjectId;
    }

    public String getSelectionSourceType() {
        return this.mSelectionSourceType;
    }

    public String getEntityType() {
        return this.mEntityType;
    }

    public final String getContentSubscriptionMode() {
        return this.mContentSubscriptionMode;
    }

    public void setContentSubscriptionMode(String str) {
        this.mContentSubscriptionMode = str;
    }

    public final String getSubContentSubscriptionMode() {
        return this.mSubContentSubscriptionMode;
    }

    public void setSubContentSubscriptionMode(String str) {
        this.mSubContentSubscriptionMode = str;
    }
}
