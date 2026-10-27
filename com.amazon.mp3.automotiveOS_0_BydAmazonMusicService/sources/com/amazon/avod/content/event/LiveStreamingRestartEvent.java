package com.amazon.avod.content.event;

import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LiveStreamingRestartEvent {
    private final AudioVideoUrls mAudioVideoUrls;
    private final String mContentSessionUUID;
    private final String mContentUrlSetId;
    private final String mFailoverRuleId;
    private final String mOldUrlSetId;
    private final boolean mOverridePreviousRestart;
    private final long mRestartTimeMillis;
    private final int mType;

    public static LiveStreamingRestartEvent newOriginSwitchEvent() {
        return newOriginSwitchEvent(null, null, -1L, false, null, null, null);
    }

    public static LiveStreamingRestartEvent newOriginSwitchEvent(@Nullable String str, @Nullable AudioVideoUrls audioVideoUrls, long j, boolean z, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        return new LiveStreamingRestartEvent(1, j, str, audioVideoUrls, z, str2, str3, str4);
    }

    public static LiveStreamingRestartEvent newKeyRotationEvent(long j) {
        Preconditions.checkArgument(j >= 0, "restartTimeMillis must be non-negative.");
        return new LiveStreamingRestartEvent(3, j, null, null, false, null, null, null);
    }

    private LiveStreamingRestartEvent(int i, long j, @Nullable String str, @Nullable AudioVideoUrls audioVideoUrls, boolean z, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.mType = i;
        this.mRestartTimeMillis = j;
        this.mContentUrlSetId = str;
        this.mAudioVideoUrls = audioVideoUrls;
        this.mOverridePreviousRestart = z;
        this.mContentSessionUUID = str2;
        this.mFailoverRuleId = str3;
        this.mOldUrlSetId = str4;
    }

    public int getType() {
        return this.mType;
    }

    public long getRestartTimeMillis() {
        int i = this.mType;
        boolean z = true;
        if (i != 3 && i != 1) {
            z = false;
        }
        Preconditions.checkState(z, "getRestartTimeMillis should be only called for key rotation or origin failover restart type");
        return this.mRestartTimeMillis;
    }

    @Nullable
    public String getContentUrlSetId() {
        return this.mContentUrlSetId;
    }

    @Nullable
    public AudioVideoUrls getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    public boolean shouldOverridePreviousRestart() {
        return this.mOverridePreviousRestart;
    }

    @Nullable
    public String getContentSessionUUID() {
        return this.mContentSessionUUID;
    }

    @Nullable
    public String getFailoverRuleId() {
        return this.mFailoverRuleId;
    }

    @Nullable
    public String getOldUrlSetId() {
        return this.mOldUrlSetId;
    }
}
