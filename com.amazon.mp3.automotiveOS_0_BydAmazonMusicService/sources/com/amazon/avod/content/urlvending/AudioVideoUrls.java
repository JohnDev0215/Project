package com.amazon.avod.content.urlvending;

import com.amazon.avod.media.framework.config.TimeShiftPolicyConfig;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AudioVideoUrls {
    private final ImmutableList<AudioTrackMetadata> mAudioTrackMetadataList;
    private final String mAuxCacheKey;
    private final ImmutableList<ContentUrl> mContentUrls;
    private final CuepointPlaylistInfo mCuepointPlaylistInfo;
    private final String mEncodeId;
    private final FailoverManager mFailoverManager;
    private final Map<String, String> mPlaybackSettingsOverride;
    private final ReturnedTitleRendition mReturnedTitleRendition;
    private final String mSessionToken;
    private final TimeShiftPolicyConfig mTimeShiftPolicyConfig;

    AudioVideoUrls(@Nonnull ImmutableList<ContentUrl> immutableList, @Nonnull ImmutableList<AudioTrackMetadata> immutableList2, @Nullable String str, @Nonnull Map<String, String> map, @Nullable FailoverManager failoverManager, @Nullable ReturnedTitleRendition returnedTitleRendition, @Nonnull TimeShiftPolicyConfig timeShiftPolicyConfig, @Nullable CuepointPlaylistInfo cuepointPlaylistInfo, @Nullable String str2, @Nullable String str3) {
        this.mContentUrls = (ImmutableList) Preconditions.checkNotNull(immutableList, "ContentUrls cannot be null.");
        Preconditions.checkState(!immutableList.isEmpty(), "ContentUrls cannot be empty.");
        this.mAudioTrackMetadataList = (ImmutableList) Preconditions.checkNotNull(immutableList2, "audioTrackMetadataList");
        this.mEncodeId = str;
        this.mPlaybackSettingsOverride = (Map) Preconditions.checkNotNull(map, "playbackSettingsOverride");
        this.mFailoverManager = failoverManager;
        this.mReturnedTitleRendition = returnedTitleRendition;
        this.mTimeShiftPolicyConfig = (TimeShiftPolicyConfig) Preconditions.checkNotNull(timeShiftPolicyConfig, "timeShiftPolicyConfig");
        this.mCuepointPlaylistInfo = cuepointPlaylistInfo;
        this.mAuxCacheKey = str2;
        this.mSessionToken = str3;
    }

    @Nonnull
    public ImmutableList<ContentUrl> getContentUrls() {
        return this.mContentUrls;
    }

    @Nonnull
    public ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() {
        return this.mAudioTrackMetadataList;
    }

    @Nonnull
    public Map<String, String> getPlaybackSettingsOverride() {
        return this.mPlaybackSettingsOverride;
    }

    @Nonnull
    public TimeShiftPolicyConfig getTimeShiftPolicyConfig() {
        return this.mTimeShiftPolicyConfig;
    }

    @Nullable
    public String getEncodeId() {
        return this.mEncodeId;
    }

    @Nullable
    public String getAuxCacheKey() {
        return this.mAuxCacheKey;
    }

    @Nullable
    public String getSessionToken() {
        return this.mSessionToken;
    }

    @Nullable
    public FailoverManager getFailoverManager() {
        return this.mFailoverManager;
    }

    @Nullable
    public ReturnedTitleRendition getReturnedTitleRendition() {
        return this.mReturnedTitleRendition;
    }

    @Nullable
    public CuepointPlaylistInfo getCuepointPlaylistInfo() {
        return this.mCuepointPlaylistInfo;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("urls", this.mContentUrls).add("encodeId", this.mEncodeId).add("audioTrackMetadataList", this.mAudioTrackMetadataList).add("playbackSettingsOverride", this.mPlaybackSettingsOverride).add("failoverManager", this.mFailoverManager).add("returnedTitleRendition", this.mReturnedTitleRendition).add("cuepointPlaylistInfo", this.mCuepointPlaylistInfo).toString();
    }

    @Nullable
    public String getAuditPingUrl(@Nonnull String str) {
        ImmutableList<AuditPing> auditPings;
        Preconditions.checkNotNull(str, "adAgencyName");
        ReturnedTitleRendition returnedTitleRendition = this.mReturnedTitleRendition;
        if (returnedTitleRendition == null || (auditPings = returnedTitleRendition.getAuditPings()) == null) {
            return null;
        }
        UnmodifiableIterator<AuditPing> it = auditPings.iterator();
        while (it.hasNext()) {
            AuditPing next = it.next();
            if (str.equalsIgnoreCase(next.getAdReportingAgency())) {
                return next.getUrl();
            }
        }
        return null;
    }
}
