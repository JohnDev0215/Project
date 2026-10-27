package com.spotify.appstorage.userdirectory;

/* JADX INFO: loaded from: classes.dex */
public interface UserDirectoryApi {
    String getCachePath();

    NativeUserDirectoryManager getNativeUserDirectoryManager();

    String getSettingsPath();

    String makeCachePath(String str);

    String makeSettingsPath(String str);
}
