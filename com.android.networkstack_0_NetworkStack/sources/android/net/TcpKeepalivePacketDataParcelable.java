package android.net;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class TcpKeepalivePacketDataParcelable implements Parcelable {
    public static final Parcelable.Creator<TcpKeepalivePacketDataParcelable> CREATOR = new Parcelable.Creator<TcpKeepalivePacketDataParcelable>() { // from class: android.net.TcpKeepalivePacketDataParcelable.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TcpKeepalivePacketDataParcelable createFromParcel(Parcel parcel) {
            TcpKeepalivePacketDataParcelable tcpKeepalivePacketDataParcelable = new TcpKeepalivePacketDataParcelable();
            tcpKeepalivePacketDataParcelable.readFromParcel(parcel);
            return tcpKeepalivePacketDataParcelable;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TcpKeepalivePacketDataParcelable[] newArray(int i) {
            return new TcpKeepalivePacketDataParcelable[i];
        }
    };
    public int ack;
    public byte[] dstAddress;
    public int dstPort;
    public int rcvWnd;
    public int rcvWndScale;
    public int seq;
    public byte[] srcAddress;
    public int srcPort;
    public int tos;
    public int ttl;

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
        parcel.writeInt(this.seq);
        parcel.writeInt(this.ack);
        parcel.writeInt(this.rcvWnd);
        parcel.writeInt(this.rcvWndScale);
        parcel.writeInt(this.tos);
        parcel.writeInt(this.ttl);
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
                            this.seq = parcel.readInt();
                            if (parcel.dataPosition() - iDataPosition < i) {
                                this.ack = parcel.readInt();
                                if (parcel.dataPosition() - iDataPosition < i) {
                                    this.rcvWnd = parcel.readInt();
                                    if (parcel.dataPosition() - iDataPosition < i) {
                                        this.rcvWndScale = parcel.readInt();
                                        if (parcel.dataPosition() - iDataPosition < i) {
                                            this.tos = parcel.readInt();
                                            if (parcel.dataPosition() - iDataPosition < i) {
                                                this.ttl = parcel.readInt();
                                                if (parcel.dataPosition() - iDataPosition < i) {
                                                }
                                            }
                                        }
                                    }
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
