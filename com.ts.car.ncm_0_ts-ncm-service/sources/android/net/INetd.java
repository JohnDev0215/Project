package android.net;

import android.net.INetdUnsolicitedEventListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface INetd extends IInterface {
    public static final int CONF = 1;
    public static final int FIREWALL_BLACKLIST = 1;
    public static final int FIREWALL_CHAIN_DOZABLE = 1;
    public static final int FIREWALL_CHAIN_NONE = 0;
    public static final int FIREWALL_CHAIN_POWERSAVE = 3;
    public static final int FIREWALL_CHAIN_STANDBY = 2;
    public static final int FIREWALL_RULE_ALLOW = 1;
    public static final int FIREWALL_RULE_DENY = 2;
    public static final int FIREWALL_WHITELIST = 0;
    public static final String HASH = "63adaa5098e4d8621e90c5a84f7cb93505c79311";
    public static final String IF_FLAG_BROADCAST = "broadcast";
    public static final String IF_FLAG_LOOPBACK = "loopback";
    public static final String IF_FLAG_MULTICAST = "multicast";
    public static final String IF_FLAG_POINTOPOINT = "point-to-point";
    public static final String IF_FLAG_RUNNING = "running";
    public static final String IF_STATE_DOWN = "down";
    public static final String IF_STATE_UP = "up";
    public static final String IPSEC_INTERFACE_PREFIX = "ipsec";
    public static final int IPV4 = 4;
    public static final int IPV6 = 6;
    public static final int IPV6_ADDR_GEN_MODE_DEFAULT = 0;
    public static final int IPV6_ADDR_GEN_MODE_EUI64 = 0;
    public static final int IPV6_ADDR_GEN_MODE_NONE = 1;
    public static final int IPV6_ADDR_GEN_MODE_RANDOM = 3;
    public static final int IPV6_ADDR_GEN_MODE_STABLE_PRIVACY = 2;
    public static final int LOCAL_NET_ID = 99;
    public static final int NEIGH = 2;
    public static final String NEXTHOP_NONE = "";
    public static final String NEXTHOP_THROW = "throw";
    public static final String NEXTHOP_UNREACHABLE = "unreachable";
    public static final int NO_PERMISSIONS = 0;
    public static final int PENALTY_POLICY_ACCEPT = 1;
    public static final int PENALTY_POLICY_LOG = 2;
    public static final int PENALTY_POLICY_REJECT = 3;
    public static final int PERMISSION_INTERNET = 4;
    public static final int PERMISSION_NETWORK = 1;
    public static final int PERMISSION_NONE = 0;
    public static final int PERMISSION_SYSTEM = 2;
    public static final int PERMISSION_UNINSTALLED = -1;
    public static final int PERMISSION_UPDATE_DEVICE_STATS = 8;
    public static final int VERSION = 4;

    void bandwidthAddNaughtyApp(int i) throws RemoteException;

    void bandwidthAddNiceApp(int i) throws RemoteException;

    boolean bandwidthEnableDataSaver(boolean z) throws RemoteException;

    void bandwidthRemoveInterfaceAlert(String str) throws RemoteException;

    void bandwidthRemoveInterfaceQuota(String str) throws RemoteException;

    void bandwidthRemoveNaughtyApp(int i) throws RemoteException;

    void bandwidthRemoveNiceApp(int i) throws RemoteException;

    void bandwidthSetGlobalAlert(long j) throws RemoteException;

    void bandwidthSetInterfaceAlert(String str, long j) throws RemoteException;

    void bandwidthSetInterfaceQuota(String str, long j) throws RemoteException;

    String clatdStart(String str, String str2) throws RemoteException;

    void clatdStop(String str) throws RemoteException;

    void firewallAddUidInterfaceRules(String str, int[] iArr) throws RemoteException;

    void firewallEnableChildChain(int i, boolean z) throws RemoteException;

    void firewallRemoveUidInterfaceRules(int[] iArr) throws RemoteException;

    boolean firewallReplaceUidChain(String str, boolean z, int[] iArr) throws RemoteException;

    void firewallSetFirewallType(int i) throws RemoteException;

    void firewallSetInterfaceRule(String str, int i) throws RemoteException;

    void firewallSetUidRule(int i, int i2, int i3) throws RemoteException;

    MarkMaskParcel getFwmarkForNetwork(int i) throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    IBinder getOemNetd() throws RemoteException;

    String getProcSysNet(int i, int i2, String str, String str2) throws RemoteException;

    void idletimerAddInterface(String str, int i, String str2) throws RemoteException;

    void idletimerRemoveInterface(String str, int i, String str2) throws RemoteException;

    void interfaceAddAddress(String str, String str2, int i) throws RemoteException;

    void interfaceClearAddrs(String str) throws RemoteException;

    void interfaceDelAddress(String str, String str2, int i) throws RemoteException;

    InterfaceConfigurationParcel interfaceGetCfg(String str) throws RemoteException;

    String[] interfaceGetList() throws RemoteException;

    void interfaceSetCfg(InterfaceConfigurationParcel interfaceConfigurationParcel) throws RemoteException;

    void interfaceSetEnableIPv6(String str, boolean z) throws RemoteException;

    void interfaceSetIPv6PrivacyExtensions(String str, boolean z) throws RemoteException;

    void interfaceSetMtu(String str, int i) throws RemoteException;

    void ipSecAddSecurityAssociation(int i, int i2, String str, String str2, int i3, int i4, int i5, int i6, String str3, byte[] bArr, int i7, String str4, byte[] bArr2, int i8, String str5, byte[] bArr3, int i9, int i10, int i11, int i12, int i13) throws RemoteException;

    void ipSecAddSecurityPolicy(int i, int i2, int i3, String str, String str2, int i4, int i5, int i6, int i7) throws RemoteException;

    void ipSecAddTunnelInterface(String str, String str2, String str3, int i, int i2, int i3) throws RemoteException;

    int ipSecAllocateSpi(int i, String str, String str2, int i2) throws RemoteException;

    void ipSecApplyTransportModeTransform(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, String str, String str2, int i3) throws RemoteException;

    void ipSecDeleteSecurityAssociation(int i, String str, String str2, int i2, int i3, int i4, int i5) throws RemoteException;

    void ipSecDeleteSecurityPolicy(int i, int i2, int i3, int i4, int i5, int i6) throws RemoteException;

    void ipSecRemoveTransportModeTransform(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    void ipSecRemoveTunnelInterface(String str) throws RemoteException;

    void ipSecSetEncapSocketOwner(ParcelFileDescriptor parcelFileDescriptor, int i) throws RemoteException;

    void ipSecUpdateSecurityPolicy(int i, int i2, int i3, String str, String str2, int i4, int i5, int i6, int i7) throws RemoteException;

    void ipSecUpdateTunnelInterface(String str, String str2, String str3, int i, int i2, int i3) throws RemoteException;

    void ipfwdAddInterfaceForward(String str, String str2) throws RemoteException;

    void ipfwdDisableForwarding(String str) throws RemoteException;

    void ipfwdEnableForwarding(String str) throws RemoteException;

    boolean ipfwdEnabled() throws RemoteException;

    String[] ipfwdGetRequesterList() throws RemoteException;

    void ipfwdRemoveInterfaceForward(String str, String str2) throws RemoteException;

    boolean isAlive() throws RemoteException;

    void networkAddInterface(int i, String str) throws RemoteException;

    void networkAddLegacyRoute(int i, String str, String str2, String str3, int i2) throws RemoteException;

    void networkAddRoute(int i, String str, String str2, String str3) throws RemoteException;

    void networkAddRouteParcel(int i, RouteInfoParcel routeInfoParcel) throws RemoteException;

    void networkAddUidRanges(int i, UidRangeParcel[] uidRangeParcelArr) throws RemoteException;

    boolean networkCanProtect(int i) throws RemoteException;

    void networkClearDefault() throws RemoteException;

    void networkClearPermissionForUser(int[] iArr) throws RemoteException;

    void networkCreatePhysical(int i, int i2) throws RemoteException;

    void networkCreateVpn(int i, boolean z) throws RemoteException;

    void networkDestroy(int i) throws RemoteException;

    int networkGetDefault() throws RemoteException;

    void networkRejectNonSecureVpn(boolean z, UidRangeParcel[] uidRangeParcelArr) throws RemoteException;

    void networkRemoveInterface(int i, String str) throws RemoteException;

    void networkRemoveLegacyRoute(int i, String str, String str2, String str3, int i2) throws RemoteException;

    void networkRemoveRoute(int i, String str, String str2, String str3) throws RemoteException;

    void networkRemoveRouteParcel(int i, RouteInfoParcel routeInfoParcel) throws RemoteException;

    void networkRemoveUidRanges(int i, UidRangeParcel[] uidRangeParcelArr) throws RemoteException;

    void networkSetDefault(int i) throws RemoteException;

    void networkSetPermissionForNetwork(int i, int i2) throws RemoteException;

    void networkSetPermissionForUser(int i, int[] iArr) throws RemoteException;

    void networkSetProtectAllow(int i) throws RemoteException;

    void networkSetProtectDeny(int i) throws RemoteException;

    void networkUpdateRouteParcel(int i, RouteInfoParcel routeInfoParcel) throws RemoteException;

    void registerUnsolicitedEventListener(INetdUnsolicitedEventListener iNetdUnsolicitedEventListener) throws RemoteException;

    void setIPv6AddrGenMode(String str, int i) throws RemoteException;

    void setProcSysNet(int i, int i2, String str, String str2, String str3) throws RemoteException;

    void setTcpRWmemorySize(String str, String str2) throws RemoteException;

    void socketDestroy(UidRangeParcel[] uidRangeParcelArr, int[] iArr) throws RemoteException;

    void strictUidCleartextPenalty(int i, int i2) throws RemoteException;

    void tetherAddForward(String str, String str2) throws RemoteException;

    boolean tetherApplyDnsInterfaces() throws RemoteException;

    String[] tetherDnsList() throws RemoteException;

    void tetherDnsSet(int i, String[] strArr) throws RemoteException;

    TetherStatsParcel[] tetherGetStats() throws RemoteException;

    void tetherInterfaceAdd(String str) throws RemoteException;

    String[] tetherInterfaceList() throws RemoteException;

    void tetherInterfaceRemove(String str) throws RemoteException;

    boolean tetherIsEnabled() throws RemoteException;

    TetherStatsParcel tetherOffloadGetAndClearStats(int i) throws RemoteException;

    TetherStatsParcel[] tetherOffloadGetStats() throws RemoteException;

    void tetherOffloadRuleAdd(TetherOffloadRuleParcel tetherOffloadRuleParcel) throws RemoteException;

    void tetherOffloadRuleRemove(TetherOffloadRuleParcel tetherOffloadRuleParcel) throws RemoteException;

    void tetherOffloadSetInterfaceQuota(int i, long j) throws RemoteException;

    void tetherRemoveForward(String str, String str2) throws RemoteException;

    void tetherStart(String[] strArr) throws RemoteException;

    void tetherStartWithConfiguration(TetherConfigParcel tetherConfigParcel) throws RemoteException;

    void tetherStop() throws RemoteException;

    void trafficSetNetPermForUids(int i, int[] iArr) throws RemoteException;

    void trafficSwapActiveStatsMap() throws RemoteException;

    void wakeupAddInterface(String str, String str2, int i, int i2) throws RemoteException;

    void wakeupDelInterface(String str, String str2, int i, int i2) throws RemoteException;

    public static class Default implements INetd {
        @Override // android.net.INetd
        public boolean isAlive() throws RemoteException {
            return false;
        }

        @Override // android.net.INetd
        public boolean firewallReplaceUidChain(String chainName, boolean isWhitelist, int[] uids) throws RemoteException {
            return false;
        }

        @Override // android.net.INetd
        public boolean bandwidthEnableDataSaver(boolean enable) throws RemoteException {
            return false;
        }

        @Override // android.net.INetd
        public void networkCreatePhysical(int netId, int permission) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkCreateVpn(int netId, boolean secure) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkDestroy(int netId) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkAddInterface(int netId, String iface) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkRemoveInterface(int netId, String iface) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkAddUidRanges(int netId, UidRangeParcel[] uidRanges) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkRemoveUidRanges(int netId, UidRangeParcel[] uidRanges) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkRejectNonSecureVpn(boolean add, UidRangeParcel[] uidRanges) throws RemoteException {
        }

        @Override // android.net.INetd
        public void socketDestroy(UidRangeParcel[] uidRanges, int[] exemptUids) throws RemoteException {
        }

        @Override // android.net.INetd
        public boolean tetherApplyDnsInterfaces() throws RemoteException {
            return false;
        }

        @Override // android.net.INetd
        public TetherStatsParcel[] tetherGetStats() throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void interfaceAddAddress(String ifName, String addrString, int prefixLength) throws RemoteException {
        }

        @Override // android.net.INetd
        public void interfaceDelAddress(String ifName, String addrString, int prefixLength) throws RemoteException {
        }

        @Override // android.net.INetd
        public String getProcSysNet(int ipversion, int which, String ifname, String parameter) throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void setProcSysNet(int ipversion, int which, String ifname, String parameter, String value) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecSetEncapSocketOwner(ParcelFileDescriptor socket, int newUid) throws RemoteException {
        }

        @Override // android.net.INetd
        public int ipSecAllocateSpi(int transformId, String sourceAddress, String destinationAddress, int spi) throws RemoteException {
            return 0;
        }

        @Override // android.net.INetd
        public void ipSecAddSecurityAssociation(int transformId, int mode, String sourceAddress, String destinationAddress, int underlyingNetId, int spi, int markValue, int markMask, String authAlgo, byte[] authKey, int authTruncBits, String cryptAlgo, byte[] cryptKey, int cryptTruncBits, String aeadAlgo, byte[] aeadKey, int aeadIcvBits, int encapType, int encapLocalPort, int encapRemotePort, int interfaceId) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecDeleteSecurityAssociation(int transformId, String sourceAddress, String destinationAddress, int spi, int markValue, int markMask, int interfaceId) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecApplyTransportModeTransform(ParcelFileDescriptor socket, int transformId, int direction, String sourceAddress, String destinationAddress, int spi) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecRemoveTransportModeTransform(ParcelFileDescriptor socket) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecAddSecurityPolicy(int transformId, int selAddrFamily, int direction, String tmplSrcAddress, String tmplDstAddress, int spi, int markValue, int markMask, int interfaceId) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecUpdateSecurityPolicy(int transformId, int selAddrFamily, int direction, String tmplSrcAddress, String tmplDstAddress, int spi, int markValue, int markMask, int interfaceId) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecDeleteSecurityPolicy(int transformId, int selAddrFamily, int direction, int markValue, int markMask, int interfaceId) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecAddTunnelInterface(String deviceName, String localAddress, String remoteAddress, int iKey, int oKey, int interfaceId) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecUpdateTunnelInterface(String deviceName, String localAddress, String remoteAddress, int iKey, int oKey, int interfaceId) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipSecRemoveTunnelInterface(String deviceName) throws RemoteException {
        }

        @Override // android.net.INetd
        public void wakeupAddInterface(String ifName, String prefix, int mark, int mask) throws RemoteException {
        }

        @Override // android.net.INetd
        public void wakeupDelInterface(String ifName, String prefix, int mark, int mask) throws RemoteException {
        }

        @Override // android.net.INetd
        public void setIPv6AddrGenMode(String ifName, int mode) throws RemoteException {
        }

        @Override // android.net.INetd
        public void idletimerAddInterface(String ifName, int timeout, String classLabel) throws RemoteException {
        }

        @Override // android.net.INetd
        public void idletimerRemoveInterface(String ifName, int timeout, String classLabel) throws RemoteException {
        }

        @Override // android.net.INetd
        public void strictUidCleartextPenalty(int uid, int policyPenalty) throws RemoteException {
        }

        @Override // android.net.INetd
        public String clatdStart(String ifName, String nat64Prefix) throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void clatdStop(String ifName) throws RemoteException {
        }

        @Override // android.net.INetd
        public boolean ipfwdEnabled() throws RemoteException {
            return false;
        }

        @Override // android.net.INetd
        public String[] ipfwdGetRequesterList() throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void ipfwdEnableForwarding(String requester) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipfwdDisableForwarding(String requester) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipfwdAddInterfaceForward(String fromIface, String toIface) throws RemoteException {
        }

        @Override // android.net.INetd
        public void ipfwdRemoveInterfaceForward(String fromIface, String toIface) throws RemoteException {
        }

        @Override // android.net.INetd
        public void bandwidthSetInterfaceQuota(String ifName, long bytes) throws RemoteException {
        }

        @Override // android.net.INetd
        public void bandwidthRemoveInterfaceQuota(String ifName) throws RemoteException {
        }

        @Override // android.net.INetd
        public void bandwidthSetInterfaceAlert(String ifName, long bytes) throws RemoteException {
        }

        @Override // android.net.INetd
        public void bandwidthRemoveInterfaceAlert(String ifName) throws RemoteException {
        }

        @Override // android.net.INetd
        public void bandwidthSetGlobalAlert(long bytes) throws RemoteException {
        }

        @Override // android.net.INetd
        public void bandwidthAddNaughtyApp(int uid) throws RemoteException {
        }

        @Override // android.net.INetd
        public void bandwidthRemoveNaughtyApp(int uid) throws RemoteException {
        }

        @Override // android.net.INetd
        public void bandwidthAddNiceApp(int uid) throws RemoteException {
        }

        @Override // android.net.INetd
        public void bandwidthRemoveNiceApp(int uid) throws RemoteException {
        }

        @Override // android.net.INetd
        public void tetherStart(String[] dhcpRanges) throws RemoteException {
        }

        @Override // android.net.INetd
        public void tetherStop() throws RemoteException {
        }

        @Override // android.net.INetd
        public boolean tetherIsEnabled() throws RemoteException {
            return false;
        }

        @Override // android.net.INetd
        public void tetherInterfaceAdd(String ifName) throws RemoteException {
        }

        @Override // android.net.INetd
        public void tetherInterfaceRemove(String ifName) throws RemoteException {
        }

        @Override // android.net.INetd
        public String[] tetherInterfaceList() throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void tetherDnsSet(int netId, String[] dnsAddrs) throws RemoteException {
        }

        @Override // android.net.INetd
        public String[] tetherDnsList() throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void networkAddRoute(int netId, String ifName, String destination, String nextHop) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkRemoveRoute(int netId, String ifName, String destination, String nextHop) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkAddLegacyRoute(int netId, String ifName, String destination, String nextHop, int uid) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkRemoveLegacyRoute(int netId, String ifName, String destination, String nextHop, int uid) throws RemoteException {
        }

        @Override // android.net.INetd
        public int networkGetDefault() throws RemoteException {
            return 0;
        }

        @Override // android.net.INetd
        public void networkSetDefault(int netId) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkClearDefault() throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkSetPermissionForNetwork(int netId, int permission) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkSetPermissionForUser(int permission, int[] uids) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkClearPermissionForUser(int[] uids) throws RemoteException {
        }

        @Override // android.net.INetd
        public void trafficSetNetPermForUids(int permission, int[] uids) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkSetProtectAllow(int uid) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkSetProtectDeny(int uid) throws RemoteException {
        }

        @Override // android.net.INetd
        public boolean networkCanProtect(int uid) throws RemoteException {
            return false;
        }

        @Override // android.net.INetd
        public void firewallSetFirewallType(int firewalltype) throws RemoteException {
        }

        @Override // android.net.INetd
        public void firewallSetInterfaceRule(String ifName, int firewallRule) throws RemoteException {
        }

        @Override // android.net.INetd
        public void firewallSetUidRule(int childChain, int uid, int firewallRule) throws RemoteException {
        }

        @Override // android.net.INetd
        public void firewallEnableChildChain(int childChain, boolean enable) throws RemoteException {
        }

        @Override // android.net.INetd
        public String[] interfaceGetList() throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public InterfaceConfigurationParcel interfaceGetCfg(String ifName) throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void interfaceSetCfg(InterfaceConfigurationParcel cfg) throws RemoteException {
        }

        @Override // android.net.INetd
        public void interfaceSetIPv6PrivacyExtensions(String ifName, boolean enable) throws RemoteException {
        }

        @Override // android.net.INetd
        public void interfaceClearAddrs(String ifName) throws RemoteException {
        }

        @Override // android.net.INetd
        public void interfaceSetEnableIPv6(String ifName, boolean enable) throws RemoteException {
        }

        @Override // android.net.INetd
        public void interfaceSetMtu(String ifName, int mtu) throws RemoteException {
        }

        @Override // android.net.INetd
        public void tetherAddForward(String intIface, String extIface) throws RemoteException {
        }

        @Override // android.net.INetd
        public void tetherRemoveForward(String intIface, String extIface) throws RemoteException {
        }

        @Override // android.net.INetd
        public void setTcpRWmemorySize(String rmemValues, String wmemValues) throws RemoteException {
        }

        @Override // android.net.INetd
        public void registerUnsolicitedEventListener(INetdUnsolicitedEventListener listener) throws RemoteException {
        }

        @Override // android.net.INetd
        public void firewallAddUidInterfaceRules(String ifName, int[] uids) throws RemoteException {
        }

        @Override // android.net.INetd
        public void firewallRemoveUidInterfaceRules(int[] uids) throws RemoteException {
        }

        @Override // android.net.INetd
        public void trafficSwapActiveStatsMap() throws RemoteException {
        }

        @Override // android.net.INetd
        public IBinder getOemNetd() throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void tetherStartWithConfiguration(TetherConfigParcel config) throws RemoteException {
        }

        @Override // android.net.INetd
        public MarkMaskParcel getFwmarkForNetwork(int netId) throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void networkAddRouteParcel(int netId, RouteInfoParcel routeInfo) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkUpdateRouteParcel(int netId, RouteInfoParcel routeInfo) throws RemoteException {
        }

        @Override // android.net.INetd
        public void networkRemoveRouteParcel(int netId, RouteInfoParcel routeInfo) throws RemoteException {
        }

        @Override // android.net.INetd
        public void tetherOffloadRuleAdd(TetherOffloadRuleParcel rule) throws RemoteException {
        }

        @Override // android.net.INetd
        public void tetherOffloadRuleRemove(TetherOffloadRuleParcel rule) throws RemoteException {
        }

        @Override // android.net.INetd
        public TetherStatsParcel[] tetherOffloadGetStats() throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public void tetherOffloadSetInterfaceQuota(int ifIndex, long quotaBytes) throws RemoteException {
        }

        @Override // android.net.INetd
        public TetherStatsParcel tetherOffloadGetAndClearStats(int ifIndex) throws RemoteException {
            return null;
        }

        @Override // android.net.INetd
        public int getInterfaceVersion() {
            return 0;
        }

        @Override // android.net.INetd
        public String getInterfaceHash() {
            return INetd.NEXTHOP_NONE;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements INetd {
        private static final String DESCRIPTOR = "android$net$INetd".replace('$', '.');
        static final int TRANSACTION_bandwidthAddNaughtyApp = 50;
        static final int TRANSACTION_bandwidthAddNiceApp = 52;
        static final int TRANSACTION_bandwidthEnableDataSaver = 3;
        static final int TRANSACTION_bandwidthRemoveInterfaceAlert = 48;
        static final int TRANSACTION_bandwidthRemoveInterfaceQuota = 46;
        static final int TRANSACTION_bandwidthRemoveNaughtyApp = 51;
        static final int TRANSACTION_bandwidthRemoveNiceApp = 53;
        static final int TRANSACTION_bandwidthSetGlobalAlert = 49;
        static final int TRANSACTION_bandwidthSetInterfaceAlert = 47;
        static final int TRANSACTION_bandwidthSetInterfaceQuota = 45;
        static final int TRANSACTION_clatdStart = 37;
        static final int TRANSACTION_clatdStop = 38;
        static final int TRANSACTION_firewallAddUidInterfaceRules = 91;
        static final int TRANSACTION_firewallEnableChildChain = 79;
        static final int TRANSACTION_firewallRemoveUidInterfaceRules = 92;
        static final int TRANSACTION_firewallReplaceUidChain = 2;
        static final int TRANSACTION_firewallSetFirewallType = 76;
        static final int TRANSACTION_firewallSetInterfaceRule = 77;
        static final int TRANSACTION_firewallSetUidRule = 78;
        static final int TRANSACTION_getFwmarkForNetwork = 96;
        static final int TRANSACTION_getInterfaceHash = 16777214;
        static final int TRANSACTION_getInterfaceVersion = 16777215;
        static final int TRANSACTION_getOemNetd = 94;
        static final int TRANSACTION_getProcSysNet = 17;
        static final int TRANSACTION_idletimerAddInterface = 34;
        static final int TRANSACTION_idletimerRemoveInterface = 35;
        static final int TRANSACTION_interfaceAddAddress = 15;
        static final int TRANSACTION_interfaceClearAddrs = 84;
        static final int TRANSACTION_interfaceDelAddress = 16;
        static final int TRANSACTION_interfaceGetCfg = 81;
        static final int TRANSACTION_interfaceGetList = 80;
        static final int TRANSACTION_interfaceSetCfg = 82;
        static final int TRANSACTION_interfaceSetEnableIPv6 = 85;
        static final int TRANSACTION_interfaceSetIPv6PrivacyExtensions = 83;
        static final int TRANSACTION_interfaceSetMtu = 86;
        static final int TRANSACTION_ipSecAddSecurityAssociation = 21;
        static final int TRANSACTION_ipSecAddSecurityPolicy = 25;
        static final int TRANSACTION_ipSecAddTunnelInterface = 28;
        static final int TRANSACTION_ipSecAllocateSpi = 20;
        static final int TRANSACTION_ipSecApplyTransportModeTransform = 23;
        static final int TRANSACTION_ipSecDeleteSecurityAssociation = 22;
        static final int TRANSACTION_ipSecDeleteSecurityPolicy = 27;
        static final int TRANSACTION_ipSecRemoveTransportModeTransform = 24;
        static final int TRANSACTION_ipSecRemoveTunnelInterface = 30;
        static final int TRANSACTION_ipSecSetEncapSocketOwner = 19;
        static final int TRANSACTION_ipSecUpdateSecurityPolicy = 26;
        static final int TRANSACTION_ipSecUpdateTunnelInterface = 29;
        static final int TRANSACTION_ipfwdAddInterfaceForward = 43;
        static final int TRANSACTION_ipfwdDisableForwarding = 42;
        static final int TRANSACTION_ipfwdEnableForwarding = 41;
        static final int TRANSACTION_ipfwdEnabled = 39;
        static final int TRANSACTION_ipfwdGetRequesterList = 40;
        static final int TRANSACTION_ipfwdRemoveInterfaceForward = 44;
        static final int TRANSACTION_isAlive = 1;
        static final int TRANSACTION_networkAddInterface = 7;
        static final int TRANSACTION_networkAddLegacyRoute = 64;
        static final int TRANSACTION_networkAddRoute = 62;
        static final int TRANSACTION_networkAddRouteParcel = 97;
        static final int TRANSACTION_networkAddUidRanges = 9;
        static final int TRANSACTION_networkCanProtect = 75;
        static final int TRANSACTION_networkClearDefault = 68;
        static final int TRANSACTION_networkClearPermissionForUser = 71;
        static final int TRANSACTION_networkCreatePhysical = 4;
        static final int TRANSACTION_networkCreateVpn = 5;
        static final int TRANSACTION_networkDestroy = 6;
        static final int TRANSACTION_networkGetDefault = 66;
        static final int TRANSACTION_networkRejectNonSecureVpn = 11;
        static final int TRANSACTION_networkRemoveInterface = 8;
        static final int TRANSACTION_networkRemoveLegacyRoute = 65;
        static final int TRANSACTION_networkRemoveRoute = 63;
        static final int TRANSACTION_networkRemoveRouteParcel = 99;
        static final int TRANSACTION_networkRemoveUidRanges = 10;
        static final int TRANSACTION_networkSetDefault = 67;
        static final int TRANSACTION_networkSetPermissionForNetwork = 69;
        static final int TRANSACTION_networkSetPermissionForUser = 70;
        static final int TRANSACTION_networkSetProtectAllow = 73;
        static final int TRANSACTION_networkSetProtectDeny = 74;
        static final int TRANSACTION_networkUpdateRouteParcel = 98;
        static final int TRANSACTION_registerUnsolicitedEventListener = 90;
        static final int TRANSACTION_setIPv6AddrGenMode = 33;
        static final int TRANSACTION_setProcSysNet = 18;
        static final int TRANSACTION_setTcpRWmemorySize = 89;
        static final int TRANSACTION_socketDestroy = 12;
        static final int TRANSACTION_strictUidCleartextPenalty = 36;
        static final int TRANSACTION_tetherAddForward = 87;
        static final int TRANSACTION_tetherApplyDnsInterfaces = 13;
        static final int TRANSACTION_tetherDnsList = 61;
        static final int TRANSACTION_tetherDnsSet = 60;
        static final int TRANSACTION_tetherGetStats = 14;
        static final int TRANSACTION_tetherInterfaceAdd = 57;
        static final int TRANSACTION_tetherInterfaceList = 59;
        static final int TRANSACTION_tetherInterfaceRemove = 58;
        static final int TRANSACTION_tetherIsEnabled = 56;
        static final int TRANSACTION_tetherOffloadGetAndClearStats = 104;
        static final int TRANSACTION_tetherOffloadGetStats = 102;
        static final int TRANSACTION_tetherOffloadRuleAdd = 100;
        static final int TRANSACTION_tetherOffloadRuleRemove = 101;
        static final int TRANSACTION_tetherOffloadSetInterfaceQuota = 103;
        static final int TRANSACTION_tetherRemoveForward = 88;
        static final int TRANSACTION_tetherStart = 54;
        static final int TRANSACTION_tetherStartWithConfiguration = 95;
        static final int TRANSACTION_tetherStop = 55;
        static final int TRANSACTION_trafficSetNetPermForUids = 72;
        static final int TRANSACTION_trafficSwapActiveStatsMap = 93;
        static final int TRANSACTION_wakeupAddInterface = 31;
        static final int TRANSACTION_wakeupDelInterface = 32;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetd asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof INetd)) {
                return (INetd) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ParcelFileDescriptor parcelFileDescriptor;
            ParcelFileDescriptor parcelFileDescriptor2;
            ParcelFileDescriptor parcelFileDescriptor3;
            InterfaceConfigurationParcel interfaceConfigurationParcelCreateFromParcel;
            TetherConfigParcel tetherConfigParcelCreateFromParcel;
            RouteInfoParcel routeInfoParcelCreateFromParcel;
            RouteInfoParcel routeInfoParcelCreateFromParcel2;
            RouteInfoParcel routeInfoParcelCreateFromParcel3;
            TetherOffloadRuleParcel tetherOffloadRuleParcelCreateFromParcel;
            TetherOffloadRuleParcel tetherOffloadRuleParcelCreateFromParcel2;
            String str = DESCRIPTOR;
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(str);
                    boolean zIsAlive = isAlive();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAlive ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(str);
                    boolean zFirewallReplaceUidChain = firewallReplaceUidChain(parcel.readString(), parcel.readInt() != 0, parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zFirewallReplaceUidChain ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(str);
                    boolean zBandwidthEnableDataSaver = bandwidthEnableDataSaver(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zBandwidthEnableDataSaver ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(str);
                    networkCreatePhysical(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkCreateVpn /* 5 */:
                    parcel.enforceInterface(str);
                    networkCreateVpn(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(str);
                    networkDestroy(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(str);
                    networkAddInterface(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(str);
                    networkRemoveInterface(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkAddUidRanges /* 9 */:
                    parcel.enforceInterface(str);
                    networkAddUidRanges(parcel.readInt(), (UidRangeParcel[]) parcel.createTypedArray(UidRangeParcel.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(str);
                    networkRemoveUidRanges(parcel.readInt(), (UidRangeParcel[]) parcel.createTypedArray(UidRangeParcel.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkRejectNonSecureVpn /* 11 */:
                    parcel.enforceInterface(str);
                    networkRejectNonSecureVpn(parcel.readInt() != 0, (UidRangeParcel[]) parcel.createTypedArray(UidRangeParcel.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(str);
                    socketDestroy((UidRangeParcel[]) parcel.createTypedArray(UidRangeParcel.CREATOR), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherApplyDnsInterfaces /* 13 */:
                    parcel.enforceInterface(str);
                    boolean zTetherApplyDnsInterfaces = tetherApplyDnsInterfaces();
                    parcel2.writeNoException();
                    parcel2.writeInt(zTetherApplyDnsInterfaces ? 1 : 0);
                    return true;
                case TRANSACTION_tetherGetStats /* 14 */:
                    parcel.enforceInterface(str);
                    TetherStatsParcel[] tetherStatsParcelArrTetherGetStats = tetherGetStats();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(tetherStatsParcelArrTetherGetStats, 1);
                    return true;
                case TRANSACTION_interfaceAddAddress /* 15 */:
                    parcel.enforceInterface(str);
                    interfaceAddAddress(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(str);
                    interfaceDelAddress(parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getProcSysNet /* 17 */:
                    parcel.enforceInterface(str);
                    String procSysNet = getProcSysNet(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(procSysNet);
                    return true;
                case TRANSACTION_setProcSysNet /* 18 */:
                    parcel.enforceInterface(str);
                    setProcSysNet(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecSetEncapSocketOwner /* 19 */:
                    parcel.enforceInterface(str);
                    if (parcel.readInt() != 0) {
                        parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                    } else {
                        parcelFileDescriptor = null;
                    }
                    ipSecSetEncapSocketOwner(parcelFileDescriptor, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(str);
                    int iIpSecAllocateSpi = ipSecAllocateSpi(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iIpSecAllocateSpi);
                    return true;
                case TRANSACTION_ipSecAddSecurityAssociation /* 21 */:
                    parcel.enforceInterface(str);
                    ipSecAddSecurityAssociation(parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readString(), parcel.createByteArray(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecDeleteSecurityAssociation /* 22 */:
                    parcel.enforceInterface(str);
                    ipSecDeleteSecurityAssociation(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecApplyTransportModeTransform /* 23 */:
                    parcel.enforceInterface(str);
                    if (parcel.readInt() != 0) {
                        parcelFileDescriptor2 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                    } else {
                        parcelFileDescriptor2 = null;
                    }
                    ipSecApplyTransportModeTransform(parcelFileDescriptor2, parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecRemoveTransportModeTransform /* 24 */:
                    parcel.enforceInterface(str);
                    if (parcel.readInt() != 0) {
                        parcelFileDescriptor3 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                    } else {
                        parcelFileDescriptor3 = null;
                    }
                    ipSecRemoveTransportModeTransform(parcelFileDescriptor3);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecAddSecurityPolicy /* 25 */:
                    parcel.enforceInterface(str);
                    ipSecAddSecurityPolicy(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecUpdateSecurityPolicy /* 26 */:
                    parcel.enforceInterface(str);
                    ipSecUpdateSecurityPolicy(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecDeleteSecurityPolicy /* 27 */:
                    parcel.enforceInterface(str);
                    ipSecDeleteSecurityPolicy(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecAddTunnelInterface /* 28 */:
                    parcel.enforceInterface(str);
                    ipSecAddTunnelInterface(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecUpdateTunnelInterface /* 29 */:
                    parcel.enforceInterface(str);
                    ipSecUpdateTunnelInterface(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipSecRemoveTunnelInterface /* 30 */:
                    parcel.enforceInterface(str);
                    ipSecRemoveTunnelInterface(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_wakeupAddInterface /* 31 */:
                    parcel.enforceInterface(str);
                    wakeupAddInterface(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(str);
                    wakeupDelInterface(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setIPv6AddrGenMode /* 33 */:
                    parcel.enforceInterface(str);
                    setIPv6AddrGenMode(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_idletimerAddInterface /* 34 */:
                    parcel.enforceInterface(str);
                    idletimerAddInterface(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_idletimerRemoveInterface /* 35 */:
                    parcel.enforceInterface(str);
                    idletimerRemoveInterface(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_strictUidCleartextPenalty /* 36 */:
                    parcel.enforceInterface(str);
                    strictUidCleartextPenalty(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_clatdStart /* 37 */:
                    parcel.enforceInterface(str);
                    String strClatdStart = clatdStart(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(strClatdStart);
                    return true;
                case 38:
                    parcel.enforceInterface(str);
                    clatdStop(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipfwdEnabled /* 39 */:
                    parcel.enforceInterface(str);
                    boolean zIpfwdEnabled = ipfwdEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIpfwdEnabled ? 1 : 0);
                    return true;
                case TRANSACTION_ipfwdGetRequesterList /* 40 */:
                    parcel.enforceInterface(str);
                    String[] strArrIpfwdGetRequesterList = ipfwdGetRequesterList();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrIpfwdGetRequesterList);
                    return true;
                case TRANSACTION_ipfwdEnableForwarding /* 41 */:
                    parcel.enforceInterface(str);
                    ipfwdEnableForwarding(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipfwdDisableForwarding /* 42 */:
                    parcel.enforceInterface(str);
                    ipfwdDisableForwarding(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipfwdAddInterfaceForward /* 43 */:
                    parcel.enforceInterface(str);
                    ipfwdAddInterfaceForward(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_ipfwdRemoveInterfaceForward /* 44 */:
                    parcel.enforceInterface(str);
                    ipfwdRemoveInterfaceForward(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_bandwidthSetInterfaceQuota /* 45 */:
                    parcel.enforceInterface(str);
                    bandwidthSetInterfaceQuota(parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_bandwidthRemoveInterfaceQuota /* 46 */:
                    parcel.enforceInterface(str);
                    bandwidthRemoveInterfaceQuota(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_bandwidthSetInterfaceAlert /* 47 */:
                    parcel.enforceInterface(str);
                    bandwidthSetInterfaceAlert(parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(str);
                    bandwidthRemoveInterfaceAlert(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_bandwidthSetGlobalAlert /* 49 */:
                    parcel.enforceInterface(str);
                    bandwidthSetGlobalAlert(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_bandwidthAddNaughtyApp /* 50 */:
                    parcel.enforceInterface(str);
                    bandwidthAddNaughtyApp(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_bandwidthRemoveNaughtyApp /* 51 */:
                    parcel.enforceInterface(str);
                    bandwidthRemoveNaughtyApp(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_bandwidthAddNiceApp /* 52 */:
                    parcel.enforceInterface(str);
                    bandwidthAddNiceApp(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(str);
                    bandwidthRemoveNiceApp(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherStart /* 54 */:
                    parcel.enforceInterface(str);
                    tetherStart(parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherStop /* 55 */:
                    parcel.enforceInterface(str);
                    tetherStop();
                    parcel2.writeNoException();
                    return true;
                case 56:
                    parcel.enforceInterface(str);
                    boolean zTetherIsEnabled = tetherIsEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zTetherIsEnabled ? 1 : 0);
                    return true;
                case TRANSACTION_tetherInterfaceAdd /* 57 */:
                    parcel.enforceInterface(str);
                    tetherInterfaceAdd(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherInterfaceRemove /* 58 */:
                    parcel.enforceInterface(str);
                    tetherInterfaceRemove(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherInterfaceList /* 59 */:
                    parcel.enforceInterface(str);
                    String[] strArrTetherInterfaceList = tetherInterfaceList();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrTetherInterfaceList);
                    return true;
                case TRANSACTION_tetherDnsSet /* 60 */:
                    parcel.enforceInterface(str);
                    tetherDnsSet(parcel.readInt(), parcel.createStringArray());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherDnsList /* 61 */:
                    parcel.enforceInterface(str);
                    String[] strArrTetherDnsList = tetherDnsList();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrTetherDnsList);
                    return true;
                case TRANSACTION_networkAddRoute /* 62 */:
                    parcel.enforceInterface(str);
                    networkAddRoute(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkRemoveRoute /* 63 */:
                    parcel.enforceInterface(str);
                    networkRemoveRoute(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 64:
                    parcel.enforceInterface(str);
                    networkAddLegacyRoute(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkRemoveLegacyRoute /* 65 */:
                    parcel.enforceInterface(str);
                    networkRemoveLegacyRoute(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkGetDefault /* 66 */:
                    parcel.enforceInterface(str);
                    int iNetworkGetDefault = networkGetDefault();
                    parcel2.writeNoException();
                    parcel2.writeInt(iNetworkGetDefault);
                    return true;
                case TRANSACTION_networkSetDefault /* 67 */:
                    parcel.enforceInterface(str);
                    networkSetDefault(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkClearDefault /* 68 */:
                    parcel.enforceInterface(str);
                    networkClearDefault();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkSetPermissionForNetwork /* 69 */:
                    parcel.enforceInterface(str);
                    networkSetPermissionForNetwork(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkSetPermissionForUser /* 70 */:
                    parcel.enforceInterface(str);
                    networkSetPermissionForUser(parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkClearPermissionForUser /* 71 */:
                    parcel.enforceInterface(str);
                    networkClearPermissionForUser(parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 72:
                    parcel.enforceInterface(str);
                    trafficSetNetPermForUids(parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkSetProtectAllow /* 73 */:
                    parcel.enforceInterface(str);
                    networkSetProtectAllow(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkSetProtectDeny /* 74 */:
                    parcel.enforceInterface(str);
                    networkSetProtectDeny(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkCanProtect /* 75 */:
                    parcel.enforceInterface(str);
                    boolean zNetworkCanProtect = networkCanProtect(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zNetworkCanProtect ? 1 : 0);
                    return true;
                case TRANSACTION_firewallSetFirewallType /* 76 */:
                    parcel.enforceInterface(str);
                    firewallSetFirewallType(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_firewallSetInterfaceRule /* 77 */:
                    parcel.enforceInterface(str);
                    firewallSetInterfaceRule(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_firewallSetUidRule /* 78 */:
                    parcel.enforceInterface(str);
                    firewallSetUidRule(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_firewallEnableChildChain /* 79 */:
                    parcel.enforceInterface(str);
                    firewallEnableChildChain(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_interfaceGetList /* 80 */:
                    parcel.enforceInterface(str);
                    String[] strArrInterfaceGetList = interfaceGetList();
                    parcel2.writeNoException();
                    parcel2.writeStringArray(strArrInterfaceGetList);
                    return true;
                case TRANSACTION_interfaceGetCfg /* 81 */:
                    parcel.enforceInterface(str);
                    InterfaceConfigurationParcel interfaceConfigurationParcelInterfaceGetCfg = interfaceGetCfg(parcel.readString());
                    parcel2.writeNoException();
                    if (interfaceConfigurationParcelInterfaceGetCfg != null) {
                        parcel2.writeInt(1);
                        interfaceConfigurationParcelInterfaceGetCfg.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case TRANSACTION_interfaceSetCfg /* 82 */:
                    parcel.enforceInterface(str);
                    if (parcel.readInt() != 0) {
                        interfaceConfigurationParcelCreateFromParcel = InterfaceConfigurationParcel.CREATOR.createFromParcel(parcel);
                    } else {
                        interfaceConfigurationParcelCreateFromParcel = null;
                    }
                    interfaceSetCfg(interfaceConfigurationParcelCreateFromParcel);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_interfaceSetIPv6PrivacyExtensions /* 83 */:
                    parcel.enforceInterface(str);
                    interfaceSetIPv6PrivacyExtensions(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_interfaceClearAddrs /* 84 */:
                    parcel.enforceInterface(str);
                    interfaceClearAddrs(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_interfaceSetEnableIPv6 /* 85 */:
                    parcel.enforceInterface(str);
                    interfaceSetEnableIPv6(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_interfaceSetMtu /* 86 */:
                    parcel.enforceInterface(str);
                    interfaceSetMtu(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherAddForward /* 87 */:
                    parcel.enforceInterface(str);
                    tetherAddForward(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 88:
                    parcel.enforceInterface(str);
                    tetherRemoveForward(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_setTcpRWmemorySize /* 89 */:
                    parcel.enforceInterface(str);
                    setTcpRWmemorySize(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_registerUnsolicitedEventListener /* 90 */:
                    parcel.enforceInterface(str);
                    registerUnsolicitedEventListener(INetdUnsolicitedEventListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_firewallAddUidInterfaceRules /* 91 */:
                    parcel.enforceInterface(str);
                    firewallAddUidInterfaceRules(parcel.readString(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_firewallRemoveUidInterfaceRules /* 92 */:
                    parcel.enforceInterface(str);
                    firewallRemoveUidInterfaceRules(parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_trafficSwapActiveStatsMap /* 93 */:
                    parcel.enforceInterface(str);
                    trafficSwapActiveStatsMap();
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getOemNetd /* 94 */:
                    parcel.enforceInterface(str);
                    IBinder oemNetd = getOemNetd();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(oemNetd);
                    return true;
                case TRANSACTION_tetherStartWithConfiguration /* 95 */:
                    parcel.enforceInterface(str);
                    if (parcel.readInt() != 0) {
                        tetherConfigParcelCreateFromParcel = TetherConfigParcel.CREATOR.createFromParcel(parcel);
                    } else {
                        tetherConfigParcelCreateFromParcel = null;
                    }
                    tetherStartWithConfiguration(tetherConfigParcelCreateFromParcel);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_getFwmarkForNetwork /* 96 */:
                    parcel.enforceInterface(str);
                    MarkMaskParcel fwmarkForNetwork = getFwmarkForNetwork(parcel.readInt());
                    parcel2.writeNoException();
                    if (fwmarkForNetwork != null) {
                        parcel2.writeInt(1);
                        fwmarkForNetwork.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case TRANSACTION_networkAddRouteParcel /* 97 */:
                    parcel.enforceInterface(str);
                    int i3 = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        routeInfoParcelCreateFromParcel = RouteInfoParcel.CREATOR.createFromParcel(parcel);
                    } else {
                        routeInfoParcelCreateFromParcel = null;
                    }
                    networkAddRouteParcel(i3, routeInfoParcelCreateFromParcel);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_networkUpdateRouteParcel /* 98 */:
                    parcel.enforceInterface(str);
                    int i4 = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        routeInfoParcelCreateFromParcel2 = RouteInfoParcel.CREATOR.createFromParcel(parcel);
                    } else {
                        routeInfoParcelCreateFromParcel2 = null;
                    }
                    networkUpdateRouteParcel(i4, routeInfoParcelCreateFromParcel2);
                    parcel2.writeNoException();
                    return true;
                case 99:
                    parcel.enforceInterface(str);
                    int i5 = parcel.readInt();
                    if (parcel.readInt() != 0) {
                        routeInfoParcelCreateFromParcel3 = RouteInfoParcel.CREATOR.createFromParcel(parcel);
                    } else {
                        routeInfoParcelCreateFromParcel3 = null;
                    }
                    networkRemoveRouteParcel(i5, routeInfoParcelCreateFromParcel3);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherOffloadRuleAdd /* 100 */:
                    parcel.enforceInterface(str);
                    if (parcel.readInt() != 0) {
                        tetherOffloadRuleParcelCreateFromParcel = TetherOffloadRuleParcel.CREATOR.createFromParcel(parcel);
                    } else {
                        tetherOffloadRuleParcelCreateFromParcel = null;
                    }
                    tetherOffloadRuleAdd(tetherOffloadRuleParcelCreateFromParcel);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherOffloadRuleRemove /* 101 */:
                    parcel.enforceInterface(str);
                    if (parcel.readInt() != 0) {
                        tetherOffloadRuleParcelCreateFromParcel2 = TetherOffloadRuleParcel.CREATOR.createFromParcel(parcel);
                    } else {
                        tetherOffloadRuleParcelCreateFromParcel2 = null;
                    }
                    tetherOffloadRuleRemove(tetherOffloadRuleParcelCreateFromParcel2);
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherOffloadGetStats /* 102 */:
                    parcel.enforceInterface(str);
                    TetherStatsParcel[] tetherStatsParcelArrTetherOffloadGetStats = tetherOffloadGetStats();
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(tetherStatsParcelArrTetherOffloadGetStats, 1);
                    return true;
                case TRANSACTION_tetherOffloadSetInterfaceQuota /* 103 */:
                    parcel.enforceInterface(str);
                    tetherOffloadSetInterfaceQuota(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case TRANSACTION_tetherOffloadGetAndClearStats /* 104 */:
                    parcel.enforceInterface(str);
                    TetherStatsParcel tetherStatsParcelTetherOffloadGetAndClearStats = tetherOffloadGetAndClearStats(parcel.readInt());
                    parcel2.writeNoException();
                    if (tetherStatsParcelTetherOffloadGetAndClearStats != null) {
                        parcel2.writeInt(1);
                        tetherStatsParcelTetherOffloadGetAndClearStats.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    switch (i) {
                        case TRANSACTION_getInterfaceHash /* 16777214 */:
                            parcel.enforceInterface(str);
                            parcel2.writeNoException();
                            parcel2.writeString(getInterfaceHash());
                            return true;
                        case TRANSACTION_getInterfaceVersion /* 16777215 */:
                            parcel.enforceInterface(str);
                            parcel2.writeNoException();
                            parcel2.writeInt(getInterfaceVersion());
                            return true;
                        default:
                            return super.onTransact(i, parcel, parcel2, i2);
                    }
            }
        }

        private static class Proxy implements INetd {
            public static INetd sDefaultImpl;
            private IBinder mRemote;
            private int mCachedVersion = -1;
            private String mCachedHash = "-1";

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // android.net.INetd
            public boolean isAlive() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAlive();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public boolean firewallReplaceUidChain(String chainName, boolean isWhitelist, int[] uids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(chainName);
                    _data.writeInt(isWhitelist ? 1 : 0);
                    _data.writeIntArray(uids);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().firewallReplaceUidChain(chainName, isWhitelist, uids);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public boolean bandwidthEnableDataSaver(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().bandwidthEnableDataSaver(enable);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkCreatePhysical(int netId, int permission) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeInt(permission);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkCreatePhysical(netId, permission);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkCreateVpn(int netId, boolean secure) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeInt(secure ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkCreateVpn, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkCreateVpn(netId, secure);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkDestroy(int netId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkDestroy(netId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkAddInterface(int netId, String iface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeString(iface);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkAddInterface(netId, iface);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkRemoveInterface(int netId, String iface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeString(iface);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkRemoveInterface(netId, iface);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkAddUidRanges(int netId, UidRangeParcel[] uidRanges) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeTypedArray(uidRanges, 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkAddUidRanges, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkAddUidRanges(netId, uidRanges);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkRemoveUidRanges(int netId, UidRangeParcel[] uidRanges) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeTypedArray(uidRanges, 0);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkRemoveUidRanges(netId, uidRanges);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkRejectNonSecureVpn(boolean add, UidRangeParcel[] uidRanges) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(add ? 1 : 0);
                    _data.writeTypedArray(uidRanges, 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkRejectNonSecureVpn, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkRejectNonSecureVpn(add, uidRanges);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void socketDestroy(UidRangeParcel[] uidRanges, int[] exemptUids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedArray(uidRanges, 0);
                    _data.writeIntArray(exemptUids);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().socketDestroy(uidRanges, exemptUids);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public boolean tetherApplyDnsInterfaces() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherApplyDnsInterfaces, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tetherApplyDnsInterfaces();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public TetherStatsParcel[] tetherGetStats() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherGetStats, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tetherGetStats();
                    }
                    _reply.readException();
                    TetherStatsParcel[] _result = (TetherStatsParcel[]) _reply.createTypedArray(TetherStatsParcel.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void interfaceAddAddress(String ifName, String addrString, int prefixLength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeString(addrString);
                    _data.writeInt(prefixLength);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_interfaceAddAddress, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceAddAddress(ifName, addrString, prefixLength);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void interfaceDelAddress(String ifName, String addrString, int prefixLength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeString(addrString);
                    _data.writeInt(prefixLength);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceDelAddress(ifName, addrString, prefixLength);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public String getProcSysNet(int ipversion, int which, String ifname, String parameter) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(ipversion);
                    _data.writeInt(which);
                    _data.writeString(ifname);
                    _data.writeString(parameter);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getProcSysNet, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProcSysNet(ipversion, which, ifname, parameter);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void setProcSysNet(int ipversion, int which, String ifname, String parameter, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(ipversion);
                    _data.writeInt(which);
                    _data.writeString(ifname);
                    _data.writeString(parameter);
                    _data.writeString(value);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setProcSysNet, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setProcSysNet(ipversion, which, ifname, parameter, value);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void ipSecSetEncapSocketOwner(ParcelFileDescriptor socket, int newUid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (socket != null) {
                        _data.writeInt(1);
                        socket.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(newUid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecSetEncapSocketOwner, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ipSecSetEncapSocketOwner(socket, newUid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public int ipSecAllocateSpi(int transformId, String sourceAddress, String destinationAddress, int spi) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(transformId);
                    _data.writeString(sourceAddress);
                    _data.writeString(destinationAddress);
                    _data.writeInt(spi);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ipSecAllocateSpi(transformId, sourceAddress, destinationAddress, spi);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void ipSecAddSecurityAssociation(int transformId, int mode, String sourceAddress, String destinationAddress, int underlyingNetId, int spi, int markValue, int markMask, String authAlgo, byte[] authKey, int authTruncBits, String cryptAlgo, byte[] cryptKey, int cryptTruncBits, String aeadAlgo, byte[] aeadKey, int aeadIcvBits, int encapType, int encapLocalPort, int encapRemotePort, int interfaceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(transformId);
                    _data.writeInt(mode);
                    _data.writeString(sourceAddress);
                    _data.writeString(destinationAddress);
                    _data.writeInt(underlyingNetId);
                    _data.writeInt(spi);
                    _data.writeInt(markValue);
                    _data.writeInt(markMask);
                    _data.writeString(authAlgo);
                    _data.writeByteArray(authKey);
                    _data.writeInt(authTruncBits);
                    _data.writeString(cryptAlgo);
                    _data.writeByteArray(cryptKey);
                    _data.writeInt(cryptTruncBits);
                    _data.writeString(aeadAlgo);
                    _data.writeByteArray(aeadKey);
                    _data.writeInt(aeadIcvBits);
                    _data.writeInt(encapType);
                    _data.writeInt(encapLocalPort);
                    _data.writeInt(encapRemotePort);
                    _data.writeInt(interfaceId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecAddSecurityAssociation, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ipSecAddSecurityAssociation(transformId, mode, sourceAddress, destinationAddress, underlyingNetId, spi, markValue, markMask, authAlgo, authKey, authTruncBits, cryptAlgo, cryptKey, cryptTruncBits, aeadAlgo, aeadKey, aeadIcvBits, encapType, encapLocalPort, encapRemotePort, interfaceId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void ipSecDeleteSecurityAssociation(int transformId, String sourceAddress, String destinationAddress, int spi, int markValue, int markMask, int interfaceId) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeInt(transformId);
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(sourceAddress);
                } catch (Throwable th3) {
                    th = th3;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(destinationAddress);
                    try {
                        _data.writeInt(spi);
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(markValue);
                        _data.writeInt(markMask);
                        _data.writeInt(interfaceId);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecDeleteSecurityAssociation, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().ipSecDeleteSecurityAssociation(transformId, sourceAddress, destinationAddress, spi, markValue, markMask, interfaceId);
                            _reply.recycle();
                            _data.recycle();
                        } else {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.net.INetd
            public void ipSecApplyTransportModeTransform(ParcelFileDescriptor socket, int transformId, int direction, String sourceAddress, String destinationAddress, int spi) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (socket != null) {
                        _data.writeInt(1);
                        socket.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    _data.writeInt(transformId);
                } catch (Throwable th2) {
                    th = th2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeInt(direction);
                } catch (Throwable th3) {
                    th = th3;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(sourceAddress);
                } catch (Throwable th4) {
                    th = th4;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
                try {
                    _data.writeString(destinationAddress);
                    try {
                        _data.writeInt(spi);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecApplyTransportModeTransform, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().ipSecApplyTransportModeTransform(socket, transformId, direction, sourceAddress, destinationAddress, spi);
                            _reply.recycle();
                            _data.recycle();
                        } else {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // android.net.INetd
            public void ipSecRemoveTransportModeTransform(ParcelFileDescriptor socket) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (socket != null) {
                        _data.writeInt(1);
                        socket.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecRemoveTransportModeTransform, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ipSecRemoveTransportModeTransform(socket);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void ipSecAddSecurityPolicy(int transformId, int selAddrFamily, int direction, String tmplSrcAddress, String tmplDstAddress, int spi, int markValue, int markMask, int interfaceId) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(transformId);
                    } catch (Throwable th) {
                        th = th;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(selAddrFamily);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(direction);
                        _data.writeString(tmplSrcAddress);
                        _data.writeString(tmplDstAddress);
                        _data.writeInt(spi);
                        _data.writeInt(markValue);
                        _data.writeInt(markMask);
                        _data.writeInt(interfaceId);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecAddSecurityPolicy, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().ipSecAddSecurityPolicy(transformId, selAddrFamily, direction, tmplSrcAddress, tmplDstAddress, spi, markValue, markMask, interfaceId);
                            _reply.recycle();
                            _data.recycle();
                        } else {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // android.net.INetd
            public void ipSecUpdateSecurityPolicy(int transformId, int selAddrFamily, int direction, String tmplSrcAddress, String tmplDstAddress, int spi, int markValue, int markMask, int interfaceId) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(transformId);
                    } catch (Throwable th) {
                        th = th;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(selAddrFamily);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(direction);
                        _data.writeString(tmplSrcAddress);
                        _data.writeString(tmplDstAddress);
                        _data.writeInt(spi);
                        _data.writeInt(markValue);
                        _data.writeInt(markMask);
                        _data.writeInt(interfaceId);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecUpdateSecurityPolicy, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().ipSecUpdateSecurityPolicy(transformId, selAddrFamily, direction, tmplSrcAddress, tmplDstAddress, spi, markValue, markMask, interfaceId);
                            _reply.recycle();
                            _data.recycle();
                        } else {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            }

            @Override // android.net.INetd
            public void ipSecDeleteSecurityPolicy(int transformId, int selAddrFamily, int direction, int markValue, int markMask, int interfaceId) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(transformId);
                    } catch (Throwable th) {
                        th = th;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(selAddrFamily);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(direction);
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(markValue);
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(markMask);
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(interfaceId);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecDeleteSecurityPolicy, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().ipSecDeleteSecurityPolicy(transformId, selAddrFamily, direction, markValue, markMask, interfaceId);
                            _reply.recycle();
                            _data.recycle();
                        } else {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // android.net.INetd
            public void ipSecAddTunnelInterface(String deviceName, String localAddress, String remoteAddress, int iKey, int oKey, int interfaceId) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(deviceName);
                    } catch (Throwable th) {
                        th = th;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(localAddress);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(remoteAddress);
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(iKey);
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(oKey);
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(interfaceId);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecAddTunnelInterface, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().ipSecAddTunnelInterface(deviceName, localAddress, remoteAddress, iKey, oKey, interfaceId);
                            _reply.recycle();
                            _data.recycle();
                        } else {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // android.net.INetd
            public void ipSecUpdateTunnelInterface(String deviceName, String localAddress, String remoteAddress, int iKey, int oKey, int interfaceId) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(deviceName);
                    } catch (Throwable th) {
                        th = th;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(localAddress);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(remoteAddress);
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(iKey);
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(oKey);
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(interfaceId);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecUpdateTunnelInterface, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().ipSecUpdateTunnelInterface(deviceName, localAddress, remoteAddress, iKey, oKey, interfaceId);
                            _reply.recycle();
                            _data.recycle();
                        } else {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }

            @Override // android.net.INetd
            public void ipSecRemoveTunnelInterface(String deviceName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceName);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipSecRemoveTunnelInterface, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ipSecRemoveTunnelInterface(deviceName);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void wakeupAddInterface(String ifName, String prefix, int mark, int mask) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeString(prefix);
                    _data.writeInt(mark);
                    _data.writeInt(mask);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_wakeupAddInterface, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().wakeupAddInterface(ifName, prefix, mark, mask);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void wakeupDelInterface(String ifName, String prefix, int mark, int mask) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeString(prefix);
                    _data.writeInt(mark);
                    _data.writeInt(mask);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().wakeupDelInterface(ifName, prefix, mark, mask);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void setIPv6AddrGenMode(String ifName, int mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeInt(mode);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setIPv6AddrGenMode, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setIPv6AddrGenMode(ifName, mode);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void idletimerAddInterface(String ifName, int timeout, String classLabel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeInt(timeout);
                    _data.writeString(classLabel);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_idletimerAddInterface, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().idletimerAddInterface(ifName, timeout, classLabel);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void idletimerRemoveInterface(String ifName, int timeout, String classLabel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeInt(timeout);
                    _data.writeString(classLabel);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_idletimerRemoveInterface, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().idletimerRemoveInterface(ifName, timeout, classLabel);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void strictUidCleartextPenalty(int uid, int policyPenalty) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    _data.writeInt(policyPenalty);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_strictUidCleartextPenalty, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().strictUidCleartextPenalty(uid, policyPenalty);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public String clatdStart(String ifName, String nat64Prefix) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeString(nat64Prefix);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_clatdStart, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clatdStart(ifName, nat64Prefix);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void clatdStop(String ifName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clatdStop(ifName);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public boolean ipfwdEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipfwdEnabled, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ipfwdEnabled();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public String[] ipfwdGetRequesterList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipfwdGetRequesterList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ipfwdGetRequesterList();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void ipfwdEnableForwarding(String requester) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(requester);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipfwdEnableForwarding, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ipfwdEnableForwarding(requester);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void ipfwdDisableForwarding(String requester) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(requester);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipfwdDisableForwarding, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ipfwdDisableForwarding(requester);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void ipfwdAddInterfaceForward(String fromIface, String toIface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(fromIface);
                    _data.writeString(toIface);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipfwdAddInterfaceForward, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ipfwdAddInterfaceForward(fromIface, toIface);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void ipfwdRemoveInterfaceForward(String fromIface, String toIface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(fromIface);
                    _data.writeString(toIface);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_ipfwdRemoveInterfaceForward, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().ipfwdRemoveInterfaceForward(fromIface, toIface);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void bandwidthSetInterfaceQuota(String ifName, long bytes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeLong(bytes);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_bandwidthSetInterfaceQuota, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().bandwidthSetInterfaceQuota(ifName, bytes);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void bandwidthRemoveInterfaceQuota(String ifName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_bandwidthRemoveInterfaceQuota, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().bandwidthRemoveInterfaceQuota(ifName);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void bandwidthSetInterfaceAlert(String ifName, long bytes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeLong(bytes);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_bandwidthSetInterfaceAlert, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().bandwidthSetInterfaceAlert(ifName, bytes);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void bandwidthRemoveInterfaceAlert(String ifName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    boolean _status = this.mRemote.transact(48, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().bandwidthRemoveInterfaceAlert(ifName);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void bandwidthSetGlobalAlert(long bytes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(bytes);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_bandwidthSetGlobalAlert, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().bandwidthSetGlobalAlert(bytes);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void bandwidthAddNaughtyApp(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_bandwidthAddNaughtyApp, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().bandwidthAddNaughtyApp(uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void bandwidthRemoveNaughtyApp(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_bandwidthRemoveNaughtyApp, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().bandwidthRemoveNaughtyApp(uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void bandwidthAddNiceApp(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_bandwidthAddNiceApp, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().bandwidthAddNiceApp(uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void bandwidthRemoveNiceApp(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().bandwidthRemoveNiceApp(uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherStart(String[] dhcpRanges) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringArray(dhcpRanges);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherStart, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherStart(dhcpRanges);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherStop() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherStop, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherStop();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public boolean tetherIsEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(56, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tetherIsEnabled();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherInterfaceAdd(String ifName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherInterfaceAdd, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherInterfaceAdd(ifName);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherInterfaceRemove(String ifName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherInterfaceRemove, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherInterfaceRemove(ifName);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public String[] tetherInterfaceList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherInterfaceList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tetherInterfaceList();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherDnsSet(int netId, String[] dnsAddrs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeStringArray(dnsAddrs);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherDnsSet, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherDnsSet(netId, dnsAddrs);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public String[] tetherDnsList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherDnsList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tetherDnsList();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkAddRoute(int netId, String ifName, String destination, String nextHop) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeString(ifName);
                    _data.writeString(destination);
                    _data.writeString(nextHop);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkAddRoute, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkAddRoute(netId, ifName, destination, nextHop);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkRemoveRoute(int netId, String ifName, String destination, String nextHop) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeString(ifName);
                    _data.writeString(destination);
                    _data.writeString(nextHop);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkRemoveRoute, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkRemoveRoute(netId, ifName, destination, nextHop);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkAddLegacyRoute(int netId, String ifName, String destination, String nextHop, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeString(ifName);
                    _data.writeString(destination);
                    _data.writeString(nextHop);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(64, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkAddLegacyRoute(netId, ifName, destination, nextHop, uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkRemoveLegacyRoute(int netId, String ifName, String destination, String nextHop, int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeString(ifName);
                    _data.writeString(destination);
                    _data.writeString(nextHop);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkRemoveLegacyRoute, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkRemoveLegacyRoute(netId, ifName, destination, nextHop, uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public int networkGetDefault() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkGetDefault, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().networkGetDefault();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkSetDefault(int netId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkSetDefault, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkSetDefault(netId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkClearDefault() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkClearDefault, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkClearDefault();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkSetPermissionForNetwork(int netId, int permission) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    _data.writeInt(permission);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkSetPermissionForNetwork, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkSetPermissionForNetwork(netId, permission);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkSetPermissionForUser(int permission, int[] uids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(permission);
                    _data.writeIntArray(uids);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkSetPermissionForUser, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkSetPermissionForUser(permission, uids);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkClearPermissionForUser(int[] uids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(uids);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkClearPermissionForUser, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkClearPermissionForUser(uids);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void trafficSetNetPermForUids(int permission, int[] uids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(permission);
                    _data.writeIntArray(uids);
                    boolean _status = this.mRemote.transact(72, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().trafficSetNetPermForUids(permission, uids);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkSetProtectAllow(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkSetProtectAllow, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkSetProtectAllow(uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkSetProtectDeny(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkSetProtectDeny, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkSetProtectDeny(uid);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public boolean networkCanProtect(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkCanProtect, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().networkCanProtect(uid);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void firewallSetFirewallType(int firewalltype) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(firewalltype);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_firewallSetFirewallType, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().firewallSetFirewallType(firewalltype);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void firewallSetInterfaceRule(String ifName, int firewallRule) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeInt(firewallRule);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_firewallSetInterfaceRule, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().firewallSetInterfaceRule(ifName, firewallRule);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void firewallSetUidRule(int childChain, int uid, int firewallRule) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(childChain);
                    _data.writeInt(uid);
                    _data.writeInt(firewallRule);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_firewallSetUidRule, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().firewallSetUidRule(childChain, uid, firewallRule);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void firewallEnableChildChain(int childChain, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(childChain);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_firewallEnableChildChain, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().firewallEnableChildChain(childChain, enable);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public String[] interfaceGetList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_interfaceGetList, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().interfaceGetList();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public InterfaceConfigurationParcel interfaceGetCfg(String ifName) throws RemoteException {
                InterfaceConfigurationParcel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_interfaceGetCfg, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().interfaceGetCfg(ifName);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = InterfaceConfigurationParcel.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void interfaceSetCfg(InterfaceConfigurationParcel cfg) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cfg != null) {
                        _data.writeInt(1);
                        cfg.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_interfaceSetCfg, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceSetCfg(cfg);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void interfaceSetIPv6PrivacyExtensions(String ifName, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_interfaceSetIPv6PrivacyExtensions, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceSetIPv6PrivacyExtensions(ifName, enable);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void interfaceClearAddrs(String ifName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_interfaceClearAddrs, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceClearAddrs(ifName);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void interfaceSetEnableIPv6(String ifName, boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_interfaceSetEnableIPv6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceSetEnableIPv6(ifName, enable);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void interfaceSetMtu(String ifName, int mtu) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeInt(mtu);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_interfaceSetMtu, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().interfaceSetMtu(ifName, mtu);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherAddForward(String intIface, String extIface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(intIface);
                    _data.writeString(extIface);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherAddForward, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherAddForward(intIface, extIface);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherRemoveForward(String intIface, String extIface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(intIface);
                    _data.writeString(extIface);
                    boolean _status = this.mRemote.transact(88, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherRemoveForward(intIface, extIface);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void setTcpRWmemorySize(String rmemValues, String wmemValues) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(rmemValues);
                    _data.writeString(wmemValues);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_setTcpRWmemorySize, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setTcpRWmemorySize(rmemValues, wmemValues);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void registerUnsolicitedEventListener(INetdUnsolicitedEventListener listener) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_registerUnsolicitedEventListener, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerUnsolicitedEventListener(listener);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void firewallAddUidInterfaceRules(String ifName, int[] uids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ifName);
                    _data.writeIntArray(uids);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_firewallAddUidInterfaceRules, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().firewallAddUidInterfaceRules(ifName, uids);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void firewallRemoveUidInterfaceRules(int[] uids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(uids);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_firewallRemoveUidInterfaceRules, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().firewallRemoveUidInterfaceRules(uids);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void trafficSwapActiveStatsMap() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_trafficSwapActiveStatsMap, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().trafficSwapActiveStatsMap();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public IBinder getOemNetd() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getOemNetd, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getOemNetd();
                    }
                    _reply.readException();
                    IBinder _result = _reply.readStrongBinder();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherStartWithConfiguration(TetherConfigParcel config) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (config != null) {
                        _data.writeInt(1);
                        config.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherStartWithConfiguration, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherStartWithConfiguration(config);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public MarkMaskParcel getFwmarkForNetwork(int netId) throws RemoteException {
                MarkMaskParcel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_getFwmarkForNetwork, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFwmarkForNetwork(netId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = MarkMaskParcel.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkAddRouteParcel(int netId, RouteInfoParcel routeInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    if (routeInfo != null) {
                        _data.writeInt(1);
                        routeInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkAddRouteParcel, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkAddRouteParcel(netId, routeInfo);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkUpdateRouteParcel(int netId, RouteInfoParcel routeInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    if (routeInfo != null) {
                        _data.writeInt(1);
                        routeInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_networkUpdateRouteParcel, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkUpdateRouteParcel(netId, routeInfo);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void networkRemoveRouteParcel(int netId, RouteInfoParcel routeInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(netId);
                    if (routeInfo != null) {
                        _data.writeInt(1);
                        routeInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(99, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().networkRemoveRouteParcel(netId, routeInfo);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherOffloadRuleAdd(TetherOffloadRuleParcel rule) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rule != null) {
                        _data.writeInt(1);
                        rule.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherOffloadRuleAdd, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherOffloadRuleAdd(rule);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherOffloadRuleRemove(TetherOffloadRuleParcel rule) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rule != null) {
                        _data.writeInt(1);
                        rule.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherOffloadRuleRemove, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherOffloadRuleRemove(rule);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public TetherStatsParcel[] tetherOffloadGetStats() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherOffloadGetStats, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tetherOffloadGetStats();
                    }
                    _reply.readException();
                    TetherStatsParcel[] _result = (TetherStatsParcel[]) _reply.createTypedArray(TetherStatsParcel.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public void tetherOffloadSetInterfaceQuota(int ifIndex, long quotaBytes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(ifIndex);
                    _data.writeLong(quotaBytes);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherOffloadSetInterfaceQuota, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tetherOffloadSetInterfaceQuota(ifIndex, quotaBytes);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public TetherStatsParcel tetherOffloadGetAndClearStats(int ifIndex) throws RemoteException {
                TetherStatsParcel _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(ifIndex);
                    boolean _status = this.mRemote.transact(Stub.TRANSACTION_tetherOffloadGetAndClearStats, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().tetherOffloadGetAndClearStats(ifIndex);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = TetherStatsParcel.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // android.net.INetd
            public int getInterfaceVersion() throws RemoteException {
                if (this.mCachedVersion == -1) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(Stub.DESCRIPTOR);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_getInterfaceVersion, data, reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceVersion();
                        }
                        reply.readException();
                        this.mCachedVersion = reply.readInt();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return this.mCachedVersion;
            }

            @Override // android.net.INetd
            public synchronized String getInterfaceHash() throws RemoteException {
                if ("-1".equals(this.mCachedHash)) {
                    Parcel data = Parcel.obtain();
                    Parcel reply = Parcel.obtain();
                    try {
                        data.writeInterfaceToken(Stub.DESCRIPTOR);
                        boolean _status = this.mRemote.transact(Stub.TRANSACTION_getInterfaceHash, data, reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            return Stub.getDefaultImpl().getInterfaceHash();
                        }
                        reply.readException();
                        this.mCachedHash = reply.readString();
                        reply.recycle();
                        data.recycle();
                    } finally {
                        reply.recycle();
                        data.recycle();
                    }
                }
                return this.mCachedHash;
            }
        }

        public static boolean setDefaultImpl(INetd impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static INetd getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
