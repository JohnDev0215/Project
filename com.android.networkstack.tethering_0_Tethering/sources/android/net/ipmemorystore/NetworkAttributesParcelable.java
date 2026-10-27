package android.net.ipmemorystore;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class NetworkAttributesParcelable implements Parcelable {
    public static final Parcelable.Creator<NetworkAttributesParcelable> CREATOR = new Parcelable.Creator<NetworkAttributesParcelable>() { // from class: android.net.ipmemorystore.NetworkAttributesParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkAttributesParcelable createFromParcel(Parcel parcel) {
            NetworkAttributesParcelable networkAttributesParcelable = new NetworkAttributesParcelable();
            networkAttributesParcelable.readFromParcel(parcel);
            return networkAttributesParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetworkAttributesParcelable[] newArray(int i) {
            return new NetworkAttributesParcelable[i];
        }
    };
    public byte[] assignedV4Address;
    public long assignedV4AddressExpiry;
    public String cluster;
    public Blob[] dnsAddresses;
    public int mtu;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeByteArray(this.assignedV4Address);
        parcel.writeLong(this.assignedV4AddressExpiry);
        parcel.writeString(this.cluster);
        parcel.writeTypedArray(this.dnsAddresses, 0);
        parcel.writeInt(this.mtu);
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
            this.assignedV4Address = parcel.createByteArray();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.assignedV4AddressExpiry = parcel.readLong();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.cluster = parcel.readString();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.dnsAddresses = (Blob[]) parcel.createTypedArray(Blob.CREATOR);
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.mtu = parcel.readInt();
                            if (parcel.dataPosition() - iDataPosition < i) {
                            }
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
