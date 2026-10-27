package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Layer2PacketParcelable implements Parcelable {
    public static final Parcelable.Creator<Layer2PacketParcelable> CREATOR = new Parcelable.Creator<Layer2PacketParcelable>() { // from class: android.net.Layer2PacketParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Layer2PacketParcelable createFromParcel(Parcel parcel) {
            Layer2PacketParcelable layer2PacketParcelable = new Layer2PacketParcelable();
            layer2PacketParcelable.readFromParcel(parcel);
            return layer2PacketParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Layer2PacketParcelable[] newArray(int i) {
            return new Layer2PacketParcelable[i];
        }
    };
    public MacAddress dstMacAddress;
    public byte[] payload;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        if (this.dstMacAddress != null) {
            parcel.writeInt(1);
            this.dstMacAddress.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeByteArray(this.payload);
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
            if (parcel.readInt() != 0) {
                this.dstMacAddress = (MacAddress) MacAddress.CREATOR.createFromParcel(parcel);
            } else {
                this.dstMacAddress = null;
            }
            if (parcel.dataPosition() - iDataPosition < i) {
                this.payload = parcel.createByteArray();
                if (parcel.dataPosition() - iDataPosition < i) {
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
