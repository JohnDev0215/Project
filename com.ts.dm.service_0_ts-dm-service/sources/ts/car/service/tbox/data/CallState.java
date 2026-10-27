package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public final class CallState {
    public static final int ACTIVE = 1;
    public static final int ALERTING = 4;
    public static final int DIALING = 3;
    public static final int DISCONNECTED = 7;
    public static final int DISCONNECTING = 8;
    public static final int HOLDING = 2;
    public static final int IDLE = 0;
    public static final int INCOMING = 5;
    public static final int WAITING = 6;

    public static String toString(int callState) {
        if (callState == 0) {
            return "IDLE";
        }
        if (callState == 1) {
            return "ACTIVE";
        }
        if (callState == 2) {
            return "HOLDING";
        }
        if (callState == 3) {
            return "DIALING";
        }
        if (callState == 4) {
            return "ALERTING";
        }
        if (callState == 5) {
            return "INCOMING";
        }
        if (callState == 6) {
            return "WAITING";
        }
        if (callState == 7) {
            return "DISCONNECTED";
        }
        if (callState == 8) {
            return "DISCONNECTING";
        }
        return "0x" + Integer.toHexString(callState);
    }

    public static boolean isValidCallState(int callState) {
        return callState <= 8 && callState >= 0;
    }
}
