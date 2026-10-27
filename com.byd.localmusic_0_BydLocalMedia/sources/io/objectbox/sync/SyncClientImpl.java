package io.objectbox.sync;

import io.objectbox.BoxStore;
import io.objectbox.InternalAccess;
import io.objectbox.sync.SyncBuilder;
import io.objectbox.sync.listener.SyncChangeListener;
import io.objectbox.sync.listener.SyncCompletedListener;
import io.objectbox.sync.listener.SyncConnectionListener;
import io.objectbox.sync.listener.SyncListener;
import io.objectbox.sync.listener.SyncLoginListener;
import io.objectbox.sync.listener.SyncTimeListener;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SyncClientImpl implements SyncClient {

    @Nullable
    private BoxStore boxStore;

    @Nullable
    private volatile SyncCompletedListener completedListener;

    @Nullable
    private volatile SyncConnectionListener connectionListener;

    @Nullable
    private final ConnectivityMonitor connectivityMonitor;
    private volatile long handle;
    private final InternalSyncClientListener internalListener;
    private volatile long lastLoginCode;

    @Nullable
    private volatile SyncLoginListener loginListener;
    private final String serverUrl;
    private volatile boolean started;

    @Nullable
    private volatile SyncTimeListener timeListener;

    private native boolean nativeCancelUpdates(long j);

    private static native long nativeCreate(long j, String str, @Nullable String[] strArr);

    private native void nativeDelete(long j);

    private native int nativeGetState(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeObjectsMessageAddBytes(long j, long j2, byte[] bArr, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeObjectsMessageAddString(long j, long j2, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public native boolean nativeObjectsMessageSend(long j, long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public native long nativeObjectsMessageStart(long j, @Nullable String str);

    private native boolean nativeRequestFullSync(long j, boolean z);

    private native boolean nativeRequestUpdates(long j, boolean z);

    private native long nativeRoundtripTime(long j);

    private native long nativeServerTime(long j);

    private native long nativeServerTimeDiff(long j);

    private native void nativeSetListener(long j, @Nullable InternalSyncClientListener internalSyncClientListener);

    private native void nativeSetLoginInfo(long j, long j2, @Nullable byte[] bArr);

    private native void nativeSetRequestUpdatesMode(long j, boolean z, boolean z2);

    private native void nativeSetSyncChangesListener(long j, @Nullable SyncChangeListener syncChangeListener);

    private native void nativeSetUncommittedAcks(long j, boolean z);

    private native void nativeStart(long j);

    private native void nativeStop(long j);

    private native boolean nativeTriggerReconnect(long j);

    SyncClientImpl(SyncBuilder syncBuilder) {
        this.boxStore = syncBuilder.boxStore;
        this.serverUrl = syncBuilder.url;
        this.connectivityMonitor = syncBuilder.platform.getConnectivityMonitor();
        long jNativeCreate = nativeCreate(InternalAccess.getHandle(syncBuilder.boxStore), this.serverUrl, syncBuilder.trustedCertPaths);
        if (jNativeCreate == 0) {
            throw new RuntimeException("Failed to create sync client: handle is zero.");
        }
        this.handle = jNativeCreate;
        if (syncBuilder.requestUpdatesMode != SyncBuilder.RequestUpdatesMode.AUTO) {
            nativeSetRequestUpdatesMode(jNativeCreate, syncBuilder.requestUpdatesMode != SyncBuilder.RequestUpdatesMode.MANUAL, false);
        }
        if (syncBuilder.uncommittedAcks) {
            nativeSetUncommittedAcks(jNativeCreate, true);
        }
        if (syncBuilder.listener != null) {
            setSyncListener(syncBuilder.listener);
        } else {
            this.loginListener = syncBuilder.loginListener;
            this.completedListener = syncBuilder.completedListener;
            if (syncBuilder.changeListener != null) {
                setSyncChangeListener(syncBuilder.changeListener);
            }
            this.connectionListener = syncBuilder.connectionListener;
            this.timeListener = syncBuilder.timeListener;
        }
        InternalSyncClientListener internalSyncClientListener = new InternalSyncClientListener();
        this.internalListener = internalSyncClientListener;
        nativeSetListener(jNativeCreate, internalSyncClientListener);
        setLoginCredentials(syncBuilder.credentials);
        InternalAccess.setSyncClient(syncBuilder.boxStore, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getHandle() {
        long j = this.handle;
        if (j != 0) {
            return j;
        }
        throw new IllegalStateException("SyncClient already closed");
    }

    @Override // io.objectbox.sync.SyncClient
    public String getServerUrl() {
        return this.serverUrl;
    }

    @Override // io.objectbox.sync.SyncClient
    public long getLastLoginCode() {
        return this.lastLoginCode;
    }

    @Override // io.objectbox.sync.SyncClient
    public boolean isLoggedIn() {
        return this.lastLoginCode == 20;
    }

    @Override // io.objectbox.sync.SyncClient
    public long getServerTimeNanos() {
        return nativeServerTime(getHandle());
    }

    @Override // io.objectbox.sync.SyncClient
    public long getServerTimeDiffNanos() {
        return nativeServerTimeDiff(getHandle());
    }

    @Override // io.objectbox.sync.SyncClient
    public long getRoundtripTimeNanos() {
        return nativeRoundtripTime(getHandle());
    }

    public SyncState getSyncState() {
        return SyncState.fromId(nativeGetState(getHandle()));
    }

    @Override // io.objectbox.sync.SyncClient
    public void setSyncLoginListener(@Nullable SyncLoginListener syncLoginListener) {
        this.loginListener = syncLoginListener;
    }

    @Override // io.objectbox.sync.SyncClient
    public void setSyncCompletedListener(@Nullable SyncCompletedListener syncCompletedListener) {
        this.completedListener = syncCompletedListener;
    }

    @Override // io.objectbox.sync.SyncClient
    public void setSyncChangeListener(@Nullable SyncChangeListener syncChangeListener) {
        nativeSetSyncChangesListener(getHandle(), syncChangeListener);
    }

    @Override // io.objectbox.sync.SyncClient
    public void setSyncTimeListener(@Nullable SyncTimeListener syncTimeListener) {
        this.timeListener = syncTimeListener;
    }

    @Override // io.objectbox.sync.SyncClient
    public void setSyncConnectionListener(@Nullable SyncConnectionListener syncConnectionListener) {
        this.connectionListener = syncConnectionListener;
    }

    @Override // io.objectbox.sync.SyncClient
    public void setSyncListener(@Nullable SyncListener syncListener) {
        this.loginListener = syncListener;
        this.completedListener = syncListener;
        this.timeListener = syncListener;
        this.connectionListener = syncListener;
        setSyncChangeListener(syncListener);
    }

    @Override // io.objectbox.sync.SyncClient
    public void setLoginCredentials(SyncCredentials syncCredentials) {
        SyncCredentialsToken syncCredentialsToken = (SyncCredentialsToken) syncCredentials;
        nativeSetLoginInfo(getHandle(), syncCredentialsToken.getTypeId(), syncCredentialsToken.getTokenBytes());
        syncCredentialsToken.clear();
    }

    @Override // io.objectbox.sync.SyncClient
    public boolean awaitFirstLogin(long j) {
        if (!this.started) {
            start();
        }
        return this.internalListener.awaitFirstLogin(j);
    }

    @Override // io.objectbox.sync.SyncClient
    public synchronized void start() {
        nativeStart(getHandle());
        this.started = true;
        if (this.connectivityMonitor != null) {
            this.connectivityMonitor.setObserver(this);
        }
    }

    @Override // io.objectbox.sync.SyncClient
    public boolean isStarted() {
        return this.started;
    }

    @Override // io.objectbox.sync.SyncClient
    public synchronized void stop() {
        if (this.connectivityMonitor != null) {
            this.connectivityMonitor.removeObserver();
        }
        nativeStop(getHandle());
        this.started = false;
    }

    @Override // io.objectbox.sync.SyncClient, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j;
        synchronized (this) {
            if (this.connectivityMonitor != null) {
                this.connectivityMonitor.removeObserver();
            }
            BoxStore boxStore = this.boxStore;
            if (boxStore != null) {
                if (boxStore.getSyncClient() == this) {
                    InternalAccess.setSyncClient(boxStore, null);
                }
                this.boxStore = null;
            }
            j = this.handle;
            this.handle = 0L;
        }
        if (j != 0) {
            nativeDelete(j);
        }
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override // io.objectbox.sync.SyncClient
    public boolean requestFullSync() {
        return nativeRequestFullSync(getHandle(), false);
    }

    public boolean requestFullSyncAndUpdates() {
        return nativeRequestFullSync(getHandle(), true);
    }

    @Override // io.objectbox.sync.SyncClient
    public boolean requestUpdates() {
        return nativeRequestUpdates(getHandle(), true);
    }

    @Override // io.objectbox.sync.SyncClient
    public boolean requestUpdatesOnce() {
        return nativeRequestUpdates(getHandle(), false);
    }

    @Override // io.objectbox.sync.SyncClient
    public boolean cancelUpdates() {
        return nativeCancelUpdates(getHandle());
    }

    @Override // io.objectbox.sync.SyncClient
    public void notifyConnectionAvailable() {
        nativeTriggerReconnect(getHandle());
    }

    @Override // io.objectbox.sync.SyncClient
    public ObjectsMessageBuilder startObjectsMessage(long j, @Nullable String str) {
        return new ObjectsMessageBuilderImpl(j, str);
    }

    private class InternalSyncClientListener {
        private final CountDownLatch firstLoginLatch;

        private InternalSyncClientListener() {
            this.firstLoginLatch = new CountDownLatch(1);
        }

        public void onLogin() {
            SyncClientImpl.this.lastLoginCode = 20L;
            this.firstLoginLatch.countDown();
            SyncLoginListener syncLoginListener = SyncClientImpl.this.loginListener;
            if (syncLoginListener != null) {
                syncLoginListener.onLoggedIn();
            }
        }

        public void onLoginFailure(long j) {
            SyncClientImpl.this.lastLoginCode = j;
            this.firstLoginLatch.countDown();
            SyncLoginListener syncLoginListener = SyncClientImpl.this.loginListener;
            if (syncLoginListener != null) {
                syncLoginListener.onLoginFailed(j);
            }
        }

        public void onSyncComplete() {
            SyncCompletedListener syncCompletedListener = SyncClientImpl.this.completedListener;
            if (syncCompletedListener != null) {
                syncCompletedListener.onUpdatesCompleted();
            }
        }

        public void onServerTimeUpdate(long j) {
            SyncTimeListener syncTimeListener = SyncClientImpl.this.timeListener;
            if (syncTimeListener != null) {
                syncTimeListener.onServerTimeUpdate(j);
            }
        }

        public void onDisconnect() {
            SyncConnectionListener syncConnectionListener = SyncClientImpl.this.connectionListener;
            if (syncConnectionListener != null) {
                syncConnectionListener.onDisconnected();
            }
        }

        boolean awaitFirstLogin(long j) {
            try {
                return this.firstLoginLatch.await(j, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                return false;
            }
        }
    }

    public static class ObjectsMessageBuilderImpl implements ObjectsMessageBuilder {
        private final long builderHandle;
        private boolean sent;
        private final SyncClientImpl syncClient;

        private ObjectsMessageBuilderImpl(SyncClientImpl syncClientImpl, long j, @Nullable String str) {
            this.syncClient = syncClientImpl;
            this.builderHandle = syncClientImpl.nativeObjectsMessageStart(j, str);
        }

        @Override // io.objectbox.sync.ObjectsMessageBuilder
        public ObjectsMessageBuilderImpl addString(long j, String str) {
            checkNotSent();
            this.syncClient.nativeObjectsMessageAddString(this.builderHandle, j, str);
            return this;
        }

        @Override // io.objectbox.sync.ObjectsMessageBuilder
        public ObjectsMessageBuilderImpl addBytes(long j, byte[] bArr, boolean z) {
            checkNotSent();
            this.syncClient.nativeObjectsMessageAddBytes(this.builderHandle, j, bArr, z);
            return this;
        }

        @Override // io.objectbox.sync.ObjectsMessageBuilder
        public boolean send() {
            if (!this.syncClient.isStarted()) {
                return false;
            }
            checkNotSent();
            this.sent = true;
            SyncClientImpl syncClientImpl = this.syncClient;
            return syncClientImpl.nativeObjectsMessageSend(syncClientImpl.getHandle(), this.builderHandle);
        }

        private void checkNotSent() {
            if (this.sent) {
                throw new IllegalStateException("Already sent this message, start a new one instead.");
            }
        }
    }
}
