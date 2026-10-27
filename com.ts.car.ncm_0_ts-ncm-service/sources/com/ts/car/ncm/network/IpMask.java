package com.ts.car.ncm.network;

import android.net.INetd;
import android.net.IpConfiguration;
import android.net.IpPrefix;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.MacAddress;
import android.net.RouteInfo;
import android.net.StaticIpConfiguration;
import android.text.TextUtils;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes.dex */
public class IpMask {
    private static final String ALL_NEED_MARK = ".";
    private static final String IPV4_REPLACE = ".";
    private static final String IPV4_SPLIT = "\\.";
    private static final String IPV6_SPLIT = ":";
    private static final String MAC_SPLIT = ":";
    private static final String MASK = "*";
    private static final int MAX_IPV4_LEGNTH = 4;
    private static final int MAX_IPV6_LEGNTH = 8;
    private static final int MAX_MAC_LENGTH = 6;
    private static final Integer[] sIpv4Array = {2};
    private static final Integer[] sIpv6Array = {1, 2, 3, 4, 5, 6};
    private static final Integer[] sMacArray = {1, 2, 3, 4};
    private static final List<Integer> sIpv4List = new ArrayList(Arrays.asList(sIpv4Array));
    private static final List<Integer> sIpv6List = new ArrayList(Arrays.asList(sIpv6Array));
    private static final List<Integer> sMacList = new ArrayList(Arrays.asList(sMacArray));
    private static boolean mSecFlag = true;

    public static void setSecFlag(boolean flag) {
        mSecFlag = flag;
    }

    public static String maskIpMiddle(InetAddress ipaddress) {
        if (ipaddress == null) {
            return "NULL";
        }
        String ip = ipaddress.toString();
        if (ip.isEmpty()) {
            return "EMPTRY";
        }
        return mSecFlag ? maskIpMiddleString(ip) : ip;
    }

    public static String maskIpMiddleString(String ip) {
        String split = IPV4_SPLIT;
        int maxLen = 4;
        String replaceStr = ".";
        List<Integer> segmentsToMask = sIpv4List;
        if (ip.contains(":")) {
            split = ":";
            maxLen = 8;
            replaceStr = ":";
            segmentsToMask = sIpv6List;
        }
        String[] parts = ip.split(split);
        int totalSegments = Math.min(parts.length, maxLen);
        StringBuilder maskedIp = new StringBuilder();
        for (int i = 0; i < totalSegments; i++) {
            boolean zContains = segmentsToMask.contains(Integer.valueOf(i));
            String str = INetd.NEXTHOP_NONE;
            if (zContains && i != totalSegments - 1) {
                maskedIp.append(parts[i].replaceAll(".", MASK));
                if (i < totalSegments - 1) {
                    str = replaceStr;
                }
                maskedIp.append(str);
            } else {
                maskedIp.append(parts[i]);
                if (i < totalSegments - 1) {
                    str = replaceStr;
                }
                maskedIp.append(str);
            }
        }
        return maskedIp.toString();
    }

    public static String maskMac(MacAddress macAddr) {
        if (macAddr == null) {
            return "NULL";
        }
        String mac = macAddr.toString();
        if (mac.isEmpty()) {
            return "EMPTRY";
        }
        return mSecFlag ? maskMacString(mac) : mac;
    }

    public static String maskMacString(String mac) {
        String[] parts = mac.split(":");
        int totalSegments = Math.min(parts.length, 6);
        StringBuilder maskedIp = new StringBuilder();
        for (int i = 0; i < totalSegments; i++) {
            boolean zContains = sMacList.contains(Integer.valueOf(i));
            String str = INetd.NEXTHOP_NONE;
            if (zContains && i != totalSegments - 1) {
                maskedIp.append(parts[i].replaceAll(".", MASK));
                if (i < totalSegments - 1) {
                    str = ":";
                }
                maskedIp.append(str);
            } else {
                maskedIp.append(parts[i]);
                if (i < totalSegments - 1) {
                    str = ":";
                }
                maskedIp.append(str);
            }
        }
        return maskedIp.toString();
    }

