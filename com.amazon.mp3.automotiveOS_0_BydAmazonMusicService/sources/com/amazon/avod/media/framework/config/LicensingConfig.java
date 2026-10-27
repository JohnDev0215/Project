package com.amazon.avod.media.framework.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.LicenseError;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class LicensingConfig extends MediaConfigBase {
    private final TimeSpan DEFAULT_DRM_TIMEOUT;
    private final TimeConfigurationValue mCallTimeout;
    private final ConfigurationValue<Set<String>> mErrorsToSuggestDrmResetFor;
    private final ConfigurationValue<Boolean> mIsAndroidDrmFrameworkChallengeReplaySupported;
    private final ConfigurationValue<Boolean> mIsLicenseRightsValidationEnabled;
    private final ConfigurationValue<Boolean> mIsSoftwarePlayReadyChallengeReplaySupported;
    private final ConfigurationValue<Boolean> mKeyIdMismatchDetectionEnabled;
    private final ConfigurationValue<Boolean> mResetDrmForExternalCallersEnabled;
    private final ConfigurationValue<Boolean> mResetDrmOnClearDataEnabled;
    private final ConfigurationValue<Long> mWidevineNonStartedRentalFactorInSeconds;

    private static class Holder {
        static final LicensingConfig INSTANCE = new LicensingConfig();

        private Holder() {
        }
    }

    public static LicensingConfig getInstance() {
        return Holder.INSTANCE;
    }

    private LicensingConfig() {
        TimeSpan timeSpanFromSeconds = TimeSpan.fromSeconds(30.0d);
        this.DEFAULT_DRM_TIMEOUT = timeSpanFromSeconds;
        this.mCallTimeout = newTimeConfigurationValue("drm_playreadyCallTimeoutMilliseconds", timeSpanFromSeconds, TimeUnit.MILLISECONDS);
        this.mKeyIdMismatchDetectionEnabled = newBooleanConfigValue("drm_playreadyKeyIdMismatchDetectionEnabled", true);
        this.mErrorsToSuggestDrmResetFor = newSemicolonDelimitedStringSetConfigurationValue("drm_playreadyErrorsToSuggestDrmResetFor", new String[]{Integer.toString(LicenseError.DRM_E_SECURESTORE_CORRUPT.getIntegerErrorCode()), Integer.toString(LicenseError.DRM_E_INVALIDLICENSESTORE.getIntegerErrorCode()), Integer.toString(LicenseError.DRM_E_DEVCERTREADERROR.getIntegerErrorCode()), Integer.toString(LicenseError.DRM_E_DSTCORRUPTED.getIntegerErrorCode()), Integer.toString(LicenseError.DRM_E_DSTBLOCKMISMATCH.getIntegerErrorCode()), Integer.toString(LicenseError.DRM_E_DISKSPACE_ERROR.getIntegerErrorCode())});
        this.mWidevineNonStartedRentalFactorInSeconds = newLongConfigValue("playback_widevineNonStartedRentalFactorInSeconds", TimeUnit.HOURS.toSeconds(12L));
        this.mIsSoftwarePlayReadyChallengeReplaySupported = newBooleanConfigValue("playback_isSoftwarePlayReadyChallengeReplaySupported", true);
        this.mIsLicenseRightsValidationEnabled = newBooleanConfigValue("playback_isLicenseRightsValidationEnabled", true);
        this.mResetDrmOnClearDataEnabled = newBooleanConfigValue("playback_resetDrmOnClearDataEnabled", true);
        this.mResetDrmForExternalCallersEnabled = newBooleanConfigValue("playback_resetDrmForExternalCallersEnabled", true);
        this.mIsAndroidDrmFrameworkChallengeReplaySupported = newBooleanConfigValue("playback_isAndroidDrmFrameworkChallengeReplaySupported", true);
    }

    public TimeSpan getCallTimeout() {
        return this.mCallTimeout.getValue();
    }

    public boolean getKeyIdMismatchDetectionEnabled() {
        return this.mKeyIdMismatchDetectionEnabled.getValue().booleanValue();
    }

    public Set<Integer> getErrorsToSuggestDrmResetFor() {
        return createIntegerSetFromConfigurationValueStringSet(this.mErrorsToSuggestDrmResetFor);
    }

    public long getWidevineNonStartedRentalFactorInSeconds() {
        return this.mWidevineNonStartedRentalFactorInSeconds.getValue().longValue();
    }

    public boolean isSoftwarePlayReadyChallengeReplaySupported() {
        return this.mIsSoftwarePlayReadyChallengeReplaySupported.getValue().booleanValue();
    }

    public boolean isLicenseRightsValidationEnabled() {
        return this.mIsLicenseRightsValidationEnabled.getValue().booleanValue();
    }

    public boolean isResetDrmOnClearDataEnabled() {
        return this.mResetDrmOnClearDataEnabled.getValue().booleanValue();
    }

    public boolean isResetDrmForExternalCallersEnabled() {
        return this.mResetDrmForExternalCallersEnabled.getValue().booleanValue();
    }

    public boolean isAndroidDrmFrameworkChallengeReplaySupported() {
        return this.mIsAndroidDrmFrameworkChallengeReplaySupported.getValue().booleanValue();
    }
}
