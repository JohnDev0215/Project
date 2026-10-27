package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkRegState {
    public static final int NOT_REG_MT_NOT_SEARCHING_OP = 0;
    public static final int NOT_REG_MT_SEARCHING_OP = 2;
    public static final int REG_DENIED = 3;
    public static final int REG_HOME = 1;
    public static final int REG_ROAMING = 5;
    public static final int UNKNOWN = 4;

    public static String toString(int networkRegState) {
        if (networkRegState == 0) {
            return "NOT_REG_MT_NOT_SEARCHING_OP";
        }
        if (networkRegState == 1) {
            return "REG_HOME";
        }
        if (networkRegState == 2) {
            return "NOT_REG_MT_SEARCHING_OP";
        }
        if (networkRegState == 3) {
            return "REG_DENIED";
        }
        if (networkRegState == 4) {
            return "UNKNOWN";
        }
        if (networkRegState == 5) {
            return "REG_ROAMING";
        }
        return "0x" + Integer.toHexString(networkRegState);
    }

    public static boolean isValidNetworkRegState(int networkRegState) {
        return networkRegState <= 5 && networkRegState >= 0;
    }
}
