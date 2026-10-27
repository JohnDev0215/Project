package android.hardware.location;

import android.annotation.SystemApi;
import android.os.RemoteException;
import android.util.Log;
import dalvik.system.CloseGuard;
import java.io.Closeable;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class ContextHubClient implements Closeable {
    private static final String TAG = "ContextHubClient";
    private final ContextHubInfo mAttachedHub;
    private final CloseGuard mCloseGuard;
    private final boolean mPersistent;
    private IContextHubClient mClientProxy = null;
    private final AtomicBoolean mIsClosed = new AtomicBoolean(false);

    ContextHubClient(ContextHubInfo contextHubInfo, boolean z) {
        this.mAttachedHub = contextHubInfo;
        this.mPersistent = z;
        if (z) {
            this.mCloseGuard = null;
            return;
        }
        CloseGuard closeGuard = CloseGuard.get();
        this.mCloseGuard = closeGuard;
        closeGuard.open("close");
    }

    void setClientProxy(IContextHubClient iContextHubClient) {
        Objects.requireNonNull(iContextHubClient, "IContextHubClient cannot be null");
        if (this.mClientProxy != null) {
            throw new IllegalStateException("Cannot change client proxy multiple times");
        }
        this.mClientProxy = iContextHubClient;
    }

    public ContextHubInfo getAttachedHub() {
        return this.mAttachedHub;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.mIsClosed.getAndSet(true)) {
            return;
        }
        CloseGuard closeGuard = this.mCloseGuard;
        if (closeGuard != null) {
            closeGuard.close();
        }
        try {
            this.mClientProxy.close();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int sendMessageToNanoApp(NanoAppMessage nanoAppMessage) {
        Objects.requireNonNull(nanoAppMessage, "NanoAppMessage cannot be null");
        int maxPacketLengthBytes = this.mAttachedHub.getMaxPacketLengthBytes();
        byte[] messageBody = nanoAppMessage.getMessageBody();
        if (messageBody != null && messageBody.length > maxPacketLengthBytes) {
            Log.e(TAG, "Message (" + messageBody.length + " bytes) exceeds max payload length (" + maxPacketLengthBytes + " bytes)");
            return 2;
        }
        try {
            return this.mClientProxy.sendMessageToNanoApp(nanoAppMessage);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    protected void finalize() throws Throwable {
        try {
            CloseGuard closeGuard = this.mCloseGuard;
            if (closeGuard != null) {
                closeGuard.warnIfOpen();
            }
            if (!this.mPersistent) {
                close();
            }
        } finally {
            super.finalize();
        }
    }
}
