package com.amazon.avod.ads.parser.vast;

/* JADX INFO: loaded from: classes2.dex */
public enum VastExtensionType {
    SKIPPABLE("skippable"),
    CONTENT_METADATA("contentMetadata"),
    WATCHLIST("watchlist");

    private final String mStringRepresentation;

    VastExtensionType(String str) {
        this.mStringRepresentation = str;
    }

    public boolean equals(String str) {
        return this.mStringRepresentation.equals(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mStringRepresentation;
    }
}
