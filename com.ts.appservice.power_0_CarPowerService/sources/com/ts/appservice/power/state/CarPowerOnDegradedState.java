package com.ts.appservice.power.state;

import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerOnDegradedState extends WorkModeState {
    private static final String TAG = CarPowerOnDegradedState.class.getSimpleName();

    public CarPowerOnDegradedState(CarPowerBinder carPowerBinder) {
        super(carPowerBinder);
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setDisplayState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnDegradedState setDisplayState start");
            this.mCarPower.setDisplayState(1);
            LogUtil.info(TAG, "CarPowerOnDegradedState setDisplayState end");
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
