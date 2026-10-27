package com.ts.car.sdk.firewall;

import android.content.Context;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.car.common.service.SimpleServiceBase;
import com.ts.car.core.annotation.PlatformConstruction;
import com.ts.car.core.annotation.PlatformService;
import com.ts.firewall.service.IFirewallServiceInterface;
import com.ts.firewall.service.IFirewallServiceListener;
import com.ts.firewall.service.IFirewallServiceResponse;
import com.ts.firewall.service.data.ResultCode;
import com.ts.firewall.service.data.ResultType;
import java.util.ArrayList;
import java.util.List;
import ts.car.service.firewall.IFirewallListener;
import ts.car.service.firewall.IFirewallManager;
import ts.car.service.firewall.IFirewallResponse;
import ts.car.service.firewall.data.ApplicationRule;
import ts.car.service.firewall.data.FirewallData;

/* JADX INFO: loaded from: classes.dex */
@PlatformService
public class PlatformFirewallManager extends SimpleServiceBase implements IFirewallManager {
    private static final int PRE_SET_RULES = 0;
    private static final String REMOTE_SERVICE_NAME = "FirewallService";
    private static final int REQUEST_SERIAL = 1;
    private static final String SERVICE_COMPONENT = "com.ts.firewall.service/.FirewallService";
    private static final String TAG = PlatformFirewallManager.class.getSimpleName();
    private static final int USER_SET_RULES = 1;
    public IFirewallServiceListener iFirewallServiceListener;
    public IFirewallServiceResponse iFirewallServiceResponse;
    private FirewallHandler mHandler;
    private HandlerThread mHandlerThread;
    private final List<IFirewallListener> mIFirewallListeners;
    private final List<IFirewallResponse> mIFirewallResponses;
    private Object mLock;
    private IFirewallServiceInterface mService;

