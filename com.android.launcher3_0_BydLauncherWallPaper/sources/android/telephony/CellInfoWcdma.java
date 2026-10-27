package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CellInfoWcdma extends CellInfo implements Parcelable {
    public static final Parcelable.Creator<CellInfoWcdma> CREATOR = new Parcelable.Creator<CellInfoWcdma>() { // from class: android.telephony.CellInfoWcdma.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoWcdma createFromParcel(Parcel parcel) {
            parcel.readInt();
            return CellInfoWcdma.createFromParcelBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoWcdma[] newArray(int i) {
            return new CellInfoWcdma[i];
        }
    };
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellInfoWcdma";
    private CellIdentityWcdma mCellIdentityWcdma;
    private CellSignalStrengthWcdma mCellSignalStrengthWcdma;

    @Override // android.telephony.CellInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CellInfoWcdma() {
        this.mCellIdentityWcdma = new CellIdentityWcdma();
        this.mCellSignalStrengthWcdma = new CellSignalStrengthWcdma();
    }

    public CellInfoWcdma(CellInfoWcdma cellInfoWcdma) {
        super(cellInfoWcdma);
        this.mCellIdentityWcdma = cellInfoWcdma.mCellIdentityWcdma.copy();
        this.mCellSignalStrengthWcdma = cellInfoWcdma.mCellSignalStrengthWcdma.copy();
    }

    public CellInfoWcdma(android.hardware.radio.V1_0.CellInfo cellInfo) {
        super(cellInfo);
        android.hardware.radio.V1_0.CellInfoWcdma cellInfoWcdma = cellInfo.wcdma.get(0);
        this.mCellIdentityWcdma = new CellIdentityWcdma(cellInfoWcdma.cellIdentityWcdma);
        this.mCellSignalStrengthWcdma = new CellSignalStrengthWcdma(cellInfoWcdma.signalStrengthWcdma);
    }

    public CellInfoWcdma(android.hardware.radio.V1_2.CellInfo cellInfo) {
        super(cellInfo);
        android.hardware.radio.V1_2.CellInfoWcdma cellInfoWcdma = cellInfo.wcdma.get(0);
        this.mCellIdentityWcdma = new CellIdentityWcdma(cellInfoWcdma.cellIdentityWcdma);
        this.mCellSignalStrengthWcdma = new CellSignalStrengthWcdma(cellInfoWcdma.signalStrengthWcdma);
    }

    public CellInfoWcdma(android.hardware.radio.V1_4.CellInfo cellInfo, long j) {
        super(cellInfo, j);
        android.hardware.radio.V1_2.CellInfoWcdma cellInfoWcdmaWcdma = cellInfo.info.wcdma();
        this.mCellIdentityWcdma = new CellIdentityWcdma(cellInfoWcdmaWcdma.cellIdentityWcdma);
        this.mCellSignalStrengthWcdma = new CellSignalStrengthWcdma(cellInfoWcdmaWcdma.signalStrengthWcdma);
    }

    public CellInfoWcdma(android.hardware.radio.V1_5.CellInfo cellInfo, long j) {
        super(cellInfo, j);
        android.hardware.radio.V1_5.CellInfoWcdma cellInfoWcdmaWcdma = cellInfo.ratSpecificInfo.wcdma();
        this.mCellIdentityWcdma = new CellIdentityWcdma(cellInfoWcdmaWcdma.cellIdentityWcdma);
        this.mCellSignalStrengthWcdma = new CellSignalStrengthWcdma(cellInfoWcdmaWcdma.signalStrengthWcdma);
    }

    @Override // android.telephony.CellInfo
    public CellIdentityWcdma getCellIdentity() {
        return this.mCellIdentityWcdma;
    }

    public void setCellIdentity(CellIdentityWcdma cellIdentityWcdma) {
        this.mCellIdentityWcdma = cellIdentityWcdma;
    }

    @Override // android.telephony.CellInfo
    public CellSignalStrengthWcdma getCellSignalStrength() {
        return this.mCellSignalStrengthWcdma;
    }

    @Override // android.telephony.CellInfo
    public CellInfo sanitizeLocationInfo() {
        CellInfoWcdma cellInfoWcdma = new CellInfoWcdma(this);
        cellInfoWcdma.mCellIdentityWcdma = this.mCellIdentityWcdma.sanitizeLocationInfo();
        return cellInfoWcdma;
    }

    public void setCellSignalStrength(CellSignalStrengthWcdma cellSignalStrengthWcdma) {
        this.mCellSignalStrengthWcdma = cellSignalStrengthWcdma;
    }

    @Override // android.telephony.CellInfo
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.mCellIdentityWcdma, this.mCellSignalStrengthWcdma);
    }

    @Override // android.telephony.CellInfo
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        try {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) obj;
            if (this.mCellIdentityWcdma.equals(cellInfoWcdma.mCellIdentityWcdma)) {
                return this.mCellSignalStrengthWcdma.equals(cellInfoWcdma.mCellSignalStrengthWcdma);
            }
            return false;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    @Override // android.telephony.CellInfo
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CellInfoWcdma:{");
        stringBuffer.append(super.toString());
        stringBuffer.append(" ").append(this.mCellIdentityWcdma);
        stringBuffer.append(" ").append(this.mCellSignalStrengthWcdma);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    @Override // android.telephony.CellInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i, 4);
        this.mCellIdentityWcdma.writeToParcel(parcel, i);
        this.mCellSignalStrengthWcdma.writeToParcel(parcel, i);
    }

    private CellInfoWcdma(Parcel parcel) {
        super(parcel);
        this.mCellIdentityWcdma = CellIdentityWcdma.CREATOR.createFromParcel(parcel);
        this.mCellSignalStrengthWcdma = CellSignalStrengthWcdma.CREATOR.createFromParcel(parcel);
    }

    protected static CellInfoWcdma createFromParcelBody(Parcel parcel) {
        return new CellInfoWcdma(parcel);
    }

    private static void log(String str) {
        com.android.telephony.Rlog.w(LOG_TAG, str);
    }
}
