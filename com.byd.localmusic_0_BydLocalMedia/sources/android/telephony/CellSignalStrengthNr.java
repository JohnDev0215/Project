package android.telephony;

import android.hardware.radio.V1_4.NrSignalStrength;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CellSignalStrengthNr extends CellSignalStrength implements Parcelable {
    private static final String TAG = "CellSignalStrengthNr";
    public static final int UNKNOWN_ASU_LEVEL = 99;
    public static final int USE_SSRSRP = 1;
    public static final int USE_SSRSRQ = 2;
    public static final int USE_SSSINR = 4;
    private static final boolean VDBG = false;
    private int mCsiRsrp;
    private int mCsiRsrq;
    private int mCsiSinr;
    private int mLevel;
    private int mParametersUseForLevel;
    private int mSsRsrp;
    private int[] mSsRsrpThresholds;
    private int mSsRsrq;
    private int[] mSsRsrqThresholds;
    private int mSsSinr;
    private int[] mSsSinrThresholds;
    private static final CellSignalStrengthNr sInvalid = new CellSignalStrengthNr();
    public static final Parcelable.Creator<CellSignalStrengthNr> CREATOR = new Parcelable.Creator<CellSignalStrengthNr>() { // from class: android.telephony.CellSignalStrengthNr.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellSignalStrengthNr createFromParcel(Parcel parcel) {
            return new CellSignalStrengthNr(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellSignalStrengthNr[] newArray(int i) {
            return new CellSignalStrengthNr[i];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface SignalLevelAndReportCriteriaSource {
    }

    private static int flip(int i) {
        return i != Integer.MAX_VALUE ? -i : i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CellSignalStrengthNr() {
        this.mSsRsrpThresholds = new int[]{-110, -90, -80, -65};
        this.mSsRsrqThresholds = new int[]{-31, -19, -7, 6};
        this.mSsSinrThresholds = new int[]{-5, 5, 15, 30};
        setDefaultValues();
    }

    public CellSignalStrengthNr(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mSsRsrpThresholds = new int[]{-110, -90, -80, -65};
        this.mSsRsrqThresholds = new int[]{-31, -19, -7, 6};
        this.mSsSinrThresholds = new int[]{-5, 5, 15, 30};
        this.mCsiRsrp = inRangeOrUnavailable(i, -140, -44);
        this.mCsiRsrq = inRangeOrUnavailable(i2, -20, -3);
        this.mCsiSinr = inRangeOrUnavailable(i3, -23, 23);
        this.mSsRsrp = inRangeOrUnavailable(i4, -140, -44);
        this.mSsRsrq = inRangeOrUnavailable(i5, -43, 20);
        this.mSsSinr = inRangeOrUnavailable(i6, -23, 40);
        updateLevel(null, null);
    }

    public CellSignalStrengthNr(NrSignalStrength nrSignalStrength) {
        this(flip(nrSignalStrength.csiRsrp), flip(nrSignalStrength.csiRsrq), nrSignalStrength.csiSinr, flip(nrSignalStrength.ssRsrp), flip(nrSignalStrength.ssRsrq), nrSignalStrength.ssSinr);
    }

    public int getSsRsrp() {
        return this.mSsRsrp;
    }

    public int getSsRsrq() {
        return this.mSsRsrq;
    }

    public int getSsSinr() {
        return this.mSsSinr;
    }

    public int getCsiRsrp() {
        return this.mCsiRsrp;
    }

    public int getCsiRsrq() {
        return this.mCsiRsrq;
    }

    public int getCsiSinr() {
        return this.mCsiSinr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCsiRsrp);
        parcel.writeInt(this.mCsiRsrq);
        parcel.writeInt(this.mCsiSinr);
        parcel.writeInt(this.mSsRsrp);
        parcel.writeInt(this.mSsRsrq);
        parcel.writeInt(this.mSsSinr);
        parcel.writeInt(this.mLevel);
    }

    private CellSignalStrengthNr(Parcel parcel) {
        this.mSsRsrpThresholds = new int[]{-110, -90, -80, -65};
        this.mSsRsrqThresholds = new int[]{-31, -19, -7, 6};
        this.mSsSinrThresholds = new int[]{-5, 5, 15, 30};
        this.mCsiRsrp = parcel.readInt();
        this.mCsiRsrq = parcel.readInt();
        this.mCsiSinr = parcel.readInt();
        this.mSsRsrp = parcel.readInt();
        this.mSsRsrq = parcel.readInt();
        this.mSsSinr = parcel.readInt();
        this.mLevel = parcel.readInt();
    }

    @Override // android.telephony.CellSignalStrength
    public void setDefaultValues() {
        this.mCsiRsrp = Integer.MAX_VALUE;
        this.mCsiRsrq = Integer.MAX_VALUE;
        this.mCsiSinr = Integer.MAX_VALUE;
        this.mSsRsrp = Integer.MAX_VALUE;
        this.mSsRsrq = Integer.MAX_VALUE;
        this.mSsSinr = Integer.MAX_VALUE;
        this.mLevel = 0;
        this.mParametersUseForLevel = 1;
    }

    @Override // android.telephony.CellSignalStrength
    public int getLevel() {
        return this.mLevel;
    }

    private boolean isLevelForParameter(int i) {
        return (this.mParametersUseForLevel & i) == i;
    }

    @Override // android.telephony.CellSignalStrength
    public void updateLevel(PersistableBundle persistableBundle, ServiceState serviceState) {
        if (persistableBundle == null) {
            this.mParametersUseForLevel = 1;
        } else {
            this.mParametersUseForLevel = persistableBundle.getInt(CarrierConfigManager.KEY_PARAMETERS_USE_FOR_5G_NR_SIGNAL_BAR_INT, 1);
            this.mSsRsrpThresholds = persistableBundle.getIntArray(CarrierConfigManager.KEY_5G_NR_SSRSRP_THRESHOLDS_INT_ARRAY);
            this.mSsRsrqThresholds = persistableBundle.getIntArray(CarrierConfigManager.KEY_5G_NR_SSRSRQ_THRESHOLDS_INT_ARRAY);
            this.mSsSinrThresholds = persistableBundle.getIntArray(CarrierConfigManager.KEY_5G_NR_SSSINR_THRESHOLDS_INT_ARRAY);
        }
        this.mLevel = Math.min(Math.min(isLevelForParameter(1) ? updateLevelWithMeasure(this.mSsRsrp, this.mSsRsrpThresholds) : Integer.MAX_VALUE, isLevelForParameter(2) ? updateLevelWithMeasure(this.mSsRsrq, this.mSsRsrqThresholds) : Integer.MAX_VALUE), isLevelForParameter(4) ? updateLevelWithMeasure(this.mSsSinr, this.mSsSinrThresholds) : Integer.MAX_VALUE);
    }

    private int updateLevelWithMeasure(int i, int[] iArr) {
        if (i != Integer.MAX_VALUE) {
            if (i > iArr[3]) {
                return 4;
            }
            if (i > iArr[2]) {
                return 3;
            }
            if (i > iArr[1]) {
                return 2;
            }
            if (i > iArr[0]) {
                return 1;
            }
        }
        return 0;
    }

    @Override // android.telephony.CellSignalStrength
    public int getAsuLevel() {
        int dbm = getDbm();
        if (dbm == Integer.MAX_VALUE) {
            return 99;
        }
        if (dbm <= -140) {
            return 0;
        }
        if (dbm >= -43) {
            return 97;
        }
        return dbm + 140;
    }

    @Override // android.telephony.CellSignalStrength
    public int getDbm() {
        return this.mSsRsrp;
    }

    public CellSignalStrengthNr(CellSignalStrengthNr cellSignalStrengthNr) {
        this.mSsRsrpThresholds = new int[]{-110, -90, -80, -65};
        this.mSsRsrqThresholds = new int[]{-31, -19, -7, 6};
        this.mSsSinrThresholds = new int[]{-5, 5, 15, 30};
        this.mCsiRsrp = cellSignalStrengthNr.mCsiRsrp;
        this.mCsiRsrq = cellSignalStrengthNr.mCsiRsrq;
        this.mCsiSinr = cellSignalStrengthNr.mCsiSinr;
        this.mSsRsrp = cellSignalStrengthNr.mSsRsrp;
        this.mSsRsrq = cellSignalStrengthNr.mSsRsrq;
        this.mSsSinr = cellSignalStrengthNr.mSsSinr;
        this.mLevel = cellSignalStrengthNr.mLevel;
        this.mParametersUseForLevel = cellSignalStrengthNr.mParametersUseForLevel;
    }

    @Override // android.telephony.CellSignalStrength
    public CellSignalStrengthNr copy() {
        return new CellSignalStrengthNr(this);
    }

    @Override // android.telephony.CellSignalStrength
    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mCsiRsrp), Integer.valueOf(this.mCsiRsrq), Integer.valueOf(this.mCsiSinr), Integer.valueOf(this.mSsRsrp), Integer.valueOf(this.mSsRsrq), Integer.valueOf(this.mSsSinr), Integer.valueOf(this.mLevel));
    }

    @Override // android.telephony.CellSignalStrength
    public boolean isValid() {
        return !equals(sInvalid);
    }

    @Override // android.telephony.CellSignalStrength
    public boolean equals(Object obj) {
        if (!(obj instanceof CellSignalStrengthNr)) {
            return false;
        }
        CellSignalStrengthNr cellSignalStrengthNr = (CellSignalStrengthNr) obj;
        return this.mCsiRsrp == cellSignalStrengthNr.mCsiRsrp && this.mCsiRsrq == cellSignalStrengthNr.mCsiRsrq && this.mCsiSinr == cellSignalStrengthNr.mCsiSinr && this.mSsRsrp == cellSignalStrengthNr.mSsRsrp && this.mSsRsrq == cellSignalStrengthNr.mSsRsrq && this.mSsSinr == cellSignalStrengthNr.mSsSinr && this.mLevel == cellSignalStrengthNr.mLevel;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CellSignalStrengthNr:{");
        sb.append(" csiRsrp = " + this.mCsiRsrp);
        sb.append(" csiRsrq = " + this.mCsiRsrq);
        sb.append(" csiSinr = " + this.mCsiSinr);
        sb.append(" ssRsrp = " + this.mSsRsrp);
        sb.append(" ssRsrq = " + this.mSsRsrq);
        sb.append(" ssSinr = " + this.mSsSinr);
        sb.append(" level = " + this.mLevel);
        sb.append(" parametersUseForLevel = " + this.mParametersUseForLevel);
        sb.append(" }");
        return sb.toString();
    }
}