    @PlatformConstruction
    public PlatformFirewallManager(Context context, IBinder binder) {
        super(context, SERVICE_COMPONENT, REMOTE_SERVICE_NAME, binder);
        this.mIFirewallListeners = new ArrayList();
        this.mIFirewallResponses = new ArrayList();
        this.mLock = new Object();
        this.iFirewallServiceListener = new IFirewallServiceListener.Stub() { // from class: com.ts.car.sdk.firewall.PlatformFirewallManager.1
            @Override // com.ts.firewall.service.IFirewallServiceListener
            public void onFirewallStatusChange(boolean state) {
                Log.d(PlatformFirewallManager.TAG, "onFirewallStatusChange: " + state);
                for (int i = 0; i < PlatformFirewallManager.this.mIFirewallListeners.size(); i++) {
                    ((IFirewallListener) PlatformFirewallManager.this.mIFirewallListeners.get(i)).onFirewallStatusChange(Boolean.valueOf(state));
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceListener
            public void onWhiteListStatusChange(boolean enabled) {
                for (int i = 0; i < PlatformFirewallManager.this.mIFirewallListeners.size(); i++) {
                    ((IFirewallListener) PlatformFirewallManager.this.mIFirewallListeners.get(i)).onWhiteListStatusChange(enabled);
                }
            }
        };
        this.iFirewallServiceResponse = new IFirewallServiceResponse.Stub() { // from class: com.ts.car.sdk.firewall.PlatformFirewallManager.2
            @Override // com.ts.firewall.service.IFirewallServiceResponse
            public void onRequestFinished(ResultType type, ResultCode code) {
                for (int i = 0; i < PlatformFirewallManager.this.mIFirewallResponses.size(); i++) {
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceResponse
            public void onGetRuleResponse(int serial, int segmentNum, int index, String rule) {
                Log.d(PlatformFirewallManager.TAG, "onGetRuleResponse" + rule);
                for (int i = 0; i < PlatformFirewallManager.this.mIFirewallResponses.size(); i++) {
                    ((IFirewallResponse) PlatformFirewallManager.this.mIFirewallResponses.get(i)).onGetRuleResponse(serial, segmentNum, index, rule);
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceResponse
            public void onGetLogResponse(int serial, int segmentNum, int index, String log) {
            }
        };
        Log.d(TAG, "PlatformFirewallManager Construction");
    }

    @Override // ts.car.service.IPlatformService
    public String serviceName() {
        return "TS-Firewall_4.5.0.0";
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceConnected(IBinder iBinder) {
        Log.d(TAG, "onServiceConnected.");
        IFirewallServiceInterface iFirewallServiceInterfaceAsInterface = IFirewallServiceInterface.Stub.asInterface(iBinder);
        this.mService = iFirewallServiceInterfaceAsInterface;
        try {
            iFirewallServiceInterfaceAsInterface.registerCallback(this.iFirewallServiceListener, this.iFirewallServiceResponse);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    @Override // com.ts.car.common.service.SimpleServiceBase
    protected void onServiceDied() {
        Log.d(TAG, "onServiceDied.");
        this.mService = null;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean registerListener(IFirewallListener listener) throws RemoteException {
        Log.d(TAG, "registerListener:" + listener);
        boolean ret = false;
        if (listener != null) {
            synchronized (this.mIFirewallListeners) {
                if (!this.mIFirewallListeners.contains(listener)) {
                    ret = this.mIFirewallListeners.add(listener);
                }
            }
        }
        return ret;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean unregisterListener(IFirewallListener listener) throws RemoteException {
        Log.d(TAG, "unregisterListener:" + listener);
        boolean ret = false;
        synchronized (this.mIFirewallListeners) {
            if (this.mIFirewallListeners.contains(listener)) {
                ret = this.mIFirewallListeners.remove(listener);
            }
        }
        return ret;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean registerResponse(IFirewallResponse response) throws RemoteException {
        Log.d(TAG, "registerResponse:" + response);
        boolean ret = false;
        if (response != null) {
            synchronized (this.mIFirewallResponses) {
                if (!this.mIFirewallResponses.contains(response)) {
                    ret = this.mIFirewallResponses.add(response);
                }
            }
        }
        return ret;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean unregisterResponse(IFirewallResponse response) throws RemoteException {
        Log.d(TAG, "unregisterResponse:" + response);
        boolean ret = false;
        synchronized (this.mIFirewallResponses) {
            if (this.mIFirewallResponses.contains(response)) {
                ret = this.mIFirewallResponses.remove(response);
            }
        }
        return ret;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean isReady() throws RemoteException {
        Log.d(TAG, "isReady");
        return this.mService != null;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean setFirewallEnable(Boolean enabled) throws RemoteException {
        Log.d(TAG, "setFirewallEnable: " + enabled);
        if (isReady()) {
            this.mService.setFirewallEnable(enabled.booleanValue());
            return true;
        }
        Log.e(TAG, "firewall service is not ready!");
        return false;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean isFirewallEnabled() throws RemoteException {
        Log.d(TAG, "enter isFirewallEnabled");
        if (isReady()) {
            return this.mService.isFirewallEnabled();
        }
        Log.e(TAG, "firewall service is not ready()!");
        return false;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean setWhiteListEnable(Boolean enabled) throws RemoteException {
        Log.d(TAG, "setWhiteListEnable: " + enabled);
        if (isReady()) {
            this.mService.setWhiteListEnable(enabled.booleanValue());
            return true;
        }
        Log.e(TAG, "firewall service is not ready!");
        return false;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean isWhiteListEnabled() throws RemoteException {
        if (isReady()) {
            return this.mService.isWhiteListEnabled();
        }
        Log.e(TAG, "firewall service is not ready!");
        return false;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public List<String> getInterfaces() throws RemoteException {
        if (isReady()) {
            new ArrayList();
            List<String> interfaces = this.mService.getInterfaces();
            return interfaces;
        }
        return null;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean getRules(int serial, int type) throws RemoteException {
        if (isReady() && (type == 0 || type == 1)) {
            this.mService.getRules(serial, type);
            return true;
        }
        Log.e(TAG, "firewall service is not ready!");
        return false;
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean getLogs(StringBuilder res, long startTime, long endTime) throws RemoteException {
        Log.d(TAG, "getLogs");
        synchronized (this.mLock) {
            if (this.mService == null) {
                return false;
            }
            try {
                this.mService.getLogs(1, startTime, endTime);
            } catch (RemoteException exp) {
                exp.printStackTrace();
            }
            return true;
        }
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean addApplicationRule(Boolean isWhiteList, List<ApplicationRule> appRules) throws RemoteException {
        Log.d(TAG, "addApplicationRules");
        synchronized (this.mLock) {
            if (this.mService == null) {
                return false;
            }
            try {
            } catch (RemoteException exp) {
                exp.printStackTrace();
            }
            if (Utils.checkApplicationRule(appRules)) {
                this.mService.addApplicationRule(1, isWhiteList.booleanValue(), Utils.convertAppRuleToAidlArray(appRules));
                return true;
            }
            Log.d(TAG, "unvalid application rule!");
            return false;
        }
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean removeApplicationRule(Boolean isWhiteList, List<Integer> uids, List<String> packageNames) throws RemoteException {
        Log.d(TAG, "removeApplicationRules");
        synchronized (this.mLock) {
            if (this.mService == null) {
                return false;
            }
            try {
                List<FirewallData> uidsData = new ArrayList<>();
                for (int i = 0; i < uids.size(); i++) {
                    FirewallData data = new FirewallData(uids.get(i).intValue());
                    uidsData.add(data);
                }
                this.mService.removeApplicationRule(1, isWhiteList.booleanValue(), Utils.convertFirewallDataArrayToAidl(uidsData), packageNames);
            } catch (RemoteException exp) {
                exp.printStackTrace();
            }
            return true;
        }
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean addIpRule(Boolean isWhiteList, List<String> ipaddress, List<String> iface) throws RemoteException {
        Log.d(TAG, "addIpRules");
        synchronized (this.mLock) {
            if (this.mService == null) {
                return false;
            }
            try {
                this.mService.addIpRule(1, isWhiteList.booleanValue(), ipaddress, iface);
            } catch (RemoteException exp) {
                exp.printStackTrace();
            }
            return true;
        }
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean removeIpRule(Boolean isWhiteList, List<String> ipaddress, List<String> iface) throws RemoteException {
        Log.d(TAG, "removeIpRules");
        synchronized (this.mLock) {
            if (this.mService == null) {
                return false;
            }
            try {
                this.mService.removeIpRule(1, isWhiteList.booleanValue(), ipaddress, iface);
            } catch (RemoteException exp) {
                exp.printStackTrace();
            }
            return true;
        }
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean addPortRule(Boolean isWhiteList, List<Integer> port, List<String> iface) throws RemoteException {
        Log.d(TAG, "addPortRules");
        synchronized (this.mLock) {
            if (this.mService == null) {
                return false;
            }
            try {
                List<FirewallData> portsdata = new ArrayList<>();
                for (int i = 0; i < port.size(); i++) {
                    FirewallData data = new FirewallData(port.get(i).intValue());
                    portsdata.add(data);
                }
                this.mService.addPortRule(1, isWhiteList.booleanValue(), Utils.convertFirewallDataArrayToAidl(portsdata), iface);
            } catch (RemoteException exp) {
                exp.printStackTrace();
            }
            return true;
        }
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean removePortRule(Boolean isWhiteList, List<Integer> port, List<String> iface) throws RemoteException {
        Log.d(TAG, "removePortRules");
        synchronized (this.mLock) {
            if (this.mService == null) {
                return false;
            }
            try {
                List<FirewallData> portsdata = new ArrayList<>();
                for (int i = 0; i < port.size(); i++) {
                    FirewallData data = new FirewallData(port.get(i).intValue());
                    portsdata.add(data);
                }
                this.mService.removePortRule(1, isWhiteList.booleanValue(), Utils.convertFirewallDataArrayToAidl(portsdata), iface);
            } catch (RemoteException exp) {
                exp.printStackTrace();
            }
            return true;
        }
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean addUrlRule(Boolean isWhiteList, List<String> url) throws RemoteException {
        Log.d(TAG, "addUrlRules");
        synchronized (this.mLock) {
            if (this.mService == null) {
                return false;
            }
            try {
                this.mService.addUrlRule(1, isWhiteList.booleanValue(), url);
            } catch (RemoteException exp) {
                exp.printStackTrace();
            }
            return true;
        }
    }

    @Override // ts.car.service.firewall.IFirewallManager
    public boolean removeUrlRule(Boolean isWhiteList, List<String> url) throws RemoteException {
        Log.d(TAG, "removeUrlRule");
        synchronized (this.mLock) {
            if (this.mService == null) {
                return false;
            }
            try {
                this.mService.removeUrlRule(1, isWhiteList.booleanValue(), url);
            } catch (RemoteException exp) {
                exp.printStackTrace();
            }
            return true;
        }
    }
}
