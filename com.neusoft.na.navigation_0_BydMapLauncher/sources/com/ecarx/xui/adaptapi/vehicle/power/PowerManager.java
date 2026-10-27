package com.ecarx.xui.adaptapi.vehicle.power;

import com.ecarx.xui.adaptapi.vehicle.power.IPowerManager;

/* JADX INFO: loaded from: classes2.dex */
public class PowerManager implements IPowerManager {
    @Override // com.ecarx.xui.adaptapi.vehicle.power.IPowerManager
    public boolean attachPowerModeObserver(IPowerManager.IPowerModeObserver iPowerModeObserver) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.power.IPowerManager
    public void detachPowerModeObserver(IPowerManager.IPowerModeObserver iPowerModeObserver) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.power.IPowerManager
    public int getPowerMode() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.power.IPowerManager
    public int getSubMode(int i) {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.power.IPowerManager
    public boolean releasePowerKeepLock(String str) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.power.IPowerManager
    public boolean requestPowerkeepLock(String str) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.power.IPowerManager
    public void setResetSettingsRequest(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.power.IPowerManager
    public void trunDisplayOff(int i) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.power.IPowerManager
    public void trunDisplayOn(int i) {
    }
}
