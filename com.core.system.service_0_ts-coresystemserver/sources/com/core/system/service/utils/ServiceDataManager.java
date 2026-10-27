package com.core.system.service.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import com.core.system.foundationinterface.ICoreAdminService;
import com.core.system.foundationinterface.ICoreService;
import com.core.system.service.CoreSystemServerImpl;
import com.core.system.service.common.Utils;
import com.core.system.service.data.ServiceData;
import com.ts.car.sdk.core.Logger;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ServiceDataManager {
    public static final int SERVICE_STATE_ADDED = 4;
    public static final int SERVICE_STATE_BINDED = 2;
    public static final int SERVICE_STATE_BINDING = 1;
    public static final int SERVICE_STATE_FAKE = 6;
    public static final int SERVICE_STATE_MAX_RETRY_TIMES = 10;
    public static final int SERVICE_STATE_NORMAL = 0;
    public static final int SERVICE_STATE_ONESHOT = 5;
    public static final int SERVICE_STATE_STARTED = 3;
    public static final int SERVICE_STATE_START_FAILED = 7;
    public static final int SERVICE_STATE_STOPPED = 8;
    public static final int SERVICE_STATE_UNFIT_USERCONFIG = 9;
    private Context mContext;
    private ICoreService mCoreService;
    private CoreSystemServerImpl mCoreSystemServerImpl;
    private ServiceData mServiceData;
    private IBinder mBinder = null;
    private int mServiceState = 0;
    private boolean isServiceReady = false;
    private int retryStartServiceTimes = 0;
    public ArrayList<ServiceDataManager> mStrongDependServiceDataList = new ArrayList<>(0);
    public ArrayList<ServiceDataManager> mStrongServiceDataListDependMe = new ArrayList<>(0);
    public ArrayList<ServiceDataManager> mWeakDependServiceDataList = new ArrayList<>(0);
    public ArrayList<ServiceDataManager> mWeakServiceDataListDependMe = new ArrayList<>(0);
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.core.system.service.utils.ServiceDataManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder binder) {
            StringBuilder sb = new StringBuilder();
            sb.append("onServiceConnected name : ");
            sb.append(name == null ? "null" : name.toString());
            Logger.warning(sb.toString());
            ServiceDataManager.this.setComponentName(name);
            ServiceDataManager.this.setBinder(binder);
            ServiceDataManager.this.retryStartServiceTimes = 0;
            if (ServiceData.START_MODE_START.equals(ServiceDataManager.this.mServiceData.getStartMode())) {
                ServiceDataManager.this.setServiceState(3);
            } else {
                ServiceDataManager.this.setServiceState(2);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            Logger.warning("onServiceDisconnected, serviceData:" + ServiceDataManager.this.mServiceData.toString());
            ServiceDataManager.this.setServiceReady(false);
            ServiceDataManager.this.retryStartServiceTimes = 0;
            ServiceDataManager.this.setServiceState(8);
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName name) {
            ServiceDataManager.this.setComponentName(name);
            if (ServiceData.START_MODE_START.equals(ServiceDataManager.this.mServiceData.getStartMode())) {
                ServiceDataManager.this.setServiceState(3);
            }
        }
    };
    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: com.core.system.service.utils.ServiceDataManager.2
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ServiceDataManager.this.setServiceReady(false);
            ServiceDataManager.this.setServiceState(8);
            ServiceDataManager.this.retryStartServiceTimes = 0;
            synchronized (ServiceDataManager.this) {
                try {
                } catch (Exception e) {
                    Logger.info("binderDied-unlinkToDeath exception, there may be no impact.");
                }
                if (ServiceDataManager.this.mBinder == null || ServiceDataManager.this.mBinder.isBinderAlive()) {
                    ServiceDataManager.this.mBinder = null;
                } else {
                    ServiceDataManager.this.mBinder.unlinkToDeath(this, 0);
                    ServiceDataManager.this.mBinder = null;
                }
            }
            Logger.warning("binderDied : " + ServiceDataManager.this.mServiceData.toString());
        }
    };
    private ComponentName mComponentName = null;

    public ServiceDataManager(ServiceData serviceData, CoreSystemServerImpl coreSystemServerImpl) {
        this.mContext = coreSystemServerImpl.getApplicationContext();
        this.mServiceData = serviceData;
        this.mCoreSystemServerImpl = coreSystemServerImpl;
    }

    public ServiceData getServiceData() {
        return this.mServiceData;
    }

    public void startServiceIfNeeded() {
        if (this.mServiceData.isModeStart().booleanValue() && this.mServiceState != 0) {
            Logger.debug("Do not start coreService again if coreService is configed START not BIND, serviceData:" + this.mServiceData.toString());
            return;
        }
        int i = this.mServiceState;
        if (i == 0 || i == 8 || i == 7) {
            startService(this.mServiceData.getDelay());
        }
    }

    public synchronized void startService(long delay) {
        if (4 != getServiceState() && 10 != getServiceState() && 2 != getServiceState() && 3 != getServiceState() && 5 != getServiceState() && 1 != getServiceState() && 6 != getServiceState() && 9 != getServiceState()) {
            if (this.mServiceData.getOneShot() && OneShotEventUtil.getInstance().isShoted(this.mServiceData.getServiceName())) {
                Logger.debug("This CoreSerivce is oneshot and is already shoted! serviceName:" + this.mServiceData.getServiceName());
                setServiceState(5);
                return;
            }
            if (!isStrongDependServicePrepared()) {
                Logger.debug("CoreServer dependency not prepared, name:" + this.mServiceData.getServiceName());
                return;
            }
            if (!UserUnlockUtil.getInstance().isUserUnlock() && !this.mServiceData.isDirectBoot()) {
                Logger.debug("CoreServer User is not unlocked and this service is not directBoot, name:" + this.mServiceData.getServiceName());
                return;
            }
            setServiceState(1);
            this.mCoreSystemServerImpl.getThreadHandler().postDelayed(new Runnable() { // from class: com.core.system.service.utils.-$$Lambda$ServiceDataManager$YUuOM3qCnDBmfy0FmV6r45PTwwA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$startService$0$ServiceDataManager();
                }
            }, delay);
        }
    }

    public boolean isServiceReady() {
        return this.isServiceReady;
    }

    public void setServiceReady(boolean readyState) {
        this.isServiceReady = readyState;
        callbackServiceStateIfNeeded();
    }

    public void callbackServiceStateIfNeeded() {
        if (8 == this.mServiceState) {
            this.mCoreSystemServerImpl.onServiceStopped(this);
            return;
        }
        if (!isServiceReady()) {
            Logger.debug("checkServiceStartStopState, service not ready yet, name:" + this.mServiceData.getServiceName());
            return;
        }
        int i = this.mServiceState;
        if (4 == i || 2 == i || 3 == i) {
            this.mCoreSystemServerImpl.onServiceStarted(this);
        }
    }

    private boolean initCoreService(Context context, ICoreAdminService coreAdminService) {
        if (this.mCoreService != null) {
            Logger.debug("initCoreService , mCoreService already inited, name:" + this.mServiceData.getServiceName());
            return false;
        }
        try {
            ICoreService iCoreService = (ICoreService) Class.forName(this.mServiceData.getServiceClassName()).newInstance();
            this.mCoreService = iCoreService;
            iCoreService.init(context, coreAdminService);
            return true;
        } catch (ClassNotFoundException ex) {
            Logger.error("initCoreService error", ex);
            this.mCoreService = null;
            return false;
        } catch (Throwable ex2) {
            Logger.error("initCoreService error", ex2);
            this.mCoreService = null;
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startServiceUncheck, reason: merged with bridge method [inline-methods] */
    public void lambda$startService$0$ServiceDataManager() {
        if (this.mServiceData.isExternalService()) {
            startExternalService();
        } else {
            startInternalService();
        }
    }

    private void startInternalService() {
        Logger.debug("startInternalService Start! data : " + this.mServiceData.toString());
        if (this.mServiceData.getOneShot() && OneShotEventUtil.getInstance().isShoted(this.mServiceData.getServiceName())) {
            Logger.info("This CoreSerivce is oneshot and is already shoted! serviceName:" + this.mServiceData.getServiceName());
            setServiceState(5);
            return;
        }
        initCoreService(this.mContext, this.mCoreSystemServerImpl.getCoreAdminService());
        if (this.mCoreService == null) {
            Logger.warning("startInternalService error, CoreServer is null,ServiceName:" + this.mServiceData.getServiceName() + "serviceClassName:" + this.mServiceData.getServiceClassName() + ", check prev log for bug reason!");
            setServiceState(7);
            addRetryTimes();
            return;
        }
        if (this.mServiceData.getOneShot()) {
            OneShotEventUtil.getInstance().markShoted(this.mServiceData.getServiceName());
        }
        if (this.mCoreService.getServiceBinder() != null) {
            setBinder(this.mCoreService.getServiceBinder());
        }
        setServiceState(3);
        Logger.debug("startInternalService Success data: " + this.mServiceData.toString());
    }

    public void releaseUserForegroundService() {
        if (!this.mServiceData.isForegroundUserConfig()) {
            Logger.debug("should not release because this service is not configed 'UserForeground'");
            return;
        }
        Logger.debug("release current UserForeground Service, ServiceData:" + this.mServiceData.toString());
        setServiceReady(false);
        setServiceState(9);
        setBinder(null);
        if (this.mServiceData.isExternalService()) {
            try {
                this.mContext.unbindService(this.mServiceConnection);
            } catch (Throwable th) {
            }
        } else {
            ICoreService iCoreService = this.mCoreService;
            if (iCoreService != null) {
                iCoreService.release(this.mContext, this.mCoreSystemServerImpl.getCoreAdminService());
            }
            this.mCoreService = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0022 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void setBinder(android.os.IBinder r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            android.os.IBinder r1 = r3.mBinder     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
            if (r1 == 0) goto L15
            android.os.IBinder r1 = r3.mBinder     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
            boolean r1 = r1.isBinderAlive()     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
            if (r1 != 0) goto L15
            android.os.IBinder r1 = r3.mBinder     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
            android.os.IBinder$DeathRecipient r2 = r3.deathRecipient     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
            r1.unlinkToDeath(r2, r0)     // Catch: java.lang.Throwable -> L16 java.lang.Exception -> L18
        L15:
            goto L1e
        L16:
            r4 = move-exception
            goto L45
        L18:
            r1 = move-exception
            java.lang.String r2 = "setBinder-unlinkToDeath exception, there may be no impact."
            com.ts.car.sdk.core.Logger.info(r2)     // Catch: java.lang.Throwable -> L16
        L1e:
            r3.mBinder = r4     // Catch: java.lang.Throwable -> L16
            if (r4 == 0) goto L43
            android.os.IBinder$DeathRecipient r1 = r3.deathRecipient     // Catch: java.lang.Throwable -> L28
            r4.linkToDeath(r1, r0)     // Catch: java.lang.Throwable -> L28
            goto L43
        L28:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L16
            r1.<init>()     // Catch: java.lang.Throwable -> L16
            java.lang.String r2 = "ServiceData linkToDeath error, name:"
            r1.append(r2)     // Catch: java.lang.Throwable -> L16
            com.core.system.service.data.ServiceData r2 = r3.mServiceData     // Catch: java.lang.Throwable -> L16
            java.lang.String r2 = r2.getServiceName()     // Catch: java.lang.Throwable -> L16
            r1.append(r2)     // Catch: java.lang.Throwable -> L16
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L16
            com.ts.car.sdk.core.Logger.warning(r1, r0)     // Catch: java.lang.Throwable -> L16
        L43:
            monitor-exit(r3)
            return
        L45:
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.core.system.service.utils.ServiceDataManager.setBinder(android.os.IBinder):void");
    }

    private void startExternalService() {
        ComponentName componentName;
        if (this.mServiceData.isAccessibilityService()) {
            Utils.openAccessibilityService(this.mContext, this.mServiceData.getServicePackage(), this.mServiceData.getServiceClassName());
        }
        try {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(this.mServiceData.getComponent())) {
                componentName = new ComponentName(this.mServiceData.getServicePackage(), this.mServiceData.getServiceClassName());
            } else {
                componentName = ComponentName.unflattenFromString(this.mServiceData.getComponent());
            }
            intent.setComponent(componentName);
            if (this.mServiceData.getOneShot() && OneShotEventUtil.getInstance().isShoted(this.mServiceData.getServiceName())) {
                Logger.info("This CoreSerivce is oneshot and is already shoted! name:" + this.mServiceData.getServiceName());
                setServiceState(5);
                return;
            }
            boolean bindSuccess = this.mContext.bindService(intent, this.mServiceConnection, 1);
            StringBuilder sb = new StringBuilder();
            sb.append("startExternalService bindService : ");
            sb.append(componentName == null ? "null" : componentName.toString());
            sb.append(" bindSuccess:");
            sb.append(bindSuccess);
            Logger.warning(sb.toString());
            if (bindSuccess && this.mServiceData.getOneShot()) {
                OneShotEventUtil.getInstance().markShoted(this.mServiceData.getServiceName());
            }
            if (!bindSuccess) {
                Logger.warning("bindServiceError, serviceData:" + toString());
                setServiceState(7);
                addRetryTimes();
            }
        } catch (Exception ex) {
            Logger.warning("startExternalService bindService error!", ex);
            setServiceState(7);
            addRetryTimes();
        }
    }

    private synchronized void addRetryTimes() {
        if (getServiceData().getMaxRetryTimes() > 0) {
            int i = this.retryStartServiceTimes + 1;
            this.retryStartServiceTimes = i;
            if (i >= this.mServiceData.getMaxRetryTimes()) {
                setServiceState(10);
                Logger.warning("start service for max times, service cannot start normally, check previous log for more detail. serviceData:" + getServiceData());
            }
        }
    }

    public void setServiceState(int serviceStatestate) {
        this.mServiceState = serviceStatestate;
        callbackServiceStateIfNeeded();
    }

    public boolean isServiceStarted() {
        int i = this.mServiceState;
        return i == 4 || i == 3 || i == 2;
    }

    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    public void setComponentName(ComponentName componentName) {
        this.mComponentName = componentName;
    }

    public int getServiceState() {
        return this.mServiceState;
    }

    public String getStateStr() {
        switch (this.mServiceState) {
            case 0:
                return "SERVICE_STATE_NORMAL_" + this.mServiceState;
            case 1:
                return "SERVICE_STATE_BINDING_" + this.mServiceState;
            case 2:
                return "SERVICE_STATE_BINDED_" + this.mServiceState;
            case 3:
                return "SERVICE_STATE_STARTED_" + this.mServiceState;
            case 4:
                return "SERVICE_STATE_ADDED_" + this.mServiceState;
            case 5:
                return "SERVICE_STATE_ONESHOTE_" + this.mServiceState;
            case 6:
                return "SERVICE_STATE_FAKE_" + this.mServiceState;
            case 7:
                return "SERVICE_STATE_FAILED" + this.mServiceState;
            case 8:
                return "SERVICE_STATE_STOPPED_" + this.mServiceState;
            case SERVICE_STATE_UNFIT_USERCONFIG /* 9 */:
                return "SERVICE_STATE_UNFIT_USERCONFIG" + this.mServiceState;
            case 10:
                return "SERVICE_STATE_MAX_RETRY_TIMES" + this.mServiceState;
            default:
                return "SERVICE_STATE_ILLEGAL_" + this.mServiceState;
        }
    }

    public IBinder getServiceBinder() {
        return this.mBinder;
    }

    public boolean isStrongDependServicePrepared() {
        if (this.mStrongDependServiceDataList.size() == 0) {
            Logger.debug("isDependServicePrepared, no depend service, thisService:" + this.mServiceData.getServiceName());
            return true;
        }
        boolean res = true;
        for (ServiceDataManager serviceDataManager : this.mStrongDependServiceDataList) {
            res &= serviceDataManager.isServiceReady();
        }
        Logger.debug("isDependServicePrepared, result:" + res + ", thisService:" + this.mServiceData.getServiceName());
        return res;
    }

    public void notifyServiceReady(String serviceName, IBinder binder) {
        Logger.debug("notifyServiceReady, readyService:" + serviceName + ", thisService:" + this.mServiceData.getServiceName());
        startService(0L);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mState=");
        stringBuffer.append(getStateStr());
        stringBuffer.append(" ");
        stringBuffer.append("isServiceReady=");
        stringBuffer.append(this.isServiceReady);
        stringBuffer.append(" ");
        stringBuffer.append("mBinder=");
        Object obj = this.mBinder;
        if (obj == null) {
            obj = "null";
        }
        stringBuffer.append(obj);
        stringBuffer.append(" ");
        stringBuffer.append("retryStartServiceTimes=");
        stringBuffer.append(this.retryStartServiceTimes);
        stringBuffer.append(" ");
        stringBuffer.append(this.mServiceData.toString());
        return stringBuffer.toString();
    }

    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(toString());
        stringBuffer.append(" start dump dependService {");
        for (ServiceDataManager serviceDataManager : this.mStrongDependServiceDataList) {
            stringBuffer.append("StrongDependService = ");
            stringBuffer.append(serviceDataManager.getServiceData().getServiceName());
            stringBuffer.append(" ");
        }
        stringBuffer.append("}");
        writer.println(stringBuffer.toString());
        if (this.mBinder != null) {
            try {
                writer.println("  binder dump --: " + this.mBinder);
                this.mBinder.dump(fd, args);
            } catch (Throwable exception) {
                Logger.warning("dumpBinder error", exception);
            }
        }
        writer.println();
    }
}
