package com.core.system.service;

import android.content.ComponentName;
import android.content.Context;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.text.TextUtils;
import com.core.lib.common.ICoreSystemServerListener;
import com.core.system.foundationinterface.ICoreAdminService;
import com.core.system.service.common.CoreForegroundUserManager;
import com.core.system.service.common.CoreServicesLoader;
import com.core.system.service.common.CoreSystemCrashHandler;
import com.core.system.service.common.Utils;
import com.core.system.service.data.ExecutorData;
import com.core.system.service.data.ServiceData;
import com.core.system.service.safeguard.CoreSystemServerMonitor;
import com.core.system.service.safeguard.ExternalServiceMonitor;
import com.core.system.service.safeguard.Watchdog;
import com.core.system.service.utils.AutoBootOnUserHelper;
import com.core.system.service.utils.LocalCoreAdminManager;
import com.core.system.service.utils.ServiceDataManager;
import com.core.system.service.utils.UserSwitchListener;
import com.core.system.service.utils.UserSwitchUtil;
import com.core.system.service.utils.UserUnlockListener;
import com.core.system.service.utils.UserUnlockUtil;
import com.ts.car.sdk.core.Logger;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ts.car.service.core.IServiceStateListener;

/* JADX INFO: loaded from: classes.dex */
public class CoreSystemServerImpl implements UserSwitchListener {
    public static final int LOOP_CHECK_SERVICES = 1;
    private static final long LOOP_CHECK_SERVICES_DELAY = 5000;
    public static final int MSG_CRASH_SERVICE = 2;
    public static final int MSG_REPORT_SERVICE_START = 3;
    public static final int MSG_REPORT_SERVICE_STOPPED = 4;
    protected AutoBootOnUserHelper mAutoBootOnUserHelper;
    protected Context mContext;
    protected ICoreAdminService mCoreAdminService;
    private Watchdog.Monitor mExternalServiceMonitor;
    private CoreForegroundUserManager mForegroundUserManager;
    protected Handler mHandler;
    private Watchdog.Monitor mInternalServiceMonitor;
    private HandlerThread mThread;
    protected RemoteCallbackList<ICoreSystemServerListener> mListenerList = new RemoteCallbackList<>();
    private CoreSystemCrashHandler.ExceptionCallBack mExceptionCallBack = new CoreSystemCrashHandler.ExceptionCallBack() { // from class: com.core.system.service.CoreSystemServerImpl.1
        @Override // com.core.system.service.common.CoreSystemCrashHandler.ExceptionCallBack
        public void onServiceException(String[] stackStr) {
            Logger.debug("onServiceException()");
            if (stackStr != null && CoreSystemServerImpl.this.mHandler != null) {
                Message message = Message.obtain();
                message.what = 2;
                message.obj = stackStr;
                CoreSystemServerImpl.this.mHandler.sendMessage(message);
            }
        }
    };
    protected HashMap<String, ServiceDataManager> mServiceDataMap = new HashMap<>();
    protected HashMap<String, ExecutorData> mExecutorDataMap = new HashMap<>();
    private UserUnlockListener mUserUnlockListener = new UserUnlockListener() { // from class: com.core.system.service.CoreSystemServerImpl.2
        @Override // com.core.system.service.utils.UserUnlockListener
        public void onUserUnlocked() {
            if (CoreSystemServerImpl.this.mHandler != null) {
                CoreSystemServerImpl.this.mHandler.removeMessages(1);
            }
            CoreSystemServerImpl.this.loopServicesState();
        }
    };
    private IServiceStateListener mForegroundUserServiceStateListener = new AnonymousClass3();
    private boolean shouldRestartExecutors = true;

    public Handler getThreadHandler() {
        return this.mHandler;
    }

