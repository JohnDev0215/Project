package com.ts.car.sdk.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.core.lib.common.ICoreSystemServer;
import com.core.lib.common.ICoreSystemServerListener;
import com.ts.car.common.listener.LocalListenerHelper;
import com.ts.car.common.listener.LocalMapListenerHelper;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import java.util.function.Consumer;
import ts.car.service.core.ICoreSystemService;
import ts.car.service.core.IServiceStateListener;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformCoreSystemService extends SimpleServiceBase implements ICoreSystemService {
    protected static final int MAX_WAIT_TIME = 20;
    protected static final int PACKAGE_EXIT_STATE_EXIT = 2;
    protected static final int PACKAGE_EXIT_STATE_NOTEXIT = 3;
    protected static final int PACKAGE_EXIT_STATE_UNKNOWN = 1;
    private static final String PACKAGE_NAME = "com.core.system.service";
    private static final String SERVICE_COMPONENT = "com.core.system.service/.CoreSystemServer";
    protected static final int WAIT_INTERNAL = 100;
    private BroadcastReceiver broadcastReceiver;
    protected ICoreSystemServerListener mCoreSystemServerListener;
    protected LocalListenerHelper<IServiceStateListener> mLocalListeners;
    protected LocalMapListenerHelper<String, IServiceStateListener> mLocalMapListenerHelper;
    protected ICoreSystemServer mService;
    private int shouldCheckPackageExit;
    private static final String TAG = PlatformCoreSystemService.class.getSimpleName();
    private static final String REMOTE_SERVICE_NAME = null;

    @PlatformConstruction
    public PlatformCoreSystemService(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, null);
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.ts.car.sdk.core.PlatformCoreSystemService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
            }
        };
        this.shouldCheckPackageExit = 1;
        this.mLocalListeners = new LocalListenerHelper<>();
        this.mLocalMapListenerHelper = new LocalMapListenerHelper<>();
        this.mCoreSystemServerListener = new CoreSystemServerListener();
        createServiceInner(20);
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-CoreService_1.0.0";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        Log.d(TAG, "onServiceConnected.");
        ICoreSystemServer iCoreSystemServerAsInterface = ICoreSystemServer.Stub.asInterface(iBinder);
        this.mService = iCoreSystemServerAsInterface;
        try {
            iCoreSystemServerAsInterface.addServiceListener(this.mCoreSystemServerListener);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        Log.d(TAG, "onServiceDied.");
        this.mService = null;
    }

    @Override // ts.car.service.core.ICoreSystemService
    public boolean isServiceSupport(String serviceName) {
        if (TextUtils.equals(serviceName, REMOTE_SERVICE_NAME)) {
            return false;
        }
        if (!createServiceInner(20)) {
            Logger.warning("can not get CoreSysterServer binder service");
            return false;
        }
        try {
            return this.mService.isServiceSupport(serviceName);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // ts.car.service.core.ICoreSystemService
    public boolean isServiceReady(String serviceName) {
        if (!createServiceInner(20)) {
            Logger.warning("can not get CoreSysterServer binder service");
            return false;
        }
        try {
            return this.mService.isServiceReady(serviceName);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override // com.ts.car.common.service.SimpleServiceBase, ts.car.service.IPlatformService
    public boolean isServiceConnected() {
        return this.mService != null;
    }

    @Override // ts.car.service.core.ICoreSystemService
    public IBinder getService(String serviceName) {
        if (!createServiceInner(20)) {
            Logger.warning("can not get CoreSysterServer binder service");
            return null;
        }
        try {
            return this.mService.getService(serviceName);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override // ts.car.service.core.ICoreSystemService
    public boolean registerServiceStateListener(IServiceStateListener listener) {
        return this.mLocalListeners.addListener(listener);
    }

    @Override // ts.car.service.core.ICoreSystemService
    public boolean unregisterServiceStateListener(IServiceStateListener listener) {
        return this.mLocalListeners.removeListener(listener);
    }

    @Override // ts.car.service.core.ICoreSystemService
    public boolean registerSingleServiceStateListener(String serviceName, IServiceStateListener listener) {
        if (serviceName == null || "".equals(serviceName.trim())) {
            Logger.warning("serviceName is empty");
            return false;
        }
        return this.mLocalMapListenerHelper.addListener(serviceName, listener);
    }

    @Override // ts.car.service.core.ICoreSystemService
    public boolean unregisterSingleServiceStateListener(String serviceName, IServiceStateListener listener) {
        if (serviceName == null || "".equals(serviceName.trim())) {
            Logger.warning("serviceName is empty");
            return false;
        }
        return this.mLocalMapListenerHelper.removeListener(serviceName, listener);
    }

    @Override // ts.car.service.core.ICoreSystemService
    public void checkAndReconnect() {
        Log.w(TAG, "checkAndReconnect: coresystemserver has been bind");
        createServiceInner(20);
    }

    class CoreSystemServerListener extends ICoreSystemServerListener.Stub {
        CoreSystemServerListener() {
        }

        @Override // com.core.lib.common.ICoreSystemServerListener
        public void onServiceStarted(final String name) {
            PlatformCoreSystemService.this.mLocalListeners.notifyListener(new Consumer() { // from class: com.ts.car.sdk.core.-$$Lambda$PlatformCoreSystemService$CoreSystemServerListener$XB5IKVPqsG4vPFpVFrouN1gVWEU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IServiceStateListener) obj).onServiceStarted(name);
                }
            });
            PlatformCoreSystemService.this.mLocalMapListenerHelper.notifyListener(name, new Consumer() { // from class: com.ts.car.sdk.core.-$$Lambda$PlatformCoreSystemService$CoreSystemServerListener$e4O7RCeQBeOGlU5P0jDSULZhCjY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IServiceStateListener) obj).onServiceStarted(name);
                }
            });
        }

        @Override // com.core.lib.common.ICoreSystemServerListener
        public void onServiceStopped(final String serviceName) {
            PlatformCoreSystemService.this.mLocalListeners.notifyListener(new Consumer() { // from class: com.ts.car.sdk.core.-$$Lambda$PlatformCoreSystemService$CoreSystemServerListener$QEz-emsbRVKqh8mkhLuDiYZDkKE
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IServiceStateListener) obj).onServiceStopped(serviceName);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0028 A[Catch: all -> 0x0088, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x0008, B:10:0x000e, B:11:0x0014, B:13:0x001c, B:16:0x0021, B:17:0x0028, B:19:0x002c, B:23:0x0035, B:25:0x004d, B:28:0x0064, B:30:0x006d, B:35:0x0079, B:38:0x0082, B:33:0x0072), top: B:46:0x0001, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002c A[Catch: all -> 0x0088, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x0008, B:10:0x000e, B:11:0x0014, B:13:0x001c, B:16:0x0021, B:17:0x0028, B:19:0x002c, B:23:0x0035, B:25:0x004d, B:28:0x0064, B:30:0x006d, B:35:0x0079, B:38:0x0082, B:33:0x0072), top: B:46:0x0001, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0033  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected synchronized boolean createServiceInner(int r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            com.core.lib.common.ICoreSystemServer r0 = r7.mService     // Catch: java.lang.Throwable -> L88
            r1 = 1
            if (r0 == 0) goto L8
            monitor-exit(r7)
            return r1
        L8:
            int r0 = r7.shouldCheckPackageExit     // Catch: java.lang.Throwable -> L88
            r2 = 3
            r3 = 0
            if (r1 != r0) goto L28
            android.content.Context r0 = r7.mContext     // Catch: java.lang.Throwable -> L88
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch: java.lang.Throwable -> L88
            java.lang.String r4 = "com.core.system.service"
            android.content.pm.PackageInfo r4 = r0.getPackageInfo(r4, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L20 java.lang.Throwable -> L88
            if (r4 == 0) goto L1f
            r5 = 2
            r7.shouldCheckPackageExit = r5     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L20 java.lang.Throwable -> L88
        L1f:
            goto L28
        L20:
            r4 = move-exception
            java.lang.String r5 = "CoreSystemServer app not exit"
            com.ts.car.sdk.core.Logger.debug(r5)     // Catch: java.lang.Throwable -> L88
            r7.shouldCheckPackageExit = r2     // Catch: java.lang.Throwable -> L88
        L28:
            int r0 = r7.shouldCheckPackageExit     // Catch: java.lang.Throwable -> L88
            if (r2 != r0) goto L33
            java.lang.String r0 = "CoreSystemServer app not exit"
            com.ts.car.sdk.core.Logger.debug(r0)     // Catch: java.lang.Throwable -> L88
            monitor-exit(r7)
            return r3
        L33:
            int r8 = r8 + (-1)
            android.content.Intent r0 = new android.content.Intent     // Catch: java.lang.Throwable -> L88
            r0.<init>()     // Catch: java.lang.Throwable -> L88
            java.lang.String r2 = "com.core.system.service/.CoreSystemServer"
            android.content.ComponentName r2 = android.content.ComponentName.unflattenFromString(r2)     // Catch: java.lang.Throwable -> L88
            r0.setComponent(r2)     // Catch: java.lang.Throwable -> L88
            android.content.BroadcastReceiver r4 = r7.broadcastReceiver     // Catch: java.lang.Throwable -> L88
            android.content.Context r5 = r7.mContext     // Catch: java.lang.Throwable -> L88
            android.os.IBinder r4 = r4.peekService(r5, r0)     // Catch: java.lang.Throwable -> L88
            if (r4 == 0) goto L64
            java.lang.String r3 = com.ts.car.sdk.core.PlatformCoreSystemService.TAG     // Catch: java.lang.Throwable -> L88
            java.lang.String r5 = "createServiceInner: get coressystemserver binder success"
            android.util.Log.w(r3, r5)     // Catch: java.lang.Throwable -> L88
            r7.onServiceConnected(r4)     // Catch: java.lang.Throwable -> L88
            android.content.ServiceConnection r3 = r7.mServiceConnection     // Catch: java.lang.Throwable -> L88
            java.lang.String r5 = "com.core.system.service/.CoreSystemServer"
            android.content.ComponentName r5 = android.content.ComponentName.unflattenFromString(r5)     // Catch: java.lang.Throwable -> L88
            r3.onServiceConnected(r5, r4)     // Catch: java.lang.Throwable -> L88
            monitor-exit(r7)
            return r1
        L64:
            java.lang.String r1 = com.ts.car.sdk.core.PlatformCoreSystemService.TAG     // Catch: java.lang.Throwable -> L88
            java.lang.String r5 = "CoreSystem get null binder!"
            android.util.Log.w(r1, r5)     // Catch: java.lang.Throwable -> L88
            r5 = 100
            java.lang.Thread.sleep(r5)     // Catch: java.lang.InterruptedException -> L71 java.lang.Throwable -> L88
            goto L77
        L71:
            r1 = move-exception
            java.lang.String r5 = com.ts.car.sdk.core.PlatformCoreSystemService.TAG     // Catch: java.lang.Throwable -> L88
            android.util.Log.w(r5, r1)     // Catch: java.lang.Throwable -> L88
        L77:
            if (r8 > 0) goto L82
            java.lang.String r1 = com.ts.car.sdk.core.PlatformCoreSystemService.TAG     // Catch: java.lang.Throwable -> L88
            java.lang.String r5 = "wait long enough to get CoreSystemServer binder but got null!!!!!!!"
            android.util.Log.e(r1, r5)     // Catch: java.lang.Throwable -> L88
            monitor-exit(r7)
            return r3
        L82:
            boolean r1 = r7.createServiceInner(r8)     // Catch: java.lang.Throwable -> L88
            monitor-exit(r7)
            return r1
        L88:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.car.sdk.core.PlatformCoreSystemService.createServiceInner(int):boolean");
    }
}
