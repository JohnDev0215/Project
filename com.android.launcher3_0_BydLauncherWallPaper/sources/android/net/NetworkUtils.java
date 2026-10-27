package android.net;

import android.net.DnsResolver;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import android.util.Pair;
import com.android.net.module.util.Inet4AddressUtils;
import java.io.FileDescriptor;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkUtils {
    private static final int[] ADDRESS_FAMILIES = {OsConstants.AF_INET, OsConstants.AF_INET6};
    private static final String TAG = "NetworkUtils";

    public static native void attachDropAllBPFFilter(FileDescriptor fileDescriptor) throws SocketException;

    public static native boolean bindProcessToNetwork(int i);

    @Deprecated
    public static native boolean bindProcessToNetworkForHostResolution(int i);

    public static native int bindSocketToNetwork(int i, int i2);

    public static native void detachBPFFilter(FileDescriptor fileDescriptor) throws SocketException;

    public static native int getBoundNetworkForProcess();

    public static native Network getDnsNetwork() throws ErrnoException;

    public static native TcpRepairWindow getTcpRepairWindow(FileDescriptor fileDescriptor) throws ErrnoException;

    public static native boolean protectFromVpn(int i);

    public static native boolean queryUserAccess(int i, int i2);

    public static native void resNetworkCancel(FileDescriptor fileDescriptor);

    public static native FileDescriptor resNetworkQuery(int i, String str, int i2, int i3, int i4) throws ErrnoException;

    public static native DnsResolver.DnsResponse resNetworkResult(FileDescriptor fileDescriptor) throws ErrnoException;

    public static native FileDescriptor resNetworkSend(int i, byte[] bArr, int i2, int i3) throws ErrnoException;

    public static native void setAllowNetworkingForProcess(boolean z);

    public static boolean protectFromVpn(FileDescriptor fileDescriptor) {
        return protectFromVpn(fileDescriptor.getInt$());
    }

    @Deprecated
    public static InetAddress intToInetAddress(int i) {
        return Inet4AddressUtils.intToInet4AddressHTL(i);
    }

    @Deprecated
    public static int inetAddressToInt(Inet4Address inet4Address) throws IllegalArgumentException {
        return Inet4AddressUtils.inet4AddressToIntHTL(inet4Address);
    }

    @Deprecated
    public static int prefixLengthToNetmaskInt(int i) throws IllegalArgumentException {
        return Inet4AddressUtils.prefixLengthToV4NetmaskIntHTL(i);
    }

    public static int netmaskIntToPrefixLength(int i) {
        return Integer.bitCount(i);
    }

    @Deprecated
    public static int netmaskToPrefixLength(Inet4Address inet4Address) {
        return Inet4AddressUtils.netmaskToPrefixLength(inet4Address);
    }

    @Deprecated
    public static InetAddress numericToInetAddress(String str) throws IllegalArgumentException {
        return InetAddress.parseNumericAddress(str);
    }

    public static void maskRawAddress(byte[] bArr, int i) {
        if (i < 0 || i > bArr.length * 8) {
            throw new RuntimeException("IP address with " + bArr.length + " bytes has invalid prefix length " + i);
        }
        int i2 = i / 8;
        byte b = (byte) (255 << (8 - (i % 8)));
        if (i2 < bArr.length) {
            bArr[i2] = (byte) (b & bArr[i2]);
        }
        while (true) {
            i2++;
            if (i2 >= bArr.length) {
                return;
            } else {
                bArr[i2] = 0;
            }
        }
    }

    public static InetAddress getNetworkPart(InetAddress inetAddress, int i) {
        byte[] address = inetAddress.getAddress();
        maskRawAddress(address, i);
        try {
            return InetAddress.getByAddress(address);
        } catch (UnknownHostException e) {
            throw new RuntimeException("getNetworkPart error - " + e.toString());
        }
    }

    public static int getImplicitNetmask(Inet4Address inet4Address) {
        return Inet4AddressUtils.getImplicitNetmask(inet4Address);
    }

    public static Pair<InetAddress, Integer> parseIpAndMask(String str) {
        int i;
        InetAddress numericAddress = null;
        try {
            String[] strArrSplit = str.split("/", 2);
            i = Integer.parseInt(strArrSplit[1]);
            try {
                numericAddress = InetAddress.parseNumericAddress(strArrSplit[0]);
            } catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException | IllegalArgumentException unused) {
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException | NumberFormatException | IllegalArgumentException unused2) {
            i = -1;
        }
        if (numericAddress == null || i == -1) {
            throw new IllegalArgumentException("Invalid IP address and mask " + str);
        }
        return new Pair<>(numericAddress, Integer.valueOf(i));
    }

    public static InetAddress hexToInet6Address(String str) throws IllegalArgumentException {
        try {
            return numericToInetAddress(String.format(Locale.US, "%s:%s:%s:%s:%s:%s:%s:%s", str.substring(0, 4), str.substring(4, 8), str.substring(8, 12), str.substring(12, 16), str.substring(16, 20), str.substring(20, 24), str.substring(24, 28), str.substring(28, 32)));
        } catch (Exception e) {
            Log.e(TAG, "error in hexToInet6Address(" + str + "): " + e);
            throw new IllegalArgumentException(e);
        }
    }

    public static String[] makeStrings(Collection<InetAddress> collection) {
        String[] strArr = new String[collection.size()];
        Iterator<InetAddress> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            strArr[i] = it.next().getHostAddress();
            i++;
        }
        return strArr;
    }

    public static String trimV4AddrZeros(String str) {
        if (str == null) {
            return null;
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length != 4) {
            return str;
        }
        StringBuilder sb = new StringBuilder(16);
        for (int i = 0; i < 4; i++) {
            try {
                if (strArrSplit[i].length() > 3) {
                    return str;
                }
                sb.append(Integer.parseInt(strArrSplit[i]));
                if (i < 3) {
                    sb.append('.');
                }
            } catch (NumberFormatException unused) {
                return str;
            }
        }
        return sb.toString();
    }

    private static TreeSet<IpPrefix> deduplicatePrefixSet(TreeSet<IpPrefix> treeSet) {
        TreeSet<IpPrefix> treeSet2 = new TreeSet<>(treeSet.comparator());
        for (IpPrefix ipPrefix : treeSet) {
            Iterator<IpPrefix> it = treeSet2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().containsPrefix(ipPrefix)) {
                        break;
                    }
                } else {
                    treeSet2.add(ipPrefix);
                    break;
                }
            }
        }
        return treeSet2;
    }

    public static long routedIPv4AddressCount(TreeSet<IpPrefix> treeSet) {
        long prefixLength = 0;
        for (IpPrefix ipPrefix : deduplicatePrefixSet(treeSet)) {
            if (!ipPrefix.isIPv4()) {
                Log.wtf(TAG, "Non-IPv4 prefix in routedIPv4AddressCount");
            }
            prefixLength += 1 << (32 - ipPrefix.getPrefixLength());
        }
        return prefixLength;
    }

    public static BigInteger routedIPv6AddressCount(TreeSet<IpPrefix> treeSet) {
        BigInteger bigIntegerAdd = BigInteger.ZERO;
        for (IpPrefix ipPrefix : deduplicatePrefixSet(treeSet)) {
            if (!ipPrefix.isIPv6()) {
                Log.wtf(TAG, "Non-IPv6 prefix in routedIPv6AddressCount");
            }
            bigIntegerAdd = bigIntegerAdd.add(BigInteger.ONE.shiftLeft(128 - ipPrefix.getPrefixLength()));
        }
        return bigIntegerAdd;
    }

    public static boolean isWeaklyValidatedHostname(String str) {
        if (!str.matches("^[a-zA-Z0-9_.-]+$")) {
            return false;
        }
        for (int i : ADDRESS_FAMILIES) {
            if (Os.inet_pton(i, str) != null) {
                return false;
            }
        }
        return true;
    }
}
