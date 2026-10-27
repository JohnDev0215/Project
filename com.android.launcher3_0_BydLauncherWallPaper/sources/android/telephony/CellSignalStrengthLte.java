package android.telephony;

import android.content.pm.PackageManager;
import android.hardware.radio.V1_0.LteSignalStrength;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CellSignalStrengthLte extends CellSignalStrength implements Parcelable {
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellSignalStrengthLte";
    private static final int MAX_LTE_RSRP = -44;
    private static final int MIN_LTE_RSRP = -140;
    private static final int SIGNAL_STRENGTH_LTE_RSSI_ASU_UNKNOWN = 99;
    private static final int SIGNAL_STRENGTH_LTE_RSSI_VALID_ASU_MAX_VALUE = 31;
    private static final int SIGNAL_STRENGTH_LTE_RSSI_VALID_ASU_MIN_VALUE = 0;
    public static final int USE_RSRP = 1;
    public static final int USE_RSRQ = 2;
    public static final int USE_RSSNR = 4;
    private static final int sRsrpBoost = 0;
    private int mCqi;
    private int mLevel;
    private int mParametersUseForLevel;
    private int mRsrp;
    private int mRsrq;
    private int mRssi;
    private int mRssnr;
    private int mSignalStrength;
    private int mTimingAdvance;
    private static final int[] sRsrpThresholds = {PackageManager.INSTALL_FAILED_ABORTED, PackageManager.INSTALL_PARSE_FAILED_CERTIFICATE_ENCODING, -95, -85};
    private static final int[] sRsrqThresholds = {-19, -17, -14, -12};
    private static final int[] sRssnrThresholds = {-3, 1, 5, 13};
    private static final CellSignalStrengthLte sInvalid = new CellSignalStrengthLte();
    public static final Parcelable.Creator<CellSignalStrengthLte> CREATOR = new Parcelable.Creator<CellSignalStrengthLte>() { // from class: android.telephony.CellSignalStrengthLte.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellSignalStrengthLte createFromParcel(Parcel parcel) {
            return new CellSignalStrengthLte(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellSignalStrengthLte[] newArray(int i) {
            return new CellSignalStrengthLte[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CellSignalStrengthLte() {
        setDefaultValues();
    }

    public CellSignalStrengthLte(int i, int i2, int i3, int i4, int i5, int i6) {
        int iInRangeOrUnavailable = inRangeOrUnavailable(i, -113, -51);
        this.mRssi = iInRangeOrUnavailable;
        this.mSignalStrength = iInRangeOrUnavailable;
        this.mRsrp = inRangeOrUnavailable(i2, MIN_LTE_RSRP, -43);
        this.mRsrq = inRangeOrUnavailable(i3, -34, 3);
        this.mRssnr = inRangeOrUnavailable(i4, -20, 30);
        this.mCqi = inRangeOrUnavailable(i5, 0, 15);
        this.mTimingAdvance = inRangeOrUnavailable(i6, 0, 1282);
        updateLevel(null, null);
    }

    public CellSignalStrengthLte(LteSignalStrength lteSignalStrength) {
        this(convertRssiAsuToDBm(lteSignalStrength.signalStrength), lteSignalStrength.rsrp != Integer.MAX_VALUE ? -lteSignalStrength.rsrp : lteSignalStrength.rsrp, lteSignalStrength.rsrq != Integer.MAX_VALUE ? -lteSignalStrength.rsrq : lteSignalStrength.rsrq, convertRssnrUnitFromTenDbToDB(lteSignalStrength.rssnr), lteSignalStrength.cqi, lteSignalStrength.timingAdvance);
    }

    public CellSignalStrengthLte(CellSignalStrengthLte cellSignalStrengthLte) {
        copyFrom(cellSignalStrengthLte);
    }

    protected void copyFrom(CellSignalStrengthLte cellSignalStrengthLte) {
        this.mSignalStrength = cellSignalStrengthLte.mSignalStrength;
        this.mRssi = cellSignalStrengthLte.mRssi;
        this.mRsrp = cellSignalStrengthLte.mRsrp;
        this.mRsrq = cellSignalStrengthLte.mRsrq;
        this.mRssnr = cellSignalStrengthLte.mRssnr;
        this.mCqi = cellSignalStrengthLte.mCqi;
        this.mTimingAdvance = cellSignalStrengthLte.mTimingAdvance;
        this.mLevel = cellSignalStrengthLte.mLevel;
        this.mParametersUseForLevel = cellSignalStrengthLte.mParametersUseForLevel;
    }

    @Override // android.telephony.CellSignalStrength
    public CellSignalStrengthLte copy() {
        return new CellSignalStrengthLte(this);
    }

    @Override // android.telephony.CellSignalStrength
    public void setDefaultValues() {
        this.mSignalStrength = Integer.MAX_VALUE;
        this.mRssi = Integer.MAX_VALUE;
        this.mRsrp = Integer.MAX_VALUE;
        this.mRsrq = Integer.MAX_VALUE;
        this.mRssnr = Integer.MAX_VALUE;
        this.mCqi = Integer.MAX_VALUE;
        this.mTimingAdvance = Integer.MAX_VALUE;
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

    /* JADX WARN: Removed duplicated region for block: B:40:0x009c  */
    @Override // android.telephony.CellSignalStrength
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateLevel(android.os.PersistableBundle r8, android.telephony.ServiceState r9) {
        /*
            r7 = this;
            r0 = 1
            r1 = 0
            if (r8 != 0) goto L11
            r7.mParametersUseForLevel = r0
            int[] r8 = android.telephony.CellSignalStrengthLte.sRsrpThresholds
            int[] r2 = android.telephony.CellSignalStrengthLte.sRsrqThresholds
            int[] r3 = android.telephony.CellSignalStrengthLte.sRssnrThresholds
            r4 = r3
            r3 = r2
            r2 = r8
            r8 = r1
            goto L3f
        L11:
            java.lang.String r2 = "parameters_used_for_lte_signal_bar_int"
            int r2 = r8.getInt(r2)
            r7.mParametersUseForLevel = r2
            java.lang.String r2 = "lte_rsrp_thresholds_int_array"
            int[] r2 = r8.getIntArray(r2)
            if (r2 != 0) goto L24
            int[] r2 = android.telephony.CellSignalStrengthLte.sRsrpThresholds
        L24:
            java.lang.String r3 = "lte_rsrq_thresholds_int_array"
            int[] r3 = r8.getIntArray(r3)
            if (r3 != 0) goto L2e
            int[] r3 = android.telephony.CellSignalStrengthLte.sRsrqThresholds
        L2e:
            java.lang.String r4 = "lte_rssnr_thresholds_int_array"
            int[] r4 = r8.getIntArray(r4)
            if (r4 != 0) goto L38
            int[] r4 = android.telephony.CellSignalStrengthLte.sRssnrThresholds
        L38:
            java.lang.String r5 = "use_only_rsrp_for_lte_signal_bar_bool"
            boolean r8 = r8.getBoolean(r5, r1)
        L3f:
            if (r9 == 0) goto L46
            int r9 = r9.getLteEarfcnRsrpBoost()
            goto L47
        L46:
            r9 = r1
        L47:
            int r5 = r7.mRsrp
            int r5 = r5 + r9
            r9 = -140(0xffffffffffffff74, float:NaN)
            r6 = -44
            int r9 = inRangeOrUnavailable(r5, r9, r6)
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r8 == 0) goto L60
            int r8 = r7.updateLevelWithMeasure(r9, r2)
            if (r8 == r5) goto L60
            r7.mLevel = r8
            return
        L60:
            boolean r8 = r7.isLevelForParameter(r0)
            if (r8 == 0) goto L6b
            int r8 = r7.updateLevelWithMeasure(r9, r2)
            goto L6c
        L6b:
            r8 = r5
        L6c:
            r9 = 2
            boolean r2 = r7.isLevelForParameter(r9)
            if (r2 == 0) goto L7a
            int r2 = r7.mRsrq
            int r2 = r7.updateLevelWithMeasure(r2, r3)
            goto L7b
        L7a:
            r2 = r5
        L7b:
            r3 = 4
            boolean r6 = r7.isLevelForParameter(r3)
            if (r6 == 0) goto L89
            int r6 = r7.mRssnr
            int r4 = r7.updateLevelWithMeasure(r6, r4)
            goto L8a
        L89:
            r4 = r5
        L8a:
            int r8 = java.lang.Math.min(r8, r2)
            int r8 = java.lang.Math.min(r8, r4)
            r7.mLevel = r8
            if (r8 != r5) goto Lb6
            int r8 = r7.mRssi
            r2 = -51
            if (r8 <= r2) goto L9e
        L9c:
            r0 = r1
            goto Lb4
        L9e:
            r2 = -89
            if (r8 < r2) goto La4
            r0 = r3
            goto Lb4
        La4:
            r2 = -97
            if (r8 < r2) goto Laa
            r0 = 3
            goto Lb4
        Laa:
            r2 = -103(0xffffffffffffff99, float:NaN)
            if (r8 < r2) goto Lb0
            r0 = r9
            goto Lb4
        Lb0:
            r9 = -113(0xffffffffffffff8f, float:NaN)
            if (r8 < r9) goto L9c
        Lb4:
            r7.mLevel = r0
        Lb6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.CellSignalStrengthLte.updateLevel(android.os.PersistableBundle, android.telephony.ServiceState):void");
    }

    private int updateLevelWithMeasure(int i, int[] iArr) {
        if (i == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (i >= iArr[3]) {
            return 4;
        }
        if (i >= iArr[2]) {
            return 3;
        }
        if (i >= iArr[1]) {
            return 2;
        }
        return i >= iArr[0] ? 1 : 0;
    }

    public int getRsrq() {
        return this.mRsrq;
    }

    public int getRssi() {
        return this.mRssi;
    }

    public int getRssnr() {
        return this.mRssnr;
    }

    public int getRsrp() {
        return this.mRsrp;
    }

    public int getCqi() {
        return this.mCqi;
    }

    @Override // android.telephony.CellSignalStrength
    public int getDbm() {
        return this.mRsrp;
    }

    @Override // android.telephony.CellSignalStrength
    public int getAsuLevel() {
        int i = this.mRsrp;
        if (i == Integer.MAX_VALUE) {
            return 99;
        }
        if (i <= MIN_LTE_RSRP) {
            return 0;
        }
        if (i >= -43) {
            return 97;
        }
        return i + 140;
    }

    public int getTimingAdvance() {
        return this.mTimingAdvance;
    }

    @Override // android.telephony.CellSignalStrength
    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mRssi), Integer.valueOf(this.mRsrp), Integer.valueOf(this.mRsrq), Integer.valueOf(this.mRssnr), Integer.valueOf(this.mCqi), Integer.valueOf(this.mTimingAdvance), Integer.valueOf(this.mLevel));
    }

    @Override // android.telephony.CellSignalStrength
    public boolean isValid() {
        return !equals(sInvalid);
    }

    @Override // android.telephony.CellSignalStrength
    public boolean equals(Object obj) {
        if (!(obj instanceof CellSignalStrengthLte)) {
            return false;
        }
        CellSignalStrengthLte cellSignalStrengthLte = (CellSignalStrengthLte) obj;
        return this.mRssi == cellSignalStrengthLte.mRssi && this.mRsrp == cellSignalStrengthLte.mRsrp && this.mRsrq == cellSignalStrengthLte.mRsrq && this.mRssnr == cellSignalStrengthLte.mRssnr && this.mCqi == cellSignalStrengthLte.mCqi && this.mTimingAdvance == cellSignalStrengthLte.mTimingAdvance && this.mLevel == cellSignalStrengthLte.mLevel;
    }

    public String toString() {
        return "CellSignalStrengthLte: rssi=" + this.mRssi + " rsrp=" + this.mRsrp + " rsrq=" + this.mRsrq + " rssnr=" + this.mRssnr + " cqi=" + this.mCqi + " ta=" + this.mTimingAdvance + " level=" + this.mLevel + " parametersUseForLevel=" + this.mParametersUseForLevel;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mRssi);
        parcel.writeInt(this.mRsrp);
        parcel.writeInt(this.mRsrq);
        parcel.writeInt(this.mRssnr);
        parcel.writeInt(this.mCqi);
        parcel.writeInt(this.mTimingAdvance);
        parcel.writeInt(this.mLevel);
    }

    private CellSignalStrengthLte(Parcel parcel) {
        int i = parcel.readInt();
        this.mRssi = i;
        this.mSignalStrength = i;
        this.mRsrp = parcel.readInt();
        this.mRsrq = parcel.readInt();
        this.mRssnr = parcel.readInt();
        this.mCqi = parcel.readInt();
        this.mTimingAdvance = parcel.readInt();
        this.mLevel = parcel.readInt();
    }

    private static void log(String str) {
        com.android.telephony.Rlog.w(LOG_TAG, str);
    }

    private static int convertRssnrUnitFromTenDbToDB(int i) {
        return i / 10;
    }

    private static int convertRssiAsuToDBm(int i) {
        if (i == 99) {
            return Integer.MAX_VALUE;
        }
        if (i >= 0 && i <= 31) {
            return (i * 2) - 113;
        }
        com.android.telephony.Rlog.e(LOG_TAG, "convertRssiAsuToDBm: invalid RSSI in ASU=" + i);
        return Integer.MAX_VALUE;
    }
}
