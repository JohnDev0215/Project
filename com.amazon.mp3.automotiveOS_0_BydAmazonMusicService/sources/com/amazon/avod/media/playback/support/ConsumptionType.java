package com.amazon.avod.media.playback.support;

/* JADX INFO: loaded from: classes2.dex */
public enum ConsumptionType {
    Download("DOWNLOAD"),
    Streaming("STREAMING");

    private final String mPartnerServiceParameterName;

    ConsumptionType(String str) {
        this.mPartnerServiceParameterName = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return Download.getPartnerServiceParameterName(this);
    }

    public String getPartnerServiceParameterName(ConsumptionType consumptionType) {
        return consumptionType.mPartnerServiceParameterName;
    }
}
