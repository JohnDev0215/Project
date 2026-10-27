package com.ts.firewall.service.firewallhaladapter;

import android.os.RemoteException;
import com.ts.firewall.service.data.ApplicationRule;
import com.ts.firewall.service.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import vendor.ts.firewall.V1_0.IFirewallDaemon;
import vendor.ts.firewall.V1_0.IFirewallDaemonListener;
import vendor.ts.firewall.V1_0.IFirewallDaemonResponse;
import vendor.ts.firewall.V1_0.Url;

/* JADX INFO: loaded from: classes.dex */
public class FirewallHal implements FirewallHalInterface {
    private IFirewallDaemon mFirewallService = null;
    private FirewallHalListener mListener = null;
    private ServiceAction mServiceAction = new ServiceAction() { // from class: com.ts.firewall.service.firewallhaladapter.FirewallHal.1
        @Override // com.ts.firewall.service.firewallhaladapter.ServiceAction
        public boolean connectService() {
            return FirewallHal.this.registerFirewallService();
        }

        @Override // com.ts.firewall.service.firewallhaladapter.ServiceAction
        public boolean releaseService() {
            return FirewallHal.this.unRegisterFirewallService();
        }
    };
    private IFirewallDaemonResponse mFirewallResponse = new FirewallDaemonResponse();
    private IFirewallDaemonListener mFirewallListener = new FirewallDaemonListener();
    private BinderServiceConnection mServiceConnection = new BinderServiceConnection(this.mServiceAction);

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public void init(FirewallHalListener listener) {
        LogUtil.debug("init start");
        this.mListener = listener;
        this.mServiceConnection.init();
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public void unit() {
        LogUtil.debug("unInit start");
        if (this.mFirewallService != null) {
            this.mServiceConnection.unInit();
            this.mFirewallService = null;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean setFirewallEnable(boolean enable) {
        IFirewallDaemon iFirewallDaemon = this.mFirewallService;
        if (iFirewallDaemon == null) {
            LogUtil.warn("enable mFirewallService is null", new Exception());
            return false;
        }
        try {
            boolean res = iFirewallDaemon.setFirewallEnable(enable);
            if (!res) {
                LogUtil.warn("mFirewallService.setFirewallEnable get false, enable:" + enable);
            }
            return res;
        } catch (Exception ex) {
            LogUtil.warn("setFirewallEnable error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean isFirewallEnabled() {
        IFirewallDaemon iFirewallDaemon = this.mFirewallService;
        if (iFirewallDaemon == null) {
            LogUtil.warn("isFirewallEnabled mFirewallService is null", new Exception());
            return false;
        }
        try {
            boolean res = iFirewallDaemon.isFirewallEnabled();
            if (!res) {
                LogUtil.warn("mFirewallService.isFirewallEnabled get false");
            }
            return res;
        } catch (Exception ex) {
            LogUtil.warn("isFirewallEnabled error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean setWhiteListEnable(boolean enable) {
        IFirewallDaemon iFirewallDaemon = this.mFirewallService;
        if (iFirewallDaemon == null) {
            LogUtil.warn("setWhiteListEnable mFirewallService is null", new Exception());
            return false;
        }
        try {
            boolean res = iFirewallDaemon.setWhiteListEnable(enable);
            if (!res) {
                LogUtil.warn("mFirewallService.setWhiteListEnable get false");
            }
            return res;
        } catch (Exception ex) {
            LogUtil.warn("setWhiteListEnable error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean isWhiteListEnabled() {
        IFirewallDaemon iFirewallDaemon = this.mFirewallService;
        if (iFirewallDaemon == null) {
            LogUtil.warn("isWhiterListEnabled mFirewallService is null", new Exception());
            return false;
        }
        try {
            boolean res = iFirewallDaemon.isWhiteListEnabled();
            if (!res) {
                LogUtil.warn("mFirewallService.isWhiterListEnabled get false");
            }
            return res;
        } catch (Exception ex) {
            LogUtil.warn("isWhiterListEnabled error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public ArrayList<String> getInterfaces() {
        IFirewallDaemon iFirewallDaemon = this.mFirewallService;
        if (iFirewallDaemon == null) {
            LogUtil.warn("getInterfaces mFirewallService is null", new Exception());
            return null;
        }
        try {
            ArrayList<String> res = iFirewallDaemon.getInterfaces();
            if (res == null) {
                LogUtil.warn("mFirewallService.getInterfaces get false");
            }
            return res;
        } catch (Exception ex) {
            LogUtil.warn("getInterfaces error!", ex);
            return null;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean getRules(int serial, int type) {
        IFirewallDaemon iFirewallDaemon = this.mFirewallService;
        if (iFirewallDaemon == null) {
            LogUtil.warn("getRules mFirewallService is null", new Exception());
            return false;
        }
        try {
            iFirewallDaemon.getRules(serial, type);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("getRules error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean getLogs(int serial, long startTime, long endTime) {
        IFirewallDaemon iFirewallDaemon = this.mFirewallService;
        if (iFirewallDaemon == null) {
            LogUtil.warn("getLogs mFirewallService is null", new Exception());
            return false;
        }
        try {
            iFirewallDaemon.getLogs(serial, startTime, endTime);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("getLogs error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean addApplicationRule(int serial, boolean isWhiteList, List<ApplicationRule> uidRuls) {
        if (this.mFirewallService == null) {
            LogUtil.warn("addApplicationRule mFirewallService is null", new Exception());
            return false;
        }
        try {
            ArrayList<vendor.ts.firewall.V1_0.ApplicationRule> tempUidRuls = new ArrayList<>();
            for (int i = 0; i < uidRuls.size(); i++) {
                vendor.ts.firewall.V1_0.ApplicationRule tempApplicationRule = new vendor.ts.firewall.V1_0.ApplicationRule();
                tempApplicationRule.iface = uidRuls.get(i).mIface;
                tempApplicationRule.packageName = uidRuls.get(i).packageName == null ? "" : uidRuls.get(i).packageName;
                tempApplicationRule.uid = uidRuls.get(i).mUid;
                ArrayList<Url> tempUrls = new ArrayList<>();
                for (int j = 0; j < uidRuls.get(i).urlArray.size(); j++) {
                    Url tempUrl = new Url();
                    tempUrl.domain = uidRuls.get(i).urlArray.get(j).mDomain;
                    tempUrl.port = uidRuls.get(i).urlArray.get(j).mPort;
                    tempUrls.add(tempUrl);
                }
                tempApplicationRule.url = tempUrls;
                tempUidRuls.add(tempApplicationRule);
            }
            this.mFirewallService.addApplicationRule(serial, isWhiteList, tempUidRuls);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("addApplicationRule error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean removeApplicationRule(int serial, boolean isWhiteList, List<Integer> uids) {
        if (this.mFirewallService == null) {
            LogUtil.warn("removeApplicationRule mFirewallService is null", new Exception());
            return false;
        }
        try {
            ArrayList<Integer> tempUids = new ArrayList<>();
            tempUids.addAll(uids);
            this.mFirewallService.removeApplicationRule(serial, isWhiteList, tempUids);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("removeApplicationRule error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean addIpRule(int serial, boolean isWhiteList, List<String> ipaddress, List<String> ifaces) {
        if (this.mFirewallService == null) {
            LogUtil.warn("addIpRule mFirewallService is null", new Exception());
            return false;
        }
        try {
            ArrayList<String> tempIpaddress = new ArrayList<>();
            tempIpaddress.addAll(ipaddress);
            ArrayList<String> tempIfaces = new ArrayList<>();
            tempIfaces.addAll(ifaces);
            this.mFirewallService.addIpRule(serial, isWhiteList, tempIpaddress, tempIfaces);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("addIpRule error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean removeIpRule(int serial, boolean isWhiteList, List<String> ipaddress, List<String> ifaces) {
        if (this.mFirewallService == null) {
            LogUtil.warn("removeIpRule mFirewallService is null", new Exception());
            return false;
        }
        try {
            ArrayList<String> tempIpaddress = new ArrayList<>();
            tempIpaddress.addAll(ipaddress);
            ArrayList<String> tempIfaces = new ArrayList<>();
            tempIfaces.addAll(ifaces);
            this.mFirewallService.removeIpRule(serial, isWhiteList, tempIpaddress, tempIfaces);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("removeIpRule error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean addPortRule(int serial, boolean isWhiteList, List<Integer> port, List<String> iface) {
        if (this.mFirewallService == null) {
            LogUtil.warn("addPortRule mFirewallService is null", new Exception());
            return false;
        }
        try {
            ArrayList<Integer> tempPort = new ArrayList<>();
            tempPort.addAll(port);
            ArrayList<String> tempIfaces = new ArrayList<>();
            tempIfaces.addAll(iface);
            this.mFirewallService.addPortRule(serial, isWhiteList, tempPort, tempIfaces);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("addPortRule error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean removePortRule(int serial, boolean isWhiteList, List<Integer> port, List<String> iface) {
        if (this.mFirewallService == null) {
            LogUtil.warn("removePortRule mFirewallService is null", new Exception());
            return false;
        }
        try {
            ArrayList<Integer> tempPort = new ArrayList<>();
            tempPort.addAll(port);
            ArrayList<String> tempIfaces = new ArrayList<>();
            tempIfaces.addAll(iface);
            this.mFirewallService.removePortRule(serial, isWhiteList, tempPort, tempIfaces);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("removePortRule error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean addUrlRule(int serial, boolean isWhiteList, List<String> url) {
        if (this.mFirewallService == null) {
            LogUtil.warn("addUrlRule mFirewallService is null", new Exception());
            return false;
        }
        try {
            ArrayList<String> tempUrl = new ArrayList<>();
            tempUrl.addAll(url);
            this.mFirewallService.addUrlRule(serial, isWhiteList, tempUrl);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("addUrlRule error!", ex);
            return false;
        }
    }

    @Override // com.ts.firewall.service.firewallhaladapter.FirewallHalInterface
    public boolean removeUrlRule(int serial, boolean isWhiteList, List<String> url) {
        if (this.mFirewallService == null) {
            LogUtil.warn("removeUrlRule mFirewallService is null", new Exception());
            return false;
        }
        try {
            ArrayList<String> tempUrl = new ArrayList<>();
            tempUrl.addAll(url);
            this.mFirewallService.removeUrlRule(serial, isWhiteList, tempUrl);
            return true;
        } catch (Exception ex) {
            LogUtil.warn("removeUrlRule error!", ex);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean registerFirewallService() {
        try {
            try {
                LogUtil.warn("registerFirewallService start");
                this.mFirewallService = IFirewallDaemon.getService();
                LogUtil.warn("registerFirewallService end");
                if (this.mFirewallService != null) {
                    try {
                        this.mFirewallService.registerCallback(this.mFirewallListener, this.mFirewallResponse);
                    } catch (Exception ex) {
                        LogUtil.warn("mFirewallService.registerCallback error", ex);
                    }
                    if (this.mServiceConnection.init()) {
                        this.mServiceConnection.onGetBinder(this.mFirewallService);
                        return true;
                    }
                    return false;
                }
                LogUtil.warn("mFirewallService.getService() is null");
                return false;
            } catch (RemoteException ex2) {
                LogUtil.warn("Firewall registerFirewallService error ", ex2);
                return false;
            } catch (NoSuchElementException ex3) {
                LogUtil.warn("IFirewall.getService error, Service not exist", ex3);
                return false;
            }
        } catch (Exception ex4) {
            LogUtil.warn("Firewall registerFirewallService may not exist", ex4);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean unRegisterFirewallService() {
        LogUtil.debug("FirewallService released");
        this.mFirewallService = null;
        return true;
    }

    public class FirewallDaemonResponse extends IFirewallDaemonResponse.Stub {
        public FirewallDaemonResponse() {
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemonResponse
        public void onRequestFinished(int type, int code) {
            LogUtil.debug("onRequestFinished type:" + type + " code:" + code);
            FirewallHal.this.mListener.onRequestFinished(type, code);
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemonResponse
        public void onGetRuleResponse(int serial, int segmentNum, int index, String rule) {
            FirewallHal.this.mListener.onGetRuleResponse(serial, segmentNum, index, rule);
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemonResponse
        public void onGetLogResponse(int serial, int segmentNum, int index, String log) {
            FirewallHal.this.mListener.onGetLogResponse(serial, segmentNum, index, log);
        }
    }

    public class FirewallDaemonListener extends IFirewallDaemonListener.Stub {
        public FirewallDaemonListener() {
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemonListener
        public void onFirewallStatusChange(boolean state) {
            LogUtil.debug("onFirewallStatusChange state:" + state);
            FirewallHal.this.mListener.onFirewallStatusChange(state);
        }

        @Override // vendor.ts.firewall.V1_0.IFirewallDaemonListener
        public void onWhiteListStatusChange(boolean enabled) {
            LogUtil.debug("onWhiteListStatusChange enabled:" + enabled);
            FirewallHal.this.mListener.onWhiteListStatusChange(enabled);
        }
    }
}
