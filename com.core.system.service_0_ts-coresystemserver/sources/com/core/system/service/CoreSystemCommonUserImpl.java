package com.core.system.service;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.text.TextUtils;
import com.core.system.service.common.CoreSystemUserManager;
import com.core.system.service.common.Utils;
import com.core.system.service.data.ServiceData;
import com.core.system.service.utils.ServiceDataManager;
import com.ts.car.sdk.core.Logger;
import ts.car.service.core.IServiceStateListener;

/* JADX INFO: loaded from: classes.dex */
public class CoreSystemCommonUserImpl extends CoreSystemServerImpl {
    private IServiceStateListener mServiceStateListener;
    private CoreSystemUserManager mSystemUserManager;

    public CoreSystemCommonUserImpl(Context context) {
        super(context);
        this.mServiceStateListener = new AnonymousClass1();
        CoreSystemUserManager coreSystemUserManager = new CoreSystemUserManager(context);
        this.mSystemUserManager = coreSystemUserManager;
        coreSystemUserManager.registerServiceStateListener(this.mServiceStateListener);
    }

    /* JADX INFO: renamed from: com.core.system.service.CoreSystemCommonUserImpl$1, reason: invalid class name */
    class AnonymousClass1 implements IServiceStateListener {
        AnonymousClass1() {
        }

        public void onServiceStarted(String serviceName) {
            final ServiceDataManager serviceDataManager;
            synchronized (CoreSystemCommonUserImpl.this.mServiceDataMap) {
                serviceDataManager = CoreSystemCommonUserImpl.this.mServiceDataMap.get(serviceName);
            }
            if (serviceDataManager == null) {
                CoreSystemCommonUserImpl.this.notifyServiceStateToListeners(serviceName, true);
                return;
            }
            if (CoreSystemCommonUserImpl.this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(CoreSystemCommonUserImpl.this.mContext) && serviceDataManager.getServiceData().isForegroundUserConfig()) {
                Logger.debug("CommonUser-onServiceStarted-current user is foreground user, uid:" + Process.myUid() + ", serviceName:" + serviceName);
                return;
            }
            if (serviceDataManager.getServiceData().isAllUserConfig() || serviceDataManager.getServiceData().isExcludeProfileUserConfig()) {
                Logger.debug("CommonUser-onServiceStarted. this type of service callback from current process, uid:" + Process.myUid() + ", serviceName:" + serviceName);
                return;
            }
            CoreSystemCommonUserImpl.this.mHandler.post(new Runnable() { // from class: com.core.system.service.-$$Lambda$CoreSystemCommonUserImpl$1$A1f2CM206QoCymlXW7pna0drl6E
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onServiceStarted$0$CoreSystemCommonUserImpl$1(serviceDataManager);
                }
            });
        }

        public /* synthetic */ void lambda$onServiceStarted$0$CoreSystemCommonUserImpl$1(ServiceDataManager serviceDataManager) {
            CoreSystemCommonUserImpl.this.notifyServiceStarted(serviceDataManager);
        }

