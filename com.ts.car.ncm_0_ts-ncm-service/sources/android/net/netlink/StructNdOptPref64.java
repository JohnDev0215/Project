package android.net.netlink;

import android.net.IpPrefix;
import android.util.Log;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class StructNdOptPref64 extends NdOption {
    public static final byte LENGTH = 2;
    public static final int STRUCT_SIZE = 16;
    private static final String TAG = StructNdOptPref64.class.getSimpleName();
    public static final int TYPE = 38;
    public final int lifetime;
    public final IpPrefix prefix;

    static int plcToPrefixLength(int plc) {
        if (plc == 0) {
            return 96;
        }
        if (plc == 1) {
            return 64;
        }
        if (plc == 2) {
            return 56;
        }
        if (plc == 3) {
            return 48;
        }
        if (plc == 4) {
            return 40;
        }
        if (plc == 5) {
            return 32;
        }
        throw new IllegalArgumentException("Invalid prefix length code " + plc);
    }

    static int prefixLengthToPlc(int prefixLength) {
        if (prefixLength == 32) {
            return 5;
        }
        if (prefixLength == 40) {
            return 4;
        }
        if (prefixLength == 48) {
            return 3;
        }
        if (prefixLength == 56) {
            return 2;
        }
        if (prefixLength == 64) {
            return 1;
        }
        if (prefixLength == 96) {
            return 0;
        }
        throw new IllegalArgumentException("Invalid prefix length " + prefixLength);
    }

    static short getScaledLifetimePlc(int lifetime, int prefixLengthCode) {
        return (short) ((65528 & lifetime) | (prefixLengthCode & 7));
    }

    public StructNdOptPref64(IpPrefix prefix, int lifetime) {
        super((byte) 38, 2);
        Objects.requireNonNull(prefix, "prefix must not be null");
        if (!(prefix.getAddress() instanceof Inet6Address)) {
            throw new IllegalArgumentException("Must be an IPv6 prefix: " + prefix);
        }
        prefixLengthToPlc(prefix.getPrefixLength());
        this.prefix = prefix;
        if (lifetime < 0 || lifetime > 65528) {
            throw new IllegalArgumentException("Invalid lifetime " + lifetime);
        }
        this.lifetime = 65528 & lifetime;
    }

    private StructNdOptPref64(ByteBuffer buf) {
        super(buf.get(), Byte.toUnsignedInt(buf.get()));
        if (this.type != 38) {
            throw new IllegalArgumentException("Invalid type " + ((int) this.type));
        }
        if (this.length != 2) {
            throw new IllegalArgumentException("Invalid length " + this.length);
        }
        int scaledLifetimePlc = Short.toUnsignedInt(buf.getShort());
        this.lifetime = 65528 & scaledLifetimePlc;
        byte[] addressBytes = new byte[16];
        buf.get(addressBytes, 0, 12);
        try {
            InetAddress addr = InetAddress.getByAddress(addressBytes);
            this.prefix = new IpPrefix(addr, plcToPrefixLength(scaledLifetimePlc & 7));
        } catch (UnknownHostException e) {
            throw new AssertionError("16-byte array not valid InetAddress?");
        }
    }

    public static StructNdOptPref64 parse(ByteBuffer buf) {
        if (buf == null || buf.remaining() < 16) {
            return null;
        }
        try {
            return new StructNdOptPref64(buf);
        } catch (IllegalArgumentException e) {
            Log.d(TAG, "Invalid PREF64 option: " + e);
            return null;
        }
    }

    @Override // android.net.netlink.NdOption
    protected void writeToByteBuffer(ByteBuffer buf) {
        super.writeToByteBuffer(buf);
        buf.putShort(getScaledLifetimePlc(this.lifetime, prefixLengthToPlc(this.prefix.getPrefixLength())));
        buf.put(this.prefix.getRawAddress(), 0, 12);
    }

    public ByteBuffer toByteBuffer() {
        ByteBuffer buf = ByteBuffer.allocate(16);
        writeToByteBuffer(buf);
        buf.flip();
        return buf;
    }

    @Override // android.net.netlink.NdOption
    public String toString() {
        return String.format("NdOptPref64(%s, %d)", this.prefix, Integer.valueOf(this.lifetime));
    }
}
