package com.amazon.music.destination;

/* JADX INFO: loaded from: classes4.dex */
public enum DeeplinkTier {
    PRIME("prime"),
    UNLIMITED_INDIVIDUAL("unlimited-individual"),
    UNLIMITED_FAMILY("unlimited-family");

    private final String value;

    DeeplinkTier(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public static DeeplinkTier fromString(String str) {
        for (DeeplinkTier deeplinkTier : values()) {
            if (deeplinkTier.value.equals(str)) {
                return deeplinkTier;
            }
        }
        return handleSpecialLegacyCasesIfNeeded(str);
    }

    private static DeeplinkTier handleSpecialLegacyCasesIfNeeded(String str) {
        if ("full-catalog".equals(str)) {
            return UNLIMITED_INDIVIDUAL;
        }
        return null;
    }
}
