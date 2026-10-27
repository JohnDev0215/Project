package com.neusoft.na.sens;

import com.neusoft.na.sens.jni.OneCoreSenJni;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreCarInform {
    public boolean getReverseSignal() {
        return true;
    }

    public float getVehicleSpeed() {
        return 0.0f;
    }

    public synchronized int getVehicleSpeedPulse() {
        return 0;
    }

    public void setNaviVehicleSpeed(float f, long j) {
        OneCoreSenJni.getInstance().notifyChangeSpeed(f, j);
    }
}
