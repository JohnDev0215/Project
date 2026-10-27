package com.spotify.appstorage.userdirectory;

import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class UserDirectoryCachePaths {
    private final String cachePath;
    private final String settingsPath;

    public UserDirectoryCachePaths(String str, String str2) {
        v03.j(str, "cachePath");
        v03.j(str2, "settingsPath");
        this.cachePath = str;
        this.settingsPath = str2;
    }

    public static /* synthetic */ UserDirectoryCachePaths copy$default(UserDirectoryCachePaths userDirectoryCachePaths, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userDirectoryCachePaths.cachePath;
        }
        if ((i & 2) != 0) {
            str2 = userDirectoryCachePaths.settingsPath;
        }
        return userDirectoryCachePaths.copy(str, str2);
    }

    public final String component1() {
        return this.cachePath;
    }

    public final String component2() {
        return this.settingsPath;
    }

    public final UserDirectoryCachePaths copy(String str, String str2) {
        v03.j(str, "cachePath");
        v03.j(str2, "settingsPath");
        return new UserDirectoryCachePaths(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserDirectoryCachePaths)) {
            return false;
        }
        UserDirectoryCachePaths userDirectoryCachePaths = (UserDirectoryCachePaths) obj;
        return v03.b(this.cachePath, userDirectoryCachePaths.cachePath) && v03.b(this.settingsPath, userDirectoryCachePaths.settingsPath);
    }

    public final String getCachePath() {
        return this.cachePath;
    }

    public final String getSettingsPath() {
        return this.settingsPath;
    }

    public int hashCode() {
        return this.settingsPath.hashCode() + (this.cachePath.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UserDirectoryCachePaths(cachePath=");
        sb.append(this.cachePath);
        sb.append(", settingsPath=");
        return hf2.d(sb, this.settingsPath, ')');
    }
}
