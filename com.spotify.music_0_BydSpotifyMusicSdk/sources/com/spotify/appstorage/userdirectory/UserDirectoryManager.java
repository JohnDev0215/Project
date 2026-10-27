package com.spotify.appstorage.userdirectory;

/* JADX INFO: loaded from: classes.dex */
public interface UserDirectoryManager {
    String getCachePath();

    String getSettingsPath();

    String makeCachePath(String str);

    String makeSettingsPath(String str);
}
