package android.media.tv.tuner.frontend;

import android.annotation.SystemApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class FrontendStatus {
    public static final int FRONTEND_STATUS_TYPE_AGC = 14;
    public static final int FRONTEND_STATUS_TYPE_ATSC3_PLP_INFO = 21;
    public static final int FRONTEND_STATUS_TYPE_BER = 2;
    public static final int FRONTEND_STATUS_TYPE_DEMOD_LOCK = 0;
    public static final int FRONTEND_STATUS_TYPE_EWBS = 13;
    public static final int FRONTEND_STATUS_TYPE_FEC = 8;
    public static final int FRONTEND_STATUS_TYPE_FREQ_OFFSET = 18;
    public static final int FRONTEND_STATUS_TYPE_HIERARCHY = 19;
    public static final int FRONTEND_STATUS_TYPE_LAYER_ERROR = 16;
    public static final int FRONTEND_STATUS_TYPE_LNA = 15;
    public static final int FRONTEND_STATUS_TYPE_LNB_VOLTAGE = 11;
    public static final int FRONTEND_STATUS_TYPE_MER = 17;
    public static final int FRONTEND_STATUS_TYPE_MODULATION = 9;
    public static final int FRONTEND_STATUS_TYPE_PER = 3;
    public static final int FRONTEND_STATUS_TYPE_PLP_ID = 12;
    public static final int FRONTEND_STATUS_TYPE_PRE_BER = 4;
    public static final int FRONTEND_STATUS_TYPE_RF_LOCK = 20;
    public static final int FRONTEND_STATUS_TYPE_SIGNAL_QUALITY = 5;
    public static final int FRONTEND_STATUS_TYPE_SIGNAL_STRENGTH = 6;
    public static final int FRONTEND_STATUS_TYPE_SNR = 1;
    public static final int FRONTEND_STATUS_TYPE_SPECTRAL = 10;
    public static final int FRONTEND_STATUS_TYPE_SYMBOL_RATE = 7;
    private Integer mAgc;
    private Integer mBer;
    private Integer mFreqOffset;
    private Integer mHierarchy;
    private Long mInnerFec;
    private Integer mInversion;
    private Boolean mIsDemodLocked;
    private Boolean mIsEwbs;
    private boolean[] mIsLayerErrors;
    private Boolean mIsLnaOn;
    private Boolean mIsRfLocked;
    private Integer mLnbVoltage;
    private Integer mMer;
    private Integer mModulation;
    private Integer mPer;
    private Integer mPerBer;
    private Integer mPlpId;
    private Atsc3PlpTuningInfo[] mPlpInfo;
    private Integer mSignalQuality;
    private Integer mSignalStrength;
    private Integer mSnr;
    private Integer mSymbolRate;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FrontendModulation {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FrontendStatusType {
    }

    private FrontendStatus() {
    }

    public boolean isDemodLocked() {
        Boolean bool = this.mIsDemodLocked;
        if (bool == null) {
            throw new IllegalStateException();
        }
        return bool.booleanValue();
    }

    public int getSnr() {
        Integer num = this.mSnr;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getBer() {
        Integer num = this.mBer;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getPer() {
        Integer num = this.mPer;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getPerBer() {
        Integer num = this.mPerBer;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getSignalQuality() {
        Integer num = this.mSignalQuality;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getSignalStrength() {
        Integer num = this.mSignalStrength;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getSymbolRate() {
        Integer num = this.mSymbolRate;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public long getInnerFec() {
        Long l = this.mInnerFec;
        if (l == null) {
            throw new IllegalStateException();
        }
        return l.longValue();
    }

    public int getModulation() {
        Integer num = this.mModulation;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getSpectralInversion() {
        Integer num = this.mInversion;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getLnbVoltage() {
        Integer num = this.mLnbVoltage;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getPlpId() {
        Integer num = this.mPlpId;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public boolean isEwbs() {
        Boolean bool = this.mIsEwbs;
        if (bool == null) {
            throw new IllegalStateException();
        }
        return bool.booleanValue();
    }

    public int getAgc() {
        Integer num = this.mAgc;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public boolean isLnaOn() {
        Boolean bool = this.mIsLnaOn;
        if (bool == null) {
            throw new IllegalStateException();
        }
        return bool.booleanValue();
    }

    public boolean[] getLayerErrors() {
        boolean[] zArr = this.mIsLayerErrors;
        if (zArr != null) {
            return zArr;
        }
        throw new IllegalStateException();
    }

    public int getMer() {
        Integer num = this.mMer;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getFreqOffset() {
        Integer num = this.mFreqOffset;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public int getHierarchy() {
        Integer num = this.mHierarchy;
        if (num == null) {
            throw new IllegalStateException();
        }
        return num.intValue();
    }

    public boolean isRfLocked() {
        Boolean bool = this.mIsRfLocked;
        if (bool == null) {
            throw new IllegalStateException();
        }
        return bool.booleanValue();
    }

    public Atsc3PlpTuningInfo[] getAtsc3PlpTuningInfo() {
        Atsc3PlpTuningInfo[] atsc3PlpTuningInfoArr = this.mPlpInfo;
        if (atsc3PlpTuningInfoArr != null) {
            return atsc3PlpTuningInfoArr;
        }
        throw new IllegalStateException();
    }

    public static class Atsc3PlpTuningInfo {
        private final boolean mIsLocked;
        private final int mPlpId;
        private final int mUec;

        private Atsc3PlpTuningInfo(int i, boolean z, int i2) {
            this.mPlpId = i;
            this.mIsLocked = z;
            this.mUec = i2;
        }

        public int getPlpId() {
            return this.mPlpId;
        }

        public boolean isLocked() {
            return this.mIsLocked;
        }

        public int getUec() {
            return this.mUec;
        }
    }
}
