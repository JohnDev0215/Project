package com.byd.profile.request.cell;

import android.content.Context;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class AirRequest extends BaseRequest<AirValue> {
    private BYDAutoAcDevice mBYDAutoAcDevice;

    @Override // com.byd.profile.request.cell.BaseRequest
    protected boolean canReset() {
        return false;
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public void initRequestValues(AirValue wantedAirValue) {
    }

    public AirRequest(Context context, AirValue wantedValue) {
        super(context, wantedValue);
        try {
            this.mBYDAutoAcDevice = BYDAutoAcDevice.getInstance(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.byd.profile.request.cell.BaseRequest
    public boolean handleRequest(AirValue airValue) {
        BydLogHelper.logD("handleRequest AirValue");
        if (this.mBYDAutoAcDevice != null) {
            try {
                if (airValue.isControlMode) {
                    int acControlMode = this.mBYDAutoAcDevice.getAcControlMode();
                    BydLogHelper.logD("acControlMode = " + acControlMode);
                    if (acControlMode == 1) {
                        this.mBYDAutoAcDevice.setAcWindLevel(0, 2);
                    }
                } else {
                    boolean z = this.mBYDAutoAcDevice.getAcStartState() == 1;
                    BydLogHelper.logD("isStarted = " + z);
                    if (z) {
                        int acCycleMode = this.mBYDAutoAcDevice.getAcCycleMode();
                        BydLogHelper.logD("acCycleMode = " + acCycleMode);
                        if (acCycleMode == 1) {
                            this.mBYDAutoAcDevice.setAcCycleMode(0, 0);
                        }
                    } else {
                        this.mBYDAutoAcDevice.setAcWindLevel(0, 1);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return true;
    }

    public static class AirValue extends BaseValue {
        protected int controlMode;
        protected int cycleMode;
        protected boolean isControlMode;
        protected int wind;

        public AirValue(boolean isControlMode) {
            this.isControlMode = isControlMode;
        }

        public void setMode(int mode, int wind) {
            if (this.isControlMode) {
                this.controlMode = mode;
            } else {
                this.cycleMode = mode;
            }
            this.wind = wind;
        }
    }
}
