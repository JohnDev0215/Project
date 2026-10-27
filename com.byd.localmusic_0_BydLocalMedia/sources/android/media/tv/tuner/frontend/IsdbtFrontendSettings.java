package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class IsdbtFrontendSettings extends FrontendSettings {
    public static final int BANDWIDTH_6MHZ = 8;
    public static final int BANDWIDTH_7MHZ = 4;
    public static final int BANDWIDTH_8MHZ = 2;
    public static final int BANDWIDTH_AUTO = 1;
    public static final int BANDWIDTH_UNDEFINED = 0;
    public static final int MODE_1 = 2;
    public static final int MODE_2 = 4;
    public static final int MODE_3 = 8;
    public static final int MODE_AUTO = 1;
    public static final int MODE_UNDEFINED = 0;
    public static final int MODULATION_AUTO = 1;
    public static final int MODULATION_MOD_16QAM = 8;
    public static final int MODULATION_MOD_64QAM = 16;
    public static final int MODULATION_MOD_DQPSK = 2;
    public static final int MODULATION_MOD_QPSK = 4;
    public static final int MODULATION_UNDEFINED = 0;
    private final int mBandwidth;
    private final int mCodeRate;
    private final int mGuardInterval;
    private final int mMode;
    private final int mModulation;
    private final int mServiceAreaId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Bandwidth {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Modulation {
    }

    @Override // android.media.tv.tuner.frontend.FrontendSettings
    public int getType() {
        return 9;
    }

    private IsdbtFrontendSettings(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        super(i);
        this.mModulation = i2;
        this.mBandwidth = i3;
        this.mMode = i4;
        this.mCodeRate = i5;
        this.mGuardInterval = i6;
        this.mServiceAreaId = i7;
    }

    public int getModulation() {
        return this.mModulation;
    }

    public int getBandwidth() {
        return this.mBandwidth;
    }

    public int getMode() {
        return this.mMode;
    }

    public int getCodeRate() {
        return this.mCodeRate;
    }

    public int getGuardInterval() {
        return this.mGuardInterval;
    }

    public int getServiceAreaId() {
        return this.mServiceAreaId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int mBandwidth;
        private int mCodeRate;
        private int mFrequency;
        private int mGuardInterval;
        private int mMode;
        private int mModulation;
        private int mServiceAreaId;

        private Builder() {
            this.mFrequency = 0;
            this.mModulation = 0;
            this.mBandwidth = 0;
            this.mMode = 0;
            this.mCodeRate = 0;
            this.mGuardInterval = 0;
            this.mServiceAreaId = 0;
        }

        public Builder setFrequency(int i) {
            this.mFrequency = i;
            return this;
        }

        public Builder setModulation(int i) {
            this.mModulation = i;
            return this;
        }

        public Builder setBandwidth(int i) {
            this.mBandwidth = i;
            return this;
        }

        public Builder setMode(int i) {
            this.mMode = i;
            return this;
        }

        public Builder setCodeRate(int i) {
            this.mCodeRate = i;
            return this;
        }

        public Builder setGuardInterval(int i) {
            this.mGuardInterval = i;
            return this;
        }

        public Builder setServiceAreaId(int i) {
            this.mServiceAreaId = i;
            return this;
        }

        public IsdbtFrontendSettings build() {
            return new IsdbtFrontendSettings(this.mFrequency, this.mModulation, this.mBandwidth, this.mMode, this.mCodeRate, this.mGuardInterval, this.mServiceAreaId);
        }
    }
}
