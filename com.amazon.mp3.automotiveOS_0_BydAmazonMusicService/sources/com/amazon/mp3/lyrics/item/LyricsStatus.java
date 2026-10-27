package com.amazon.mp3.lyrics.item;

/* JADX INFO: loaded from: classes3.dex */
public enum LyricsStatus {
    AVAILABLE("AVAILABLE"),
    NO_LYRICS("NO_LYRICS"),
    UNKNOWN("UNKNOWN");

    private final String mStatus;

    LyricsStatus(String status) {
        this.mStatus = status;
    }

    public static LyricsStatus fromString(String value) {
        for (LyricsStatus lyricsStatus : values()) {
            if (lyricsStatus.toString().equals(value)) {
                return lyricsStatus;
            }
        }
        return UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mStatus;
    }
}
