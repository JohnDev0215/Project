package com.amazon.minerva.client.common.api;

/* JADX INFO: loaded from: classes3.dex */
public enum MinervaVersion {
    BASE(5),
    ADDITIONAL_PREDEFINEDS_SET_1(7),
    AGGREGATION(8),
    RECORD_CALLBACK(9),
    RECORD_CALLBACK_MGID_AND_SCHEMA(10),
    LOW_PRIORITY_METRIC_OPTION(11);

    private int mVersionNumber;

    MinervaVersion(int i) {
        this.mVersionNumber = i;
    }

    public int getVersionNumber() {
        return this.mVersionNumber;
    }
}
