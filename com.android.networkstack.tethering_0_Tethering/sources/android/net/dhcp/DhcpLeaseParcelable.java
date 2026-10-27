package android.net.dhcp;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DhcpLeaseParcelable implements Parcelable {
    public static final Parcelable.Creator<DhcpLeaseParcelable> CREATOR = new Parcelable.Creator<DhcpLeaseParcelable>() { // from class: android.net.dhcp.DhcpLeaseParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpLeaseParcelable createFromParcel(Parcel parcel) {
            DhcpLeaseParcelable dhcpLeaseParcelable = new DhcpLeaseParcelable();
            dhcpLeaseParcelable.readFromParcel(parcel);
            return dhcpLeaseParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DhcpLeaseParcelable[] newArray(int i) {
            return new DhcpLeaseParcelable[i];
        }
    };
    public byte[] clientId;
    public long expTime;
    public String hostname;
    public byte[] hwAddr;
    public int netAddr;
    public int prefixLength;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeByteArray(this.clientId);
        parcel.writeByteArray(this.hwAddr);
        parcel.writeInt(this.netAddr);
        parcel.writeInt(this.prefixLength);
        parcel.writeLong(this.expTime);
        parcel.writeString(this.hostname);
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
            this.clientId = parcel.createByteArray();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.hwAddr = parcel.createByteArray();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.netAddr = parcel.readInt();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.prefixLength = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                            this.expTime = parcel.readLong();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.hostname = parcel.readString();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                }
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
