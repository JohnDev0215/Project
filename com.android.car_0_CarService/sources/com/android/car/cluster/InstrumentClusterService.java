package com.android.car.cluster;

import android.annotation.SystemApi;
import android.app.ActivityOptions;
import android.car.cluster.IInstrumentClusterManagerCallback;
import android.car.cluster.IInstrumentClusterManagerService;
import android.car.cluster.renderer.IInstrumentCluster;
import android.car.cluster.renderer.IInstrumentClusterHelper;
import android.car.cluster.renderer.IInstrumentClusterNavigation;
import android.car.encryptionrunner.DummyEncryptionRunner;
import android.car.navigation.CarNavigationInstrumentCluster;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import com.android.car.AppFocusService;
import com.android.car.CarInputService;
import com.android.car.CarLocalServices;
import com.android.car.CarServiceBase;
import com.android.car.ICarImpl;
import com.android.car.Manifest;
import com.android.car.R;
import com.android.car.am.FixedActivityService;
import com.android.car.user.CarUserService;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public class InstrumentClusterService implements CarServiceBase, AppFocusService.FocusOwnershipCallback, CarInputService.KeyEventListener {
    private static final ContextOwner NO_OWNER = new ContextOwner(0, 0);
    private static final long RENDERER_SERVICE_WAIT_TIMEOUT_MS = 5000;
    private static final long RENDERER_WAIT_MAX_RETRY = 2;
    private static final String TAG = "CAR.CLUSTER";
    private final AppFocusService mAppFocusService;
    private final CarInputService mCarInputService;

    @Deprecated
    private final ClusterManagerService mClusterManagerService;
    private final Context mContext;
    private final DeferredRebinder mDeferredRebinder;
    private IInstrumentClusterNavigation mIInstrumentClusterNavigationFromRenderer;
    private IInstrumentCluster mRendererService;
    private final String mRenderingServiceConfig;
    private final Object mLock = new Object();
    private ContextOwner mNavContextOwner = NO_OWNER;
    private boolean mRendererBound = false;
    private final IInstrumentClusterNavigation mIInstrumentClusterNavigationnWrapper = new IInstrumentClusterNavigation.Stub() { // from class: com.android.car.cluster.InstrumentClusterService.1
        public void onNavigationStateChanged(Bundle bundle) {
            ICarImpl.assertPermission(InstrumentClusterService.this.mContext, Manifest.permission.CAR_NAVIGATION_MANAGER);
            InstrumentClusterService.this.assertNavigationFocus();
            IInstrumentClusterNavigation navigationBinder = InstrumentClusterService.this.getNavigationBinder(false);
            if (navigationBinder == null) {
                Log.e("CAR.CLUSTER", "onNavigationStateChanged failed, renderer not ready, Bundle:" + bundle);
                return;
            }
            try {
                navigationBinder.onNavigationStateChanged(bundle);
            } catch (RemoteException e) {
                Log.e("CAR.CLUSTER", "onNavigationStateChanged failed, bundle:" + bundle, e);
            }
        }

        public CarNavigationInstrumentCluster getInstrumentClusterInfo() {
            ICarImpl.assertPermission(InstrumentClusterService.this.mContext, Manifest.permission.CAR_NAVIGATION_MANAGER);
            for (int i = 0; i < 2; i++) {
                IInstrumentClusterNavigation navigationBinder = InstrumentClusterService.this.getNavigationBinder(true);
                if (navigationBinder != null) {
                    try {
                        return navigationBinder.getInstrumentClusterInfo();
                    } catch (RemoteException e) {
                        Log.e("CAR.CLUSTER", "getInstrumentClusterInfo failed", e);
                    }
                }
            }
            throw new IllegalStateException("cannot access renderer service");
        }
    };
    final ServiceConnection mRendererServiceConnection = new ServiceConnection() { // from class: com.android.car.cluster.InstrumentClusterService.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder binder) {
            ContextOwner navContextOwner;
            if (Log.isLoggable("CAR.CLUSTER", 3)) {
                Log.d("CAR.CLUSTER", "onServiceConnected, name: " + name + ", binder: " + binder);
            }
            IInstrumentCluster service = IInstrumentCluster.Stub.asInterface(binder);
            synchronized (InstrumentClusterService.this.mLock) {
                InstrumentClusterService.this.mRendererService = service;
                navContextOwner = InstrumentClusterService.this.mNavContextOwner;
                InstrumentClusterService.this.mLock.notifyAll();
            }
            if (navContextOwner != null && service != null) {
                InstrumentClusterService.notifyNavContextOwnerChanged(service, navContextOwner);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            if (Log.isLoggable("CAR.CLUSTER", 3)) {
                Log.d("CAR.CLUSTER", "onServiceDisconnected, name: " + name);
            }
            InstrumentClusterService.this.mContext.unbindService(this);
            synchronized (InstrumentClusterService.this.mLock) {
                InstrumentClusterService.this.mRendererBound = false;
                InstrumentClusterService.this.mRendererService = null;
                InstrumentClusterService.this.mIInstrumentClusterNavigationFromRenderer = null;
            }
            InstrumentClusterService.this.mDeferredRebinder.rebind();
        }
    };
    private final IInstrumentClusterHelper mInstrumentClusterHelper = new IInstrumentClusterHelper.Stub() { // from class: com.android.car.cluster.InstrumentClusterService.3
        public boolean startFixedActivityModeForDisplayAndUser(Intent intent, Bundle activityOptionsBundle, int userId) {
            Binder.clearCallingIdentity();
            ActivityOptions options = new ActivityOptions(activityOptionsBundle);
            FixedActivityService service = (FixedActivityService) CarLocalServices.getService(FixedActivityService.class);
            return service.startFixedActivityModeForDisplayAndUser(intent, options, options.getLaunchDisplayId(), userId);
        }

        public void stopFixedActivityMode(int displayId) {
            Binder.clearCallingIdentity();
            FixedActivityService service = (FixedActivityService) CarLocalServices.getService(FixedActivityService.class);
            service.stopFixedActivityMode(displayId);
        }
    };

    public InstrumentClusterService(Context context, AppFocusService appFocusService, CarInputService carInputService) {
        this.mClusterManagerService = new ClusterManagerService();
        this.mContext = context;
        this.mAppFocusService = appFocusService;
        this.mCarInputService = carInputService;
        this.mRenderingServiceConfig = context.getString(R.string.instrumentClusterRendererService);
        this.mDeferredRebinder = new DeferredRebinder();
    }

    private IInstrumentCluster waitForRendererLocked() {
        if (this.mRendererService == null) {
            try {
                this.mLock.wait(RENDERER_SERVICE_WAIT_TIMEOUT_MS);
            } catch (InterruptedException e) {
                Log.d("CAR.CLUSTER", "waitForRenderer, interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        return this.mRendererService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IInstrumentClusterNavigation getNavigationBinder(boolean retryOnFail) {
        IInstrumentClusterNavigation navigationBinder;
        synchronized (this.mLock) {
            if (this.mIInstrumentClusterNavigationFromRenderer != null) {
                return this.mIInstrumentClusterNavigationFromRenderer;
            }
            IInstrumentCluster renderer = waitForRendererLocked();
            int i = 0;
            while (true) {
                if (i >= 2) {
                    navigationBinder = null;
                    break;
                }
                if (renderer == null) {
                    synchronized (this.mLock) {
                        renderer = waitForRendererLocked();
                    }
                }
                try {
                    IInstrumentClusterNavigation navigationBinder2 = renderer.getNavigationService();
                    navigationBinder = navigationBinder2;
                    break;
                } catch (RemoteException e) {
                    Log.e("CAR.CLUSTER", "RemoteException from renderer", e);
                    renderer = null;
                    i++;
                }
            }
            if (navigationBinder == null) {
                return navigationBinder;
            }
            synchronized (this.mLock) {
                this.mIInstrumentClusterNavigationFromRenderer = navigationBinder;
            }
            return navigationBinder;
        }
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        if (Log.isLoggable("CAR.CLUSTER", 3)) {
            Log.d("CAR.CLUSTER", DummyEncryptionRunner.INIT);
        }
        this.mAppFocusService.registerContextOwnerChangedCallback(this);
        this.mCarInputService.setInstrumentClusterKeyListener(this);
        if (!isRendererServiceEnabled()) {
            synchronized (this.mLock) {
                this.mRendererBound = false;
            }
            return;
        }
        ((CarUserService) CarLocalServices.getService(CarUserService.class)).runOnUser0Unlock(new Runnable() { // from class: com.android.car.cluster.-$$Lambda$InstrumentClusterService$Nko0QYkm6E_U9NrbyWroocy7oAE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$init$0$InstrumentClusterService();
            }
        });
    }

    public /* synthetic */ void lambda$init$0$InstrumentClusterService() {
        boolean bound = bindInstrumentClusterRendererService();
        synchronized (this.mLock) {
            this.mRendererBound = bound;
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        if (Log.isLoggable("CAR.CLUSTER", 3)) {
            Log.d("CAR.CLUSTER", "release");
        }
        this.mAppFocusService.unregisterContextOwnerChangedCallback(this);
        synchronized (this.mLock) {
            if (this.mRendererBound) {
                this.mContext.unbindService(this.mRendererServiceConnection);
                this.mRendererBound = false;
            }
        }
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("**" + getClass().getSimpleName() + "**");
        synchronized (this.mLock) {
            writer.println("bound with renderer: " + this.mRendererBound);
            writer.println("renderer service: " + this.mRendererService);
            writer.println("context owner: " + this.mNavContextOwner);
            writer.println("mRenderingServiceConfig:" + this.mRenderingServiceConfig);
            writer.println("mIInstrumentClusterNavigationFromRenderer:" + this.mIInstrumentClusterNavigationFromRenderer);
        }
    }

    @Override // com.android.car.AppFocusService.FocusOwnershipCallback
    public void onFocusAcquired(int appType, int uid, int pid) {
        changeNavContextOwner(appType, uid, pid, true);
    }

    @Override // com.android.car.AppFocusService.FocusOwnershipCallback
    public void onFocusAbandoned(int appType, int uid, int pid) {
        changeNavContextOwner(appType, uid, pid, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assertNavigationFocus() {
        int uid = Binder.getCallingUid();
        int pid = Binder.getCallingPid();
        synchronized (this.mLock) {
            if (uid == this.mNavContextOwner.uid && pid == this.mNavContextOwner.pid) {
                return;
            }
            AppFocusService afs = (AppFocusService) CarLocalServices.getService(AppFocusService.class);
            if (afs != null && afs.isFocusOwner(uid, pid, 1)) {
            } else {
                throw new IllegalStateException("Client not owning APP_FOCUS_TYPE_NAVIGATION");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void changeNavContextOwner(int r7, int r8, int r9, boolean r10) {
        /*
            r6 = this;
            r0 = 1
            if (r7 == r0) goto L4
            return
        L4:
            com.android.car.cluster.InstrumentClusterService$ContextOwner r0 = new com.android.car.cluster.InstrumentClusterService$ContextOwner
            r0.<init>(r8, r9)
            if (r10 == 0) goto Ld
            r1 = r0
            goto Lf
        Ld:
            com.android.car.cluster.InstrumentClusterService$ContextOwner r1 = com.android.car.cluster.InstrumentClusterService.NO_OWNER
        Lf:
            java.lang.Object r2 = r6.mLock
            monitor-enter(r2)
            if (r10 == 0) goto L1f
            com.android.car.cluster.InstrumentClusterService$ContextOwner r3 = r6.mNavContextOwner     // Catch: java.lang.Throwable -> L1d
            boolean r3 = java.util.Objects.equals(r3, r0)     // Catch: java.lang.Throwable -> L1d
            if (r3 != 0) goto L29
            goto L1f
        L1d:
            r3 = move-exception
            goto L63
        L1f:
            if (r10 != 0) goto L58
            com.android.car.cluster.InstrumentClusterService$ContextOwner r3 = r6.mNavContextOwner     // Catch: java.lang.Throwable -> L1d
            boolean r3 = java.util.Objects.equals(r3, r0)     // Catch: java.lang.Throwable -> L1d
            if (r3 != 0) goto L58
        L29:
            java.lang.String r3 = "CAR.CLUSTER"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1d
            r4.<init>()     // Catch: java.lang.Throwable -> L1d
            java.lang.String r5 = "Invalid nav context owner change (acquiring: "
            r4.append(r5)     // Catch: java.lang.Throwable -> L1d
            r4.append(r10)     // Catch: java.lang.Throwable -> L1d
            java.lang.String r5 = "), current owner: ["
            r4.append(r5)     // Catch: java.lang.Throwable -> L1d
            com.android.car.cluster.InstrumentClusterService$ContextOwner r5 = r6.mNavContextOwner     // Catch: java.lang.Throwable -> L1d
            r4.append(r5)     // Catch: java.lang.Throwable -> L1d
            java.lang.String r5 = "], requester: ["
            r4.append(r5)     // Catch: java.lang.Throwable -> L1d
            r4.append(r0)     // Catch: java.lang.Throwable -> L1d
            java.lang.String r5 = "]"
            r4.append(r5)     // Catch: java.lang.Throwable -> L1d
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L1d
            android.util.Log.w(r3, r4)     // Catch: java.lang.Throwable -> L1d
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1d
            return
        L58:
            r6.mNavContextOwner = r1     // Catch: java.lang.Throwable -> L1d
            android.car.cluster.renderer.IInstrumentCluster r3 = r6.mRendererService     // Catch: java.lang.Throwable -> L1d
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1d
            if (r3 == 0) goto L62
            notifyNavContextOwnerChanged(r3, r1)
        L62:
            return
        L63:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1d
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.cluster.InstrumentClusterService.changeNavContextOwner(int, int, int, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifyNavContextOwnerChanged(IInstrumentCluster service, ContextOwner owner) {
        try {
            service.setNavigationContextOwner(owner.uid, owner.pid);
        } catch (RemoteException e) {
            Log.e("CAR.CLUSTER", "Failed to call setNavigationContextOwner", e);
        }
    }

    private boolean isRendererServiceEnabled() {
        if (TextUtils.isEmpty(this.mRenderingServiceConfig)) {
            Log.d("CAR.CLUSTER", "Instrument cluster renderer was not configured");
            return false;
        }
        boolean explicitlyDisabled = "true".equals(Settings.Global.getString(this.mContext.getContentResolver(), "android.car.DISABLE_INSTRUMENTATION_SERVICE"));
        if (explicitlyDisabled) {
            Log.i("CAR.CLUSTER", "Instrument cluster renderer explicitly disabled by settings");
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean bindInstrumentClusterRendererService() {
        if (!isRendererServiceEnabled()) {
            return false;
        }
        Log.d("CAR.CLUSTER", "bindInstrumentClusterRendererService, component: " + this.mRenderingServiceConfig);
        Intent intent = new Intent();
        intent.setComponent(ComponentName.unflattenFromString(this.mRenderingServiceConfig));
        Bundle bundle = new Bundle();
        bundle.putBinder("android.car.cluster.renderer.IInstrumentClusterHelper", this.mInstrumentClusterHelper.asBinder());
        intent.putExtra("android.car.cluster.renderer.IInstrumentClusterHelper", bundle);
        return this.mContext.bindServiceAsUser(intent, this.mRendererServiceConnection, 65, UserHandle.SYSTEM);
    }

    public IInstrumentClusterNavigation getNavigationService() {
        if (!isRendererServiceEnabled()) {
            return null;
        }
        return this.mIInstrumentClusterNavigationnWrapper;
    }

    @Deprecated
    public IInstrumentClusterManagerService.Stub getManagerService() {
        return this.mClusterManagerService;
    }

    @Override // com.android.car.CarInputService.KeyEventListener
    public void onKeyEvent(KeyEvent event) {
        if (Log.isLoggable("CAR.CLUSTER", 3)) {
            Log.d("CAR.CLUSTER", "InstrumentClusterService#onKeyEvent: " + event);
        }
        IInstrumentCluster service = getInstrumentClusterRendererService();
        if (service != null) {
            try {
                service.onKeyEvent(event);
            } catch (RemoteException e) {
                Log.e("CAR.CLUSTER", "onKeyEvent", e);
            }
        }
    }

    private IInstrumentCluster getInstrumentClusterRendererService() {
        IInstrumentCluster iInstrumentCluster;
        synchronized (this.mLock) {
            iInstrumentCluster = this.mRendererService;
        }
        return iInstrumentCluster;
    }

    private static class ContextOwner {
        final int pid;
        final int uid;

        ContextOwner(int uid, int pid) {
            this.uid = uid;
            this.pid = pid;
        }

        public String toString() {
            return "uid: " + this.uid + ", pid: " + this.pid;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ContextOwner that = (ContextOwner) o;
            if (this.uid == that.uid && this.pid == that.pid) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.uid), Integer.valueOf(this.pid));
        }
    }

    @Deprecated
    private class ClusterManagerService extends IInstrumentClusterManagerService.Stub {
        private ClusterManagerService() {
        }

        public void startClusterActivity(Intent intent) throws RemoteException {
        }

        public void registerCallback(IInstrumentClusterManagerCallback callback) throws RemoteException {
        }

        public void unregisterCallback(IInstrumentClusterManagerCallback callback) throws RemoteException {
        }
    }

    private static final class DeferredRebinder extends Handler {
        private static final long NEXT_REBIND_ATTEMPT_DELAY_MS = 1000;
        private static final int NUMBER_OF_ATTEMPTS = 10;
        private static final String TAG = DeferredRebinder.class.getSimpleName();
        private final WeakReference<InstrumentClusterService> mService;

        private DeferredRebinder(InstrumentClusterService service) {
            this.mService = new WeakReference<>(service);
        }

        public void rebind() {
            InstrumentClusterService service = this.mService.get();
            if (service != null) {
                service.mRendererBound = service.bindInstrumentClusterRendererService();
                if (!service.mRendererBound) {
                    removeMessages(0);
                    sendMessageDelayed(obtainMessage(0, 10, 0), NEXT_REBIND_ATTEMPT_DELAY_MS);
                    return;
                }
                return;
            }
            Log.i(TAG, "rebind null service");
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            InstrumentClusterService service = this.mService.get();
            if (service != null) {
                service.mRendererBound = service.bindInstrumentClusterRendererService();
                if (service.mRendererBound) {
                    Log.w(TAG, "Failed to bound to render service, next attempt in 1000ms.");
                    int attempts = msg.arg1 - 1;
                    if (attempts >= 0) {
                        sendMessageDelayed(obtainMessage(0, attempts, 0), NEXT_REBIND_ATTEMPT_DELAY_MS);
                        return;
                    } else {
                        Log.wtf(TAG, "Failed to rebind with cluster rendering service");
                        return;
                    }
                }
                return;
            }
            Log.i(TAG, "handleMessage null service");
        }
    }
}
