package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ScanResultInfoParcelable implements Parcelable {
    public static final Parcelable.Creator<ScanResultInfoParcelable> CREATOR = new Parcelable.Creator<ScanResultInfoParcelable>() { // from class: android.net.ScanResultInfoParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScanResultInfoParcelable createFromParcel(Parcel parcel) {
            ScanResultInfoParcelable scanResultInfoParcelable = new ScanResultInfoParcelable();
            scanResultInfoParcelable.readFromParcel(parcel);
            return scanResultInfoParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScanResultInfoParcelable[] newArray(int i) {
            return new ScanResultInfoParcelable[i];
        }
    };
    public String bssid;
    public InformationElementParcelable[] informationElements;
    public String ssid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.ssid);
        parcel.writeString(this.bssid);
        parcel.writeTypedArray(this.informationElements, 0);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            this.ssid = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.bssid = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.informationElements = (InformationElementParcelable[]) parcel.createTypedArray(InformationElementParcelable.CREATOR);
                    if (parcel.dataPosition() - iDataPosition < i) {
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
