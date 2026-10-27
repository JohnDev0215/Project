package android.telephony;

import android.content.pm.PackageManager;
import android.hardware.radio.V1_0.CdmaSignalStrength;
import android.hardware.radio.V1_0.EvdoSignalStrength;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CellSignalStrengthCdma extends CellSignalStrength implements Parcelable {
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellSignalStrengthCdma";
    private int mCdmaDbm;
    private int mCdmaEcio;
    private int mEvdoDbm;
    private int mEvdoEcio;
    private int mEvdoSnr;
    private int mLevel;
    private static final CellSignalStrengthCdma sInvalid = new CellSignalStrengthCdma();
    public static final Parcelable.Creator<CellSignalStrengthCdma> CREATOR = new Parcelable.Creator<CellSignalStrengthCdma>() { // from class: android.telephony.CellSignalStrengthCdma.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellSignalStrengthCdma createFromParcel(Parcel parcel) {
            return new CellSignalStrengthCdma(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellSignalStrengthCdma[] newArray(int i) {
            return new CellSignalStrengthCdma[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CellSignalStrengthCdma() {
        setDefaultValues();
    }

    public CellSignalStrengthCdma(int i, int i2, int i3, int i4, int i5) {
        this.mCdmaDbm = inRangeOrUnavailable(i, PackageManager.INSTALL_FAILED_MULTIPACKAGE_INCONSISTENCY, 0);
        this.mCdmaEcio = inRangeOrUnavailable(i2, -160, 0);
        this.mEvdoDbm = inRangeOrUnavailable(i3, PackageManager.INSTALL_FAILED_MULTIPACKAGE_INCONSISTENCY, 0);
        this.mEvdoEcio = inRangeOrUnavailable(i4, -160, 0);
        this.mEvdoSnr = inRangeOrUnavailable(i5, 0, 8);
        updateLevel(null, null);
    }

    public CellSignalStrengthCdma(CdmaSignalStrength cdmaSignalStrength, EvdoSignalStrength evdoSignalStrength) {
        this(-cdmaSignalStrength.dbm, -cdmaSignalStrength.ecio, -evdoSignalStrength.dbm, -evdoSignalStrength.ecio, evdoSignalStrength.signalNoiseRatio);
    }

    public CellSignalStrengthCdma(CellSignalStrengthCdma cellSignalStrengthCdma) {
        copyFrom(cellSignalStrengthCdma);
    }

    protected void copyFrom(CellSignalStrengthCdma cellSignalStrengthCdma) {
        this.mCdmaDbm = cellSignalStrengthCdma.mCdmaDbm;
        this.mCdmaEcio = cellSignalStrengthCdma.mCdmaEcio;
        this.mEvdoDbm = cellSignalStrengthCdma.mEvdoDbm;
        this.mEvdoEcio = cellSignalStrengthCdma.mEvdoEcio;
        this.mEvdoSnr = cellSignalStrengthCdma.mEvdoSnr;
        this.mLevel = cellSignalStrengthCdma.mLevel;
    }

    @Override // android.telephony.CellSignalStrength
    public CellSignalStrengthCdma copy() {
        return new CellSignalStrengthCdma(this);
    }

    @Override // android.telephony.CellSignalStrength
    public void setDefaultValues() {
        this.mCdmaDbm = Integer.MAX_VALUE;
        this.mCdmaEcio = Integer.MAX_VALUE;
        this.mEvdoDbm = Integer.MAX_VALUE;
        this.mEvdoEcio = Integer.MAX_VALUE;
        this.mEvdoSnr = Integer.MAX_VALUE;
        this.mLevel = 0;
    }

    @Override // android.telephony.CellSignalStrength
    public int getLevel() {
        return this.mLevel;
    }

    @Override // android.telephony.CellSignalStrength
    public void updateLevel(PersistableBundle persistableBundle, ServiceState serviceState) {
        int cdmaLevel = getCdmaLevel();
        int evdoLevel = getEvdoLevel();
        if (evdoLevel == 0) {
            this.mLevel = getCdmaLevel();
        } else {
            if (cdmaLevel == 0) {
                this.mLevel = getEvdoLevel();
                return;
            }
            if (cdmaLevel >= evdoLevel) {
                cdmaLevel = evdoLevel;
            }
            this.mLevel = cdmaLevel;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x001a  */
    @Override // android.telephony.CellSignalStrength
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getAsuLevel() {
        /*
            r12 = this;
            int r0 = r12.getCdmaDbm()
            int r1 = r12.getCdmaEcio()
            r2 = 1
            r3 = 2
            r4 = -100
            r5 = 4
            r6 = 8
            r7 = -90
            r8 = 16
            r9 = 2147483647(0x7fffffff, float:NaN)
            r10 = 99
            if (r0 != r9) goto L1c
        L1a:
            r0 = r10
            goto L35
        L1c:
            r11 = -75
            if (r0 < r11) goto L22
            r0 = r8
            goto L35
        L22:
            r11 = -82
            if (r0 < r11) goto L28
            r0 = r6
            goto L35
        L28:
            if (r0 < r7) goto L2c
            r0 = r5
            goto L35
        L2c:
            r11 = -95
            if (r0 < r11) goto L32
            r0 = r3
            goto L35
        L32:
            if (r0 < r4) goto L1a
            r0 = r2
        L35:
            if (r1 != r9) goto L39
        L37:
            r2 = r10
            goto L51
        L39:
            if (r1 < r7) goto L3d
            r2 = r8
            goto L51
        L3d:
            if (r1 < r4) goto L41
            r2 = r6
            goto L51
        L41:
            r4 = -115(0xffffffffffffff8d, float:NaN)
            if (r1 < r4) goto L47
            r2 = r5
            goto L51
        L47:
            r4 = -130(0xffffffffffffff7e, float:NaN)
            if (r1 < r4) goto L4d
            r2 = r3
            goto L51
        L4d:
            r3 = -150(0xffffffffffffff6a, float:NaN)
            if (r1 < r3) goto L37
        L51:
            if (r0 >= r2) goto L54
            goto L55
        L54:
            r0 = r2
        L55:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.CellSignalStrengthCdma.getAsuLevel():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getCdmaLevel() {
        /*
            r9 = this;
            int r0 = r9.getCdmaDbm()
            int r1 = r9.getCdmaEcio()
            r2 = 1
            r3 = 2
            r4 = 3
            r5 = 4
            r6 = 2147483647(0x7fffffff, float:NaN)
            r7 = 0
            if (r0 != r6) goto L14
        L12:
            r0 = r7
            goto L2b
        L14:
            r8 = -75
            if (r0 < r8) goto L1a
            r0 = r5
            goto L2b
        L1a:
            r8 = -85
            if (r0 < r8) goto L20
            r0 = r4
            goto L2b
        L20:
            r8 = -95
            if (r0 < r8) goto L26
            r0 = r3
            goto L2b
        L26:
            r8 = -100
            if (r0 < r8) goto L12
            r0 = r2
        L2b:
            if (r1 != r6) goto L2f
        L2d:
            r2 = r7
            goto L45
        L2f:
            r6 = -90
            if (r1 < r6) goto L35
            r2 = r5
            goto L45
        L35:
            r5 = -110(0xffffffffffffff92, float:NaN)
            if (r1 < r5) goto L3b
            r2 = r4
            goto L45
        L3b:
            r4 = -130(0xffffffffffffff7e, float:NaN)
            if (r1 < r4) goto L41
            r2 = r3
            goto L45
        L41:
            r3 = -150(0xffffffffffffff6a, float:NaN)
            if (r1 < r3) goto L2d
        L45:
            if (r0 >= r2) goto L48
            goto L49
        L48:
            r0 = r2
        L49:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.CellSignalStrengthCdma.getCdmaLevel():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getEvdoLevel() {
        /*
            r9 = this;
            int r0 = r9.getEvdoDbm()
            int r1 = r9.getEvdoSnr()
            r2 = 2
            r3 = 4
            r4 = 2147483647(0x7fffffff, float:NaN)
            r5 = 1
            r6 = 3
            r7 = 0
            if (r0 != r4) goto L14
        L12:
            r0 = r7
            goto L2b
        L14:
            r8 = -65
            if (r0 < r8) goto L1a
            r0 = r3
            goto L2b
        L1a:
            r8 = -75
            if (r0 < r8) goto L20
            r0 = r6
            goto L2b
        L20:
            r8 = -90
            if (r0 < r8) goto L26
            r0 = r2
            goto L2b
        L26:
            r8 = -105(0xffffffffffffff97, float:NaN)
            if (r0 < r8) goto L12
            r0 = r5
        L2b:
            if (r1 != r4) goto L2f
        L2d:
            r2 = r7
            goto L3f
        L2f:
            r4 = 7
            if (r1 < r4) goto L34
            r2 = r3
            goto L3f
        L34:
            r3 = 5
            if (r1 < r3) goto L39
            r2 = r6
            goto L3f
        L39:
            if (r1 < r6) goto L3c
            goto L3f
        L3c:
            if (r1 < r5) goto L2d
            r2 = r5
        L3f:
            if (r0 >= r2) goto L42
            goto L43
        L42:
            r0 = r2
        L43:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.CellSignalStrengthCdma.getEvdoLevel():int");
    }

    public int getEvdoAsuLevel() {
        int evdoDbm = getEvdoDbm();
        int evdoSnr = getEvdoSnr();
        int i = 99;
        int i2 = evdoDbm >= -65 ? 16 : evdoDbm >= -75 ? 8 : evdoDbm >= -85 ? 4 : evdoDbm >= -95 ? 2 : evdoDbm >= -105 ? 1 : 99;
        if (evdoSnr >= 7) {
            i = 16;
        } else if (evdoSnr >= 6) {
            i = 8;
        } else if (evdoSnr >= 5) {
            i = 4;
        } else if (evdoSnr >= 3) {
            i = 2;
        } else if (evdoSnr >= 1) {
            i = 1;
        }
        return i2 < i ? i2 : i;
    }

    @Override // android.telephony.CellSignalStrength
    public int getDbm() {
        int cdmaDbm = getCdmaDbm();
        int evdoDbm = getEvdoDbm();
        return cdmaDbm < evdoDbm ? cdmaDbm : evdoDbm;
    }

    public int getCdmaDbm() {
        return this.mCdmaDbm;
    }

    public void setCdmaDbm(int i) {
        this.mCdmaDbm = i;
    }

    public int getCdmaEcio() {
        return this.mCdmaEcio;
    }

    public void setCdmaEcio(int i) {
        this.mCdmaEcio = i;
    }

    public int getEvdoDbm() {
        return this.mEvdoDbm;
    }

    public void setEvdoDbm(int i) {
        this.mEvdoDbm = i;
    }

    public int getEvdoEcio() {
        return this.mEvdoEcio;
    }

    public void setEvdoEcio(int i) {
        this.mEvdoEcio = i;
    }

    public int getEvdoSnr() {
        return this.mEvdoSnr;
    }

    public void setEvdoSnr(int i) {
        this.mEvdoSnr = i;
    }

    @Override // android.telephony.CellSignalStrength
    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mCdmaDbm), Integer.valueOf(this.mCdmaEcio), Integer.valueOf(this.mEvdoDbm), Integer.valueOf(this.mEvdoEcio), Integer.valueOf(this.mEvdoSnr), Integer.valueOf(this.mLevel));
    }

    @Override // android.telephony.CellSignalStrength
    public boolean isValid() {
        return !equals(sInvalid);
    }

    @Override // android.telephony.CellSignalStrength
    public boolean equals(Object obj) {
        if (!(obj instanceof CellSignalStrengthCdma)) {
            return false;
        }
        CellSignalStrengthCdma cellSignalStrengthCdma = (CellSignalStrengthCdma) obj;
        return this.mCdmaDbm == cellSignalStrengthCdma.mCdmaDbm && this.mCdmaEcio == cellSignalStrengthCdma.mCdmaEcio && this.mEvdoDbm == cellSignalStrengthCdma.mEvdoDbm && this.mEvdoEcio == cellSignalStrengthCdma.mEvdoEcio && this.mEvdoSnr == cellSignalStrengthCdma.mEvdoSnr && this.mLevel == cellSignalStrengthCdma.mLevel;
    }

    public String toString() {
        return "CellSignalStrengthCdma: cdmaDbm=" + this.mCdmaDbm + " cdmaEcio=" + this.mCdmaEcio + " evdoDbm=" + this.mEvdoDbm + " evdoEcio=" + this.mEvdoEcio + " evdoSnr=" + this.mEvdoSnr + " level=" + this.mLevel;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mCdmaDbm);
        parcel.writeInt(this.mCdmaEcio);
        parcel.writeInt(this.mEvdoDbm);
        parcel.writeInt(this.mEvdoEcio);
        parcel.writeInt(this.mEvdoSnr);
        parcel.writeInt(this.mLevel);
    }

    private CellSignalStrengthCdma(Parcel parcel) {
        this.mCdmaDbm = parcel.readInt();
        this.mCdmaEcio = parcel.readInt();
        this.mEvdoDbm = parcel.readInt();
        this.mEvdoEcio = parcel.readInt();
        this.mEvdoSnr = parcel.readInt();
        this.mLevel = parcel.readInt();
    }

    private static void log(String str) {
        com.android.telephony.Rlog.w(LOG_TAG, str);
    }
}
