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
import com.core.system.service.utils.AutoBootOnUserHelper;
import com.core.system.service.utils.UserSwitchListener;
import com.core.system.service.utils.UserSwitchUtil;
import com.ts.car.common.listener.LocalListenerHelper;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.sdk.core.Logger;
import java.util.function.Consumer;
import ts.car.service.core.ICoreSystemService;
import ts.car.service.core.IServiceStateListener;

/* JADX INFO: loaded from: classes.dex */
public class CoreForegroundUserManager extends SimpleServiceBase implements ICoreSystemService, ICoreAdminService {
    private static final String SERVICE_COMPONENT = "com.core.system.service/.CoreSystemServer";
    private AutoBootOnUserHelper mAutoBootOnUserHelper;
    private ICoreSystemServerListener mListener;
    protected LocalListenerHelper<IServiceStateListener> mLocalListeners;
    protected ICoreSystemServer mService;
    private UserSwitchListener mUserSwitchListener;

    public CoreForegroundUserManager(Context context) {
        super(context, SERVICE_COMPONENT, (String) null, (IBinder) null);
        this.mUserSwitchListener = new UserSwitchListener() { // from class: com.core.system.service.common.CoreForegroundUserManager.1
            @Override // com.core.system.service.utils.UserSwitchListener
            public void onUserSwitched(int prevCurrentUser, int currentUserId) {
                Logger.debug("ForegroundCoreManager-onUserSwitched, currentUserId:" + currentUserId);
                try {
                    if (CoreForegroundUserManager.this.mService != null && CoreForegroundUserManager.this.mService.asBinder().isBinderAlive()) {
                        CoreForegroundUserManager.this.mService.removeServiceListener(CoreForegroundUserManager.this.mListener);
                        CoreForegroundUserManager.this.mService = null;
                    }
                    CoreForegroundUserManager.this.unbindService();
                } catch (Throwable ex) {
                    Logger.warning("ForegroundCoreManager removeServiceListener error", ex);
                }
                CoreForegroundUserManager.this.bindService();
            }
        };
        this.mListener = new AnonymousClass2();
        this.mLocalListeners = new LocalListenerHelper<>();
        new UserSwitchUtil(context).init(this.mUserSwitchListener);
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
            if (this.mAutoBootOnUserHelper == null) {
                this.mAutoBootOnUserHelper = new AutoBootOnUserHelper(this.mContext);
            }
            int currentUser = this.mAutoBootOnUserHelper.getForegroundUserId(this.mContext);
            if (currentUser >= 0 && (!this.mAutoBootOnUserHelper.isCurrentUserSystemUser() || !this.mAutoBootOnUserHelper.isSystemUserHeadlessUser())) {
                return this.mContext.bindServiceAsUser(intent, this.mServiceConnection, 1, UserHandle.of(currentUser));
            }
            Logger.warning("currentUser cannot be foreground user, check it! currentUser:" + currentUser);
            return false;
        }
    }

    protected void onServiceConnected(IBinder binder) {
        ICoreSystemServer iCoreSystemServerAsInterface = ICoreSystemServer.Stub.asInterface(binder);
        this.mService = iCoreSystemServerAsInterface;
        try {
            iCoreSystemServerAsInterface.addServiceListener(this.mListener);
        } catch (RemoteException ex) {
            Logger.warning("ForegroundCoreManager addServiceListener error", ex);
        }
    }

    /* JADX INFO: renamed from: com.core.system.service.common.CoreForegroundUserManager$2, reason: invalid class name */
    class AnonymousClass2 extends ICoreSystemServerListener.Stub {
        AnonymousClass2() {
        }

        public void onServiceStarted(final String serviceName) {
            CoreForegroundUserManager.this.mLocalListeners.notifyListener(new Consumer() { // from class: com.core.system.service.common.-$$Lambda$CoreForegroundUserManager$2$YlpIjp3GdO43t8HpD6HR5QyMA9o
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IServiceStateListener) obj).onServiceStarted(serviceName);
                }
            });
        }

        public void onServiceStopped(final String serviceName) {
            CoreForegroundUserManager.this.mLocalListeners.notifyListener(new Consumer() { // from class: com.core.system.service.common.-$$Lambda$CoreForegroundUserManager$2$lLS7kwuB78EfCAvUJzipOyrqfwc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((IServiceStateListener) obj).onServiceStopped(serviceName);
                }
            });
        }
    }

    protected void onServiceDied() {
        Logger.debug("ForegroundCoreManager onServiceDied, maybe remote service died or user closed");
        this.mService = null;
    }

    public IBinder getService(String serviceName) {
        ICoreSystemServer iCoreSystemServer = this.mService;
        if (iCoreSystemServer == null) {
            Logger.warning("ForegroundCoreManager getService, mService is null");
            return null;
        }
        try {
            return iCoreSystemServer.getService(serviceName);
        } catch (RemoteException ex) {
            Logger.warning("ForegroundCoreManager getService error", ex);
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
        Logger.warning("CoreForegroundUserManager isServiceSupport Method not implemented!");
        return false;
    }

    public boolean isServiceReady(String serviceName) {
        ICoreSystemServer iCoreSystemServer = this.mService;
        if (iCoreSystemServer == null) {
            Logger.warning("ForegroundCoreManager isServiceReady, mService is null");
            return false;
        }
        try {
            return iCoreSystemServer.isServiceReady(serviceName);
        } catch (RemoteException ex) {
            Logger.warning("ForegroundCoreManager isServiceReady error", ex);
            return false;
        }
    }

    public boolean registerSingleServiceStateListener(String serviceName, IServiceStateListener listener) {
        Logger.warning("CoreForegroundUserManager registerSingleServiceStateListener Method not implemented!");
        return false;
    }

    public boolean unregisterSingleServiceStateListener(String serviceName, IServiceStateListener listener) {
        Logger.warning("CoreForegroundUserManager unregisterSingleServiceStateListener Method not implemented!");
        return false;
    }

    public String serviceName() {
        Logger.warning("CoreForegroundUserManager serviceName Method not implemented!");
        return null;
    }

    @Override // com.core.system.foundationinterface.ICoreAdminService
    public boolean addService(ICoreService coreService) {
        Logger.warning("CoreForegroundUserManager addService Method not implemented!");
        return false;
    }

    public boolean addService(String serviceName, IBinder service) {
        ICoreSystemServer iCoreSystemServer = this.mService;
        if (iCoreSystemServer == null) {
            Logger.warning("CoreForegroundUserManager addService error, mService is null, serviceName:" + serviceName);
            return false;
        }
        try {
            return iCoreSystemServer.addService(serviceName, service);
        } catch (RemoteException ex) {
            Logger.warning("CoreForegroundUserManager addService error", ex);
            return false;
        }
    }

    @Override // com.core.system.foundationinterface.ICoreAdminService
    public boolean notifyServiceReady(ICoreService coreService) {
        Logger.warning("CoreForegroundUserManager notifyServiceReady Method not implemented!");
        return false;
    }

    public boolean notifyServiceReady(String serviceName, IBinder binder) {
        Logger.warning("CoreForegroundUserManager notifyServiceReady Method not implemented!");
        return false;
    }
}
