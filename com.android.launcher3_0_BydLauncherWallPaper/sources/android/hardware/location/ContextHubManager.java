package android.hardware.location;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.content.Context;
import android.hardware.location.ContextHubTransaction;
import android.hardware.location.IContextHubCallback;
import android.hardware.location.IContextHubClientCallback;
import android.hardware.location.IContextHubService;
import android.hardware.location.IContextHubTransactionCallback;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class ContextHubManager {
    public static final int EVENT_HUB_RESET = 6;
    public static final int EVENT_NANOAPP_ABORTED = 4;
    public static final int EVENT_NANOAPP_DISABLED = 3;
    public static final int EVENT_NANOAPP_ENABLED = 2;
    public static final int EVENT_NANOAPP_LOADED = 0;
    public static final int EVENT_NANOAPP_MESSAGE = 5;
    public static final int EVENT_NANOAPP_UNLOADED = 1;
    public static final String EXTRA_CONTEXT_HUB_INFO = "android.hardware.location.extra.CONTEXT_HUB_INFO";
    public static final String EXTRA_EVENT_TYPE = "android.hardware.location.extra.EVENT_TYPE";
    public static final String EXTRA_MESSAGE = "android.hardware.location.extra.MESSAGE";
    public static final String EXTRA_NANOAPP_ABORT_CODE = "android.hardware.location.extra.NANOAPP_ABORT_CODE";
    public static final String EXTRA_NANOAPP_ID = "android.hardware.location.extra.NANOAPP_ID";
    private static final String TAG = "ContextHubManager";
    private Callback mCallback;
    private Handler mCallbackHandler;
    private final IContextHubCallback.Stub mClientCallback;

    @Deprecated
    private ICallback mLocalCallback;
    private final Looper mMainLooper;
    private final IContextHubService mService;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Event {
    }

    @Deprecated
    public interface ICallback {
        void onMessageReceipt(int i, int i2, ContextHubMessage contextHubMessage);
    }

    @Deprecated
    public static abstract class Callback {
        public abstract void onMessageReceipt(int i, int i2, ContextHubMessage contextHubMessage);

        protected Callback() {
        }
    }

    @Deprecated
    public int[] getContextHubHandles() {
        try {
            return this.mService.getContextHubHandles();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public ContextHubInfo getContextHubInfo(int i) {
        try {
            return this.mService.getContextHubInfo(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public int loadNanoApp(int i, NanoApp nanoApp) {
        try {
            return this.mService.loadNanoApp(i, nanoApp);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public int unloadNanoApp(int i) {
        try {
            return this.mService.unloadNanoApp(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public NanoAppInstanceInfo getNanoAppInstanceInfo(int i) {
        try {
            return this.mService.getNanoAppInstanceInfo(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public int[] findNanoAppOnHub(int i, NanoAppFilter nanoAppFilter) {
        try {
            return this.mService.findNanoAppOnHub(i, nanoAppFilter);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public int sendMessage(int i, int i2, ContextHubMessage contextHubMessage) {
        try {
            return this.mService.sendMessage(i, i2, contextHubMessage);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<ContextHubInfo> getContextHubs() {
        try {
            return this.mService.getContextHubs();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private IContextHubTransactionCallback createTransactionCallback(final ContextHubTransaction<Void> contextHubTransaction) {
        return new IContextHubTransactionCallback.Stub() { // from class: android.hardware.location.ContextHubManager.1
            @Override // android.hardware.location.IContextHubTransactionCallback
            public void onQueryResponse(int i, List<NanoAppState> list) {
                Log.e(ContextHubManager.TAG, "Received a query callback on a non-query request");
                contextHubTransaction.setResponse(new ContextHubTransaction.Response(7, null));
            }

            @Override // android.hardware.location.IContextHubTransactionCallback
            public void onTransactionComplete(int i) {
                contextHubTransaction.setResponse(new ContextHubTransaction.Response(i, null));
            }
        };
    }

    private IContextHubTransactionCallback createQueryCallback(final ContextHubTransaction<List<NanoAppState>> contextHubTransaction) {
        return new IContextHubTransactionCallback.Stub() { // from class: android.hardware.location.ContextHubManager.2
            @Override // android.hardware.location.IContextHubTransactionCallback
            public void onQueryResponse(int i, List<NanoAppState> list) {
                contextHubTransaction.setResponse(new ContextHubTransaction.Response(i, list));
            }

            @Override // android.hardware.location.IContextHubTransactionCallback
            public void onTransactionComplete(int i) {
                Log.e(ContextHubManager.TAG, "Received a non-query callback on a query request");
                contextHubTransaction.setResponse(new ContextHubTransaction.Response(7, null));
            }
        };
    }

    public ContextHubTransaction<Void> loadNanoApp(ContextHubInfo contextHubInfo, NanoAppBinary nanoAppBinary) {
        Objects.requireNonNull(contextHubInfo, "ContextHubInfo cannot be null");
        Objects.requireNonNull(nanoAppBinary, "NanoAppBinary cannot be null");
        ContextHubTransaction<Void> contextHubTransaction = new ContextHubTransaction<>(0);
        try {
            this.mService.loadNanoAppOnHub(contextHubInfo.getId(), createTransactionCallback(contextHubTransaction), nanoAppBinary);
            return contextHubTransaction;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ContextHubTransaction<Void> unloadNanoApp(ContextHubInfo contextHubInfo, long j) {
        Objects.requireNonNull(contextHubInfo, "ContextHubInfo cannot be null");
        ContextHubTransaction<Void> contextHubTransaction = new ContextHubTransaction<>(1);
        try {
            this.mService.unloadNanoAppFromHub(contextHubInfo.getId(), createTransactionCallback(contextHubTransaction), j);
            return contextHubTransaction;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ContextHubTransaction<Void> enableNanoApp(ContextHubInfo contextHubInfo, long j) {
        Objects.requireNonNull(contextHubInfo, "ContextHubInfo cannot be null");
        ContextHubTransaction<Void> contextHubTransaction = new ContextHubTransaction<>(2);
        try {
            this.mService.enableNanoApp(contextHubInfo.getId(), createTransactionCallback(contextHubTransaction), j);
            return contextHubTransaction;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ContextHubTransaction<Void> disableNanoApp(ContextHubInfo contextHubInfo, long j) {
        Objects.requireNonNull(contextHubInfo, "ContextHubInfo cannot be null");
        ContextHubTransaction<Void> contextHubTransaction = new ContextHubTransaction<>(3);
        try {
            this.mService.disableNanoApp(contextHubInfo.getId(), createTransactionCallback(contextHubTransaction), j);
            return contextHubTransaction;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ContextHubTransaction<List<NanoAppState>> queryNanoApps(ContextHubInfo contextHubInfo) {
        Objects.requireNonNull(contextHubInfo, "ContextHubInfo cannot be null");
        ContextHubTransaction<List<NanoAppState>> contextHubTransaction = new ContextHubTransaction<>(4);
        try {
            this.mService.queryNanoApps(contextHubInfo.getId(), createQueryCallback(contextHubTransaction));
            return contextHubTransaction;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public int registerCallback(Callback callback) {
        return registerCallback(callback, null);
    }

    @Deprecated
    public int registerCallback(ICallback iCallback) {
        if (this.mLocalCallback != null) {
            Log.w(TAG, "Max number of local callbacks reached!");
            return -1;
        }
        this.mLocalCallback = iCallback;
        return 0;
    }

    @Deprecated
    public int registerCallback(Callback callback, Handler handler) {
        synchronized (this) {
            if (this.mCallback != null) {
                Log.w(TAG, "Max number of callbacks reached!");
                return -1;
            }
            this.mCallback = callback;
            if (handler == null) {
                handler = new Handler(this.mMainLooper);
            }
            this.mCallbackHandler = handler;
            return 0;
        }
    }

    /* JADX INFO: renamed from: android.hardware.location.ContextHubManager$3, reason: invalid class name */
    class AnonymousClass3 extends IContextHubClientCallback.Stub {
        final /* synthetic */ ContextHubClientCallback val$callback;
        final /* synthetic */ ContextHubClient val$client;
        final /* synthetic */ Executor val$executor;

        AnonymousClass3(Executor executor, ContextHubClientCallback contextHubClientCallback, ContextHubClient contextHubClient) {
            this.val$executor = executor;
            this.val$callback = contextHubClientCallback;
            this.val$client = contextHubClient;
        }

        @Override // android.hardware.location.IContextHubClientCallback
        public void onMessageFromNanoApp(final NanoAppMessage nanoAppMessage) {
            Executor executor = this.val$executor;
            final ContextHubClientCallback contextHubClientCallback = this.val$callback;
            final ContextHubClient contextHubClient = this.val$client;
            executor.execute(new Runnable() { // from class: android.hardware.location.-$$Lambda$ContextHubManager$3$DxH6Sz99pyx6VSXEnOtr1-eAyc8
                @Override // java.lang.Runnable
                public final void run() {
                    contextHubClientCallback.onMessageFromNanoApp(contextHubClient, nanoAppMessage);
                }
            });
        }

        @Override // android.hardware.location.IContextHubClientCallback
        public void onHubReset() {
            Executor executor = this.val$executor;
            final ContextHubClientCallback contextHubClientCallback = this.val$callback;
            final ContextHubClient contextHubClient = this.val$client;
            executor.execute(new Runnable() { // from class: android.hardware.location.-$$Lambda$ContextHubManager$3$iIfAaiE5KeDJQGXE-LzQds4biBA
                @Override // java.lang.Runnable
                public final void run() {
                    contextHubClientCallback.onHubReset(contextHubClient);
                }
            });
        }

        @Override // android.hardware.location.IContextHubClientCallback
        public void onNanoAppAborted(final long j, final int i) {
            Executor executor = this.val$executor;
            final ContextHubClientCallback contextHubClientCallback = this.val$callback;
            final ContextHubClient contextHubClient = this.val$client;
            executor.execute(new Runnable() { // from class: android.hardware.location.-$$Lambda$ContextHubManager$3$p2UDkzdXL5QjM7lEp9eTbQaVrdg
                @Override // java.lang.Runnable
                public final void run() {
                    contextHubClientCallback.onNanoAppAborted(contextHubClient, j, i);
                }
            });
        }

        @Override // android.hardware.location.IContextHubClientCallback
        public void onNanoAppLoaded(final long j) {
            Executor executor = this.val$executor;
            final ContextHubClientCallback contextHubClientCallback = this.val$callback;
            final ContextHubClient contextHubClient = this.val$client;
            executor.execute(new Runnable() { // from class: android.hardware.location.-$$Lambda$ContextHubManager$3$2CiY8n79rSF1M7F9Y0w4vaLEdjg
                @Override // java.lang.Runnable
                public final void run() {
                    contextHubClientCallback.onNanoAppLoaded(contextHubClient, j);
                }
            });
        }

        @Override // android.hardware.location.IContextHubClientCallback
        public void onNanoAppUnloaded(final long j) {
            Executor executor = this.val$executor;
            final ContextHubClientCallback contextHubClientCallback = this.val$callback;
            final ContextHubClient contextHubClient = this.val$client;
            executor.execute(new Runnable() { // from class: android.hardware.location.-$$Lambda$ContextHubManager$3$rsYj8nrX6WsyIIrNcfHrIP_u8fI
                @Override // java.lang.Runnable
                public final void run() {
                    contextHubClientCallback.onNanoAppUnloaded(contextHubClient, j);
                }
            });
        }

        @Override // android.hardware.location.IContextHubClientCallback
        public void onNanoAppEnabled(final long j) {
            Executor executor = this.val$executor;
            final ContextHubClientCallback contextHubClientCallback = this.val$callback;
            final ContextHubClient contextHubClient = this.val$client;
            executor.execute(new Runnable() { // from class: android.hardware.location.-$$Lambda$ContextHubManager$3$jLN9nWoAuFZeWUJVD9rq--09rBI
                @Override // java.lang.Runnable
                public final void run() {
                    contextHubClientCallback.onNanoAppEnabled(contextHubClient, j);
                }
            });
        }

        @Override // android.hardware.location.IContextHubClientCallback
        public void onNanoAppDisabled(final long j) {
            Executor executor = this.val$executor;
            final ContextHubClientCallback contextHubClientCallback = this.val$callback;
            final ContextHubClient contextHubClient = this.val$client;
            executor.execute(new Runnable() { // from class: android.hardware.location.-$$Lambda$ContextHubManager$3$pPHmuWLhpL1BobPfHKNtgaczbI8
                @Override // java.lang.Runnable
                public final void run() {
                    contextHubClientCallback.onNanoAppDisabled(contextHubClient, j);
                }
            });
        }
    }

    private IContextHubClientCallback createClientCallback(ContextHubClient contextHubClient, ContextHubClientCallback contextHubClientCallback, Executor executor) {
        return new AnonymousClass3(executor, contextHubClientCallback, contextHubClient);
    }

    public ContextHubClient createClient(ContextHubInfo contextHubInfo, ContextHubClientCallback contextHubClientCallback, Executor executor) {
        Objects.requireNonNull(contextHubClientCallback, "Callback cannot be null");
        Objects.requireNonNull(contextHubInfo, "ContextHubInfo cannot be null");
        Objects.requireNonNull(executor, "Executor cannot be null");
        ContextHubClient contextHubClient = new ContextHubClient(contextHubInfo, false);
        try {
            contextHubClient.setClientProxy(this.mService.createClient(contextHubInfo.getId(), createClientCallback(contextHubClient, contextHubClientCallback, executor)));
            return contextHubClient;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public ContextHubClient createClient(ContextHubInfo contextHubInfo, ContextHubClientCallback contextHubClientCallback) {
        return createClient(contextHubInfo, contextHubClientCallback, new HandlerExecutor(Handler.getMain()));
    }

    public ContextHubClient createClient(ContextHubInfo contextHubInfo, PendingIntent pendingIntent, long j) {
        Objects.requireNonNull(pendingIntent);
        Objects.requireNonNull(contextHubInfo);
        ContextHubClient contextHubClient = new ContextHubClient(contextHubInfo, true);
        try {
            contextHubClient.setClientProxy(this.mService.createPendingIntentClient(contextHubInfo.getId(), pendingIntent, j));
            return contextHubClient;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public int unregisterCallback(Callback callback) {
        synchronized (this) {
            if (callback != this.mCallback) {
                Log.w(TAG, "Cannot recognize callback!");
                return -1;
            }
            this.mCallback = null;
            this.mCallbackHandler = null;
            return 0;
        }
    }

    @Deprecated
    public synchronized int unregisterCallback(ICallback iCallback) {
        if (iCallback != this.mLocalCallback) {
            Log.w(TAG, "Cannot recognize local callback!");
            return -1;
        }
        this.mLocalCallback = null;
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void invokeOnMessageReceiptCallback(int i, int i2, ContextHubMessage contextHubMessage) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onMessageReceipt(i, i2, contextHubMessage);
        }
    }

    /* JADX INFO: renamed from: android.hardware.location.ContextHubManager$4, reason: invalid class name */
    class AnonymousClass4 extends IContextHubCallback.Stub {
        AnonymousClass4() {
        }

        @Override // android.hardware.location.IContextHubCallback
        public void onMessageReceipt(final int i, final int i2, final ContextHubMessage contextHubMessage) {
            synchronized (ContextHubManager.this) {
                if (ContextHubManager.this.mCallback != null) {
                    ContextHubManager.this.mCallbackHandler.post(new Runnable() { // from class: android.hardware.location.-$$Lambda$ContextHubManager$4$wBwSvOP7GAeQGPPWXHOxU9oOtOg
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onMessageReceipt$0$ContextHubManager$4(i, i2, contextHubMessage);
                        }
                    });
                } else if (ContextHubManager.this.mLocalCallback != null) {
                    ContextHubManager.this.mLocalCallback.onMessageReceipt(i, i2, contextHubMessage);
                }
            }
        }

        public /* synthetic */ void lambda$onMessageReceipt$0$ContextHubManager$4(int i, int i2, ContextHubMessage contextHubMessage) {
            ContextHubManager.this.invokeOnMessageReceiptCallback(i, i2, contextHubMessage);
        }
    }

    public ContextHubManager(Context context, Looper looper) throws ServiceManager.ServiceNotFoundException {
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        this.mClientCallback = anonymousClass4;
        this.mMainLooper = looper;
        IContextHubService iContextHubServiceAsInterface = IContextHubService.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.CONTEXTHUB_SERVICE));
        this.mService = iContextHubServiceAsInterface;
        try {
            iContextHubServiceAsInterface.registerCallback(anonymousClass4);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
