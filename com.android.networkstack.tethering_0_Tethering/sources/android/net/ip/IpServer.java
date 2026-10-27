package android.net.ip;

import android.net.INetd;
import android.net.INetworkStackStatusCallback;
import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.MacAddress;
import android.net.RouteInfo;
import android.net.TetheredClient;
import android.net.TetheringManager;
import android.net.TetheringRequestParcel;
import android.net.dhcp.DhcpLeaseParcelable;
import android.net.dhcp.DhcpServerCallbacks;
import android.net.dhcp.DhcpServingParamsParcel;
import android.net.dhcp.DhcpServingParamsParcelExt;
import android.net.dhcp.IDhcpEventCallbacks;
import android.net.dhcp.IDhcpServer;
import android.net.ip.IpNeighborMonitor;
import android.net.ip.RouterAdvertisementDaemon;
import android.net.shared.NetdUtils;
import android.net.shared.RouteUtils;
import android.net.util.InterfaceParams;
import android.net.util.InterfaceSet;
import android.net.util.NetworkConstants;
import android.net.util.PrefixUtils;
import android.net.util.SharedLog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.os.SystemProperties;
import android.system.OsConstants;
import android.util.Log;
import android.util.SparseArray;
import com.android.networkstack.tethering.BpfCoordinator;
import com.android.networkstack.tethering.PrivateAddressCoordinator;
import com.android.networkstack.tethering.util.Inet4AddressUtils;
import com.android.networkstack.tethering.util.MessageUtils;
import com.android.networkstack.tethering.util.State;
import com.android.networkstack.tethering.util.StateMachine;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class IpServer extends StateMachine {
    public static final int CMD_INTERFACE_DOWN = 103;
    public static final int CMD_IPV6_TETHER_UPDATE = 110;
    public static final int CMD_IP_FORWARDING_DISABLE_ERROR = 105;
    public static final int CMD_IP_FORWARDING_ENABLE_ERROR = 104;
    public static final int CMD_NEIGHBOR_EVENT = 111;
    public static final int CMD_NEW_PREFIX_REQUEST = 112;
    public static final int CMD_NOTIFY_PREFIX_CONFLICT = 113;
    public static final int CMD_SET_DNS_FORWARDERS_ERROR = 108;
    public static final int CMD_START_TETHERING_ERROR = 106;
    public static final int CMD_STOP_TETHERING_ERROR = 107;
    public static final int CMD_TETHER_CONNECTION_CHANGED = 109;
    public static final int CMD_TETHER_REQUESTED = 101;
    public static final int CMD_TETHER_UNREQUESTED = 102;
    private static final MacAddress NULL_MAC_ADDRESS = MacAddress.fromString("00:00:00:00:00:00");
    private static final SparseArray<String> sMagicDecoderRing;
    private static final Class[] sMessageClasses;
    private final BpfCoordinator mBpfCoordinator;
    private final Callback mCallback;
    private final Dependencies mDeps;
    private List<TetheredClient> mDhcpLeases;
    private IDhcpServer mDhcpServer;
    private int mDhcpServerStartIndex;
    private final String mIfaceName;
    private final State mInitialState;
    private final InterfaceController mInterfaceCtrl;
    private InterfaceParams mInterfaceParams;
    private final int mInterfaceType;
    private final IpNeighborMonitor mIpNeighborMonitor;
    private LinkAddress mIpv4Address;
    private int mLastError;
    private LinkProperties mLastIPv6LinkProperties;
    private int mLastIPv6UpstreamIfindex;
    private RouterAdvertisementDaemon.RaParams mLastRaParams;
    private final LinkProperties mLinkProperties;
    private final State mLocalHotspotState;
    private final SharedLog mLog;
    private final INetd mNetd;
    private final PrivateAddressCoordinator mPrivateAddressCoordinator;
    private RouterAdvertisementDaemon mRaDaemon;
    private int mServingMode;
    private LinkAddress mStaticIpv4ClientAddr;
    private LinkAddress mStaticIpv4ServerAddr;
    private final State mTetheredState;
    private final State mUnavailableState;
    private InterfaceSet mUpstreamIfaceSet;
    private final boolean mUsingBpfOffload;
    private final boolean mUsingLegacyDhcp;
    private final State mWaitingForRestartState;

    public static class Callback {
        public abstract void dhcpLeasesChanged();

        public abstract void requestEnableTethering(int i, boolean z);

        public abstract void updateInterfaceState(IpServer ipServer, int i, int i2);

        public abstract void updateLinkProperties(IpServer ipServer, LinkProperties linkProperties);
    }

    public static String getStateString(int i) {
        if (i == 0) {
            return "UNAVAILABLE";
        }
        if (i == 1) {
            return "AVAILABLE";
        }
        if (i == 2) {
            return "TETHERED";
        }
        if (i == 3) {
            return "LOCAL_ONLY";
        }
        return "UNKNOWN: " + i;
    }

    static {
        Class[] clsArr = {IpServer.class};
        sMessageClasses = clsArr;
        sMagicDecoderRing = MessageUtils.findMessageNames(clsArr);
    }

    public static abstract class Dependencies {
        public abstract void makeDhcpServer(String str, DhcpServingParamsParcel dhcpServingParamsParcel, DhcpServerCallbacks dhcpServerCallbacks);

        public IpNeighborMonitor getIpNeighborMonitor(Handler handler, SharedLog sharedLog, IpNeighborMonitor.NeighborEventConsumer neighborEventConsumer) {
            return new IpNeighborMonitor(handler, sharedLog, neighborEventConsumer);
        }

        public RouterAdvertisementDaemon getRouterAdvertisementDaemon(InterfaceParams interfaceParams) {
            return new RouterAdvertisementDaemon(interfaceParams);
        }

        public InterfaceParams getInterfaceParams(String str) {
            return InterfaceParams.getByName(str);
        }

        public int getIfindex(String str) {
            try {
                return NetworkInterface.getByName(str).getIndex();
            } catch (IOException | NullPointerException unused) {
                Log.e("IpServer", "Can't determine interface index for interface " + str);
                return 0;
            }
        }
    }

    private class MyNeighborEventConsumer implements IpNeighborMonitor.NeighborEventConsumer {
        private MyNeighborEventConsumer() {
        }

        @Override // android.net.ip.IpNeighborMonitor.NeighborEventConsumer
        public void accept(IpNeighborMonitor.NeighborEvent neighborEvent) {
            IpServer.this.sendMessage(IpServer.CMD_NEIGHBOR_EVENT, neighborEvent);
        }
    }

    public IpServer(String str, Looper looper, int i, SharedLog sharedLog, INetd iNetd, BpfCoordinator bpfCoordinator, Callback callback, boolean z, boolean z2, PrivateAddressCoordinator privateAddressCoordinator, Dependencies dependencies) {
        super(str, looper);
        this.mDhcpServerStartIndex = 0;
        this.mDhcpLeases = Collections.emptyList();
        this.mLastIPv6UpstreamIfindex = 0;
        SharedLog sharedLogForSubComponent = sharedLog.forSubComponent(str);
        this.mLog = sharedLogForSubComponent;
        this.mNetd = iNetd;
        this.mBpfCoordinator = bpfCoordinator;
        this.mCallback = callback;
        this.mInterfaceCtrl = new InterfaceController(str, iNetd, sharedLogForSubComponent);
        this.mIfaceName = str;
        this.mInterfaceType = i;
        this.mLinkProperties = new LinkProperties();
        this.mUsingLegacyDhcp = z;
        this.mUsingBpfOffload = z2;
        this.mPrivateAddressCoordinator = privateAddressCoordinator;
        this.mDeps = dependencies;
        resetLinkProperties();
        this.mLastError = 0;
        this.mServingMode = 1;
        IpNeighborMonitor ipNeighborMonitor = this.mDeps.getIpNeighborMonitor(getHandler(), this.mLog, new MyNeighborEventConsumer());
        this.mIpNeighborMonitor = ipNeighborMonitor;
        if (this.mUsingBpfOffload && !ipNeighborMonitor.start()) {
            this.mLog.e("Failed to create IpNeighborMonitor on " + this.mIfaceName);
        }
        this.mInitialState = new InitialState();
        this.mLocalHotspotState = new LocalHotspotState();
        this.mTetheredState = new TetheredState();
        this.mUnavailableState = new UnavailableState();
        this.mWaitingForRestartState = new WaitingForRestartState();
        addState(this.mInitialState);
        addState(this.mLocalHotspotState);
        addState(this.mTetheredState);
        addState(this.mWaitingForRestartState, this.mTetheredState);
        addState(this.mUnavailableState);
        setInitialState(this.mInitialState);
    }

    public String interfaceName() {
        return this.mIfaceName;
    }

    public int interfaceType() {
        return this.mInterfaceType;
    }

    public int lastError() {
        return this.mLastError;
    }

    public int servingMode() {
        return this.mServingMode;
    }

    public LinkProperties linkProperties() {
        return new LinkProperties(this.mLinkProperties);
    }

    public LinkAddress getAddress() {
        return this.mIpv4Address;
    }

    public List<TetheredClient> getAllLeases() {
        return Collections.unmodifiableList(this.mDhcpLeases);
    }

    public void stop() {
        sendMessage(CMD_INTERFACE_DOWN);
    }

    public void unwanted() {
        sendMessage(CMD_TETHER_UNREQUESTED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startIPv4() {
        return configureIPv4(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    abstract class OnHandlerStatusCallback extends INetworkStackStatusCallback.Stub {
        /* JADX INFO: renamed from: callback, reason: merged with bridge method [inline-methods] */
        public abstract void lambda$onStatusAvailable$0$IpServer$OnHandlerStatusCallback(int i);

        @Override // android.net.INetworkStackStatusCallback
        public String getInterfaceHash() {
            return "02cd6fd07d5c04eca0c35a350f7b0be576242883";
        }

        @Override // android.net.INetworkStackStatusCallback
        public int getInterfaceVersion() {
            return 7;
        }

        private OnHandlerStatusCallback() {
        }

        @Override // android.net.INetworkStackStatusCallback
        public void onStatusAvailable(final int i) {
            IpServer.this.getHandler().post(new Runnable() { // from class: android.net.ip.-$$Lambda$IpServer$OnHandlerStatusCallback$czoKoFz-ZQJY8J5O14qT9czTIoo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStatusAvailable$0$IpServer$OnHandlerStatusCallback(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DhcpServerCallbacksImpl extends DhcpServerCallbacks {
        private final int mStartIndex;

        private DhcpServerCallbacksImpl(int i) {
            this.mStartIndex = i;
        }

        @Override // android.net.dhcp.IDhcpServerCallbacks
        public void onDhcpServerCreated(final int i, final IDhcpServer iDhcpServer) throws RemoteException {
            IpServer.this.getHandler().post(new Runnable() { // from class: android.net.ip.-$$Lambda$IpServer$DhcpServerCallbacksImpl$nBlfeyPZEu2j0KBs4BJklDJTve4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDhcpServerCreated$0$IpServer$DhcpServerCallbacksImpl(i, iDhcpServer);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onDhcpServerCreated$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onDhcpServerCreated$0$IpServer$DhcpServerCallbacksImpl(int i, IDhcpServer iDhcpServer) {
            if (this.mStartIndex != IpServer.this.mDhcpServerStartIndex) {
                return;
            }
            if (i != 1) {
                IpServer.this.mLog.e("Error obtaining DHCP server: " + i);
                handleError();
                return;
            }
            IpServer.this.mDhcpServer = iDhcpServer;
            try {
                IpServer.this.mDhcpServer.startWithCallbacks(new OnHandlerStatusCallback() { // from class: android.net.ip.IpServer.DhcpServerCallbacksImpl.1
                    {
                        IpServer ipServer = IpServer.this;
                    }

                    @Override // android.net.ip.IpServer.OnHandlerStatusCallback
                    /* JADX INFO: renamed from: callback */
                    public void lambda$onStatusAvailable$0(int i2) {
                        if (i2 != 1) {
                            IpServer.this.mLog.e("Error starting DHCP server: " + i2);
                            DhcpServerCallbacksImpl.this.handleError();
                        }
                    }
                }, new DhcpEventCallback());
            } catch (RemoteException e) {
                throw new IllegalStateException(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleError() {
            IpServer.this.mLastError = 12;
            IpServer ipServer = IpServer.this;
            ipServer.transitionTo(ipServer.mInitialState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DhcpEventCallback extends IDhcpEventCallbacks.Stub {
        @Override // android.net.dhcp.IDhcpEventCallbacks
        public String getInterfaceHash() throws RemoteException {
            return "02cd6fd07d5c04eca0c35a350f7b0be576242883";
        }

        @Override // android.net.dhcp.IDhcpEventCallbacks
        public int getInterfaceVersion() {
            return 7;
        }

        private DhcpEventCallback() {
        }

        @Override // android.net.dhcp.IDhcpEventCallbacks
        public void onLeasesChanged(List<DhcpLeaseParcelable> list) {
            final ArrayList arrayList = new ArrayList();
            for (DhcpLeaseParcelable dhcpLeaseParcelable : list) {
                Inet4Address inet4AddressIntToInet4AddressHTH = Inet4AddressUtils.intToInet4AddressHTH(dhcpLeaseParcelable.netAddr);
                int i = dhcpLeaseParcelable.prefixLength;
                int i2 = OsConstants.RT_SCOPE_UNIVERSE;
                long j = dhcpLeaseParcelable.expTime;
                try {
                    arrayList.add(new TetheredClient(MacAddress.fromBytes(dhcpLeaseParcelable.hwAddr), Collections.singletonList(new TetheredClient.AddressInfo(new LinkAddress(inet4AddressIntToInet4AddressHTH, i, 0, i2, j, j), dhcpLeaseParcelable.hostname)), IpServer.this.mInterfaceType));
                } catch (IllegalArgumentException unused) {
                    Log.wtf("IpServer", "Invalid address received from DhcpServer: " + Arrays.toString(dhcpLeaseParcelable.hwAddr));
                    return;
                }
            }
            IpServer.this.getHandler().post(new Runnable() { // from class: android.net.ip.-$$Lambda$IpServer$DhcpEventCallback$7aH9Sw9af63oN_b5UeJcSHs2dCA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onLeasesChanged$0$IpServer$DhcpEventCallback(arrayList);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onLeasesChanged$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onLeasesChanged$0$IpServer$DhcpEventCallback(ArrayList arrayList) {
            IpServer.this.mDhcpLeases = arrayList;
            IpServer.this.mCallback.dhcpLeasesChanged();
        }

        @Override // android.net.dhcp.IDhcpEventCallbacks
        public void onNewPrefixRequest(IpPrefix ipPrefix) {
            Objects.requireNonNull(ipPrefix);
            IpServer.this.sendMessage(IpServer.CMD_NEW_PREFIX_REQUEST, ipPrefix);
        }
    }

    private RouteInfo getDirectConnectedRoute(LinkAddress linkAddress) {
        Objects.requireNonNull(linkAddress);
        return new RouteInfo(PrefixUtils.asIpPrefix(linkAddress), null, this.mIfaceName, 1);
    }

    private DhcpServingParamsParcel makeServingParams(Inet4Address inet4Address, Inet4Address inet4Address2, LinkAddress linkAddress, Inet4Address inet4Address3) {
        boolean z = this.mInterfaceType == 4 && inet4Address3 == null;
        DhcpServingParamsParcelExt dhcpServingParamsParcelExt = new DhcpServingParamsParcelExt();
        dhcpServingParamsParcelExt.setDefaultRouters(inet4Address);
        dhcpServingParamsParcelExt.setDhcpLeaseTimeSecs(3600L);
        dhcpServingParamsParcelExt.setDnsServers(inet4Address2);
        dhcpServingParamsParcelExt.setServerAddr(linkAddress);
        dhcpServingParamsParcelExt.setMetered(true);
        dhcpServingParamsParcelExt.setSingleClientAddr(inet4Address3);
        dhcpServingParamsParcelExt.setChangePrefixOnDecline(z);
        return dhcpServingParamsParcelExt;
    }

    private boolean startDhcp(LinkAddress linkAddress, LinkAddress linkAddress2) {
        if (this.mUsingLegacyDhcp) {
            return true;
        }
        Inet4Address inet4Address = (Inet4Address) linkAddress.getAddress();
        DhcpServingParamsParcel dhcpServingParamsParcelMakeServingParams = makeServingParams(inet4Address, inet4Address, linkAddress, linkAddress2 == null ? null : (Inet4Address) linkAddress2.getAddress());
        this.mDhcpServerStartIndex++;
        this.mDeps.makeDhcpServer(this.mIfaceName, dhcpServingParamsParcelMakeServingParams, new DhcpServerCallbacksImpl(this.mDhcpServerStartIndex));
        return true;
    }

    private void stopDhcp() {
        this.mDhcpServerStartIndex++;
        IDhcpServer iDhcpServer = this.mDhcpServer;
        if (iDhcpServer != null) {
            try {
                iDhcpServer.stop(new OnHandlerStatusCallback() { // from class: android.net.ip.IpServer.1
                    @Override // android.net.ip.IpServer.OnHandlerStatusCallback
                    /* JADX INFO: renamed from: callback */
                    public void lambda$onStatusAvailable$0(int i) {
                        if (i != 1) {
                            IpServer.this.mLog.e("Error stopping DHCP server: " + i);
                            IpServer.this.mLastError = 12;
                        }
                        IpServer.this.mDhcpLeases.clear();
                        Handler handler = IpServer.this.getHandler();
                        final Callback callback = IpServer.this.mCallback;
                        Objects.requireNonNull(callback);
                        handler.post(new Runnable() { // from class: android.net.ip.-$$Lambda$VnCe_2ytttPRhxeMpr6qsJseAAg
                            @Override // java.lang.Runnable
                            public final void run() {
                                callback.dhcpLeasesChanged();
                            }
                        });
                    }
                });
                this.mDhcpServer = null;
            } catch (RemoteException e) {
                this.mLog.e("Error stopping DHCP server", e);
            }
        }
    }

    private boolean configureDhcp(boolean z, LinkAddress linkAddress, LinkAddress linkAddress2) {
        if (z) {
            return startDhcp(linkAddress, linkAddress2);
        }
        stopDhcp();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopIPv4() {
        configureIPv4(false);
        this.mInterfaceCtrl.clearIPv4Address();
        this.mPrivateAddressCoordinator.releaseDownstream(this);
        this.mIpv4Address = null;
        this.mStaticIpv4ServerAddr = null;
        this.mStaticIpv4ClientAddr = null;
    }

    private boolean configureIPv4(boolean z) {
        Log.d("IpServer", "configureIPv4(" + z + ")");
        if (z) {
            this.mIpv4Address = requestIpv4Address();
        }
        if (this.mIpv4Address == null) {
            this.mLog.e("No available ipv4 address");
            return false;
        }
        Log.d("IpServer", "configureIPv4 ipv4 address:(" + this.mIpv4Address + "); mInterfaceType" + this.mInterfaceType);
        Boolean boolValueOf = null;
        if (this.mInterfaceType == 2) {
            return configureDhcp(z, this.mIpv4Address, null);
        }
        PrefixUtils.asIpPrefix(this.mIpv4Address);
        int i = this.mInterfaceType;
        if (i != 0 && i != 3 && i != 5 && i != 6) {
            boolValueOf = Boolean.valueOf(z);
        }
        if (!this.mInterfaceCtrl.setInterfaceConfiguration(this.mIpv4Address, boolValueOf)) {
            this.mLog.e("Error configuring interface");
            if (!z) {
                stopDhcp();
            }
            return false;
        }
        if (z) {
            this.mLinkProperties.addLinkAddress(this.mIpv4Address);
            this.mLinkProperties.addRoute(getDirectConnectedRoute(this.mIpv4Address));
        } else {
            this.mLinkProperties.removeLinkAddress(this.mIpv4Address);
            this.mLinkProperties.removeRoute(getDirectConnectedRoute(this.mIpv4Address));
        }
        return configureDhcp(z, this.mIpv4Address, this.mStaticIpv4ClientAddr);
    }

    private LinkAddress requestIpv4Address() {
        LinkAddress linkAddress = this.mStaticIpv4ServerAddr;
        if (linkAddress != null) {
            return linkAddress;
        }
        int i = this.mInterfaceType;
        if (i == 2) {
            return new LinkAddress("192.168.44.1/24");
        }
        if (i == 0) {
            return new LinkAddress("192.168.43.1/24");
        }
        return this.mPrivateAddressCoordinator.requestDownstreamAddress(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startIPv6() {
        InterfaceParams interfaceParams = this.mDeps.getInterfaceParams(this.mIfaceName);
        this.mInterfaceParams = interfaceParams;
        if (interfaceParams == null) {
            this.mLog.e("Failed to find InterfaceParams");
            stopIPv6();
            return false;
        }
        RouterAdvertisementDaemon routerAdvertisementDaemon = this.mDeps.getRouterAdvertisementDaemon(interfaceParams);
        this.mRaDaemon = routerAdvertisementDaemon;
        if (routerAdvertisementDaemon.start()) {
            return true;
        }
        stopIPv6();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopIPv6() {
        this.mInterfaceParams = null;
        setRaParams(null);
        RouterAdvertisementDaemon routerAdvertisementDaemon = this.mRaDaemon;
        if (routerAdvertisementDaemon != null) {
            routerAdvertisementDaemon.stop();
            this.mRaDaemon = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUpstreamIPv6LinkProperties(LinkProperties linkProperties, int i) {
        RouterAdvertisementDaemon.RaParams raParams;
        if (this.mRaDaemon == null || Objects.equals(this.mLastIPv6LinkProperties, linkProperties)) {
            return;
        }
        int i2 = 0;
        if (linkProperties != null) {
            String interfaceName = linkProperties.getInterfaceName();
            raParams = new RouterAdvertisementDaemon.RaParams();
            raParams.mtu = linkProperties.getMtu();
            boolean zHasIpv6DefaultRoute = linkProperties.hasIpv6DefaultRoute();
            raParams.hasDefaultRoute = zHasIpv6DefaultRoute;
            if (zHasIpv6DefaultRoute) {
                raParams.hopLimit = getHopLimit(interfaceName, i);
            }
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                if (linkAddress.getPrefixLength() == 64) {
                    IpPrefix ipPrefix = new IpPrefix(linkAddress.getAddress(), linkAddress.getPrefixLength());
                    raParams.prefixes.add(ipPrefix);
                    Inet6Address localDnsIpFor = getLocalDnsIpFor(ipPrefix);
                    if (localDnsIpFor != null) {
                        raParams.dnses.add(localDnsIpFor);
                    }
                }
            }
            int ifindex = this.mDeps.getIfindex(interfaceName);
            this.mBpfCoordinator.addUpstreamNameToLookupTable(ifindex, interfaceName);
            i2 = ifindex;
        } else {
            raParams = null;
        }
        setRaParams(raParams);
        this.mLastIPv6LinkProperties = linkProperties;
        updateIpv6ForwardingRules(this.mLastIPv6UpstreamIfindex, i2, null);
        this.mLastIPv6UpstreamIfindex = i2;
    }

    private void removeRoutesFromLocalNetwork(List<RouteInfo> list) {
        int iRemoveRoutesFromLocalNetwork = RouteUtils.removeRoutesFromLocalNetwork(this.mNetd, list);
        if (iRemoveRoutesFromLocalNetwork > 0) {
            this.mLog.e(String.format("Failed to remove %d IPv6 routes from local table.", Integer.valueOf(iRemoveRoutesFromLocalNetwork)));
        }
        Iterator<RouteInfo> it = list.iterator();
        while (it.hasNext()) {
            this.mLinkProperties.removeRoute(it.next());
        }
    }

    private void addRoutesToLocalNetwork(List<RouteInfo> list) {
        try {
            this.mNetd.networkAddInterface(99, this.mIfaceName);
            try {
                RouteUtils.addRoutesToLocalNetwork(this.mNetd, this.mIfaceName, list);
                Iterator<RouteInfo> it = list.iterator();
                while (it.hasNext()) {
                    this.mLinkProperties.addRoute(it.next());
                }
            } catch (IllegalStateException e) {
                this.mLog.e("Failed to add IPv4/v6 routes to local table: " + e);
            }
        } catch (ServiceSpecificException | RemoteException e2) {
            this.mLog.e("Failed to add " + this.mIfaceName + " to local table: ", e2);
        }
    }

    private void configureLocalIPv6Routes(HashSet<IpPrefix> hashSet, HashSet<IpPrefix> hashSet2) {
        if (!hashSet.isEmpty()) {
            removeRoutesFromLocalNetwork(getLocalRoutesFor(this.mIfaceName, hashSet));
        }
        if (hashSet2 == null || hashSet2.isEmpty()) {
            return;
        }
        HashSet hashSet3 = (HashSet) hashSet2.clone();
        RouterAdvertisementDaemon.RaParams raParams = this.mLastRaParams;
        if (raParams != null) {
            hashSet3.removeAll(raParams.prefixes);
        }
        if (hashSet3.isEmpty()) {
            return;
        }
        addRoutesToLocalNetwork(getLocalRoutesFor(this.mIfaceName, hashSet3));
    }

    private void configureLocalIPv6Dns(HashSet<Inet6Address> hashSet, HashSet<Inet6Address> hashSet2) {
        if (this.mNetd == null) {
            if (hashSet2 != null) {
                hashSet2.clear();
            }
            this.mLog.e("No netd service instance available; not setting local IPv6 addresses");
            return;
        }
        if (!hashSet.isEmpty()) {
            for (Inet6Address inet6Address : hashSet) {
                if (!this.mInterfaceCtrl.removeAddress(inet6Address, 64)) {
                    this.mLog.e("Failed to remove local dns IP " + inet6Address);
                }
                this.mLinkProperties.removeLinkAddress(new LinkAddress(inet6Address, 64));
            }
        }
        if (hashSet2 != null && !hashSet2.isEmpty()) {
            HashSet<Inet6Address> hashSet3 = (HashSet) hashSet2.clone();
            RouterAdvertisementDaemon.RaParams raParams = this.mLastRaParams;
            if (raParams != null) {
                hashSet3.removeAll(raParams.dnses);
            }
            for (Inet6Address inet6Address2 : hashSet3) {
                if (!this.mInterfaceCtrl.addAddress(inet6Address2, 64)) {
                    this.mLog.e("Failed to add local dns IP " + inet6Address2);
                    hashSet2.remove(inet6Address2);
                }
                this.mLinkProperties.addLinkAddress(new LinkAddress(inet6Address2, 64));
            }
        }
        try {
            this.mNetd.tetherApplyDnsInterfaces();
        } catch (ServiceSpecificException | RemoteException unused) {
            this.mLog.e("Failed to update local DNS caching server");
            if (hashSet2 != null) {
                hashSet2.clear();
            }
        }
    }

    private void addIpv6ForwardingRule(BpfCoordinator.Ipv6ForwardingRule ipv6ForwardingRule) {
        if (this.mUsingBpfOffload) {
            this.mBpfCoordinator.tetherOffloadRuleAdd(this, ipv6ForwardingRule);
        }
    }

    private void removeIpv6ForwardingRule(BpfCoordinator.Ipv6ForwardingRule ipv6ForwardingRule) {
        if (this.mUsingBpfOffload) {
            this.mBpfCoordinator.tetherOffloadRuleRemove(this, ipv6ForwardingRule);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearIpv6ForwardingRules() {
        if (this.mUsingBpfOffload) {
            this.mBpfCoordinator.tetherOffloadRuleClear(this);
        }
    }

    private void updateIpv6ForwardingRule(int i) {
        if (this.mUsingBpfOffload) {
            this.mBpfCoordinator.tetherOffloadRuleUpdate(this, i);
        }
    }

    private void updateIpv6ForwardingRules(int i, int i2, IpNeighborMonitor.NeighborEvent neighborEvent) {
        if (i2 == 0) {
            clearIpv6ForwardingRules();
            return;
        }
        if (i != i2) {
            updateIpv6ForwardingRule(i2);
        }
        if (neighborEvent == null) {
            return;
        }
        InetAddress inetAddress = neighborEvent.ip;
        if (!(inetAddress instanceof Inet6Address) || inetAddress.isMulticastAddress() || neighborEvent.ip.isLoopbackAddress() || neighborEvent.ip.isLinkLocalAddress()) {
            return;
        }
        MacAddress macAddress = neighborEvent.isValid() ? neighborEvent.macAddr : NULL_MAC_ADDRESS;
        InterfaceParams interfaceParams = this.mInterfaceParams;
        BpfCoordinator.Ipv6ForwardingRule ipv6ForwardingRule = new BpfCoordinator.Ipv6ForwardingRule(i2, interfaceParams.index, (Inet6Address) neighborEvent.ip, interfaceParams.macAddr, macAddress);
        if (neighborEvent.isValid()) {
            addIpv6ForwardingRule(ipv6ForwardingRule);
        } else {
            removeIpv6ForwardingRule(ipv6ForwardingRule);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNeighborEvent(IpNeighborMonitor.NeighborEvent neighborEvent) {
        InterfaceParams interfaceParams = this.mInterfaceParams;
        if (interfaceParams != null && interfaceParams.index == neighborEvent.ifindex && interfaceParams.hasMacAddress) {
            int i = this.mLastIPv6UpstreamIfindex;
            updateIpv6ForwardingRules(i, i, neighborEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewPrefixRequest(IpPrefix ipPrefix) {
        if (!ipPrefix.contains(this.mIpv4Address.getAddress()) || ipPrefix.getPrefixLength() != this.mIpv4Address.getPrefixLength()) {
            Log.e("IpServer", "Invalid prefix: " + ipPrefix);
            return;
        }
        LinkAddress linkAddress = this.mIpv4Address;
        LinkAddress linkAddressRequestIpv4Address = requestIpv4Address();
        this.mIpv4Address = linkAddressRequestIpv4Address;
        if (linkAddressRequestIpv4Address == null) {
            this.mLog.e("Fail to request a new downstream prefix");
            return;
        }
        Inet4Address inet4Address = (Inet4Address) linkAddressRequestIpv4Address.getAddress();
        if (!this.mInterfaceCtrl.addAddress(inet4Address, ipPrefix.getPrefixLength())) {
            this.mLog.e("Failed to add new IP " + inet4Address);
            return;
        }
        removeRoutesFromLocalNetwork(Collections.singletonList(getDirectConnectedRoute(linkAddress)));
        this.mLinkProperties.removeLinkAddress(linkAddress);
        addRoutesToLocalNetwork(Collections.singletonList(getDirectConnectedRoute(this.mIpv4Address)));
        this.mLinkProperties.addLinkAddress(this.mIpv4Address);
        try {
            this.mNetd.tetherApplyDnsInterfaces();
            sendLinkProperties();
            LinkAddress linkAddress2 = this.mStaticIpv4ClientAddr;
            try {
                this.mDhcpServer.updateParams(makeServingParams(inet4Address, inet4Address, this.mIpv4Address, linkAddress2 == null ? null : (Inet4Address) linkAddress2.getAddress()), new OnHandlerStatusCallback() { // from class: android.net.ip.IpServer.2
                    @Override // android.net.ip.IpServer.OnHandlerStatusCallback
                    /* JADX INFO: renamed from: callback */
                    public void lambda$onStatusAvailable$0(int i) {
                        if (i != 1) {
                            IpServer.this.mLog.e("Error updating DHCP serving params: " + i);
                        }
                    }
                });
            } catch (RemoteException e) {
                this.mLog.e("Error updating DHCP serving params", e);
            }
        } catch (ServiceSpecificException | RemoteException unused) {
            this.mLog.e("Failed to update local DNS caching server");
        }
    }

    private byte getHopLimit(String str, int i) {
        try {
            return (byte) Integer.min(Integer.parseUnsignedInt(this.mNetd.getProcSysNet(6, 1, str, "hop_limit")) + i, 255);
        } catch (Exception e) {
            this.mLog.e("Failed to find upstream interface hop limit", e);
            return (byte) 65;
        }
    }

    private void setRaParams(RouterAdvertisementDaemon.RaParams raParams) {
        if (this.mRaDaemon != null) {
            RouterAdvertisementDaemon.RaParams deprecatedRaParams = RouterAdvertisementDaemon.RaParams.getDeprecatedRaParams(this.mLastRaParams, raParams);
            configureLocalIPv6Routes(deprecatedRaParams.prefixes, raParams != null ? raParams.prefixes : null);
            configureLocalIPv6Dns(deprecatedRaParams.dnses, raParams != null ? raParams.dnses : null);
            this.mRaDaemon.buildNewRa(deprecatedRaParams, raParams);
        }
        this.mLastRaParams = raParams;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logMessage(State state, int i) {
        this.mLog.log(state.getName() + " got " + sMagicDecoderRing.get(i, Integer.toString(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendInterfaceState(int i) {
        this.mServingMode = i;
        this.mCallback.updateInterfaceState(this, i, this.mLastError);
        sendLinkProperties();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendLinkProperties() {
        this.mCallback.updateLinkProperties(this, new LinkProperties(this.mLinkProperties));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetLinkProperties() {
        this.mLinkProperties.clear();
        this.mLinkProperties.setInterfaceName(this.mIfaceName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeConfigureStaticIp(TetheringRequestParcel tetheringRequestParcel) {
        LinkAddress linkAddress;
        LinkAddress linkAddress2;
        if (tetheringRequestParcel == null || (linkAddress = tetheringRequestParcel.localIPv4Address) == null || (linkAddress2 = tetheringRequestParcel.staticClientAddress) == null || !TetheringManager.TetheringRequest.checkStaticAddressConfiguration(linkAddress, linkAddress2)) {
            return;
        }
        this.mStaticIpv4ServerAddr = tetheringRequestParcel.localIPv4Address;
        this.mStaticIpv4ClientAddr = tetheringRequestParcel.staticClientAddress;
    }

    class InitialState extends State {
        InitialState() {
        }

        @Override // com.android.networkstack.tethering.util.State
        public void enter() {
            IpServer.this.sendInterfaceState(1);
        }

        @Override // com.android.networkstack.tethering.util.State
        public boolean processMessage(Message message) {
            IpServer.this.logMessage(this, message.what);
            int i = message.what;
            if (i != 101) {
                if (i == 103) {
                    IpServer ipServer = IpServer.this;
                    ipServer.transitionTo(ipServer.mUnavailableState);
                    return true;
                }
                if (i != 110) {
                    return false;
                }
                IpServer.this.updateUpstreamIPv6LinkProperties((LinkProperties) message.obj, message.arg1);
                return true;
            }
            IpServer.this.mLastError = 0;
            int i2 = message.arg1;
            if (i2 == 2) {
                IpServer.this.maybeConfigureStaticIp((TetheringRequestParcel) message.obj);
                IpServer ipServer2 = IpServer.this;
                ipServer2.transitionTo(ipServer2.mTetheredState);
                return true;
            }
            if (i2 != 3) {
                IpServer.this.mLog.e("Invalid tethering interface serving state specified.");
                return true;
            }
            IpServer.this.maybeConfigureStaticIp((TetheringRequestParcel) message.obj);
            IpServer ipServer3 = IpServer.this;
            ipServer3.transitionTo(ipServer3.mLocalHotspotState);
            return true;
        }
    }

    class BaseServingState extends State {
        BaseServingState() {
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
        @Override // com.android.networkstack.tethering.util.State
        public void enter() throws ServiceSpecificException {
            if (!IpServer.this.startIPv4()) {
                IpServer.this.mLastError = 10;
                return;
            }
            try {
                NetdUtils.tetherInterface(IpServer.this.mNetd, IpServer.this.mIfaceName, PrefixUtils.asIpPrefix(IpServer.this.mIpv4Address));
                if (IpServer.this.startIPv6()) {
                    return;
                }
                IpServer.this.mLog.e("Failed to startIPv6");
            } catch (RemoteException | ServiceSpecificException | IllegalStateException e) {
                IpServer.this.mLog.e("Error Tethering", e);
                IpServer.this.mLastError = 6;
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
        @Override // com.android.networkstack.tethering.util.State
        public void exit() throws ServiceSpecificException {
            IpServer.this.stopIPv6();
            try {
                NetdUtils.untetherInterface(IpServer.this.mNetd, IpServer.this.mIfaceName);
            } catch (RemoteException | ServiceSpecificException e) {
                IpServer.this.mLastError = 7;
                IpServer.this.mLog.e("Failed to untether interface: " + e);
            }
            IpServer.this.stopIPv4();
            IpServer.this.resetLinkProperties();
        }

        @Override // com.android.networkstack.tethering.util.State
        public boolean processMessage(Message message) {
            IpServer.this.logMessage(this, message.what);
            switch (message.what) {
                case IpServer.CMD_TETHER_UNREQUESTED /* 102 */:
                    IpServer ipServer = IpServer.this;
                    ipServer.transitionTo(ipServer.mInitialState);
                    Log.d("IpServer", "Untethered (unrequested)" + IpServer.this.mIfaceName);
                    return true;
                case IpServer.CMD_INTERFACE_DOWN /* 103 */:
                    IpServer ipServer2 = IpServer.this;
                    ipServer2.transitionTo(ipServer2.mUnavailableState);
                    Log.d("IpServer", "Untethered (ifdown)" + IpServer.this.mIfaceName);
                    return true;
                case IpServer.CMD_IP_FORWARDING_ENABLE_ERROR /* 104 */:
                case IpServer.CMD_IP_FORWARDING_DISABLE_ERROR /* 105 */:
                case IpServer.CMD_START_TETHERING_ERROR /* 106 */:
                case IpServer.CMD_STOP_TETHERING_ERROR /* 107 */:
                case IpServer.CMD_SET_DNS_FORWARDERS_ERROR /* 108 */:
                    IpServer.this.mLastError = 5;
                    IpServer ipServer3 = IpServer.this;
                    ipServer3.transitionTo(ipServer3.mInitialState);
                    return true;
                case IpServer.CMD_TETHER_CONNECTION_CHANGED /* 109 */:
                case IpServer.CMD_NEIGHBOR_EVENT /* 111 */:
                default:
                    return false;
                case IpServer.CMD_IPV6_TETHER_UPDATE /* 110 */:
                    IpServer.this.updateUpstreamIPv6LinkProperties((LinkProperties) message.obj, message.arg1);
                    IpServer.this.sendLinkProperties();
                    return true;
                case IpServer.CMD_NEW_PREFIX_REQUEST /* 112 */:
                    IpServer.this.handleNewPrefixRequest((IpPrefix) message.obj);
                    return true;
                case IpServer.CMD_NOTIFY_PREFIX_CONFLICT /* 113 */:
                    IpServer.this.mLog.i("restart tethering: " + IpServer.this.mInterfaceType);
                    IpServer.this.mCallback.requestEnableTethering(IpServer.this.mInterfaceType, false);
                    IpServer ipServer4 = IpServer.this;
                    ipServer4.transitionTo(ipServer4.mWaitingForRestartState);
                    return true;
            }
        }
    }

    class LocalHotspotState extends BaseServingState {
        LocalHotspotState() {
            super();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
        @Override // android.net.ip.IpServer.BaseServingState, com.android.networkstack.tethering.util.State
        public void enter() throws ServiceSpecificException {
            super.enter();
            if (IpServer.this.mLastError != 0) {
                IpServer ipServer = IpServer.this;
                ipServer.transitionTo(ipServer.mInitialState);
            }
            Log.d("IpServer", "Local hotspot " + IpServer.this.mIfaceName);
            IpServer.this.sendInterfaceState(3);
        }

        @Override // android.net.ip.IpServer.BaseServingState, com.android.networkstack.tethering.util.State
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            IpServer.this.logMessage(this, message.what);
            int i = message.what;
            if (i == 101) {
                IpServer.this.mLog.e("CMD_TETHER_REQUESTED while in local-only hotspot mode.");
            } else if (i != 109) {
                return false;
            }
            return true;
        }
    }

    class TetheredState extends BaseServingState {
        TetheredState() {
            super();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
        @Override // android.net.ip.IpServer.BaseServingState, com.android.networkstack.tethering.util.State
        public void enter() throws ServiceSpecificException {
            super.enter();
            if (IpServer.this.mLastError != 0) {
                IpServer ipServer = IpServer.this;
                ipServer.transitionTo(ipServer.mInitialState);
            }
            Log.d("IpServer", "Tethered " + IpServer.this.mIfaceName);
            IpServer.this.sendInterfaceState(2);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: android.os.ServiceSpecificException */
        @Override // android.net.ip.IpServer.BaseServingState, com.android.networkstack.tethering.util.State
        public void exit() throws ServiceSpecificException {
            cleanupUpstream();
            super.exit();
        }

        private void cleanupUpstream() {
            if (IpServer.this.mUpstreamIfaceSet == null) {
                return;
            }
            Iterator<String> it = IpServer.this.mUpstreamIfaceSet.ifnames.iterator();
            while (it.hasNext()) {
                cleanupUpstreamInterface(it.next());
            }
            IpServer.this.mUpstreamIfaceSet = null;
            IpServer.this.clearIpv6ForwardingRules();
        }

        private void cleanupUpstreamInterface(String str) {
            try {
                IpServer.this.mNetd.ipfwdRemoveInterfaceForward(IpServer.this.mIfaceName, str);
            } catch (RemoteException | ServiceSpecificException e) {
                IpServer.this.mLog.e("Exception in ipfwdRemoveInterfaceForward: " + e.toString());
            }
            try {
                IpServer.this.mNetd.tetherRemoveForward(IpServer.this.mIfaceName, str);
            } catch (RemoteException | ServiceSpecificException e2) {
                IpServer.this.mLog.e("Exception in disableNat: " + e2.toString());
            }
        }

        @Override // android.net.ip.IpServer.BaseServingState, com.android.networkstack.tethering.util.State
        public boolean processMessage(Message message) {
            if (super.processMessage(message)) {
                return true;
            }
            IpServer.this.logMessage(this, message.what);
            int i = message.what;
            if (i == 101) {
                IpServer.this.mLog.e("CMD_TETHER_REQUESTED while already tethering.");
            } else if (i != 109) {
                if (i != 111) {
                    return false;
                }
                IpServer.this.handleNeighborEvent((IpNeighborMonitor.NeighborEvent) message.obj);
            } else {
                InterfaceSet interfaceSet = (InterfaceSet) message.obj;
                if (noChangeInUpstreamIfaceSet(interfaceSet)) {
                    Log.d("IpServer", "Connection changed noop - dropping");
                } else if (interfaceSet == null) {
                    cleanupUpstream();
                } else {
                    Iterator<String> it = upstreamInterfacesRemoved(interfaceSet).iterator();
                    while (it.hasNext()) {
                        cleanupUpstreamInterface(it.next());
                    }
                    Set<String> setUpstreamInterfacesAdd = upstreamInterfacesAdd(interfaceSet);
                    IpServer.this.mUpstreamIfaceSet = interfaceSet;
                    for (String str : setUpstreamInterfacesAdd) {
                        if (SystemProperties.getBoolean("persist.wifi.SOFTAP_SHARED_DATA_DISABLE", true) && (IpServer.this.mIfaceName.contains("wlan1") || IpServer.this.mIfaceName.contains("wlan2"))) {
                            break;
                        }
                        try {
                            IpServer.this.mNetd.tetherAddForward(IpServer.this.mIfaceName, str);
                            IpServer.this.mNetd.ipfwdAddInterfaceForward(IpServer.this.mIfaceName, str);
                        } catch (RemoteException | ServiceSpecificException e) {
                            IpServer.this.mLog.e("Exception enabling NAT: " + e.toString());
                            cleanupUpstream();
                            IpServer.this.mLastError = 8;
                            IpServer ipServer = IpServer.this;
                            ipServer.transitionTo(ipServer.mInitialState);
                            return true;
                        }
                    }
                }
            }
            return true;
        }

        private boolean noChangeInUpstreamIfaceSet(InterfaceSet interfaceSet) {
            if (IpServer.this.mUpstreamIfaceSet == null && interfaceSet == null) {
                return true;
            }
            if (IpServer.this.mUpstreamIfaceSet == null || interfaceSet == null) {
                return false;
            }
            return IpServer.this.mUpstreamIfaceSet.equals(interfaceSet);
        }

        private Set<String> upstreamInterfacesRemoved(InterfaceSet interfaceSet) {
            if (IpServer.this.mUpstreamIfaceSet == null) {
                return new HashSet();
            }
            HashSet hashSet = new HashSet(IpServer.this.mUpstreamIfaceSet.ifnames);
            hashSet.removeAll(interfaceSet.ifnames);
            return hashSet;
        }

        private Set<String> upstreamInterfacesAdd(InterfaceSet interfaceSet) {
            HashSet hashSet = new HashSet(interfaceSet.ifnames);
            if (IpServer.this.mUpstreamIfaceSet != null) {
                hashSet.removeAll(IpServer.this.mUpstreamIfaceSet.ifnames);
            }
            return hashSet;
        }
    }

    class UnavailableState extends State {
        UnavailableState() {
        }

        @Override // com.android.networkstack.tethering.util.State
        public void enter() {
            IpServer.this.mIpNeighborMonitor.stop();
            IpServer.this.mLastError = 0;
            IpServer.this.sendInterfaceState(0);
        }
    }

    class WaitingForRestartState extends State {
        WaitingForRestartState() {
        }

        @Override // com.android.networkstack.tethering.util.State
        public boolean processMessage(Message message) {
            IpServer.this.logMessage(this, message.what);
            int i = message.what;
            if (i == 102) {
                IpServer ipServer = IpServer.this;
                ipServer.transitionTo(ipServer.mInitialState);
                IpServer.this.mLog.i("Untethered (unrequested) and restarting " + IpServer.this.mIfaceName);
                IpServer.this.mCallback.requestEnableTethering(IpServer.this.mInterfaceType, true);
            } else {
                if (i != 103) {
                    return false;
                }
                IpServer ipServer2 = IpServer.this;
                ipServer2.transitionTo(ipServer2.mUnavailableState);
                IpServer.this.mLog.i("Untethered (interface down) and restarting" + IpServer.this.mIfaceName);
                IpServer.this.mCallback.requestEnableTethering(IpServer.this.mInterfaceType, true);
            }
            return true;
        }
    }

    private static ArrayList<RouteInfo> getLocalRoutesFor(String str, HashSet<IpPrefix> hashSet) {
        ArrayList<RouteInfo> arrayList = new ArrayList<>();
        Iterator<IpPrefix> it = hashSet.iterator();
        while (it.hasNext()) {
            arrayList.add(new RouteInfo(it.next(), null, str, 1));
        }
        return arrayList;
    }

    private static Inet6Address getLocalDnsIpFor(IpPrefix ipPrefix) {
        byte[] rawAddress = ipPrefix.getRawAddress();
        rawAddress[rawAddress.length - 1] = getRandomSanitizedByte((byte) 42, NetworkConstants.asByte(0), NetworkConstants.asByte(1));
        try {
            return Inet6Address.getByAddress((String) null, rawAddress, 0);
        } catch (UnknownHostException unused) {
            Log.wtf("IpServer", "Failed to construct Inet6Address from: " + ipPrefix);
            return null;
        }
    }

    private static byte getRandomSanitizedByte(byte b, byte... bArr) {
        byte bNextInt = (byte) new Random().nextInt();
        for (byte b2 : bArr) {
            if (bNextInt == b2) {
                return b;
            }
        }
        return bNextInt;
    }
}
