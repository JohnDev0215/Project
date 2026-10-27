package com.amazon.avod.drm;

import com.amazon.avod.drm.db.DrmStoredRights;
import com.amazon.avod.media.framework.error.LicenseError;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DrmStoredRightsOrError {
    private final DrmStoredRights mDrmStoredRights;
    private final String mErrorMessage;
    private final LicenseError mLicenseError;

    public DrmStoredRightsOrError(@Nonnull LicenseError licenseError, @Nullable String str) {
        this.mDrmStoredRights = null;
        this.mLicenseError = (LicenseError) Preconditions.checkNotNull(licenseError, "licenseError");
        this.mErrorMessage = str == null ? "" : str;
    }

    public DrmStoredRightsOrError(@Nonnull DrmStoredRights drmStoredRights) {
        this.mDrmStoredRights = (DrmStoredRights) Preconditions.checkNotNull(drmStoredRights, "drmStoredRights");
        this.mLicenseError = null;
        this.mErrorMessage = "";
    }

    public boolean hasError() {
        return this.mLicenseError != null;
    }

    @Nullable
    public LicenseError getLicenseError() {
        return this.mLicenseError;
    }

    @Nonnull
    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    @Nullable
    public DrmStoredRights getDrmStoredRights() {
        return this.mDrmStoredRights;
    }
}
