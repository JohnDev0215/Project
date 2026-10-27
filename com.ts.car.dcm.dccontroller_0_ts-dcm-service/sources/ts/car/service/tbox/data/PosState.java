package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public final class PosState {
    public static final int CURRENT_LOCATION = 1;
    public static final int INVALID_LOCATION = 2;
    public static final int LAST_KNOW_LOCATION = 0;

    public static String toString(int posState) {
        if (posState == 0) {
            return "LAST_KNOW_LOCATION";
        }
        if (posState == 1) {
            return "CURRENT_LOCATION";
        }
        if (posState == 2) {
            return "INVALID_LOCATION";
        }
        return "0x" + Integer.toHexString(posState);
    }
}
