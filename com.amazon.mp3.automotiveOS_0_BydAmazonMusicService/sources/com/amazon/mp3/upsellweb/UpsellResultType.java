package com.amazon.mp3.upsellweb;

/* JADX INFO: loaded from: classes4.dex */
public enum UpsellResultType {
    CONTINUE("continue"),
    SUCCESS("subscribeResult=SUCCESS");

    private final String mResultType;

    UpsellResultType(String resultType) {
        this.mResultType = resultType;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mResultType;
    }
}
