package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes2.dex */
public final class NetworkResetMode {
    public static final int HARD_MODE = 2;
    public static final int SOFT_MODE = 1;

    public static String toString(int networkResetMode) {
        if (networkResetMode == 1) {
            return "SOFT_MODE";
        }
        if (networkResetMode == 2) {
            return "HARD_MODE";
        }
        return "0x" + Integer.toHexString(networkResetMode);
    }
}
