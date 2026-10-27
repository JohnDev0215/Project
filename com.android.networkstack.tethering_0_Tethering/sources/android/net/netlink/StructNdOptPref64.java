package android.net.netlink;

import android.net.IpPrefix;
import android.util.Log;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class StructNdOptPref64 extends NdOption {
    private static final String TAG = "StructNdOptPref64";
    public final int lifetime;
    public final IpPrefix prefix;

    static int plcToPrefixLength(int i) {
        if (i == 0) {
            return 96;
        }
        if (i == 1) {
            return 64;
        }
        if (i == 2) {
            return 56;
        }
        if (i == 3) {
            return 48;
        }
        if (i == 4) {
            return 40;
        }
        if (i == 5) {
            return 32;
        }
        throw new IllegalArgumentException("Invalid prefix length code " + i);
    }

    private StructNdOptPref64(ByteBuffer byteBuffer) {
        super(byteBuffer.get(), Byte.toUnsignedInt(byteBuffer.get()));
        if (this.type != 38) {
            throw new IllegalArgumentException("Invalid type " + ((int) this.type));
        }
        if (this.length != 2) {
            throw new IllegalArgumentException("Invalid length " + this.length);
        }
        int unsignedInt = Short.toUnsignedInt(byteBuffer.getShort());
        this.lifetime = 65528 & unsignedInt;
        byte[] bArr = new byte[16];
        byteBuffer.get(bArr, 0, 12);
        try {
            this.prefix = new IpPrefix(InetAddress.getByAddress(bArr), plcToPrefixLength(unsignedInt & 7));
        } catch (UnknownHostException unused) {
            throw new AssertionError("16-byte array not valid InetAddress?");
        }
    }

    public static StructNdOptPref64 parse(ByteBuffer byteBuffer) {
        if (byteBuffer != null && byteBuffer.remaining() >= 16) {
            try {
                return new StructNdOptPref64(byteBuffer);
            } catch (IllegalArgumentException e) {
                Log.d(TAG, "Invalid PREF64 option: " + e);
            }
        }
        return null;
    }

    @Override // android.net.netlink.NdOption
    public String toString() {
        return String.format("NdOptPref64(%s, %d)", this.prefix, Integer.valueOf(this.lifetime));
    }
}
