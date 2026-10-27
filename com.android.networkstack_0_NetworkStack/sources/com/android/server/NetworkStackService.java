package com.android.server;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.IIpMemoryStore;
import android.net.IIpMemoryStoreCallbacks;
import android.net.INetd;
import android.net.INetworkMonitor;
import android.net.INetworkMonitorCallbacks;
import android.net.INetworkStackConnector;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.PrivateDnsConfigParcel;
import android.net.dhcp.DhcpServer;
import android.net.dhcp.DhcpServingParams;
import android.net.dhcp.DhcpServingParamsParcel;
import android.net.dhcp.IDhcpServerCallbacks;
import android.net.ip.IIpClientCallbacks;
import android.net.ip.IpClient;
import android.net.networkstack.util.IndentingPrintWriter;
import android.net.shared.PrivateDnsConfig;
import android.net.util.SharedLog;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.ArraySet;
import com.android.networkstack.NetworkStackNotifier;
import com.android.networkstack.apishim.common.ShimUtils;
import com.android.server.NetworkStackService;
import com.android.server.connectivity.NetworkMonitor;
import com.android.server.connectivity.ipmemorystore.IpMemoryStoreService;
import com.android.server.util.PermissionUtil;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes.dex */
public class NetworkStackService extends Service {
    private static final String TAG = NetworkStackService.class.getSimpleName();
    private static NetworkStackConnector sConnector;

    public interface NetworkStackServiceManager {
        IIpMemoryStore getIpMemoryStoreService();

        NetworkStackNotifier getNotifier();
    }

    public static synchronized IBinder makeConnector(Context context) {
        if (sConnector == null) {
            sConnector = new NetworkStackConnector(context);
        }
        return sConnector;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return makeConnector(this);
    }

    public static class PermissionChecker {
        public void enforceNetworkStackCallingPermission() {
            PermissionUtil.enforceNetworkStackCallingPermission();
        }
    }

    public static class Dependencies {
        public IpMemoryStoreService makeIpMemoryStoreService(Context context) {
            return new IpMemoryStoreService(context);
        }

        public NetworkStackNotifier makeNotifier(Context context, Looper looper) {
            return new NetworkStackNotifier(context, looper);
        }

        public DhcpServer makeDhcpServer(Context context, String str, DhcpServingParams dhcpServingParams, SharedLog sharedLog) {
            return new DhcpServer(context, str, dhcpServingParams, sharedLog);
        }

        public NetworkMonitor makeNetworkMonitor(Context context, INetworkMonitorCallbacks iNetworkMonitorCallbacks, Network network, SharedLog sharedLog, NetworkStackServiceManager networkStackServiceManager) {
            return new NetworkMonitor(context, iNetworkMonitorCallbacks, network, sharedLog, networkStackServiceManager);
        }

        public IpClient makeIpClient(Context context, String str, IIpClientCallbacks iIpClientCallbacks, NetworkObserverRegistry networkObserverRegistry, NetworkStackServiceManager networkStackServiceManager) {
            return new IpClient(context, str, iIpClientCallbacks, networkObserverRegistry, networkStackServiceManager);
        }
    }

    public static class NetworkStackConnector extends INetworkStackConnector.Stub implements NetworkStackServiceManager {
        private final ArraySet<AidlVersion> mAidlVersions;
        private final Context mContext;
        private final Dependencies mDeps;
        private final ArrayList<WeakReference<IpClient>> mIpClients;
        private final IpMemoryStoreService mIpMemoryStoreService;
        private final SharedLog mLog;
        private final INetd mNetd;
        private final NetworkStackNotifier mNotifier;
        private final NetworkObserverRegistry mObserverRegistry;
        private final PermissionChecker mPermChecker;
        private final ArrayDeque<SharedLog> mValidationLogs;

        @Override // android.net.INetworkStackConnector
        public String getInterfaceHash() {
            return "02cd6fd07d5c04eca0c35a350f7b0be576242883";
        }

