package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes2.dex */
public final class TboxUpgradeResponse {
    public static final byte NO = 2;
    public static final byte OK = 1;

    public static final String toString(byte response) {
        if (response == 1) {
            return "OK";
        }
        if (response == 2) {
            return "NO";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(response));
    }
}
