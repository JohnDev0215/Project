package ts.car.service.tbox.data;

import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class TboxUpgradeInfo {
    private static final String TAG = "TboxUpgradeInfo";
    public int cmdType;
    public final ArrayList<TboxUpgradeInfrom> infrom = new ArrayList<>();
    public final ArrayList<Byte> response = new ArrayList<>();
    public final ArrayList<Byte> result = new ArrayList<>();
    public final ArrayList<TboxUpgradeStateInfo> progress = new ArrayList<>();
    public final ArrayList<TboxUpgradeTrasportMode> usbUpgradeMode = new ArrayList<>();
    public final ArrayList<String> upgradePrompt = new ArrayList<>();

    public final boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != TboxUpgradeInfo.class) {
            return false;
        }
        TboxUpgradeInfo other = (TboxUpgradeInfo) otherObject;
        if (this.cmdType != other.cmdType || !this.infrom.equals(other.infrom) || !this.response.equals(other.response) || !this.result.equals(other.result) || !this.progress.equals(other.progress) || !this.usbUpgradeMode.equals(other.usbUpgradeMode)) {
            return false;
        }
        return this.upgradePrompt.equals(other.upgradePrompt);
    }

    public final String toString() {
        return "{.cmdType = " + TboxUpgradeCommand.toString(this.cmdType) + ", .infrom = " + this.infrom + ", .response = " + this.response + ", .result = " + this.result + ", .progress = " + this.progress + ", .usbUpgradeMode = " + this.usbUpgradeMode + ", .upgradePrompt = " + this.upgradePrompt + "}";
    }

    public static final boolean checkInvalidUpgradeInfo(TboxUpgradeInfo info) {
        if (info == null) {
            Log.e(TAG, "checkInvalidUpgradeInfo info is null");
            return false;
        }
        int infromSize = info.infrom.size();
        if (infromSize > 0) {
            if (infromSize == 1) {
                TboxUpgradeInfrom infrom = info.infrom.get(0);
                if (!TboxUpgradeInfrom.checkInvalidTboxUpgradeInfrom(infrom)) {
                    Log.e(TAG, "checkInvalidTboxUpgradeInfrom infrom fails:" + infrom);
                    return false;
                }
            } else {
                Log.e(TAG, "checkInvalidUpgradeInfo infromSize invalid size:" + infromSize);
                return false;
            }
        }
        int usbSize = info.usbUpgradeMode.size();
        if (usbSize > 0) {
            if (usbSize == 1) {
                TboxUpgradeTrasportMode mode = info.usbUpgradeMode.get(0);
                if (!TboxUpgradeTrasportMode.checkInvalidTboxUpgradeTrasportMode(mode)) {
                    Log.e(TAG, "checkInvalidTboxUpgradeTrasportMode mode fails:" + mode);
                    return false;
                }
            } else {
                Log.e(TAG, "checkInvalidUpgradeInfo usb size invalid size:" + usbSize);
                return false;
            }
        }
        return true;
    }
}
