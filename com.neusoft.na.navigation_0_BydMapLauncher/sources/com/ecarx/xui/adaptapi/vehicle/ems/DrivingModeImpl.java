package com.ecarx.xui.adaptapi.vehicle.ems;

import com.ecarx.xui.adaptapi.FunctionStatus;
import com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode;

/* JADX INFO: loaded from: classes2.dex */
public class DrivingModeImpl implements IDrivingMode {
    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode
    public void attachDrivingModeChangeListener(IDrivingMode.DrivingModeChangeListener drivingModeChangeListener) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode
    public void detachDrivingModeChangeListener(IDrivingMode.DrivingModeChangeListener drivingModeChangeListener) {
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode
    public int getDrivingMode() {
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode
    public FunctionStatus getDrivingModeStatus(int i) {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode
    public boolean isDrivingModeTriggerByHardwareKey() {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode
    public boolean setDrivingMode(int i) {
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.vehicle.ems.IDrivingMode
    public int[] getSupportDrivingModes() {
        return new int[]{1, 2, 3};
    }
}
