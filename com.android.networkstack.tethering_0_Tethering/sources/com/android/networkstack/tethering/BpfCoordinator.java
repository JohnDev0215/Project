package com.android.networkstack.tethering;

import android.app.usage.NetworkStatsManager;
import android.net.INetd;
import android.net.MacAddress;
import android.net.NetworkStats;
import android.net.TetherOffloadRuleParcel;
import android.net.TetherStatsParcel;
import android.net.ip.IpServer;
import android.net.netstats.provider.NetworkStatsProvider;
import android.net.util.SharedLog;
import android.net.util.TetheringUtils;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ServiceSpecificException;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.android.networkstack.tethering.util.IndentingPrintWriter;
import java.net.Inet6Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class BpfCoordinator {
    private static final String TAG = "BpfCoordinator";
    private final Dependencies mDeps;
    private final Handler mHandler;
    private final SharedLog mLog;
    private final INetd mNetd;
    private final BpfTetherStatsProvider mStatsProvider;
    private boolean mPollingStarted = false;
    private long mRemainingAlertQuota = -1;
    private final SparseArray<TetheringUtils.ForwardedStats> mStats = new SparseArray<>();
    private final HashMap<String, Long> mInterfaceQuotas = new HashMap<>();
    private final SparseArray<String> mInterfaceNames = new SparseArray<>();
    private final HashMap<IpServer, LinkedHashMap<Inet6Address, Ipv6ForwardingRule>> mIpv6ForwardingRules = new LinkedHashMap();
    private final Runnable mScheduledPollingTask = new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$BpfCoordinator$kB2QPENJUq4QZhh72tMIjAQu5PQ
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$BpfCoordinator();
        }
    };
    private final boolean mIsBpfEnabled = isBpfEnabled();

    public static abstract class Dependencies {
        public abstract Handler getHandler();

        public abstract INetd getNetd();

        public abstract NetworkStatsManager getNetworkStatsManager();

        public abstract SharedLog getSharedLog();

        public abstract TetheringConfiguration getTetherConfig();
    }

    enum StatsType {
        STATS_PER_IFACE,
        STATS_PER_UID
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$BpfCoordinator() {
        updateForwardedStatsFromNetd();
        maybeSchedulePollingStats();
    }

    public BpfCoordinator(Dependencies dependencies) {
        this.mDeps = dependencies;
        this.mHandler = dependencies.getHandler();
        this.mNetd = this.mDeps.getNetd();
        this.mLog = this.mDeps.getSharedLog().forSubComponent(TAG);
        BpfTetherStatsProvider bpfTetherStatsProvider = new BpfTetherStatsProvider();
        try {
            this.mDeps.getNetworkStatsManager().registerNetworkStatsProvider(getClass().getSimpleName(), bpfTetherStatsProvider);
        } catch (RuntimeException e) {
            Log.wtf(TAG, "Cannot register offload stats provider: " + e);
            bpfTetherStatsProvider = null;
        }
        this.mStatsProvider = bpfTetherStatsProvider;
    }

    public void startPolling() {
        if (this.mPollingStarted) {
            return;
        }
        if (!this.mIsBpfEnabled) {
            this.mLog.i("Offload disabled");
            return;
        }
        this.mPollingStarted = true;
        maybeSchedulePollingStats();
        this.mLog.i("Polling started");
    }

    public void stopPolling() {
        if (this.mPollingStarted) {
            if (this.mHandler.hasCallbacks(this.mScheduledPollingTask)) {
                this.mHandler.removeCallbacks(this.mScheduledPollingTask);
            }
            updateForwardedStatsFromNetd();
            this.mPollingStarted = false;
            this.mLog.i("Polling stopped");
        }
    }

    public void tetherOffloadRuleAdd(IpServer ipServer, Ipv6ForwardingRule ipv6ForwardingRule) {
        if (this.mIsBpfEnabled) {
            try {
                this.mNetd.tetherOffloadRuleAdd(ipv6ForwardingRule.toTetherOffloadRuleParcel());
                if (!this.mIpv6ForwardingRules.containsKey(ipServer)) {
                    this.mIpv6ForwardingRules.put(ipServer, new LinkedHashMap<>());
                }
                LinkedHashMap<Inet6Address, Ipv6ForwardingRule> linkedHashMap = this.mIpv6ForwardingRules.get(ipServer);
                int i = ipv6ForwardingRule.upstreamIfindex;
                if (!isAnyRuleOnUpstream(i) && !updateDataLimit(i)) {
                    String str = this.mInterfaceNames.get(i);
                    this.mLog.e("Setting data limit for " + str + " failed.");
                }
                linkedHashMap.put(ipv6ForwardingRule.address, ipv6ForwardingRule);
            } catch (RemoteException | ServiceSpecificException e) {
                this.mLog.e("Could not add IPv6 forwarding rule: ", e);
            }
        }
    }

    public void tetherOffloadRuleRemove(IpServer ipServer, Ipv6ForwardingRule ipv6ForwardingRule) {
        if (this.mIsBpfEnabled) {
            try {
                this.mNetd.tetherOffloadRuleRemove(ipv6ForwardingRule.toTetherOffloadRuleParcel());
                LinkedHashMap<Inet6Address, Ipv6ForwardingRule> linkedHashMap = this.mIpv6ForwardingRules.get(ipServer);
                if (linkedHashMap == null || linkedHashMap.remove(ipv6ForwardingRule.address) == null) {
                    return;
                }
                if (linkedHashMap.isEmpty()) {
                    this.mIpv6ForwardingRules.remove(ipServer);
                }
                int i = ipv6ForwardingRule.upstreamIfindex;
                if (isAnyRuleOnUpstream(i)) {
                    return;
                }
                try {
                    updateQuotaAndStatsFromSnapshot(new TetherStatsParcel[]{this.mNetd.tetherOffloadGetAndClearStats(i)});
                    this.mStats.remove(i);
                } catch (RemoteException | ServiceSpecificException e) {
                    Log.wtf(TAG, "Exception when cleanup tether stats for upstream index " + i + ": ", e);
                }
            } catch (RemoteException | ServiceSpecificException e2) {
                this.mLog.e("Could not remove IPv6 forwarding rule: ", e2);
            }
        }
    }

    public void tetherOffloadRuleClear(IpServer ipServer) {
        LinkedHashMap<Inet6Address, Ipv6ForwardingRule> linkedHashMap;
        if (this.mIsBpfEnabled && (linkedHashMap = this.mIpv6ForwardingRules.get(ipServer)) != null) {
            Iterator it = new ArrayList(linkedHashMap.values()).iterator();
            while (it.hasNext()) {
                tetherOffloadRuleRemove(ipServer, (Ipv6ForwardingRule) it.next());
            }
        }
    }

    public void tetherOffloadRuleUpdate(IpServer ipServer, int i) {
        LinkedHashMap<Inet6Address, Ipv6ForwardingRule> linkedHashMap;
        if (this.mIsBpfEnabled && (linkedHashMap = this.mIpv6ForwardingRules.get(ipServer)) != null) {
            for (Ipv6ForwardingRule ipv6ForwardingRule : new ArrayList(linkedHashMap.values())) {
                tetherOffloadRuleRemove(ipServer, ipv6ForwardingRule);
                tetherOffloadRuleAdd(ipServer, ipv6ForwardingRule.onNewUpstream(i));
            }
        }
    }

    public void addUpstreamNameToLookupTable(int i, String str) {
        if (!this.mIsBpfEnabled || i == 0 || TextUtils.isEmpty(str)) {
            return;
        }
        String str2 = this.mInterfaceNames.get(i);
        if (str2 == null) {
            this.mInterfaceNames.put(i, str);
            return;
        }
        if (TextUtils.equals(str2, str)) {
            return;
        }
        Log.wtf(TAG, "The upstream interface name " + str + " is different from the existing interface name " + str2 + " for index " + i);
    }

    public void dump(final IndentingPrintWriter indentingPrintWriter) {
        final ConditionVariable conditionVariable = new ConditionVariable();
        this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$BpfCoordinator$ATdNqQGJKq7Ugha6pTBneaYcpzY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dump$1$BpfCoordinator(indentingPrintWriter, conditionVariable);
            }
        });
        if (conditionVariable.block(10000L)) {
            return;
        }
        indentingPrintWriter.println("... dump timed-out after 10000ms");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$dump$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$dump$1$BpfCoordinator(IndentingPrintWriter indentingPrintWriter, ConditionVariable conditionVariable) {
        indentingPrintWriter.println("mIsBpfEnabled: " + this.mIsBpfEnabled);
        StringBuilder sb = new StringBuilder();
        sb.append("Polling ");
        sb.append(this.mPollingStarted ? "started" : "not started");
        indentingPrintWriter.println(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Stats provider ");
        sb2.append(this.mStatsProvider != null ? "registered" : "not registered");
        indentingPrintWriter.println(sb2.toString());
        indentingPrintWriter.println("Upstream quota: " + this.mInterfaceQuotas.toString());
        indentingPrintWriter.println("Polling interval: " + getPollingInterval() + " ms");
        indentingPrintWriter.println("Forwarding stats:");
        indentingPrintWriter.increaseIndent();
        if (this.mStats.size() == 0) {
            indentingPrintWriter.println("<empty>");
        } else {
            dumpStats(indentingPrintWriter);
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Forwarding rules:");
        indentingPrintWriter.increaseIndent();
        if (this.mIpv6ForwardingRules.size() == 0) {
            indentingPrintWriter.println("<empty>");
        } else {
            dumpIpv6ForwardingRules(indentingPrintWriter);
        }
        indentingPrintWriter.decreaseIndent();
        conditionVariable.open();
    }

    private void dumpStats(IndentingPrintWriter indentingPrintWriter) {
        for (int i = 0; i < this.mStats.size(); i++) {
            int iKeyAt = this.mStats.keyAt(i);
            indentingPrintWriter.println(String.format("%d(%s) - %s", Integer.valueOf(iKeyAt), this.mInterfaceNames.get(iKeyAt), this.mStats.get(iKeyAt).toString()));
        }
    }

    private void dumpIpv6ForwardingRules(IndentingPrintWriter indentingPrintWriter) {
        for (Map.Entry<IpServer, LinkedHashMap<Inet6Address, Ipv6ForwardingRule>> entry : this.mIpv6ForwardingRules.entrySet()) {
            String strInterfaceName = entry.getKey().interfaceName();
            indentingPrintWriter.println("[" + strInterfaceName + "]: iif(iface) oif(iface) v6addr srcmac dstmac");
            indentingPrintWriter.increaseIndent();
            for (Ipv6ForwardingRule ipv6ForwardingRule : entry.getValue().values()) {
                int i = ipv6ForwardingRule.upstreamIfindex;
                indentingPrintWriter.println(String.format("%d(%s) %d(%s) %s %s %s", Integer.valueOf(i), this.mInterfaceNames.get(i), Integer.valueOf(ipv6ForwardingRule.downstreamIfindex), strInterfaceName, ipv6ForwardingRule.address, ipv6ForwardingRule.srcMac, ipv6ForwardingRule.dstMac));
            }
            indentingPrintWriter.decreaseIndent();
        }
    }

    public static class Ipv6ForwardingRule {
        public final Inet6Address address;
        public final int downstreamIfindex;
        public final MacAddress dstMac;
        public final MacAddress srcMac;
        public final int upstreamIfindex;

        public Ipv6ForwardingRule(int i, int i2, Inet6Address inet6Address, MacAddress macAddress, MacAddress macAddress2) {
            this.upstreamIfindex = i;
            this.downstreamIfindex = i2;
            this.address = inet6Address;
            this.srcMac = macAddress;
            this.dstMac = macAddress2;
        }

        public Ipv6ForwardingRule onNewUpstream(int i) {
            return new Ipv6ForwardingRule(i, this.downstreamIfindex, this.address, this.srcMac, this.dstMac);
        }

        public TetherOffloadRuleParcel toTetherOffloadRuleParcel() {
            TetherOffloadRuleParcel tetherOffloadRuleParcel = new TetherOffloadRuleParcel();
            tetherOffloadRuleParcel.inputInterfaceIndex = this.upstreamIfindex;
            tetherOffloadRuleParcel.outputInterfaceIndex = this.downstreamIfindex;
            tetherOffloadRuleParcel.destination = this.address.getAddress();
            tetherOffloadRuleParcel.prefixLength = 128;
            tetherOffloadRuleParcel.srcL2Address = this.srcMac.toByteArray();
            tetherOffloadRuleParcel.dstL2Address = this.dstMac.toByteArray();
            return tetherOffloadRuleParcel;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Ipv6ForwardingRule)) {
                return false;
            }
            Ipv6ForwardingRule ipv6ForwardingRule = (Ipv6ForwardingRule) obj;
            return this.upstreamIfindex == ipv6ForwardingRule.upstreamIfindex && this.downstreamIfindex == ipv6ForwardingRule.downstreamIfindex && Objects.equals(this.address, ipv6ForwardingRule.address) && Objects.equals(this.srcMac, ipv6ForwardingRule.srcMac) && Objects.equals(this.dstMac, ipv6ForwardingRule.dstMac);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.upstreamIfindex), Integer.valueOf(this.downstreamIfindex), this.address, this.srcMac, this.dstMac);
        }
    }

    class BpfTetherStatsProvider extends NetworkStatsProvider {
        private NetworkStats mIfaceStats = new NetworkStats(0, 0);
        private NetworkStats mUidStats = new NetworkStats(0, 0);

        BpfTetherStatsProvider() {
        }

        public void onRequestStatsUpdate(int i) {
            BpfCoordinator.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$BpfCoordinator$BpfTetherStatsProvider$_UtfGvXQ1xItI8NYvwMxLNY3wss
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onRequestStatsUpdate$0$BpfCoordinator$BpfTetherStatsProvider();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onSetAlert$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onSetAlert$1$BpfCoordinator$BpfTetherStatsProvider(long j) {
            BpfCoordinator.this.updateAlertQuota(j);
        }

        public void onSetAlert(final long j) {
            BpfCoordinator.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$BpfCoordinator$BpfTetherStatsProvider$6SN86cQkRaY6MWWaNUeVXD5wgQU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onSetAlert$1$BpfCoordinator$BpfTetherStatsProvider(j);
                }
            });
        }

        public void onSetLimit(final String str, final long j) {
            if (j >= -1) {
                BpfCoordinator.this.mHandler.post(new Runnable() { // from class: com.android.networkstack.tethering.-$$Lambda$BpfCoordinator$BpfTetherStatsProvider$b3CvYUUHEPCmp_P9tMhdB981VPo
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onSetLimit$2$BpfCoordinator$BpfTetherStatsProvider(str, j);
                    }
                });
                return;
            }
            throw new IllegalArgumentException("invalid quota value " + j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onSetLimit$2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onSetLimit$2$BpfCoordinator$BpfTetherStatsProvider(String str, long j) {
            if (((Long) BpfCoordinator.this.mInterfaceQuotas.get(str)) == null && -1 == j) {
                return;
            }
            if (j == -1) {
                BpfCoordinator.this.mInterfaceQuotas.remove(str);
            } else {
                BpfCoordinator.this.mInterfaceQuotas.put(str, Long.valueOf(j));
            }
            BpfCoordinator.this.maybeUpdateDataLimit(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX INFO: renamed from: pushTetherStats, reason: merged with bridge method [inline-methods] */
        public void lambda$onRequestStatsUpdate$0$BpfCoordinator$BpfTetherStatsProvider() {
            try {
                notifyStatsUpdated(0, this.mIfaceStats, this.mUidStats);
                this.mIfaceStats = new NetworkStats(0L, 0);
                this.mUidStats = new NetworkStats(0L, 0);
            } catch (RuntimeException e) {
                BpfCoordinator.this.mLog.e("Cannot report network stats: ", e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void accumulateDiff(NetworkStats networkStats, NetworkStats networkStats2) {
            this.mIfaceStats = this.mIfaceStats.add(networkStats);
            this.mUidStats = this.mUidStats.add(networkStats2);
        }
    }

    private boolean isBpfEnabled() {
        TetheringConfiguration tetherConfig = this.mDeps.getTetherConfig();
        if (tetherConfig != null) {
            return tetherConfig.isBpfOffloadEnabled();
        }
        return true;
    }

    private int getInterfaceIndexFromRules(String str) {
        Iterator<LinkedHashMap<Inet6Address, Ipv6ForwardingRule>> it = this.mIpv6ForwardingRules.values().iterator();
        while (it.hasNext()) {
            Iterator<Ipv6ForwardingRule> it2 = it.next().values().iterator();
            while (it2.hasNext()) {
                int i = it2.next().upstreamIfindex;
                if (TextUtils.equals(str, this.mInterfaceNames.get(i))) {
                    return i;
                }
            }
        }
        return 0;
    }

    private long getQuotaBytes(String str) {
        Long l = this.mInterfaceQuotas.get(str);
        if (l != null) {
            return l.longValue();
        }
        return -1L;
    }

    private boolean sendDataLimitToNetd(int i, long j) {
        if (i == 0) {
            Log.wtf(TAG, "Invalid interface index.");
            return false;
        }
        try {
            this.mNetd.tetherOffloadSetInterfaceQuota(i, j);
            return true;
        } catch (RemoteException | ServiceSpecificException e) {
            this.mLog.e("Exception when updating quota " + j + ": ", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeUpdateDataLimit(String str) {
        int interfaceIndexFromRules = getInterfaceIndexFromRules(str);
        if (interfaceIndexFromRules == 0) {
            return;
        }
        sendDataLimitToNetd(interfaceIndexFromRules, getQuotaBytes(str));
    }

    private boolean updateDataLimit(int i) {
        String str = this.mInterfaceNames.get(i);
        if (str == null) {
            this.mLog.e("Fail to get the interface name for index " + i);
            return false;
        }
        return sendDataLimitToNetd(i, getQuotaBytes(str));
    }

    private boolean isAnyRuleOnUpstream(int i) {
        Iterator<LinkedHashMap<Inet6Address, Ipv6ForwardingRule>> it = this.mIpv6ForwardingRules.values().iterator();
        while (it.hasNext()) {
            Iterator<Ipv6ForwardingRule> it2 = it.next().values().iterator();
            while (it2.hasNext()) {
                if (i == it2.next().upstreamIfindex) {
                    return true;
                }
            }
        }
        return false;
    }

    private NetworkStats buildNetworkStats(StatsType statsType, int i, TetheringUtils.ForwardedStats forwardedStats) {
        NetworkStats networkStats = new NetworkStats(0L, 0);
        String str = this.mInterfaceNames.get(i);
        if (str == null) {
            return networkStats;
        }
        return networkStats.addEntry(new NetworkStats.Entry(str, statsType == StatsType.STATS_PER_UID ? -5 : -1, 0, 0, 0, 0, 0, forwardedStats.rxBytes, forwardedStats.rxPackets, forwardedStats.txBytes, forwardedStats.txPackets, 0L));
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
            this.mLog.i("onAlertReached");
            BpfTetherStatsProvider bpfTetherStatsProvider = this.mStatsProvider;
            if (bpfTetherStatsProvider != null) {
                bpfTetherStatsProvider.notifyAlertReached();
            }
        }
    }

    private void updateQuotaAndStatsFromSnapshot(TetherStatsParcel[] tetherStatsParcelArr) {
        long j = 0;
        for (TetherStatsParcel tetherStatsParcel : tetherStatsParcelArr) {
            Integer numValueOf = Integer.valueOf(tetherStatsParcel.ifIndex);
            TetheringUtils.ForwardedStats forwardedStats = new TetheringUtils.ForwardedStats(tetherStatsParcel);
            TetheringUtils.ForwardedStats forwardedStats2 = this.mStats.get(numValueOf.intValue());
            TetheringUtils.ForwardedStats forwardedStatsSubtract = forwardedStats2 != null ? forwardedStats.subtract(forwardedStats2) : forwardedStats;
            j += forwardedStatsSubtract.rxBytes + forwardedStatsSubtract.txBytes;
            this.mStats.put(numValueOf.intValue(), forwardedStats);
            BpfTetherStatsProvider bpfTetherStatsProvider = this.mStatsProvider;
            if (bpfTetherStatsProvider != null) {
                try {
                    bpfTetherStatsProvider.accumulateDiff(buildNetworkStats(StatsType.STATS_PER_IFACE, numValueOf.intValue(), forwardedStatsSubtract), buildNetworkStats(StatsType.STATS_PER_UID, numValueOf.intValue(), forwardedStatsSubtract));
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.wtf(TAG, "Fail to update the accumulated stats delta for interface index " + numValueOf + " : ", e);
                }
            }
        }
        long j2 = this.mRemainingAlertQuota;
        if (j2 <= 0 || j <= 0) {
            return;
        }
        updateAlertQuota(Math.max(j2 - j, 0L));
    }

    private void updateForwardedStatsFromNetd() {
        try {
            updateQuotaAndStatsFromSnapshot(this.mNetd.tetherOffloadGetStats());
        } catch (RemoteException | ServiceSpecificException e) {
            this.mLog.e("Problem fetching tethering stats: ", e);
        }
    }

    int getPollingInterval() {
        TetheringConfiguration tetherConfig = this.mDeps.getTetherConfig();
        return Math.max(5000, tetherConfig != null ? tetherConfig.getOffloadPollInterval() : 0);
    }

    private void maybeSchedulePollingStats() {
        if (this.mPollingStarted) {
            if (this.mHandler.hasCallbacks(this.mScheduledPollingTask)) {
                this.mHandler.removeCallbacks(this.mScheduledPollingTask);
            }
            this.mHandler.postDelayed(this.mScheduledPollingTask, getPollingInterval());
        }
    }

    final HashMap<IpServer, LinkedHashMap<Inet6Address, Ipv6ForwardingRule>> getForwardingRulesForTesting() {
        return this.mIpv6ForwardingRules;
    }

    final SparseArray<String> getInterfaceNamesForTesting() {
        return this.mInterfaceNames;
    }
}
