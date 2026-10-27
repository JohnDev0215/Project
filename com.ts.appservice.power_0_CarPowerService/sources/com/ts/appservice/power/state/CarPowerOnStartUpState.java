package com.ts.appservice.power.state;

import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerOnStartUpState extends WorkModeState {
    private static final String TAG = CarPowerOnStartUpState.class.getSimpleName();

    public CarPowerOnStartUpState(CarPowerBinder carPowerBinder) {
        super(carPowerBinder);
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setDisplayState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnStandbyState setDisplayState start");
            this.mCarPower.setDisplayState(0);
            LogUtil.info(TAG, "CarPowerOnStandbyState setDisplayState end");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setHardKeyState() {
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setAudioState() {
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setOtherState() {
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void sendReportMessage() {
    }
}
