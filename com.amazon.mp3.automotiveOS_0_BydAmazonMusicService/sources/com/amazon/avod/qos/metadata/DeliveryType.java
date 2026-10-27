package com.amazon.avod.qos.metadata;

/* JADX INFO: loaded from: classes2.dex */
public enum DeliveryType {
    DOWNLOAD("download"),
    STREAMING("streaming"),
    PRECACHE("precache"),
    FREETIME("freetime");

    private final String mReportingName;

    DeliveryType(String str) {
        this.mReportingName = str;
    }

    public String getReportingName() {
        return this.mReportingName;
    }
}
