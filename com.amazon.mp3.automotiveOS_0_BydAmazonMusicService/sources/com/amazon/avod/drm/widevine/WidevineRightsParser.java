package com.amazon.avod.drm.widevine;

import com.amazon.avod.drm.DrmStoredRightsOrError;
import com.amazon.avod.drm.db.DrmStoredRights;
import com.amazon.avod.media.framework.config.LicensingConfig;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineRightsParser {
    @Nonnull
    public static DrmStoredRightsOrError parse(@Nonnull Map<String, Object> map) {
        DrmStoredRights fixedPeriodLicense;
        Preconditions.checkNotNull(map, "rights map must be nonnull");
        if (!WidevineLicenseRightsValidator.validate(map)) {
            return new DrmStoredRightsOrError(LicenseError.INVALID_RIGHTS_MAP, "Rights map is not valid");
        }
        if (!checkPlaybackAllowed(map)) {
            return new DrmStoredRightsOrError(LicenseError.RIGHTS_PLAYBACK_NOT_ALLOWED, "License does not allow playback");
        }
        String string = map.get("LicenseType").toString();
        if (!string.equalsIgnoreCase("Offline") && !string.equalsIgnoreCase("Streaming")) {
            String str = String.format(Locale.US, "Invalid Widevine license type %s", string);
            DLog.errorf(str);
            return new DrmStoredRightsOrError(LicenseError.INVALID_LICENSE_TYPE, str);
        }
        try {
            long j = Long.parseLong(map.get("PlaybackDurationRemaining").toString());
            long j2 = Long.parseLong(map.get("LicenseDurationRemaining").toString());
            if (j <= 0 && j2 <= 0) {
                String str2 = String.format(Locale.US, "Expired license: Invalid or expired playback and license durations returned, playbackDurationRemainingInSeconds: %d, licenseDurationRemainingInSeconds: %d", Long.valueOf(j), Long.valueOf(j2));
                DLog.errorf(str2);
                return new DrmStoredRightsOrError(LicenseError.LICENSE_RIGHTS_EXPIRED, str2);
            }
            if (j <= 0) {
                String str3 = String.format(Locale.US, "Expired license: Invalid or expired playback duration returned, playbackDurationRemainingInSeconds: %d", Long.valueOf(j));
                DLog.errorf(str3);
                return new DrmStoredRightsOrError(LicenseError.LICENSE_RIGHTS_PLAYBACK_CLOCK_EXPIRED, str3);
            }
            if (j2 <= 0) {
                String str4 = String.format(Locale.US, "Expired license: Invalid or expired license duration returned, licenseDurationRemainingInSeconds: %d", Long.valueOf(j2));
                DLog.errorf(str4);
                return new DrmStoredRightsOrError(LicenseError.LICENSE_RIGHTS_LICENSE_CLOCK_EXPIRED, str4);
            }
            boolean z = j % LicensingConfig.getInstance().getWidevineNonStartedRentalFactorInSeconds() == 0;
            long jMin = Math.min(j2, j);
            if (j == Long.MAX_VALUE && j2 == Long.MAX_VALUE) {
                fixedPeriodLicense = getEternalLicense();
            } else if (z) {
                fixedPeriodLicense = getExpiresAfterFirstUseLicense(j2, jMin);
            } else {
                fixedPeriodLicense = getFixedPeriodLicense(jMin);
            }
            return new DrmStoredRightsOrError(fixedPeriodLicense);
        } catch (NumberFormatException e) {
            String str5 = String.format(Locale.US, "Exception when parsing playback or license durations: %s", e);
            DLog.errorf(str5);
            return new DrmStoredRightsOrError(LicenseError.LICENSE_PARSING_FAILED, str5);
        }
    }

    private static boolean checkPlaybackAllowed(Map<String, Object> map) {
        String string = map.get("PlayAllowed").toString();
        if (string.equalsIgnoreCase("True")) {
            return true;
        }
        DLog.devf("The rights map %s key does not allow playback with the value set to: %s", "PlayAllowed", string);
        return false;
    }

    static DrmStoredRights getEternalLicense() {
        DLog.logf("UNLIM license: valid for all time");
        return DrmStoredRights.newEternalLicense();
    }

    static DrmStoredRights getFixedPeriodLicense(long j) {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + j;
        DLog.logf("UNTIL license: valid until %s", new Date(TimeUnit.SECONDS.toMillis(seconds)));
        return DrmStoredRights.newFixedPeriodLicense(seconds);
    }

    static DrmStoredRights getExpiresAfterFirstUseLicense(long j, long j2) {
        long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        long j3 = j < Long.MAX_VALUE - seconds ? seconds + j : Long.MAX_VALUE;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        long hours = timeUnit.toHours(j2);
        DLog.logf("EXPIRATION_AFTER_FIRST_USE license: playback valid for %s hours, license valid until %s", Long.valueOf(hours), new Date(timeUnit.toMillis(j3)));
        return DrmStoredRights.newExpiresAfterFirstUseLicense(j3, hours);
    }
}
