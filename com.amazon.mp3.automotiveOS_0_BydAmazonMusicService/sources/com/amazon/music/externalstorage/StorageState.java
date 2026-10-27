package com.amazon.music.externalstorage;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public enum StorageState {
    STORAGE_STATE_UNKNOWN(-1),
    STORAGE_MOUNTED(0),
    STORAGE_REMOVED(1);

    private static final Map<Integer, StorageState> valuesByCode = new HashMap();
    private final int code;

    static {
        for (StorageState storageState : values()) {
            valuesByCode.put(Integer.valueOf(storageState.code), storageState);
        }
    }

    StorageState(int i) {
        this.code = i;
    }

    public static StorageState lookupByCode(int i) {
        StorageState storageState = valuesByCode.get(Integer.valueOf(i));
        if (storageState != null) {
            return storageState;
        }
        throw new IllegalArgumentException(String.format("Incorrect code %s provided", "" + i));
    }

    public int getCode() {
        return this.code;
    }
}
