package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class DvbsFrontendSettings extends FrontendSettings {
    public static final int MODULATION_AUTO = 1;
    public static final int MODULATION_MOD_128APSK = 2048;
    public static final int MODULATION_MOD_16APSK = 256;
    public static final int MODULATION_MOD_16PSK = 16;
    public static final int MODULATION_MOD_16QAM = 8;
    public static final int MODULATION_MOD_256APSK = 4096;
    public static final int MODULATION_MOD_32APSK = 512;
    public static final int MODULATION_MOD_32PSK = 32;
    public static final int MODULATION_MOD_64APSK = 1024;
    public static final int MODULATION_MOD_8APSK = 128;
    public static final int MODULATION_MOD_8PSK = 4;
    public static final int MODULATION_MOD_ACM = 64;
    public static final int MODULATION_MOD_QPSK = 2;
    public static final int MODULATION_MOD_RESERVED = 8192;
    public static final int MODULATION_UNDEFINED = 0;
    public static final int PILOT_AUTO = 3;
    public static final int PILOT_OFF = 2;
    public static final int PILOT_ON = 1;
    public static final int PILOT_UNDEFINED = 0;
    public static final int ROLLOFF_0_10 = 5;
    public static final int ROLLOFF_0_15 = 4;
    public static final int ROLLOFF_0_20 = 3;
    public static final int ROLLOFF_0_25 = 2;
    public static final int ROLLOFF_0_35 = 1;
    public static final int ROLLOFF_0_5 = 6;
    public static final int ROLLOFF_UNDEFINED = 0;
    public static final int STANDARD_AUTO = 1;
    public static final int STANDARD_S = 2;
    public static final int STANDARD_S2 = 4;
    public static final int STANDARD_S2X = 8;
    public static final int VCM_MODE_AUTO = 1;
    public static final int VCM_MODE_MANUAL = 2;
    public static final int VCM_MODE_UNDEFINED = 0;
    private final DvbsCodeRate mCodeRate;
    private final int mInputStreamId;
    private final int mModulation;
    private final int mPilot;
    private final int mRolloff;
    private final int mStandard;
    private final int mSymbolRate;
    private final int mVcmMode;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Modulation {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Pilot {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Rolloff {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Standard {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface VcmMode {
    }

    @Override // android.media.tv.tuner.frontend.FrontendSettings
    public int getType() {
        return 5;
    }

    private DvbsFrontendSettings(int i, int i2, DvbsCodeRate dvbsCodeRate, int i3, int i4, int i5, int i6, int i7, int i8) {
        super(i);
        this.mModulation = i2;
        this.mCodeRate = dvbsCodeRate;
        this.mSymbolRate = i3;
        this.mRolloff = i4;
        this.mPilot = i5;
        this.mInputStreamId = i6;
        this.mStandard = i7;
        this.mVcmMode = i8;
    }

    public int getModulation() {
        return this.mModulation;
    }

    public DvbsCodeRate getCodeRate() {
        return this.mCodeRate;
    }

    public int getSymbolRate() {
        return this.mSymbolRate;
    }

    public int getRolloff() {
        return this.mRolloff;
    }

    public int getPilot() {
        return this.mPilot;
    }

    public int getInputStreamId() {
        return this.mInputStreamId;
    }

    public int getStandard() {
        return this.mStandard;
    }

    public int getVcmMode() {
        return this.mVcmMode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private DvbsCodeRate mCodeRate;
        private int mFrequency;
        private int mInputStreamId;
        private int mModulation;
        private int mPilot;
        private int mRolloff;
        private int mStandard;
        private int mSymbolRate;
        private int mVcmMode;

        private Builder() {
            this.mFrequency = 0;
            this.mModulation = 0;
            this.mCodeRate = null;
            this.mSymbolRate = 0;
            this.mRolloff = 0;
            this.mPilot = 0;
            this.mInputStreamId = 65535;
            this.mStandard = 1;
            this.mVcmMode = 0;
        }

        public Builder setFrequency(int i) {
            this.mFrequency = i;
            return this;
        }

        public Builder setModulation(int i) {
            this.mModulation = i;
            return this;
        }

        public Builder setCodeRate(DvbsCodeRate dvbsCodeRate) {
            this.mCodeRate = dvbsCodeRate;
            return this;
        }

        public Builder setSymbolRate(int i) {
            this.mSymbolRate = i;
            return this;
        }

        public Builder setRolloff(int i) {
            this.mRolloff = i;
            return this;
        }

        public Builder setPilot(int i) {
            this.mPilot = i;
            return this;
        }

        public Builder setInputStreamId(int i) {
            this.mInputStreamId = i;
            return this;
        }

        public Builder setStandard(int i) {
            this.mStandard = i;
            return this;
        }

        public Builder setVcmMode(int i) {
            this.mVcmMode = i;
            return this;
        }

        public DvbsFrontendSettings build() {
            return new DvbsFrontendSettings(this.mFrequency, this.mModulation, this.mCodeRate, this.mSymbolRate, this.mRolloff, this.mPilot, this.mInputStreamId, this.mStandard, this.mVcmMode);
        }
    }
}
