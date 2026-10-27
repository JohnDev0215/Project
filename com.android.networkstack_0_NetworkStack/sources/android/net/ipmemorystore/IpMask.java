package android.net.ipmemorystore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class IpMask {
    private static final Integer[] sIpv4Array = {2};
    private static final Integer[] sIpv6Array = {1, 2, 3, 4, 5, 6};
    private static final Integer[] sMacArray = {1, 2, 3, 4};
    private static final List<Integer> sIpv4List = new ArrayList(Arrays.asList(sIpv4Array));
    private static final List<Integer> sIpv6List = new ArrayList(Arrays.asList(sIpv6Array));

    static {
        new ArrayList(Arrays.asList(sMacArray));
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
}
