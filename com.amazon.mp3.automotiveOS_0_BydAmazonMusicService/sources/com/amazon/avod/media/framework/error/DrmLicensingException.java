package com.amazon.avod.media.framework.error;

import com.amazon.avod.playback.drm.DrmScheme;
import com.google.common.base.Strings;
import java.net.URL;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DrmLicensingException extends MediaException {
    private static final long serialVersionUID = 5313725342780500186L;
    private final String mNote;

    public DrmLicensingException(LicenseError licenseError, String str, Throwable th, String str2, DrmScheme drmScheme, URL url) {
        super(licenseError, drmScheme != null ? String.format(Locale.US, "DrmScheme: %s, %s", drmScheme, str) : str, th, url);
        this.mNote = Strings.nullToEmpty(str2);
    }

    public DrmLicensingException(LicenseError licenseError, String str, Throwable th) {
        this(licenseError, str, th, null, null, null);
    }

    public DrmLicensingException(LicenseError licenseError, String str, Throwable th, DrmScheme drmScheme) {
        this(licenseError, str, th, null, drmScheme, null);
    }

    public DrmLicensingException(LicenseError licenseError, String str, Throwable th, DrmScheme drmScheme, URL url) {
        this(licenseError, str, th, null, drmScheme, url);
    }

    public DrmLicensingException(String str) {
        this(LicenseError.UNKNOWN_PLAYREADY_ERROR, str, null, null, null, null);
    }

    public DrmLicensingException(Throwable th) {
        this(LicenseError.UNKNOWN_PLAYREADY_ERROR, null, th, null, null, null);
    }

    public DrmLicensingException(LicenseError licenseError) {
        this(licenseError, null, null, null, null, null);
    }

    public DrmLicensingException(LicenseError licenseError, Throwable th) {
        this(licenseError, null, th, null, null, null);
    }

    public DrmLicensingException(LicenseError licenseError, String str) {
        this(licenseError, str, null, null, null, null);
    }

    public DrmLicensingException(LicenseError licenseError, String str, DrmScheme drmScheme) {
        this(licenseError, str, null, null, drmScheme, null);
    }

    public DrmLicensingException(LicenseError licenseError, Throwable th, String str) {
        this(licenseError, null, th, str, null, null);
    }

    public DrmLicensingException(LicenseError licenseError, Throwable th, URL url) {
        this(licenseError, null, th, null, null, url);
    }

    public DrmLicensingException(DrmLicensingException drmLicensingException, String str) {
        this(drmLicensingException.getErrorCode(), drmLicensingException.getMessage(), drmLicensingException.getCause(), drmLicensingException.getNote() + Strings.nullToEmpty(str), null, null);
    }

    @Nonnull
    public String getNote() {
        return this.mNote;
    }

    @Override // com.amazon.avod.media.framework.error.MediaException
    public LicenseError getErrorCode() {
        return (LicenseError) super.getErrorCode();
    }

    @Override // com.amazon.avod.media.framework.error.MediaException
    public int getIntegerErrorCode() {
        return getErrorCode().getIntegerErrorCode();
    }

    @Nonnull
    public LicenseQueryException toLicenseQueryException(@Nullable String str) {
        if (str == null) {
            str = getMessage();
        }
        return new LicenseQueryException(getErrorCode().getExternalCode(), str, this);
    }
}
