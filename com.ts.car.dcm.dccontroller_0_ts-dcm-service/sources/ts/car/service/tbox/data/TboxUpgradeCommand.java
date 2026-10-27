package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public final class TboxUpgradeCommand {
    public static final int DOWNLOAD_CONFIRM = 3;
    public static final int DOWNLOAD_RESULT = 4;
    public static final int INSTALL_CONFIRM = 5;
    public static final int INSTALL_RESULT = 6;
    public static final int NEW_VERSION = 1;
    public static final int NEW_VERSION_INFORM = 32;
    public static final int NO_NEW_VERSION = 2;
    public static final int PROGRESS_UPDATE = 7;
    public static final int UPGRADE_BY_USB = 128;
    public static final int UPGRADE_PROMPT = 130;
    public static final int UPGRADE_STATE_INFO = 129;

    public static String toString(int tboxUpgradeCommand) {
        if (tboxUpgradeCommand == 1) {
            return "NEW_VERSION";
        }
        if (tboxUpgradeCommand == 2) {
            return "NO_NEW_VERSION";
        }
        if (tboxUpgradeCommand == 3) {
            return "DOWNLOAD_CONFIRM";
        }
        if (tboxUpgradeCommand == 4) {
            return "DOWNLOAD_RESULT";
        }
        if (tboxUpgradeCommand == 5) {
            return "INSTALL_CONFIRM";
        }
        if (tboxUpgradeCommand == 6) {
            return "INSTALL_RESULT";
        }
        if (tboxUpgradeCommand == 7) {
            return "PROGRESS_UPDATE";
        }
        if (tboxUpgradeCommand == 32) {
            return "NEW_VERSION_INFORM";
        }
        if (tboxUpgradeCommand == 128) {
            return "UPGRADE_BY_USB";
        }
        if (tboxUpgradeCommand == 129) {
            return "UPGRADE_STATE_INFO";
        }
        if (tboxUpgradeCommand == 130) {
            return "UPGRADE_PROMPT";
        }
        return "0x" + Integer.toHexString(tboxUpgradeCommand);
    }
}
