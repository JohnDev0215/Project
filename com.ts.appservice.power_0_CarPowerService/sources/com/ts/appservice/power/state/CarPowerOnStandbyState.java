package com.ts.appservice.power.state;

import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;
import java.util.Timer;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerOnStandbyState extends WorkModeState {
    private static final String TAG = CarPowerOnStandbyState.class.getSimpleName();
    private Timer mTimer;

    public CarPowerOnStandbyState(CarPowerBinder tsCarPowerBinder) {
        super(tsCarPowerBinder);
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setDisplayState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnStandbyState setDisplayState start");
            this.mCarPower.setDisplayState(1);
            LogUtil.info(TAG, "CarPowerOnStandbyState setDisplayState end");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setHardKeyState() {
        if (this.mCarPower != null) {
            LogUtil.info(TAG, "CarPowerOnStandbyState setHardKeyState start");
            this.mCarPower.setHardKeyState(0);
            LogUtil.info(TAG, "CarPowerOnStandbyState setHardKeyState end");
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setAudioState() {
        if (this.mCarPower != null && !this.mCarPower.mShutdownState) {
            LogUtil.info(TAG, "CarPowerOnStandbyState setAudioState start");
            this.mCarPower.setAudioState(0);
            LogUtil.info(TAG, "CarPowerOnStandbyState setAudioState end");
        } else if (this.mCarPower != null) {
            this.mCarPower.mShutdownState = false;
        }
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void setOtherState() {
        this.mCarPower.forceStopThirdApp();
    }

    @Override // com.ts.appservice.power.state.WorkModeState
    public void sendReportMessage() {
        LogUtil.info(TAG, "CarPowerOnStandbyState sendReportMessage start");
        this.mCarPower.sendReportMessage(5);
        LogUtil.info(TAG, "CarPowerOnStandbyState sendReportMessage end");
    }

    private void timerForSleep() {
        if (this.mCarPower != null) {
            this.mCarPower.sendShutdownPendingMessage();
        }
    }
}
