package com.ts.appservice.power.state;

import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerOnFotaState extends WorkModeState {
    private static final String TAG = CarPowerOnFotaState.class.getSimpleName();

    public CarPowerOnFotaState(CarPowerBinder tsCarPowerBinder) {
        super(tsCarPowerBinder);
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setDisplayState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnFotaState setDisplayState start ");
            this.mCarPower.setDisplayState(1);
            LogUtil.info(TAG, "CarPowerOnFotaState setDisplayState end ");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setHardKeyState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnFotaState setHardKeyState start ");
            this.mCarPower.setHardKeyState(0);
            LogUtil.info(TAG, "CarPowerOnFotaState setHardKeyState end ");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setAudioState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnFotaState setAudioState start ");
            this.mCarPower.setAudioState(1);
            LogUtil.info(TAG, "CarPowerOnFotaState setAudioState end ");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setOtherState() {
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void sendReportMessage() {
        LogUtil.info(TAG, "sendReportMessage");
        LogUtil.info(TAG, "CarPowerOtaState sendReportMessage start ");
        this.mCarPower.sendReportMessage(6);
        LogUtil.info(TAG, "CarPowerOtaState sendReportMessage end ");
    }
}
