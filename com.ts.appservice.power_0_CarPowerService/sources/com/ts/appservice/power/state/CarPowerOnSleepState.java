package com.ts.appservice.power.state;

import com.ts.appservice.power.CarPowerBinder;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerOnSleepState extends WorkModeState {
    public CarPowerOnSleepState(CarPowerBinder carPowerBinder) {
        super(carPowerBinder);
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setDisplayState() {
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setHardKeyState() {
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setAudioState() {
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setOtherState() {
        this.mCarPower.shutdown();
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void sendReportMessage() {
    }
}
