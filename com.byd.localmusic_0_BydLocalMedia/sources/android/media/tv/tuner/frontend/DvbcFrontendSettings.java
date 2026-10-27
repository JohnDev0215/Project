package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class DvbcFrontendSettings extends FrontendSettings {
    public static final int ANNEX_A = 1;
    public static final int ANNEX_B = 2;
    public static final int ANNEX_C = 4;
    public static final int ANNEX_UNDEFINED = 0;
    public static final int MODULATION_AUTO = 1;
    public static final int MODULATION_MOD_128QAM = 16;
    public static final int MODULATION_MOD_16QAM = 2;
    public static final int MODULATION_MOD_256QAM = 32;
    public static final int MODULATION_MOD_32QAM = 4;
    public static final int MODULATION_MOD_64QAM = 8;
    public static final int MODULATION_UNDEFINED = 0;
    public static final int OUTER_FEC_OUTER_FEC_NONE = 1;
    public static final int OUTER_FEC_OUTER_FEC_RS = 2;
    public static final int OUTER_FEC_UNDEFINED = 0;
    public static final int SPECTRAL_INVERSION_INVERTED = 2;
    public static final int SPECTRAL_INVERSION_NORMAL = 1;
    public static final int SPECTRAL_INVERSION_UNDEFINED = 0;
    private final int mAnnex;
    private final long mInnerFec;
    private final int mModulation;
    private final int mOuterFec;
    private final int mSpectralInversion;
    private final int mSymbolRate;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Annex {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Modulation {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OuterFec {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SpectralInversion {
    }

    @Override // android.media.tv.tuner.frontend.FrontendSettings
    public int getType() {
        return 4;
    }

    private DvbcFrontendSettings(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        super(i);
        this.mModulation = i2;
        this.mInnerFec = j;
        this.mSymbolRate = i3;
        this.mOuterFec = i4;
        this.mAnnex = i5;
        this.mSpectralInversion = i6;
    }

    public int getModulation() {
        return this.mModulation;
    }

    public long getInnerFec() {
        return this.mInnerFec;
    }

    public int getSymbolRate() {
        return this.mSymbolRate;
    }

    public int getOuterFec() {
        return this.mOuterFec;
    }

    public int getAnnex() {
        return this.mAnnex;
    }

    public int getSpectralInversion() {
        return this.mSpectralInversion;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int mAnnex;
        private int mFrequency;
        private long mInnerFec;
        private int mModulation;
        private int mOuterFec;
        private int mSpectralInversion;
        private int mSymbolRate;

        private Builder() {
            this.mFrequency = 0;
            this.mModulation = 0;
            this.mInnerFec = 0L;
            this.mSymbolRate = 0;
            this.mOuterFec = 0;
            this.mAnnex = 0;
            this.mSpectralInversion = 0;
        }

        public Builder setFrequency(int i) {
            this.mFrequency = i;
            return this;
        }

        public Builder setModulation(int i) {
            this.mModulation = i;
            return this;
        }

        public Builder setInnerFec(long j) {
            this.mInnerFec = j;
            return this;
        }

        public Builder setSymbolRate(int i) {
            this.mSymbolRate = i;
            return this;
        }

        public Builder setOuterFec(int i) {
            this.mOuterFec = i;
            return this;
        }

        public Builder setAnnex(int i) {
            this.mAnnex = i;
            return this;
        }

        public Builder setSpectralInversion(int i) {
            this.mSpectralInversion = i;
            return this;
        }

        public DvbcFrontendSettings build() {
            return new DvbcFrontendSettings(this.mFrequency, this.mModulation, this.mInnerFec, this.mSymbolRate, this.mOuterFec, this.mAnnex, this.mSpectralInversion);
        }
    }
}
