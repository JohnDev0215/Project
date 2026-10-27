package m7;

/* JADX INFO: compiled from: UpDownStatus.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {
    public static final String a(byte b8) {
        if (b8 == 0) {
            return "NONE";
        }
        if (b8 == 1) {
            return "IN";
        }
        if (b8 == 2) {
            return "OUT";
        }
        if (b8 == 3) {
            return "IN_AND_OUT";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(b8));
    }
}
