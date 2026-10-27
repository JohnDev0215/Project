package com.ts.car.ncm.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.INetd;
import android.net.InterfaceConfiguration;
import android.net.IpConfiguration;
import android.net.LinkAddress;
import android.net.NetworkCapabilities;
import android.net.StaticIpConfiguration;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.INetworkManagementService;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.server.net.BaseNetworkObserver;
import com.ts.car.core.FoundationProvider;
import com.ts.car.ncm.R;
import com.ts.car.ncm.network.ExtNetworkBroadCast;
import com.ts.car.ncm.network.ExtNetworkFactory;
import com.ts.car.ncm.plugin.iface.PluginInterface;
import com.ts.car.ncm.plugin.iface.PluginListener;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import ts.car.ncm.network.IExtConnectivityServiceListener;
import ts.car.ncm.network.IfaceInfo;
import ts.car.ncm.network.IpConfiguration;

/* JADX INFO: loaded from: classes.dex */
public class ExtNetworkTracker {
    private static final int ADD = 1000;
    private static final int BANDWIDTH_100MB = 100000;
    private static final int BASE = 1000;
    private static final int CHANGE = 1001;
    private static final boolean DBG = true;
    private static final String ENABLE_IP_RULE = "1";
    private static final String ENABLE_IP_RULE_PROP = "vendor.ncm.addmainrule";
    private static final String EXT_NETWORK_STATE = "ExtNetworkState";
    private static final int IFACE_NAME = 0;
    private static final int IFACE_POS = 0;
    private static final int IFACE_STRATEGY = 1;
    private static final int IP_CONFIG_POS = 2;
    private static final int LINK_STATE_CHANGE = 1;
    private static final int MB = 1000;
    private static final int MOBILE_NETWORK_STATE_CHANGE = 2;
    private static final int NETWORK_CAPABILITIES_POS = 1;
    private static final int REMOVE = 1002;
    private static final int RETRY_SET_IFACE_TIMES = 3;
    private static final int SCORE_POS = 4;
    private static final int TRANSPORT_TYPE_POS = 3;
    private ExtNetworkBroadCast mBroadCast;
    private final Context mContext;
    private boolean mEnableIpRuleMain;
    private boolean mEnableSecurityLog;
    private final Handler mHandler;
    private boolean mIfaceDown;
    private String[] mIfaces;
    private PluginInterface mPlugin;
    private PluginListener mPluginListener;
    private SharedPreferences mPrefer;
    private static final String TAG = ExtNetworkTracker.class.getSimpleName();
    private static String[] sEventToString = {"INTERFACE ADD ", "INTERFACE CHANGE", "INTERFACE REMOVE"};
    private ConcurrentHashMap<String, ExtNetworkSet> mExtNetworkSets = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<IBinder, IExtConnectivityServiceListener> mListenersMap = new ConcurrentHashMap<>();
    private final HashMap<IBinder, ExtConSvcLtnDeathRecipient> mDeathRecipientMap = new HashMap<>();
    private final RemoteCallbackList<IExtConnectivityServiceListener> mListeners = new RemoteCallbackList<>();
    private final BaseNetworkObserver mObserver = new InterfaceObserver();
    private ConcurrentHashMap<String, Boolean> mStrategys = new ConcurrentHashMap<>();
    ExtNetworkFactory.LinkStateListener factoryListener = new ExtNetworkFactory.LinkStateListener() { // from class: com.ts.car.ncm.network.ExtNetworkTracker.3
        @Override // com.ts.car.ncm.network.ExtNetworkFactory.LinkStateListener
        public void onLinkStateChanged(String iface, boolean status) {
            Log.d(ExtNetworkTracker.TAG, "onLinkStateChanged:" + status + " iface:" + iface);
            ExtNetworkTracker.this.notifyAllListener(iface, status, 1);
        }

        @Override // com.ts.car.ncm.network.ExtNetworkFactory.LinkStateListener
        public void onMoibleNetworkStateChanged(String iface, boolean status) {
            Log.d(ExtNetworkTracker.TAG, " onMoibleNetworkStateChanged: " + status + " iface: " + iface);
            ExtNetworkTracker.this.notifyAllListener(iface, status, 2);
        }
    };
    private final INetworkManagementService mNetworkManagerService = INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));

    public boolean getEnableSecLogFlag() {
        return this.mEnableSecurityLog;
    }

    public static String ifaceEventString(int event) {
        if (event > REMOVE || event < 1000) {
            return "ERROR EVENT MESSAGE:" + event;
        }
        return sEventToString[event - 1000];
    }

    public class ExtNetworkSet {
        String mIfaceName;
        IpConfiguration mIpConfig;
        IfaceMonitor mMonitor;
        NetworkCapabilities mNetworkCapabilities;
        ExtNetworkFactory mNetworkFactory;
        int mNetworkType;
        int mScore;
        boolean needStaticIpConfig = true;
        boolean isIfaceExists = false;

        public ExtNetworkSet(int networkType, String iface, ExtNetworkFactory factory, NetworkCapabilities ncs, IpConfiguration ipconf, int score) {
            this.mNetworkType = networkType;
            this.mIfaceName = iface;
            this.mNetworkFactory = factory;
            this.mNetworkCapabilities = ncs;
            this.mIpConfig = ipconf;
            this.mScore = score;
            IfaceMonitor ifaceMonitorMakeIfaceMonitor = IfaceMonitor.makeIfaceMonitor(iface, ExtNetworkTracker.this.mHandler, ExtNetworkTracker.this);
            this.mMonitor = ifaceMonitorMakeIfaceMonitor;
            this.mNetworkFactory.setIfaceMonitor(ifaceMonitorMakeIfaceMonitor);
        }

        public void updateInterfaceConfig(boolean needStaticIpConfig) {
            this.needStaticIpConfig = needStaticIpConfig;
        }

        public void interfaceAdded() {
            Log.d(ExtNetworkTracker.TAG, this.mMonitor + "interfaceAdded");
            this.mMonitor.ifaceAdd();
        }

        public void interfaceRemoved() {
            Log.d(ExtNetworkTracker.TAG, this.mMonitor + " interfaceRemoved");
            this.mMonitor.ifaceRemove();
        }

        public void interfaceLinkStateChanged(boolean state) {
            Log.d(ExtNetworkTracker.TAG, this.mMonitor + " interfaceLinkStateChanged:" + state);
            this.mMonitor.ifaceLinkChange(state);
        }

        public IfaceMonitor getInterfaceMonitor() {
            return this.mMonitor;
        }

        public void allowConnectCs(boolean allow) {
            Log.d(ExtNetworkTracker.TAG, this.mMonitor + "allowConnectCs:" + allow);
            this.mMonitor.setAllowConnectCs(allow);
        }

        public void destory() {
            Log.d(ExtNetworkTracker.TAG, this.mMonitor + "destory");
            this.mNetworkFactory.updateInterfaceState(false);
            this.mMonitor.dispose();
            this.mNetworkFactory.destory();
        }

        public String toString() {
            return "ExtNetworkSet [mNetworkType=" + this.mNetworkType + ", mIfaceName=" + this.mIfaceName + ", mNetworkFactory=" + this.mNetworkFactory + ", mNetworkCapabilities=" + this.mNetworkCapabilities + ", needStaticIpConfig=" + this.needStaticIpConfig + ", mIpConfig=" + IpMask.getIpConfigurationString(this.mIpConfig) + ", isIfaceExists=" + this.isIfaceExists + ", mScore=" + this.mScore + ", mMonitor=" + this.mMonitor + "]";
        }
    }

    private void initBroadcast(Context context) {
        Log.i(TAG, "initBroadcast");
        ExtNetworkBroadCast extNetworkBroadCast = new ExtNetworkBroadCast(context);
        this.mBroadCast = extNetworkBroadCast;
        extNetworkBroadCast.registerBroadcastIface(new ExtNetworkBroadCast.BroadcastInterface() { // from class: com.ts.car.ncm.network.ExtNetworkTracker.1
            @Override // com.ts.car.ncm.network.ExtNetworkBroadCast.BroadcastInterface
            public void onNetworkConfigChanged(ExtNetworkBroadCast.DebugConfig config) {
                Log.i(ExtNetworkTracker.TAG, "onNetworkConfigChanged config:" + config);
                ExtNetworkSet set = (ExtNetworkSet) ExtNetworkTracker.this.mExtNetworkSets.get(config.getIface());
                if (set == null) {
                    Log.e(ExtNetworkTracker.TAG, "NetworkFactory not register " + config.getIface() + " failed");
                    return;
                }
                NetworkCapabilities originNcs = set.mNetworkCapabilities;
                if (originNcs == null) {
                    originNcs = new NetworkCapabilities();
                }
                IpConfiguration ipconfig = set.mIpConfig;
                if (config.getIpAddr() != null) {
                    ipconfig.staticIpConfiguration.ipAddress = new LinkAddress(config.getIpAddr());
                    Log.d(ExtNetworkTracker.TAG, "ipAddress:" + IpMask.getLinkAddressString(ipconfig.staticIpConfiguration.ipAddress));
                }
                if (config.getGateway() != null) {
                    ipconfig.staticIpConfiguration.gateway = InetAddress.parseNumericAddress(config.getGateway());
                    Log.d(ExtNetworkTracker.TAG, "gateway:" + IpMask.maskIpMiddle(ipconfig.staticIpConfiguration.gateway));
                }
                ArrayList<String> dnsServers = config.getDnsServers();
                if (dnsServers != null && !dnsServers.isEmpty()) {
                    ArrayList<InetAddress> dnsaadrs = ipconfig.staticIpConfiguration.dnsServers;
                    ArrayList<InetAddress> tmp = new ArrayList<>();
                    for (String dns : dnsServers) {
                        tmp.add(InetAddress.parseNumericAddress(dns));
                    }
                    if (!tmp.isEmpty()) {
                        dnsaadrs.clear();
                        for (int i = 0; i < tmp.size(); i++) {
                            dnsaadrs.add(tmp.get(i));
                        }
                    }
                }
                int[] caps = config.getNetcaps();
                if (caps != null) {
                    NetworkCapabilities.Builder builder = new NetworkCapabilities.Builder();
                    new NetworkCapabilities();
                    int[] trans = originNcs.getTransportTypes();
                    if (trans != null) {
                        for (int i2 : trans) {
                            builder.addTransportType(i2);
                        }
                    }
                    builder.removeCapability(13).removeCapability(14).removeCapability(15);
                    for (int i3 : caps) {
                        builder.addCapability(i3);
                    }
                    originNcs = builder.build();
                }
                ExtNetworkFactory factory = set.mNetworkFactory;
                factory.updateNetworkInfo(ipconfig.getStaticIpConfiguration().toLinkProperties(config.getIface()), originNcs);
            }
        });
    }

    public ExtNetworkTracker(Context context, Handler handler) {
        this.mIfaceDown = true;
        char c = 0;
        this.mEnableIpRuleMain = false;
        this.mEnableSecurityLog = false;
        this.mHandler = handler;
        this.mContext = context;
        this.mPrefer = this.mContext.getSharedPreferences(EXT_NETWORK_STATE, 0);
        this.mIfaces = this.mContext.getResources().getStringArray(R.array.config_ext_network_regexs);
        try {
            this.mEnableIpRuleMain = this.mContext.getResources().getBoolean(R.bool.AddMainRule);
        } catch (Exception e) {
            Log.e(TAG, "get enable ip rule main error:" + this.mEnableIpRuleMain);
        }
        Log.i(TAG, "enable ip rule main:" + this.mEnableIpRuleMain);
        boolean z = this.mEnableIpRuleMain;
        String str = ENABLE_IP_RULE;
        if (z) {
            SystemProperties.set(ENABLE_IP_RULE_PROP, ENABLE_IP_RULE);
        }
        try {
            boolean z2 = this.mContext.getResources().getBoolean(R.bool.security_log_enable);
            this.mEnableSecurityLog = z2;
            IpMask.setSecFlag(z2);
        } catch (Exception e2) {
            Log.e(TAG, "get security log enable failed");
        }
        Log.i(TAG, "start parse ext network interfaces config");
        String[] stringArray = this.mContext.getResources().getStringArray(R.array.config_ext_network_interfaces);
        if (stringArray != null) {
            for (String str2 : stringArray) {
                parseExtConfig(str2);
            }
        }
        Log.i(TAG, "finish parse ext network interfaces config");
        if (context.getResources().getBoolean(R.bool.switch_captive_protal_url)) {
            String string = this.mContext.getResources().getString(R.string.captive_protal_https_url);
            Settings.Global.putString(context.getContentResolver(), "captive_portal_https_url", string);
            Log.d(TAG, "update captive url, https url = " + string);
            String string2 = this.mContext.getResources().getString(R.string.captive_protal_http_url);
            Settings.Global.putString(context.getContentResolver(), "captive_portal_http_url", string2);
            Log.d(TAG, "update captive url, http url = " + string2);
            String string3 = this.mContext.getResources().getString(R.string.captive_protal_fallback_url);
            Settings.Global.putString(context.getContentResolver(), "captive_portal_fallback_url", string3);
            Log.d(TAG, "update captive url, fallback url = " + string3);
            String string4 = this.mContext.getResources().getString(R.string.captive_protal_other_fallback_url);
            Settings.Global.putString(context.getContentResolver(), "captive_portal_other_fallback_urls", string4);
            Log.d(TAG, "update captive url, other fallback url = " + string4);
        }
        String[] stringArray2 = this.mContext.getResources().getStringArray(R.array.config_ext_ifaces_strategy);
        if (stringArray2 != null) {
            int length = stringArray2.length;
            int i = 0;
            while (i < length) {
                String[] strArrSplit = stringArray2[i].split(";");
                String str3 = strArrSplit[c];
                String str4 = strArrSplit.length > 1 ? strArrSplit[1] : null;
                if (str4 == null) {
                    Log.d(TAG, "config for strategy error");
                } else {
                    this.mStrategys.put(str3, Boolean.valueOf(Boolean.parseBoolean(str4)));
                }
                i++;
                c = 0;
            }
        }
        boolean z3 = this.mContext.getResources().getBoolean(R.bool.use_https_protal);
        Settings.Global.putInt(context.getContentResolver(), "captive_portal_use_https", z3 ? 1 : 0);
        this.mIfaceDown = this.mContext.getResources().getBoolean(R.bool.config_down_iface);
        Log.d(TAG, "parse use_https_protal:" + z3 + " ifaceDown:" + this.mIfaceDown);
        if (Build.VERSION.SDK_INT > 29) {
            DeviceConfig.setProperty("connectivity", "captive_portal_use_https", z3 ? str : "0", true);
        }
        initBroadcast(this.mContext);
    }

    public void start() {
        Log.i(TAG, "enter start()");
        try {
            this.mNetworkManagerService.registerObserver(this.mObserver);
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
        tryUpIfaces();
        PluginInterface pluginInterface = (PluginInterface) FoundationProvider.create(PluginInterface.class, this.mContext);
        this.mPlugin = pluginInterface;
        if (pluginInterface != null) {
            PluginListener pluginListener = new PluginListener() { // from class: com.ts.car.ncm.network.ExtNetworkTracker.2
                @Override // com.ts.car.ncm.plugin.iface.PluginListener
                public void onPluginStateChanged(String iface, boolean state) {
                    Log.i(ExtNetworkTracker.TAG, "onPluginStateChanged iface:" + iface + " state:" + state);
                    ExtNetworkTracker.this.enableIface(iface, state);
                }
            };
            this.mPluginListener = pluginListener;
            this.mPlugin.registerPluginListener(pluginListener);
            Log.i(TAG, "registerPluginListener success");
            return;
        }
        Log.w(TAG, "create PluginInterface failed!");
    }

    public void stop() {
        for (ExtNetworkSet set : this.mExtNetworkSets.values()) {
            set.destory();
        }
        try {
            this.mNetworkManagerService.unregisterObserver(this.mObserver);
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
        PluginInterface pluginInterface = this.mPlugin;
        if (pluginInterface != null) {
            pluginInterface.registerPluginListener(this.mPluginListener);
        }
    }

    public boolean checkStoreState(String iface) {
        SharedPreferences sharedPreferences;
        Log.d(TAG, "checkStoreState iface:" + iface);
        ExtNetworkSet set = this.mExtNetworkSets.get(iface);
        if (set == null || (sharedPreferences = this.mPrefer) == null) {
            return true;
        }
        boolean isOpen = sharedPreferences.getBoolean(iface, true);
        Log.d(TAG, "checkStoreState type:" + set.mNetworkType + " state:" + isOpen);
        return isOpen;
    }

    public void tryUpIfaces() {
        Log.d(TAG, "tryUpIfaces");
        try {
            String[] ifaces = this.mNetworkManagerService.listInterfaces();
            for (String iface : ifaces) {
                Log.d(TAG, "list iface:" + iface);
                postIfaceEvent(iface, 1000, false);
            }
        } catch (RemoteException | IllegalStateException exception) {
            exception.printStackTrace();
        }
    }

    private boolean setStaticIpConfig(String iface, StaticIpConfiguration sip) {
        boolean success = false;
        if (sip.ipAddress != null && sip.dnsServers.size() != 0 && sip.gateway != null) {
            for (int i = 0; i < 3 && !success; i++) {
                try {
                    InterfaceConfiguration ifaceConfig = this.mNetworkManagerService.getInterfaceConfig(iface);
                    if (ifaceConfig == null) {
                        Log.d(TAG, "setStaticIpConfig iface:" + iface + " staticIpConfig:" + IpMask.getStaticConfigString(sip));
                        return success;
                    }
                    ifaceConfig.setLinkAddress(sip.ipAddress);
                    this.mNetworkManagerService.setInterfaceConfig(iface, ifaceConfig);
                    success = true;
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return success;
    }

    public IfaceInfo getIfaceInfo(String ifaceName) {
        if (ifaceName == null || ifaceName.trim().length() == 0) {
            Log.e(TAG, "Invaild interface name:" + ifaceName);
            return null;
        }
        String iface = ifaceName.trim();
        ExtNetworkSet set = this.mExtNetworkSets.get(iface);
        if (set == null) {
            Log.e(TAG, "Can't find interface info for:" + iface);
            return null;
        }
        IfaceInfo info = new IfaceInfo();
        info.setIfaceName(iface);
        info.setIfaceType(set.mNetworkType);
        info.setIfaceState(set.mNetworkFactory.isIfaceUp() ? IfaceInfo.IfaceState.UP : IfaceInfo.IfaceState.DOWN);
        ts.car.ncm.network.IpConfiguration extIpConf = new ts.car.ncm.network.IpConfiguration();
        String ipAssName = set.mIpConfig.getIpAssignment().name();
        extIpConf.setIpAssignment(IpConfiguration.IpAssignment.valueOf(ipAssName));
        Parcel parcel = Parcel.obtain();
        set.mIpConfig.getStaticIpConfiguration().writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        ts.car.ncm.network.StaticIpConfiguration extStaticIpConf = new ts.car.ncm.network.StaticIpConfiguration();
        ts.car.ncm.network.StaticIpConfiguration.readFromParcel(extStaticIpConf, parcel);
        parcel.recycle();
        extIpConf.setStaticIpConfiguration(extStaticIpConf);
        info.setIpConfig(extIpConf);
        return info;
    }

    public List<IfaceInfo> getIfaceInfos() {
        List<IfaceInfo> infoList = new ArrayList<>();
        for (String iface : this.mExtNetworkSets.keySet()) {
            IfaceInfo info = getIfaceInfo(iface);
            if (info != null) {
                infoList.add(info);
            }
        }
        return infoList;
    }

    private int getNetworkTypeByTransport(String transportStr) {
        try {
            int i = Integer.parseInt(transportStr);
            if (i == 0) {
                return 1;
            }
            if (i == 3) {
                return 2;
            }
            return -1;
        } catch (NumberFormatException e) {
            Log.e(TAG, "Error in parsing transport type from the xml file" + transportStr);
            return -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAllListener(String iface, boolean status, int event) {
        synchronized (this.mListeners) {
            IfaceInfo info = getIfaceInfo(iface);
            int broadcastCount = this.mListeners.beginBroadcast();
            for (int i = 0; i < broadcastCount; i++) {
                if (event == 1) {
                    this.mListeners.getBroadcastItem(i).onAvailabilityChanged(info);
                } else if (event == 2) {
                    try {
                        this.mListeners.getBroadcastItem(i).onMoibleNetworkStateChanged(status);
                    } catch (RemoteException excption) {
                        excption.printStackTrace();
                    }
                }
            }
            this.mListeners.finishBroadcast();
        }
    }

    private void parseExtConfig(String configString) {
        String[] subConfigs = configString.split(";");
        String iface = subConfigs[0];
        Log.i(TAG, "parse iface name:" + iface);
        String netCapStr = subConfigs.length > 1 ? subConfigs[1] : null;
        String ipStr = subConfigs.length > 2 ? subConfigs[2] : null;
        String transport = subConfigs.length > 3 ? subConfigs[3] : null;
        String scoreStr = subConfigs.length > 4 ? subConfigs[4] : "70";
        NetworkCapabilities ncs = buildNetworkCapabilitiesFromStr(netCapStr, transport);
        Log.d(TAG, "parse NetworkCapabilities:" + ncs);
        android.net.IpConfiguration ipConfig = parseStaticIpConfiguration(ipStr);
        Log.d(TAG, "parse static ip config:" + IpMask.getIpConfigurationString(ipConfig));
        int score = parseScoreFromStr(scoreStr);
        int type = getNetworkTypeByTransport(transport);
        Log.d(TAG, "parse Network score:" + score + ", type:" + type);
        ExtNetworkFactory network = getNetworkFactoryByType(type, ncs);
        if (network != null) {
            network.registerNetworkLinkListener(this.factoryListener);
            network.register();
            ExtNetworkSet set = new ExtNetworkSet(type, iface, network, ncs, ipConfig, score);
            set.allowConnectCs(checkStoreState(iface));
            Log.d(TAG, "add ExtNetworkSet:" + set);
            this.mExtNetworkSets.put(iface, set);
        }
    }

    private ExtNetworkFactory getNetworkFactoryByType(int type, NetworkCapabilities ncs) {
        if (type == -1) {
            Log.e(TAG, "The network type is invalid:" + type);
            return null;
        }
        if (type == 1) {
            ExtNetworkFactory network = new TboxNetworkFactory(this.mHandler, this.mContext, ncs);
            return network;
        }
        if (type == 2) {
            ExtNetworkFactory network2 = new ExtEthernetNetworkFactory(this.mHandler, this.mContext, ncs);
            return network2;
        }
        ExtNetworkFactory network3 = new ExtEthernetNetworkFactory(this.mHandler, this.mContext, ncs);
        return network3;
    }

    public static int parseScoreFromStr(String scoreStr) {
        try {
            int score = Integer.parseInt(scoreStr);
            return score;
        } catch (NumberFormatException e) {
            Log.e(TAG, "The network score for in config xml is invalid:" + scoreStr + ". Use score for ethernet as default");
            return 70;
        }
    }

    public static NetworkCapabilities buildNetworkCapabilitiesFromStr(String strNetworkCapabilities, String transport) {
        NetworkCapabilities ncs = new NetworkCapabilities();
        if (TextUtils.isEmpty(strNetworkCapabilities)) {
            ncs.clearAll();
        } else {
            String[] strSubCapabilities = strNetworkCapabilities.split(",");
            for (String strCapability : strSubCapabilities) {
                try {
                    ncs.addCapability(Integer.parseInt(strCapability));
                } catch (Exception e) {
                    Log.e(TAG, "Error in parsing capabilities from the xml file: " + strCapability);
                }
            }
        }
        if (TextUtils.isEmpty(transport)) {
            ncs.addTransportType(3);
        } else {
            try {
                int itransport = Integer.parseInt(transport);
                ncs.addTransportType(itransport);
            } catch (NumberFormatException e2) {
                Log.e(TAG, "Error in parsing transport type from the xml file" + transport);
                return ncs;
            }
        }
        ncs.setLinkDownstreamBandwidthKbps(BANDWIDTH_100MB);
        ncs.setLinkUpstreamBandwidthKbps(BANDWIDTH_100MB);
        return ncs;
    }

    private static android.net.IpConfiguration parseStaticIpConfiguration(String configString) {
        StaticIpConfiguration staticIpConfig = new StaticIpConfiguration();
        if (TextUtils.isEmpty(configString)) {
            Log.e(TAG, "parseStaticIpConfiguration: configString is null");
            return null;
        }
        String[] subConfigs = configString.trim().split(" ");
        for (String subConfig : subConfigs) {
            if (!TextUtils.isEmpty(subConfig)) {
                String[] pairs = subConfig.trim().split("=");
                if (pairs.length != 2) {
                    Log.e(TAG, "error interface config " + subConfig + "in " + Arrays.toString(subConfigs));
                    throw new IllegalArgumentException("unexpected key and values:" + subConfig);
                }
                String key = pairs[0];
                String value = pairs[1];
                byte b = -1;
                int iHashCode = key.hashCode();
                if (iHashCode != -189118908) {
                    if (iHashCode != 3367) {
                        if (iHashCode != 99625) {
                            if (iHashCode == 1837548591 && key.equals("domains")) {
                                b = 1;
                            }
                        } else if (key.equals("dns")) {
                            b = 2;
                        }
                    } else if (key.equals("ip")) {
                        b = 0;
                    }
                } else if (key.equals("gateway")) {
                    b = 3;
                }
                if (b != 0) {
                    if (b == 1) {
                        staticIpConfig.domains = value;
                    } else if (b == 2) {
                        ArrayList<InetAddress> dns = staticIpConfig.dnsServers;
                        for (String ipAddress : value.split(",")) {
                            dns.add(InetAddress.parseNumericAddress(ipAddress));
                        }
                    } else if (b == 3) {
                        staticIpConfig.gateway = InetAddress.parseNumericAddress(value);
                    } else {
                        throw new IllegalArgumentException("Unexpected key: " + key + " in " + staticIpConfig);
                    }
                } else {
                    staticIpConfig.ipAddress = new LinkAddress(value);
                }
            }
        }
        return new android.net.IpConfiguration(IpConfiguration.IpAssignment.STATIC, IpConfiguration.ProxySettings.NONE, staticIpConfig, null);
    }

    private boolean isTrackingInterface(String iface) {
        if (TextUtils.isEmpty(iface)) {
            return false;
        }
        for (String iface1 : this.mIfaces) {
            if (iface.matches(iface1)) {
                return true;
            }
        }
        return false;
    }

    private ExtNetworkSet findExtNetworkSetByType(int type) {
        for (ExtNetworkSet set : this.mExtNetworkSets.values()) {
            if (set.mNetworkType == type) {
                return set;
            }
        }
        return null;
    }

    private boolean setIface(String iface, boolean up) {
        Log.d(TAG, "setIface(" + iface + ", " + up + ")");
        boolean success = false;
        ExtNetworkSet set = this.mExtNetworkSets.get(iface);
        if (!checkIfaceExist(set)) {
            Log.i(TAG, "iface not exist");
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= 3) {
                break;
            }
            try {
                if (up) {
                    InterfaceConfiguration ifaceConfig = this.mNetworkManagerService.getInterfaceConfig(iface);
                    if (ifaceConfig == null || !ifaceConfig.hasFlag(INetd.IF_FLAG_RUNNING)) {
                        this.mNetworkManagerService.setInterfaceUp(iface);
                    } else {
                        set.interfaceLinkStateChanged(true);
                    }
                    if (!this.mIfaceDown) {
                        postIfaceEvent(iface, CHANGE, up);
                    }
                } else if (this.mIfaceDown) {
                    this.mNetworkManagerService.setInterfaceDown(iface);
                } else {
                    postIfaceEvent(iface, CHANGE, false);
                }
                success = true;
            } catch (Exception exception) {
                exception.printStackTrace();
                i++;
            }
        }
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setIface(");
        sb.append(iface);
        sb.append(", ");
        sb.append(up);
        sb.append(") ");
        sb.append(success ? "succeeded" : "failed");
        Log.d(str, sb.toString());
        return success;
    }

    private boolean checkIfaceExist(ExtNetworkSet set) {
        if (set == null) {
            return false;
        }
        return set.isIfaceExists;
    }

    public boolean enableInterface(String ifaceName, boolean enable) {
        Log.d(TAG, "  ifaceName " + ifaceName + " enable: " + enable);
        return enableIface(ifaceName, enable);
    }

    public boolean enableMobileNetwork(boolean enable) {
        Log.d(TAG, "enableMobileNetwork(" + enable + ")");
        ExtNetworkSet set = findExtNetworkSetByType(1);
        if (set == null) {
            Log.e(TAG, "can't find the type:1 network");
            return false;
        }
        return enableIface(set.mIfaceName, enable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean enableIface(String ifaceName, boolean enable) {
        try {
            String[] ifaces = this.mNetworkManagerService.listInterfaces();
            if (!Arrays.asList(ifaces).contains(ifaceName)) {
                Log.e(TAG, "Can't find interface info for:" + ifaceName);
                return false;
            }
            ExtNetworkSet set = this.mExtNetworkSets.get(ifaceName);
            if (set != null) {
                Log.i(TAG, "enableIface " + ifaceName + " connectectCS:" + enable);
                set.allowConnectCs(enable);
                if (checkStoreState(ifaceName) == enable && set.mNetworkFactory.isIfaceUp() == enable) {
                    Log.i(TAG, "current iface status equal to user selected");
                    return true;
                }
            }
            if (set != null && set.isIfaceExists) {
                SharedPreferences.Editor editor = this.mPrefer.edit();
                editor.putBoolean(ifaceName, enable);
                editor.commit();
            }
            if (getStrategy(ifaceName) && !setIface(ifaceName, enable)) {
                return false;
            }
            return true;
        } catch (RemoteException | IllegalStateException e) {
            Log.e(TAG, "listInterfaces error");
            return false;
        }
    }

    public boolean getMobileNetworkEnable() {
        Log.d(TAG, "getMobileNetworkEnable ");
        ExtNetworkSet set = findExtNetworkSetByType(1);
        if (set == null) {
            Log.d(TAG, "can't find the type:1 network");
            return false;
        }
        return checkStoreState(set.mIfaceName);
    }

    public synchronized void updateInterface(final String iface, final int event, final boolean status) {
        this.mHandler.post(new Runnable() { // from class: com.ts.car.ncm.network.-$$Lambda$ExtNetworkTracker$1sOlvuR8c3S4h3nwFHM_bub1ddo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateInterface$0$ExtNetworkTracker(iface, event, status);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postIfaceEvent(String iface, int event, boolean status) {
        ExtNetworkSet set = this.mExtNetworkSets.get(iface);
        if (set == null) {
            Log.e(TAG, "ExtNetworkSet of " + iface + " not found");
        }
        Log.d(TAG, "postIfaceEvent:" + iface + " event:" + ifaceEventString(event) + " status:" + status);
        switch (event) {
            case 1000:
                set.interfaceAdded();
                set.allowConnectCs(false);
                break;
            case CHANGE /* 1001 */:
                set.interfaceLinkStateChanged(status);
                break;
            case REMOVE /* 1002 */:
                set.interfaceRemoved();
                break;
        }
    }

    public class InterfaceObserver extends BaseNetworkObserver {
        public InterfaceObserver() {
        }

        public void interfaceAdded(String iface) {
            Log.d(ExtNetworkTracker.TAG, "interfaceAdded:" + iface);
            ExtNetworkTracker.this.postIfaceEvent(iface, 1000, false);
        }

        public void interfaceRemoved(String iface) {
            Log.d(ExtNetworkTracker.TAG, "interfaceRemoved:" + iface);
            ExtNetworkTracker.this.postIfaceEvent(iface, ExtNetworkTracker.REMOVE, false);
        }

        public void interfaceLinkStateChanged(String iface, boolean up) {
            Log.d(ExtNetworkTracker.TAG, "interfaceLinkStateChanged:" + iface + " up: " + up);
            ExtNetworkTracker.this.postIfaceEvent(iface, ExtNetworkTracker.CHANGE, up);
        }
    }

    private void setInterfaceState(String iface, boolean state) {
        Log.i(TAG, "setInterfaceState(" + iface + ", " + state + ")");
        if (!this.mExtNetworkSets.containsKey(iface)) {
            Log.i(TAG, getClass().getName() + " didn't manager this iface " + iface);
            return;
        }
        Log.i(TAG, "setInterfaceState iface:" + iface + " state:" + state);
        setIface(iface, state);
    }

    private synchronized void updateInterface(String iface, boolean up) {
        Log.d(TAG, "updateInterface(iface:" + iface + ", status:" + up + ")");
        ExtNetworkSet set = this.mExtNetworkSets.get(iface);
        set.mNetworkFactory.updateLinkProperties(set.mIpConfig.getStaticIpConfiguration().toLinkProperties(set.mIfaceName));
        set.mNetworkFactory.updateScore(set.mScore);
        set.mNetworkFactory.updateInterfaceState(up);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleEventRemove, reason: merged with bridge method [inline-methods] */
    public void lambda$handleInterface$1$ExtNetworkTracker(ExtNetworkSet set) {
        Log.d(TAG, "handleEventRemove:" + set);
        updateInterface(set.mIfaceName, false);
        set.isIfaceExists = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleEventAdd, reason: merged with bridge method [inline-methods] */
    public void lambda$handleInterface$2$ExtNetworkTracker(ExtNetworkSet set) {
        Log.d(TAG, "handleEventAdd:" + set);
        String ifaceName = set.mIfaceName;
        set.isIfaceExists = true;
        if (set.needStaticIpConfig) {
            android.net.IpConfiguration ipc = set.mIpConfig;
            Log.d(TAG, "set static ip:" + IpMask.getIpConfigurationString(ipc) + " for iface:" + ifaceName);
            if (ipc == null) {
                Log.e(TAG, "Not found static ip config for:" + ifaceName);
                return;
            }
            setStaticIpConfig(ifaceName, ipc.getStaticIpConfiguration());
        }
        Log.d(TAG, "getStrategy(" + ifaceName + "):" + getStrategy(ifaceName));
        if (!getStrategy(ifaceName)) {
            setInterfaceState(ifaceName, true);
        } else {
            setInterfaceState(ifaceName, checkStoreState(ifaceName));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleEventChange, reason: merged with bridge method [inline-methods] */
    public void lambda$handleInterface$3$ExtNetworkTracker(ExtNetworkSet set, boolean state) {
        Log.d(TAG, "handleEventChange:" + set + " state:" + state);
        updateInterface(set.mIfaceName, state);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleInterface, reason: merged with bridge method [inline-methods] */
    public synchronized void lambda$updateInterface$0$ExtNetworkTracker(String iface, int event, final boolean ifaceLinkState) {
        final ExtNetworkSet set = this.mExtNetworkSets.get(iface);
        if (set == null) {
            Log.e(TAG, "ExtNetworkSet of " + iface + " not found");
            return;
        }
        Log.d(TAG, "iface:" + iface + " event:" + ifaceEventString(event) + " ifaceLinkState:" + ifaceLinkState);
        switch (event) {
            case 1000:
                this.mHandler.post(new Runnable() { // from class: com.ts.car.ncm.network.-$$Lambda$ExtNetworkTracker$Ng7ZPtleoEKGWEjt4qU2lLB_xJs
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleInterface$2$ExtNetworkTracker(set);
                    }
                });
                break;
            case CHANGE /* 1001 */:
                this.mHandler.post(new Runnable() { // from class: com.ts.car.ncm.network.-$$Lambda$ExtNetworkTracker$-5RbSXj8DVbhTpixYWG52qB_7qk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleInterface$3$ExtNetworkTracker(set, ifaceLinkState);
                    }
                });
                break;
            case REMOVE /* 1002 */:
                this.mHandler.post(new Runnable() { // from class: com.ts.car.ncm.network.-$$Lambda$ExtNetworkTracker$vDuUCPTTXCX1qEebfGzA3dFugMY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$handleInterface$1$ExtNetworkTracker(set);
                    }
                });
                break;
            default:
                Log.e(TAG, "event: " + event + " not available");
                return;
        }
    }

    public final class ExtConSvcLtnDeathRecipient implements IBinder.DeathRecipient {
        private static final String EXT_TAG = "ExtConSvcLtnDeathRecipient";
        private IBinder listenerBinder;

        public ExtConSvcLtnDeathRecipient(IBinder binder) {
            this.listenerBinder = binder;
        }

        @Override // android.os.IBinder.DeathRecipient
        public synchronized void binderDied() {
            Log.d(EXT_TAG, "binderDied: " + this.listenerBinder);
            ExtNetworkTracker.this.unregisterListener((IExtConnectivityServiceListener) ExtNetworkTracker.this.mListenersMap.get(this.listenerBinder));
        }
    }

    public synchronized void registerListener(IExtConnectivityServiceListener listener) {
        IBinder listenerBinder = listener.asBinder();
        if (this.mListenersMap.containsKey(listenerBinder)) {
            return;
        }
        ExtConSvcLtnDeathRecipient deathRecipient = new ExtConSvcLtnDeathRecipient(listenerBinder);
        try {
            listenerBinder.linkToDeath(deathRecipient, 0);
        } catch (RemoteException exp) {
            Log.e(TAG, "Failed to link death for recipient. " + exp);
        }
        this.mListeners.register(listener);
        this.mListenersMap.put(listenerBinder, listener);
        this.mDeathRecipientMap.put(listenerBinder, deathRecipient);
    }

    public synchronized void unregisterListener(IExtConnectivityServiceListener listener) {
        IBinder listenerBinder = listener.asBinder();
        if (!this.mListenersMap.containsKey(listenerBinder)) {
            Log.e(TAG, "unregisterListener: Listener was not previously registered.");
        }
        listenerBinder.unlinkToDeath(this.mDeathRecipientMap.get(listenerBinder), 0);
        this.mListeners.unregister(listener);
        this.mListenersMap.remove(listenerBinder);
        this.mDeathRecipientMap.remove(listenerBinder);
    }

    private boolean getStrategy(String ifaceName) {
        return this.mStrategys.getOrDefault(ifaceName, true).booleanValue();
    }

    public IfaceMonitor getInterfaceMonitor(String iface) {
        ExtNetworkSet set = this.mExtNetworkSets.get(iface);
        if (set == null) {
            return null;
        }
        return set.getInterfaceMonitor();
    }
}
