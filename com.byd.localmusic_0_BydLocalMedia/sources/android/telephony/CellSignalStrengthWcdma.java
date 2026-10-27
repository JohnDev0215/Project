package android.telephony;

import android.hardware.radio.V1_0.WcdmaSignalStrength;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CellSignalStrengthWcdma extends CellSignalStrength implements Parcelable {
    private static final boolean DBG = false;
    private static final String DEFAULT_LEVEL_CALCULATION_METHOD = "rssi";
    public static final String LEVEL_CALCULATION_METHOD_RSCP = "rscp";
    public static final String LEVEL_CALCULATION_METHOD_RSSI = "rssi";
    private static final String LOG_TAG = "CellSignalStrengthWcdma";
    private static final int WCDMA_RSCP_GOOD = -95;
    private static final int WCDMA_RSCP_MAX = -24;
    private static final int WCDMA_RSCP_MIN = -120;
    private static final int WCDMA_RSCP_MODERATE = -105;
    private static final int WCDMA_RSCP_POOR = -115;
    private static final int WCDMA_RSSI_MAX = -51;
    private static final int WCDMA_RSSI_MIN = -113;
    private static final int WCDMA_RSSI_MODERATE = -97;
    private static final int WCDMA_RSSI_POOR = -107;
    private int mBitErrorRate;
    private int mEcNo;
    private int mLevel;
    private int mRscp;
    private int mRssi;
    private static final int WCDMA_RSSI_GOOD = -87;
    private static final int WCDMA_RSSI_GREAT = -77;
    private static final int[] sRssiThresholds = {-107, -97, WCDMA_RSSI_GOOD, WCDMA_RSSI_GREAT};
    private static final int WCDMA_RSCP_GREAT = -85;
    private static final int[] sRscpThresholds = {-115, -105, -95, WCDMA_RSCP_GREAT};
    private static final CellSignalStrengthWcdma sInvalid = new CellSignalStrengthWcdma();
    public static final Parcelable.Creator<CellSignalStrengthWcdma> CREATOR = new Parcelable.Creator<CellSignalStrengthWcdma>() { // from class: android.telephony.CellSignalStrengthWcdma.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellSignalStrengthWcdma createFromParcel(Parcel parcel) {
            return new CellSignalStrengthWcdma(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellSignalStrengthWcdma[] newArray(int i) {
            return new CellSignalStrengthWcdma[i];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface LevelCalculationMethod {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CellSignalStrengthWcdma() {
        setDefaultValues();
    }

    public CellSignalStrengthWcdma(int i, int i2, int i3, int i4) {
        this.mRssi = inRangeOrUnavailable(i, -113, -51);
        this.mBitErrorRate = inRangeOrUnavailable(i2, 0, 7, 99);
        this.mRscp = inRangeOrUnavailable(i3, -120, -24);
        this.mEcNo = inRangeOrUnavailable(i4, -24, 1);
        updateLevel(null, null);
    }

    public CellSignalStrengthWcdma(WcdmaSignalStrength wcdmaSignalStrength) {
        this(getRssiDbmFromAsu(wcdmaSignalStrength.signalStrength), wcdmaSignalStrength.bitErrorRate, Integer.MAX_VALUE, Integer.MAX_VALUE);
        if (this.mRssi == Integer.MAX_VALUE && this.mRscp == Integer.MAX_VALUE) {
            setDefaultValues();
        }
    }

    public CellSignalStrengthWcdma(android.hardware.radio.V1_2.WcdmaSignalStrength wcdmaSignalStrength) {
        this(getRssiDbmFromAsu(wcdmaSignalStrength.base.signalStrength), wcdmaSignalStrength.base.bitErrorRate, getRscpDbmFromAsu(wcdmaSignalStrength.rscp), getEcNoDbFromAsu(wcdmaSignalStrength.ecno));
        if (this.mRssi == Integer.MAX_VALUE && this.mRscp == Integer.MAX_VALUE) {
            setDefaultValues();
        }
    }

    public CellSignalStrengthWcdma(CellSignalStrengthWcdma cellSignalStrengthWcdma) {
        copyFrom(cellSignalStrengthWcdma);
    }

    protected void copyFrom(CellSignalStrengthWcdma cellSignalStrengthWcdma) {
        this.mRssi = cellSignalStrengthWcdma.mRssi;
        this.mBitErrorRate = cellSignalStrengthWcdma.mBitErrorRate;
        this.mRscp = cellSignalStrengthWcdma.mRscp;
        this.mEcNo = cellSignalStrengthWcdma.mEcNo;
        this.mLevel = cellSignalStrengthWcdma.mLevel;
    }

    @Override // android.telephony.CellSignalStrength
    public CellSignalStrengthWcdma copy() {
        return new CellSignalStrengthWcdma(this);
    }

    @Override // android.telephony.CellSignalStrength
    public void setDefaultValues() {
        this.mRssi = Integer.MAX_VALUE;
        this.mBitErrorRate = Integer.MAX_VALUE;
        this.mRscp = Integer.MAX_VALUE;
        this.mEcNo = Integer.MAX_VALUE;
        this.mLevel = 0;
    }

    @Override // android.telephony.CellSignalStrength
    public int getLevel() {
        return this.mLevel;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004a  */
    @Override // android.telephony.CellSignalStrength
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateLevel(android.os.PersistableBundle r7, android.telephony.ServiceState r8) {
        /*
            r6 = this;
            r8 = 4
            java.lang.String r0 = "rssi"
            if (r7 != 0) goto La
            int[] r7 = android.telephony.CellSignalStrengthWcdma.sRscpThresholds
            r1 = r0
            goto L26
        La:
            java.lang.String r1 = "wcdma_default_signal_strength_measurement_string"
            java.lang.String r1 = r7.getString(r1, r0)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L18
            r1 = r0
        L18:
            java.lang.String r2 = "wcdma_rscp_thresholds_int_array"
            int[] r7 = r7.getIntArray(r2)
            if (r7 == 0) goto L24
            int r2 = r7.length
            if (r2 == r8) goto L26
        L24:
            int[] r7 = android.telephony.CellSignalStrengthWcdma.sRscpThresholds
        L26:
            int r2 = r1.hashCode()
            r3 = 3509870(0x358e6e, float:4.918375E-39)
            r4 = 2
            r5 = 0
            if (r2 == r3) goto L3f
            r3 = 3510359(0x359057, float:4.91906E-39)
            if (r2 == r3) goto L37
            goto L4a
        L37:
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L4a
            r0 = r4
            goto L4b
        L3f:
            java.lang.String r0 = "rscp"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L4a
            r0 = r5
            goto L4b
        L4a:
            r0 = -1
        L4b:
            if (r0 == 0) goto L83
            if (r0 == r4) goto L63
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "Invalid Level Calculation Method for CellSignalStrengthWcdma = "
            r7.append(r0)
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            loge(r7)
        L63:
            int r7 = r6.mRssi
            r0 = -113(0xffffffffffffff8f, float:NaN)
            if (r7 < r0) goto L80
            r0 = -51
            if (r7 <= r0) goto L6e
            goto L80
        L6e:
            if (r8 <= 0) goto L7d
            int r7 = r6.mRssi
            int[] r0 = android.telephony.CellSignalStrengthWcdma.sRssiThresholds
            int r1 = r8 + (-1)
            r0 = r0[r1]
            if (r7 >= r0) goto L7d
            int r8 = r8 + (-1)
            goto L6e
        L7d:
            r6.mLevel = r8
            return
        L80:
            r6.mLevel = r5
            return
        L83:
            int r0 = r6.mRscp
            r1 = -120(0xffffffffffffff88, float:NaN)
            if (r0 < r1) goto L9e
            r1 = -24
            if (r0 <= r1) goto L8e
            goto L9e
        L8e:
            if (r8 <= 0) goto L9b
            int r0 = r6.mRscp
            int r1 = r8 + (-1)
            r1 = r7[r1]
            if (r0 >= r1) goto L9b
            int r8 = r8 + (-1)
            goto L8e
        L9b:
            r6.mLevel = r8
            return
        L9e:
            r6.mLevel = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.CellSignalStrengthWcdma.updateLevel(android.os.PersistableBundle, android.telephony.ServiceState):void");
    }

    @Override // android.telephony.CellSignalStrength
    public int getDbm() {
        int i = this.mRscp;
        return i != Integer.MAX_VALUE ? i : this.mRssi;
    }

    @Override // android.telephony.CellSignalStrength
    public int getAsuLevel() {
        int i = this.mRscp;
        if (i != Integer.MAX_VALUE) {
            return getAsuFromRscpDbm(i);
        }
        int i2 = this.mRssi;
        return i2 != Integer.MAX_VALUE ? getAsuFromRssiDbm(i2) : getAsuFromRscpDbm(Integer.MAX_VALUE);
    }

    public int getRssi() {
        return this.mRssi;
    }

    public int getRscp() {
        return this.mRscp;
    }

    public int getEcNo() {
        return this.mEcNo;
    }

    public int getBitErrorRate() {
        return this.mBitErrorRate;
    }

    @Override // android.telephony.CellSignalStrength
    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mRssi), Integer.valueOf(this.mBitErrorRate), Integer.valueOf(this.mRscp), Integer.valueOf(this.mEcNo), Integer.valueOf(this.mLevel));
    }

    @Override // android.telephony.CellSignalStrength
    public boolean isValid() {
        return !equals(sInvalid);
    }

    @Override // android.telephony.CellSignalStrength
    public boolean equals(Object obj) {
        if (!(obj instanceof CellSignalStrengthWcdma)) {
            return false;
        }
        CellSignalStrengthWcdma cellSignalStrengthWcdma = (CellSignalStrengthWcdma) obj;
        return this.mRssi == cellSignalStrengthWcdma.mRssi && this.mBitErrorRate == cellSignalStrengthWcdma.mBitErrorRate && this.mRscp == cellSignalStrengthWcdma.mRscp && this.mEcNo == cellSignalStrengthWcdma.mEcNo && this.mLevel == cellSignalStrengthWcdma.mLevel;
    }

    public String toString() {
        return "CellSignalStrengthWcdma: ss=" + this.mRssi + " ber=" + this.mBitErrorRate + " rscp=" + this.mRscp + " ecno=" + this.mEcNo + " level=" + this.mLevel;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mRssi);
        parcel.writeInt(this.mBitErrorRate);
        parcel.writeInt(this.mRscp);
        parcel.writeInt(this.mEcNo);
        parcel.writeInt(this.mLevel);
    }

    private CellSignalStrengthWcdma(Parcel parcel) {
        this.mRssi = parcel.readInt();
        this.mBitErrorRate = parcel.readInt();
        this.mRscp = parcel.readInt();
        this.mEcNo = parcel.readInt();
        this.mLevel = parcel.readInt();
    }

    private static void log(String str) {
        com.android.telephony.Rlog.w(LOG_TAG, str);
    }

    private static void loge(String str) {
        com.android.telephony.Rlog.e(LOG_TAG, str);
    }
}
