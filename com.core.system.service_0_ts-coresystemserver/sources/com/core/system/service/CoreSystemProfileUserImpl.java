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
public class CoreSystemProfileUserImpl extends CoreSystemServerImpl {
    private IServiceStateListener mServiceStateListener;
    private CoreSystemUserManager mSystemUserManager;

    public CoreSystemProfileUserImpl(Context context) {
        super(context);
        this.mServiceStateListener = new AnonymousClass1();
        CoreSystemUserManager coreSystemUserManager = new CoreSystemUserManager(context);
        this.mSystemUserManager = coreSystemUserManager;
        coreSystemUserManager.registerServiceStateListener(this.mServiceStateListener);
    }

    /* JADX INFO: renamed from: com.core.system.service.CoreSystemProfileUserImpl$1, reason: invalid class name */
    class AnonymousClass1 implements IServiceStateListener {
        AnonymousClass1() {
        }

        public void onServiceStarted(String serviceName) {
            final ServiceDataManager serviceDataManager;
            synchronized (CoreSystemProfileUserImpl.this.mServiceDataMap) {
                serviceDataManager = CoreSystemProfileUserImpl.this.mServiceDataMap.get(serviceName);
            }
            if (serviceDataManager == null) {
                CoreSystemProfileUserImpl.this.notifyServiceStateToListeners(serviceName, true);
                return;
            }
            if (serviceDataManager.getServiceData().isAllUserConfig()) {
                Logger.debug("ProfileUser-onServiceStarted. this type of service callback from current process, uid:" + Process.myUid() + ", serviceName:" + serviceName);
                return;
            }
            CoreSystemProfileUserImpl.this.mHandler.post(new Runnable() { // from class: com.core.system.service.-$$Lambda$CoreSystemProfileUserImpl$1$3S7eFepa4XdeCcJAcG_p7sMBWWg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onServiceStarted$0$CoreSystemProfileUserImpl$1(serviceDataManager);
                }
            });
        }

        public /* synthetic */ void lambda$onServiceStarted$0$CoreSystemProfileUserImpl$1(ServiceDataManager serviceDataManager) {
            CoreSystemProfileUserImpl.this.notifyServiceStarted(serviceDataManager);
        }

        public void onServiceStopped(String serviceName) {
            final ServiceDataManager serviceDataManager;
            synchronized (CoreSystemProfileUserImpl.this.mServiceDataMap) {
                serviceDataManager = CoreSystemProfileUserImpl.this.mServiceDataMap.get(serviceName);
            }
            if (serviceDataManager == null) {
                CoreSystemProfileUserImpl.this.notifyServiceStateToListeners(serviceName, false);
                return;
            }
            if (serviceDataManager.getServiceData().isAllUserConfig()) {
                Logger.debug("ProfileUser-onServiceStopped. this type of service callback from current process, uid:" + Process.myUid() + ", serviceName:" + serviceName);
                return;
            }
            CoreSystemProfileUserImpl.this.mHandler.post(new Runnable() { // from class: com.core.system.service.-$$Lambda$CoreSystemProfileUserImpl$1$-iExmnnESQA-zCC2plXbZMvMZq8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onServiceStopped$1$CoreSystemProfileUserImpl$1(serviceDataManager);
                }
            });
        }

        public /* synthetic */ void lambda$onServiceStopped$1$CoreSystemProfileUserImpl$1(ServiceDataManager serviceDataManager) {
            CoreSystemProfileUserImpl.this.notifyServiceStopped(serviceDataManager);
        }
    }

    @Override // com.core.system.service.CoreSystemServerImpl, com.core.system.service.utils.UserSwitchListener
    public void onUserSwitched(int prevCurrentUser, int currentUserId) {
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
            if (serviceDataManager.getServiceData().isForegroundUserConfig()) {
                Logger.warning("addServiceBinder error, current user is not forground user, uid:" + Process.myUid());
                return false;
            }
            if (serviceDataManager.getServiceData().isSystemUserConfig() || serviceDataManager.getServiceData().isExcludeProfileUserConfig()) {
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
        if (serviceDataManager.getServiceData().isForegroundUserConfig()) {
            Logger.warning("notifyServiceReady error, cannot notify ready in profile user, uid:" + Process.myUid());
            return false;
        }
        if (!serviceDataManager.getServiceData().isAllUserConfig()) {
            return this.mSystemUserManager.notifyServiceReady(serviceName, binder);
        }
        if (6 == serviceDataManager.getServiceState()) {
            Logger.warning("notifyServiceReady error, serviceDataManager is state fake, param name:" + serviceName);
            return false;
        }
        addServiceBinder(serviceName, binder);
        serviceDataManager.setServiceReady(true);
        return true;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.core.system.service.CoreSystemServerImpl
    protected void startServicesByUserConfig(ServiceDataManager serviceDataManager, boolean onlyStartUserForground) {
        if (onlyStartUserForground) {
            Logger.debug("do not start ForeGround Services, current user is not foreground:" + Process.myUid());
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
        if (b == 0) {
            serviceDataManager.startServiceIfNeeded();
            return;
        }
        if (b == 1 || b == 2 || b == 3) {
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
        if (serviceDataManager.getServiceData().isSystemUserConfig() || serviceDataManager.getServiceData().isExcludeProfileUserConfig() || serviceDataManager.getServiceData().isForegroundUserConfig()) {
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
        if (serviceDataManager.getServiceData().isAllUserConfig()) {
            return serviceDataManager.isServiceReady();
        }
        Logger.debug("isServiceReady , user config not fit with current user, check from SystemUser. name:" + name);
        return this.mSystemUserManager.isServiceReady(name);
    }
}
