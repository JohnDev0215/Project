package io.objectbox.sync.server;

import io.objectbox.InternalAccess;
import io.objectbox.sync.SyncCredentials;
import io.objectbox.sync.SyncCredentialsToken;
import io.objectbox.sync.listener.SyncChangeListener;
import java.util.Iterator;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SyncServerImpl implements SyncServer {
    private volatile long handle;

    @Nullable
    private volatile SyncChangeListener syncChangeListener;
    private final String url;

    private native void nativeAddPeer(long j, String str, long j2, @Nullable byte[] bArr);

    private static native long nativeCreate(long j, String str, @Nullable String str2);

    private native void nativeDelete(long j);

    private native int nativeGetPort(long j);

    private native String nativeGetStatsString(long j);

    private native boolean nativeIsRunning(long j);

    private native void nativeSetAuthenticator(long j, long j2, @Nullable byte[] bArr);

    private native void nativeSetSyncChangesListener(long j, @Nullable SyncChangeListener syncChangeListener);

    private native void nativeStart(long j);

    private native void nativeStop(long j);

    SyncServerImpl(SyncServerBuilder syncServerBuilder) {
        this.url = syncServerBuilder.url;
        long jNativeCreate = nativeCreate(InternalAccess.getHandle(syncServerBuilder.boxStore), this.url, syncServerBuilder.certificatePath);
        if (jNativeCreate == 0) {
            throw new RuntimeException("Failed to create sync server: handle is zero.");
        }
        this.handle = jNativeCreate;
        Iterator<SyncCredentials> it = syncServerBuilder.credentials.iterator();
        while (it.hasNext()) {
            SyncCredentialsToken syncCredentialsToken = (SyncCredentialsToken) it.next();
            nativeSetAuthenticator(jNativeCreate, syncCredentialsToken.getTypeId(), syncCredentialsToken.getTokenBytes());
            syncCredentialsToken.clear();
        }
        for (PeerInfo peerInfo : syncServerBuilder.peers) {
            SyncCredentialsToken syncCredentialsToken2 = (SyncCredentialsToken) peerInfo.credentials;
            nativeAddPeer(jNativeCreate, peerInfo.url, syncCredentialsToken2.getTypeId(), syncCredentialsToken2.getTokenBytes());
        }
        if (syncServerBuilder.changeListener != null) {
            setSyncChangeListener(syncServerBuilder.changeListener);
        }
    }

    private long getHandle() {
        long j = this.handle;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("SyncServer already closed");
    }

    @Override // io.objectbox.sync.server.SyncServer
    public String getUrl() {
        return this.url;
    }

    @Override // io.objectbox.sync.server.SyncServer
    public int getPort() {
        return nativeGetPort(getHandle());
    }

    @Override // io.objectbox.sync.server.SyncServer
    public boolean isRunning() {
        return nativeIsRunning(getHandle());
    }

    @Override // io.objectbox.sync.server.SyncServer
    public String getStatsString() {
        return nativeGetStatsString(getHandle());
    }

    @Override // io.objectbox.sync.server.SyncServer
    public void setSyncChangeListener(@Nullable SyncChangeListener syncChangeListener) {
        this.syncChangeListener = syncChangeListener;
        nativeSetSyncChangesListener(getHandle(), syncChangeListener);
    }

    @Override // io.objectbox.sync.server.SyncServer
    public void start() {
        nativeStart(getHandle());
    }

    @Override // io.objectbox.sync.server.SyncServer
    public void stop() {
        nativeStop(getHandle());
    }

    @Override // io.objectbox.sync.server.SyncServer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j = this.handle;
        this.handle = 0L;
        if (j != 0) {
            nativeDelete(j);
        }
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
