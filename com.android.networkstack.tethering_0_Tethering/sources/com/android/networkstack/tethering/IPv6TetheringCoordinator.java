package com.android.networkstack.tethering;

import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.RouteInfo;
import android.net.ip.IpServer;
import android.net.util.SharedLog;
import android.util.Log;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public class IPv6TetheringCoordinator {
    private static final String TAG = "IPv6TetheringCoordinator";
    private final SharedLog mLog;
    private final ArrayList<IpServer> mNotifyList;
    private UpstreamNetworkState mUpstreamNetworkState;
    private final LinkedList<Downstream> mActiveDownstreams = new LinkedList<>();
    private final byte[] mUniqueLocalPrefix = generateUniqueLocalPrefix();
    private short mNextSubnetId = 0;

    private static class Downstream {
        public final IpServer ipServer;
        public final int mode;
        public final short subnetId;

        Downstream(IpServer ipServer, int i, short s) {
            this.ipServer = ipServer;
            this.mode = i;
            this.subnetId = s;
        }
    }

    public IPv6TetheringCoordinator(ArrayList<IpServer> arrayList, SharedLog sharedLog) {
        this.mNotifyList = arrayList;
        this.mLog = sharedLog.forSubComponent(TAG);
    }

    public void addActiveDownstream(IpServer ipServer, int i) {
        if (findDownstream(ipServer) == null) {
            if (this.mActiveDownstreams.offer(new Downstream(ipServer, i, this.mNextSubnetId))) {
                this.mNextSubnetId = (short) Math.max(0, this.mNextSubnetId + 1);
            }
            updateIPv6TetheringInterfaces();
        }
    }

    public void removeActiveDownstream(IpServer ipServer) {
        stopIPv6TetheringOn(ipServer);
        if (this.mActiveDownstreams.remove(findDownstream(ipServer))) {
            updateIPv6TetheringInterfaces();
        }
        if (this.mNotifyList.isEmpty()) {
            if (!this.mActiveDownstreams.isEmpty()) {
                Log.wtf(TAG, "Tethering notify list empty, IPv6 downstreams non-empty.");
            }
            this.mNextSubnetId = (short) 0;
        }
    }

    public void updateUpstreamNetworkState(UpstreamNetworkState upstreamNetworkState) {
        if (TetheringInterfaceUtils.getIPv6Interface(upstreamNetworkState) == null) {
            stopIPv6TetheringOnAllInterfaces();
            setUpstreamNetworkState(null);
            return;
        }
        UpstreamNetworkState upstreamNetworkState2 = this.mUpstreamNetworkState;
        if (upstreamNetworkState2 != null && !upstreamNetworkState.network.equals(upstreamNetworkState2.network)) {
            stopIPv6TetheringOnAllInterfaces();
        }
        setUpstreamNetworkState(upstreamNetworkState);
        updateIPv6TetheringInterfaces();
    }

    private void stopIPv6TetheringOnAllInterfaces() {
        Iterator<IpServer> it = this.mNotifyList.iterator();
        while (it.hasNext()) {
            stopIPv6TetheringOn(it.next());
        }
    }

    private void setUpstreamNetworkState(UpstreamNetworkState upstreamNetworkState) {
        if (upstreamNetworkState == null) {
            this.mUpstreamNetworkState = null;
        } else {
            this.mUpstreamNetworkState = new UpstreamNetworkState(new LinkProperties(upstreamNetworkState.linkProperties), new NetworkCapabilities(upstreamNetworkState.networkCapabilities), new Network(upstreamNetworkState.network));
        }
        this.mLog.log("setUpstreamNetworkState: " + toDebugString(this.mUpstreamNetworkState));
    }

    private void updateIPv6TetheringInterfaces() {
        Iterator<IpServer> it = this.mNotifyList.iterator();
        if (it.hasNext()) {
            IpServer next = it.next();
            next.sendMessage(IpServer.CMD_IPV6_TETHER_UPDATE, getTtlAdjustment(), 0, getInterfaceIPv6LinkProperties(next));
        }
    }

    private int getTtlAdjustment() {
        NetworkCapabilities networkCapabilities;
        UpstreamNetworkState upstreamNetworkState = this.mUpstreamNetworkState;
        if (upstreamNetworkState == null || (networkCapabilities = upstreamNetworkState.networkCapabilities) == null) {
            return 0;
        }
        return networkCapabilities.hasTransport(0) ? -1 : 1;
    }

    private LinkProperties getInterfaceIPv6LinkProperties(IpServer ipServer) {
        Downstream downstreamPeek;
        Downstream downstreamFindDownstream = findDownstream(ipServer);
        if (downstreamFindDownstream == null) {
            return null;
        }
        if (downstreamFindDownstream.mode == 3) {
            return getUniqueLocalConfig(this.mUniqueLocalPrefix, downstreamFindDownstream.subnetId);
        }
        UpstreamNetworkState upstreamNetworkState = this.mUpstreamNetworkState;
        if (upstreamNetworkState != null && upstreamNetworkState.linkProperties != null && (downstreamPeek = this.mActiveDownstreams.peek()) != null && downstreamPeek.ipServer == ipServer) {
            LinkProperties iPv6OnlyLinkProperties = getIPv6OnlyLinkProperties(this.mUpstreamNetworkState.linkProperties);
            if (iPv6OnlyLinkProperties.hasIpv6DefaultRoute() && iPv6OnlyLinkProperties.hasGlobalIpv6Address()) {
                return iPv6OnlyLinkProperties;
            }
        }
        return null;
    }

    Downstream findDownstream(IpServer ipServer) {
        for (Downstream downstream : this.mActiveDownstreams) {
            if (downstream.ipServer == ipServer) {
                return downstream;
            }
        }
        return null;
    }

    private static LinkProperties getIPv6OnlyLinkProperties(LinkProperties linkProperties) {
        LinkProperties linkProperties2 = new LinkProperties();
        if (linkProperties == null) {
            return linkProperties2;
        }
        linkProperties2.setInterfaceName(linkProperties.getInterfaceName());
        linkProperties2.setMtu(linkProperties.getMtu());
        for (LinkAddress linkAddress : linkProperties.getLinkAddresses()) {
            if (linkAddress.isGlobalPreferred() && linkAddress.getPrefixLength() == 64) {
                linkProperties2.addLinkAddress(linkAddress);
            }
        }
        for (RouteInfo routeInfo : linkProperties.getRoutes()) {
            IpPrefix destination = routeInfo.getDestination();
            if ((destination.getAddress() instanceof Inet6Address) && destination.getPrefixLength() <= 64) {
                linkProperties2.addRoute(routeInfo);
            }
        }
        for (InetAddress inetAddress : linkProperties.getDnsServers()) {
            if (isIPv6GlobalAddress(inetAddress)) {
                linkProperties2.addDnsServer(inetAddress);
            }
        }
        linkProperties2.setDomains(linkProperties.getDomains());
        return linkProperties2;
    }

    private static boolean isIPv6GlobalAddress(InetAddress inetAddress) {
        return (!(inetAddress instanceof Inet6Address) || inetAddress.isAnyLocalAddress() || inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress() || inetAddress.isSiteLocalAddress() || inetAddress.isMulticastAddress()) ? false : true;
    }

    private static LinkProperties getUniqueLocalConfig(byte[] bArr, short s) {
        LinkProperties linkProperties = new LinkProperties();
        linkProperties.addRoute(new RouteInfo(makeUniqueLocalPrefix(bArr, (short) 0, 48), null, null, 1));
        linkProperties.addLinkAddress(new LinkAddress(makeUniqueLocalPrefix(bArr, s, 64).getAddress(), 64));
        linkProperties.setMtu(1500);
        return linkProperties;
    }

    private static IpPrefix makeUniqueLocalPrefix(byte[] bArr, short s, int i) {
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        bArrCopyOf[7] = (byte) (s >> 8);
        bArrCopyOf[8] = (byte) s;
        try {
            return new IpPrefix(InetAddress.getByAddress(bArrCopyOf), i);
        } catch (UnknownHostException e) {
            throw new IllegalStateException("Invalid address length: " + bArrCopyOf.length, e);
        }
    }

    private static byte[] generateUniqueLocalPrefix() {
        byte[] bArr = new byte[6];
        new Random().nextBytes(bArr);
        byte[] bArrCopyOf = Arrays.copyOf(bArr, 16);
        bArrCopyOf[0] = -3;
        return bArrCopyOf;
    }

    private static String toDebugString(UpstreamNetworkState upstreamNetworkState) {
        return upstreamNetworkState == null ? "UpstreamNetworkState{null}" : upstreamNetworkState.toString();
    }

    private static void stopIPv6TetheringOn(IpServer ipServer) {
        ipServer.sendMessage(IpServer.CMD_IPV6_TETHER_UPDATE, 0, 0, null);
    }
}
