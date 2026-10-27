package android.net.ip;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.INetd;
import android.net.LinkProperties;
import android.net.RouteInfo;
import android.net.ip.IpNeighborMonitor;
import android.net.metrics.IpConnectivityLog;
import android.net.metrics.IpReachabilityEvent;
import android.net.netlink.StructNdMsg;
import android.net.networkstack.util.Preconditions;
import android.net.util.InterfaceParams;
import android.net.util.IpMask;
import android.net.util.SharedLog;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.android.networkstack.R;
import java.io.PrintWriter;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class IpReachabilityMonitor {
    private static final boolean DBG = Log.isLoggable("IpReachabilityMonitor", 3);
    private final Callback mCallback;
    private final ConnectivityManager mCm;
    private final Context mContext;
    private final Dependencies mDependencies;
    private int mInterSolicitIntervalMs;
    private final InterfaceParams mInterfaceParams;
    private final IpNeighborMonitor mIpNeighborMonitor;
    private volatile long mLastProbeTimeMs;
    private LinkProperties mLinkProperties;
    private final SharedLog mLog;
    private final IpConnectivityLog mMetricsLog;
    private Map<InetAddress, IpNeighborMonitor.NeighborEvent> mNeighborWatchList;
    private final INetd mNetd;
    private int mNumSolicits;
    private final boolean mUsingMultinetworkPolicyTracker;

    public interface Callback {
        void notifyLost(InetAddress inetAddress, String str);
    }

    private static int nudFailureEventType(boolean z, boolean z2) {
        return z ? z2 ? 768 : 512 : z2 ? 1280 : 1024;
    }

    static {
        Log.isLoggable("IpReachabilityMonitor", 2);
    }

    interface Dependencies {
        void acquireWakeLock(long j);

        IpNeighborMonitor makeIpNeighborMonitor(Handler handler, SharedLog sharedLog, IpNeighborMonitor.NeighborEventConsumer neighborEventConsumer);

        static Dependencies makeDefault(Context context, String str) {
            final PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "IpReachabilityMonitor." + str);
            return new Dependencies() { // from class: android.net.ip.IpReachabilityMonitor.Dependencies.1
                @Override // android.net.ip.IpReachabilityMonitor.Dependencies
                public void acquireWakeLock(long j) {
                    wakeLockNewWakeLock.acquire(j);
                }

                @Override // android.net.ip.IpReachabilityMonitor.Dependencies
                public IpNeighborMonitor makeIpNeighborMonitor(Handler handler, SharedLog sharedLog, IpNeighborMonitor.NeighborEventConsumer neighborEventConsumer) {
                    return new IpNeighborMonitor(handler, sharedLog, neighborEventConsumer);
                }
            };
        }
    }

    public IpReachabilityMonitor(Context context, InterfaceParams interfaceParams, Handler handler, SharedLog sharedLog, Callback callback, boolean z, INetd iNetd) {
        this(context, interfaceParams, handler, sharedLog, callback, z, Dependencies.makeDefault(context, interfaceParams.name), new IpConnectivityLog(), iNetd);
    }

    IpReachabilityMonitor(Context context, InterfaceParams interfaceParams, Handler handler, SharedLog sharedLog, Callback callback, boolean z, Dependencies dependencies, IpConnectivityLog ipConnectivityLog, INetd iNetd) {
        this.mLinkProperties = new LinkProperties();
        this.mNeighborWatchList = new HashMap();
        if (interfaceParams == null) {
            throw new IllegalArgumentException("null InterfaceParams");
        }
        this.mContext = context;
        this.mInterfaceParams = interfaceParams;
        this.mLog = sharedLog.forSubComponent("IpReachabilityMonitor");
        this.mCallback = callback;
        this.mUsingMultinetworkPolicyTracker = z;
        this.mCm = (ConnectivityManager) context.getSystemService(ConnectivityManager.class);
        this.mDependencies = dependencies;
        this.mMetricsLog = ipConnectivityLog;
        this.mNetd = iNetd;
        Preconditions.checkNotNull(iNetd);
        Preconditions.checkArgument(!TextUtils.isEmpty(this.mInterfaceParams.name));
        try {
            setNeighborParameters(5, 750);
        } catch (Exception unused) {
            Log.e("IpReachabilityMonitor", "Failed to adjust neighbor parameters with hardcoded defaults");
        }
        setNeighbourParametersForSteadyState();
        IpNeighborMonitor ipNeighborMonitorMakeIpNeighborMonitor = this.mDependencies.makeIpNeighborMonitor(handler, this.mLog, new IpNeighborMonitor.NeighborEventConsumer() { // from class: android.net.ip.-$$Lambda$IpReachabilityMonitor$5Sg30oRgfU2r5ogQj53SRYnnFiQ
            @Override // android.net.ip.IpNeighborMonitor.NeighborEventConsumer
            public final void accept(IpNeighborMonitor.NeighborEvent neighborEvent) {
                this.f$0.lambda$new$0$IpReachabilityMonitor(neighborEvent);
            }
        });
        this.mIpNeighborMonitor = ipNeighborMonitorMakeIpNeighborMonitor;
        ipNeighborMonitorMakeIpNeighborMonitor.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$IpReachabilityMonitor(IpNeighborMonitor.NeighborEvent neighborEvent) {
        if (this.mInterfaceParams.index == neighborEvent.ifindex && this.mNeighborWatchList.containsKey(neighborEvent.ip)) {
            IpNeighborMonitor.NeighborEvent neighborEventPut = this.mNeighborWatchList.put(neighborEvent.ip, neighborEvent);
            short s = neighborEvent.nudState;
            if (s != 32) {
                if (s == 2) {
                    maybeRestoreNeighborParameters();
                    return;
                }
                return;
            }
            this.mLog.w("ALERT neighbor went from: " + neighborEventPut + " to: " + neighborEvent);
            handleNeighborLost(neighborEvent);
        }
    }

    public void stop() {
        this.mIpNeighborMonitor.stop();
        clearLinkProperties();
    }

    public void dump(final PrintWriter printWriter) {
        if (Looper.myLooper() == this.mIpNeighborMonitor.getHandler().getLooper()) {
            printWriter.println(describeWatchList("\n"));
            return;
        }
        final ConditionVariable conditionVariable = new ConditionVariable(false);
        this.mIpNeighborMonitor.getHandler().post(new Runnable() { // from class: android.net.ip.-$$Lambda$IpReachabilityMonitor$c1J5Y17FHcHurCdAcdDM8R0lYUE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$dump$1$IpReachabilityMonitor(printWriter, conditionVariable);
            }
        });
        if (conditionVariable.block(1000L)) {
            return;
        }
        printWriter.println("Timed out waiting for IpReachabilityMonitor dump");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$dump$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$dump$1$IpReachabilityMonitor(PrintWriter printWriter, ConditionVariable conditionVariable) {
        printWriter.println(describeWatchList("\n"));
        conditionVariable.open();
    }

    private String describeWatchList() {
        return describeWatchList(" ");
    }

    private String describeWatchList(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("iface{" + this.mInterfaceParams + "}," + str);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("ntable=[");
        sb2.append(str);
        sb.append(sb2.toString());
        String string = "";
        for (Map.Entry<InetAddress, IpNeighborMonitor.NeighborEvent> entry : this.mNeighborWatchList.entrySet()) {
            sb.append(string);
            sb.append(IpMask.maskIpMiddleString(entry.getKey().getHostAddress()) + "/" + entry.getValue());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(",");
            sb3.append(str);
            string = sb3.toString();
        }
        sb.append("]");
        return sb.toString();
    }

    private static boolean isOnLink(List<RouteInfo> list, InetAddress inetAddress) {
        for (RouteInfo routeInfo : list) {
            if (!routeInfo.hasGateway() && routeInfo.matches(inetAddress)) {
                return true;
            }
        }
        return false;
    }

    public void updateLinkProperties(LinkProperties linkProperties) {
        if (!this.mInterfaceParams.name.equals(linkProperties.getInterfaceName())) {
            Log.wtf("IpReachabilityMonitor", "requested LinkProperties interface '" + linkProperties.getInterfaceName() + "' does not match: " + this.mInterfaceParams.name);
            return;
        }
        this.mLinkProperties = new LinkProperties(linkProperties);
        HashMap map = new HashMap();
        List<RouteInfo> routes = this.mLinkProperties.getRoutes();
        for (RouteInfo routeInfo : routes) {
            if (routeInfo.hasGateway()) {
                InetAddress gateway = routeInfo.getGateway();
                if (isOnLink(routes, gateway)) {
                    map.put(gateway, this.mNeighborWatchList.getOrDefault(gateway, null));
                }
            }
        }
        for (InetAddress inetAddress : linkProperties.getDnsServers()) {
            if (isOnLink(routes, inetAddress)) {
                map.put(inetAddress, this.mNeighborWatchList.getOrDefault(inetAddress, null));
            }
        }
        this.mNeighborWatchList = map;
        if (DBG) {
            Log.d("IpReachabilityMonitor", "watch: " + describeWatchList());
        }
    }

    public void clearLinkProperties() {
        this.mLinkProperties.clear();
        this.mNeighborWatchList.clear();
        if (DBG) {
            Log.d("IpReachabilityMonitor", "clear: " + describeWatchList());
        }
    }

    private boolean isNeighborDefaultRouter(IpNeighborMonitor.NeighborEvent neighborEvent) {
        for (RouteInfo routeInfo : this.mLinkProperties.getRoutes()) {
            if (DBG) {
                Log.d("IpReachabilityMonitor", "getateway:" + IpMask.maskIpMiddle(routeInfo.getGateway()) + " event.ip:" + IpMask.maskIpMiddle(neighborEvent.ip));
            }
            if (routeInfo.isDefaultRoute() && neighborEvent.ip.equals(routeInfo.getGateway())) {
                return true;
            }
        }
        return false;
    }

    private boolean isNeighborDnsServer(IpNeighborMonitor.NeighborEvent neighborEvent) {
        for (InetAddress inetAddress : this.mLinkProperties.getDnsServers()) {
            if (DBG) {
                Log.d("IpReachabilityMonitor", "dns:" + IpMask.maskIpMiddle(inetAddress) + " event.ip:" + IpMask.maskIpMiddle(neighborEvent.ip));
            }
            if (neighborEvent.ip.equals(inetAddress)) {
                return true;
            }
        }
        return false;
    }

    private void handleNeighborLost(IpNeighborMonitor.NeighborEvent neighborEvent) {
        LinkProperties linkProperties = new LinkProperties(this.mLinkProperties);
        InetAddress key = null;
        for (Map.Entry<InetAddress, IpNeighborMonitor.NeighborEvent> entry : this.mNeighborWatchList.entrySet()) {
            IpNeighborMonitor.NeighborEvent value = entry.getValue();
            if (value != null && value.nudState == 32) {
                key = entry.getKey();
                for (RouteInfo routeInfo : this.mLinkProperties.getRoutes()) {
                    if (key.equals(routeInfo.getGateway())) {
                        linkProperties.removeRoute(routeInfo);
                    }
                }
                if (avoidingBadLinks() || !(key instanceof Inet6Address)) {
                    linkProperties.removeDnsServer(key);
                }
            }
        }
        boolean z = (this.mLinkProperties.isIpv4Provisioned() && !linkProperties.isIpv4Provisioned()) || (this.mLinkProperties.isIpv6Provisioned() && !linkProperties.isIpv6Provisioned());
        boolean zIsNeighborDefaultRouter = isNeighborDefaultRouter(neighborEvent);
        boolean zIsNeighborDnsServer = isNeighborDnsServer(neighborEvent);
        if (z && (zIsNeighborDefaultRouter || zIsNeighborDnsServer)) {
            String str = "FAILURE: LOST_PROVISIONING, " + neighborEvent + " defaluteRoute:" + zIsNeighborDefaultRouter + " defaluteDns:" + zIsNeighborDnsServer;
            Log.w("IpReachabilityMonitor", str);
            Callback callback = this.mCallback;
            if (callback != null) {
                callback.notifyLost(key, str);
            }
        }
        logNudFailed(z);
    }

    private void maybeRestoreNeighborParameters() {
        for (Map.Entry<InetAddress, IpNeighborMonitor.NeighborEvent> entry : this.mNeighborWatchList.entrySet()) {
            if (DBG) {
                Log.d("IpReachabilityMonitor", "neighbour IPv4(v6): " + IpMask.maskIpMiddle(entry.getKey()) + " neighbour state: " + StructNdMsg.stringForNudState(entry.getValue().nudState));
            }
            IpNeighborMonitor.NeighborEvent value = entry.getValue();
            if (value == null || value.nudState != 2) {
                return;
            }
        }
        setNeighbourParametersForSteadyState();
    }

    private boolean avoidingBadLinks() {
        return !this.mUsingMultinetworkPolicyTracker || this.mCm.shouldAvoidBadWifi();
    }

    public void probeAll() {
        setNeighbourParametersPostRoaming();
        ArrayList<InetAddress> arrayList = new ArrayList(this.mNeighborWatchList.keySet());
        if (!arrayList.isEmpty()) {
            this.mDependencies.acquireWakeLock(getProbeWakeLockDuration());
        }
        for (InetAddress inetAddress : arrayList) {
            int iStartKernelNeighborProbe = IpNeighborMonitor.startKernelNeighborProbe(this.mInterfaceParams.index, inetAddress);
            this.mLog.log(String.format("put neighbor %s into NUD_PROBE state (rval=%d)", inetAddress.getHostAddress(), Integer.valueOf(iStartKernelNeighborProbe)));
            logEvent(256, iStartKernelNeighborProbe);
        }
        this.mLastProbeTimeMs = SystemClock.elapsedRealtime();
    }

    private long getProbeWakeLockDuration() {
        return ((long) (this.mNumSolicits * this.mInterSolicitIntervalMs)) + 500;
    }

    private void setNeighbourParametersPostRoaming() {
        setNeighborParametersFromResources(R.integer.config_nud_postroaming_solicit_num, R.integer.config_nud_postroaming_solicit_interval);
    }

    private void setNeighbourParametersForSteadyState() {
        setNeighborParametersFromResources(R.integer.config_nud_steadystate_solicit_num, R.integer.config_nud_steadystate_solicit_interval);
    }

    private void setNeighborParametersFromResources(int i, int i2) {
        try {
            setNeighborParameters(this.mContext.getResources().getInteger(i), this.mContext.getResources().getInteger(i2));
        } catch (Exception unused) {
            Log.e("IpReachabilityMonitor", "Failed to adjust neighbor parameters");
        }
    }

    private void setNeighborParameters(int i, int i2) throws RemoteException, IllegalArgumentException {
        Preconditions.checkArgument(i >= 5, "numSolicits must be at least 5");
        Preconditions.checkArgument(i <= 15, "numSolicits must be at most 15");
        Preconditions.checkArgument(i2 >= 750, "interSolicitIntervalMs must be at least 750");
        Preconditions.checkArgument(i2 <= 1000, "interSolicitIntervalMs must be at most 1000");
        Integer[] numArr = {4, 6};
        for (int i3 = 0; i3 < 2; i3++) {
            int iIntValue = numArr[i3].intValue();
            this.mNetd.setProcSysNet(iIntValue, 2, this.mInterfaceParams.name, "retrans_time_ms", Integer.toString(i2));
            this.mNetd.setProcSysNet(iIntValue, 2, this.mInterfaceParams.name, "ucast_solicit", Integer.toString(i));
        }
        this.mNumSolicits = i;
        this.mInterSolicitIntervalMs = i2;
    }

    private void logEvent(int i, int i2) {
        this.mMetricsLog.log(this.mInterfaceParams.name, new IpReachabilityEvent(i | (i2 & 255)));
    }

    private void logNudFailed(boolean z) {
        this.mMetricsLog.log(this.mInterfaceParams.name, new IpReachabilityEvent(nudFailureEventType(SystemClock.elapsedRealtime() - this.mLastProbeTimeMs < getProbeWakeLockDuration(), z)));
    }
}
