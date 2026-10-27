package com.android.systemui.statusbar.phone;

/* JADX INFO: loaded from: classes.dex */
public class StatusBean {
    private int mCameraOnSaved;
    private int mCarModeValueOnSaved = -1;
    private int mChargingScheduleOnSaved = -1;
    private int mMicOnSaved;
    private int mMsgShownOnSaved;
    private String mPMInOnSaved;
    private String mPMOutOnSaved;

    public int getChargingScheduleOnSaved() {
        return this.mChargingScheduleOnSaved;
    }

    public void setChargingScheduleOnSaved(int mChargingScheduleOnSaved) {
        this.mChargingScheduleOnSaved = mChargingScheduleOnSaved;
    }

    public int getCarModeValueOnSaved() {
        return this.mCarModeValueOnSaved;
    }

    public void setCarModeValueOnSaved(int mCarModeValueOnSaved) {
        this.mCarModeValueOnSaved = mCarModeValueOnSaved;
    }

    public String getPMInOnSaved() {
        return this.mPMInOnSaved;
    }

    public void setPMInOnSaved(String mPMInOnSaved) {
        this.mPMInOnSaved = mPMInOnSaved;
    }

    public String getPMOutOnSaved() {
        return this.mPMOutOnSaved;
    }

    public void setPMOutOnSaved(String mPMOutOnSaved) {
        this.mPMOutOnSaved = mPMOutOnSaved;
    }

    public int getMicOnSaved() {
        return this.mMicOnSaved;
    }

    public void setMicOnSaved(int mMicOnSaved) {
        this.mMicOnSaved = mMicOnSaved;
    }

    public int getCameraOnSaved() {
        return this.mCameraOnSaved;
    }

    public void setCameraOnSaved(int mCameraOnSaved) {
        this.mCameraOnSaved = mCameraOnSaved;
    }

    public int getMsgShownOnSaved() {
        return this.mMsgShownOnSaved;
    }

    public void setMsgShownOnSaved(int mMsgShownOnSaved) {
        this.mMsgShownOnSaved = mMsgShownOnSaved;
    }
}
