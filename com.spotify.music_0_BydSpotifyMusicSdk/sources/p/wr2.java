package p;

import com.spotify.appstorage.userdirectory.NativeUserDirectoryManager;
import com.spotify.appstorage.userdirectory.UserDirectoryApi;
import com.spotify.appstorage.userdirectory.UserDirectoryCachePaths;
import com.spotify.appstorage.userdirectoryimpl.NativeUserDirectoryManagerImpl;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.sessionservice.SessionService;

/* JADX INFO: loaded from: classes.dex */
public final class wr2 implements UserDirectoryApi, sb2 {
    public final NativeUserDirectoryManagerImpl a;

    public wr2(SessionService sessionService, UserDirectoryCachePaths userDirectoryCachePaths) {
        this.a = NativeUserDirectoryManagerImpl.Companion.create(sessionService.getNativeSession().getCanonicalUsername(), userDirectoryCachePaths.getCachePath(), userDirectoryCachePaths.getSettingsPath());
    }

    @Override // com.spotify.appstorage.userdirectory.UserDirectoryApi
    public final String getCachePath() {
        return this.a.getCachePath();
    }

    @Override // com.spotify.appstorage.userdirectory.UserDirectoryApi
    public final NativeUserDirectoryManager getNativeUserDirectoryManager() {
        return this.a;
    }

    @Override // com.spotify.appstorage.userdirectory.UserDirectoryApi
    public final String getSettingsPath() {
        return this.a.getSettingsPath();
    }

    @Override // com.spotify.appstorage.userdirectory.UserDirectoryApi
    public final String makeCachePath(String str) {
        v03.j(str, "directoryName");
        return this.a.makeCachePath(str);
    }

    @Override // com.spotify.appstorage.userdirectory.UserDirectoryApi
    public final String makeSettingsPath(String str) {
        v03.j(str, "directoryName");
        return this.a.makeSettingsPath(str);
    }

    @Override // p.sb2
    public final void shutdown() {
        Logger.a("UserDirectoryService shutdown called", new Object[0]);
        this.a.destroy();
        Logger.a("UserDirectoryService shutdown completed", new Object[0]);
    }
}
