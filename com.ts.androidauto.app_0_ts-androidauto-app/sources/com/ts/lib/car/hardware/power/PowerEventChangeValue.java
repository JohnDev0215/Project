package com.ts.lib.car.hardware.power;

import com.ts.lib.car.CarBaseProperty;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class PowerEventChangeValue extends CarBaseProperty {
    private int[] mGetOffScreenFlagResult;
    private int[] mIviPowerModeChanged;
    private int[] mIviPowerStateChanged;
    private int[] mQueryOffScreenFlag;
    private int mVehicleBcallStatusReq;
    private int mVehiclePowerShutdownRequest;
    private int[] mVehiclePowerStateReport;

    public int[] getQueryOffScreenFlag() {
        return this.mQueryOffScreenFlag;
    }

    public void setQueryOffScreenFlag(int[] queryOffScreenFlag) {
        this.mQueryOffScreenFlag = queryOffScreenFlag;
    }

    public int[] getGetOffScreenFlagResult() {
        return this.mGetOffScreenFlagResult;
    }

    public void setGetOffScreenFlagResult(int[] getOffScreenFlagResult) {
        this.mGetOffScreenFlagResult = getOffScreenFlagResult;
    }

    public int[] getIviPowerModeChanged() {
        return this.mIviPowerModeChanged;
    }

    public void setIviPowerModeChanged(int[] iviPowerModeChanged) {
        this.mIviPowerModeChanged = iviPowerModeChanged;
    }

    public int[] getIviPowerStateChanged() {
        return this.mIviPowerStateChanged;
    }

    public void setIviPowerStateChanged(int[] iviPowerStateChanged) {
        this.mIviPowerStateChanged = iviPowerStateChanged;
    }

    public int getVehicleBcallStatusReq() {
        return this.mVehicleBcallStatusReq;
    }

    public void setVehicleBcallStatusReq(int vehicleBcallStatusReq) {
        this.mVehicleBcallStatusReq = vehicleBcallStatusReq;
    }

    public int getVehiclePowerShutdownRequest() {
        return this.mVehiclePowerShutdownRequest;
    }

    public void setVehiclePowerShutdownRequest(int vehiclePowerShutdownRequest) {
        this.mVehiclePowerShutdownRequest = vehiclePowerShutdownRequest;
    }

    public int[] getVehiclePowerStateReport() {
        return this.mVehiclePowerStateReport;
    }

    public void setVehiclePowerStateReport(int[] vehiclePowerStateReport) {
        this.mVehiclePowerStateReport = vehiclePowerStateReport;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PowerEventChangeValue{");
        builder.append("PropertyId=0x" + propertyIdTenToHex());
        builder.append(" areaId=0x" + areaIdTenToHex());
        builder.append(" status=" + getStatus());
        builder.append(" timestamp=" + getTimestamp());
        builder.append(" value=" + getValue());
        builder.append(", mIviPowerModeChanged=" + Arrays.toString(this.mIviPowerModeChanged));
        builder.append(", mIviPowerStateChanged=" + Arrays.toString(this.mIviPowerStateChanged));
        builder.append(", mVehicleBcallStatusReq=" + this.mVehicleBcallStatusReq);
        builder.append(", mVehiclePowerShutdownRequest=" + this.mVehiclePowerShutdownRequest);
        builder.append(", mVehiclePowerStateReport=" + Arrays.toString(this.mVehiclePowerStateReport));
        builder.append("}");
        return builder.toString();
    }
}
