package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public final class ResultCode {
    public static final int ERROR = -1;
    public static final int GENERIC_ERROR = 1;
    public static final int HALSERVICE_INTERNAL_ERROR = 4;
    public static final int SUCCESS = 0;
    public static final int TBOX_ABSENT = 2;
    public static final int TBOX_UNAUTHORIZED = 3;

    public static String toString(int resultCode) {
        if (resultCode == 0) {
            return "SUCCESS";
        }
        if (resultCode == 1) {
            return "GENERIC_ERROR";
        }
        if (resultCode == 2) {
            return "TBOX_ABSENT";
        }
        if (resultCode == 3) {
            return "TBOX_UNAUTHORIZED";
        }
        if (resultCode == -1) {
            return "ERROR";
        }
        if (resultCode == 4) {
            return "HALSERVICE_INTERNAL_ERROR";
        }
        return "0x" + Integer.toHexString(resultCode);
    }
}
