package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public final class TboxConnectionState {
    public static final int ABNORMAL = 2;
    public static final int ABSENT = 1;
    public static final int CONNECTED = 4;
    public static final int CONNECTING = 3;
    public static final int UNKNOW = 0;
    public static final int UNKNOWN = 0;

    public static final String toString(int tboxConnectionState) {
        if (tboxConnectionState == 0) {
            return "UNKNOWN";
        }
        if (tboxConnectionState == 1) {
            return "ABSENT";
        }
        if (tboxConnectionState == 2) {
            return "ABNORMAL";
        }
        if (tboxConnectionState == 3) {
            return "CONNECTING";
        }
        if (tboxConnectionState == 4) {
            return "CONNECTED";
        }
        return "0x" + Integer.toHexString(tboxConnectionState);
    }

    public static boolean isValidTboxConnectionState(int state) {
        return state <= 4 && state >= 0;
    }
}
