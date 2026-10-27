package android.hardware.gnss.V1_0;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class GnssConstellationType {
    public static final byte BEIDOU = 5;
    public static final byte GALILEO = 6;
    public static final byte GLONASS = 3;
    public static final byte GPS = 1;
    public static final byte QZSS = 4;
    public static final byte SBAS = 2;
    public static final byte UNKNOWN = 0;

    public static final String toString(byte b) {
        return b == 0 ? "UNKNOWN" : b == 1 ? "GPS" : b == 2 ? "SBAS" : b == 3 ? "GLONASS" : b == 4 ? "QZSS" : b == 5 ? "BEIDOU" : b == 6 ? "GALILEO" : "0x" + Integer.toHexString(Byte.toUnsignedInt(b));
    }

    public static final String dumpBitfield(byte b) {
        byte b2;
        ArrayList arrayList = new ArrayList();
        arrayList.add("UNKNOWN");
        if ((b & 1) == 1) {
            arrayList.add("GPS");
            b2 = (byte) 1;
        } else {
            b2 = 0;
        }
        if ((b & 2) == 2) {
            arrayList.add("SBAS");
            b2 = (byte) (b2 | 2);
        }
        if ((b & 3) == 3) {
            arrayList.add("GLONASS");
            b2 = (byte) (b2 | 3);
        }
        if ((b & 4) == 4) {
            arrayList.add("QZSS");
            b2 = (byte) (b2 | 4);
        }
        if ((b & 5) == 5) {
            arrayList.add("BEIDOU");
            b2 = (byte) (b2 | 5);
        }
        if ((b & 6) == 6) {
            arrayList.add("GALILEO");
            b2 = (byte) (b2 | 6);
        }
        if (b != b2) {
            arrayList.add("0x" + Integer.toHexString(Byte.toUnsignedInt((byte) (b & (~b2)))));
        }
        return String.join(" | ", arrayList);
    }
}
