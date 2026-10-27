package com.amazon.avod.drm.playready;

import com.amazon.avod.drm.DrmStoredRightsOrError;
import com.amazon.avod.drm.db.DrmStoredRights;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlayReadyRightsParser {
    @Nonnull
    public static DrmStoredRightsOrError parse(@Nonnull Map<String, Object> map) {
        Date dateJavaDateFromAndroidTimeStamp;
        Date date;
        DrmStoredRights drmStoredRightsHandleLicenseTypeExpirationAfterFirstUse;
        Preconditions.checkNotNull(map, "rights map must be nonnull");
        String str = "No license currently available for this content";
        if (map.containsKey("Error")) {
            String string = map.get("Error").toString();
            LicenseError licenseErrorFromErrorString = LicenseError.fromErrorString(string);
            if (licenseErrorFromErrorString == LicenseError.NO_ERROR) {
                DLog.devf("The rights map incorrectly contains an Error key with a non-error value; ignoring");
                map.remove("Error");
            } else {
                DLog.devf("The rights map contains an Error key with value \"%s\"", string);
                if (licenseErrorFromErrorString == LicenseError.UNKNOWN_PLAYREADY_ERROR) {
                    str = String.format(Locale.US, "Unrecognized error \"%s\" from PlayReady's getConstraints()", string);
                    DLog.errorf(str);
                } else if (licenseErrorFromErrorString == LicenseError.DRM_E_LICENSENOTFOUND) {
                    DLog.logf("No license currently available for this content");
                } else {
                    str = String.format(Locale.US, "License error parsing rights: %s", licenseErrorFromErrorString);
                    DLog.warnf(str);
                }
                return new DrmStoredRightsOrError(licenseErrorFromErrorString, str);
            }
        }
        if (!PlayReadyLicenseRightsValidator.validate(map)) {
            return new DrmStoredRightsOrError(LicenseError.INVALID_RIGHTS_MAP, "Rights map is not valid");
        }
        String string2 = map.get("NumDates").toString();
        try {
            int i = Integer.parseInt(string2, 16);
            Date[] dateArr = new Date[i];
            int i2 = 0;
            while (i2 < i) {
                StringBuilder sb = new StringBuilder();
                sb.append("datetime");
                int i3 = i2 + 1;
                sb.append(Integer.toString(i3));
                String string3 = sb.toString();
                Object obj = map.get(string3);
                if (obj != null && obj.toString() != null) {
                    dateArr[i2] = PlayReadyDate.javaDateFromPlayReadyDate(obj.toString());
                    i2 = i3;
                } else {
                    String str2 = String.format(Locale.US, "Did not find expected key \"%s\" in getConstraints() response", string3);
                    DLog.errorf(str2);
                    return new DrmStoredRightsOrError(LicenseError.LICENSE_PARSING_FAILED, str2);
                }
            }
            String string4 = map.get("NumCounts").toString();
            try {
                int i4 = Integer.parseInt(string4, 16);
                int[] iArr = new int[i4];
                int i5 = 0;
                while (i5 < i4) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Count");
                    int i6 = i5 + 1;
                    sb2.append(Integer.toString(i6));
                    String string5 = sb2.toString();
                    Object obj2 = map.get(string5);
                    if (obj2 == null) {
                        String str3 = String.format(Locale.US, "The license doesn't contain key count entry: %s", string5);
                        DLog.errorf(str3);
                        return new DrmStoredRightsOrError(LicenseError.INVALID_KEY_COUNT, str3);
                    }
                    iArr[i5] = Integer.parseInt(obj2.toString(), 16);
                    i5 = i6;
                }
                Object obj3 = map.get("CurrTime");
                Date date2 = null;
                if (obj3 != null) {
                    dateJavaDateFromAndroidTimeStamp = PlayReadyDate.javaDateFromAndroidTimeStamp(obj3.toString());
                    if (i > 0 && dateArr[0] != null) {
                        dateArr[0] = new Date(dateArr[0].getTime() - (dateJavaDateFromAndroidTimeStamp.getTime() - System.currentTimeMillis()));
                    }
                } else {
                    DLog.warnf("Did not find expected key \"%s\" in getConstraints() response", "CurrTime");
                    dateJavaDateFromAndroidTimeStamp = null;
                }
                String string6 = map.get("Category").toString();
                try {
                    PlayReadyLicenseType playReadyLicenseTypeFromNumericString = PlayReadyLicenseType.fromNumericString(string6);
                    if (playReadyLicenseTypeFromNumericString == PlayReadyLicenseType.NORIGHT) {
                        DLog.logf("No license currently available for this content");
                        return new DrmStoredRightsOrError(LicenseError.NO_LICENSE_AVAILABLE, "No license currently available for this content");
                    }
                    if (playReadyLicenseTypeFromNumericString == PlayReadyLicenseType.UNLIM) {
                        Preconditions.checkState(i == 0, "Invalid numDates value %s; must be 0 for UNLIM licenses", i);
                        Preconditions.checkState(i4 == 0, "Invalid numCounts value %s; must be 0 for UNLIM licenses", i4);
                        drmStoredRightsHandleLicenseTypeExpirationAfterFirstUse = handleLicenseTypeUnlim();
                    } else if (playReadyLicenseTypeFromNumericString == PlayReadyLicenseType.FROM) {
                        Preconditions.checkState(i == 1, "Invalid numDates value %s; must be 1 for FROM licenses", i);
                        Preconditions.checkState(i4 == 0, "Invalid numCounts value %s; must be 0 for FROM licenses", i4);
                        drmStoredRightsHandleLicenseTypeExpirationAfterFirstUse = handleLicenseTypeFrom(dateArr[0], dateJavaDateFromAndroidTimeStamp);
                    } else if (playReadyLicenseTypeFromNumericString == PlayReadyLicenseType.UNTIL) {
                        Preconditions.checkState(i == 1, "Invalid numDates value %s; must be 1 for UNTIL licenses", i);
                        Preconditions.checkState(i4 == 0, "Invalid numCounts value %s; must be 0 for UNTIL licenses", i4);
                        drmStoredRightsHandleLicenseTypeExpirationAfterFirstUse = handleLicenseTypeUntil(dateArr[0], dateJavaDateFromAndroidTimeStamp);
                    } else if (playReadyLicenseTypeFromNumericString == PlayReadyLicenseType.FROM_UNTIL) {
                        Preconditions.checkState(i == 2, "Invalid numDates value %s; must be 2 for FROM_UNTIL licenses", i);
                        Preconditions.checkState(i4 == 0, "Invalid numCounts value %s; must be 0 for FROM_UNTIL licenses", i4);
                        drmStoredRightsHandleLicenseTypeExpirationAfterFirstUse = handleLicenseTypeFromUntil(dateArr[0], dateArr[1], dateJavaDateFromAndroidTimeStamp);
                    } else if (playReadyLicenseTypeFromNumericString == PlayReadyLicenseType.EXPIRATION_AFTER_FIRSTUSE) {
                        Preconditions.checkState(i <= 2, "Invalid numDates value %s; must be <= 2 for EXPIRATION_AFTER_FIRSTUSE licenses", i);
                        Preconditions.checkState(i4 == 1, "Invalid numCounts value %s; must be 1 for EXPIRATION_AFTER_FIRSTUSE licenses", i4);
                        if (i == 2) {
                            date2 = dateArr[0];
                            date = dateArr[1];
                        } else {
                            date = i == 1 ? dateArr[0] : null;
                        }
                        drmStoredRightsHandleLicenseTypeExpirationAfterFirstUse = handleLicenseTypeExpirationAfterFirstUse(date2, date, iArr[0], dateJavaDateFromAndroidTimeStamp);
                    } else {
                        DLog.errorf("Unexpected license type: %s", playReadyLicenseTypeFromNumericString);
                        return new DrmStoredRightsOrError(LicenseError.INVALID_LICENSE_TYPE, String.format(Locale.US, "Unexpected license type: %s", playReadyLicenseTypeFromNumericString));
                    }
                    return new DrmStoredRightsOrError(drmStoredRightsHandleLicenseTypeExpirationAfterFirstUse);
                } catch (NumberFormatException unused) {
                    String str4 = String.format(Locale.US, "Invalid license type string: %s", string6);
                    DLog.errorf(str4);
                    return new DrmStoredRightsOrError(LicenseError.INVALID_LICENSE_TYPE, str4);
                }
            } catch (NumberFormatException unused2) {
                String str5 = String.format(Locale.US, "Invalid number of keys in the license: %s", string4);
                DLog.errorf(str5);
                return new DrmStoredRightsOrError(LicenseError.INVALID_KEY_COUNT, str5);
            }
        } catch (NumberFormatException unused3) {
            String str6 = String.format(Locale.US, "Invalid number of dates entry in the license: %s", string2);
            DLog.errorf(str6);
            return new DrmStoredRightsOrError(LicenseError.LICENSE_PARSING_FAILED, str6);
        }
    }

    static DrmStoredRights handleLicenseTypeUnlim() {
        DLog.logf("UNLIM license: valid for all time");
        return DrmStoredRights.newEternalLicense();
    }

    static DrmStoredRights handleLicenseTypeFrom(Date date, Date date2) {
        DLog.logf("FROM license: valid from %s onward", date);
        if (date.getTime() > getCurrentTimeInMillis(date2)) {
            DLog.errorf("Invalid FROM license: start time: %s is in the future, current time as returned by drm framework: %s, current system time: %s", date, date2, new Date());
        }
        return DrmStoredRights.newEternalLicense();
    }

    static DrmStoredRights handleLicenseTypeUntil(Date date, Date date2) {
        DLog.logf("UNTIL license: valid until %s", date);
        long currentTimeInMillis = getCurrentTimeInMillis(date2);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(date.getTime());
        if (date.getTime() < currentTimeInMillis) {
            DLog.warnf("Surprising UNTIL license: expiry date %s is in the past, current time as returned by drm framework: %s, current system time: %s,  creating a new fixedPeriodLicense with expiryTime as 0", date, date2, new Date());
        }
        return DrmStoredRights.newFixedPeriodLicense(seconds);
    }

    static DrmStoredRights handleLicenseTypeFromUntil(Date date, Date date2, Date date3) {
        DLog.logf("FROM_UNTIL license: valid from %s to %s, current time as returned from DRMSystem %s", date, date2, date3);
        long currentTimeInMillis = getCurrentTimeInMillis(date3);
        if (date.getTime() > currentTimeInMillis) {
            DLog.errorf("Invalid FROM_UNTIL license: start time %s is in the future, current time as returned by drm framework: %s, current system time: %s", date, date3, new Date());
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(date2.getTime());
        if (date2.getTime() < currentTimeInMillis) {
            DLog.warnf("Surprising FROM_UNTIL license: expiry date %s is in the past, current time as returned by drm framework: %s, current system time: %s", date2, date3, new Date());
        }
        return DrmStoredRights.newFixedPeriodLicense(seconds);
    }

    private static long getCurrentTimeInMillis(Date date) {
        if (date == null) {
            return new Date().getTime();
        }
        return date.getTime();
    }

    static DrmStoredRights handleLicenseTypeExpirationAfterFirstUse(Date date, Date date2, int i, Date date3) {
        long seconds;
        Locale locale = Locale.US;
        String str = String.format(locale, "EXPIRATION_AFTER_FIRSTUSE license: valid for %s hours", Integer.valueOf(i));
        if (date != null) {
            str = str + String.format(locale, " from %s", date.toString());
        }
        if (date2 != null) {
            str = str + String.format(locale, " until %s", date2.toString());
        }
        DLog.logf(str + String.format(locale, "current time as returned by drm framework: %s, current system time: %s", date3, new Date()));
        long currentTimeInMillis = getCurrentTimeInMillis(date3);
        if (date != null && date.getTime() > currentTimeInMillis) {
            DLog.errorf("Invalid EXPIRATION_AFTER_FIRSTUSE license: start time %s is in the future, current time as returned by drm framework: %s, current system time: %s", date, date3, new Date());
        }
        if (date2 != null) {
            seconds = TimeUnit.MILLISECONDS.toSeconds(date2.getTime());
            if (date2.getTime() < currentTimeInMillis) {
                DLog.warnf("EXPIRATION_AFTER_FIRSTUSE license has expired without being played: expiry time was %s , current time as returned by drm framework: %s, current system time: %s", date2, date3, new Date());
            }
        } else {
            seconds = -1;
        }
        if (i % 2 != 0) {
            i++;
        }
        return DrmStoredRights.newExpiresAfterFirstUseLicense(seconds, i);
    }
}
