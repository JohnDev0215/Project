package android.net;

import android.annotation.SystemApi;
import android.bluetooth.BluetoothHidDevice;
import android.net.util.IpUtils;
import android.os.Parcel;
import android.os.Parcelable;
import android.system.OsConstants;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class NattKeepalivePacketData extends KeepalivePacketData implements Parcelable {
    public static final Parcelable.Creator<NattKeepalivePacketData> CREATOR = new Parcelable.Creator<NattKeepalivePacketData>() { // from class: android.net.NattKeepalivePacketData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NattKeepalivePacketData createFromParcel(Parcel parcel) {
            try {
                return NattKeepalivePacketData.nattKeepalivePacket(InetAddresses.parseNumericAddress(parcel.readString()), parcel.readInt(), InetAddresses.parseNumericAddress(parcel.readString()), parcel.readInt());
            } catch (InvalidPacketException e) {
                throw new IllegalArgumentException("Invalid NAT-T keepalive data: " + e.getError());
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NattKeepalivePacketData[] newArray(int i) {
            return new NattKeepalivePacketData[i];
        }
    };
    private static final int IPV4_HEADER_LENGTH = 20;
    private static final int UDP_HEADER_LENGTH = 8;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NattKeepalivePacketData(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2, byte[] bArr) throws InvalidPacketException {
        super(inetAddress, i, inetAddress2, i2, bArr);
    }

    public static NattKeepalivePacketData nattKeepalivePacket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws InvalidPacketException {
        if (!(inetAddress instanceof Inet4Address) || !(inetAddress2 instanceof Inet4Address)) {
            throw new InvalidPacketException(-21);
        }
        if (i2 != 4500) {
            throw new InvalidPacketException(-22);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(29);
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocate.putShort((short) 17664);
        byteBufferAllocate.putShort((short) 29);
        byteBufferAllocate.putInt(0);
        byteBufferAllocate.put(BluetoothHidDevice.SUBCLASS1_KEYBOARD);
        byteBufferAllocate.put((byte) OsConstants.IPPROTO_UDP);
        int iPosition = byteBufferAllocate.position();
        byteBufferAllocate.putShort((short) 0);
        byteBufferAllocate.put(inetAddress.getAddress());
        byteBufferAllocate.put(inetAddress2.getAddress());
        byteBufferAllocate.putShort((short) i);
        byteBufferAllocate.putShort((short) i2);
        byteBufferAllocate.putShort((short) 9);
        int iPosition2 = byteBufferAllocate.position();
        byteBufferAllocate.putShort((short) 0);
        byteBufferAllocate.put((byte) -1);
        byteBufferAllocate.putShort(iPosition, IpUtils.ipChecksum(byteBufferAllocate, 0));
        byteBufferAllocate.putShort(iPosition2, IpUtils.udpChecksum(byteBufferAllocate, 0, 20));
        return new NattKeepalivePacketData(inetAddress, i, inetAddress2, i2, byteBufferAllocate.array());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getSrcAddress().getHostAddress());
        parcel.writeString(getDstAddress().getHostAddress());
        parcel.writeInt(getSrcPort());
        parcel.writeInt(getDstPort());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NattKeepalivePacketData)) {
            return false;
        }
        NattKeepalivePacketData nattKeepalivePacketData = (NattKeepalivePacketData) obj;
        return getSrcAddress().equals(nattKeepalivePacketData.getSrcAddress()) && getDstAddress().equals(nattKeepalivePacketData.getDstAddress()) && getSrcPort() == nattKeepalivePacketData.getSrcPort() && getDstPort() == nattKeepalivePacketData.getDstPort();
    }

    public int hashCode() {
        return Objects.hash(getSrcAddress(), getDstAddress(), Integer.valueOf(getSrcPort()), Integer.valueOf(getDstPort()));
    }
}
