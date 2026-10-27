package android.net.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.MacAddress;
import android.provider.DeviceConfig;
import android.util.Log;
import android.util.SparseArray;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class NetworkStackUtils {
    public static final String[] DEFAULT_CAPTIVE_PORTAL_FALLBACK_PROBE_SPECS = new String[0];
    public static final String[] DEFAULT_CAPTIVE_PORTAL_HTTP_URLS = {"http://connectivitycheck.gstatic.com/generate_204"};
    public static final String[] DEFAULT_CAPTIVE_PORTAL_HTTPS_URLS = {"https://www.google.com/generate_204"};

    private static native void addArpEntry(byte[] bArr, byte[] bArr2, String str, FileDescriptor fileDescriptor) throws IOException;

    public static native void attachControlPacketFilter(FileDescriptor fileDescriptor, int i) throws SocketException;

    public static native void attachDhcpFilter(FileDescriptor fileDescriptor) throws SocketException;

    public static native void attachRaFilter(FileDescriptor fileDescriptor, int i) throws SocketException;

    public static int saturatedCast(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j;
    }

    static {
        System.loadLibrary("networkstackutilsjni");
    }

    public static <T> boolean isEmpty(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static void closeSocketQuietly(FileDescriptor fileDescriptor) {
        try {
            SocketUtils.closeSocket(fileDescriptor);
        } catch (IOException unused) {
        }
    }

    public static int[] convertToIntArray(List<Integer> list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }

    public static long[] convertToLongArray(List<Long> list) {
        long[] jArr = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            jArr[i] = list.get(i).longValue();
        }
        return jArr;
    }

    public static <T> boolean any(SparseArray<T> sparseArray, Predicate<T> predicate) {
        for (int i = 0; i < sparseArray.size(); i++) {
            if (predicate.test(sparseArray.valueAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static String getDeviceConfigProperty(String str, String str2, String str3) {
        String property = DeviceConfig.getProperty(str, str2);
        return property != null ? property : str3;
    }

    public static int getDeviceConfigPropertyInt(String str, String str2, int i) {
        String deviceConfigProperty = getDeviceConfigProperty(str, str2, null);
        if (deviceConfigProperty == null) {
            return i;
        }
        try {
            return Integer.parseInt(deviceConfigProperty);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static int getDeviceConfigPropertyInt(String str, String str2, int i, int i2, int i3) {
        int deviceConfigPropertyInt = getDeviceConfigPropertyInt(str, str2, i3);
        return (deviceConfigPropertyInt < i || deviceConfigPropertyInt > i2) ? i3 : deviceConfigPropertyInt;
    }

    public static boolean isFeatureEnabled(Context context, String str, String str2) {
        return isFeatureEnabled(context, str, str2, false);
    }

    public static boolean isFeatureEnabled(Context context, String str, String str2, boolean z) {
        try {
            int deviceConfigPropertyInt = getDeviceConfigPropertyInt(str, str2, 0);
            return (deviceConfigPropertyInt == 0 && z) || (deviceConfigPropertyInt != 0 && context.getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode() >= ((long) deviceConfigPropertyInt));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("NetworkStackUtils", "Could not find the package name", e);
            return false;
        }
    }

    public static void addArpEntry(Inet4Address inet4Address, MacAddress macAddress, String str, FileDescriptor fileDescriptor) throws IOException {
        addArpEntry(macAddress.toByteArray(), inet4Address.getAddress(), str, fileDescriptor);
    }

    public static String addressAndPortToString(InetAddress inetAddress, int i) {
        return String.format(inetAddress instanceof Inet6Address ? "[%s]:%d" : "%s:%d", inetAddress.getHostAddress(), Integer.valueOf(i));
    }

    public static boolean isIPv6ULA(InetAddress inetAddress) {
        return (inetAddress instanceof Inet6Address) && (inetAddress.getAddress()[0] & 254) == 252;
    }
}
