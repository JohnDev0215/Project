package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CellInfoTdscdma extends CellInfo implements Parcelable {
    public static final Parcelable.Creator<CellInfoTdscdma> CREATOR = new Parcelable.Creator<CellInfoTdscdma>() { // from class: android.telephony.CellInfoTdscdma.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoTdscdma createFromParcel(Parcel parcel) {
            parcel.readInt();
            return CellInfoTdscdma.createFromParcelBody(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoTdscdma[] newArray(int i) {
            return new CellInfoTdscdma[i];
        }
    };
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CellInfoTdscdma";
    private CellIdentityTdscdma mCellIdentityTdscdma;
    private CellSignalStrengthTdscdma mCellSignalStrengthTdscdma;

    @Override // android.telephony.CellInfo, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CellInfoTdscdma() {
        this.mCellIdentityTdscdma = new CellIdentityTdscdma();
        this.mCellSignalStrengthTdscdma = new CellSignalStrengthTdscdma();
    }

    public CellInfoTdscdma(CellInfoTdscdma cellInfoTdscdma) {
        super(cellInfoTdscdma);
        this.mCellIdentityTdscdma = cellInfoTdscdma.mCellIdentityTdscdma.copy();
        this.mCellSignalStrengthTdscdma = cellInfoTdscdma.mCellSignalStrengthTdscdma.copy();
    }

    public CellInfoTdscdma(android.hardware.radio.V1_0.CellInfo cellInfo) {
        super(cellInfo);
        android.hardware.radio.V1_0.CellInfoTdscdma cellInfoTdscdma = cellInfo.tdscdma.get(0);
        this.mCellIdentityTdscdma = new CellIdentityTdscdma(cellInfoTdscdma.cellIdentityTdscdma);
        this.mCellSignalStrengthTdscdma = new CellSignalStrengthTdscdma(cellInfoTdscdma.signalStrengthTdscdma);
    }

    public CellInfoTdscdma(android.hardware.radio.V1_2.CellInfo cellInfo) {
        super(cellInfo);
        android.hardware.radio.V1_2.CellInfoTdscdma cellInfoTdscdma = cellInfo.tdscdma.get(0);
        this.mCellIdentityTdscdma = new CellIdentityTdscdma(cellInfoTdscdma.cellIdentityTdscdma);
        this.mCellSignalStrengthTdscdma = new CellSignalStrengthTdscdma(cellInfoTdscdma.signalStrengthTdscdma);
    }

    public CellInfoTdscdma(android.hardware.radio.V1_4.CellInfo cellInfo, long j) {
        super(cellInfo, j);
        android.hardware.radio.V1_2.CellInfoTdscdma cellInfoTdscdmaTdscdma = cellInfo.info.tdscdma();
        this.mCellIdentityTdscdma = new CellIdentityTdscdma(cellInfoTdscdmaTdscdma.cellIdentityTdscdma);
        this.mCellSignalStrengthTdscdma = new CellSignalStrengthTdscdma(cellInfoTdscdmaTdscdma.signalStrengthTdscdma);
    }

    public CellInfoTdscdma(android.hardware.radio.V1_5.CellInfo cellInfo, long j) {
        super(cellInfo, j);
        android.hardware.radio.V1_5.CellInfoTdscdma cellInfoTdscdmaTdscdma = cellInfo.ratSpecificInfo.tdscdma();
        this.mCellIdentityTdscdma = new CellIdentityTdscdma(cellInfoTdscdmaTdscdma.cellIdentityTdscdma);
        this.mCellSignalStrengthTdscdma = new CellSignalStrengthTdscdma(cellInfoTdscdmaTdscdma.signalStrengthTdscdma);
    }

    @Override // android.telephony.CellInfo
    public CellIdentityTdscdma getCellIdentity() {
        return this.mCellIdentityTdscdma;
    }

    public void setCellIdentity(CellIdentityTdscdma cellIdentityTdscdma) {
        this.mCellIdentityTdscdma = cellIdentityTdscdma;
    }

    @Override // android.telephony.CellInfo
    public CellSignalStrengthTdscdma getCellSignalStrength() {
        return this.mCellSignalStrengthTdscdma;
    }

    @Override // android.telephony.CellInfo
    public CellInfo sanitizeLocationInfo() {
        CellInfoTdscdma cellInfoTdscdma = new CellInfoTdscdma(this);
        cellInfoTdscdma.mCellIdentityTdscdma = this.mCellIdentityTdscdma.sanitizeLocationInfo();
        return cellInfoTdscdma;
    }

    public void setCellSignalStrength(CellSignalStrengthTdscdma cellSignalStrengthTdscdma) {
        this.mCellSignalStrengthTdscdma = cellSignalStrengthTdscdma;
    }

    @Override // android.telephony.CellInfo
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.mCellIdentityTdscdma, this.mCellSignalStrengthTdscdma);
    }

    @Override // android.telephony.CellInfo
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        try {
            CellInfoTdscdma cellInfoTdscdma = (CellInfoTdscdma) obj;
            if (this.mCellIdentityTdscdma.equals(cellInfoTdscdma.mCellIdentityTdscdma)) {
                return this.mCellSignalStrengthTdscdma.equals(cellInfoTdscdma.mCellSignalStrengthTdscdma);
            }
            return false;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    @Override // android.telephony.CellInfo
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("CellInfoTdscdma:{");
        stringBuffer.append(super.toString());
        stringBuffer.append(" ");
        stringBuffer.append(this.mCellIdentityTdscdma);
        stringBuffer.append(" ");
        stringBuffer.append(this.mCellSignalStrengthTdscdma);
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    @Override // android.telephony.CellInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i, 5);
        this.mCellIdentityTdscdma.writeToParcel(parcel, i);
        this.mCellSignalStrengthTdscdma.writeToParcel(parcel, i);
    }

    private CellInfoTdscdma(Parcel parcel) {
        super(parcel);
        this.mCellIdentityTdscdma = CellIdentityTdscdma.CREATOR.createFromParcel(parcel);
        this.mCellSignalStrengthTdscdma = CellSignalStrengthTdscdma.CREATOR.createFromParcel(parcel);
    }

    protected static CellInfoTdscdma createFromParcelBody(Parcel parcel) {
        return new CellInfoTdscdma(parcel);
    }

    private static void log(String str) {
        com.android.telephony.Rlog.w(LOG_TAG, str);
    }
}
