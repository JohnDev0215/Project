package com.ts.appservice.power.state;

import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerOnNormalState extends WorkModeState {
    private static final String TAG = CarPowerOnNormalState.class.getSimpleName();

    public CarPowerOnNormalState(CarPowerBinder tsCarPowerBinder) {
        super(tsCarPowerBinder);
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setDisplayState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnNormalState setDisplayState start ");
            this.mCarPower.setDisplayState(0);
            LogUtil.info(TAG, "CarPowerOnNormalState setDisplayState end ");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setHardKeyState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnNormalState setHardKeyState start ");
            this.mCarPower.setHardKeyState(1);
            LogUtil.info(TAG, "CarPowerOnNormalState setHardKeyState end ");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setAudioState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnNormalState setAudioState start ");
            this.mCarPower.setAudioState(1);
            LogUtil.info(TAG, "CarPowerOnNormalState setAudioState end ");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setOtherState() {
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void sendReportMessage() {
        LogUtil.info(TAG, "sendReportMessage");
        LogUtil.info(TAG, "CarPowerOnNormalState sendReportMessage start ");
        this.mCarPower.sendReportMessage(7);
        LogUtil.info(TAG, "CarPowerOnNormalState sendReportMessage end ");
    }
}
