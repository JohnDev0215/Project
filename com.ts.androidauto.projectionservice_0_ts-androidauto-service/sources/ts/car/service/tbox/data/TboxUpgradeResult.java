package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes2.dex */
public class TboxUpgradeResult {
    public static final int FILE_ERR = 5;
    public static final int INSTALL_ERR = 6;
    public static final int NETWROK_ERR = 4;
    public static final int NO_NETWORK = 2;
    public static final int SUCCESS = 0;
    public static final int UNAVAILABLE_SERVER = 3;
    public static final int UPGRADE_ERR = 7;

    public static String toString(int result) {
        if (result == 0) {
            return "SUCCESS";
        }
        if (result == 2) {
            return "NO_NETWORK";
        }
        if (result == 3) {
            return "UNAVAILABLE_SERVER";
        }
        if (result == 4) {
            return "NETWROK_ERR";
        }
        if (result == 5) {
            return "FILE_ERR";
        }
        if (result == 6) {
            return "INSTALL_ERR";
        }
        return "UPGRADE_ERR";
    }
}
