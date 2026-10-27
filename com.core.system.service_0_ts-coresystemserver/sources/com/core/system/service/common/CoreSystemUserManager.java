package com.core.system.service.common;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import com.core.lib.common.ICoreSystemServer;
import com.core.lib.common.ICoreSystemServerListener;
import com.core.system.foundationinterface.ICoreAdminService;
import com.core.system.foundationinterface.ICoreService;
import com.ts.car.common.listener.LocalListenerHelper;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.sdk.core.Logger;
import java.util.function.Consumer;
import ts.car.service.core.ICoreSystemService;
import ts.car.service.core.IServiceStateListener;

/* JADX INFO: loaded from: classes.dex */
public class CoreSystemUserManager extends SimpleServiceBase implements ICoreSystemService, ICoreAdminService {
    private static final String SERVICE_COMPONENT = "com.core.system.service/.CoreSystemServer";
    private ICoreSystemServerListener mListener;
    protected LocalListenerHelper<IServiceStateListener> mLocalListeners;
    protected ICoreSystemServer mService;

    public CoreSystemUserManager(Context context) {
        super(context, SERVICE_COMPONENT, (String) null, (IBinder) null);
        this.mListener = new AnonymousClass1();
        this.mLocalListeners = new LocalListenerHelper<>();
    }

    public boolean bindService() {
        synchronized (this) {
            if (this.mService != null) {
                return true;
            }
            this.mManualDisconnected = true;
            this.mIsBinding = true;
            Intent intent = new Intent();
            intent.setComponent(ComponentName.unflattenFromString(SERVICE_COMPONENT));
            return this.mContext.bindServiceAsUser(intent, this.mServiceConnection, 1, UserHandle.SYSTEM);
        }
    }

    protected void onServiceConnected(IBinder binder) {
        ICoreSystemServer iCoreSystemServerAsInterface = ICoreSystemServer.Stub.asInterface(binder);
        this.mService = iCoreSystemServerAsInterface;
        try {
            iCoreSystemServerAsInterface.addServiceListener(this.mListener);
        } catch (RemoteException ex) {
            Logger.warning("CoreSystemUserManager addServiceListener error", ex);
        }
    }

    /* JADX INFO: renamed from: com.core.system.service.common.CoreSystemUserManager$1, reason: invalid class name */
    class AnonymousClass1 extends ICoreSystemServerListener.Stub {
        AnonymousClass1() {
        }

        public void onServiceStarted(final String serviceName) {
            CoreSystemUserManager.this.mLocalListeners.notifyListener(new Consumer() { // from class: com.core.system.service.common.-$$Lambda$CoreSystemUserManager$1$-30BXfQLBilnQnethE4idc2hVRg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IServiceStateListener) obj).onServiceStarted(serviceName);
                }
            });
        }

        public void onServiceStopped(final String serviceName) {
            CoreSystemUserManager.this.mLocalListeners.notifyListener(new Consumer() { // from class: com.core.system.service.common.-$$Lambda$CoreSystemUserManager$1$KqoAQunRjcu6y57MkmHwqRPtmhY
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IServiceStateListener) obj).onServiceStopped(serviceName);
                }
            });
        }
    }

    protected void onServiceDied() {
        Logger.debug("CoreSystemUserManager onServiceDied, maybe remote service died or user closed");
        this.mService = null;
    }

    public IBinder getService(String serviceName) {
        ICoreSystemServer iCoreSystemServer = this.mService;
        if (iCoreSystemServer == null) {
            Logger.warning("CoreSystemUserManager getService, mService is null");
            return null;
        }
        try {
            return iCoreSystemServer.getService(serviceName);
        } catch (RemoteException ex) {
            Logger.warning("CoreSystemUserManager getService error", ex);
            return null;
        }
    }

    public boolean registerServiceStateListener(IServiceStateListener listener) {
        return this.mLocalListeners.addListener(listener);
    }

    public boolean unregisterServiceStateListener(IServiceStateListener listener) {
        return this.mLocalListeners.removeListener(listener);
    }

    public boolean isServiceSupport(String serviceName) {
        ICoreSystemServer iCoreSystemServer = this.mService;
        if (iCoreSystemServer == null) {
            Logger.warning("CoreSystemUserManager isServiceSupport, mService is null");
            return false;
        }
        try {
            return iCoreSystemServer.isServiceSupport(serviceName);
        } catch (RemoteException ex) {
            Logger.warning("CoreSystemUserManager isServiceSupport error", ex);
            return false;
        }
    }

    public boolean isServiceReady(String serviceName) {
        ICoreSystemServer iCoreSystemServer = this.mService;
        if (iCoreSystemServer == null) {
            Logger.warning("CoreSystemUserManager isServiceReady, mService is null");
            return false;
        }
        try {
            return iCoreSystemServer.isServiceReady(serviceName);
        } catch (RemoteException ex) {
            Logger.warning("CoreSystemUserManager isServiceReady error", ex);
            return false;
        }
    }

    public boolean registerSingleServiceStateListener(String serviceName, IServiceStateListener listener) {
        Logger.warning("CoreSystemUserManager registerSingleServiceStateListener Method not implemented!");
        return false;
    }

    public boolean unregisterSingleServiceStateListener(String serviceName, IServiceStateListener listener) {
        Logger.warning("CoreSystemUserManager unregisterSingleServiceStateListener Method not implemented!");
        return false;
    }

    public String serviceName() {
        Logger.warning("CoreSystemUserManager serviceName Method not implemented!");
        return null;
    }

    @Override // com.core.system.foundationinterface.ICoreAdminService
    public boolean addService(ICoreService coreService) {
        Logger.warning("CoreSystemUserManager addService Method not implemented!");
        return false;
    }

    public boolean addService(String serviceName, IBinder service) {
        ICoreSystemServer iCoreSystemServer = this.mService;
        if (iCoreSystemServer == null) {
            Logger.warning("CoreSystemUserManager addService error, mService is null, serviceName:" + serviceName);
            return false;
        }
        try {
            return iCoreSystemServer.addService(serviceName, service);
        } catch (Throwable ex) {
            Logger.warning("CoreSystemUserManager addService error", ex);
            return false;
        }
    }

    @Override // com.core.system.foundationinterface.ICoreAdminService
    public boolean notifyServiceReady(ICoreService coreService) {
        Logger.warning("CoreSystemUserManager notifyServiceReady Method not implemented!");
        return false;
    }

    public boolean notifyServiceReady(String serviceName, IBinder binder) {
        ICoreSystemServer iCoreSystemServer = this.mService;
        if (iCoreSystemServer == null) {
            Logger.warning("CoreSystemUserManager notifyServiceReady error, mService is null, serviceName:" + serviceName);
            return false;
        }
        try {
            return iCoreSystemServer.notifyServiceReady(serviceName, binder);
        } catch (RemoteException ex) {
            Logger.warning("CoreSystemUserManager notifyServiceReady error", ex);
            return false;
        }
    }
}
