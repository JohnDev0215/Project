package com.ts.lib.car.hardware.xcall;

import com.ts.lib.car.CarBaseProperty;

/* JADX INFO: loaded from: classes3.dex */
public class XcallEventChangeValue extends CarBaseProperty {
    private int mCallBackModeR;
    private int mCallStatusR;
    private int mCallTypeR;
    private int mCallTypeS;
    private int mRequestTypeS;

    public int getCallBackModeR() {
        return this.mCallBackModeR;
    }

    public void setCallBackModeR(int callBackModeR) {
        this.mCallBackModeR = callBackModeR;
    }

    public int getCallStatusR() {
        return this.mCallStatusR;
    }

    public void setCallStatusR(int callStatusR) {
        this.mCallStatusR = callStatusR;
    }

    public int getCallTypeR() {
        return this.mCallTypeR;
    }

    public void setCallTypeR(int callTypeR) {
        this.mCallTypeR = callTypeR;
    }

    public int getCallTypeS() {
        return this.mCallTypeS;
    }

    public void setCallTypeS(int callTypeS) {
        this.mCallTypeS = callTypeS;
    }

    public int getRequestTypeS() {
        return this.mRequestTypeS;
    }

    public void setRequestTypeS(int requestTypeS) {
        this.mRequestTypeS = requestTypeS;
    }
}
