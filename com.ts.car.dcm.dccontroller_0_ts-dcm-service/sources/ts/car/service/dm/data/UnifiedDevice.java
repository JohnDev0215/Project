package ts.car.service.dm.data;

import com.ts.car.dcm.dccontroller.configdata.IConfiguration;
import ts.car.service.dm.data.DmConstants;

/* JADX INFO: loaded from: classes.dex */
public final class UnifiedDevice {
    private int mA2dpState;
    private int mAndroidAutoProjectionState;
    private int mCarPlayProjectionState;
    private int mCarlifeProjectionState;
    private String mDeviceUuid;
    private String mFriendlyName;
    private int mHfpState;
    private int mHicarProjectionState;
    private boolean mIsPaired;
    private int mLastProfile = 0;
    private long mLastProfileTimeMillis;

    public synchronized String getDeviceUuid() {
        return this.mDeviceUuid;
    }

    public synchronized void setDeviceUuid(String deviceUuid) {
        this.mDeviceUuid = deviceUuid;
    }

    public synchronized String getFriendlyName() {
        return this.mFriendlyName;
    }

    public synchronized void setFriendlyName(String friendlyName) {
        this.mFriendlyName = friendlyName;
    }

    public synchronized boolean isPaired() {
        return this.mIsPaired;
    }

    public synchronized void setPaired(boolean isPaired) {
        this.mIsPaired = isPaired;
    }

    public synchronized void setCarPlayProjectionState(int state) {
        this.mCarPlayProjectionState = state;
    }

    public synchronized int getCarPlayProjectionState() {
        return this.mCarPlayProjectionState;
    }

    public synchronized void setCarlifeProjectionState(int state) {
        this.mCarlifeProjectionState = state;
    }

    public synchronized int getCarlifeProjectionState() {
        return this.mCarlifeProjectionState;
    }

    public synchronized void setAndroidAutoProjectionState(int state) {
        this.mAndroidAutoProjectionState = state;
    }

    public synchronized int getAndroidAutoProjectionState() {
        return this.mAndroidAutoProjectionState;
    }

    public synchronized void setHicarProjectionState(int state) {
        this.mHicarProjectionState = state;
    }

    public synchronized int getHicarProjectionState() {
        return this.mHicarProjectionState;
    }

    public synchronized void setHfpState(int state) {
        this.mHfpState = state;
    }

    public synchronized int getHfpState() {
        return this.mHfpState;
    }

    public synchronized void setA2dpState(int state) {
        this.mA2dpState = state;
    }

    public synchronized int getA2dpState() {
        return this.mA2dpState;
    }

    public synchronized void setLastProfile(int profile) {
        this.mLastProfile = profile;
    }

    public int getLastProfile() {
        return this.mLastProfile;
    }

    public synchronized void setLastProfileTimeMillis(long time) {
        this.mLastProfileTimeMillis = time;
    }

    public long getLastProfileTimeMillis() {
        return this.mLastProfileTimeMillis;
    }

    public synchronized boolean isCapabilitySupport(int type) {
        switch (type) {
            case 1:
                return this.mCarPlayProjectionState != 0;
            case 2:
                return this.mAndroidAutoProjectionState != 0;
            case 3:
                return this.mCarlifeProjectionState != 0;
            case 4:
                return this.mHicarProjectionState != 0;
            case 5:
                return this.mHfpState != 0;
            case 6:
                return this.mA2dpState != 0;
            default:
                return false;
        }
    }

    public synchronized boolean isCapabilityConnected(int type) {
        switch (type) {
            case 1:
                return this.mCarPlayProjectionState == 4;
            case 2:
                return this.mAndroidAutoProjectionState == 4;
            case 3:
                return this.mCarlifeProjectionState == 4;
            case 4:
                return this.mHicarProjectionState == 4;
            case 5:
                return this.mHfpState == 4;
            case 6:
                return this.mA2dpState == 4;
            default:
                return false;
        }
    }

    public int hashCode() {
        String str = this.mDeviceUuid;
        if (str == null) {
            str = IConfiguration.DEFAULT_JAR_VERSION;
        }
        return str.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnifiedDevice other = (UnifiedDevice) obj;
        if (getDeviceUuid() == null || other.getDeviceUuid() == null) {
            return false;
        }
        return getDeviceUuid().equals(other.getDeviceUuid());
    }

    private String getProjectionCapabilityString() {
        return "[mCarPlayProjectionState:" + DmConstants.CapabilityState.getStateString(this.mCarPlayProjectionState) + ", mCarlifeProjectionState:" + DmConstants.CapabilityState.getStateString(this.mCarlifeProjectionState) + ", mAndroidAutoProjectionState:" + DmConstants.CapabilityState.getStateString(this.mAndroidAutoProjectionState) + ", mHicarProjectionState:" + DmConstants.CapabilityState.getStateString(this.mHicarProjectionState) + "]";
    }

    public String toString() {
        return "UnifiedDevice[mDeviceUuid:" + this.mDeviceUuid + ", mFriendlyName:" + this.mFriendlyName + ", mLastProfile:" + DmConstants.Capability.getString(this.mLastProfile) + ", mLastProfileTimeMillis:" + this.mLastProfileTimeMillis + ", mProjectionCapability:" + getProjectionCapabilityString() + ", mIsPaired:" + isPaired() + ", mHfpState:" + DmConstants.CapabilityState.getStateString(this.mHfpState) + ", mA2dpState:" + DmConstants.CapabilityState.getStateString(this.mA2dpState) + "]";
    }
}
