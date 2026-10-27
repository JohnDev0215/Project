package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public final class AirPlaneMode {
    public static final int AIRPLANE_MODE_DISABLED = 2;
    public static final int AIRPLANE_MODE_ENABLED = 1;
    public static final int AIRPLANE_MODE_UNKNOW = 0;

    public static String toString(int airplane) {
        if (airplane == 0) {
            return "AIRPLANE_MODE_UNKNOW";
        }
        if (airplane == 1) {
            return "AIRPLANE_MODE_ENABLED";
        }
        if (airplane == 2) {
            return "AIRPLANE_MODE_DISABLED";
        }
        return "0x" + Integer.toHexString(airplane);
    }

    public static boolean isValidAirplaneMode(int mode) {
        return mode <= 2 && mode >= 0;
    }
}
