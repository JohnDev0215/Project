package com.core.system.foundationinterface;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.core.lib.common.ICoreSystemServer;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.FoundationComponent;
import com.ts.car.core.annotation.FoundationConstruction;

/* JADX INFO: loaded from: classes.dex */
@FoundationComponent
public class PlatformCoreAdminService extends SimpleServiceBase implements ICoreAdminService {
    protected static final int MAX_WAIT_TIME = 20;
    protected static final int PACKAGE_EXIT_STATE_EXIT = 2;
    protected static final int PACKAGE_EXIT_STATE_NOTEXIT = 3;
    protected static final int PACKAGE_EXIT_STATE_UNKNOWN = 1;
    private static final String PACKAGE_NAME = "com.core.system.service";
    private static final String SERVICE_COMPONENT = "com.core.system.service/.CoreSystemServer";
    protected static final int WAIT_INTERNAL = 100;
    private BroadcastReceiver broadcastReceiver;
    protected ICoreSystemServer mService;
    private int shouldCheckPackageExit;
    private static final String TAG = PlatformCoreAdminService.class.getSimpleName();
    private static final String REMOTE_SERVICE_NAME = null;

    @FoundationConstruction
    public PlatformCoreAdminService(Context context) {
        this(context, null);
    }

    @FoundationConstruction
    public PlatformCoreAdminService(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, (IBinder) null);
        this.broadcastReceiver = new BroadcastReceiver() { // from class: com.core.system.foundationinterface.PlatformCoreAdminService.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
            }
        };
        this.shouldCheckPackageExit = 1;
        createServiceInner(MAX_WAIT_TIME);
    }

    @Override // com.core.system.foundationinterface.ICoreAdminService
    public boolean addService(ICoreService coreService) {
        if (coreService == null) {
            Log.w(TAG, "addService error, coreService param is null!!");
            return false;
        }
        if (TextUtils.isEmpty(coreService.serviceName()) || coreService.getServiceBinder() == null) {
            Log.w(TAG, "addService error, coreService serviceName or ServiceBinder is null!!");
            return false;
        }
        if (!createServiceInner(MAX_WAIT_TIME)) {
            Log.w(TAG, "addService error, can not get coreService binder, check coreSystemService state");
            return false;
        }
        try {
            return this.mService.addService(coreService.serviceName(), coreService.getServiceBinder());
        } catch (RemoteException ex) {
            Log.w(TAG, "addService error!", ex);
            return false;
        }
    }

    @Override // com.core.system.foundationinterface.ICoreAdminService
    public boolean notifyServiceReady(ICoreService coreService) {
        if (coreService == null) {
            Log.w(TAG, "notifyServiceReady error, coreService param is null!!");
            return false;
        }
        if (TextUtils.isEmpty(coreService.serviceName()) || coreService.getServiceBinder() == null) {
            Log.w(TAG, "notifyServiceReady error, coreService serviceName or ServiceBinder is null!!");
            return false;
        }
        if (!createServiceInner(MAX_WAIT_TIME)) {
            Log.w(TAG, "notifyServiceReady error, can not get coreService binder, check coreSystemService state");
            return false;
        }
        try {
            return this.mService.notifyServiceReady(coreService.serviceName(), coreService.getServiceBinder());
        } catch (RemoteException ex) {
            Log.w(TAG, "notifyServiceReady error!", ex);
            return false;
        }
    }

    public String serviceName() {
        return "TS-CoreAdminService_1.0.0";
    }

    protected void onServiceConnected(IBinder iBinder) {
        Log.d(TAG, "onServiceConnected.");
        this.mService = ICoreSystemServer.Stub.asInterface(iBinder);
    }

    protected void onServiceDied() {
        Log.d(TAG, "onServiceDied.");
        this.mService = null;
    }

    public boolean isServiceConnected() {
        return this.mService != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002a A[Catch: all -> 0x007a, TryCatch #2 {, blocks: (B:3:0x0001, B:8:0x0008, B:10:0x000e, B:11:0x0014, B:13:0x001c, B:16:0x0021, B:17:0x002a, B:19:0x002e, B:23:0x0039, B:25:0x0051, B:28:0x0056, B:30:0x005f, B:35:0x006b, B:38:0x0074, B:33:0x0064), top: B:48:0x0001, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x002e A[Catch: all -> 0x007a, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:8:0x0008, B:10:0x000e, B:11:0x0014, B:13:0x001c, B:16:0x0021, B:17:0x002a, B:19:0x002e, B:23:0x0039, B:25:0x0051, B:28:0x0056, B:30:0x005f, B:35:0x006b, B:38:0x0074, B:33:0x0064), top: B:48:0x0001, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected synchronized boolean createServiceInner(int r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            com.core.lib.common.ICoreSystemServer r0 = r7.mService     // Catch: java.lang.Throwable -> L7a
            r1 = 1
            if (r0 == 0) goto L8
            monitor-exit(r7)
            return r1
        L8:
            int r0 = r7.shouldCheckPackageExit     // Catch: java.lang.Throwable -> L7a
            r2 = 3
            r3 = 0
            if (r1 != r0) goto L2a
            android.content.Context r0 = r7.mContext     // Catch: java.lang.Throwable -> L7a
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch: java.lang.Throwable -> L7a
            java.lang.String r4 = "com.core.system.service"
            android.content.pm.PackageInfo r4 = r0.getPackageInfo(r4, r3)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L20 java.lang.Throwable -> L7a
            if (r4 == 0) goto L1f
            r5 = 2
            r7.shouldCheckPackageExit = r5     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L20 java.lang.Throwable -> L7a
        L1f:
            goto L2a
        L20:
            r4 = move-exception
            java.lang.String r5 = com.core.system.foundationinterface.PlatformCoreAdminService.TAG     // Catch: java.lang.Throwable -> L7a
            java.lang.String r6 = "CoreSystemServer app not exit"
            android.util.Log.d(r5, r6)     // Catch: java.lang.Throwable -> L7a
            r7.shouldCheckPackageExit = r2     // Catch: java.lang.Throwable -> L7a
        L2a:
            int r0 = r7.shouldCheckPackageExit     // Catch: java.lang.Throwable -> L7a
            if (r2 != r0) goto L37
            java.lang.String r0 = com.core.system.foundationinterface.PlatformCoreAdminService.TAG     // Catch: java.lang.Throwable -> L7a
            java.lang.String r1 = "CoreSystemServer app not exit"
            android.util.Log.d(r0, r1)     // Catch: java.lang.Throwable -> L7a
            monitor-exit(r7)
            return r3
        L37:
            int r8 = r8 + (-1)
            android.content.Intent r0 = new android.content.Intent     // Catch: java.lang.Throwable -> L7a
            r0.<init>()     // Catch: java.lang.Throwable -> L7a
            java.lang.String r2 = "com.core.system.service/.CoreSystemServer"
            android.content.ComponentName r2 = android.content.ComponentName.unflattenFromString(r2)     // Catch: java.lang.Throwable -> L7a
            r0.setComponent(r2)     // Catch: java.lang.Throwable -> L7a
            android.content.BroadcastReceiver r4 = r7.broadcastReceiver     // Catch: java.lang.Throwable -> L7a
            android.content.Context r5 = r7.mContext     // Catch: java.lang.Throwable -> L7a
            android.os.IBinder r4 = r4.peekService(r5, r0)     // Catch: java.lang.Throwable -> L7a
            if (r4 == 0) goto L56
            r7.onServiceConnected(r4)     // Catch: java.lang.Throwable -> L7a
            monitor-exit(r7)
            return r1
        L56:
            java.lang.String r1 = com.core.system.foundationinterface.PlatformCoreAdminService.TAG     // Catch: java.lang.Throwable -> L7a
            java.lang.String r5 = "CoreSystem get null binder!"
            android.util.Log.w(r1, r5)     // Catch: java.lang.Throwable -> L7a
            r5 = 100
            java.lang.Thread.sleep(r5)     // Catch: java.lang.InterruptedException -> L63 java.lang.Throwable -> L7a
            goto L69
        L63:
            r1 = move-exception
            java.lang.String r5 = com.core.system.foundationinterface.PlatformCoreAdminService.TAG     // Catch: java.lang.Throwable -> L7a
            android.util.Log.w(r5, r1)     // Catch: java.lang.Throwable -> L7a
        L69:
            if (r8 > 0) goto L74
            java.lang.String r1 = com.core.system.foundationinterface.PlatformCoreAdminService.TAG     // Catch: java.lang.Throwable -> L7a
            java.lang.String r5 = "wait long enough to get CoreSystemServer binder but got null!!!!!!!"
            android.util.Log.e(r1, r5)     // Catch: java.lang.Throwable -> L7a
            monitor-exit(r7)
            return r3
        L74:
            boolean r1 = r7.createServiceInner(r8)     // Catch: java.lang.Throwable -> L7a
            monitor-exit(r7)
            return r1
        L7a:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.core.system.foundationinterface.PlatformCoreAdminService.createServiceInner(int):boolean");
    }
}
