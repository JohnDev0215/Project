package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class DvbtFrontendSettings extends FrontendSettings {
    public static final int BANDWIDTH_10MHZ = 64;
    public static final int BANDWIDTH_1_7MHZ = 32;
    public static final int BANDWIDTH_5MHZ = 16;
    public static final int BANDWIDTH_6MHZ = 8;
    public static final int BANDWIDTH_7MHZ = 4;
    public static final int BANDWIDTH_8MHZ = 2;
    public static final int BANDWIDTH_AUTO = 1;
    public static final int BANDWIDTH_UNDEFINED = 0;
    public static final int CODERATE_1_2 = 2;
    public static final int CODERATE_2_3 = 4;
    public static final int CODERATE_3_4 = 8;
    public static final int CODERATE_3_5 = 64;
    public static final int CODERATE_4_5 = 128;
    public static final int CODERATE_5_6 = 16;
    public static final int CODERATE_6_7 = 256;
    public static final int CODERATE_7_8 = 32;
    public static final int CODERATE_8_9 = 512;
    public static final int CODERATE_AUTO = 1;
    public static final int CODERATE_UNDEFINED = 0;
    public static final int CONSTELLATION_16QAM = 4;
    public static final int CONSTELLATION_256QAM = 16;
    public static final int CONSTELLATION_64QAM = 8;
    public static final int CONSTELLATION_AUTO = 1;
    public static final int CONSTELLATION_QPSK = 2;
    public static final int CONSTELLATION_UNDEFINED = 0;
    public static final int GUARD_INTERVAL_19_128 = 64;
    public static final int GUARD_INTERVAL_19_256 = 128;
    public static final int GUARD_INTERVAL_1_128 = 32;
    public static final int GUARD_INTERVAL_1_16 = 4;
    public static final int GUARD_INTERVAL_1_32 = 2;
    public static final int GUARD_INTERVAL_1_4 = 16;
    public static final int GUARD_INTERVAL_1_8 = 8;
    public static final int GUARD_INTERVAL_AUTO = 1;
    public static final int GUARD_INTERVAL_UNDEFINED = 0;
    public static final int HIERARCHY_1_INDEPTH = 64;
    public static final int HIERARCHY_1_NATIVE = 4;
    public static final int HIERARCHY_2_INDEPTH = 128;
    public static final int HIERARCHY_2_NATIVE = 8;
    public static final int HIERARCHY_4_INDEPTH = 256;
    public static final int HIERARCHY_4_NATIVE = 16;
    public static final int HIERARCHY_AUTO = 1;
    public static final int HIERARCHY_NON_INDEPTH = 32;
    public static final int HIERARCHY_NON_NATIVE = 2;
    public static final int HIERARCHY_UNDEFINED = 0;
    public static final int PLP_MODE_AUTO = 1;
    public static final int PLP_MODE_MANUAL = 2;
    public static final int PLP_MODE_UNDEFINED = 0;
    public static final int STANDARD_AUTO = 1;
    public static final int STANDARD_T = 2;
    public static final int STANDARD_T2 = 4;
    public static final int TRANSMISSION_MODE_16K = 32;
    public static final int TRANSMISSION_MODE_1K = 16;
    public static final int TRANSMISSION_MODE_2K = 2;
    public static final int TRANSMISSION_MODE_32K = 64;
    public static final int TRANSMISSION_MODE_4K = 8;
    public static final int TRANSMISSION_MODE_8K = 4;
    public static final int TRANSMISSION_MODE_AUTO = 1;
    public static final int TRANSMISSION_MODE_UNDEFINED = 0;
    private final int mBandwidth;
    private final int mConstellation;
    private final int mGuardInterval;
    private final int mHierarchy;
    private final int mHpCodeRate;
    private final boolean mIsHighPriority;
    private final boolean mIsMiso;
    private final int mLpCodeRate;
    private final int mPlpGroupId;
    private final int mPlpId;
    private final int mPlpMode;
    private final int mStandard;
    private final int mTransmissionMode;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Bandwidth {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CodeRate {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Constellation {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface GuardInterval {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Hierarchy {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PlpMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Standard {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TransmissionMode {
    }

    @Override // android.media.tv.tuner.frontend.FrontendSettings
    public int getType() {
        return 6;
    }

    private DvbtFrontendSettings(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z, int i9, boolean z2, int i10, int i11, int i12) {
        super(i);
        this.mTransmissionMode = i2;
        this.mBandwidth = i3;
        this.mConstellation = i4;
        this.mHierarchy = i5;
        this.mHpCodeRate = i6;
        this.mLpCodeRate = i7;
        this.mGuardInterval = i8;
        this.mIsHighPriority = z;
        this.mStandard = i9;
        this.mIsMiso = z2;
        this.mPlpMode = i10;
        this.mPlpId = i11;
        this.mPlpGroupId = i12;
    }

    public int getTransmissionMode() {
        return this.mTransmissionMode;
    }

    public int getBandwidth() {
        return this.mBandwidth;
    }

    public int getConstellation() {
        return this.mConstellation;
    }

    public int getHierarchy() {
        return this.mHierarchy;
    }

    public int getHighPriorityCodeRate() {
        return this.mHpCodeRate;
    }

    public int getLowPriorityCodeRate() {
        return this.mLpCodeRate;
    }

    public int getGuardInterval() {
        return this.mGuardInterval;
    }

    public boolean isHighPriority() {
        return this.mIsHighPriority;
    }

    public int getStandard() {
        return this.mStandard;
    }

    public boolean isMiso() {
        return this.mIsMiso;
    }

    public int getPlpMode() {
        return this.mPlpMode;
    }

    public int getPlpId() {
        return this.mPlpId;
    }

    public int getPlpGroupId() {
        return this.mPlpGroupId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int mBandwidth;
        private int mConstellation;
        private int mFrequency;
        private int mGuardInterval;
        private int mHierarchy;
        private int mHpCodeRate;
        private boolean mIsHighPriority;
        private boolean mIsMiso;
        private int mLpCodeRate;
        private int mPlpGroupId;
        private int mPlpId;
        private int mPlpMode;
        private int mStandard;
        private int mTransmissionMode;

        private Builder() {
            this.mFrequency = 0;
            this.mTransmissionMode = 0;
            this.mBandwidth = 0;
            this.mConstellation = 0;
            this.mHierarchy = 0;
            this.mHpCodeRate = 0;
            this.mLpCodeRate = 0;
            this.mGuardInterval = 0;
            this.mIsHighPriority = false;
            this.mStandard = 1;
            this.mIsMiso = false;
            this.mPlpMode = 0;
            this.mPlpId = 0;
            this.mPlpGroupId = 0;
        }

        public Builder setFrequency(int i) {
            this.mFrequency = i;
            return this;
        }

        public Builder setTransmissionMode(int i) {
            this.mTransmissionMode = i;
            return this;
        }

        public Builder setBandwidth(int i) {
            this.mBandwidth = i;
            return this;
        }

        public Builder setConstellation(int i) {
            this.mConstellation = i;
            return this;
        }

        public Builder setHierarchy(int i) {
            this.mHierarchy = i;
            return this;
        }

        public Builder setHighPriorityCodeRate(int i) {
            this.mHpCodeRate = i;
            return this;
        }

        public Builder setLowPriorityCodeRate(int i) {
            this.mLpCodeRate = i;
            return this;
        }

        public Builder setGuardInterval(int i) {
            this.mGuardInterval = i;
            return this;
        }

        public Builder setHighPriority(boolean z) {
            this.mIsHighPriority = z;
            return this;
        }

        public Builder setStandard(int i) {
            this.mStandard = i;
            return this;
        }

        public Builder setMiso(boolean z) {
            this.mIsMiso = z;
            return this;
        }

        public Builder setPlpMode(int i) {
            this.mPlpMode = i;
            return this;
        }

        public Builder setPlpId(int i) {
            this.mPlpId = i;
            return this;
        }

        public Builder setPlpGroupId(int i) {
            this.mPlpGroupId = i;
            return this;
        }

        public DvbtFrontendSettings build() {
            return new DvbtFrontendSettings(this.mFrequency, this.mTransmissionMode, this.mBandwidth, this.mConstellation, this.mHierarchy, this.mHpCodeRate, this.mLpCodeRate, this.mGuardInterval, this.mIsHighPriority, this.mStandard, this.mIsMiso, this.mPlpMode, this.mPlpId, this.mPlpGroupId);
        }
    }
}
