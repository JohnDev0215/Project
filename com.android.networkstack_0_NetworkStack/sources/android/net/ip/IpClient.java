package android.net.ip;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.INetd;
import android.net.IpPrefix;
import android.net.Layer2InformationParcelable;
import android.net.Layer2PacketParcelable;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.MacAddress;
import android.net.NattKeepalivePacketDataParcelable;
import android.net.NetworkStackIpMemoryStore;
import android.net.ProvisioningConfigurationParcelable;
import android.net.ProxyInfo;
import android.net.RouteInfo;
import android.net.StaticIpConfiguration;
import android.net.TcpKeepalivePacketDataParcelable;
import android.net.Uri;
import android.net.apf.ApfCapabilities;
import android.net.apf.ApfFilter;
import android.net.dhcp.DhcpClient;
import android.net.dhcp.DhcpResultsParcelableUtil;
import android.net.ip.IIpClient;
import android.net.ip.IpClientLinkObserver;
import android.net.ip.IpReachabilityMonitor;
import android.net.metrics.IpConnectivityLog;
import android.net.metrics.IpManagerEvent;
import android.net.networkstack.DhcpResults;
import android.net.networkstack.util.IState;
import android.net.networkstack.util.IndentingPrintWriter;
import android.net.networkstack.util.LocalLog;
import android.net.networkstack.util.MessageUtils;
import android.net.networkstack.util.State;
import android.net.networkstack.util.StateMachine;
import android.net.networkstack.util.WakeupMessage;
import android.net.shared.InitialConfiguration;
import android.net.shared.Layer2Information;
import android.net.shared.ProvisioningConfiguration;
import android.net.util.InterfaceParams;
import android.net.util.NetworkStackUtils;
import android.net.util.SharedLog;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.SystemClock;
import android.stats.connectivity.DisconnectCode;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.android.networkstack.apishim.NetworkInformationShimImpl;
import com.android.networkstack.apishim.common.NetworkInformationShim;
import com.android.networkstack.apishim.common.ShimUtils;
import com.android.networkstack.metrics.IpProvisioningMetrics;
import com.android.server.NetworkObserverRegistry;
import com.android.server.NetworkStackService;
import com.android.server.util.PermissionUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class IpClient extends StateMachine {
    private static final int CMD_ADDRESSES_CLEARED = 100;
    private static final int CMD_ADD_KEEPALIVE_PACKET_FILTER_TO_APF = 13;
    private static final int CMD_COMPLETE_PRECONNECTION = 16;
    private static final int CMD_CONFIRM = 4;
    private static final int CMD_JUMP_RUNNING_TO_STOPPING = 101;
    private static final int CMD_JUMP_STOPPING_TO_STOPPED = 102;
    private static final int CMD_REMOVE_KEEPALIVE_PACKET_FILTER_FROM_APF = 14;
    private static final int CMD_SET_MULTICAST_FILTER = 9;
    private static final int CMD_START = 3;
    private static final int CMD_STOP = 2;
    private static final int CMD_TERMINATE_AFTER_STOP = 1;
    private static final int CMD_UPDATE_HTTP_PROXY = 8;
    private static final int CMD_UPDATE_L2INFORMATION = 17;
    private static final int CMD_UPDATE_L2KEY_CLUSTER = 15;
    private static final int CMD_UPDATE_TCP_BUFFER_SIZES = 7;
    static final String CONFIG_MIN_RDNSS_LIFETIME = "ipclient_min_rdnss_lifetime";
    private static final int DEFAULT_MIN_RDNSS_LIFETIME;
    private static final Set<String> DHCP_ROAMING_SSID_SET;
    private static final int EVENT_DHCPACTION_TIMEOUT = 11;
    private static final int EVENT_NETLINK_LINKPROPERTIES_CHANGED = 6;
    private static final int EVENT_PRE_DHCP_ACTION_COMPLETE = 5;
    private static final int EVENT_PROVISIONING_TIMEOUT = 10;
    private static final int EVENT_READ_PACKET_FILTER_COMPLETE = 12;
    private static final List<byte[]> METERED_IE_PATTERN_LIST;
    private static final Class[] sMessageClasses;
    private static final SparseArray<String> sWhatToString;
    private final ConditionVariable mApfDataSnapshotComplete;
    private ApfFilter mApfFilter;
    protected final IpClientCallbacksWrapper mCallback;
    private final String mClatInterfaceName;
    private final State mClearingIpAddressesState;
    private String mCluster;
    private final ConnectivityManager mCm;
    private ProvisioningConfiguration mConfiguration;
    private final LocalLog mConnectivityPacketLog;
    private final Context mContext;
    private MacAddress mCurrentBssid;
    private final Dependencies mDependencies;
    private final WakeupMessage mDhcpActionTimeoutAlarm;
    private DhcpClient mDhcpClient;
    private DhcpResults mDhcpResults;
    private boolean mHasDisabledIPv6OnProvLoss;
    private ProxyInfo mHttpProxy;
    private final InterfaceController mInterfaceCtrl;
    private final String mInterfaceName;
    private InterfaceParams mInterfaceParams;
    private final NetworkStackIpMemoryStore mIpMemoryStore;
    private final IpProvisioningMetrics mIpProvisioningMetrics;
    private IpReachabilityMonitor mIpReachabilityMonitor;
    private String mL2Key;
    private final IpClientLinkObserver mLinkObserver;
    private LinkProperties mLinkProperties;
    private final SharedLog mLog;
    private final IpConnectivityLog mMetricsLog;
    private final int mMinRdnssLifetimeSec;
    private final MessageHandlingLogger mMsgStateLogger;
    private boolean mMulticastFiltering;
    private final INetd mNetd;
    private final NetworkObserverRegistry mObserverRegistry;
    private final State mPreconnectingState;
    private final WakeupMessage mProvisioningTimeoutAlarm;
    private final State mRunningState;
    private final NetworkInformationShim mShim;
    private final CountDownLatch mShutdownLatch;
    private long mStartTimeMillis;
    private final State mStartedState;
    private final State mStoppedState;
    private final State mStoppingState;
    private final String mTag;
    private String mTcpBufferSizes;
    private static final ConcurrentHashMap<String, SharedLog> sSmLogs = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, LocalLog> sPktLogs = new ConcurrentHashMap<>();

    private void maybeSaveNetworkToIpMemoryStore() {
    }

    static {
        Class[] clsArr = {IpClient.class, DhcpClient.class};
        sMessageClasses = clsArr;
        sWhatToString = MessageUtils.findMessageNames(clsArr);
        DEFAULT_MIN_RDNSS_LIFETIME = ShimUtils.isReleaseOrDevelopmentApiAbove(29) ? 120 : 0;
        METERED_IE_PATTERN_LIST = Collections.unmodifiableList(Arrays.asList(new byte[]{0, 23, -14, 6}));
        DHCP_ROAMING_SSID_SET = new HashSet(Arrays.asList("0001docomo", "ollehWiFi", "olleh GiGa WiFi", "KT WiFi", "KT GiGA WiFi", "marente"));
    }

    public static void dumpAllLogs(PrintWriter printWriter, Set<String> set) {
        for (String str : sSmLogs.keySet()) {
            if (!set.contains(str)) {
                printWriter.println(String.format("--- BEGIN %s ---", str));
                SharedLog sharedLog = sSmLogs.get(str);
                if (sharedLog != null) {
                    printWriter.println("State machine log:");
                    sharedLog.dump(null, printWriter, null);
                }
                printWriter.println("");
                LocalLog localLog = sPktLogs.get(str);
                if (localLog != null) {
                    printWriter.println("Connectivity packet log:");
                    localLog.readOnlyLocalLog().dump(null, printWriter, null);
                }
                printWriter.println(String.format("--- END %s ---", str));
            }
        }
    }

    public static class IpClientCallbacksWrapper {
        private final IIpClientCallbacks mCallback;
        private final SharedLog mLog;
        private final NetworkInformationShim mShim;

        protected IpClientCallbacksWrapper(IIpClientCallbacks iIpClientCallbacks, SharedLog sharedLog, NetworkInformationShim networkInformationShim) {
            this.mCallback = iIpClientCallbacks;
            this.mLog = sharedLog;
            this.mShim = networkInformationShim;
        }

        private void log(String str) {
            this.mLog.log("INVOKE " + str);
        }

        private void log(String str, Throwable th) {
            this.mLog.e("INVOKE " + str, th);
        }

        public void onPreDhcpAction() {
            log("onPreDhcpAction()");
            try {
                this.mCallback.onPreDhcpAction();
            } catch (RemoteException e) {
                log("Failed to call onPreDhcpAction", e);
            }
        }

        public void onPostDhcpAction() {
            log("onPostDhcpAction()");
            try {
                this.mCallback.onPostDhcpAction();
            } catch (RemoteException e) {
                log("Failed to call onPostDhcpAction", e);
            }
        }

        public void onNewDhcpResults(DhcpResults dhcpResults) {
            log("onNewDhcpResults({" + dhcpResults + "})");
            try {
                this.mCallback.onNewDhcpResults(DhcpResultsParcelableUtil.toStableParcelable(dhcpResults));
            } catch (RemoteException e) {
                log("Failed to call onNewDhcpResults", e);
            }
        }

        public void onProvisioningSuccess(LinkProperties linkProperties) {
            log("onProvisioningSuccess({" + linkProperties + "})");
            try {
                this.mCallback.onProvisioningSuccess(this.mShim.makeSensitiveFieldsParcelingCopy(linkProperties));
            } catch (RemoteException e) {
                log("Failed to call onProvisioningSuccess", e);
            }
        }

        public void onProvisioningFailure(LinkProperties linkProperties) {
            log("onProvisioningFailure({" + linkProperties + "})");
            try {
                this.mCallback.onProvisioningFailure(this.mShim.makeSensitiveFieldsParcelingCopy(linkProperties));
            } catch (RemoteException e) {
                log("Failed to call onProvisioningFailure", e);
            }
        }

        public void onLinkPropertiesChange(LinkProperties linkProperties) {
            log("onLinkPropertiesChange({" + linkProperties + "})");
            try {
                this.mCallback.onLinkPropertiesChange(this.mShim.makeSensitiveFieldsParcelingCopy(linkProperties));
            } catch (RemoteException e) {
                log("Failed to call onLinkPropertiesChange", e);
            }
        }

        public void onReachabilityLost(String str) {
            log("onReachabilityLost(" + str + ")");
            try {
                this.mCallback.onReachabilityLost(str);
            } catch (RemoteException e) {
                log("Failed to call onReachabilityLost", e);
            }
        }

        public void onQuit() {
            log("onQuit()");
            try {
                this.mCallback.onQuit();
            } catch (RemoteException e) {
                log("Failed to call onQuit", e);
            }
        }

        public void installPacketFilter(byte[] bArr) {
            log("installPacketFilter(byte[" + bArr.length + "])");
            try {
                this.mCallback.installPacketFilter(bArr);
            } catch (RemoteException e) {
                log("Failed to call installPacketFilter", e);
            }
        }

        public void startReadPacketFilter() {
            log("startReadPacketFilter()");
            try {
                this.mCallback.startReadPacketFilter();
            } catch (RemoteException e) {
                log("Failed to call startReadPacketFilter", e);
            }
        }

        public void setFallbackMulticastFilter(boolean z) {
            log("setFallbackMulticastFilter(" + z + ")");
            try {
                this.mCallback.setFallbackMulticastFilter(z);
            } catch (RemoteException e) {
                log("Failed to call setFallbackMulticastFilter", e);
            }
        }

        public void setNeighborDiscoveryOffload(boolean z) {
            log("setNeighborDiscoveryOffload(" + z + ")");
            try {
                this.mCallback.setNeighborDiscoveryOffload(z);
            } catch (RemoteException e) {
                log("Failed to call setNeighborDiscoveryOffload", e);
            }
        }

        public void onPreconnectionStart(List<Layer2PacketParcelable> list) {
            log("onPreconnectionStart(Layer2Packets[" + list.size() + "])");
            try {
                this.mCallback.onPreconnectionStart(list);
            } catch (RemoteException e) {
                log("Failed to call onPreconnectionStart", e);
            }
        }
    }

    public static class Dependencies {
        public InterfaceParams getInterfaceParams(String str) {
            return InterfaceParams.getByName(str);
        }

        public INetd getNetd(Context context) {
            return INetd.Stub.asInterface((IBinder) context.getSystemService("netd"));
        }

        public NetworkStackIpMemoryStore getIpMemoryStore(Context context, NetworkStackService.NetworkStackServiceManager networkStackServiceManager) {
            return new NetworkStackIpMemoryStore(context, networkStackServiceManager.getIpMemoryStoreService());
        }

        public DhcpClient makeDhcpClient(Context context, StateMachine stateMachine, InterfaceParams interfaceParams, DhcpClient.Dependencies dependencies) {
            return DhcpClient.makeDhcpClient(context, stateMachine, interfaceParams, dependencies);
        }

        public DhcpClient.Dependencies getDhcpClientDependencies(NetworkStackIpMemoryStore networkStackIpMemoryStore, IpProvisioningMetrics ipProvisioningMetrics) {
            return new DhcpClient.Dependencies(networkStackIpMemoryStore, ipProvisioningMetrics);
        }

        public int getDeviceConfigPropertyInt(String str, int i) {
            return NetworkStackUtils.getDeviceConfigPropertyInt("connectivity", str, i);
        }

        public IpConnectivityLog getIpConnectivityLog() {
            return new IpConnectivityLog();
        }
    }

    public IpClient(Context context, String str, IIpClientCallbacks iIpClientCallbacks, NetworkObserverRegistry networkObserverRegistry, NetworkStackService.NetworkStackServiceManager networkStackServiceManager) {
        this(context, str, iIpClientCallbacks, networkObserverRegistry, networkStackServiceManager, new Dependencies());
    }

    IpClient(Context context, String str, IIpClientCallbacks iIpClientCallbacks, NetworkObserverRegistry networkObserverRegistry, NetworkStackService.NetworkStackServiceManager networkStackServiceManager, Dependencies dependencies) {
        super(IpClient.class.getSimpleName() + "." + str);
        this.mShim = NetworkInformationShimImpl.newInstance();
        this.mIpProvisioningMetrics = new IpProvisioningMetrics();
        this.mStoppedState = new StoppedState();
        this.mStoppingState = new StoppingState();
        this.mClearingIpAddressesState = new ClearingIpAddressesState();
        this.mStartedState = new StartedState();
        this.mRunningState = new RunningState();
        this.mPreconnectingState = new PreconnectingState();
        this.mApfDataSnapshotComplete = new ConditionVariable();
        Objects.requireNonNull(str);
        Objects.requireNonNull(iIpClientCallbacks);
        this.mTag = getName();
        this.mContext = context;
        this.mInterfaceName = str;
        this.mClatInterfaceName = "v4-" + str;
        this.mDependencies = dependencies;
        this.mMetricsLog = dependencies.getIpConnectivityLog();
        this.mShutdownLatch = new CountDownLatch(1);
        this.mCm = (ConnectivityManager) this.mContext.getSystemService(ConnectivityManager.class);
        this.mObserverRegistry = networkObserverRegistry;
        this.mIpMemoryStore = dependencies.getIpMemoryStore(context, networkStackServiceManager);
        sSmLogs.putIfAbsent(this.mInterfaceName, new SharedLog(500, this.mTag));
        this.mLog = sSmLogs.get(this.mInterfaceName);
        sPktLogs.putIfAbsent(this.mInterfaceName, new LocalLog(CMD_ADDRESSES_CLEARED));
        this.mConnectivityPacketLog = sPktLogs.get(this.mInterfaceName);
        this.mMsgStateLogger = new MessageHandlingLogger(null);
        this.mCallback = new IpClientCallbacksWrapper(iIpClientCallbacks, this.mLog, this.mShim);
        INetd netd = dependencies.getNetd(this.mContext);
        this.mNetd = netd;
        this.mInterfaceCtrl = new InterfaceController(this.mInterfaceName, netd, this.mLog);
        int deviceConfigPropertyInt = this.mDependencies.getDeviceConfigPropertyInt(CONFIG_MIN_RDNSS_LIFETIME, DEFAULT_MIN_RDNSS_LIFETIME);
        this.mMinRdnssLifetimeSec = deviceConfigPropertyInt;
        this.mLinkObserver = new AnonymousClass1(this.mContext, getHandler(), this.mInterfaceName, new IpClientLinkObserver.Callback() { // from class: android.net.ip.-$$Lambda$IpClient$RCwsJ_cvcfbyv9gWfFy4b66hrNk
            @Override // android.net.ip.IpClientLinkObserver.Callback
            public final void update(boolean z) {
                this.f$0.lambda$new$0$IpClient(z);
            }
        }, new IpClientLinkObserver.Configuration(deviceConfigPropertyInt), this.mLog);
        LinkProperties linkProperties = new LinkProperties();
        this.mLinkProperties = linkProperties;
        linkProperties.setInterfaceName(this.mInterfaceName);
        this.mProvisioningTimeoutAlarm = new WakeupMessage(this.mContext, getHandler(), this.mTag + ".EVENT_PROVISIONING_TIMEOUT", EVENT_PROVISIONING_TIMEOUT);
        this.mDhcpActionTimeoutAlarm = new WakeupMessage(this.mContext, getHandler(), this.mTag + ".EVENT_DHCPACTION_TIMEOUT", EVENT_DHCPACTION_TIMEOUT);
        configureAndStartStateMachine();
        startStateMachineUpdaters();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$IpClient(boolean z) {
        sendMessage(6, z ? 1 : 0);
    }

    /* JADX INFO: renamed from: android.net.ip.IpClient$1, reason: invalid class name */
    class AnonymousClass1 extends IpClientLinkObserver {
        AnonymousClass1(Context context, Handler handler, String str, IpClientLinkObserver.Callback callback, IpClientLinkObserver.Configuration configuration, SharedLog sharedLog) {
            super(context, handler, str, callback, configuration, sharedLog);
        }

        @Override // com.android.server.NetworkObserver
        public void onInterfaceAdded(String str) {
            super.onInterfaceAdded(str);
            if (!IpClient.this.mClatInterfaceName.equals(str)) {
                if (!IpClient.this.mInterfaceName.equals(str)) {
                    return;
                }
            } else {
                IpClient.this.mCallback.setNeighborDiscoveryOffload(false);
            }
            logMsg("interfaceAdded(" + str + ")");
        }

        @Override // android.net.ip.IpClientLinkObserver, com.android.server.NetworkObserver
        public void onInterfaceRemoved(String str) {
            super.onInterfaceRemoved(str);
            if (!IpClient.this.mClatInterfaceName.equals(str)) {
                if (!IpClient.this.mInterfaceName.equals(str)) {
                    return;
                }
            } else {
                IpClient.this.mCallback.setNeighborDiscoveryOffload(true);
            }
            logMsg("interfaceRemoved(" + str + ")");
        }

        private void logMsg(final String str) {
            Log.d(IpClient.this.mTag, str);
            IpClient.this.getHandler().post(new Runnable() { // from class: android.net.ip.-$$Lambda$IpClient$1$9fQ1SZ-gZXSwOLpm6cTU88p3I8c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$logMsg$0$IpClient$1(str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$logMsg$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$logMsg$0$IpClient$1(String str) {
            IpClient.this.mLog.log("OBSERVED " + str);
        }
    }

    public IIpClient makeConnector() {
        return new IpClientConnector();
    }

    class IpClientConnector extends IIpClient.Stub {
        @Override // android.net.ip.IIpClient
        public String getInterfaceHash() {
            return "02cd6fd07d5c04eca0c35a350f7b0be576242883";
        }

        @Override // android.net.ip.IIpClient
        public int getInterfaceVersion() {
            return 7;
        }

        IpClientConnector() {
        }

        @Override // android.net.ip.IIpClient
        public void completedPreDhcpAction() {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.completedPreDhcpAction();
        }

        @Override // android.net.ip.IIpClient
        public void confirmConfiguration() {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.confirmConfiguration();
        }

        @Override // android.net.ip.IIpClient
        public void readPacketFilterComplete(byte[] bArr) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.readPacketFilterComplete(bArr);
        }

        @Override // android.net.ip.IIpClient
        public void shutdown() {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.shutdown();
        }

        @Override // android.net.ip.IIpClient
        public void startProvisioning(ProvisioningConfigurationParcelable provisioningConfigurationParcelable) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.startProvisioning(ProvisioningConfiguration.fromStableParcelable(provisioningConfigurationParcelable));
        }

        @Override // android.net.ip.IIpClient
        public void stop() {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.stop();
        }

        @Override // android.net.ip.IIpClient
        public void setL2KeyAndGroupHint(String str, String str2) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.setL2KeyAndCluster(str, str2);
        }

        @Override // android.net.ip.IIpClient
        public void setTcpBufferSizes(String str) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.setTcpBufferSizes(str);
        }

        @Override // android.net.ip.IIpClient
        public void setHttpProxy(ProxyInfo proxyInfo) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.setHttpProxy(proxyInfo);
        }

        @Override // android.net.ip.IIpClient
        public void setMulticastFilter(boolean z) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.setMulticastFilter(z);
        }

        @Override // android.net.ip.IIpClient
        public void addKeepalivePacketFilter(int i, TcpKeepalivePacketDataParcelable tcpKeepalivePacketDataParcelable) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.addKeepalivePacketFilter(i, tcpKeepalivePacketDataParcelable);
        }

        @Override // android.net.ip.IIpClient
        public void addNattKeepalivePacketFilter(int i, NattKeepalivePacketDataParcelable nattKeepalivePacketDataParcelable) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.addNattKeepalivePacketFilter(i, nattKeepalivePacketDataParcelable);
        }

        @Override // android.net.ip.IIpClient
        public void removeKeepalivePacketFilter(int i) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.removeKeepalivePacketFilter(i);
        }

        @Override // android.net.ip.IIpClient
        public void notifyPreconnectionComplete(boolean z) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.notifyPreconnectionComplete(z);
        }

        @Override // android.net.ip.IIpClient
        public void updateLayer2Information(Layer2InformationParcelable layer2InformationParcelable) {
            PermissionUtil.enforceNetworkStackCallingPermission();
            IpClient.this.updateLayer2Information(layer2InformationParcelable);
        }
    }

    public String getInterfaceName() {
        return this.mInterfaceName;
    }

    private void configureAndStartStateMachine() {
        addState(this.mStoppedState);
        addState(this.mStartedState);
        addState(this.mPreconnectingState, this.mStartedState);
        addState(this.mClearingIpAddressesState, this.mStartedState);
        addState(this.mRunningState, this.mStartedState);
        addState(this.mStoppingState);
        setInitialState(this.mStoppedState);
        super.start();
    }

    private void startStateMachineUpdaters() {
        this.mObserverRegistry.registerObserverForNonblockingCallback(this.mLinkObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopStateMachineUpdaters() {
        this.mObserverRegistry.unregisterObserver(this.mLinkObserver);
    }

    @Override // android.net.networkstack.util.StateMachine
    protected void onQuitting() {
        this.mCallback.onQuit();
        this.mShutdownLatch.countDown();
    }

    public void shutdown() {
        stop();
        sendMessage(1);
    }

    public void startProvisioning(ProvisioningConfiguration provisioningConfiguration) {
        if (!provisioningConfiguration.isValid()) {
            doImmediateProvisioningFailure(7);
            return;
        }
        ProvisioningConfiguration.ScanResultInfo scanResultInfo = provisioningConfiguration.mScanResultInfo;
        this.mCurrentBssid = null;
        if (scanResultInfo != null) {
            try {
                this.mCurrentBssid = MacAddress.fromString(scanResultInfo.getBssid());
            } catch (IllegalArgumentException e) {
                Log.wtf(this.mTag, "Invalid BSSID: " + scanResultInfo.getBssid() + " in provisioning configuration", e);
            }
        }
        Layer2Information layer2Information = provisioningConfiguration.mLayer2Info;
        if (layer2Information != null) {
            this.mL2Key = layer2Information.mL2Key;
            String str = layer2Information.mCluster;
        }
        sendMessage(3, new ProvisioningConfiguration(provisioningConfiguration));
    }

    public void stop() {
        sendMessage(2, DisconnectCode.DC_NORMAL_TERMINATION.getNumber());
    }

    public void confirmConfiguration() {
        sendMessage(4);
    }

    public void completedPreDhcpAction() {
        sendMessage(5);
    }

    public void readPacketFilterComplete(byte[] bArr) {
        sendMessage(EVENT_READ_PACKET_FILTER_COMPLETE, bArr);
    }

    public void setTcpBufferSizes(String str) {
        sendMessage(7, str);
    }

    public void setL2KeyAndCluster(String str, String str2) {
        if (ShimUtils.isReleaseOrDevelopmentApiAbove(29)) {
            return;
        }
        sendMessage(CMD_UPDATE_L2KEY_CLUSTER, new Pair(str, str2));
    }

    public void setHttpProxy(ProxyInfo proxyInfo) {
        sendMessage(CMD_UPDATE_HTTP_PROXY, proxyInfo);
    }

    public void setMulticastFilter(boolean z) {
        sendMessage(CMD_SET_MULTICAST_FILTER, Boolean.valueOf(z));
    }

    public void addKeepalivePacketFilter(int i, TcpKeepalivePacketDataParcelable tcpKeepalivePacketDataParcelable) {
        sendMessage(CMD_ADD_KEEPALIVE_PACKET_FILTER_TO_APF, i, 0, tcpKeepalivePacketDataParcelable);
    }

    public void addNattKeepalivePacketFilter(int i, NattKeepalivePacketDataParcelable nattKeepalivePacketDataParcelable) {
        sendMessage(CMD_ADD_KEEPALIVE_PACKET_FILTER_TO_APF, i, 0, nattKeepalivePacketDataParcelable);
    }

    public void removeKeepalivePacketFilter(int i) {
        sendMessage(CMD_REMOVE_KEEPALIVE_PACKET_FILTER_FROM_APF, i, 0);
    }

    public void notifyPreconnectionComplete(boolean z) {
        sendMessage(CMD_COMPLETE_PRECONNECTION, z ? 1 : 0);
    }

    public void updateLayer2Information(Layer2InformationParcelable layer2InformationParcelable) {
        sendMessage(CMD_UPDATE_L2INFORMATION, layer2InformationParcelable);
    }

    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        if (strArr != null && strArr.length > 0 && "confirm".equals(strArr[0])) {
            confirmConfiguration();
            return;
        }
        ApfFilter apfFilter = this.mApfFilter;
        ProvisioningConfiguration provisioningConfiguration = this.mConfiguration;
        ApfCapabilities apfCapabilities = provisioningConfiguration != null ? provisioningConfiguration.mApfCapabilities : null;
        IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
        indentingPrintWriter.println(this.mTag + " APF dump:");
        indentingPrintWriter.increaseIndent();
        if (apfFilter != null) {
            if (apfCapabilities.hasDataAccess()) {
                this.mApfDataSnapshotComplete.close();
                this.mCallback.startReadPacketFilter();
                if (!this.mApfDataSnapshotComplete.block(1000L)) {
                    indentingPrintWriter.print("TIMEOUT: DUMPING STALE APF SNAPSHOT");
                }
            }
            apfFilter.dump(indentingPrintWriter);
        } else {
            indentingPrintWriter.print("No active ApfFilter; ");
            if (provisioningConfiguration == null) {
                indentingPrintWriter.println("IpClient not yet started.");
            } else if (apfCapabilities == null || apfCapabilities.apfVersionSupported == 0) {
                indentingPrintWriter.println("Hardware does not support APF.");
            } else {
                indentingPrintWriter.println("ApfFilter not yet started, APF capabilities: " + apfCapabilities);
            }
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println();
        indentingPrintWriter.println(this.mTag + " current ProvisioningConfiguration:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println(Objects.toString(provisioningConfiguration, "N/A"));
        indentingPrintWriter.decreaseIndent();
        IpReachabilityMonitor ipReachabilityMonitor = this.mIpReachabilityMonitor;
        if (ipReachabilityMonitor != null) {
            indentingPrintWriter.println();
            indentingPrintWriter.println(this.mTag + " current IpReachabilityMonitor state:");
            indentingPrintWriter.increaseIndent();
            ipReachabilityMonitor.dump(indentingPrintWriter);
            indentingPrintWriter.decreaseIndent();
        }
        indentingPrintWriter.println();
        indentingPrintWriter.println(this.mTag + " StateMachine dump:");
        indentingPrintWriter.increaseIndent();
        this.mLog.dump(fileDescriptor, indentingPrintWriter, strArr);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println();
        indentingPrintWriter.println(this.mTag + " connectivity packet log:");
        indentingPrintWriter.println();
        indentingPrintWriter.println("Debug with python and scapy via:");
        indentingPrintWriter.println("shell$ python");
        indentingPrintWriter.println(">>> from scapy import all as scapy");
        indentingPrintWriter.println(">>> scapy.Ether(\"<paste_hex_string>\".decode(\"hex\")).show2()");
        indentingPrintWriter.println();
        indentingPrintWriter.increaseIndent();
        this.mConnectivityPacketLog.readOnlyLocalLog().dump(fileDescriptor, indentingPrintWriter, strArr);
        indentingPrintWriter.decreaseIndent();
    }

    @Override // android.net.networkstack.util.StateMachine
    protected String getWhatToString(int i) {
        return sWhatToString.get(i, "UNKNOWN: " + Integer.toString(i));
    }

    @Override // android.net.networkstack.util.StateMachine
    protected String getLogRecString(Message message) {
        Object[] objArr = new Object[6];
        objArr[0] = this.mInterfaceName;
        InterfaceParams interfaceParams = this.mInterfaceParams;
        objArr[1] = Integer.valueOf(interfaceParams == null ? -1 : interfaceParams.index);
        objArr[2] = Integer.valueOf(message.arg1);
        objArr[3] = Integer.valueOf(message.arg2);
        objArr[4] = Objects.toString(message.obj);
        objArr[5] = this.mMsgStateLogger;
        String str = String.format("%s/%d %d %d %s [%s]", objArr);
        this.mLog.log(getWhatToString(message.what) + " " + str);
        this.mMsgStateLogger.reset();
        return str;
    }

    @Override // android.net.networkstack.util.StateMachine
    protected boolean recordLogRec(Message message) {
        boolean z = message.what != 6;
        if (!z) {
            this.mMsgStateLogger.reset();
        }
        return z;
    }

    private void logError(String str, Throwable th, Object... objArr) {
        this.mLog.e(String.format(str, objArr), th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logError(String str, Object... objArr) {
        logError(str, null, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetLinkProperties() {
        this.mLinkObserver.clearLinkProperties();
        this.mConfiguration = null;
        this.mDhcpResults = null;
        this.mTcpBufferSizes = "";
        this.mHttpProxy = null;
        LinkProperties linkProperties = new LinkProperties();
        this.mLinkProperties = linkProperties;
        linkProperties.setInterfaceName(this.mInterfaceName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordMetric(int i) {
        this.mMetricsLog.log(this.mInterfaceName, new IpManagerEvent(i, this.mStartTimeMillis > 0 ? SystemClock.elapsedRealtime() - this.mStartTimeMillis : 0L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionToStoppingState(DisconnectCode disconnectCode) {
        this.mIpProvisioningMetrics.setDisconnectCode(disconnectCode);
        transitionTo(this.mStoppingState);
    }

    static boolean isProvisioned(LinkProperties linkProperties, InitialConfiguration initialConfiguration) {
        if (linkProperties.hasIpv4Address() || linkProperties.isProvisioned()) {
            return true;
        }
        if (initialConfiguration == null) {
            return false;
        }
        return initialConfiguration.isProvisionedBy(linkProperties.getLinkAddresses(), linkProperties.getRoutes());
    }

    private int compareProvisioning(LinkProperties linkProperties, LinkProperties linkProperties2) {
        ProvisioningConfiguration provisioningConfiguration;
        ProvisioningConfiguration provisioningConfiguration2 = this.mConfiguration;
        InitialConfiguration initialConfiguration = provisioningConfiguration2 != null ? provisioningConfiguration2.mInitialConfig : null;
        boolean zIsProvisioned = isProvisioned(linkProperties, initialConfiguration);
        boolean zIsProvisioned2 = isProvisioned(linkProperties2, initialConfiguration);
        int i = (zIsProvisioned || !zIsProvisioned2) ? (zIsProvisioned && zIsProvisioned2) ? 4 : (zIsProvisioned || zIsProvisioned2) ? 2 : 1 : 3;
        boolean z = false;
        boolean z2 = linkProperties.isIpv6Provisioned() && !linkProperties2.isIpv6Provisioned();
        boolean z3 = linkProperties.hasIpv4Address() && !linkProperties2.hasIpv4Address();
        boolean z4 = linkProperties.hasIpv6DefaultRoute() && !linkProperties2.hasIpv6DefaultRoute();
        if (this.mHasDisabledIPv6OnProvLoss || ((provisioningConfiguration = this.mConfiguration) != null && provisioningConfiguration.mUsingMultinetworkPolicyTracker && !this.mCm.shouldAvoidBadWifi())) {
            z = true;
        }
        if (z3 || (z2 && !z)) {
            i = 2;
        }
        if (!linkProperties.hasGlobalIpv6Address() || !z4 || z) {
            return i;
        }
        if (!linkProperties2.isIpv4Provisioned()) {
            return 2;
        }
        this.mInterfaceCtrl.disableIPv6();
        this.mHasDisabledIPv6OnProvLoss = true;
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCallback(int i, LinkProperties linkProperties) {
        if (i == 2) {
            recordMetric(2);
            this.mCallback.onProvisioningFailure(linkProperties);
        } else if (i == 3) {
            recordMetric(1);
            this.mCallback.onProvisioningSuccess(linkProperties);
        } else {
            this.mCallback.onLinkPropertiesChange(linkProperties);
        }
    }

    private int setLinkProperties(LinkProperties linkProperties) {
        ApfFilter apfFilter = this.mApfFilter;
        if (apfFilter != null) {
            apfFilter.setLinkProperties(linkProperties);
        }
        IpReachabilityMonitor ipReachabilityMonitor = this.mIpReachabilityMonitor;
        if (ipReachabilityMonitor != null) {
            ipReachabilityMonitor.updateLinkProperties(linkProperties);
        }
        int iCompareProvisioning = compareProvisioning(this.mLinkProperties, linkProperties);
        this.mLinkProperties = new LinkProperties(linkProperties);
        if (iCompareProvisioning == 3) {
            this.mProvisioningTimeoutAlarm.cancel();
        }
        return iCompareProvisioning;
    }

    private LinkProperties assembleLinkProperties() {
        InitialConfiguration initialConfiguration;
        LinkProperties linkProperties = new LinkProperties();
        linkProperties.setInterfaceName(this.mInterfaceName);
        LinkProperties linkProperties2 = this.mLinkObserver.getLinkProperties();
        linkProperties.setLinkAddresses(linkProperties2.getLinkAddresses());
        Iterator<RouteInfo> it = linkProperties2.getRoutes().iterator();
        while (it.hasNext()) {
            linkProperties.addRoute(it.next());
        }
        addAllReachableDnsServers(linkProperties, linkProperties2.getDnsServers());
        linkProperties.setNat64Prefix(linkProperties2.getNat64Prefix());
        DhcpResults dhcpResults = this.mDhcpResults;
        if (dhcpResults != null) {
            Iterator it2 = dhcpResults.toStaticIpConfiguration().getRoutes(this.mInterfaceName).iterator();
            while (it2.hasNext()) {
                linkProperties.addRoute((RouteInfo) it2.next());
            }
            addAllReachableDnsServers(linkProperties, this.mDhcpResults.dnsServers);
            linkProperties.setDomains(this.mDhcpResults.domains);
            int i = this.mDhcpResults.mtu;
            if (i != 0) {
                linkProperties.setMtu(i);
            }
            Inet4Address inet4Address = this.mDhcpResults.serverAddress;
            if (inet4Address != null) {
                this.mShim.setDhcpServerAddress(linkProperties, inet4Address);
            }
            String str = this.mDhcpResults.captivePortalApiUrl;
            if (isParseableUrl(str)) {
                NetworkInformationShimImpl.newInstance().setCaptivePortalApiUrl(linkProperties, Uri.parse(str));
            }
        }
        if (!TextUtils.isEmpty(this.mTcpBufferSizes)) {
            linkProperties.setTcpBufferSizes(this.mTcpBufferSizes);
        }
        ProxyInfo proxyInfo = this.mHttpProxy;
        if (proxyInfo != null) {
            linkProperties.setHttpProxy(proxyInfo);
        }
        ProvisioningConfiguration provisioningConfiguration = this.mConfiguration;
        if (provisioningConfiguration != null && (initialConfiguration = provisioningConfiguration.mInitialConfig) != null) {
            if (initialConfiguration.isProvisionedBy(linkProperties.getLinkAddresses(), null)) {
                Iterator<IpPrefix> it3 = initialConfiguration.directlyConnectedRoutes.iterator();
                while (it3.hasNext()) {
                    linkProperties.addRoute(new RouteInfo(it3.next(), null, this.mInterfaceName, 1));
                }
            }
            addAllReachableDnsServers(linkProperties, initialConfiguration.dnsServers);
        }
        return linkProperties;
    }

    private static boolean isParseableUrl(String str) {
        if (str == null) {
            return false;
        }
        try {
            new URL(str);
            return true;
        } catch (MalformedURLException unused) {
            return false;
        }
    }

    private static void addAllReachableDnsServers(LinkProperties linkProperties, Iterable<InetAddress> iterable) {
        for (InetAddress inetAddress : iterable) {
            if (!inetAddress.isAnyLocalAddress() && linkProperties.isReachable(inetAddress)) {
                linkProperties.addDnsServer(inetAddress);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleLinkPropertiesUpdate(boolean z) {
        LinkProperties linkPropertiesAssembleLinkProperties = assembleLinkProperties();
        if (Objects.equals(linkPropertiesAssembleLinkProperties, this.mLinkProperties)) {
            return true;
        }
        this.mIpProvisioningMetrics.setIPv4ProvisionedLatencyOnFirstTime(linkPropertiesAssembleLinkProperties.isIpv4Provisioned());
        this.mIpProvisioningMetrics.setIPv6ProvisionedLatencyOnFirstTime(linkPropertiesAssembleLinkProperties.isIpv6Provisioned());
        int linkProperties = setLinkProperties(linkPropertiesAssembleLinkProperties);
        maybeSaveNetworkToIpMemoryStore();
        if (z) {
            dispatchCallback(linkProperties, linkPropertiesAssembleLinkProperties);
        }
        return linkProperties != 2;
    }

    static String removeDoubleQuotes(String str) {
        int length = str.length();
        if (length <= 1 || str.charAt(0) != '\"') {
            return str;
        }
        int i = length - 1;
        return str.charAt(i) == '\"' ? str.substring(1, i) : str;
    }

    private List<ByteBuffer> getVendorSpecificIEs(ProvisioningConfiguration.ScanResultInfo scanResultInfo) {
        ArrayList arrayList = new ArrayList();
        for (ProvisioningConfiguration.ScanResultInfo.InformationElement informationElement : scanResultInfo.getInformationElements()) {
            if (informationElement.getId() == 221) {
                arrayList.add(informationElement.getPayload());
            }
        }
        return arrayList;
    }

    private boolean detectUpstreamHotspotFromVendorIe() {
        ProvisioningConfiguration.ScanResultInfo scanResultInfo = this.mConfiguration.mScanResultInfo;
        if (scanResultInfo == null) {
            return false;
        }
        String ssid = scanResultInfo.getSsid();
        List<ByteBuffer> vendorSpecificIEs = getVendorSpecificIEs(scanResultInfo);
        String str = this.mConfiguration.mDisplayName;
        if (str != null && removeDoubleQuotes(str).equals(ssid)) {
            Iterator<ByteBuffer> it = vendorSpecificIEs.iterator();
            while (it.hasNext()) {
                byte[] bArr = new byte[4];
                try {
                    it.next().get(bArr);
                    Iterator<byte[]> it2 = METERED_IE_PATTERN_LIST.iterator();
                    while (it2.hasNext()) {
                        if (Arrays.equals(it2.next(), bArr)) {
                            return true;
                        }
                    }
                } catch (BufferUnderflowException unused) {
                    Log.e(this.mTag, "Couldn't parse vendor specific IE, buffer underflow");
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIPv4Success(DhcpResults dhcpResults) {
        this.mDhcpResults = new DhcpResults(dhcpResults);
        LinkProperties linkPropertiesAssembleLinkProperties = assembleLinkProperties();
        int linkProperties = setLinkProperties(linkPropertiesAssembleLinkProperties);
        if (this.mDhcpResults.vendorInfo == null && detectUpstreamHotspotFromVendorIe()) {
            this.mDhcpResults.vendorInfo = "ANDROID_METERED";
        }
        this.mCallback.onNewDhcpResults(this.mDhcpResults);
        maybeSaveNetworkToIpMemoryStore();
        dispatchCallback(linkProperties, linkPropertiesAssembleLinkProperties);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIPv4Failure() {
        this.mInterfaceCtrl.clearIPv4Address();
        this.mDhcpResults = null;
        this.mCallback.onNewDhcpResults(null);
        handleProvisioningFailure(DisconnectCode.DC_PROVISIONING_FAIL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleProvisioningFailure(DisconnectCode disconnectCode) {
        LinkProperties linkPropertiesAssembleLinkProperties = assembleLinkProperties();
        int linkProperties = setLinkProperties(linkPropertiesAssembleLinkProperties);
        if (linkProperties == 1) {
            linkProperties = 2;
        }
        dispatchCallback(linkProperties, linkPropertiesAssembleLinkProperties);
        if (linkProperties == 2) {
            transitionToStoppingState(disconnectCode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doImmediateProvisioningFailure(int i) {
        logError("onProvisioningFailure(): %s", Integer.valueOf(i));
        recordMetric(i);
        this.mCallback.onProvisioningFailure(this.mLinkProperties);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startIPv4() {
        StaticIpConfiguration staticIpConfiguration = this.mConfiguration.mStaticIpConfig;
        if (staticIpConfiguration != null) {
            if (!this.mInterfaceCtrl.setIPv4Address(staticIpConfiguration.getIpAddress())) {
                return false;
            }
            handleIPv4Success(new DhcpResults(this.mConfiguration.mStaticIpConfig));
            return true;
        }
        if (this.mDhcpClient != null) {
            Log.wtf(this.mTag, "DhcpClient should never be non-null in startIPv4()");
        }
        startDhcpClient();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startIPv6() {
        return this.mInterfaceCtrl.setIPv6PrivacyExtensions(true) && this.mInterfaceCtrl.setIPv6AddrGenModeIfSupported(this.mConfiguration.mIPv6AddrGenMode) && this.mInterfaceCtrl.enableIPv6();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean applyInitialConfig(InitialConfiguration initialConfiguration) {
        Iterator it = findAll(initialConfiguration.ipAddresses, new Predicate() { // from class: android.net.ip.-$$Lambda$k-eHyZd-cKxxp4Kcp-stEfOXet8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((LinkAddress) obj).isIpv6();
            }
        }).iterator();
        while (it.hasNext()) {
            if (!this.mInterfaceCtrl.addAddress((LinkAddress) it.next())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startIpReachabilityMonitor() {
        try {
            this.mIpReachabilityMonitor = new IpReachabilityMonitor(this.mContext, this.mInterfaceParams, getHandler(), this.mLog, new IpReachabilityMonitor.Callback() { // from class: android.net.ip.IpClient.2
                @Override // android.net.ip.IpReachabilityMonitor.Callback
                public void notifyLost(InetAddress inetAddress, String str) {
                    IpClient.this.mCallback.onReachabilityLost(str);
                }
            }, this.mConfiguration.mUsingMultinetworkPolicyTracker, this.mNetd);
        } catch (IllegalArgumentException e) {
            logError("IpReachabilityMonitor failure: %s", e, new Object[0]);
            this.mIpReachabilityMonitor = null;
        }
        return this.mIpReachabilityMonitor != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAllIP() {
        this.mInterfaceCtrl.disableIPv6();
        this.mInterfaceCtrl.clearAllAddresses();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeRestoreInterfaceMtu() {
        InterfaceParams interfaceParams = this.mDependencies.getInterfaceParams(this.mInterfaceName);
        if (interfaceParams == null) {
            Log.w(this.mTag, "interface: " + this.mInterfaceName + " is gone");
            return;
        }
        int i = interfaceParams.index;
        InterfaceParams interfaceParams2 = this.mInterfaceParams;
        if (i != interfaceParams2.index) {
            Log.w(this.mTag, "interface: " + this.mInterfaceName + " has a different index: " + interfaceParams.index);
            return;
        }
        int i2 = interfaceParams.defaultMtu;
        int i3 = interfaceParams2.defaultMtu;
        if (i2 == i3) {
            return;
        }
        try {
            this.mNetd.interfaceSetMtu(this.mInterfaceName, i3);
        } catch (RemoteException | ServiceSpecificException e) {
            logError("Couldn't reset MTU on " + this.mInterfaceName + " from " + interfaceParams.defaultMtu + " to " + this.mInterfaceParams.defaultMtu, e, new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUpdateL2Information(Layer2InformationParcelable layer2InformationParcelable) {
        MacAddress macAddress;
        DhcpClient dhcpClient;
        this.mL2Key = layer2InformationParcelable.l2Key;
        String str = layer2InformationParcelable.cluster;
        MacAddress macAddress2 = layer2InformationParcelable.bssid;
        if (macAddress2 == null || (macAddress = this.mCurrentBssid) == null) {
            Log.wtf(this.mTag, "bssid in the parcelable or current tracked bssid should be non-null");
            return;
        }
        if (macAddress2.equals(macAddress)) {
            return;
        }
        IpReachabilityMonitor ipReachabilityMonitor = this.mIpReachabilityMonitor;
        if (ipReachabilityMonitor != null) {
            ipReachabilityMonitor.probeAll();
        }
        if (DHCP_ROAMING_SSID_SET.contains(removeDoubleQuotes(this.mConfiguration.mDisplayName)) && (dhcpClient = this.mDhcpClient) != null) {
            dhcpClient.sendMessage(DhcpClient.CMD_REFRESH_LINKADDRESS);
        }
        this.mCurrentBssid = layer2InformationParcelable.bssid;
    }

    class StoppedState extends State {
        StoppedState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            IpClient.this.stopAllIP();
            IpClient.this.mHasDisabledIPv6OnProvLoss = false;
            IpClient.this.mLinkObserver.clearInterfaceParams();
            IpClient.this.resetLinkProperties();
            if (IpClient.this.mStartTimeMillis > 0) {
                IpClient ipClient = IpClient.this;
                ipClient.mCallback.onLinkPropertiesChange(ipClient.mLinkProperties);
                IpClient.this.recordMetric(3);
                IpClient.this.mStartTimeMillis = 0L;
            }
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                IpClient.this.stopStateMachineUpdaters();
                IpClient.this.quit();
            } else if (i != 2) {
                if (i == 3) {
                    IpClient.this.mConfiguration = (ProvisioningConfiguration) message.obj;
                    IpClient ipClient = IpClient.this;
                    ipClient.transitionTo(ipClient.mClearingIpAddressesState);
                } else if (i == IpClient.CMD_UPDATE_L2KEY_CLUSTER) {
                    Pair pair = (Pair) message.obj;
                    IpClient.this.mL2Key = (String) pair.first;
                    IpClient.this.mCluster = (String) pair.second;
                } else if (i != 1005) {
                    switch (i) {
                        case 6:
                            IpClient.this.handleLinkPropertiesUpdate(false);
                            break;
                        case 7:
                            IpClient.this.mTcpBufferSizes = (String) message.obj;
                            IpClient.this.handleLinkPropertiesUpdate(false);
                            break;
                        case IpClient.CMD_UPDATE_HTTP_PROXY /* 8 */:
                            IpClient.this.mHttpProxy = (ProxyInfo) message.obj;
                            IpClient.this.handleLinkPropertiesUpdate(false);
                            break;
                        case IpClient.CMD_SET_MULTICAST_FILTER /* 9 */:
                            IpClient.this.mMulticastFiltering = ((Boolean) message.obj).booleanValue();
                            break;
                        default:
                            return false;
                    }
                } else {
                    IpClient.this.logError("Unexpected CMD_ON_QUIT (already stopped).", new Object[0]);
                }
            }
            IpClient.this.mMsgStateLogger.handled(this, IpClient.this.getCurrentState());
            return true;
        }
    }

    class StoppingState extends State {
        StoppingState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            if (IpClient.this.mDhcpClient == null) {
                IpClient ipClient = IpClient.this;
                ipClient.deferMessage(ipClient.obtainMessage(IpClient.CMD_JUMP_STOPPING_TO_STOPPED));
            }
            IpClient.this.maybeRestoreInterfaceMtu();
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i != 2) {
                if (i == IpClient.CMD_JUMP_STOPPING_TO_STOPPED) {
                    IpClient ipClient = IpClient.this;
                    ipClient.transitionTo(ipClient.mStoppedState);
                } else if (i == 1005) {
                    IpClient.this.mDhcpClient = null;
                    IpClient ipClient2 = IpClient.this;
                    ipClient2.transitionTo(ipClient2.mStoppedState);
                } else if (i == 1007) {
                    IpClient.this.mInterfaceCtrl.clearIPv4Address();
                } else {
                    IpClient.this.deferMessage(message);
                }
            }
            IpClient.this.mMsgStateLogger.handled(this, IpClient.this.getCurrentState());
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUsingPreconnection() {
        ProvisioningConfiguration provisioningConfiguration = this.mConfiguration;
        return provisioningConfiguration.mEnablePreconnection && provisioningConfiguration.mStaticIpConfig == null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDhcpClient() {
        Dependencies dependencies = this.mDependencies;
        this.mDhcpClient = dependencies.makeDhcpClient(this.mContext, this, this.mInterfaceParams, dependencies.getDhcpClientDependencies(this.mIpMemoryStore, this.mIpProvisioningMetrics));
        if (!isUsingPreconnection()) {
            this.mDhcpClient.registerForPreDhcpNotification();
        }
        this.mDhcpClient.sendMessage(DhcpClient.CMD_START_DHCP, new DhcpClient.Configuration(this.mL2Key, isUsingPreconnection()));
    }

    class ClearingIpAddressesState extends State {
        ClearingIpAddressesState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            IpClient ipClient = IpClient.this;
            ipClient.mInterfaceParams = ipClient.mDependencies.getInterfaceParams(IpClient.this.mInterfaceName);
            if (IpClient.this.mInterfaceParams != null) {
                IpClient.this.mLinkObserver.setInterfaceParams(IpClient.this.mInterfaceParams);
                if (!readyToProceed()) {
                    IpClient.this.stopAllIP();
                } else {
                    IpClient ipClient2 = IpClient.this;
                    ipClient2.deferMessage(ipClient2.obtainMessage(IpClient.CMD_ADDRESSES_CLEARED));
                }
                IpClient.this.mCallback.setNeighborDiscoveryOffload(true);
                return;
            }
            IpClient.this.logError("Failed to find InterfaceParams for " + IpClient.this.mInterfaceName, new Object[0]);
            IpClient.this.doImmediateProvisioningFailure(IpClient.CMD_UPDATE_HTTP_PROXY);
            IpClient ipClient3 = IpClient.this;
            ipClient3.deferMessage(ipClient3.obtainMessage(2, DisconnectCode.DC_INTERFACE_NOT_FOUND.getNumber()));
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i != 2) {
                if (i == 6) {
                    IpClient.this.handleLinkPropertiesUpdate(false);
                    if (!readyToProceed()) {
                        return true;
                    }
                    IpClient ipClient = IpClient.this;
                    boolean zIsUsingPreconnection = ipClient.isUsingPreconnection();
                    IpClient ipClient2 = IpClient.this;
                    ipClient.transitionTo(zIsUsingPreconnection ? ipClient2.mPreconnectingState : ipClient2.mRunningState);
                    return true;
                }
                if (i != IpClient.EVENT_PROVISIONING_TIMEOUT) {
                    if (i == IpClient.CMD_ADDRESSES_CLEARED) {
                        IpClient ipClient3 = IpClient.this;
                        boolean zIsUsingPreconnection2 = ipClient3.isUsingPreconnection();
                        IpClient ipClient4 = IpClient.this;
                        ipClient3.transitionTo(zIsUsingPreconnection2 ? ipClient4.mPreconnectingState : ipClient4.mRunningState);
                        return true;
                    }
                    IpClient.this.deferMessage(message);
                    return true;
                }
            }
            return false;
        }

        private boolean readyToProceed() {
            return (IpClient.this.mLinkProperties.hasIpv4Address() || IpClient.this.mLinkProperties.hasGlobalIpv6Address()) ? false : true;
        }
    }

    class PreconnectingState extends State {
        PreconnectingState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            IpClient.this.startDhcpClient();
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 2 || i == IpClient.EVENT_PROVISIONING_TIMEOUT) {
                return false;
            }
            if (i == IpClient.CMD_COMPLETE_PRECONNECTION) {
                boolean z = message.arg1 == 1;
                IpClient.this.mDhcpClient.registerForPreDhcpNotification();
                if (!z) {
                    IpClient.this.mDhcpClient.sendMessage(DhcpClient.CMD_ABORT_PRECONNECTION);
                }
                IpClient ipClient = IpClient.this;
                ipClient.transitionTo(ipClient.mRunningState);
            } else if (i == 1010) {
                IpClient.this.mCallback.onPreconnectionStart(Collections.singletonList((Layer2PacketParcelable) message.obj));
            } else {
                IpClient.this.deferMessage(message);
            }
            return true;
        }
    }

    class StartedState extends State {
        StartedState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            IpClient.this.mIpProvisioningMetrics.reset();
            IpClient.this.mStartTimeMillis = SystemClock.elapsedRealtime();
            if (IpClient.this.mConfiguration.mProvisioningTimeoutMs > 0) {
                IpClient.this.mProvisioningTimeoutAlarm.schedule(SystemClock.elapsedRealtime() + ((long) IpClient.this.mConfiguration.mProvisioningTimeoutMs));
            }
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            IpClient.this.mProvisioningTimeoutAlarm.cancel();
            IpClient.this.mIpProvisioningMetrics.statsWrite();
        }

        @Override // android.net.networkstack.util.State
        public boolean processMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                IpClient.this.transitionToStoppingState(DisconnectCode.forNumber(message.arg1));
            } else if (i == IpClient.EVENT_PROVISIONING_TIMEOUT) {
                IpClient.this.handleProvisioningFailure(DisconnectCode.DC_PROVISIONING_TIMEOUT);
            } else if (i == IpClient.CMD_UPDATE_L2KEY_CLUSTER) {
                Pair pair = (Pair) message.obj;
                IpClient.this.mL2Key = (String) pair.first;
                IpClient.this.mCluster = (String) pair.second;
            } else {
                if (i != IpClient.CMD_UPDATE_L2INFORMATION) {
                    return false;
                }
                IpClient.this.handleUpdateL2Information((Layer2InformationParcelable) message.obj);
            }
            IpClient.this.mMsgStateLogger.handled(this, IpClient.this.getCurrentState());
            return true;
        }
    }

    class RunningState extends State {
        private boolean mDhcpActionInFlight;
        private ConnectivityPacketTracker mPacketTracker;

        RunningState() {
        }

        @Override // android.net.networkstack.util.State
        public void enter() {
            ApfFilter.ApfConfiguration apfConfiguration = new ApfFilter.ApfConfiguration();
            apfConfiguration.apfCapabilities = IpClient.this.mConfiguration.mApfCapabilities;
            apfConfiguration.multicastFilter = IpClient.this.mMulticastFiltering;
            apfConfiguration.ieee802_3Filter = ApfCapabilities.getApfDrop8023Frames();
            apfConfiguration.ethTypeBlackList = ApfCapabilities.getApfEtherTypeBlackList();
            apfConfiguration.minRdnssLifetimeSec = IpClient.this.mMinRdnssLifetimeSec;
            IpClient ipClient = IpClient.this;
            ipClient.mApfFilter = ApfFilter.maybeCreate(ipClient.mContext, apfConfiguration, IpClient.this.mInterfaceParams, IpClient.this.mCallback);
            if (IpClient.this.mApfFilter == null) {
                IpClient ipClient2 = IpClient.this;
                ipClient2.mCallback.setFallbackMulticastFilter(ipClient2.mMulticastFiltering);
            }
            ConnectivityPacketTracker connectivityPacketTrackerCreatePacketTracker = createPacketTracker();
            this.mPacketTracker = connectivityPacketTrackerCreatePacketTracker;
            if (connectivityPacketTrackerCreatePacketTracker != null) {
                connectivityPacketTrackerCreatePacketTracker.start(IpClient.this.mConfiguration.mDisplayName);
            }
            if (!IpClient.this.mConfiguration.mEnableIPv6 || IpClient.this.startIPv6()) {
                if (!IpClient.this.mConfiguration.mEnableIPv4 || IpClient.this.isUsingPreconnection() || IpClient.this.startIPv4()) {
                    InitialConfiguration initialConfiguration = IpClient.this.mConfiguration.mInitialConfig;
                    if (initialConfiguration == null || IpClient.this.applyInitialConfig(initialConfiguration)) {
                        if (!IpClient.this.mConfiguration.mUsingIpReachabilityMonitor || IpClient.this.startIpReachabilityMonitor()) {
                            return;
                        }
                        IpClient.this.doImmediateProvisioningFailure(6);
                        enqueueJumpToStoppingState(DisconnectCode.DC_ERROR_STARTING_IPREACHABILITYMONITOR);
                        return;
                    }
                    IpClient.this.doImmediateProvisioningFailure(7);
                    enqueueJumpToStoppingState(DisconnectCode.DC_INVALID_PROVISIONING);
                    return;
                }
                IpClient.this.doImmediateProvisioningFailure(4);
                enqueueJumpToStoppingState(DisconnectCode.DC_ERROR_STARTING_IPV4);
                return;
            }
            IpClient.this.doImmediateProvisioningFailure(5);
            enqueueJumpToStoppingState(DisconnectCode.DC_ERROR_STARTING_IPV6);
        }

        @Override // android.net.networkstack.util.State
        public void exit() {
            stopDhcpAction();
            if (IpClient.this.mIpReachabilityMonitor != null) {
                IpClient.this.mIpReachabilityMonitor.stop();
                IpClient.this.mIpReachabilityMonitor = null;
            }
            if (IpClient.this.mDhcpClient != null) {
                IpClient.this.mDhcpClient.sendMessage(DhcpClient.CMD_STOP_DHCP);
                IpClient.this.mDhcpClient.doQuit();
            }
            ConnectivityPacketTracker connectivityPacketTracker = this.mPacketTracker;
            if (connectivityPacketTracker != null) {
                connectivityPacketTracker.stop();
                this.mPacketTracker = null;
            }
            if (IpClient.this.mApfFilter != null) {
                IpClient.this.mApfFilter.shutdown();
                IpClient.this.mApfFilter = null;
            }
            IpClient.this.resetLinkProperties();
        }

        private void enqueueJumpToStoppingState(DisconnectCode disconnectCode) {
            IpClient ipClient = IpClient.this;
            ipClient.deferMessage(ipClient.obtainMessage(IpClient.CMD_JUMP_RUNNING_TO_STOPPING, disconnectCode.getNumber()));
        }

        private ConnectivityPacketTracker createPacketTracker() {
            try {
                return new ConnectivityPacketTracker(IpClient.this.getHandler(), IpClient.this.mInterfaceParams, IpClient.this.mConnectivityPacketLog);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        private void ensureDhcpAction() {
            if (this.mDhcpActionInFlight) {
                return;
            }
            IpClient.this.mCallback.onPreDhcpAction();
            this.mDhcpActionInFlight = true;
            IpClient.this.mDhcpActionTimeoutAlarm.schedule(SystemClock.elapsedRealtime() + ((long) IpClient.this.mConfiguration.mRequestedPreDhcpActionMs));
        }

        private void stopDhcpAction() {
            IpClient.this.mDhcpActionTimeoutAlarm.cancel();
            if (this.mDhcpActionInFlight) {
                IpClient.this.mCallback.onPostDhcpAction();
                this.mDhcpActionInFlight = false;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:67:0x01b9  */
        @Override // android.net.networkstack.util.State
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean processMessage(android.os.Message r6) {
            /*
                Method dump skipped, instruction units count: 510
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.net.ip.IpClient.RunningState.processMessage(android.os.Message):boolean");
        }
    }

    private static class MessageHandlingLogger {
        public String processedInState;
        public String receivedInState;

        private MessageHandlingLogger() {
        }

        /* synthetic */ MessageHandlingLogger(AnonymousClass1 anonymousClass1) {
            this();
        }

        public void reset() {
            this.processedInState = null;
            this.receivedInState = null;
        }

        public void handled(State state, IState iState) {
            this.processedInState = state.getClass().getSimpleName();
            this.receivedInState = iState.getName();
        }

        public String toString() {
            return String.format("rcvd_in=%s, proc_in=%s", this.receivedInState, this.processedInState);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> List<T> findAll(Collection<T> collection, Predicate<T> predicate) {
        return (List) collection.stream().filter(predicate).collect(Collectors.toList());
    }
}
