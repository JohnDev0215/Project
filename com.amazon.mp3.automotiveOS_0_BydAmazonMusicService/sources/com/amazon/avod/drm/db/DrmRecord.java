package com.amazon.avod.drm.db;

import com.amazon.avod.media.error.DrmErrorCode;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DrmRecord {
    private final DrmScheme mDrmScheme;
    private final DrmStoredRights mDrmStoredRights;
    private final long mLastLicenseRefreshSecs;
    private final MediaErrorCode mMediaErrorCode;
    private final String mOfflineKeyId;
    private final RendererSchemeType mRendererSchemeType;

    @Nonnull
    public static DrmRecord forRightsWithExpiryCheck(@Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nullable String str, @Nonnull DrmStoredRights drmStoredRights, @Nonnegative long j) {
        return new DrmRecord(drmScheme, rendererSchemeType, str, drmStoredRights, drmStoredRights.isExpired() ? DrmErrorCode.LICENSE_EXPIRED : null, j);
    }

    @Nonnull
    public static DrmRecord forMissingRights(@Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nonnull MediaErrorCode mediaErrorCode) {
        return new DrmRecord(drmScheme, rendererSchemeType, null, DrmStoredRights.newMissingLicense(), mediaErrorCode, 0L);
    }

    @Nonnull
    public static DrmRecord fromPersistedData(@Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nullable String str, @Nonnull DrmStoredRights drmStoredRights, @Nullable MediaErrorCode mediaErrorCode, @Nonnegative long j) {
        return new DrmRecord(drmScheme, rendererSchemeType, str, drmStoredRights, mediaErrorCode, j);
    }

    private DrmRecord(@Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nullable String str, @Nonnull DrmStoredRights drmStoredRights, @Nullable MediaErrorCode mediaErrorCode, @Nonnegative long j) {
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
        RendererSchemeType rendererSchemeType2 = (RendererSchemeType) Preconditions.checkNotNull(rendererSchemeType, "rendererSchemeType");
        this.mRendererSchemeType = rendererSchemeType2;
        this.mOfflineKeyId = str;
        DrmStoredRights drmStoredRights2 = (DrmStoredRights) Preconditions.checkNotNull(drmStoredRights, "drmStoredRights");
        this.mDrmStoredRights = drmStoredRights2;
        this.mMediaErrorCode = mediaErrorCode;
        this.mLastLicenseRefreshSecs = Preconditions2.checkNonNegativeWeakly(j, 0L, "lastLicenseRefreshSecs");
        if (drmStoredRights2.isMissing()) {
            Preconditions.checkArgument(mediaErrorCode != null, "mediaErrorCode is required when license is missing");
        }
        if (drmStoredRights2.isMissing()) {
            Preconditions.checkArgument(Strings.isNullOrEmpty(str), "offlineKeyId has no meaning when license is missing");
        } else if (rendererSchemeType2 == RendererSchemeType.MEDIACODEC_MEDIADRM) {
            Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "offlineKeyId cannot be absent with a valid license when using %s", rendererSchemeType2);
        } else {
            Preconditions2.checkArgumentWeakly(Strings.isNullOrEmpty(str), "offlineKeyId not usable with %s", rendererSchemeType2);
        }
    }

    @Nonnull
    public DrmScheme getDrmScheme() {
        return this.mDrmScheme;
    }

    @Nonnull
    public RendererSchemeType getRendererSchemeType() {
        return this.mRendererSchemeType;
    }

    @Nullable
    public String getOfflineKeyId() {
        return this.mOfflineKeyId;
    }

    @Nonnull
    public DrmStoredRights getDrmStoredRights() {
        return this.mDrmStoredRights;
    }

    @Nullable
    public MediaErrorCode getErrorCode() {
        return this.mMediaErrorCode;
    }

    @Nonnegative
    public long getLastLicenseRefreshSecs() {
        return this.mLastLicenseRefreshSecs;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DrmRecord drmRecord = (DrmRecord) obj;
        return Objects.equal(this.mDrmScheme, drmRecord.mDrmScheme) && Objects.equal(this.mRendererSchemeType, drmRecord.mRendererSchemeType) && Objects.equal(this.mOfflineKeyId, drmRecord.mOfflineKeyId) && Objects.equal(this.mDrmStoredRights, drmRecord.mDrmStoredRights) && Objects.equal(this.mMediaErrorCode, drmRecord.mMediaErrorCode);
    }

    public int hashCode() {
        return Objects.hashCode(this.mDrmScheme, this.mRendererSchemeType, this.mOfflineKeyId, this.mDrmStoredRights, this.mMediaErrorCode);
    }

    public String toString() {
        return MoreObjects.toStringHelper("").omitNullValues().add("drmScheme", this.mDrmScheme).add("rendererScheme", this.mRendererSchemeType).add("offlineKeyId", DLog.maskString(this.mOfflineKeyId)).add("error", this.mMediaErrorCode).add("drmRights", this.mDrmStoredRights).toString();
    }

    @Nonnull
    public String toQosNote() {
        return MoreObjects.toStringHelper("").omitNullValues().add("scheme", this.mDrmScheme + "/" + this.mRendererSchemeType).add("keyId", DLog.maskString(this.mOfflineKeyId)).add("error", this.mMediaErrorCode).addValue(this.mDrmStoredRights.toQosNote()).toString();
    }
}
