package android.net.ip;

import android.app.AlarmManager;
import android.content.Context;
import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.RouteInfo;
import android.net.netlink.NdOption;
import android.net.netlink.NduseroptMessage;
import android.net.netlink.NetlinkMessage;
import android.net.netlink.StructNdOptPref64;
import android.net.util.InterfaceParams;
import android.net.util.SharedLog;
import android.os.Handler;
import android.system.OsConstants;
import com.android.networkstack.apishim.NetworkInformationShimImpl;
import com.android.networkstack.apishim.common.NetworkInformationShim;
import com.android.server.NetworkObserver;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class IpClientLinkObserver implements NetworkObserver {
    private final AlarmManager mAlarmManager;
    private final Callback mCallback;
    private final Configuration mConfig;
    private DnsServerRepository mDnsServerRepository;
    private boolean mInterfaceLinkState;
    private final String mInterfaceName;
    private final LinkProperties mLinkProperties;
    private final MyNetlinkMonitor mNetlinkMonitor;
    private final String mTag;

    public interface Callback {
        void update(boolean z);
    }

    private void maybeLog(String str, Object obj) {
    }

    private void maybeLog(String str, String str2, LinkAddress linkAddress) {
    }

    public static class Configuration {
        public final int minRdnssLifetime;

        public Configuration(int i) {
            this.minRdnssLifetime = i;
        }
    }

    public IpClientLinkObserver(Context context, Handler handler, String str, Callback callback, Configuration configuration, SharedLog sharedLog) {
        this.mInterfaceName = str;
        this.mTag = "NetlinkTracker/" + this.mInterfaceName;
        this.mCallback = callback;
        LinkProperties linkProperties = new LinkProperties();
        this.mLinkProperties = linkProperties;
        linkProperties.setInterfaceName(this.mInterfaceName);
        this.mConfig = configuration;
        this.mInterfaceLinkState = true;
        this.mDnsServerRepository = new DnsServerRepository(configuration.minRdnssLifetime);
        this.mAlarmManager = (AlarmManager) context.getSystemService("alarm");
        final MyNetlinkMonitor myNetlinkMonitor = new MyNetlinkMonitor(handler, sharedLog, this.mTag);
        this.mNetlinkMonitor = myNetlinkMonitor;
        Objects.requireNonNull(myNetlinkMonitor);
        handler.post(new Runnable() { // from class: android.net.ip.-$$Lambda$i-4tJ_qJrck-SvB5IYgoAt9_FJU
            @Override // java.lang.Runnable
            public final void run() {
                myNetlinkMonitor.start();
            }
        });
    }

    @Override // com.android.server.NetworkObserver
    public void onInterfaceRemoved(String str) {
        boolean interfaceLinkStateLocked;
        maybeLog("interfaceRemoved", str);
        if (this.mInterfaceName.equals(str)) {
            synchronized (this) {
                clearLinkProperties();
                interfaceLinkStateLocked = getInterfaceLinkStateLocked();
            }
            this.mCallback.update(interfaceLinkStateLocked);
        }
    }

    @Override // com.android.server.NetworkObserver
    public void onInterfaceLinkStateChanged(String str, boolean z) {
        if (this.mInterfaceName.equals(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(z ? " up" : " down");
            maybeLog("interfaceLinkStateChanged", sb.toString());
            synchronized (this) {
                setInterfaceLinkStateLocked(z);
            }
        }
    }

    @Override // com.android.server.NetworkObserver
    public void onInterfaceAddressUpdated(LinkAddress linkAddress, String str) {
        boolean zAddLinkAddress;
        boolean interfaceLinkStateLocked;
        if (this.mInterfaceName.equals(str)) {
            maybeLog("addressUpdated", str, linkAddress);
            synchronized (this) {
                zAddLinkAddress = this.mLinkProperties.addLinkAddress(linkAddress);
                interfaceLinkStateLocked = getInterfaceLinkStateLocked();
            }
            if (zAddLinkAddress) {
                this.mCallback.update(interfaceLinkStateLocked);
            }
        }
    }

    @Override // com.android.server.NetworkObserver
    public void onInterfaceAddressRemoved(LinkAddress linkAddress, String str) {
        boolean zRemoveLinkAddress;
        boolean interfaceLinkStateLocked;
        if (this.mInterfaceName.equals(str)) {
            maybeLog("addressRemoved", str, linkAddress);
            synchronized (this) {
                zRemoveLinkAddress = this.mLinkProperties.removeLinkAddress(linkAddress);
                interfaceLinkStateLocked = getInterfaceLinkStateLocked();
            }
            if (zRemoveLinkAddress) {
                this.mCallback.update(interfaceLinkStateLocked);
            }
        }
    }

    @Override // com.android.server.NetworkObserver
    public void onRouteUpdated(RouteInfo routeInfo) {
        boolean zAddRoute;
        boolean interfaceLinkStateLocked;
        if (this.mInterfaceName.equals(routeInfo.getInterface())) {
            maybeLog("routeUpdated", routeInfo);
            synchronized (this) {
                zAddRoute = this.mLinkProperties.addRoute(routeInfo);
                interfaceLinkStateLocked = getInterfaceLinkStateLocked();
            }
            if (zAddRoute) {
                this.mCallback.update(interfaceLinkStateLocked);
            }
        }
    }

    @Override // com.android.server.NetworkObserver
    public void onRouteRemoved(RouteInfo routeInfo) {
        boolean zRemoveRoute;
        boolean interfaceLinkStateLocked;
        if (this.mInterfaceName.equals(routeInfo.getInterface())) {
            maybeLog("routeRemoved", routeInfo);
            synchronized (this) {
                zRemoveRoute = this.mLinkProperties.removeRoute(routeInfo);
                interfaceLinkStateLocked = getInterfaceLinkStateLocked();
            }
            if (zRemoveRoute) {
                this.mCallback.update(interfaceLinkStateLocked);
            }
        }
    }

    @Override // com.android.server.NetworkObserver
    public void onInterfaceDnsServerInfo(String str, long j, String[] strArr) {
        boolean interfaceLinkStateLocked;
        if (this.mInterfaceName.equals(str)) {
            maybeLog("interfaceDnsServerInfo", Arrays.toString(strArr));
            if (this.mDnsServerRepository.addServers(j, strArr)) {
                synchronized (this) {
                    this.mDnsServerRepository.setDnsServersOn(this.mLinkProperties);
                    interfaceLinkStateLocked = getInterfaceLinkStateLocked();
                }
                this.mCallback.update(interfaceLinkStateLocked);
            }
        }
    }

    public synchronized LinkProperties getLinkProperties() {
        return new LinkProperties(this.mLinkProperties);
    }

    public synchronized void clearLinkProperties() {
        this.mDnsServerRepository = new DnsServerRepository(this.mConfig.minRdnssLifetime);
        this.mNetlinkMonitor.clearAlarms();
        this.mLinkProperties.clear();
        this.mLinkProperties.setInterfaceName(this.mInterfaceName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getInterfaceLinkStateLocked() {
        return this.mInterfaceLinkState;
    }

    private void setInterfaceLinkStateLocked(boolean z) {
        this.mInterfaceLinkState = z;
    }

    public void setInterfaceParams(InterfaceParams interfaceParams) {
        this.mNetlinkMonitor.setIfindex(interfaceParams.index);
    }

    public void clearInterfaceParams() {
        this.mNetlinkMonitor.setIfindex(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class MyNetlinkMonitor extends NetlinkMonitor {
        private final AlarmManager.OnAlarmListener mExpirePref64Alarm;
        private final Handler mHandler;
        private int mIfindex;
        private long mNat64PrefixExpiry;
        private final NetworkInformationShim mShim;

        MyNetlinkMonitor(Handler handler, SharedLog sharedLog, String str) {
            super(handler, sharedLog, str, OsConstants.NETLINK_ROUTE, 524288);
            this.mShim = NetworkInformationShimImpl.newInstance();
            this.mExpirePref64Alarm = new AlarmManager.OnAlarmListener() { // from class: android.net.ip.-$$Lambda$IpClientLinkObserver$MyNetlinkMonitor$LyK1DX1kpxITnAB5L2weu51PJ-w
                @Override // android.app.AlarmManager.OnAlarmListener
                public final void onAlarm() {
                    this.f$0.lambda$new$0$IpClientLinkObserver$MyNetlinkMonitor();
                }
            };
            this.mHandler = handler;
        }

        void setIfindex(int i) {
            this.mIfindex = i;
        }

        void clearAlarms() {
            cancelPref64Alarm();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$new$0$IpClientLinkObserver$MyNetlinkMonitor() {
            if (this.mNat64PrefixExpiry == 0) {
                return;
            }
            IpPrefix nat64Prefix = this.mShim.getNat64Prefix(IpClientLinkObserver.this.mLinkProperties);
            long j = this.mNat64PrefixExpiry;
            updatePref64(nat64Prefix, j, j);
        }

        private void cancelPref64Alarm() {
            if (this.mNat64PrefixExpiry == 0) {
                return;
            }
            this.mNat64PrefixExpiry = 0L;
            IpClientLinkObserver.this.mAlarmManager.cancel(this.mExpirePref64Alarm);
        }

        private void schedulePref64Alarm() {
            IpClientLinkObserver.this.mAlarmManager.setExact(2, this.mNat64PrefixExpiry, this.mTag + ".PREF64", this.mExpirePref64Alarm, this.mHandler);
        }

        private synchronized void updatePref64(IpPrefix ipPrefix, long j, long j2) {
            if (ipPrefix.equals(this.mShim.getNat64Prefix(IpClientLinkObserver.this.mLinkProperties))) {
                this.mNat64PrefixExpiry = j2;
                if (j2 > j) {
                    schedulePref64Alarm();
                }
            }
            if (this.mNat64PrefixExpiry > j) {
                return;
            }
            if (j2 > j) {
                this.mShim.setNat64Prefix(IpClientLinkObserver.this.mLinkProperties, ipPrefix);
                this.mNat64PrefixExpiry = j2;
                schedulePref64Alarm();
            } else {
                this.mShim.setNat64Prefix(IpClientLinkObserver.this.mLinkProperties, null);
                cancelPref64Alarm();
            }
            IpClientLinkObserver.this.mCallback.update(IpClientLinkObserver.this.getInterfaceLinkStateLocked());
        }

        private void processPref64Option(StructNdOptPref64 structNdOptPref64, long j) {
            updatePref64(structNdOptPref64.prefix, j, j + TimeUnit.SECONDS.toMillis(structNdOptPref64.lifetime));
        }

        private void processNduseroptMessage(NduseroptMessage nduseroptMessage, long j) {
            NdOption ndOption;
            if (nduseroptMessage.family == OsConstants.AF_INET6 && (ndOption = nduseroptMessage.option) != null && nduseroptMessage.ifindex == this.mIfindex && nduseroptMessage.icmp_type == -122 && ndOption.type == 38) {
                processPref64Option((StructNdOptPref64) ndOption, j);
            }
        }

        @Override // android.net.ip.NetlinkMonitor
        protected void processNetlinkMessage(NetlinkMessage netlinkMessage, long j) {
            if (netlinkMessage instanceof NduseroptMessage) {
                processNduseroptMessage((NduseroptMessage) netlinkMessage, j);
            }
        }
    }

    private static class DnsServerRepository {
        private final int mMinLifetime;
        private Set<InetAddress> mCurrentServers = new HashSet();
        private ArrayList<DnsServerEntry> mAllServers = new ArrayList<>(12);
        private HashMap<InetAddress, DnsServerEntry> mIndex = new HashMap<>(12);

        DnsServerRepository(int i) {
            this.mMinLifetime = i;
        }

        public synchronized void setDnsServersOn(LinkProperties linkProperties) {
            linkProperties.setDnsServers(this.mCurrentServers);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x001c A[Catch: all -> 0x0049, TRY_LEAVE, TryCatch #1 {, blocks: (B:5:0x0008, B:10:0x0011, B:12:0x001c, B:13:0x001e, B:14:0x0022, B:18:0x002c, B:19:0x003b, B:20:0x003e), top: B:29:0x0008 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public synchronized boolean addServers(long r8, java.lang.String[] r10) {
            /*
                r7 = this;
                monitor-enter(r7)
                r0 = 0
                int r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
                r1 = 0
                if (r0 == 0) goto L11
                int r0 = r7.mMinLifetime     // Catch: java.lang.Throwable -> L49
                long r2 = (long) r0
                int r0 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                if (r0 >= 0) goto L11
                monitor-exit(r7)
                return r1
            L11:
                long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L49
                r4 = 1000(0x3e8, double:4.94E-321)
                long r8 = r8 * r4
                long r8 = r8 + r2
                int r0 = r10.length     // Catch: java.lang.Throwable -> L49
            L1a:
                if (r1 >= r0) goto L3e
                r4 = r10[r1]     // Catch: java.lang.Throwable -> L49
                java.net.InetAddress r4 = android.net.InetAddresses.parseNumericAddress(r4)     // Catch: java.lang.IllegalArgumentException -> L3b java.lang.Throwable -> L49
                boolean r5 = r7.updateExistingEntry(r4, r8)     // Catch: java.lang.Throwable -> L49
                if (r5 != 0) goto L3b
                int r5 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                if (r5 <= 0) goto L3b
                android.net.ip.IpClientLinkObserver$DnsServerEntry r5 = new android.net.ip.IpClientLinkObserver$DnsServerEntry     // Catch: java.lang.Throwable -> L49
                r5.<init>(r4, r8)     // Catch: java.lang.Throwable -> L49
                java.util.ArrayList<android.net.ip.IpClientLinkObserver$DnsServerEntry> r6 = r7.mAllServers     // Catch: java.lang.Throwable -> L49
                r6.add(r5)     // Catch: java.lang.Throwable -> L49
                java.util.HashMap<java.net.InetAddress, android.net.ip.IpClientLinkObserver$DnsServerEntry> r6 = r7.mIndex     // Catch: java.lang.Throwable -> L49
                r6.put(r4, r5)     // Catch: java.lang.Throwable -> L49
            L3b:
                int r1 = r1 + 1
                goto L1a
            L3e:
                java.util.ArrayList<android.net.ip.IpClientLinkObserver$DnsServerEntry> r8 = r7.mAllServers     // Catch: java.lang.Throwable -> L49
                java.util.Collections.sort(r8)     // Catch: java.lang.Throwable -> L49
                boolean r8 = r7.updateCurrentServers()     // Catch: java.lang.Throwable -> L49
                monitor-exit(r7)
                return r8
            L49:
                r8 = move-exception
                monitor-exit(r7)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: android.net.ip.IpClientLinkObserver.DnsServerRepository.addServers(long, java.lang.String[]):boolean");
        }

        private synchronized boolean updateExistingEntry(InetAddress inetAddress, long j) {
            DnsServerEntry dnsServerEntry = this.mIndex.get(inetAddress);
            if (dnsServerEntry == null) {
                return false;
            }
            dnsServerEntry.expiry = j;
            return true;
        }

        private synchronized boolean updateCurrentServers() {
            boolean zAdd;
            long jCurrentTimeMillis = System.currentTimeMillis();
            zAdd = false;
            for (int size = this.mAllServers.size() - 1; size >= 0 && (size >= 12 || this.mAllServers.get(size).expiry <= jCurrentTimeMillis); size--) {
                DnsServerEntry dnsServerEntryRemove = this.mAllServers.remove(size);
                this.mIndex.remove(dnsServerEntryRemove.address);
                zAdd |= this.mCurrentServers.remove(dnsServerEntryRemove.address);
            }
            for (DnsServerEntry dnsServerEntry : this.mAllServers) {
                if (this.mCurrentServers.size() >= 3) {
                    break;
                }
                zAdd |= this.mCurrentServers.add(dnsServerEntry.address);
            }
            return zAdd;
        }
    }

    private static class DnsServerEntry implements Comparable<DnsServerEntry> {
        public final InetAddress address;
        public long expiry;

        DnsServerEntry(InetAddress inetAddress, long j) throws IllegalArgumentException {
            this.address = inetAddress;
            this.expiry = j;
        }

        @Override // java.lang.Comparable
        public int compareTo(DnsServerEntry dnsServerEntry) {
            return Long.compare(dnsServerEntry.expiry, this.expiry);
        }
    }
}
