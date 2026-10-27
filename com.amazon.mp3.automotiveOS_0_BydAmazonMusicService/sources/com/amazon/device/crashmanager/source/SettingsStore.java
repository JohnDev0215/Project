package com.amazon.device.crashmanager.source;

/* JADX INFO: loaded from: classes2.dex */
public interface SettingsStore {

    public enum SettingsKey {
        DROPBOX_TIME_INDEX_KEY,
        MAP_BUILD_INDEX_KEY,
        RAMDUMP_TIME_INDEX_KEY,
        VERSION_NUMBER,
        DEDUPE_TIMESTAMPS_JSON,
        UPLOAD_TIMESTAMPS_JSON
    }

    long getLong(SettingsKey settingsKey, long j);

    String getString(SettingsKey settingsKey, String str);

    void saveLong(SettingsKey settingsKey, long j);

    void saveString(SettingsKey settingsKey, String str);
}
