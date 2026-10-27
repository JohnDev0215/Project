package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public final class SimStatus {
    public static final int ABSENT = 1;
    public static final int FAULT = 3;
    public static final int SIM_READY = 2;
    public static final int UNKNOWN = 0;

    public static String toString(int simStatus) {
        if (simStatus == 0) {
            return "UNKNOWN";
        }
        if (simStatus == 1) {
            return "ABSENT";
        }
        if (simStatus == 2) {
            return "SIM_READY";
        }
        if (simStatus == 3) {
            return "FAULT";
        }
        return "0x" + Integer.toHexString(simStatus);
    }

    public static boolean isValidSimStatus(int status) {
        return status <= 3 && status >= 0;
    }
}
