package com.amazon.avod.drm.playready;

/* JADX INFO: loaded from: classes2.dex */
public enum PlayReadyLicenseType {
    NORIGHT(0, true, "Playback not permitted"),
    UNLIM(1, true, "Playback unlimited"),
    COUNT(2, false, "Playback valid <n> times"),
    FROM(3, true, "Playback valid from <date>"),
    UNTIL(4, true, "Playback valid until <date>"),
    FROM_UNTIL(5, true, "Playback valid from <date> until <date>"),
    COUNT_FROM(6, false, "Playback valid <n> times from <date>"),
    COUNT_UNTIL(7, false, "Playback valid <n> times until <date>"),
    COUNT_FROM_UNTIL(8, false, "Playback valid <n> times from <date> until <date>"),
    EXPIRATION_AFTER_FIRSTUSE(9, true, "Playback valid for <n> hours from first use"),
    FORCE_SYNC(10, false, "Internal error: unexpected code FORCE_SYNC"),
    NOT_FOUND(11, false, "Internal error: unexpected code NOT_FOUND"),
    ENTRY_MARKED_FOR_DELETION(12, false, "Internal error: unexpected code ENTRY_MARKED_FOR_DELETION"),
    INDEX_OUT_OF_RANGE(13, false, "Internal error: license type out of valid range");

    private final int mCategory;
    private final String mDisplayName;
    private final boolean mExpected;
    private static final PlayReadyLicenseType[] TYPES_BY_INDEX = {NORIGHT, UNLIM, COUNT, FROM, UNTIL, FROM_UNTIL, COUNT_FROM, COUNT_UNTIL, COUNT_FROM_UNTIL, EXPIRATION_AFTER_FIRSTUSE, FORCE_SYNC, NOT_FOUND, ENTRY_MARKED_FOR_DELETION};

    PlayReadyLicenseType(int i, boolean z, String str) {
        this.mCategory = i;
        this.mExpected = z;
        this.mDisplayName = str;
    }

    public static PlayReadyLicenseType fromNumericString(String str) {
        int i = Integer.parseInt(str, 16);
        if (i < 0 || i >= INDEX_OUT_OF_RANGE.mCategory) {
            return INDEX_OUT_OF_RANGE;
        }
        return TYPES_BY_INDEX[i];
    }

    public int getCategoryCode() {
        return this.mCategory;
    }

    public boolean isExpected() {
        return this.mExpected;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mDisplayName;
    }
}
