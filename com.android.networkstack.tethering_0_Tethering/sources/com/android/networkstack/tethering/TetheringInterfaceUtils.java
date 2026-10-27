package com.android.networkstack.tethering;

import android.net.LinkProperties;
import android.net.RouteInfo;
import android.net.util.InterfaceSet;
import com.android.networkstack.tethering.util.NetUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes.dex */
public final class TetheringInterfaceUtils {
    private static final InetAddress IN6ADDR_ANY = getByAddress(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    private static final InetAddress INADDR_ANY = getByAddress(new byte[]{0, 0, 0, 0});

    public static InterfaceSet getTetheringInterfaces(UpstreamNetworkState upstreamNetworkState) {
        if (upstreamNetworkState == null) {
            return null;
        }
        String interfaceForDestination = getInterfaceForDestination(upstreamNetworkState.linkProperties, INADDR_ANY);
        String iPv6Interface = getIPv6Interface(upstreamNetworkState);
        if (interfaceForDestination == null && iPv6Interface == null) {
            return null;
        }
        return new InterfaceSet(interfaceForDestination, iPv6Interface);
    }

    public static String getIPv6Interface(UpstreamNetworkState upstreamNetworkState) {
        LinkProperties linkProperties;
        boolean z = false;
        if (upstreamNetworkState != null && upstreamNetworkState.network != null && (linkProperties = upstreamNetworkState.linkProperties) != null && upstreamNetworkState.networkCapabilities != null && linkProperties.hasIpv6DnsServer() && upstreamNetworkState.linkProperties.hasGlobalIpv6Address() && (upstreamNetworkState.networkCapabilities.hasTransport(0) || upstreamNetworkState.networkCapabilities.hasTransport(3))) {
            z = true;
        }
        if (z) {
            return getInterfaceForDestination(upstreamNetworkState.linkProperties, IN6ADDR_ANY);
        }
        return null;
    }

    private static String getInterfaceForDestination(LinkProperties linkProperties, InetAddress inetAddress) {
        RouteInfo routeInfoSelectBestRoute = linkProperties != null ? NetUtils.selectBestRoute(linkProperties.getAllRoutes(), inetAddress) : null;
        if (routeInfoSelectBestRoute != null) {
            return routeInfoSelectBestRoute.getInterface();
        }
        return null;
    }

    private static InetAddress getByAddress(byte[] bArr) {
        try {
            return InetAddress.getByAddress(null, bArr);
        } catch (UnknownHostException unused) {
            throw new AssertionError("illegal address length" + bArr.length);
        }
    }
}
