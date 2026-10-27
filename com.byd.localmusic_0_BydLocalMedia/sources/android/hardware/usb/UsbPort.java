package android.hardware.usb;

import android.annotation.SystemApi;
import com.android.internal.accessibility.common.ShortcutConstants;
import com.android.internal.util.Preconditions;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class UsbPort {
    private static final int NUM_DATA_ROLES = 3;
    private static final int POWER_ROLE_OFFSET = 0;
    private final String mId;
    private final int mSupportedContaminantProtectionModes;
    private final int mSupportedModes;
    private final boolean mSupportsEnableContaminantPresenceDetection;
    private final boolean mSupportsEnableContaminantPresenceProtection;
    private final UsbManager mUsbManager;

    public UsbPort(UsbManager usbManager, String str, int i, int i2, boolean z, boolean z2) {
        Objects.requireNonNull(str);
        Preconditions.checkFlagsArgument(i, 15);
        this.mUsbManager = usbManager;
        this.mId = str;
        this.mSupportedModes = i;
        this.mSupportedContaminantProtectionModes = i2;
        this.mSupportsEnableContaminantPresenceProtection = z;
        this.mSupportsEnableContaminantPresenceDetection = z2;
    }

    public String getId() {
        return this.mId;
    }

    public int getSupportedModes() {
        return this.mSupportedModes;
    }

    public int getSupportedContaminantProtectionModes() {
        return this.mSupportedContaminantProtectionModes;
    }

    public boolean supportsEnableContaminantPresenceProtection() {
        return this.mSupportsEnableContaminantPresenceProtection;
    }

    public boolean supportsEnableContaminantPresenceDetection() {
        return this.mSupportsEnableContaminantPresenceDetection;
    }

    public UsbPortStatus getStatus() {
        return this.mUsbManager.getPortStatus(this);
    }

    public void setRoles(int i, int i2) {
        checkRoles(i, i2);
        this.mUsbManager.setPortRoles(this, i, i2);
    }

    public void enableContaminantDetection(boolean z) {
        this.mUsbManager.enableContaminantDetection(this, z);
    }

    public static int combineRolesAsBit(int i, int i2) {
        checkRoles(i, i2);
        return 1 << (((i + 0) * 3) + i2);
    }

    public static String modeToString(int i) {
        StringBuilder sb = new StringBuilder();
        if (i == 0) {
            return "none";
        }
        if ((i & 3) == 3) {
            sb.append("dual, ");
        } else if ((i & 2) == 2) {
            sb.append("dfp, ");
        } else if ((i & 1) == 1) {
            sb.append("ufp, ");
        }
        if ((i & 4) == 4) {
            sb.append("audio_acc, ");
        }
        if ((i & 8) == 8) {
            sb.append("debug_acc, ");
        }
        if (sb.length() == 0) {
            return Integer.toString(i);
        }
        return sb.substring(0, sb.length() - 2);
    }

    public static String powerRoleToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? Integer.toString(i) : "sink" : "source" : "no-power";
    }

    public static String dataRoleToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? Integer.toString(i) : UsbManager.EXTRA_DEVICE : "host" : "no-data";
    }

    public static String contaminantPresenceStatusToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? Integer.toString(i) : "detected" : "not detected" : "disabled" : "not-supported";
    }

    public static String roleCombinationsToString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        boolean z = true;
        while (i != 0) {
            int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
            i &= ~(1 << iNumberOfTrailingZeros);
            int i2 = (iNumberOfTrailingZeros / 3) + 0;
            int i3 = iNumberOfTrailingZeros % 3;
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(powerRoleToString(i2));
            sb.append(ShortcutConstants.SERVICES_SEPARATOR);
            sb.append(dataRoleToString(i3));
        }
        sb.append("]");
        return sb.toString();
    }

    public static void checkMode(int i) {
        Preconditions.checkArgumentInRange(i, 0, 3, "portMode");
    }

    public static void checkPowerRole(int i) {
        Preconditions.checkArgumentInRange(i, 0, 2, "powerRole");
    }

    public static void checkDataRole(int i) {
        Preconditions.checkArgumentInRange(i, 0, 2, "powerRole");
    }

    public static void checkRoles(int i, int i2) {
        Preconditions.checkArgumentInRange(i, 0, 2, "powerRole");
        Preconditions.checkArgumentInRange(i2, 0, 2, "dataRole");
    }

    public boolean isModeSupported(int i) {
        return (this.mSupportedModes & i) == i;
    }

    public String toString() {
        return "UsbPort{id=" + this.mId + ", supportedModes=" + modeToString(this.mSupportedModes) + "supportedContaminantProtectionModes=" + this.mSupportedContaminantProtectionModes + "supportsEnableContaminantPresenceProtection=" + this.mSupportsEnableContaminantPresenceProtection + "supportsEnableContaminantPresenceDetection=" + this.mSupportsEnableContaminantPresenceDetection;
    }
}
