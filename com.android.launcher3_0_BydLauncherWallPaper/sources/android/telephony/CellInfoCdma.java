package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class CellInfoCdma extends CellInfo implements Parcelable {
    public static final Parcelable.Creator<CellInfoCdma> CREATOR = new Parcelable.Creator<CellInfoCdma>() { // from class: android.telephony.CellInfoCdma.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoCdma createFromParcel(Parcel parcel) {
            parcel.readInt();
            return CellInfoCdma.createFromParcelBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoCdma[] newArray(int i) {
            return new CellInfoCdma[i];
        }
    };
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellInfoCdma";
    private CellIdentityCdma mCellIdentityCdma;
    private CellSignalStrengthCdma mCellSignalStrengthCdma;

    @Override // android.telephony.CellInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CellInfoCdma() {
        this.mCellIdentityCdma = new CellIdentityCdma();
        this.mCellSignalStrengthCdma = new CellSignalStrengthCdma();
    }

    public CellInfoCdma(CellInfoCdma cellInfoCdma) {
        super(cellInfoCdma);
        this.mCellIdentityCdma = cellInfoCdma.mCellIdentityCdma.copy();
        this.mCellSignalStrengthCdma = cellInfoCdma.mCellSignalStrengthCdma.copy();
    }

    public CellInfoCdma(android.hardware.radio.V1_0.CellInfo cellInfo) {
        super(cellInfo);
        android.hardware.radio.V1_0.CellInfoCdma cellInfoCdma = cellInfo.cdma.get(0);
        this.mCellIdentityCdma = new CellIdentityCdma(cellInfoCdma.cellIdentityCdma);
        this.mCellSignalStrengthCdma = new CellSignalStrengthCdma(cellInfoCdma.signalStrengthCdma, cellInfoCdma.signalStrengthEvdo);
    }

    public CellInfoCdma(android.hardware.radio.V1_2.CellInfo cellInfo) {
        super(cellInfo);
        android.hardware.radio.V1_2.CellInfoCdma cellInfoCdma = cellInfo.cdma.get(0);
        this.mCellIdentityCdma = new CellIdentityCdma(cellInfoCdma.cellIdentityCdma);
        this.mCellSignalStrengthCdma = new CellSignalStrengthCdma(cellInfoCdma.signalStrengthCdma, cellInfoCdma.signalStrengthEvdo);
    }

    public CellInfoCdma(android.hardware.radio.V1_4.CellInfo cellInfo, long j) {
        super(cellInfo, j);
        android.hardware.radio.V1_2.CellInfoCdma cellInfoCdmaCdma = cellInfo.info.cdma();
        this.mCellIdentityCdma = new CellIdentityCdma(cellInfoCdmaCdma.cellIdentityCdma);
        this.mCellSignalStrengthCdma = new CellSignalStrengthCdma(cellInfoCdmaCdma.signalStrengthCdma, cellInfoCdmaCdma.signalStrengthEvdo);
    }

    public CellInfoCdma(android.hardware.radio.V1_5.CellInfo cellInfo, long j) {
        super(cellInfo, j);
        android.hardware.radio.V1_2.CellInfoCdma cellInfoCdmaCdma = cellInfo.ratSpecificInfo.cdma();
        this.mCellIdentityCdma = new CellIdentityCdma(cellInfoCdmaCdma.cellIdentityCdma);
        this.mCellSignalStrengthCdma = new CellSignalStrengthCdma(cellInfoCdmaCdma.signalStrengthCdma, cellInfoCdmaCdma.signalStrengthEvdo);
    }

    @Override // android.telephony.CellInfo
    public CellIdentityCdma getCellIdentity() {
        return this.mCellIdentityCdma;
    }

    public void setCellIdentity(CellIdentityCdma cellIdentityCdma) {
        this.mCellIdentityCdma = cellIdentityCdma;
    }

    @Override // android.telephony.CellInfo
    public CellSignalStrengthCdma getCellSignalStrength() {
        return this.mCellSignalStrengthCdma;
    }

    @Override // android.telephony.CellInfo
    public CellInfo sanitizeLocationInfo() {
        CellInfoCdma cellInfoCdma = new CellInfoCdma(this);
        cellInfoCdma.mCellIdentityCdma = this.mCellIdentityCdma.sanitizeLocationInfo();
        return cellInfoCdma;
    }

    public void setCellSignalStrength(CellSignalStrengthCdma cellSignalStrengthCdma) {
        this.mCellSignalStrengthCdma = cellSignalStrengthCdma;
    }

    @Override // android.telephony.CellInfo
    public int hashCode() {
        return super.hashCode() + this.mCellIdentityCdma.hashCode() + this.mCellSignalStrengthCdma.hashCode();
    }

    @Override // android.telephony.CellInfo
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        try {
            CellInfoCdma cellInfoCdma = (CellInfoCdma) obj;
            if (this.mCellIdentityCdma.equals(cellInfoCdma.mCellIdentityCdma)) {
                return this.mCellSignalStrengthCdma.equals(cellInfoCdma.mCellSignalStrengthCdma);
            }
            return false;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    @Override // android.telephony.CellInfo
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CellInfoCdma:{");
        stringBuffer.append(super.toString());
        stringBuffer.append(" ").append(this.mCellIdentityCdma);
        stringBuffer.append(" ").append(this.mCellSignalStrengthCdma);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    @Override // android.telephony.CellInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i, 2);
        this.mCellIdentityCdma.writeToParcel(parcel, i);
        this.mCellSignalStrengthCdma.writeToParcel(parcel, i);
    }

    private CellInfoCdma(Parcel parcel) {
        super(parcel);
        this.mCellIdentityCdma = CellIdentityCdma.CREATOR.createFromParcel(parcel);
        this.mCellSignalStrengthCdma = CellSignalStrengthCdma.CREATOR.createFromParcel(parcel);
    }

    protected static CellInfoCdma createFromParcelBody(Parcel parcel) {
        return new CellInfoCdma(parcel);
    }

    private static void log(String str) {
        com.android.telephony.Rlog.w(LOG_TAG, str);
    }
}
