package com.amazon.avod.media.playback.support;

import androidx.annotation.NonNull;
import com.amazon.avod.media.framework.config.profiles.ThirdPartyProfileName;
import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AvailabilityStatus implements MetricParameter {
    private final AvailabilityCode mAvailabilityCode;
    private final boolean mIsAutoResolved;
    private final int mMaxHdcpLevel;
    private final ThirdPartyProfileName mSupportedProfile;

    public AvailabilityStatus(@Nonnull AvailabilityCode availabilityCode) {
        this(availabilityCode, (ThirdPartyProfileName) null, false);
    }

    public AvailabilityStatus(@Nonnull AvailabilityCode availabilityCode, @Nullable ThirdPartyProfileName thirdPartyProfileName, boolean z) {
        this(availabilityCode, thirdPartyProfileName, Integer.MAX_VALUE, z);
    }

    public AvailabilityStatus(@Nonnull AvailabilityCode availabilityCode, int i, boolean z) {
        this(availabilityCode, null, i, z);
    }

    public AvailabilityStatus(@Nonnull AvailabilityCode availabilityCode, @Nullable ThirdPartyProfileName thirdPartyProfileName, int i, boolean z) {
        this.mAvailabilityCode = (AvailabilityCode) Preconditions.checkNotNull(availabilityCode, "availabilityCode");
        this.mSupportedProfile = thirdPartyProfileName;
        this.mMaxHdcpLevel = i;
        this.mIsAutoResolved = z;
    }

    public boolean isAvailable() {
        return this.mAvailabilityCode == AvailabilityCode.AVAILABLE;
    }

    @Nonnull
    public AvailabilityCode getAvailabilityCode() {
        return this.mAvailabilityCode;
    }

    @Nullable
    public ThirdPartyProfileName getSupportedProfile() {
        return this.mSupportedProfile;
    }

    public int getMaxHdcpLevel() {
        return this.mMaxHdcpLevel;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mAvailabilityCode.name());
        if (this.mIsAutoResolved && this.mSupportedProfile != null) {
            sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            sb.append(this.mSupportedProfile.name());
        }
        return sb.toString();
    }

    public boolean isAutoResolved() {
        return this.mIsAutoResolved;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @NonNull
    public String toReportableString() {
        return toString();
    }
}
