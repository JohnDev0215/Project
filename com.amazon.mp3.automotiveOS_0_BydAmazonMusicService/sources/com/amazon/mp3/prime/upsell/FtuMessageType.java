package com.amazon.mp3.prime.upsell;

/* JADX INFO: loaded from: classes4.dex */
public enum FtuMessageType {
    UNKNOWN(""),
    INITIALIZED("initialized"),
    NOT_NOW("notnow"),
    CONTINUE("continue"),
    START_PLAYING("startPlaying"),
    EMIT_METRIC("emitMetric");

    private final String mName;

    FtuMessageType(String name) {
        this.mName = name;
    }

    public String getName() {
        return this.mName;
    }

    public static FtuMessageType fromString(final String value) {
        for (FtuMessageType ftuMessageType : values()) {
            if (ftuMessageType.mName.equals(value)) {
                return ftuMessageType;
            }
        }
        return UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mName;
    }
}
