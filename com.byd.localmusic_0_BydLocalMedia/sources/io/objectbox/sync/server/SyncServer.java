package io.objectbox.sync.server;

import io.objectbox.sync.listener.SyncChangeListener;
import java.io.Closeable;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface SyncServer extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    int getPort();

    String getStatsString();

    String getUrl();

    boolean isRunning();

    void setSyncChangeListener(@Nullable SyncChangeListener syncChangeListener);

    void start();

    void stop();
}
