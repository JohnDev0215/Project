package com.ts.appservice.power.state;

import com.ts.appservice.power.CarPowerBinder;

/* JADX INFO: loaded from: classes.dex */
public abstract class WorkModeState {
    protected CarPowerBinder mCarPower;

    public abstract void sendReportMessage();

    public abstract void setAudioState();

    public abstract void setDisplayState();

    public abstract void setHardKeyState();

    public abstract void setOtherState();

    protected WorkModeState(CarPowerBinder carPowerBinder) {
        this.mCarPower = carPowerBinder;
    }

    public void changeState() {
        sendReportMessage();
        setDisplayState();
        setHardKeyState();
        setAudioState();
        setOtherState();
    }
}
