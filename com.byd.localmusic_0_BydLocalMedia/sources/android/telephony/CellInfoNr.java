package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class CellInfoNr extends CellInfo {
    public static final Parcelable.Creator<CellInfoNr> CREATOR = new Parcelable.Creator<CellInfoNr>() { // from class: android.telephony.CellInfoNr.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoNr createFromParcel(Parcel parcel) {
            parcel.readInt();
            return new CellInfoNr(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CellInfoNr[] newArray(int i) {
            return new CellInfoNr[i];
        }
    };
    private static final String TAG = "CellInfoNr";
    private CellIdentityNr mCellIdentity;
    private final CellSignalStrengthNr mCellSignalStrength;

    @Override // android.telephony.CellInfo
    public /* bridge */ /* synthetic */ CellIdentityNr getCellIdentity() {
        return (CellIdentityNr) getCellIdentity();
    }

    @Override // android.telephony.CellInfo
    public /* bridge */ /* synthetic */ CellSignalStrengthNr getCellSignalStrength() {
        return (CellSignalStrengthNr) getCellSignalStrength();
    }

    public CellInfoNr() {
        this.mCellIdentity = new CellIdentityNr();
        this.mCellSignalStrength = new CellSignalStrengthNr();
    }

    private CellInfoNr(Parcel parcel) {
        super(parcel);
        this.mCellIdentity = CellIdentityNr.CREATOR.createFromParcel(parcel);
        this.mCellSignalStrength = CellSignalStrengthNr.CREATOR.createFromParcel(parcel);
    }

    private CellInfoNr(CellInfoNr cellInfoNr, boolean z) {
        CellIdentityNr cellIdentityNrSanitizeLocationInfo;
        super(cellInfoNr);
        if (z) {
            cellIdentityNrSanitizeLocationInfo = cellInfoNr.mCellIdentity.sanitizeLocationInfo();
        } else {
            cellIdentityNrSanitizeLocationInfo = cellInfoNr.mCellIdentity;
        }
        this.mCellIdentity = cellIdentityNrSanitizeLocationInfo;
        this.mCellSignalStrength = cellInfoNr.mCellSignalStrength;
    }

    public CellInfoNr(android.hardware.radio.V1_4.CellInfo cellInfo, long j) {
        super(cellInfo, j);
        android.hardware.radio.V1_4.CellInfoNr cellInfoNrNr = cellInfo.info.nr();
        this.mCellIdentity = new CellIdentityNr(cellInfoNrNr.cellidentity);
        this.mCellSignalStrength = new CellSignalStrengthNr(cellInfoNrNr.signalStrength);
    }

    public CellInfoNr(android.hardware.radio.V1_5.CellInfo cellInfo, long j) {
        super(cellInfo, j);
        android.hardware.radio.V1_5.CellInfoNr cellInfoNrNr = cellInfo.ratSpecificInfo.nr();
        this.mCellIdentity = new CellIdentityNr(cellInfoNrNr.cellIdentityNr);
        this.mCellSignalStrength = new CellSignalStrengthNr(cellInfoNrNr.signalStrengthNr);
    }

    @Override // android.telephony.CellInfo
    public CellIdentity getCellIdentity() {
        return this.mCellIdentity;
    }

    public void setCellIdentity(CellIdentityNr cellIdentityNr) {
        this.mCellIdentity = cellIdentityNr;
    }

    @Override // android.telephony.CellInfo
    public CellSignalStrength getCellSignalStrength() {
        return this.mCellSignalStrength;
    }

    @Override // android.telephony.CellInfo
    public CellInfo sanitizeLocationInfo() {
        return new CellInfoNr(this, true);
    }

    @Override // android.telephony.CellInfo
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.mCellIdentity, this.mCellSignalStrength);
    }

    @Override // android.telephony.CellInfo
    public boolean equals(Object obj) {
        if (!(obj instanceof CellInfoNr)) {
            return false;
        }
        CellInfoNr cellInfoNr = (CellInfoNr) obj;
        return super.equals(cellInfoNr) && this.mCellIdentity.equals(cellInfoNr.mCellIdentity) && this.mCellSignalStrength.equals(cellInfoNr.mCellSignalStrength);
    }

    @Override // android.telephony.CellInfo
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CellInfoNr:{");
        sb.append(" " + super.toString());
        sb.append(" " + this.mCellIdentity);
        sb.append(" " + this.mCellSignalStrength);
        sb.append(" }");
        return sb.toString();
    }

    @Override // android.telephony.CellInfo, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i, 6);
        this.mCellIdentity.writeToParcel(parcel, i);
        this.mCellSignalStrength.writeToParcel(parcel, i);
    }

    protected static CellInfoNr createFromParcelBody(Parcel parcel) {
        return new CellInfoNr(parcel);
    }
}
