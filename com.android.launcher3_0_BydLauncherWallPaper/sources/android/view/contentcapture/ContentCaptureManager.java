package android.view.contentcapture;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.ContentCaptureOptions;
import android.content.Context;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.util.Slog;
import android.view.WindowManager;
import android.view.contentcapture.IContentCaptureManager;
import android.view.contentcapture.IDataShareWriteAdapter;
import com.android.internal.util.Preconditions;
import com.android.internal.util.SyncResultReceiver;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public final class ContentCaptureManager {
    public static final int DATA_SHARE_ERROR_CONCURRENT_REQUEST = 2;
    public static final int DATA_SHARE_ERROR_TIMEOUT_INTERRUPTED = 3;
    public static final int DATA_SHARE_ERROR_UNKNOWN = 1;
    public static final int DEFAULT_IDLE_FLUSHING_FREQUENCY_MS = 5000;
    public static final int DEFAULT_LOG_HISTORY_SIZE = 10;
    public static final int DEFAULT_MAX_BUFFER_SIZE = 500;
    public static final int DEFAULT_TEXT_CHANGE_FLUSHING_FREQUENCY_MS = 1000;
    public static final String DEVICE_CONFIG_PROPERTY_IDLE_FLUSH_FREQUENCY = "idle_flush_frequency";
    public static final String DEVICE_CONFIG_PROPERTY_IDLE_UNBIND_TIMEOUT = "idle_unbind_timeout";
    public static final String DEVICE_CONFIG_PROPERTY_LOGGING_LEVEL = "logging_level";
    public static final String DEVICE_CONFIG_PROPERTY_LOG_HISTORY_SIZE = "log_history_size";
    public static final String DEVICE_CONFIG_PROPERTY_MAX_BUFFER_SIZE = "max_buffer_size";
    public static final String DEVICE_CONFIG_PROPERTY_SERVICE_EXPLICITLY_ENABLED = "service_explicitly_enabled";
    public static final String DEVICE_CONFIG_PROPERTY_TEXT_CHANGE_FLUSH_FREQUENCY = "text_change_flush_frequency";
    public static final int LOGGING_LEVEL_DEBUG = 1;
    public static final int LOGGING_LEVEL_OFF = 0;
    public static final int LOGGING_LEVEL_VERBOSE = 2;

    @SystemApi
    public static final int NO_SESSION_ID = 0;
    public static final int RESULT_CODE_FALSE = 2;
    public static final int RESULT_CODE_OK = 0;
    public static final int RESULT_CODE_SECURITY_EXCEPTION = -1;
    public static final int RESULT_CODE_TRUE = 1;
    private static final int SYNC_CALLS_TIMEOUT_MS = 5000;
    private static final String TAG = "ContentCaptureManager";
    private final Context mContext;
    private final LocalDataShareAdapterResourceManager mDataShareAdapterResourceManager;
    private int mFlags;
    private final Handler mHandler;
    private final Object mLock = new Object();
    private MainContentCaptureSession mMainSession;
    final ContentCaptureOptions mOptions;
    private final IContentCaptureManager mService;

    public interface ContentCaptureClient {
        ComponentName contentCaptureClientGetComponentName();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DataShareError {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LoggingLevel {
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface MyRunnable {
        void run(SyncResultReceiver syncResultReceiver) throws RemoteException;
    }

    public ContentCaptureManager(Context context, IContentCaptureManager iContentCaptureManager, ContentCaptureOptions contentCaptureOptions) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "context cannot be null");
        this.mService = (IContentCaptureManager) Preconditions.checkNotNull(iContentCaptureManager, "service cannot be null");
        ContentCaptureOptions contentCaptureOptions2 = (ContentCaptureOptions) Preconditions.checkNotNull(contentCaptureOptions, "options cannot be null");
        this.mOptions = contentCaptureOptions2;
        ContentCaptureHelper.setLoggingLevel(contentCaptureOptions2.loggingLevel);
        if (ContentCaptureHelper.sVerbose) {
            Log.v(TAG, "Constructor for " + context.getPackageName());
        }
        this.mHandler = Handler.createAsync(Looper.getMainLooper());
        this.mDataShareAdapterResourceManager = new LocalDataShareAdapterResourceManager();
    }

    public MainContentCaptureSession getMainContentCaptureSession() {
        MainContentCaptureSession mainContentCaptureSession;
        synchronized (this.mLock) {
            if (this.mMainSession == null) {
                this.mMainSession = new MainContentCaptureSession(this.mContext, this, this.mHandler, this.mService);
                if (ContentCaptureHelper.sVerbose) {
                    Log.v(TAG, "getMainContentCaptureSession(): created " + this.mMainSession);
                }
            }
            mainContentCaptureSession = this.mMainSession;
        }
        return mainContentCaptureSession;
    }

    public void onActivityCreated(IBinder iBinder, ComponentName componentName) {
        if (this.mOptions.lite) {
            return;
        }
        synchronized (this.mLock) {
            getMainContentCaptureSession().start(iBinder, componentName, this.mFlags);
        }
    }

    public void onActivityResumed() {
        if (this.mOptions.lite) {
            return;
        }
        getMainContentCaptureSession().notifySessionResumed();
    }

    public void onActivityPaused() {
        if (this.mOptions.lite) {
            return;
        }
        getMainContentCaptureSession().notifySessionPaused();
    }

    public void onActivityDestroyed() {
        if (this.mOptions.lite) {
            return;
        }
        getMainContentCaptureSession().destroy();
    }

    public void flush(int i) {
        if (this.mOptions.lite) {
            return;
        }
        getMainContentCaptureSession().flush(i);
    }

    public ComponentName getServiceComponentName() {
        if (!isContentCaptureEnabled() && !this.mOptions.lite) {
            return null;
        }
        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
        try {
            this.mService.getServiceComponentName(syncResultReceiver);
            return (ComponentName) syncResultReceiver.getParcelableResult();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get service componentName.");
        }
    }

    public static ComponentName getServiceSettingsComponentName() {
        IBinder iBinderCheckService = ServiceManager.checkService("content_capture");
        if (iBinderCheckService == null) {
            return null;
        }
        IContentCaptureManager iContentCaptureManagerAsInterface = IContentCaptureManager.Stub.asInterface(iBinderCheckService);
        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
        try {
            iContentCaptureManagerAsInterface.getServiceSettingsActivity(syncResultReceiver);
            if (syncResultReceiver.getIntResult() == -1) {
                throw new SecurityException(syncResultReceiver.getStringResult());
            }
            return (ComponentName) syncResultReceiver.getParcelableResult();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException e2) {
            Log.e(TAG, "Fail to get service settings componentName: " + e2);
            return null;
        }
    }

    public boolean isContentCaptureEnabled() {
        MainContentCaptureSession mainContentCaptureSession;
        if (this.mOptions.lite) {
            return false;
        }
        synchronized (this.mLock) {
            mainContentCaptureSession = this.mMainSession;
        }
        return mainContentCaptureSession == null || !mainContentCaptureSession.isDisabled();
    }

    public Set<ContentCaptureCondition> getContentCaptureConditions() {
        if (!isContentCaptureEnabled() && !this.mOptions.lite) {
            return null;
        }
        try {
            return ContentCaptureHelper.toSet(syncRun(new MyRunnable() { // from class: android.view.contentcapture.-$$Lambda$ContentCaptureManager$coYVEEmixmlD0uLbjOYtONlcjo4
                @Override // android.view.contentcapture.ContentCaptureManager.MyRunnable
                public final void run(SyncResultReceiver syncResultReceiver) throws RemoteException {
                    this.f$0.lambda$getContentCaptureConditions$0$ContentCaptureManager(syncResultReceiver);
                }
            }).getParcelableListResult());
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get content capture conditions.");
        }
    }

    public /* synthetic */ void lambda$getContentCaptureConditions$0$ContentCaptureManager(SyncResultReceiver syncResultReceiver) throws RemoteException {
        this.mService.getContentCaptureConditions(this.mContext.getPackageName(), syncResultReceiver);
    }

    public void setContentCaptureEnabled(boolean z) {
        MainContentCaptureSession mainContentCaptureSession;
        if (ContentCaptureHelper.sDebug) {
            Log.d(TAG, "setContentCaptureEnabled(): setting to " + z + " for " + this.mContext);
        }
        synchronized (this.mLock) {
            if (z) {
                this.mFlags &= -2;
            } else {
                this.mFlags |= 1;
            }
            mainContentCaptureSession = this.mMainSession;
        }
        if (mainContentCaptureSession != null) {
            mainContentCaptureSession.setDisabled(!z);
        }
    }

    public void updateWindowAttributes(WindowManager.LayoutParams layoutParams) {
        MainContentCaptureSession mainContentCaptureSession;
        if (ContentCaptureHelper.sDebug) {
            Log.d(TAG, "updateWindowAttributes(): window flags=" + layoutParams.flags);
        }
        boolean z = (layoutParams.flags & 8192) != 0;
        synchronized (this.mLock) {
            if (z) {
                this.mFlags |= 2;
            } else {
                this.mFlags &= -3;
            }
            mainContentCaptureSession = this.mMainSession;
        }
        if (mainContentCaptureSession != null) {
            mainContentCaptureSession.setDisabled(z);
        }
    }

    @SystemApi
    public boolean isContentCaptureFeatureEnabled() {
        try {
            int intResult = syncRun(new MyRunnable() { // from class: android.view.contentcapture.-$$Lambda$ContentCaptureManager$s5qALXiliWWE2mybFk1-QZ5N2NU
                @Override // android.view.contentcapture.ContentCaptureManager.MyRunnable
                public final void run(SyncResultReceiver syncResultReceiver) throws RemoteException {
                    this.f$0.lambda$isContentCaptureFeatureEnabled$1$ContentCaptureManager(syncResultReceiver);
                }
            }).getIntResult();
            if (intResult == 1) {
                return true;
            }
            if (intResult != 2) {
                Log.wtf(TAG, "received invalid result: " + intResult);
            }
            return false;
        } catch (SyncResultReceiver.TimeoutException e) {
            Log.e(TAG, "Fail to get content capture feature enable status: " + e);
            return false;
        }
    }

    public /* synthetic */ void lambda$isContentCaptureFeatureEnabled$1$ContentCaptureManager(SyncResultReceiver syncResultReceiver) throws RemoteException {
        this.mService.isContentCaptureFeatureEnabled(syncResultReceiver);
    }

    public void removeData(DataRemovalRequest dataRemovalRequest) {
        Preconditions.checkNotNull(dataRemovalRequest);
        try {
            this.mService.removeData(dataRemovalRequest);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void shareData(DataShareRequest dataShareRequest, Executor executor, DataShareWriteAdapter dataShareWriteAdapter) {
        Preconditions.checkNotNull(dataShareRequest);
        Preconditions.checkNotNull(dataShareWriteAdapter);
        Preconditions.checkNotNull(executor);
        try {
            this.mService.shareData(dataShareRequest, new DataShareAdapterDelegate(executor, dataShareWriteAdapter, this.mDataShareAdapterResourceManager));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private SyncResultReceiver syncRun(MyRunnable myRunnable) {
        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
        try {
            myRunnable.run(syncResultReceiver);
            if (syncResultReceiver.getIntResult() != -1) {
                return syncResultReceiver;
            }
            throw new SecurityException(syncResultReceiver.getStringResult());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get syn run result from SyncResultReceiver.");
        }
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.println("ContentCaptureManager");
        String str2 = str + "  ";
        synchronized (this.mLock) {
            printWriter.print(str2);
            printWriter.print("isContentCaptureEnabled(): ");
            printWriter.println(isContentCaptureEnabled());
            printWriter.print(str2);
            printWriter.print("Debug: ");
            printWriter.print(ContentCaptureHelper.sDebug);
            printWriter.print(" Verbose: ");
            printWriter.println(ContentCaptureHelper.sVerbose);
            printWriter.print(str2);
            printWriter.print("Context: ");
            printWriter.println(this.mContext);
            printWriter.print(str2);
            printWriter.print("User: ");
            printWriter.println(this.mContext.getUserId());
            printWriter.print(str2);
            printWriter.print("Service: ");
            printWriter.println(this.mService);
            printWriter.print(str2);
            printWriter.print("Flags: ");
            printWriter.println(this.mFlags);
            printWriter.print(str2);
            printWriter.print("Options: ");
            this.mOptions.dumpShort(printWriter);
            printWriter.println();
            if (this.mMainSession != null) {
                printWriter.print(str2);
                printWriter.println("Main session:");
                this.mMainSession.dump(str2 + "  ", printWriter);
            } else {
                printWriter.print(str2);
                printWriter.println("No sessions");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class DataShareAdapterDelegate extends IDataShareWriteAdapter.Stub {
        private final WeakReference<LocalDataShareAdapterResourceManager> mResourceManagerReference;

        private DataShareAdapterDelegate(Executor executor, DataShareWriteAdapter dataShareWriteAdapter, LocalDataShareAdapterResourceManager localDataShareAdapterResourceManager) {
            Preconditions.checkNotNull(executor);
            Preconditions.checkNotNull(dataShareWriteAdapter);
            Preconditions.checkNotNull(localDataShareAdapterResourceManager);
            localDataShareAdapterResourceManager.initializeForDelegate(this, dataShareWriteAdapter, executor);
            this.mResourceManagerReference = new WeakReference<>(localDataShareAdapterResourceManager);
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void write(final ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
            executeAdapterMethodLocked(new Consumer() { // from class: android.view.contentcapture.-$$Lambda$ContentCaptureManager$DataShareAdapterDelegate$yNQBvKveOXolJaLDyORsJ3Mf9Sw
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((DataShareWriteAdapter) obj).onWrite(parcelFileDescriptor);
                }
            }, "onWrite");
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void error(final int i) throws RemoteException {
            executeAdapterMethodLocked(new Consumer() { // from class: android.view.contentcapture.-$$Lambda$ContentCaptureManager$DataShareAdapterDelegate$jPhmewv_x6j6x2Spf4RcOc0zp2M
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((DataShareWriteAdapter) obj).onError(i);
                }
            }, "onError");
            clearHardReferences();
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void rejected() throws RemoteException {
            executeAdapterMethodLocked(new Consumer() { // from class: android.view.contentcapture.-$$Lambda$-sMTEX6ON78oZ6o7AJW1eLqWO2U
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((DataShareWriteAdapter) obj).onRejected();
                }
            }, "onRejected");
            clearHardReferences();
        }

        @Override // android.view.contentcapture.IDataShareWriteAdapter
        public void finish() throws RemoteException {
            clearHardReferences();
        }

        private void executeAdapterMethodLocked(final Consumer<DataShareWriteAdapter> consumer, String str) {
            LocalDataShareAdapterResourceManager localDataShareAdapterResourceManager = this.mResourceManagerReference.get();
            if (localDataShareAdapterResourceManager == null) {
                Slog.w(ContentCaptureManager.TAG, "Can't execute " + str + "(), resource manager has been GC'ed");
                return;
            }
            final DataShareWriteAdapter adapter = localDataShareAdapterResourceManager.getAdapter(this);
            Executor executor = localDataShareAdapterResourceManager.getExecutor(this);
            if (adapter == null || executor == null) {
                Slog.w(ContentCaptureManager.TAG, "Can't execute " + str + "(), references are null");
                return;
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                executor.execute(new Runnable() { // from class: android.view.contentcapture.-$$Lambda$ContentCaptureManager$DataShareAdapterDelegate$4RFRIiC_ju3lUZM4X0n9bSD7O8I
                    @Override // java.lang.Runnable
                    public final void run() {
                        consumer.accept(adapter);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        private void clearHardReferences() {
            LocalDataShareAdapterResourceManager localDataShareAdapterResourceManager = this.mResourceManagerReference.get();
            if (localDataShareAdapterResourceManager == null) {
                Slog.w(ContentCaptureManager.TAG, "Can't clear references, resource manager has been GC'ed");
            } else {
                localDataShareAdapterResourceManager.clearHardReferences(this);
            }
        }
    }

    private static class LocalDataShareAdapterResourceManager {
        private Map<DataShareAdapterDelegate, Executor> mExecutorHardReferences;
        private Map<DataShareAdapterDelegate, DataShareWriteAdapter> mWriteAdapterHardReferences;

        private LocalDataShareAdapterResourceManager() {
            this.mWriteAdapterHardReferences = new HashMap();
            this.mExecutorHardReferences = new HashMap();
        }

        void initializeForDelegate(DataShareAdapterDelegate dataShareAdapterDelegate, DataShareWriteAdapter dataShareWriteAdapter, Executor executor) {
            this.mWriteAdapterHardReferences.put(dataShareAdapterDelegate, dataShareWriteAdapter);
            this.mExecutorHardReferences.put(dataShareAdapterDelegate, executor);
        }

        Executor getExecutor(DataShareAdapterDelegate dataShareAdapterDelegate) {
            return this.mExecutorHardReferences.get(dataShareAdapterDelegate);
        }

        DataShareWriteAdapter getAdapter(DataShareAdapterDelegate dataShareAdapterDelegate) {
            return this.mWriteAdapterHardReferences.get(dataShareAdapterDelegate);
        }

        void clearHardReferences(DataShareAdapterDelegate dataShareAdapterDelegate) {
            this.mWriteAdapterHardReferences.remove(dataShareAdapterDelegate);
            this.mExecutorHardReferences.remove(dataShareAdapterDelegate);
        }
    }
}
