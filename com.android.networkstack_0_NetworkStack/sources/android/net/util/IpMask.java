package android.net.util;

import android.net.MacAddress;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class IpMask {
    private static final Integer[] sIpv4Array = {2};
    private static final Integer[] sIpv6Array = {1, 2, 3, 4, 5, 6};
    private static final Integer[] sMacArray = {1, 2, 3, 4};
    private static final List<Integer> sIpv4List = new ArrayList(Arrays.asList(sIpv4Array));
    private static final List<Integer> sIpv6List = new ArrayList(Arrays.asList(sIpv6Array));
    private static final List<Integer> sMacList = new ArrayList(Arrays.asList(sMacArray));

    public static String maskIpMiddle(InetAddress inetAddress) {
        if (inetAddress == null) {
            return "NULL";
        }
        String string = inetAddress.toString();
        return string.isEmpty() ? "EMTPY" : maskIpMiddleString(string);
    }

    public static String maskIpMiddleString(String str) {
        String str2;
        int i;
        if (str == null || str.isEmpty()) {
            return "EMPTY";
        }
        int i2 = 4;
        List<Integer> list = sIpv4List;
        String str3 = ":";
        if (str.contains(":")) {
            i2 = 8;
            list = sIpv6List;
            str2 = ":";
        } else {
            str3 = "\\.";
            str2 = ".";
        }
        String[] strArrSplit = str.split(str3);
        int iMin = Math.min(strArrSplit.length, i2);
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        while (i3 < iMin) {
            if (list.contains(Integer.valueOf(i3)) && i3 != iMin - 1) {
                sb.append(strArrSplit[i3].replaceAll(".", "*"));
                sb.append(i3 < i ? str2 : "");
            } else {
                sb.append(strArrSplit[i3]);
                sb.append(i3 < iMin + (-1) ? str2 : "");
            }
            i3++;
        }
        return sb.toString();
    }

    public static String maskMac(MacAddress macAddress) {
        if (macAddress == null) {
            return "NULL";
        }
        String string = macAddress.toString();
        return string.isEmpty() ? "EMTPY" : maskMacString(string);
    }

    public static String maskMacString(String str) {
        int i;
        String[] strArrSplit = str.split(":");
        int iMin = Math.min(strArrSplit.length, 6);
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < iMin) {
            if (sMacList.contains(Integer.valueOf(i2)) && i2 != iMin - 1) {
                sb.append(strArrSplit[i2].replaceAll(".", "*"));
                sb.append(i2 < i ? ":" : "");
            } else {
                sb.append(strArrSplit[i2]);
                sb.append(i2 < iMin + (-1) ? ":" : "");
            }
            i2++;
        }
        return sb.toString();
    }

    public static String maskIpMiddleList(List<? extends InetAddress> list) {
        if (list == null) {
            return "NULL";
        }
        if (list.size() == 0) {
            return "EMPTY";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        Iterator<? extends InetAddress> it = list.iterator();
        while (it.hasNext()) {
            stringBuffer.append(maskIpMiddle(it.next()));
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
