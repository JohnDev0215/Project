package com.ts.firewall.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import com.ts.firewall.service.IFirewallServiceInterface;
import com.ts.firewall.service.data.ApplicationRule;
import com.ts.firewall.service.data.FirewallData;
import com.ts.firewall.service.firewallhaladapter.FirewallHal;
import com.ts.firewall.service.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FirewallService extends Service {
    private static final String FIREWALL_PERMISSION = "car.permission.FIREWALL_ACCESS";
    private static final String MSG_PERMISSION_ERROR = "permission check error for firewall permission";
    private FirewallServiceCallback mFirewallServiceCallback;
    private PackageManager mPm;
    private IBinder myBind;
    private FirewallHal mFirewallHal = null;
    private final Object mCallbackListLock = new Object();
    private final Object mInitLock = new Object();
    private RemoteCallbackList<IFirewallServiceListener> mFirewallServiceListenerList = new RemoteCallbackList<>();
    private RemoteCallbackList<IFirewallServiceResponse> mFirewallServiceResponseList = new RemoteCallbackList<>();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.myBind;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkFirewallPermission() {
        enforceCallingOrSelfPermission("car.permission.FIREWALL_ACCESS", MSG_PERMISSION_ERROR);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.myBind = new FirewallBinder();
        this.mFirewallHal = new FirewallHal();
        FirewallServiceCallback firewallServiceCallback = new FirewallServiceCallback(this.mFirewallServiceListenerList, this.mFirewallServiceResponseList, this.mCallbackListLock);
        this.mFirewallServiceCallback = firewallServiceCallback;
        this.mFirewallHal.init(firewallServiceCallback);
        this.mPm = getPackageManager();
        Log.d("FirewallService", "on create");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.mFirewallServiceListenerList.kill();
        this.mFirewallServiceResponseList.kill();
        this.mFirewallHal = null;
        super.onDestroy();
    }

    class FirewallBinder extends IFirewallServiceInterface.Stub {
        FirewallBinder() {
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean registerCallback(IFirewallServiceListener listener, IFirewallServiceResponse response) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            LogUtil.debug("service registerCallback");
            if (listener != null && response != null) {
                try {
                    synchronized (FirewallService.this.mCallbackListLock) {
                        boolean resultBoolean = FirewallService.this.mFirewallServiceListenerList.register(listener);
                        if (resultBoolean) {
                            boolean resultBoolean2 = FirewallService.this.mFirewallServiceResponseList.register(response);
                            if (!resultBoolean2) {
                                LogUtil.warn("register mFirewallServiceResponseList: register failed.");
                                return false;
                            }
                            return true;
                        }
                        LogUtil.warn("register mFirewallServiceListenerList: register failed.");
                        return false;
                    }
                } catch (Exception exception) {
                    LogUtil.warn("registerCallback error!", exception);
                    return true;
                }
            }
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean setFirewallEnable(boolean enable) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.setFirewallEnable(enable);
                if (!res) {
                    LogUtil.warn("FirewallService.setFirewallEnable  enable:" + enable);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("setFirewallEnable error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean isFirewallEnabled() throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.isFirewallEnabled();
                return res;
            } catch (Exception exception) {
                LogUtil.warn("isFirewallEnabled error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean setWhiteListEnable(boolean enable) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.setWhiteListEnable(enable);
                if (!res) {
                    LogUtil.warn("FirewallService.setWhiteListEnable  enable:" + enable);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("setWhiteListEnable error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean isWhiteListEnabled() throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.isWhiteListEnabled();
                return res;
            } catch (Exception exception) {
                LogUtil.warn("isWhiteListEnabled error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public List<String> getInterfaces() throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                List<String> res = FirewallService.this.mFirewallHal.getInterfaces();
                return res;
            } catch (Exception exception) {
                LogUtil.warn("getInterfaces error!", exception);
                return null;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean getRules(int serial, int type) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.getRules(serial, type);
                if (!res) {
                    LogUtil.warn("FirewallService.getRules  serial:" + serial + " type:" + type);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("getRules error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean getLogs(int serial, long startTime, long endTime) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.getLogs(serial, startTime, endTime);
                if (!res) {
                    LogUtil.warn("FirewallService.getLogs  serial:" + serial + " startTime:" + startTime + " endTime:" + endTime);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("getLogs error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean addApplicationRule(int serial, boolean isWhiteList, List<ApplicationRule> uidRuls) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            for (int i = 0; i < uidRuls.size(); i++) {
                try {
                    if (uidRuls.get(i).getUid() <= 0) {
                        ApplicationInfo appInfo = FirewallService.this.mPm.getApplicationInfo(uidRuls.get(i).getPackageName(), 1);
                        uidRuls.get(i).setUid(appInfo.uid);
                    }
                } catch (Exception exception) {
                    LogUtil.warn("addApplicationRule error!", exception);
                    return false;
                }
            }
            boolean res = FirewallService.this.mFirewallHal.addApplicationRule(serial, isWhiteList, uidRuls);
            if (!res) {
                LogUtil.warn("FirewallService.addApplicationRule  serial:" + serial + " isWhiteList:" + isWhiteList);
            }
            return res;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean removeApplicationRule(int serial, boolean isWhiteList, List<FirewallData> uids, List<String> packageNames) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                List<Integer> tempUids = new ArrayList<>();
                for (int i = 0; i < uids.size(); i++) {
                    tempUids.add(Integer.valueOf(uids.get(i).mData));
                }
                for (int i2 = 0; i2 < packageNames.size(); i2++) {
                    ApplicationInfo appInfo = FirewallService.this.mPm.getApplicationInfo(packageNames.get(i2), 1);
                    tempUids.add(Integer.valueOf(appInfo.uid));
                }
                boolean res = FirewallService.this.mFirewallHal.removeApplicationRule(serial, isWhiteList, tempUids);
                if (!res) {
                    LogUtil.warn("FirewallService.removeApplicationRule  serial:" + serial + " isWhiteList:" + isWhiteList);
                }
                return res;
            } catch (Exception exception) {
                exception.printStackTrace();
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean addIpRule(int serial, boolean isWhiteList, List<String> ipaddress, List<String> ifaces) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.addIpRule(serial, isWhiteList, ipaddress, ifaces);
                if (!res) {
                    LogUtil.warn("FirewallService.addIpRule  serial:" + serial + " isWhiteList:" + isWhiteList);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("addIpRule error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean removeIpRule(int serial, boolean isWhiteList, List<String> ipaddress, List<String> ifaces) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.removeIpRule(serial, isWhiteList, ipaddress, ifaces);
                if (!res) {
                    LogUtil.warn("FirewallService.removeIpRule  serial:" + serial + " isWhiteList:" + isWhiteList);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("removeIpRule error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean addPortRule(int serial, boolean isWhiteList, List<FirewallData> port, List<String> iface) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                List<Integer> tempPort = new ArrayList<>();
                for (int i = 0; i < port.size(); i++) {
                    tempPort.add(Integer.valueOf(port.get(i).mData));
                }
                boolean res = FirewallService.this.mFirewallHal.addPortRule(serial, isWhiteList, tempPort, iface);
                if (!res) {
                    LogUtil.warn("FirewallService.addPortRule  serial:" + serial + " isWhiteList:" + isWhiteList);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("addPortRule error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean removePortRule(int serial, boolean isWhiteList, List<FirewallData> port, List<String> iface) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                List<Integer> tempPort = new ArrayList<>();
                for (int i = 0; i < port.size(); i++) {
                    tempPort.add(Integer.valueOf(port.get(i).mData));
                }
                boolean res = FirewallService.this.mFirewallHal.removePortRule(serial, isWhiteList, tempPort, iface);
                if (!res) {
                    LogUtil.warn("FirewallService.removePortRule  serial:" + serial + " isWhiteList:" + isWhiteList);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("removePortRule error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean addUrlRule(int serial, boolean isWhiteList, List<String> url) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.addUrlRule(serial, isWhiteList, url);
                if (!res) {
                    LogUtil.warn("FirewallService.addUrlRule  serial:" + serial + " isWhiteList:" + isWhiteList);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("addUrlRule error!", exception);
                return false;
            }
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean removeUrlRule(int serial, boolean isWhiteList, List<String> url) throws RemoteException {
            FirewallService.this.checkFirewallPermission();
            try {
                boolean res = FirewallService.this.mFirewallHal.removeUrlRule(serial, isWhiteList, url);
                if (!res) {
                    LogUtil.warn("FirewallService.removeUrlRule  serial:" + serial + " isWhiteList:" + isWhiteList);
                }
                return res;
            } catch (Exception exception) {
                LogUtil.warn("removeUrlRule error!", exception);
                return false;
            }
        }
    }
}
