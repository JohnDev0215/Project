package com.amazon.avod.userdownload.reporting;

import com.amazon.avod.drm.db.DrmRecord;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum DrmRecordChangeType {
    RECORD_ADDED("RecordAdded"),
    RECORD_REMOVED("RecordRemoved"),
    DRM_SCHEME_CHANGE("DrmSchemeChange"),
    RENDERER_SCHEME_CHANGE("RendererSchemeChange"),
    LICENSE_ACQUIRED("LicenseAcquired"),
    LICENSE_LOST("LicenseLost"),
    LICENSE_TYPE_CHANGE("LicenseTypeChange"),
    ERROR_CODE_CHANGE("ErrorCodeChange");

    private final String mEventSubtype;

    DrmRecordChangeType(@Nonnull String str) {
        this.mEventSubtype = "DrmStateChange:" + str;
    }

    @Nonnull
    public String getEventSubtype() {
        return this.mEventSubtype;
    }

    @Nullable
    public static DrmRecordChangeType determineChangeType(@Nullable DrmRecord drmRecord, @Nullable DrmRecord drmRecord2) {
        if (drmRecord == null && drmRecord2 == null) {
            return null;
        }
        if (drmRecord == null) {
            return drmRecord2.getErrorCode() == null ? LICENSE_ACQUIRED : RECORD_ADDED;
        }
        if (drmRecord2 == null) {
            return RECORD_REMOVED;
        }
        if (drmRecord.getDrmScheme() != drmRecord2.getDrmScheme()) {
            return DRM_SCHEME_CHANGE;
        }
        if (drmRecord.getRendererSchemeType() != drmRecord2.getRendererSchemeType()) {
            return RENDERER_SCHEME_CHANGE;
        }
        boolean z = drmRecord.getErrorCode() == null;
        boolean z2 = drmRecord2.getErrorCode() == null;
        if (z != z2) {
            return z2 ? LICENSE_ACQUIRED : LICENSE_LOST;
        }
        if (z2) {
            if (drmRecord.getDrmStoredRights().getLicenseType() != drmRecord2.getDrmStoredRights().getLicenseType()) {
                return LICENSE_TYPE_CHANGE;
            }
        } else if (drmRecord.getErrorCode() != drmRecord2.getErrorCode()) {
            return ERROR_CODE_CHANGE;
        }
        return null;
    }
}
