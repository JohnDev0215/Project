package com.ts.appservice.power.state;

import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerOnOffState extends WorkModeState {
    private static final String TAG = CarPowerOnOffState.class.getSimpleName();

    public CarPowerOnOffState(CarPowerBinder tsCarPowerBinder) {
        super(tsCarPowerBinder);
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setDisplayState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnDisplayOffState setDisplayState start");
            this.mCarPower.setDisplayState(1);
            LogUtil.info(TAG, "CarPowerOnDisplayOffState setDisplayState end");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setHardKeyState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnDisplayOffState setHardKeyState start");
            this.mCarPower.setHardKeyState(0);
            LogUtil.info(TAG, "CarPowerOnDisplayOffState setHardKeyState end");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setAudioState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnDisplayOffState setAudioState start");
            this.mCarPower.setAudioState(0);
            LogUtil.info(TAG, "CarPowerOnDisplayOffState setAudioState end");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setOtherState() {
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void sendReportMessage() {
        LogUtil.info(TAG, "sendReportMessage");
        LogUtil.info(TAG, "CarPowerOnDisplayOffState sendReportMessage start");
        this.mCarPower.sendReportMessage(6);
        LogUtil.info(TAG, "CarPowerOnDisplayOffState sendReportMessage end");
    }
}