    public static String getLinkAddressString(LinkAddress linkAddress) {
        if (linkAddress == null) {
            return "NULL";
        }
        return mSecFlag ? maskIpMiddle(linkAddress.getAddress()) : linkAddress.toString();
    }

    public static String getStaticConfigString(StaticIpConfiguration config) {
        if (config == null) {
            return "NULL";
        }
        if (!mSecFlag) {
            return config.toString();
        }
        StringBuffer sb = new StringBuffer();
        sb.append("IP: [");
        sb.append(getLinkAddressString(config.ipAddress));
        sb.append("],");
        sb.append("DNS: [");
        sb.append(joinInetAddress(",", config.dnsServers));
        sb.append("],");
        sb.append("GATEWAY: [");
        sb.append(maskIpMiddle(config.gateway));
        sb.append("]");
        return sb.toString();
    }

    public static String getIpPrefixString(IpPrefix prefix) {
        if (prefix == null) {
            return "NULL";
        }
        if (!mSecFlag) {
            return prefix.toString();
        }
        return maskIpMiddle(prefix.getAddress()) + "/" + prefix.getPrefixLength();
    }

    public static String getRouteInfoString(RouteInfo route) {
        if (route == null) {
            return "NULL";
        }
        if (!mSecFlag) {
            return route.toString();
        }
        StringBuffer sb = new StringBuffer();
        sb.append("DEST: [");
        sb.append(getIpPrefixString(route.getDestination()));
        sb.append("],");
        sb.append("GATEWAY: [");
        sb.append(maskIpMiddle(route.getGateway()));
        sb.append("],");
        sb.append("IFACE: [");
        sb.append(route.getInterface());
        sb.append("]");
        return sb.toString();
    }

    public static String getIpConfigurationString(IpConfiguration config) {
        if (config == null) {
            return "NULL";
        }
        if (!mSecFlag) {
            return config.toString();
        }
        StringBuilder sbuf = new StringBuilder();
        sbuf.append("IP assignment: " + config.getIpAssignment().toString());
        sbuf.append("\n");
        if (config.getStaticIpConfiguration() != null) {
            sbuf.append("Static configuration: " + getStaticConfigString(config.getStaticIpConfiguration()));
            sbuf.append("\n");
        }
        sbuf.append("Proxy settings: " + config.getProxySettings().toString());
        sbuf.append("\n");
        if (config.getHttpProxy() != null) {
            sbuf.append("HTTP proxy: " + config.getHttpProxy().toString());
            sbuf.append("\n");
        }
        return sbuf.toString();
    }

