package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
public class ReturnedTitleRendition {
    private final String mAudioQuality;
    private final List<AuditPing> mAuditPings;
    private final String mContentId;
    private final TimeShiftPolicy mTimeShiftPolicy;
    private final String mTitleId;
    private final String mVideoMaterialType;
    private final String mVideoQuality;

    @JsonCreator
    public ReturnedTitleRendition(@JsonProperty("audioQuality") @Nullable String str, @JsonProperty("contentId") @Nullable String str2, @JsonProperty("videoQuality") @Nullable String str3, @JsonProperty("videoMaterialType") @Nullable String str4, @JsonProperty("titleId") @Nullable String str5, @JsonProperty("auditPings") @Nullable List<AuditPing> list, @JsonProperty("timeShiftPolicy") @Nullable TimeShiftPolicy timeShiftPolicy) {
        this.mAudioQuality = Strings.nullToEmpty(str);
        this.mContentId = Strings.nullToEmpty(str2);
        this.mVideoQuality = Strings.nullToEmpty(str3);
        this.mVideoMaterialType = Strings.nullToEmpty(str4);
        this.mTitleId = Strings.nullToEmpty(str5);
        this.mAuditPings = list;
        this.mTimeShiftPolicy = timeShiftPolicy;
    }

    @Nonnull
    public String getContentId() {
        return this.mContentId;
    }

    @Nonnull
    public String getTitleId() {
        return this.mTitleId;
    }

    @Nullable
    public ImmutableList<AuditPing> getAuditPings() {
        List<AuditPing> list = this.mAuditPings;
        if (list != null) {
            return ImmutableList.copyOf((Collection) list);
        }
        return null;
    }

    @Nullable
    public TimeShiftPolicy getTimeShiftPolicy() {
        return this.mTimeShiftPolicy;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("audioQuality", this.mAudioQuality).add("contentId", this.mContentId).add("videoQuality", this.mVideoQuality).add("videoMaterialType", this.mVideoMaterialType).add("titleId", this.mTitleId).add("auditPings", getAuditPings()).add("timeShiftPolicy", getTimeShiftPolicy() != null ? getTimeShiftPolicy().toString() : null).toString();
    }
}
