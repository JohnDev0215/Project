package com.neusoft.na.ocservice.greatwallsdk.bean;

/* JADX INFO: loaded from: classes2.dex */
public class ETAData {
    private int remainDistance;
    private int remainTime;
    private int remianBatteryPower;
    private String remainTimeString = "";
    private String arrivalTimeString = "";
    private String remainDistanceString = "";
    private String remianBatteryPowerString = "";

    public int getRemainTime() {
        return this.remainTime;
    }

    public void setRemainTime(int i) {
        this.remainTime = i;
    }

    public int getRemainDistance() {
        return this.remainDistance;
    }

    public void setRemainDistance(int i) {
        this.remainDistance = i;
    }

    public int getRemianBatteryPower() {
        return this.remianBatteryPower;
    }

    public void setRemianBatteryPower(int i) {
        this.remianBatteryPower = i;
    }

    public String getRemainTimeString() {
        return this.remainTimeString;
    }

    public void setRemainTimeString(String str) {
        this.remainTimeString = str;
    }

    public String getArrivalTimeString() {
        return this.arrivalTimeString;
    }

    public void setArrivalTimeString(String str) {
        this.arrivalTimeString = str;
    }

    public String getRemainDistanceString() {
        return this.remainDistanceString;
    }

    public void setRemainDistanceString(String str) {
        this.remainDistanceString = str;
    }

    public String getRemianBatteryPowerString() {
        return this.remianBatteryPowerString;
    }

    public void setRemianBatteryPowerString(String str) {
        this.remianBatteryPowerString = str;
    }
}