    private static String joinInetAddress(String delimiter, List<InetAddress> address) {
        if (address == null || address.isEmpty()) {
            return "NULL";
        }
        if (!mSecFlag) {
            StringJoiner joiner = new StringJoiner(" ");
            joiner.add(TextUtils.join(delimiter, address));
            return joiner.toString();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(maskIpMiddle(address.get(0)));
        for (int i = 1; i < address.size(); i++) {
            sb.append(delimiter);
            sb.append(maskIpMiddle(address.get(i)));
        }
        return sb.toString();
    }

    private static String joinRouteInfo(String delimiter, List<RouteInfo> infos) {
        if (infos == null || infos.isEmpty()) {
            return "NULL";
        }
        if (!mSecFlag) {
            StringJoiner joiner = new StringJoiner(" ");
            joiner.add(TextUtils.join(delimiter, infos));
            return joiner.toString();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(getRouteInfoString(infos.get(0)));
        for (int i = 0; i < infos.size(); i++) {
            sb.append(delimiter);
            sb.append(getRouteInfoString(infos.get(i)));
        }
        return sb.toString();
    }

    private static String joinLinkProperties(String delimiter, List<LinkProperties> props) {
        if (props == null || props.isEmpty()) {
            return "NULL";
        }
        if (!mSecFlag) {
            StringJoiner joiner = new StringJoiner(" ");
            joiner.add(TextUtils.join(delimiter, props));
            return joiner.toString();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(getLinkPropertiesString(props.get(0)));
        for (int i = 0; i < props.size(); i++) {
            sb.append(delimiter);
            sb.append(getLinkPropertiesString(props.get(i)));
        }
        return sb.toString();
    }

    public static String getLinkPropertiesString(LinkProperties prop) {
        if (prop == null) {
            return "NULL";
        }
        if (!mSecFlag) {
            return prop.toString();
        }
        StringJoiner resultJoiner = new StringJoiner(" ", "{", "}");
        if (prop.getInterfaceName() != null) {
            resultJoiner.add("InterfaceName:");
            resultJoiner.add(prop.getInterfaceName());
        }
        resultJoiner.add("LinkAddresses: [");
        if (!prop.getAddresses().isEmpty()) {
            resultJoiner.add(joinInetAddress(",", prop.getAddresses()));
        }
        resultJoiner.add("]");
        resultJoiner.add("DnsAddresses: [");
        if (!prop.getDnsServers().isEmpty()) {
            resultJoiner.add(joinInetAddress(",", prop.getDnsServers()));
        }
        resultJoiner.add("]");
        if (prop.isPrivateDnsActive()) {
            resultJoiner.add("UsePrivateDns: true");
        }
        if (prop.getPrivateDnsServerName() != null) {
            resultJoiner.add("PrivateDnsServerName:");
            resultJoiner.add(prop.getPrivateDnsServerName());
        }
        if (!prop.getPcscfServers().isEmpty()) {
            resultJoiner.add("PcscfAddresses: [");
            resultJoiner.add(joinInetAddress(",", prop.getPcscfServers()));
            resultJoiner.add("]");
        }
        if (!prop.getValidatedPrivateDnsServers().isEmpty()) {
            StringJoiner validatedPrivateDnsesJoiner = new StringJoiner(",", "ValidatedPrivateDnsAddresses: [", "]");
            validatedPrivateDnsesJoiner.add(joinInetAddress(",", prop.getValidatedPrivateDnsServers()));
            resultJoiner.add(validatedPrivateDnsesJoiner.toString());
        }
        resultJoiner.add("Domains:");
        resultJoiner.add(prop.getDomains());
        resultJoiner.add("MTU:");
        resultJoiner.add(Integer.toString(prop.getMtu()));
        if (prop.isWakeOnLanSupported()) {
            resultJoiner.add("WakeOnLanSupported: true");
        }
        if (prop.getDhcpServerAddress() != null) {
            resultJoiner.add("ServerAddress:");
            resultJoiner.add(maskIpMiddle(prop.getDhcpServerAddress()));
        }
        if (prop.getCaptivePortalApiUrl() != null) {
            resultJoiner.add("CaptivePortalApiUrl: " + prop.getCaptivePortalApiUrl());
        }
        if (prop.getCaptivePortalData() != null) {
            resultJoiner.add("CaptivePortalData: " + prop.getCaptivePortalData());
        }
        if (prop.getTcpBufferSizes() != null) {
            resultJoiner.add("TcpBufferSizes:");
            resultJoiner.add(prop.getTcpBufferSizes());
        }
        resultJoiner.add("Routes: [");
        if (!prop.getRoutes().isEmpty()) {
            resultJoiner.add(joinRouteInfo(",", prop.getRoutes()));
        }
        resultJoiner.add("]");
        if (prop.getHttpProxy() != null) {
            resultJoiner.add("HttpProxy:");
            resultJoiner.add(prop.getHttpProxy().toString());
        }
        if (prop.getNat64Prefix() != null) {
            resultJoiner.add("Nat64Prefix:");
            resultJoiner.add(getIpPrefixString(prop.getNat64Prefix()));
        }
        List<LinkProperties> stackedLinksValues = prop.getStackedLinks();
        if (!stackedLinksValues.isEmpty()) {
            StringJoiner stackedLinksJoiner = new StringJoiner(",", "Stacked: [", "]");
            stackedLinksJoiner.add(joinLinkProperties(",", stackedLinksValues));
            resultJoiner.add(stackedLinksJoiner.toString());
        }
        return resultJoiner.toString();
    }
}