        @Override // android.net.INetworkStackConnector
        public int getInterfaceVersion() {
            return 7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        static final class AidlVersion implements Comparable<AidlVersion> {
            private static final Comparator<AidlVersion> COMPARATOR = Comparator.comparing(new Function() { // from class: com.android.server.-$$Lambda$NetworkStackService$NetworkStackConnector$AidlVersion$ub-SNILJYjHmAqYD8XOQlVggbZQ
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((NetworkStackService.NetworkStackConnector.AidlVersion) obj).mKey;
                }
            }).thenComparingInt(new ToIntFunction() { // from class: com.android.server.-$$Lambda$NetworkStackService$NetworkStackConnector$AidlVersion$vgsY2grI-PUt_eZR8Oh0whFbaw4
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((NetworkStackService.NetworkStackConnector.AidlVersion) obj).mVersion;
                }
            }).thenComparing(new Function() { // from class: com.android.server.-$$Lambda$NetworkStackService$NetworkStackConnector$AidlVersion$ihIrhx_2kocXR9vFwJBbg5Q5e3c
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((NetworkStackService.NetworkStackConnector.AidlVersion) obj).mHash;
                }
            }, new Comparator() { // from class: com.android.server.-$$Lambda$TEfSBt3hRUlBSSARfPEHsJesTtE
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ((String) obj).compareTo((String) obj2);
                }
            });
            final String mHash;
            final String mKey;
            final int mVersion;

            AidlVersion(String str, int i, String str2) {
                this.mKey = str;
                this.mVersion = i;
                this.mHash = str2;
            }

            public int hashCode() {
                return Objects.hash(Integer.valueOf(this.mVersion), this.mHash);
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof AidlVersion)) {
                    return false;
                }
                AidlVersion aidlVersion = (AidlVersion) obj;
                return Objects.equals(this.mKey, aidlVersion.mKey) && Objects.equals(Integer.valueOf(this.mVersion), Integer.valueOf(aidlVersion.mVersion)) && Objects.equals(this.mHash, aidlVersion.mHash);
            }

            public String toString() {
                return String.format("%s:%s:%s", this.mKey, Integer.valueOf(this.mVersion), this.mHash);
            }

            @Override // java.lang.Comparable
            public int compareTo(AidlVersion aidlVersion) {
                return COMPARATOR.compare(this, aidlVersion);
            }
        }

        private SharedLog addValidationLogs(Network network, String str) {
            SharedLog sharedLog = new SharedLog(20, network + " - " + str);
            synchronized (this.mValidationLogs) {
                while (this.mValidationLogs.size() >= 10) {
                    this.mValidationLogs.removeLast();
                }
                this.mValidationLogs.addFirst(sharedLog);
            }
            return sharedLog;
        }

        NetworkStackConnector(Context context) {
            this(context, new PermissionChecker(), new Dependencies());
        }

        public NetworkStackConnector(Context context, PermissionChecker permissionChecker, Dependencies dependencies) {
            int interfaceVersion;
            String interfaceHash;
            this.mIpClients = new ArrayList<>();
            this.mValidationLogs = new ArrayDeque<>(10);
            this.mAidlVersions = new ArraySet<>();
            this.mLog = new SharedLog(NetworkStackService.TAG);
            this.mContext = context;
            this.mPermChecker = permissionChecker;
            this.mDeps = dependencies;
            this.mNetd = INetd.Stub.asInterface((IBinder) context.getSystemService("netd"));
            this.mObserverRegistry = new NetworkObserverRegistry();
            this.mIpMemoryStoreService = this.mDeps.makeIpMemoryStoreService(context);
            if (ShimUtils.isReleaseOrDevelopmentApiAbove(29)) {
                HandlerThread handlerThread = new HandlerThread(NetworkStackNotifier.class.getSimpleName());
                handlerThread.start();
                this.mNotifier = this.mDeps.makeNotifier(context, handlerThread.getLooper());
            } else {
                this.mNotifier = null;
            }
            try {
                interfaceVersion = this.mNetd.getInterfaceVersion();
                interfaceHash = this.mNetd.getInterfaceHash();
            } catch (RemoteException e) {
                this.mLog.e("Error obtaining INetd version", e);
                interfaceVersion = -1;
                interfaceHash = "unknown";
            }
            updateNetdAidlVersion(interfaceVersion, interfaceHash);
            try {
                this.mObserverRegistry.register(this.mNetd);
            } catch (RemoteException e2) {
                this.mLog.e("Error registering observer on Netd", e2);
            }
        }

        private void updateNetdAidlVersion(int i, String str) {
            synchronized (this.mAidlVersions) {
                this.mAidlVersions.add(new AidlVersion("netd", i, str));
            }
        }

        private void updateNetworkStackAidlVersion(int i, String str) {
            synchronized (this.mAidlVersions) {
                this.mAidlVersions.add(new AidlVersion("networkstack", i, str));
            }
        }

        private void updateIpMemoryStoreAidlVersion(int i, String str) {
            synchronized (this.mAidlVersions) {
                this.mAidlVersions.add(new AidlVersion("ipmemorystore", i, str));
            }
        }

        @Override // android.net.INetworkStackConnector
        public void makeDhcpServer(String str, DhcpServingParamsParcel dhcpServingParamsParcel, IDhcpServerCallbacks iDhcpServerCallbacks) throws RemoteException {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            updateNetworkStackAidlVersion(iDhcpServerCallbacks.getInterfaceVersion(), iDhcpServerCallbacks.getInterfaceHash());
            try {
                iDhcpServerCallbacks.onDhcpServerCreated(1, this.mDeps.makeDhcpServer(this.mContext, str, DhcpServingParams.fromParcelableObject(dhcpServingParamsParcel), this.mLog.forSubComponent(str + ".DHCP")).makeConnector());
            } catch (DhcpServingParams.InvalidParameterException e) {
                this.mLog.e("Invalid DhcpServingParams", e);
                iDhcpServerCallbacks.onDhcpServerCreated(2, null);
            } catch (Exception e2) {
                this.mLog.e("Unknown error starting DhcpServer", e2);
                iDhcpServerCallbacks.onDhcpServerCreated(3, null);
            }
        }

        @Override // android.net.INetworkStackConnector
        public void makeNetworkMonitor(Network network, String str, INetworkMonitorCallbacks iNetworkMonitorCallbacks) throws RemoteException {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            updateNetworkStackAidlVersion(iNetworkMonitorCallbacks.getInterfaceVersion(), iNetworkMonitorCallbacks.getInterfaceHash());
            iNetworkMonitorCallbacks.onNetworkMonitorCreated(new NetworkMonitorConnector(this.mDeps.makeNetworkMonitor(this.mContext, iNetworkMonitorCallbacks, network, addValidationLogs(network, str), this), this.mPermChecker));
        }

        @Override // android.net.INetworkStackConnector
        public void makeIpClient(String str, IIpClientCallbacks iIpClientCallbacks) throws RemoteException {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            updateNetworkStackAidlVersion(iIpClientCallbacks.getInterfaceVersion(), iIpClientCallbacks.getInterfaceHash());
            IpClient ipClientMakeIpClient = this.mDeps.makeIpClient(this.mContext, str, iIpClientCallbacks, this.mObserverRegistry, this);
            synchronized (this.mIpClients) {
                Iterator<WeakReference<IpClient>> it = this.mIpClients.iterator();
                while (it.hasNext()) {
                    if (it.next().get() == null) {
                        it.remove();
                    }
                }
                this.mIpClients.add(new WeakReference<>(ipClientMakeIpClient));
            }
            iIpClientCallbacks.onIpClientCreated(ipClientMakeIpClient.makeConnector());
        }

        @Override // com.android.server.NetworkStackService.NetworkStackServiceManager
        public IIpMemoryStore getIpMemoryStoreService() {
            return this.mIpMemoryStoreService;
        }

        @Override // com.android.server.NetworkStackService.NetworkStackServiceManager
        public NetworkStackNotifier getNotifier() {
            return this.mNotifier;
        }

        @Override // android.net.INetworkStackConnector
        public void fetchIpMemoryStore(IIpMemoryStoreCallbacks iIpMemoryStoreCallbacks) throws RemoteException {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            updateIpMemoryStoreAidlVersion(iIpMemoryStoreCallbacks.getInterfaceVersion(), iIpMemoryStoreCallbacks.getInterfaceHash());
            iIpMemoryStoreCallbacks.onIpMemoryStoreFetched(this.mIpMemoryStoreService);
        }

        @Override // android.os.Binder
        protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            PermissionUtil.checkDumpPermission();
            IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
            indentingPrintWriter.println("NetworkStack version:");
            dumpVersion(indentingPrintWriter);
            indentingPrintWriter.println();
            if (strArr == null || strArr.length < 1 || !"version".equals(strArr[0])) {
                indentingPrintWriter.println("NetworkStack logs:");
                this.mLog.dump(fileDescriptor, indentingPrintWriter, strArr);
                indentingPrintWriter.println();
                indentingPrintWriter.println("Recently active IpClient logs:");
                ArrayList<IpClient> arrayList = new ArrayList();
                HashSet hashSet = new HashSet();
                synchronized (this.mIpClients) {
                    Iterator<WeakReference<IpClient>> it = this.mIpClients.iterator();
                    while (it.hasNext()) {
                        IpClient ipClient = it.next().get();
                        if (ipClient != null) {
                            arrayList.add(ipClient);
                        }
                    }
                }
                for (IpClient ipClient2 : arrayList) {
                    indentingPrintWriter.println(ipClient2.getName());
                    indentingPrintWriter.increaseIndent();
                    ipClient2.dump(fileDescriptor, indentingPrintWriter, strArr);
                    indentingPrintWriter.decreaseIndent();
                    hashSet.add(ipClient2.getInterfaceName());
                }
                indentingPrintWriter.println();
                indentingPrintWriter.println("Other IpClient logs:");
                IpClient.dumpAllLogs(printWriter, hashSet);
                indentingPrintWriter.println();
                indentingPrintWriter.println("Validation logs (most recent first):");
                synchronized (this.mValidationLogs) {
                    for (SharedLog sharedLog : this.mValidationLogs) {
                        indentingPrintWriter.println(sharedLog.getTag());
                        indentingPrintWriter.increaseIndent();
                        sharedLog.dump(fileDescriptor, indentingPrintWriter, strArr);
                        indentingPrintWriter.decreaseIndent();
                    }
                }
            }
        }

        private void dumpVersion(PrintWriter printWriter) {
            if (!ShimUtils.isReleaseOrDevelopmentApiAbove(29)) {
                dumpVersionNumberOnly(printWriter);
                return;
            }
            printWriter.println("LocalInterface:7:02cd6fd07d5c04eca0c35a350f7b0be576242883");
            synchronized (this.mAidlVersions) {
                Iterator<AidlVersion> it = sortVersions(this.mAidlVersions).iterator();
                while (it.hasNext()) {
                    printWriter.println(it.next());
                }
            }
        }

        private List<AidlVersion> sortVersions(Collection<AidlVersion> collection) {
            ArrayList arrayList = new ArrayList(collection);
            Collections.sort(arrayList);
            return arrayList;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0067  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void dumpVersionNumberOnly(java.io.PrintWriter r11) {
            /*
                r10 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "NetworkStackConnector: "
                r0.append(r1)
                r1 = 7
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r11.println(r0)
                java.util.TreeSet r0 = new java.util.TreeSet
                r0.<init>()
                android.util.ArraySet<com.android.server.NetworkStackService$NetworkStackConnector$AidlVersion> r1 = r10.mAidlVersions
                monitor-enter(r1)
                android.util.ArraySet<com.android.server.NetworkStackService$NetworkStackConnector$AidlVersion> r10 = r10.mAidlVersions     // Catch: java.lang.Throwable -> Lb1
                java.util.Iterator r10 = r10.iterator()     // Catch: java.lang.Throwable -> Lb1
                r2 = -1
                r3 = r2
            L25:
                boolean r4 = r10.hasNext()     // Catch: java.lang.Throwable -> Lb1
                if (r4 == 0) goto L7c
                java.lang.Object r4 = r10.next()     // Catch: java.lang.Throwable -> Lb1
                com.android.server.NetworkStackService$NetworkStackConnector$AidlVersion r4 = (com.android.server.NetworkStackService.NetworkStackConnector.AidlVersion) r4     // Catch: java.lang.Throwable -> Lb1
                java.lang.String r5 = r4.mKey     // Catch: java.lang.Throwable -> Lb1
                int r6 = r5.hashCode()     // Catch: java.lang.Throwable -> Lb1
                r7 = -2027865863(0xffffffff872138f9, float:-1.2129037E-34)
                r8 = 2
                r9 = 1
                if (r6 == r7) goto L5d
                r7 = -1010406(0xfffffffffff0951a, float:NaN)
                if (r6 == r7) goto L53
                r7 = 3377767(0x338a67, float:4.73326E-39)
                if (r6 == r7) goto L49
                goto L67
            L49:
                java.lang.String r6 = "netd"
                boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> Lb1
                if (r5 == 0) goto L67
                r5 = r8
                goto L68
            L53:
                java.lang.String r6 = "networkstack"
                boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> Lb1
                if (r5 == 0) goto L67
                r5 = r9
                goto L68
            L5d:
                java.lang.String r6 = "ipmemorystore"
                boolean r5 = r5.equals(r6)     // Catch: java.lang.Throwable -> Lb1
                if (r5 == 0) goto L67
                r5 = 0
                goto L68
            L67:
                r5 = r2
            L68:
                if (r5 == 0) goto L72
                if (r5 == r9) goto L72
                if (r5 == r8) goto L6f
                goto L25
            L6f:
                int r3 = r4.mVersion     // Catch: java.lang.Throwable -> Lb1
                goto L25
            L72:
                int r4 = r4.mVersion     // Catch: java.lang.Throwable -> Lb1
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> Lb1
                r0.add(r4)     // Catch: java.lang.Throwable -> Lb1
                goto L25
            L7c:
                monitor-exit(r1)     // Catch: java.lang.Throwable -> Lb1
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>()
                java.lang.String r1 = "SystemServer: {"
                r10.append(r1)
                java.lang.String r1 = ", "
                java.lang.String r0 = android.text.TextUtils.join(r1, r0)
                r10.append(r0)
                java.lang.String r0 = "}"
                r10.append(r0)
                java.lang.String r10 = r10.toString()
                r11.println(r10)
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>()
                java.lang.String r0 = "Netd: "
                r10.append(r0)
                r10.append(r3)
                java.lang.String r10 = r10.toString()
                r11.println(r10)
                return
            Lb1:
                r10 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> Lb1
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.NetworkStackService.NetworkStackConnector.dumpVersionNumberOnly(java.io.PrintWriter):void");
        }
    }

    public static class NetworkMonitorConnector extends INetworkMonitor.Stub {
        private final NetworkMonitor mNm;
        private final PermissionChecker mPermChecker;

        @Override // android.net.INetworkMonitor
        public String getInterfaceHash() {
            return "02cd6fd07d5c04eca0c35a350f7b0be576242883";
        }

        @Override // android.net.INetworkMonitor
        public int getInterfaceVersion() {
            return 7;
        }

        public NetworkMonitorConnector(NetworkMonitor networkMonitor, PermissionChecker permissionChecker) {
            this.mNm = networkMonitor;
            this.mPermChecker = permissionChecker;
        }

        @Override // android.net.INetworkMonitor
        public void start() {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.start();
        }

        @Override // android.net.INetworkMonitor
        public void launchCaptivePortalApp() {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.launchCaptivePortalApp();
        }

        @Override // android.net.INetworkMonitor
        public void notifyCaptivePortalAppFinished(int i) {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.notifyCaptivePortalAppFinished(i);
        }

        @Override // android.net.INetworkMonitor
        public void setAcceptPartialConnectivity() {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.setAcceptPartialConnectivity();
        }

        @Override // android.net.INetworkMonitor
        public void forceReevaluation(int i) {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.forceReevaluation(i);
        }

        @Override // android.net.INetworkMonitor
        public void notifyPrivateDnsChanged(PrivateDnsConfigParcel privateDnsConfigParcel) {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.notifyPrivateDnsSettingsChanged(PrivateDnsConfig.fromParcel(privateDnsConfigParcel));
        }

        @Override // android.net.INetworkMonitor
        public void notifyDnsResponse(int i) {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.notifyDnsResponse(i);
        }

        @Override // android.net.INetworkMonitor
        public void notifyNetworkConnected(LinkProperties linkProperties, NetworkCapabilities networkCapabilities) {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.notifyNetworkConnected(linkProperties, networkCapabilities);
        }

        @Override // android.net.INetworkMonitor
        public void notifyNetworkDisconnected() {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.notifyNetworkDisconnected();
        }

        @Override // android.net.INetworkMonitor
        public void notifyLinkPropertiesChanged(LinkProperties linkProperties) {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.notifyLinkPropertiesChanged(linkProperties);
        }

        @Override // android.net.INetworkMonitor
        public void notifyNetworkCapabilitiesChanged(NetworkCapabilities networkCapabilities) {
            this.mPermChecker.enforceNetworkStackCallingPermission();
            this.mNm.notifyNetworkCapabilitiesChanged(networkCapabilities);
        }
    }
}
