package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public final class KeepAliveState {
    public static final int KEEP_ALIVE_STATE_OFF = 2;
    public static final int KEEP_ALIVE_STATE_ON = 1;
    public static final int KEEP_ALIVE_STATE_UNKNOW = 0;

    public static String toString(int keepAliveState) {
        if (keepAliveState == 0) {
            return "KEEP_ALIVE_STATE_UNKNOW";
        }
        if (keepAliveState == 1) {
            return "KEEP_ALIVE_STATE_ON";
        }
        if (keepAliveState == 2) {
            return "KEEP_ALIVE_STATE_OFF";
        }
        return "0x" + Integer.toHexString(keepAliveState);
    }

    public static boolean isValidKeepAliveState(int keepAliveState) {
        return keepAliveState >= 0 && keepAliveState <= 2;
    }
}