    @Override // com.core.system.service.utils.UserSwitchListener
    public void onUserSwitched(final int prevCurrentUser, final int currentUserId) {
        this.mHandler.post(new Runnable() { // from class: com.core.system.service.-$$Lambda$CoreSystemServerImpl$wkqOV9TjB5f6kuQoPtjTuP8JkfE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onUserSwitched$0$CoreSystemServerImpl(prevCurrentUser, currentUserId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: processUserSwitched, reason: merged with bridge method [inline-methods] */
    public void lambda$onUserSwitched$0$CoreSystemServerImpl(int prevCurrentUser, int currentUserId) {
        if (prevCurrentUser == this.mAutoBootOnUserHelper.getMyUserId()) {
            releaseForegroundService();
        } else if (currentUserId == this.mAutoBootOnUserHelper.getMyUserId()) {
            startServices(true);
        }
    }

    private void releaseForegroundService() {
        synchronized (this.mServiceDataMap) {
            for (ServiceDataManager serviceDataManager : this.mServiceDataMap.values()) {
                serviceDataManager.releaseUserForegroundService();
                notifyServiceStopped(serviceDataManager);
            }
        }
    }

    public CoreSystemServerImpl(Context context) {
        this.mContext = context;
        this.mAutoBootOnUserHelper = new AutoBootOnUserHelper(this.mContext);
        initHandler();
        this.mCoreAdminService = new LocalCoreAdminManager(this.mContext, this);
        UserUnlockUtil.getInstance().init(this.mContext, this.mUserUnlockListener);
        CoreSystemCrashHandler.getInstance().registerCallBack(this.mExceptionCallBack);
        initAllServices();
        Logger.info("mHandler: " + this.mHandler.toString());
        initWatchDog();
        if (this.mAutoBootOnUserHelper.isCurrentUserSystemUser()) {
            CoreForegroundUserManager coreForegroundUserManager = new CoreForegroundUserManager(this.mContext);
            this.mForegroundUserManager = coreForegroundUserManager;
            coreForegroundUserManager.registerServiceStateListener(this.mForegroundUserServiceStateListener);
        }
        new UserSwitchUtil(this.mContext).init(this);
    }

    /* JADX INFO: renamed from: com.core.system.service.CoreSystemServerImpl$3, reason: invalid class name */
    class AnonymousClass3 implements IServiceStateListener {
        AnonymousClass3() {
        }

        public void onServiceStarted(String serviceName) {
            final ServiceDataManager serviceDataManager;
            if (CoreSystemServerImpl.this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(CoreSystemServerImpl.this.mContext)) {
                Logger.debug("current user is foreground user. do not need callback onServiceStarted, serviceName:" + serviceName);
                return;
            }
            synchronized (CoreSystemServerImpl.this.mServiceDataMap) {
                serviceDataManager = CoreSystemServerImpl.this.mServiceDataMap.get(serviceName);
            }
            if (serviceDataManager == null || !serviceDataManager.getServiceData().isForegroundUserConfig()) {
                Logger.warning("foregroundService-onServiceStarted, service is not ForegroundUserConfig, check this code! serviceName:" + serviceName);
                return;
            }
            CoreSystemServerImpl.this.mHandler.post(new Runnable() { // from class: com.core.system.service.-$$Lambda$CoreSystemServerImpl$3$pjLY_JXqYrRHoQHHs2KJmo7_Fes
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onServiceStarted$0$CoreSystemServerImpl$3(serviceDataManager);
                }
            });
        }

        public /* synthetic */ void lambda$onServiceStarted$0$CoreSystemServerImpl$3(ServiceDataManager serviceDataManager) {
            CoreSystemServerImpl.this.notifyServiceStarted(serviceDataManager);
        }

        public void onServiceStopped(String serviceName) {
            final ServiceDataManager serviceDataManager;
            if (CoreSystemServerImpl.this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(CoreSystemServerImpl.this.mContext)) {
                Logger.debug("current user is foreground user. do not need callback onServiceStopped, serviceName:" + serviceName);
                return;
            }
            synchronized (CoreSystemServerImpl.this.mServiceDataMap) {
                serviceDataManager = CoreSystemServerImpl.this.mServiceDataMap.get(serviceName);
            }
            if (serviceDataManager == null || !serviceDataManager.getServiceData().isForegroundUserConfig()) {
                Logger.warning("foregroundService-onServiceStopped, user is not ForegroundUserConfig, name:" + serviceName);
                return;
            }
            CoreSystemServerImpl.this.mHandler.post(new Runnable() { // from class: com.core.system.service.-$$Lambda$CoreSystemServerImpl$3$JoSlGHRbORrsUcMiluRSY5T6RaU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onServiceStopped$1$CoreSystemServerImpl$3(serviceDataManager);
                }
            });
        }

        public /* synthetic */ void lambda$onServiceStopped$1$CoreSystemServerImpl$3(ServiceDataManager serviceDataManager) {
            CoreSystemServerImpl.this.notifyServiceStopped(serviceDataManager);
        }
    }

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
        if (serviceDataManager == null || 6 == serviceDataManager.getServiceState()) {
            Logger.warning("notifyServiceReady error, serviceDataManager is fake, param name:" + serviceName);
            return false;
        }
        if (serviceDataManager.getServiceData().isForegroundUserConfig() && !this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext)) {
            Logger.warning("notifyServiceReady error, current user is not foreground user, uid:" + Process.myUid() + ", serviceName:" + serviceName);
            return false;
        }
        addServiceBinder(serviceName, binder);
        serviceDataManager.setServiceReady(true);
        return true;
    }

    private void notifyServiceReady(ServiceDataManager serviceDataManager) {
        ArrayList<ServiceDataManager> dependMeServiceDataList = serviceDataManager.mStrongServiceDataListDependMe;
        for (ServiceDataManager tempServiceDataManager : dependMeServiceDataList) {
            tempServiceDataManager.notifyServiceReady(serviceDataManager.getServiceData().getServiceName(), null);
        }
        ArrayList<ServiceDataManager> dependMeServiceDataList2 = serviceDataManager.mWeakServiceDataListDependMe;
        for (ServiceDataManager tempServiceDataManager2 : dependMeServiceDataList2) {
            tempServiceDataManager2.notifyServiceReady(serviceDataManager.getServiceData().getServiceName(), null);
        }
    }

    public IBinder getServiceBinder(String serviceName) {
        ServiceDataManager serviceDataManager;
        if (serviceName == null) {
            Logger.warning("getServiceBinder error, param name is null!");
            return null;
        }
        synchronized (this.mServiceDataMap) {
            serviceDataManager = this.mServiceDataMap.get(serviceName);
        }
        if (serviceDataManager == null) {
            Logger.warning("getServiceBinder error, coreService not exit! paramName:" + serviceName);
            return null;
        }
        if (!serviceDataManager.getServiceData().isServiceUsedState()) {
            Logger.warning("getServiceBinder error, service is configed unUsed, serviceName:" + serviceName);
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
        if (this.mForegroundUserManager != null && serviceDataManager.getServiceData().isForegroundUserConfig() && !this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext)) {
            return this.mForegroundUserManager.getService(serviceName);
        }
        Logger.info("getServiceBinder name : " + serviceName + " binder : " + serviceDataManager.getServiceBinder());
        return serviceDataManager.getServiceBinder();
    }

    public ICoreAdminService getCoreAdminService() {
        return this.mCoreAdminService;
    }

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
                ServiceData serviceData = new ServiceData();
                serviceData.setServiceName(name);
                ServiceDataManager serviceDataManager2 = new ServiceDataManager(serviceData, this);
                serviceDataManager2.getServiceData().setServiceUsedState(true);
                serviceDataManager2.setBinder(binder);
                serviceDataManager2.setServiceState(4);
                synchronized (this.mServiceDataMap) {
                    this.mServiceDataMap.put(name, serviceDataManager2);
                }
                Logger.info("addServiceBinder success,  name : " + name);
                return true;
            }
            if (5 == serviceDataManager.getServiceState()) {
                Logger.warning("addServiceBinder failed, this service is marked oneShot");
                return false;
            }
            if (serviceDataManager.getServiceData().isForegroundUserConfig() && !this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext)) {
                Logger.debug("addServiceBinder error, current user is not forground user,, cannot add serviceBinder which configed ForegroundUser type. uid:" + Process.myUid());
                return false;
            }
            serviceDataManager.getServiceData().setServiceUsedState(true);
            serviceDataManager.setBinder(binder);
            serviceDataManager.setServiceState(4);
            return true;
        }
    }

    public boolean addServiceListener(ICoreSystemServerListener listener) {
        if (listener == null) {
            Logger.warning("addServiceListener, listener is null!!");
            return false;
        }
        Logger.debug("addServiceListener listener : " + listener);
        synchronized (this.mListenerList) {
            this.mListenerList.register(listener);
        }
        return true;
    }

    public boolean removeServiceListener(ICoreSystemServerListener listener) {
        boolean zUnregister;
        if (listener == null) {
            Logger.warning("removeServiceListener, listener is null!!");
            return false;
        }
        Logger.debug("removeServiceListener listener : " + listener);
        synchronized (this.mListenerList) {
            zUnregister = this.mListenerList.unregister(listener);
        }
        return zUnregister;
    }

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
            Logger.debug("isServiceSupport, service is not managed in CoreSystemServer, name:" + name);
            return false;
        }
        if (serviceDataManager.getServiceState() == 6 || !serviceDataManager.getServiceData().isServiceUsedState()) {
            Logger.warning("isServiceSupport error, service is fake or serviceUsed state is false, serviceData:" + serviceDataManager.getServiceData().toString());
            return false;
        }
        return true;
    }

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
            Logger.warning("isServiceReady error, Service not exit in CoreSystemService! name:" + name);
            return false;
        }
        if (serviceDataManager.getServiceData().isForegroundUserConfig() && !this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext)) {
            return this.mForegroundUserManager.isServiceReady(name);
        }
        if (serviceDataManager.isServiceReady()) {
            return true;
        }
        Logger.debug("service not ready, name:" + name);
        return false;
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    private void initWatchDog() {
        this.mExternalServiceMonitor = new ExternalServiceMonitor(this.mContext, this.mServiceDataMap);
        this.mInternalServiceMonitor = new CoreSystemServerMonitor(this.mContext, this.mListenerList, this.mServiceDataMap);
        Watchdog.getInstance().init(this.mContext);
        Watchdog.getInstance().addMonitor(this.mInternalServiceMonitor);
        Watchdog.getInstance().addMonitor(this.mExternalServiceMonitor);
        Watchdog.getInstance().start();
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread(this.mContext.getPackageName() + "_HANDLER_THREAD");
        this.mThread = handlerThread;
        handlerThread.start();
        Handler handler = new Handler(this.mThread.getLooper()) { // from class: com.core.system.service.CoreSystemServerImpl.4
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                int i = msg.what;
                if (i == 1) {
                    CoreSystemServerImpl.this.loopServicesState();
                    return;
                }
                if (i != 2) {
                    if (i == 3) {
                        ServiceDataManager serviceDataManagerStarted = (ServiceDataManager) msg.obj;
                        CoreSystemServerImpl.this.notifyServiceStarted(serviceDataManagerStarted);
                        return;
                    } else if (i == 4) {
                        ServiceDataManager serviceDataManagerStopped = (ServiceDataManager) msg.obj;
                        CoreSystemServerImpl.this.notifyServiceStopped(serviceDataManagerStopped);
                        return;
                    } else {
                        Logger.warning("unexpected msg, what:" + msg.what);
                        return;
                    }
                }
                String[] stackStr = (String[]) msg.obj;
                ServiceDataManager serviceDataManager = CoreSystemServerImpl.this.checkCrashService(stackStr);
                if (serviceDataManager == null) {
                    Logger.debug("receive MSG_CRASH_SERVICE, Not CoreService crashed.");
                    return;
                }
                CoreSystemServerImpl.this.onServiceStopped(serviceDataManager);
                if (4 == serviceDataManager.getServiceState()) {
                    serviceDataManager.setServiceState(6);
                    Logger.warning("added service exception, change this service to fakeService:" + serviceDataManager.getServiceData().getServiceName());
                    return;
                }
                serviceDataManager.setServiceState(8);
                serviceDataManager.startService(serviceDataManager.getServiceData().getDelay());
            }
        };
        this.mHandler = handler;
        handler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, LOOP_CHECK_SERVICES_DELAY);
        Watchdog.getInstance().addThread(this.mHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ServiceDataManager checkCrashService(String[] stackStr) {
        ComponentName componentName;
        synchronized (this.mServiceDataMap) {
            for (ServiceDataManager serviceDataManager : this.mServiceDataMap.values()) {
                for (int i = 0; i < stackStr.length; i++) {
                    String stackInfo = stackStr[i];
                    Logger.debug("checkCrashService() stackInfo[" + i + "]:" + stackInfo);
                    if (!TextUtils.isEmpty(serviceDataManager.getServiceData().getServicePackage())) {
                        if (compareServiceName(stackInfo, serviceDataManager.getServiceData().getServicePackage())) {
                            Logger.debug("checked Crashed Service， serviceName:" + serviceDataManager.getServiceData().getServiceName());
                            return serviceDataManager;
                        }
                    } else if (serviceDataManager.getServiceData().getComponent() != null && (componentName = ComponentName.unflattenFromString(serviceDataManager.getServiceData().getComponent())) != null && compareServiceName(stackInfo, componentName.getPackageName())) {
                        Logger.debug("checked Crashed Service， serviceName:" + serviceDataManager.getServiceData().getServiceName());
                        return serviceDataManager;
                    }
                }
            }
            return null;
        }
    }

    private boolean compareServiceName(String stackStr, String serviceName) {
        Logger.debug("compareServiceName()\n[stackStr]:" + stackStr + "\n[serviceName]:" + serviceName);
        if (stackStr == null || serviceName == null || stackStr.length() < serviceName.length() || !serviceName.equals(stackStr.substring(0, serviceName.length()))) {
            return false;
        }
        return true;
    }

    public synchronized void onServiceStarted(ServiceDataManager serviceDataManager) {
        Logger.info("onServiceStarted : " + serviceDataManager.toString());
        Message message = Message.obtain(this.mHandler, 3);
        message.obj = serviceDataManager;
        this.mHandler.sendMessage(message);
    }

    public synchronized void onServiceStopped(ServiceDataManager serviceDataManager) {
        Logger.info("onServiceStopped : " + serviceDataManager.toString());
        Message message = Message.obtain(this.mHandler, 4);
        message.obj = serviceDataManager;
        this.mHandler.sendMessage(message);
    }

    protected void notifyServiceStopped(ServiceDataManager serviceDataManager) {
        notifyServiceStateToListeners(serviceDataManager.getServiceData().getServiceName(), false);
    }

    protected void notifyServiceStarted(ServiceDataManager serviceDataManager) {
        notifyServiceReady(serviceDataManager);
        notifyServiceStateToListeners(serviceDataManager.getServiceData().getServiceName(), true);
    }

    protected void notifyServiceStateToListeners(String name, boolean serviceStarted) {
        List<ICoreSystemServerListener> list = new ArrayList<>();
        synchronized (this.mListenerList) {
            int size = this.mListenerList.beginBroadcast();
            for (int i = 0; i < size; i++) {
                list.add(this.mListenerList.getBroadcastItem(i));
            }
            this.mListenerList.finishBroadcast();
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            ICoreSystemServerListener linkListener = list.get(i2);
            if (linkListener != null) {
                if (serviceStarted) {
                    try {
                        linkListener.onServiceStarted(name);
                    } catch (Throwable ex) {
                        Logger.warning("Listener.onServiceStarted error", ex);
                    }
                } else {
                    linkListener.onServiceStopped(name);
                }
            }
        }
    }

    private void initAllServices() {
        Logger.info("initAllServices Start!");
        CoreServicesLoader loader = new CoreServicesLoader();
        loader.loadConfigData(this, this.mServiceDataMap, this.mExecutorDataMap);
        startServices(false);
        startExecutors();
    }

    private void startServices(boolean onlyStartUserForground) {
        synchronized (this.mServiceDataMap) {
            for (ServiceDataManager serviceDataManager : this.mServiceDataMap.values()) {
                startServicesByUserConfig(serviceDataManager, onlyStartUserForground);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void startServicesByUserConfig(com.core.system.service.utils.ServiceDataManager r7, boolean r8) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.core.system.service.CoreSystemServerImpl.startServicesByUserConfig(com.core.system.service.utils.ServiceDataManager, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loopServicesState() {
        Logger.debug("loopServicesState start! ");
        startServices(false);
        startExecutors();
        Logger.debug("loopServicesState end! ");
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageDelayed(1, LOOP_CHECK_SERVICES_DELAY);
    }

    private void startExecutors() {
        if (!this.shouldRestartExecutors) {
            return;
        }
        synchronized (this.mExecutorDataMap) {
            for (ExecutorData executorData : this.mExecutorDataMap.values()) {
                this.shouldRestartExecutors &= executorData.executeIfNeeded(this.mContext);
            }
        }
    }

    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        writer.println("CoreSystemServer dump info:");
        synchronized (this.mServiceDataMap) {
            writer.println("Executor info-shouldRestartExecutors:" + this.shouldRestartExecutors);
            writer.println("Services size:" + this.mServiceDataMap.size());
            for (ServiceDataManager serviceDataManager : this.mServiceDataMap.values()) {
                serviceDataManager.dump(fd, writer, args);
            }
        }
        writer.println();
        writer.println("ICoreSystemServerListener info:");
        synchronized (this.mListenerList) {
            int registeredCallbackCount = this.mListenerList.beginBroadcast();
            writer.println("mListenerList.size:" + registeredCallbackCount);
            for (int i = 0; i < registeredCallbackCount; i++) {
                ICoreSystemServerListener coreSystemServerListener = this.mListenerList.getBroadcastItem(i);
                if (coreSystemServerListener != null) {
                    writer.println("listener item:" + i + ", iCoreSystemServerListener:" + coreSystemServerListener);
                }
            }
            this.mListenerList.finishBroadcast();
        }
    }
}
