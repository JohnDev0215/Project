package com.amazon.avod.drm.db;

import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DrmPersistenceInfo {
    private final String mAsin;
    private final String mDrmAssetId;
    private final Optional<DrmRecord> mDrmRecord;
    private final Optional<Boolean> mIsDash;
    private final Map<String, String> mPlaybackSessionContext;
    private final String mPlaybackToken;

    public DrmPersistenceInfo(@Nonnull String str, @Nonnull Optional<Boolean> optional, @Nonnull String str2, @Nonnull Optional<DrmRecord> optional2, @Nullable String str3, @Nonnull Map<String, String> map) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "null or empty asin");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str2), "null or empty drmAssetId");
        this.mAsin = str;
        this.mIsDash = (Optional) Preconditions.checkNotNull(optional, "isDash");
        this.mDrmAssetId = str2;
        this.mDrmRecord = (Optional) Preconditions.checkNotNull(optional2, "drmRecord");
        this.mPlaybackToken = str3;
        this.mPlaybackSessionContext = (Map) Preconditions.checkNotNull(map, "playbackSessionContext");
    }

    @Nonnull
    public String getAsin() {
        return this.mAsin;
    }

    @Nonnull
    public Map<String, String> getPlaybackSessionContext() {
        return this.mPlaybackSessionContext;
    }

    @Nonnull
    public Optional<Boolean> isDash() {
        return this.mIsDash;
    }

    @Nonnull
    public String getDrmAssetId() {
        return this.mDrmAssetId;
    }

    @Nonnull
    public Optional<DrmRecord> getDrmRecord() {
        return this.mDrmRecord;
    }

    @Nullable
    public String getPlaybackToken() {
        return this.mPlaybackToken;
    }

    public String toString() {
        return MoreObjects.toStringHelper("").omitNullValues().add("titleId", this.mAsin).add("isDash", this.mIsDash.orNull()).add("assetId", DLog.maskString(this.mDrmAssetId)).add("record", this.mDrmRecord.orNull()).add("playbackToken", this.mPlaybackToken).add("playbackSessionContext", DLog.maskString(this.mPlaybackSessionContext)).toString();
    }
}
