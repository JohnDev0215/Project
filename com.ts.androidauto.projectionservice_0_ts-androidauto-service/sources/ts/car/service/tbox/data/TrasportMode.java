package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes2.dex */
public final class TrasportMode {
    public static final byte TRANSPORT_FTP = 1;
    public static final byte TRANSPORT_OTHER = 3;
    public static final byte TRANSPORT_SCP = 2;

    public static String toString(byte transportMode) {
        if (transportMode == 1) {
            return "TRANSPORT_FTP";
        }
        if (transportMode == 2) {
            return "TRANSPORT_SCP";
        }
        if (transportMode == 3) {
            return "TRANSPORT_OTHER";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(transportMode));
    }
}
