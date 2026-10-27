package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Layer2InformationParcelable implements Parcelable {
    public static final Parcelable.Creator<Layer2InformationParcelable> CREATOR = new Parcelable.Creator<Layer2InformationParcelable>() { // from class: android.net.Layer2InformationParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Layer2InformationParcelable createFromParcel(Parcel parcel) {
            Layer2InformationParcelable layer2InformationParcelable = new Layer2InformationParcelable();
            layer2InformationParcelable.readFromParcel(parcel);
            return layer2InformationParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Layer2InformationParcelable[] newArray(int i) {
            return new Layer2InformationParcelable[i];
        }
    };
    public MacAddress bssid;
    public String cluster;
    public String l2Key;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeString(this.l2Key);
        parcel.writeString(this.cluster);
        if (this.bssid != null) {
            parcel.writeInt(1);
            this.bssid.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
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
            this.l2Key = parcel.readString();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.cluster = parcel.readString();
                if (parcel.dataPosition() - iDataPosition < i) {
                    if (parcel.readInt() != 0) {
                        this.bssid = (MacAddress) MacAddress.CREATOR.createFromParcel(parcel);
                    } else {
                        this.bssid = null;
                    }
                    if (parcel.dataPosition() - iDataPosition < i) {
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
