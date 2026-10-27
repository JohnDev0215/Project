package ts.car.dm.fsdk.interfaces;

import android.util.SparseIntArray;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothDevice {
    private String mMacAddress;
    private String mName;
    private SparseIntArray profiles = new SparseIntArray(2);

    public static class BondStatus {
        public static final int BONDED = 2;
        public static final int BONDING = 1;
        public static final int UNBOND = 0;
    }

    public static class Profile {
        public static final int A2DP = 2;
        public static final int HFP = 1;
        public int status;
    }

    public static class ProfileStatus {
        public static final int AVAILABLE = 1;
        public static final int CONNECTED = 4;
        public static final int CONNECTING = 3;
        public static final int DISCONNECTING = 2;
        public static final int INVALID = -1;
        public static final int UNAVAILABLE = 0;

        public static String toString(int profile) {
            if (profile == -1) {
                return "INVALID";
            }
            if (profile == 0) {
                return "UNAVAILABLE";
            }
            if (profile == 1) {
                return "AVAILABLE";
            }
            if (profile == 2) {
                return "DISCONNECTING";
            }
            if (profile == 3) {
                return "CONNECTING";
            }
            if (profile == 4) {
                return "CONNECTED";
            }
            return "";
        }
    }

    public String getMacAddress() {
        return this.mMacAddress;
    }

    public void setMacAddress(String macAddress) {
        this.mMacAddress = macAddress;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public SparseIntArray getProfiles() {
        return this.profiles;
    }

    public void putProfileStatus(int profile, int status) {
        getProfiles().put(profile, status);
    }

    public String toString() {
        return "BluetoothDevice [mMacAddress=" + this.mMacAddress + ", mName=" + this.mName + ",profiles=" + this.profiles + "]";
    }
}
