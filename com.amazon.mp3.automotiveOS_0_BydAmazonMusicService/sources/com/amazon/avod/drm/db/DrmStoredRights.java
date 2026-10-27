package com.amazon.avod.drm.db;

import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class DrmStoredRights {
    private final long mExpiryTimeInSeconds;
    private final LicenseType mLicenseType;
    private final long mViewingHours;

    public enum LicenseType {
        ETERNAL("eternal"),
        FIXED_PERIOD("fixed_period", "started"),
        EXPIRES_AFTER_FIRST_USE("expires_after_first_use", "unstarted"),
        MISSING("missing");

        private static final ImmutableMap<String, LicenseType> NAME_MAP;
        private final String mNameInDB;
        private final String mNameShort;

        static {
            LicenseType licenseType = ETERNAL;
            LicenseType licenseType2 = FIXED_PERIOD;
            LicenseType licenseType3 = EXPIRES_AFTER_FIRST_USE;
            LicenseType licenseType4 = MISSING;
            NAME_MAP = new ImmutableMap.Builder().put(licenseType.mNameInDB, licenseType).put(licenseType2.mNameInDB, licenseType2).put(licenseType3.mNameInDB, licenseType3).put(licenseType4.mNameInDB, licenseType4).build();
        }

        LicenseType(@Nonnull String str) {
            this(str, str);
        }

        LicenseType(@Nonnull String str, @Nonnull String str2) {
            this.mNameInDB = str;
            this.mNameShort = str2;
        }

        @Nonnull
        public static LicenseType fromString(@Nullable String str) {
            LicenseType licenseType = NAME_MAP.get(str);
            if (licenseType != null) {
                return licenseType;
            }
            DLog.logf("Unknown licenseType, LicenseType for licenseTypeString %s not found in map. returning MISSING license", str);
            return MISSING;
        }

        @Nonnull
        public String getDBName() {
            return this.mNameInDB;
        }

        @Nonnull
        public String getNameShort() {
            return this.mNameShort;
        }

        @Override // java.lang.Enum
        public String toString() {
            return getDBName();
        }
    }

    @Nonnull
    public static DrmStoredRights newEternalLicense() {
        return new DrmStoredRights(LicenseType.ETERNAL, -1L, -1L);
    }

    @Nonnull
    public static DrmStoredRights newMissingLicense() {
        return new DrmStoredRights(LicenseType.MISSING, -1L, -1L);
    }

    @Nonnull
    public static DrmStoredRights newFixedPeriodLicense(long j) {
        return new DrmStoredRights(LicenseType.FIXED_PERIOD, j, -1L);
    }

    @Nonnull
    public static DrmStoredRights newExpiresAfterFirstUseLicense(long j, long j2) {
        return new DrmStoredRights(LicenseType.EXPIRES_AFTER_FIRST_USE, j, j2);
    }

    @Nonnull
    public static DrmStoredRights fromPersistedData(@Nonnull LicenseType licenseType, long j, long j2) {
        return new DrmStoredRights(licenseType, j, j2);
    }

    DrmStoredRights(@Nonnull LicenseType licenseType, long j, long j2) {
        Preconditions.checkNotNull(licenseType, "Bad DRM content rights [%s]: null license type field", this);
        if (licenseType == LicenseType.MISSING) {
            DLog.logf("Returning Missing license, passed in expiry time %s, passed in viewing hours %s", Long.valueOf(j), Long.valueOf(j2));
            this.mLicenseType = licenseType;
            this.mExpiryTimeInSeconds = -1L;
            this.mViewingHours = -1L;
            return;
        }
        boolean z = j != -1;
        if (licenseType == LicenseType.FIXED_PERIOD) {
            Preconditions.checkState(z, "Bad content rights: '%s' license with no expiry time", licenseType);
        }
        if (licenseType == LicenseType.ETERNAL) {
            Preconditions.checkState(!z, "Bad content rights: '%s' license with expiry time = %ss", licenseType, j);
        }
        Preconditions.checkState((licenseType == LicenseType.EXPIRES_AFTER_FIRST_USE) == ((j2 > (-1L) ? 1 : (j2 == (-1L) ? 0 : -1)) != 0), "Bad content rights: '%s' license with viewing hours = %s", licenseType, j2);
        this.mLicenseType = licenseType;
        this.mExpiryTimeInSeconds = j;
        this.mViewingHours = j2;
    }

    public boolean isMissing() {
        return this.mLicenseType == LicenseType.MISSING;
    }

    public boolean isExpirable() {
        LicenseType licenseType = this.mLicenseType;
        return licenseType == LicenseType.FIXED_PERIOD || licenseType == LicenseType.EXPIRES_AFTER_FIRST_USE;
    }

    public boolean isExpired() {
        return isExpirable() && TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) >= this.mExpiryTimeInSeconds;
    }

    public boolean isExpiredOrExpiringSoon() {
        return isExpirable() && TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) >= this.mExpiryTimeInSeconds - 30;
    }

    @Nonnull
    public LicenseType getLicenseType() {
        return this.mLicenseType;
    }

    public long getExpiryTimeInSeconds() {
        return this.mExpiryTimeInSeconds;
    }

    public long getViewingHours() {
        return this.mViewingHours;
    }

    public int hashCode() {
        return Objects.hashCode(this.mLicenseType, Long.valueOf(this.mExpiryTimeInSeconds), Long.valueOf(this.mViewingHours));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DrmStoredRights drmStoredRights = (DrmStoredRights) obj;
        return Objects.equal(this.mLicenseType, drmStoredRights.mLicenseType) && Objects.equal(Long.valueOf(this.mExpiryTimeInSeconds), Long.valueOf(drmStoredRights.mExpiryTimeInSeconds)) && Objects.equal(Long.valueOf(this.mViewingHours), Long.valueOf(drmStoredRights.mViewingHours));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("type '");
        sb.append(this.mLicenseType);
        sb.append("'");
        if (this.mExpiryTimeInSeconds != -1) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm z", Locale.US);
            sb.append(": valid until ");
            sb.append(simpleDateFormat.format(new Date(this.mExpiryTimeInSeconds * 1000)));
        }
        if (this.mViewingHours != -1) {
            sb.append(": viewable for ");
            sb.append(this.mViewingHours);
            sb.append(" hours");
        }
        return sb.toString();
    }

    @Nonnull
    public String toQosNote() {
        MoreObjects.ToStringHelper toStringHelperAddValue = MoreObjects.toStringHelper("").addValue(this.mLicenseType.getNameShort());
        if (this.mExpiryTimeInSeconds != -1) {
            toStringHelperAddValue.add("until", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US).format(new Date(this.mExpiryTimeInSeconds * 1000)));
        }
        long j = this.mViewingHours;
        if (j != -1) {
            toStringHelperAddValue.add("viewable", String.format(Locale.US, "%dhrs", Long.valueOf(j)));
        }
        return toStringHelperAddValue.toString();
    }
}
