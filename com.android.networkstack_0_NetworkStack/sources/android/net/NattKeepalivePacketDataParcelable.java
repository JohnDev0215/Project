package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class NattKeepalivePacketDataParcelable implements Parcelable {
    public static final Parcelable.Creator<NattKeepalivePacketDataParcelable> CREATOR = new Parcelable.Creator<NattKeepalivePacketDataParcelable>() { // from class: android.net.NattKeepalivePacketDataParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NattKeepalivePacketDataParcelable createFromParcel(Parcel parcel) {
            NattKeepalivePacketDataParcelable nattKeepalivePacketDataParcelable = new NattKeepalivePacketDataParcelable();
            nattKeepalivePacketDataParcelable.readFromParcel(parcel);
            return nattKeepalivePacketDataParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NattKeepalivePacketDataParcelable[] newArray(int i) {
            return new NattKeepalivePacketDataParcelable[i];
        }
    };
    public byte[] dstAddress;
    public int dstPort;
    public byte[] srcAddress;
    public int srcPort;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        parcel.writeByteArray(this.srcAddress);
        parcel.writeInt(this.srcPort);
        parcel.writeByteArray(this.dstAddress);
        parcel.writeInt(this.dstPort);
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
            this.srcAddress = parcel.createByteArray();
            if (parcel.dataPosition() - iDataPosition < i) {
                this.srcPort = parcel.readInt();
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.dstAddress = parcel.createByteArray();
                    if (parcel.dataPosition() - iDataPosition < i) {
                        this.dstPort = parcel.readInt();
                        if (parcel.dataPosition() - iDataPosition < i) {
                        }
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
