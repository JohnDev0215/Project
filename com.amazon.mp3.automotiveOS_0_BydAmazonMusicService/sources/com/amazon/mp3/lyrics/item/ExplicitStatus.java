package com.amazon.mp3.lyrics.item;

/* JADX INFO: loaded from: classes3.dex */
public enum ExplicitStatus {
    EXPLICIT("EXPLICIT"),
    NON_EXPLICIT("NON_EXPLICIT"),
    UNFILTERED("UNFILTERED"),
    UNKNOWN("UNKNOWN");

    private final String mStatus;

    ExplicitStatus(String status) {
        this.mStatus = status;
    }

    public static ExplicitStatus fromString(String value) {
        for (ExplicitStatus explicitStatus : values()) {
            if (explicitStatus.toString().equals(value)) {
                return explicitStatus;
            }
        }
        return UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mStatus;
    }
}
