package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class PowerUpdate {
    private int mMaximumCurrentDrawnFromAccessory = -1;
    private int mDeviceBatteryWillChargeIfPowerIsPresent = -1;
    private int mIsExternalChargerConnected = -1;
    private int mBatteryChargingState = -1;
    private int mBatteryChargeLevel = -1;

    public int getMaximumCurrentDrawnFromAccessory() {
        return this.mMaximumCurrentDrawnFromAccessory;
    }

    public void setMaximumCurrentDrawnFromAccessory(int maxCurrent) {
        this.mMaximumCurrentDrawnFromAccessory = maxCurrent;
    }

    public int getDeviceBatteryWillChargeIfPowerIsPresent() {
        return this.mDeviceBatteryWillChargeIfPowerIsPresent;
    }

    public void setDeviceBatteryWillChargeIfPowerIsPresent(int willCharge) {
        this.mDeviceBatteryWillChargeIfPowerIsPresent = willCharge;
    }

    public int getIsExternalChargerConnected() {
        return this.mIsExternalChargerConnected;
    }

    public void setIsExternalChargerConnected(int isConnected) {
        this.mIsExternalChargerConnected = isConnected;
    }

    public int getBatteryChargingState() {
        return this.mBatteryChargingState;
    }

    public void setBatteryChargingState(int state) {
        this.mBatteryChargingState = state;
    }

    public int getBatteryChargeLevel() {
        return this.mBatteryChargeLevel;
    }

    public void setBatteryChargeLevel(int level) {
        this.mBatteryChargeLevel = level;
    }
}
