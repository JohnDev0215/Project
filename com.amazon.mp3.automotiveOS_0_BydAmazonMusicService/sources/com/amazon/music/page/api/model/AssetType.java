package com.amazon.music.page.api.model;

/* JADX INFO: loaded from: classes4.dex */
public enum AssetType {
    AUDIO("AUDIO"),
    VIDEO("VIDEO");

    private String value;

    AssetType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AssetType fromValue(String str) {
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        for (AssetType assetType : values()) {
            if (assetType.toString().equals(str)) {
                return assetType;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
