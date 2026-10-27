package android.net.util;

import android.net.INetd;
import android.net.MacAddress;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static String maskIpMiddle(InetAddress ipaddress) {
        if (ipaddress == null) {
            return "NULL";
        }
        String ip = ipaddress.toString();
        if (ip.isEmpty()) {
            return "EMTPY";
        }
        return maskIpMiddleString(ip);
    }

    public static String maskIpMiddleString(String ip) {
        if (ip == null || ip.isEmpty()) {
            return "EMPTY";
        }
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
            return "EMTPY";
        }
        return maskMacString(mac);
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

    public static String maskIpMiddleArray(InetAddress[] ipaddress) {
        if (ipaddress == null) {
            return "NULL";
        }
        if (ipaddress.length == 0) {
            return "EMPTY";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (InetAddress addr : ipaddress) {
            sb.append(maskIpMiddle(addr));
        }
        sb.append("]");
        return sb.toString();
    }

    public static String maskIpMiddleList(List<? extends InetAddress> ipaddress) {
        if (ipaddress == null) {
            return "NULL";
        }
        if (ipaddress.size() == 0) {
            return "EMPTY";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (InetAddress addr : ipaddress) {
            sb.append(maskIpMiddle(addr));
        }
        sb.append("]");
        return sb.toString();
    }
}
