package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes2.dex */
public class TboxPowerMode {
    public static final int TBOX_POWERMODE_OFF = 3;
    public static final int TBOX_POWERMODE_RUNNING = 0;
    public static final int TBOX_POWERMODE_SUSPEND = 1;
    public static final int TBOX_POWERMODE_UNKNOWN = -1;
    public static final int TBOX_POWERMODE_UPGRADE = 2;

    public static String toString(int tboxPowerMode) {
        if (tboxPowerMode == -1) {
            return "TBOX_POWERMODE_UNKNOWN";
        }
        if (tboxPowerMode == 0) {
            return "TBOX_POWERMODE_RUNNING";
        }
        if (tboxPowerMode == 1) {
            return "TBOX_POWERMODE_SUSPEND";
        }
        if (tboxPowerMode == 2) {
            return "TBOX_POWERMODE_UPGRADE";
        }
        if (tboxPowerMode == 3) {
            return "TBOX_POWERMODE_OFF";
        }
        return "0x" + tboxPowerMode;
    }

    public static boolean isValidMode(int mode) {
        return mode >= -1 && mode <= 3;
    }
}
