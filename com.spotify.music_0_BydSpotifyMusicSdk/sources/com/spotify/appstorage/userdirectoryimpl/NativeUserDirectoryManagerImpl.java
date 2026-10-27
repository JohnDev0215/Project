package com.spotify.appstorage.userdirectoryimpl;

import com.spotify.appstorage.userdirectory.NativeUserDirectoryManager;
import com.spotify.appstorage.userdirectory.UserDirectoryManager;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: classes.dex */
public final class NativeUserDirectoryManagerImpl implements NativeUserDirectoryManager, UserDirectoryManager {
    public static final Companion Companion = new Companion(null);
    private final String cachePath;
    private long nThis;
    private final String settingsPath;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NativeUserDirectoryManagerImpl create(String str, String str2, String str3) {
            return NativeUserDirectoryManagerImpl.create(str, str2, str3);
        }
    }

    private NativeUserDirectoryManagerImpl() {
    }

    public static final native NativeUserDirectoryManagerImpl create(String str, String str2, String str3);

    @Override // com.spotify.appstorage.userdirectory.NativeUserDirectoryManager
    public native void destroy();

    @Override // com.spotify.appstorage.userdirectory.UserDirectoryManager
    public native String getCachePath();

    @Override // com.spotify.appstorage.userdirectory.NativeUserDirectoryManager
    public long getNThis() {
        return this.nThis;
    }

    @Override // com.spotify.appstorage.userdirectory.UserDirectoryManager
    public native String getSettingsPath();

    @Override // com.spotify.appstorage.userdirectory.UserDirectoryManager
    public native String makeCachePath(String str);

    @Override // com.spotify.appstorage.userdirectory.UserDirectoryManager
    public native String makeSettingsPath(String str);
}
