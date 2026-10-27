package android.net;

import android.annotation.SystemApi;
import android.net.ISocketKeepaliveCallback;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import com.android.internal.util.FunctionalUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SocketKeepalive implements AutoCloseable {
    public static final int BINDER_DIED = -10;
    public static final int DATA_RECEIVED = -2;
    public static final int ERROR_HARDWARE_ERROR = -31;
    public static final int ERROR_HARDWARE_UNSUPPORTED = -30;
    public static final int ERROR_INSUFFICIENT_RESOURCES = -32;
    public static final int ERROR_INVALID_INTERVAL = -24;
    public static final int ERROR_INVALID_IP_ADDRESS = -21;
    public static final int ERROR_INVALID_LENGTH = -23;
    public static final int ERROR_INVALID_NETWORK = -20;
    public static final int ERROR_INVALID_PORT = -22;
    public static final int ERROR_INVALID_SOCKET = -25;
    public static final int ERROR_SOCKET_NOT_IDLE = -26;
    public static final int ERROR_UNSUPPORTED = -30;
    public static final int MAX_INTERVAL_SEC = 3600;
    public static final int MIN_INTERVAL_SEC = 10;
    public static final int NO_KEEPALIVE = -1;

    @SystemApi
    public static final int SUCCESS = 0;
    static final String TAG = "SocketKeepalive";
    final ISocketKeepaliveCallback mCallback;
    final Executor mExecutor;
    final Network mNetwork;
    final ParcelFileDescriptor mPfd;
    final IConnectivityManager mService;
    Integer mSlot;

    public static class Callback {
        public void onDataReceived() {
        }

        public void onError(int i) {
        }

        public void onStarted() {
        }

        public void onStopped() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KeepaliveEvent {
    }

    abstract void startImpl(int i);

    abstract void stopImpl();

    public static class ErrorCodeException extends Exception {
        public final int error;

        public ErrorCodeException(int i, Throwable th) {
            super(th);
            this.error = i;
        }

        public ErrorCodeException(int i) {
            this.error = i;
        }
    }

    public static class InvalidSocketException extends ErrorCodeException {
        public InvalidSocketException(int i, Throwable th) {
            super(i, th);
        }

        public InvalidSocketException(int i) {
            super(i);
        }
    }

    SocketKeepalive(IConnectivityManager iConnectivityManager, Network network, ParcelFileDescriptor parcelFileDescriptor, Executor executor, Callback callback) {
        this.mService = iConnectivityManager;
        this.mNetwork = network;
        this.mPfd = parcelFileDescriptor;
        this.mExecutor = executor;
        this.mCallback = new AnonymousClass1(callback, executor);
    }

    /* JADX INFO: renamed from: android.net.SocketKeepalive$1, reason: invalid class name */
    class AnonymousClass1 extends ISocketKeepaliveCallback.Stub {
        final /* synthetic */ Callback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass1(Callback callback, Executor executor) {
            this.val$callback = callback;
            this.val$executor = executor;
        }

        @Override // android.net.ISocketKeepaliveCallback
        public void onStarted(final int i) {
            final Callback callback = this.val$callback;
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$SocketKeepalive$1$m-VPtyb2YaC8aWd5gXQYgFGhVbM
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onStarted$1$SocketKeepalive$1(i, callback);
                }
            });
        }

        public /* synthetic */ void lambda$onStarted$1$SocketKeepalive$1(final int i, final Callback callback) throws Exception {
            SocketKeepalive.this.mExecutor.execute(new Runnable() { // from class: android.net.-$$Lambda$SocketKeepalive$1$nDWCSiqzvu6z8lptsLq-qY42hTk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStarted$0$SocketKeepalive$1(i, callback);
                }
            });
        }

        public /* synthetic */ void lambda$onStarted$0$SocketKeepalive$1(int i, Callback callback) {
            SocketKeepalive.this.mSlot = Integer.valueOf(i);
            callback.onStarted();
        }

        @Override // android.net.ISocketKeepaliveCallback
        public void onStopped() {
            final Executor executor = this.val$executor;
            final Callback callback = this.val$callback;
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$SocketKeepalive$1$GQbcC2yhPzv5xknkQV01K3_QTNA
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onStopped$3$SocketKeepalive$1(executor, callback);
                }
            });
        }

        public /* synthetic */ void lambda$onStopped$3$SocketKeepalive$1(Executor executor, final Callback callback) throws Exception {
            executor.execute(new Runnable() { // from class: android.net.-$$Lambda$SocketKeepalive$1$Ghy-awbQuJd8C-GZAjeZCXMiaUw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStopped$2$SocketKeepalive$1(callback);
                }
            });
        }

        public /* synthetic */ void lambda$onStopped$2$SocketKeepalive$1(Callback callback) {
            SocketKeepalive.this.mSlot = null;
            callback.onStopped();
        }

        @Override // android.net.ISocketKeepaliveCallback
        public void onError(final int i) {
            final Executor executor = this.val$executor;
            final Callback callback = this.val$callback;
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$SocketKeepalive$1$0jK7H49vYYFjBANIXTac00ocnSo
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onError$5$SocketKeepalive$1(executor, callback, i);
                }
            });
        }

        public /* synthetic */ void lambda$onError$5$SocketKeepalive$1(Executor executor, final Callback callback, final int i) throws Exception {
            executor.execute(new Runnable() { // from class: android.net.-$$Lambda$SocketKeepalive$1$xxwNi85oVXVQ_ILhrZNWwo4ppA8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onError$4$SocketKeepalive$1(callback, i);
                }
            });
        }

        public /* synthetic */ void lambda$onError$4$SocketKeepalive$1(Callback callback, int i) {
            SocketKeepalive.this.mSlot = null;
            callback.onError(i);
        }

        @Override // android.net.ISocketKeepaliveCallback
        public void onDataReceived() {
            final Executor executor = this.val$executor;
            final Callback callback = this.val$callback;
            Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: android.net.-$$Lambda$SocketKeepalive$1$nPQMIWzmX3WEJCjp1qnz_O7qaxs
                @Override // com.android.internal.util.FunctionalUtils.ThrowingRunnable
                public final void runOrThrow() throws Exception {
                    this.f$0.lambda$onDataReceived$7$SocketKeepalive$1(executor, callback);
                }
            });
        }

        public /* synthetic */ void lambda$onDataReceived$7$SocketKeepalive$1(Executor executor, final Callback callback) throws Exception {
            executor.execute(new Runnable() { // from class: android.net.-$$Lambda$SocketKeepalive$1$yVvEaumPDc_celEzvlSEH2FU0nc
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDataReceived$6$SocketKeepalive$1(callback);
                }
            });
        }

        public /* synthetic */ void lambda$onDataReceived$6$SocketKeepalive$1(Callback callback) {
            SocketKeepalive.this.mSlot = null;
            callback.onDataReceived();
        }
    }

    public final void start(int i) {
        startImpl(i);
    }

    public final void stop() {
        stopImpl();
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        stop();
        try {
            this.mPfd.close();
        } catch (IOException unused) {
        }
    }
}
