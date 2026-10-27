package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSSEIPicTiming {
    voOSSEIClockTimeStamp[] getClock();

    int getCpbDpbDelaysPresentFlag();

    int getCpbRemovalDelay();

    int getDpbOutputDelay();

    int getNumClockTs();

    int getPictureStructure();

    int getPictureStructurePresentFlag();
}