        public void onServiceStopped(String serviceName) {
            final ServiceDataManager serviceDataManager;
            synchronized (CoreSystemCommonUserImpl.this.mServiceDataMap) {
                serviceDataManager = CoreSystemCommonUserImpl.this.mServiceDataMap.get(serviceName);
            }
            if (serviceDataManager == null) {
                CoreSystemCommonUserImpl.this.notifyServiceStateToListeners(serviceName, false);
                return;
            }
            if (CoreSystemCommonUserImpl.this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(CoreSystemCommonUserImpl.this.mContext) && serviceDataManager.getServiceData().isForegroundUserConfig()) {
                Logger.debug("onServiceStopped-do nothing because current user is foreground user, uid:" + Process.myUid() + ", serviceName:" + serviceName);
                return;
            }
            if (serviceDataManager.getServiceData().isAllUserConfig() || serviceDataManager.getServiceData().isExcludeProfileUserConfig()) {
                Logger.debug("onServiceStopped. this type of service callback from current process, uid:" + Process.myUid() + ", serviceName:" + serviceName);
                return;
            }
            CoreSystemCommonUserImpl.this.mHandler.post(new Runnable() { // from class: com.core.system.service.-$$Lambda$CoreSystemCommonUserImpl$1$S0681Ro6DfFVFaNSjb6TxnjMYCQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onServiceStopped$1$CoreSystemCommonUserImpl$1(serviceDataManager);
                }
            });
        }

        public /* synthetic */ void lambda$onServiceStopped$1$CoreSystemCommonUserImpl$1(ServiceDataManager serviceDataManager) {
            CoreSystemCommonUserImpl.this.notifyServiceStopped(serviceDataManager);
        }
    }

    @Override // com.core.system.service.CoreSystemServerImpl
    public boolean addServiceBinder(String name, IBinder binder) {
        if (binder == null || TextUtils.isEmpty(name)) {
            StringBuilder sb = new StringBuilder();
            sb.append("addService error, name or binder is null, name:");
            sb.append(name);
            sb.append(", binder:");
            sb.append(binder == null ? "null" : binder);
            Logger.warning(sb.toString());
            return false;
        }
        if (!Utils.isSameSignatures(this.mContext, Binder.getCallingUid())) {
            Logger.warning("You don't contains system signature, can not addServiceBinder! serviceName:" + name);
            return false;
        }
        Logger.info("addServiceBinder name : " + name);
        synchronized (this.mServiceDataMap) {
            ServiceDataManager serviceDataManager = this.mServiceDataMap.get(name);
            if (serviceDataManager == null) {
                return this.mSystemUserManager.addService(name, binder);
            }
            if (5 == serviceDataManager.getServiceState()) {
                Logger.warning("addServiceBinder failed, this service is marked oneShot");
                return false;
            }
            if (serviceDataManager.getServiceData().isForegroundUserConfig() && !this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext)) {
                Logger.debug("addServiceBinder error, current user is not forground user, uid:" + Process.myUid());
                return false;
            }
            if (serviceDataManager.getServiceData().isSystemUserConfig()) {
                return this.mSystemUserManager.addService(name, binder);
            }
            serviceDataManager.getServiceData().setServiceUsedState(true);
            serviceDataManager.setBinder(binder);
            serviceDataManager.setServiceState(4);
            return true;
        }
    }

    @Override // com.core.system.service.CoreSystemServerImpl
    public boolean notifyServiceReady(String serviceName, IBinder binder) {
        ServiceDataManager serviceDataManager;
        Logger.debug("notifyServiceReady, name:" + serviceName);
        if (TextUtils.isEmpty(serviceName) || binder == null) {
            Logger.warning("notifyServiceReady error, name or binder is null, name:" + serviceName);
            return false;
        }
        if (!Utils.isSameSignatures(this.mContext, Binder.getCallingUid())) {
            Logger.warning("You don't contains system signature, can not notifyServiceReady! serviceName:" + serviceName);
            return false;
        }
        synchronized (this.mServiceDataMap) {
            serviceDataManager = this.mServiceDataMap.get(serviceName);
        }
        if (serviceDataManager == null) {
            return this.mSystemUserManager.notifyServiceReady(serviceName, binder);
        }
        if (serviceDataManager.getServiceData().isForegroundUserConfig() && !this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext)) {
            Logger.warning("notifyServiceReady error, cannot notify ready in non-foreground user, uid:" + Process.myUid());
            return false;
        }
        if (serviceDataManager.getServiceData().isSystemUserConfig()) {
            return this.mSystemUserManager.notifyServiceReady(serviceName, binder);
        }
        if (6 == serviceDataManager.getServiceState()) {
            Logger.warning("notifyServiceReady error, fake service cannot be ready, param name:" + serviceName);
            return false;
        }
        addServiceBinder(serviceName, binder);
        serviceDataManager.setServiceReady(true);
        return true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.core.system.service.CoreSystemServerImpl
    protected void startServicesByUserConfig(ServiceDataManager serviceDataManager, boolean onlyStartUserForground) {
        if (onlyStartUserForground && !this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext)) {
            Logger.debug("current user is not foreground, no need  to start with onlyStartUserForground flag, uid:" + Process.myUid());
            return;
        }
        String userConfig = serviceDataManager.getServiceData().getUserConfig();
        byte b = -1;
        switch (userConfig.hashCode()) {
            case -1066894544:
                if (userConfig.equals(ServiceData.USER_CONFIG_USER_ALL_EXCLUDE_PROFILE_USER)) {
                    b = 1;
                }
                break;
            case -704716294:
                if (userConfig.equals(ServiceData.USER_CONFIG_USER_SYSTEM)) {
                    b = 2;
                }
                break;
            case 1517491638:
                if (userConfig.equals(ServiceData.USER_CONFIG_USER_ALL)) {
                    b = 0;
                }
                break;
            case 1736965006:
                if (userConfig.equals(ServiceData.USER_CONFIG_USER_FOREGROUND)) {
                    b = 3;
                }
                break;
        }
        if (b == 0 || b == 1) {
            if (onlyStartUserForground) {
                return;
            }
            serviceDataManager.startServiceIfNeeded();
            return;
        }
        if (b == 2) {
            serviceDataManager.setServiceState(9);
            return;
        }
        if (b == 3) {
            if (this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext)) {
                if (!serviceDataManager.isServiceStarted()) {
                    serviceDataManager.setServiceState(0);
                    serviceDataManager.startService(serviceDataManager.getServiceData().getDelay());
                    return;
                }
                return;
            }
            Logger.debug("do not start this ForeGround Service, current user is not foreground:" + Process.myUid());
            serviceDataManager.setServiceState(9);
            return;
        }
        Logger.warning("startServices failed, unrecognized userconfig!, serviceData:" + serviceDataManager.getServiceData().toString());
        serviceDataManager.setServiceState(9);
    }

    @Override // com.core.system.service.CoreSystemServerImpl
    public IBinder getServiceBinder(String name) {
        ServiceDataManager serviceDataManager;
        if (name == null) {
            Logger.warning("getServiceBinder error, param name is null!");
            return null;
        }
        synchronized (this.mServiceDataMap) {
            serviceDataManager = this.mServiceDataMap.get(name);
        }
        if (serviceDataManager == null) {
            Logger.debug("getServiceBinder, ServiceData not exit, try get From SystemUser. paramName:" + name);
            return this.mSystemUserManager.getService(name);
        }
        if (!serviceDataManager.getServiceData().isServiceUsedState()) {
            Logger.warning("getServiceBinder error, service is configed unUsed, serviceName:" + name);
            return null;
        }
        int callingUid = Binder.getCallingUid();
        int protectionLevel = serviceDataManager.getServiceData().getProtectionLevel();
        if (protectionLevel > 1 && !Utils.isSameSignatures(this.mContext, callingUid)) {
            throw new RuntimeException("You are not a system application and do not have access to this service.");
        }
        if (protectionLevel > 2 && callingUid != Process.myUid()) {
            throw new RuntimeException("You are not an core application and do not have access to this service.");
        }
        if (serviceDataManager.getServiceData().isForegroundUserConfig() && !this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext)) {
            Logger.debug("current user not ForegroundUser, get binder from SystemUser");
            return this.mSystemUserManager.getService(name);
        }
        if (serviceDataManager.getServiceData().isSystemUserConfig()) {
            return this.mSystemUserManager.getService(name);
        }
        Logger.info("getServiceBinder name : " + name + " binder : " + serviceDataManager.getServiceBinder());
        return serviceDataManager.getServiceBinder();
    }

    @Override // com.core.system.service.CoreSystemServerImpl
    public boolean isServiceSupport(String name) {
        ServiceDataManager serviceDataManager;
        if (TextUtils.isEmpty(name)) {
            Logger.warning("isServiceSupport error, name is null");
            return false;
        }
        synchronized (this.mServiceDataMap) {
            serviceDataManager = this.mServiceDataMap.get(name);
        }
        if (serviceDataManager == null) {
            Logger.debug("serviceData is null in currentUser, check from SystemUser, name:" + name);
            return this.mSystemUserManager.isServiceSupport(name);
        }
        if (serviceDataManager.getServiceState() == 6 || !serviceDataManager.getServiceData().isServiceUsedState()) {
            Logger.warning("isServiceSupport error, service is fake or serviceUsed state is false, serviceData:" + serviceDataManager.getServiceData().toString());
            return false;
        }
        return true;
    }

    @Override // com.core.system.service.CoreSystemServerImpl
    public boolean isServiceReady(String name) {
        ServiceDataManager serviceDataManager;
        if (name == null) {
            Logger.warning("isServiceReady error, param of serviceName is null!");
            return false;
        }
        synchronized (this.mServiceDataMap) {
            serviceDataManager = this.mServiceDataMap.get(name);
        }
        if (serviceDataManager == null) {
            Logger.debug("isServiceReady , Service not exit, check from SystemUser. name:" + name);
            return this.mSystemUserManager.isServiceReady(name);
        }
        if (serviceDataManager.getServiceData().isForegroundUserConfig() || serviceDataManager.getServiceData().isSystemUserConfig()) {
            Logger.debug("isServiceReady , userconfig not fit with current user, check from SystemUser. name:" + name);
            return this.mSystemUserManager.isServiceReady(name);
        }
        if (serviceDataManager.isServiceReady()) {
            return true;
        }
        Logger.debug("service not ready, name:" + name);
        return false;
    }
}
