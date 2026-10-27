package com.ts.carplay.lib.iap2manager;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayPascd {
    private static CarPlayPascd mCarPlayPascd = new CarPlayPascd();
    private String checksum;
    private String mSpeed;
    private int sampleCount;
    private char sensorType;
    private String sentenceId;
    private int slipDetect;
    private String timeOffSet;
    private String timestamp;
    private char transmissionState;

    private CarPlayPascd() {
    }

    public static CarPlayPascd getInstance() {
        return mCarPlayPascd;
    }

    public String getSentenceId() {
        return this.sentenceId;
    }

    public void setSentenceId(String sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public char getSensorType() {
        return this.sensorType;
    }

    public void setSensorType(char sensorType) {
        this.sensorType = sensorType;
    }

    public char getTransmissionState() {
        return this.transmissionState;
    }

    public void setTransmissionState(char transmissionState) {
        this.transmissionState = transmissionState;
    }

    public int getSlipDetect() {
        return this.slipDetect;
    }

    public void setSlipDetect(int slipDetect) {
        this.slipDetect = slipDetect;
    }

    public int getSampleCount() {
        return this.sampleCount;
    }

    public void setSampleCount(int sampleCount) {
        this.sampleCount = sampleCount;
    }

    public String getTimeOffset_i() {
        return this.timeOffSet;
    }

    public void setTimeOffSet(String timeOffSet) {
        this.timeOffSet = timeOffSet;
    }

    public String getSpeed_i() {
        return this.mSpeed;
    }

    public void setSpeed(String speed) {
        this.mSpeed = speed;
    }

    public String toString() {
        return this.sentenceId + "," + this.timestamp + "," + this.sensorType + "," + this.transmissionState + "," + this.slipDetect + "," + this.sampleCount + "," + this.timeOffSet + "," + this.mSpeed;
    }
}
