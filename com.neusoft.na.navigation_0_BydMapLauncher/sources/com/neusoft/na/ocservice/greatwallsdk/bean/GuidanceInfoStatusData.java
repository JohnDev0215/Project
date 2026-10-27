package com.neusoft.na.ocservice.greatwallsdk.bean;

/* JADX INFO: loaded from: classes2.dex */
public class GuidanceInfoStatusData {
    private ETAData ETA;
    private TBTData firstTBT;
    private SecondTBTData secondTBT;

    public TBTData getFirstTBT() {
        return this.firstTBT;
    }

    public void setFirstTBT(TBTData tBTData) {
        this.firstTBT = tBTData;
    }

    public SecondTBTData getSecondTBT() {
        return this.secondTBT;
    }

    public void setSecondTBT(SecondTBTData secondTBTData) {
        this.secondTBT = secondTBTData;
    }

    public ETAData getETA() {
        return this.ETA;
    }

    public void setETA(ETAData eTAData) {
        this.ETA = eTAData;
    }
}
