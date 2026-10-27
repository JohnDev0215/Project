package com.amazon.music.externalstorage;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public enum StorageLocation {
    DEVICE("Device"),
    SDCARD("Sdcard");

    private static final Map<String, StorageLocation> valuesByCode = new HashMap();
    private final String code;

    static {
        for (StorageLocation storageLocation : values()) {
            valuesByCode.put(storageLocation.code, storageLocation);
        }
    }

    StorageLocation(String str) {
        this.code = str;
    }

    public static StorageLocation lookupByCode(String str) {
        StorageLocation storageLocation = valuesByCode.get(str);
        if (storageLocation != null) {
            return storageLocation;
        }
        throw new IllegalArgumentException(String.format("Incorrect code %s provided.", str));
    }

    public String getCode() {
        return this.code;
    }
}
