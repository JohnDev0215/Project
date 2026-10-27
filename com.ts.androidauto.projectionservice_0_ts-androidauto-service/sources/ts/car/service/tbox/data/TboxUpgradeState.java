package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes2.dex */
public final class TboxUpgradeState {
    public static final byte TBOX_UPDATE_FAIL = 4;
    public static final byte TBOX_UPDATE_START = 1;
    public static final byte TBOX_UPDATE_SUCCESS = 3;
    public static final byte TBOX_UPDATING = 2;

    public static String toString(byte tboxUpgradeState) {
        if (tboxUpgradeState == 1) {
            return "TBOX_UPDATE_START";
        }
        if (tboxUpgradeState == 2) {
            return "TBOX_UPDATING";
        }
        if (tboxUpgradeState == 3) {
            return "TBOX_UPDATE_SUCCESS";
        }
        if (tboxUpgradeState == 4) {
            return "TBOX_UPDATE_FAIL";
        }
        return "0x" + Integer.toHexString(Byte.toUnsignedInt(tboxUpgradeState));
    }
}
