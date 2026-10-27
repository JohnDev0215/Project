package io.objectbox.sync;

import io.objectbox.sync.listener.SyncChangeListener;
import io.objectbox.sync.listener.SyncCompletedListener;
import io.objectbox.sync.listener.SyncConnectionListener;
import io.objectbox.sync.listener.SyncListener;
import io.objectbox.sync.listener.SyncLoginListener;
import io.objectbox.sync.listener.SyncTimeListener;
import java.io.Closeable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface SyncClient extends Closeable {
    boolean awaitFirstLogin(long j);

    boolean cancelUpdates();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    long getLastLoginCode();

    long getRoundtripTimeNanos();

    long getServerTimeDiffNanos();

    long getServerTimeNanos();

    String getServerUrl();

    boolean isLoggedIn();

    boolean isStarted();

    void notifyConnectionAvailable();

    boolean requestFullSync();

    boolean requestUpdates();

    boolean requestUpdatesOnce();

    void setLoginCredentials(SyncCredentials syncCredentials);

    void setSyncChangeListener(@Nullable SyncChangeListener syncChangeListener);

    void setSyncCompletedListener(@Nullable SyncCompletedListener syncCompletedListener);

    void setSyncConnectionListener(@Nullable SyncConnectionListener syncConnectionListener);

    void setSyncListener(@Nullable SyncListener syncListener);

    void setSyncLoginListener(@Nullable SyncLoginListener syncLoginListener);

    void setSyncTimeListener(@Nullable SyncTimeListener syncTimeListener);

    void start();

    ObjectsMessageBuilder startObjectsMessage(long j, @Nullable String str);

    void stop();
}
