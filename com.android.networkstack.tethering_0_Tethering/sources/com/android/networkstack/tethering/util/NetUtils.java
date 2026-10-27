package com.android.networkstack.tethering.util;

import android.net.RouteInfo;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public final class NetUtils {
    public static boolean addressTypeMatches(InetAddress inetAddress, InetAddress inetAddress2) {
        return ((inetAddress instanceof Inet4Address) && (inetAddress2 instanceof Inet4Address)) || ((inetAddress instanceof Inet6Address) && (inetAddress2 instanceof Inet6Address));
    }

    public static RouteInfo selectBestRoute(Collection<RouteInfo> collection, InetAddress inetAddress) {
        RouteInfo routeInfo = null;
        if (collection != null && inetAddress != null) {
            for (RouteInfo routeInfo2 : collection) {
                if (addressTypeMatches(routeInfo2.getDestination().getAddress(), inetAddress) && (routeInfo == null || routeInfo.getDestination().getPrefixLength() < routeInfo2.getDestination().getPrefixLength())) {
                    if (routeInfo2.matches(inetAddress)) {
                        routeInfo = routeInfo2;
                    }
                }
            }
        }
        return routeInfo;
    }
}
