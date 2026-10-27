package com.android.networkstack.tethering;

import android.app.usage.NetworkStatsManager;
import android.content.ContentResolver;
import android.net.InetAddresses;
import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NetworkStats;
import android.net.RouteInfo;
import android.net.netlink.ConntrackMessage;
import android.net.netlink.NetlinkConstants;
import android.net.netlink.NetlinkSocket;
import android.net.netstats.provider.NetworkStatsProvider;
import android.net.util.SharedLog;
import android.os.Handler;
import android.provider.Settings;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.text.TextUtils;
import android.util.Log;
import com.android.networkstack.tethering.OffloadHardwareInterface;
import com.android.networkstack.tethering.util.IndentingPrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class OffloadController {
    private static final OffloadHardwareInterface.ForwardedStats EMPTY_STATS = new OffloadHardwareInterface.ForwardedStats();
    private static final String TAG = "OffloadController";
    private boolean mConfigInitialized;
    private final ContentResolver mContentResolver;
    private boolean mControlInitialized;
    private final Dependencies mDeps;
    private final Handler mHandler;
    private final OffloadHardwareInterface mHwInterface;
    private final SharedLog mLog;
    private int mNatUpdateCallbacksReceived;
    private int mNatUpdateNetlinkErrors;
    private final OffloadTetheringStatsProvider mStatsProvider;
    private LinkProperties mUpstreamLinkProperties;
    private ConcurrentHashMap<String, OffloadHardwareInterface.ForwardedStats> mForwardedStats = new ConcurrentHashMap<>(16, 0.75f, 1);
    private HashMap<String, Long> mInterfaceQuotas = new HashMap<>();
    private long mRemainingAlertQuota = -1;
    private final Runnable mScheduledPollingTask = new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadController$Qv_pV-kEGtEPjCx4gKdmxnSK5ew
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$OffloadController();
        }
    };
    private final HashMap<String, LinkProperties> mDownstreams = new HashMap<>();
    private Set<IpPrefix> mExemptPrefixes = new HashSet();
    private Set<String> mLastLocalPrefixStrs = new HashSet();

    interface Dependencies {
        TetheringConfiguration getTetherConfig();
    }

    enum StatsType {
        STATS_PER_IFACE,
        STATS_PER_UID
    }

    private enum UpdateType {
        IF_NEEDED,
        FORCE
    }

    private static boolean isValidUdpOrTcpPort(int i) {
        return i > 0 && i < 65536;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$OffloadController() {
        updateStatsForCurrentUpstream();
        maybeSchedulePollingStats();
    }

    public OffloadController(Handler handler, OffloadHardwareInterface offloadHardwareInterface, ContentResolver contentResolver, NetworkStatsManager networkStatsManager, SharedLog sharedLog, Dependencies dependencies) {
        this.mHandler = handler;
        this.mHwInterface = offloadHardwareInterface;
        this.mContentResolver = contentResolver;
        this.mLog = sharedLog.forSubComponent(TAG);
        OffloadTetheringStatsProvider offloadTetheringStatsProvider = new OffloadTetheringStatsProvider();
        try {
            networkStatsManager.registerNetworkStatsProvider(getClass().getSimpleName(), offloadTetheringStatsProvider);
        } catch (RuntimeException e) {
            Log.wtf(TAG, "Cannot register offload stats provider: " + e);
            offloadTetheringStatsProvider = null;
        }
        this.mStatsProvider = offloadTetheringStatsProvider;
        this.mDeps = dependencies;
    }

    public boolean start() {
        if (started()) {
            return true;
        }
        if (isOffloadDisabled()) {
            this.mLog.i("tethering offload disabled");
            return false;
        }
        if (!this.mConfigInitialized) {
            boolean zInitOffloadConfig = this.mHwInterface.initOffloadConfig();
            this.mConfigInitialized = zInitOffloadConfig;
            if (!zInitOffloadConfig) {
                this.mLog.i("tethering offload config not supported");
                stop();
                return false;
            }
        }
        this.mControlInitialized = this.mHwInterface.initOffloadControl(new OffloadHardwareInterface.ControlCallback() { // from class: com.android.networkstack.tethering.OffloadController.1
            @Override // com.android.networkstack.tethering.OffloadHardwareInterface.ControlCallback
            public void onStarted() {
                if (OffloadController.this.started()) {
                    OffloadController.this.mLog.log("onStarted");
                }
            }

            @Override // com.android.networkstack.tethering.OffloadHardwareInterface.ControlCallback
            public void onStoppedError() {
                if (OffloadController.this.started()) {
                    OffloadController.this.mLog.log("onStoppedError");
                }
            }

            @Override // com.android.networkstack.tethering.OffloadHardwareInterface.ControlCallback
            public void onStoppedUnsupported() {
                if (OffloadController.this.started()) {
                    OffloadController.this.mLog.log("onStoppedUnsupported");
                    OffloadController.this.updateStatsForAllUpstreams();
                    if (OffloadController.this.mStatsProvider != null) {
                        OffloadController.this.mStatsProvider.pushTetherStats();
                    }
                }
            }

            @Override // com.android.networkstack.tethering.OffloadHardwareInterface.ControlCallback
            public void onSupportAvailable() {
                if (OffloadController.this.started()) {
                    OffloadController.this.mLog.log("onSupportAvailable");
                    OffloadController.this.updateStatsForAllUpstreams();
                    if (OffloadController.this.mStatsProvider != null) {
                        OffloadController.this.mStatsProvider.pushTetherStats();
                    }
                    OffloadController.this.computeAndPushLocalPrefixes(UpdateType.FORCE);
                    OffloadController.this.pushAllDownstreamState();
                    OffloadController.this.pushUpstreamParameters(null);
                }
            }

            @Override // com.android.networkstack.tethering.OffloadHardwareInterface.ControlCallback
            public void onStoppedLimitReached() {
                if (OffloadController.this.started()) {
                    OffloadController.this.mLog.log("onStoppedLimitReached");
                    OffloadController.this.updateStatsForCurrentUpstream();
                    if (OffloadController.this.mStatsProvider != null) {
                        OffloadController.this.mStatsProvider.pushTetherStats();
                        OffloadController.this.mStatsProvider.notifyLimitReached();
                    }
                }
            }

            @Override // com.android.networkstack.tethering.OffloadHardwareInterface.ControlCallback
            public void onNatTimeoutUpdate(int i, String str, int i2, String str2, int i3) {
                if (OffloadController.this.started()) {
                    OffloadController.this.updateNatTimeout(i, str, i2, str2, i3);
                }
            }
        });
        boolean zStarted = started();
        if (!zStarted) {
            this.mLog.i("tethering offload control not supported");
            stop();
        } else {
            this.mLog.log("tethering offload started");
            this.mNatUpdateCallbacksReceived = 0;
            this.mNatUpdateNetlinkErrors = 0;
            maybeSchedulePollingStats();
        }
        return zStarted;
    }

    public void stop() {
        boolean zStarted = started();
        updateStatsForCurrentUpstream();
        this.mUpstreamLinkProperties = null;
        this.mHwInterface.stopOffloadControl();
        this.mControlInitialized = false;
        this.mConfigInitialized = false;
        if (this.mHandler.hasCallbacks(this.mScheduledPollingTask)) {
            this.mHandler.removeCallbacks(this.mScheduledPollingTask);
        }
        if (zStarted) {
            this.mLog.log("tethering offload stopped");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean started() {
        return this.mConfigInitialized && this.mControlInitialized;
    }

    class OffloadTetheringStatsProvider extends NetworkStatsProvider {
        private NetworkStats mIfaceStats = new NetworkStats(0, 0);
        private NetworkStats mUidStats = new NetworkStats(0, 0);

        OffloadTetheringStatsProvider() {
        }

        NetworkStats getTetherStats(StatsType statsType) {
            NetworkStats networkStats = new NetworkStats(0L, 0);
            int i = statsType == StatsType.STATS_PER_UID ? -5 : -1;
            for (Map.Entry entry : OffloadController.this.mForwardedStats.entrySet()) {
                OffloadHardwareInterface.ForwardedStats forwardedStats = (OffloadHardwareInterface.ForwardedStats) entry.getValue();
                networkStats = networkStats.addEntry(new NetworkStats.Entry((String) entry.getKey(), i, 0, 0, 0, 0, 0, forwardedStats.rxBytes, 0L, forwardedStats.txBytes, 0L, 0L));
                i = i;
            }
            return networkStats;
        }

        public void onSetLimit(final String str, final long j) {
            OffloadController.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadController$OffloadTetheringStatsProvider$UDGUeXFU4FC7dNKkgzAUNVa69_I
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSetLimit$0$OffloadController$OffloadTetheringStatsProvider(str, j);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onSetLimit$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onSetLimit$0$OffloadController$OffloadTetheringStatsProvider(String str, long j) {
            if (((Long) OffloadController.this.mInterfaceQuotas.get(str)) == null && -1 == j) {
                return;
            }
            if (j == -1) {
                OffloadController.this.mInterfaceQuotas.remove(str);
            } else {
                OffloadController.this.mInterfaceQuotas.put(str, Long.valueOf(j));
            }
            OffloadController.this.maybeUpdateDataLimit(str);
        }

        public void pushTetherStats() {
            NetworkStats networkStatsSubtract = getTetherStats(StatsType.STATS_PER_IFACE).subtract(this.mIfaceStats);
            NetworkStats networkStatsSubtract2 = getTetherStats(StatsType.STATS_PER_UID).subtract(this.mUidStats);
            try {
                notifyStatsUpdated(0, networkStatsSubtract, networkStatsSubtract2);
                this.mIfaceStats = this.mIfaceStats.add(networkStatsSubtract);
                this.mUidStats = this.mUidStats.add(networkStatsSubtract2);
            } catch (RuntimeException e) {
                OffloadController.this.mLog.e("Cannot report network stats: ", e);
            }
        }

        public void onRequestStatsUpdate(int i) {
            OffloadController.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadController$OffloadTetheringStatsProvider$3Bj-FXGYSYmlC1ti_JE9amtoFg4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onRequestStatsUpdate$1$OffloadController$OffloadTetheringStatsProvider();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onRequestStatsUpdate$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onRequestStatsUpdate$1$OffloadController$OffloadTetheringStatsProvider() {
            OffloadController.this.updateStatsForCurrentUpstream();
            pushTetherStats();
        }

        public void onSetAlert(final long j) {
            OffloadController.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$OffloadController$OffloadTetheringStatsProvider$0r49FLYgDgdvY1qOKTzRir5-ZjU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSetAlert$2$OffloadController$OffloadTetheringStatsProvider(j);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onSetAlert$2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onSetAlert$2$OffloadController$OffloadTetheringStatsProvider(long j) {
            OffloadController.this.updateAlertQuota(j);
            OffloadController.this.maybeSchedulePollingStats();
        }
    }

    private String currentUpstreamInterface() {
        LinkProperties linkProperties = this.mUpstreamLinkProperties;
        if (linkProperties != null) {
            return linkProperties.getInterfaceName();
        }
        return null;
    }

    private void maybeUpdateStats(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        OffloadHardwareInterface.ForwardedStats forwardedStats = this.mHwInterface.getForwardedStats(str);
        long j = forwardedStats.rxBytes + forwardedStats.txBytes;
        OffloadHardwareInterface.ForwardedStats forwardedStats2 = this.mForwardedStats.get(str);
        if (forwardedStats2 != null) {
            forwardedStats.add(forwardedStats2);
        }
        long j2 = this.mRemainingAlertQuota;
        if (j2 > 0 && j > 0) {
            updateAlertQuota(Math.max(j2 - j, 0L));
        }
        this.mForwardedStats.put(str, forwardedStats);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAlertQuota(long j) {
        if (j < -1) {
            throw new IllegalArgumentException("invalid quota value " + j);
        }
        if (this.mRemainingAlertQuota == j) {
            return;
        }
        this.mRemainingAlertQuota = j;
        if (j == 0) {
            this.mLog.i("notifyAlertReached");
            OffloadTetheringStatsProvider offloadTetheringStatsProvider = this.mStatsProvider;
            if (offloadTetheringStatsProvider != null) {
                offloadTetheringStatsProvider.notifyAlertReached();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeSchedulePollingStats() {
        if (isPollingStatsNeeded()) {
            if (this.mHandler.hasCallbacks(this.mScheduledPollingTask)) {
                this.mHandler.removeCallbacks(this.mScheduledPollingTask);
            }
            this.mHandler.postDelayed(this.mScheduledPollingTask, this.mDeps.getTetherConfig().getOffloadPollInterval());
        }
    }

    private boolean isPollingStatsNeeded() {
        return started() && this.mRemainingAlertQuota > 0 && !TextUtils.isEmpty(currentUpstreamInterface()) && this.mDeps.getTetherConfig() != null && this.mDeps.getTetherConfig().getOffloadPollInterval() >= 5000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean maybeUpdateDataLimit(String str) {
        if (!started() || !TextUtils.equals(str, currentUpstreamInterface())) {
            return true;
        }
        Long l = this.mInterfaceQuotas.get(str);
        if (l == null) {
            l = Long.MAX_VALUE;
        }
        return this.mHwInterface.setDataLimit(str, l.longValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatsForCurrentUpstream() {
        maybeUpdateStats(currentUpstreamInterface());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatsForAllUpstreams() {
        Iterator<Map.Entry<String, OffloadHardwareInterface.ForwardedStats>> it = this.mForwardedStats.entrySet().iterator();
        while (it.hasNext()) {
            maybeUpdateStats(it.next().getKey());
        }
    }

    public void setUpstreamLinkProperties(LinkProperties linkProperties) {
        if (!started() || Objects.equals(this.mUpstreamLinkProperties, linkProperties)) {
            return;
        }
        String strCurrentUpstreamInterface = currentUpstreamInterface();
        this.mUpstreamLinkProperties = linkProperties != null ? new LinkProperties(linkProperties) : null;
        String strCurrentUpstreamInterface2 = currentUpstreamInterface();
        if (!TextUtils.isEmpty(strCurrentUpstreamInterface2)) {
            this.mForwardedStats.putIfAbsent(strCurrentUpstreamInterface2, EMPTY_STATS);
        }
        maybeSchedulePollingStats();
        computeAndPushLocalPrefixes(UpdateType.IF_NEEDED);
        pushUpstreamParameters(strCurrentUpstreamInterface);
    }

    public void setLocalPrefixes(Set<IpPrefix> set) {
        this.mExemptPrefixes = set;
        if (started()) {
            computeAndPushLocalPrefixes(UpdateType.IF_NEEDED);
        }
    }

    public void notifyDownstreamLinkProperties(LinkProperties linkProperties) {
        LinkProperties linkPropertiesPut = this.mDownstreams.put(linkProperties.getInterfaceName(), new LinkProperties(linkProperties));
        if (!Objects.equals(linkPropertiesPut, linkProperties) && started()) {
            pushDownstreamState(linkPropertiesPut, linkProperties);
        }
    }

    private void pushDownstreamState(LinkProperties linkProperties, LinkProperties linkProperties2) {
        String interfaceName = linkProperties2.getInterfaceName();
        List<RouteInfo> routes = linkProperties != null ? linkProperties.getRoutes() : Collections.EMPTY_LIST;
        List<RouteInfo> routes2 = linkProperties2.getRoutes();
        for (RouteInfo routeInfo : routes) {
            if (!shouldIgnoreDownstreamRoute(routeInfo) && !routes2.contains(routeInfo)) {
                this.mHwInterface.removeDownstreamPrefix(interfaceName, routeInfo.getDestination().toString());
            }
        }
        for (RouteInfo routeInfo2 : routes2) {
            if (!shouldIgnoreDownstreamRoute(routeInfo2) && !routes.contains(routeInfo2)) {
                this.mHwInterface.addDownstreamPrefix(interfaceName, routeInfo2.getDestination().toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pushAllDownstreamState() {
        Iterator<LinkProperties> it = this.mDownstreams.values().iterator();
        while (it.hasNext()) {
            pushDownstreamState(null, it.next());
        }
    }

    public void removeDownstreamInterface(String str) {
        LinkProperties linkPropertiesRemove = this.mDownstreams.remove(str);
        if (linkPropertiesRemove != null && started()) {
            for (RouteInfo routeInfo : linkPropertiesRemove.getRoutes()) {
                if (!shouldIgnoreDownstreamRoute(routeInfo)) {
                    this.mHwInterface.removeDownstreamPrefix(str, routeInfo.getDestination().toString());
                }
            }
        }
    }

    private boolean isOffloadDisabled() {
        return Settings.Global.getInt(this.mContentResolver, "tether_offload_disabled", this.mHwInterface.getDefaultTetherOffloadDisabled()) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean pushUpstreamParameters(String str) {
        String hostAddress;
        String strCurrentUpstreamInterface = currentUpstreamInterface();
        if (TextUtils.isEmpty(strCurrentUpstreamInterface)) {
            boolean upstreamParameters = this.mHwInterface.setUpstreamParameters("", "0.0.0.0", "0.0.0.0", null);
            maybeUpdateStats(str);
            return upstreamParameters;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator it = this.mUpstreamLinkProperties.getAddresses().iterator();
        while (true) {
            if (!it.hasNext()) {
                hostAddress = null;
                break;
            }
            InetAddress inetAddress = (InetAddress) it.next();
            if (inetAddress instanceof Inet4Address) {
                hostAddress = inetAddress.getHostAddress();
                break;
            }
        }
        String str2 = null;
        for (RouteInfo routeInfo : this.mUpstreamLinkProperties.getRoutes()) {
            if (routeInfo.hasGateway()) {
                String hostAddress2 = routeInfo.getGateway().getHostAddress();
                InetAddress address = routeInfo.getDestination().getAddress();
                if (routeInfo.isDefaultRoute() && (address instanceof Inet4Address)) {
                    str2 = hostAddress2;
                } else if (routeInfo.isDefaultRoute() && (address instanceof Inet6Address)) {
                    arrayList.add(hostAddress2);
                }
            }
        }
        boolean upstreamParameters2 = this.mHwInterface.setUpstreamParameters(strCurrentUpstreamInterface, hostAddress, str2, arrayList.isEmpty() ? null : arrayList);
        if (!upstreamParameters2) {
            return upstreamParameters2;
        }
        maybeUpdateStats(str);
        boolean zMaybeUpdateDataLimit = maybeUpdateDataLimit(strCurrentUpstreamInterface);
        if (!zMaybeUpdateDataLimit) {
            this.mLog.log("Setting data limit for " + strCurrentUpstreamInterface + " failed, disabling offload.");
            stop();
        }
        return zMaybeUpdateDataLimit;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean computeAndPushLocalPrefixes(UpdateType updateType) {
        boolean z = updateType == UpdateType.FORCE;
        Set<String> setComputeLocalPrefixStrings = computeLocalPrefixStrings(this.mExemptPrefixes, this.mUpstreamLinkProperties);
        if (!z && this.mLastLocalPrefixStrs.equals(setComputeLocalPrefixStrings)) {
            return true;
        }
        this.mLastLocalPrefixStrs = setComputeLocalPrefixStrings;
        return this.mHwInterface.setLocalPrefixes(new ArrayList<>(setComputeLocalPrefixStrings));
    }

    private static Set<String> computeLocalPrefixStrings(Set<IpPrefix> set, LinkProperties linkProperties) {
        HashSet hashSet = new HashSet(set);
        if (linkProperties != null) {
            for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
                if (linkAddress.isGlobalPreferred()) {
                    InetAddress address = linkAddress.getAddress();
                    if (address instanceof Inet6Address) {
                        hashSet.add(new IpPrefix(address, 128));
                    }
                }
            }
        }
        HashSet hashSet2 = new HashSet();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            hashSet2.add(((IpPrefix) it.next()).toString());
        }
        return hashSet2;
    }

    private static boolean shouldIgnoreDownstreamRoute(RouteInfo routeInfo) {
        IpPrefix destination = routeInfo.getDestination();
        return !new LinkAddress(destination.getAddress(), destination.getPrefixLength()).isGlobalPreferred();
    }

    public void dump(IndentingPrintWriter indentingPrintWriter) {
        if (isOffloadDisabled()) {
            indentingPrintWriter.println("Offload disabled");
            return;
        }
        boolean zStarted = started();
        StringBuilder sb = new StringBuilder();
        sb.append("Offload HALs ");
        sb.append(zStarted ? "started" : "not started");
        indentingPrintWriter.println(sb.toString());
        LinkProperties linkProperties = this.mUpstreamLinkProperties;
        indentingPrintWriter.println("Current upstream: " + (linkProperties != null ? linkProperties.getInterfaceName() : null));
        indentingPrintWriter.println("Exempt prefixes: " + this.mLastLocalPrefixStrs);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("NAT timeout update callbacks received during the ");
        sb2.append(zStarted ? "current" : "last");
        sb2.append(" offload session: ");
        sb2.append(this.mNatUpdateCallbacksReceived);
        indentingPrintWriter.println(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("NAT timeout update netlink errors during the ");
        sb3.append(zStarted ? "current" : "last");
        sb3.append(" offload session: ");
        sb3.append(this.mNatUpdateNetlinkErrors);
        indentingPrintWriter.println(sb3.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNatTimeout(int i, String str, int i2, String str2, int i3) {
        String strProtoNameFor = protoNameFor(i);
        if (strProtoNameFor == null) {
            this.mLog.e("Unknown NAT update callback protocol: " + i);
            return;
        }
        Inet4Address iPv4Address = parseIPv4Address(str);
        if (iPv4Address == null) {
            this.mLog.e("Failed to parse IPv4 address: " + str);
            return;
        }
        if (!isValidUdpOrTcpPort(i2)) {
            this.mLog.e("Invalid src port: " + i2);
            return;
        }
        Inet4Address iPv4Address2 = parseIPv4Address(str2);
        if (iPv4Address2 == null) {
            this.mLog.e("Failed to parse IPv4 address: " + str2);
            return;
        }
        if (!isValidUdpOrTcpPort(i3)) {
            this.mLog.e("Invalid dst port: " + i3);
            return;
        }
        this.mNatUpdateCallbacksReceived++;
        String str3 = String.format("%s (%s, %s) -> (%s, %s)", strProtoNameFor, str, Integer.valueOf(i2), str2, Integer.valueOf(i3));
        byte[] bArrNewIPv4TimeoutUpdateRequest = ConntrackMessage.newIPv4TimeoutUpdateRequest(i, iPv4Address, i2, iPv4Address2, i3, connectionTimeoutUpdateSecondsFor(i));
        try {
            NetlinkSocket.sendOneShotKernelMessage(OsConstants.NETLINK_NETFILTER, bArrNewIPv4TimeoutUpdateRequest);
        } catch (ErrnoException e) {
            this.mNatUpdateNetlinkErrors++;
            this.mLog.e("Error updating NAT conntrack entry >" + str3 + "<: " + e + ", msg: " + NetlinkConstants.hexify(bArrNewIPv4TimeoutUpdateRequest));
            SharedLog sharedLog = this.mLog;
            StringBuilder sb = new StringBuilder();
            sb.append("NAT timeout update callbacks received: ");
            sb.append(this.mNatUpdateCallbacksReceived);
            sharedLog.log(sb.toString());
            this.mLog.log("NAT timeout update netlink errors: " + this.mNatUpdateNetlinkErrors);
        }
    }

    private static Inet4Address parseIPv4Address(String str) {
        try {
            InetAddress numericAddress = InetAddresses.parseNumericAddress(str);
            if (numericAddress instanceof Inet4Address) {
                return (Inet4Address) numericAddress;
            }
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static String protoNameFor(int i) {
        if (i == OsConstants.IPPROTO_UDP) {
            return "UDP";
        }
        if (i == OsConstants.IPPROTO_TCP) {
            return "TCP";
        }
        return null;
    }

    private static int connectionTimeoutUpdateSecondsFor(int i) {
        return i == OsConstants.IPPROTO_TCP ? 432000 : 180;
    }
}
